package com.code;

import com.userinterface.Browser2;
import com.userinterface.Item;
import java.awt.Desktop;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Alan Rodriguez
 */
public final class Control{

    private int countPath, maxOutput = 5;
    public static String fileName;
    private boolean case_insensitive;
    private final ArrayList<File> paths;
    private final ArrayList<BrowserThread> browsers;
    
    private final Browser2 b;
    /**
     * Crea los hilos que buscaran los archivos en los directorios espesificados
     */
    private void makeThreads() {
        for (int i = 0; i < countPath; i++) {
            browsers.add(new BrowserThread( fileName, paths.get(i), case_insensitive ));
            browsers.get(i).start();
        }
    }
    /**
     * Metodo constructor, crea la lista de los buscadores y crea la lista de los
     * directorios donde se buscara
     * @param b 
     */
    public Control(Browser2 b) {
        browsers = new ArrayList();
        paths = new ArrayList();
        
        //Aqui cambia las rutas o agrega mas lo ke kieras
        addPath("/home/rossopt/Downloads");
        
        //b es solo para cuando salga el primer item, tener la posicion del padre
        //para tener todo en orden
        this.b = b;
    }
    /**
     * Crea los hilos, si hay hilos en ejecucion los borra y crea nuevos
     */
    public void makeSearch() {
        
        if(fileName.equalsIgnoreCase("")){
            return;
        }
        
        //Si el array de los hilos no esta vacio, que los borre todos
        if(!browsers.isEmpty()){
            browsers.clear();
        }
        //Crea los hilos para realizar las busquedas
        makeThreads();
        
        try {
            //El Thread.sleep es para esperar a que los hilos que estan buscando cosas tengan algo, si no esperamos no aparece nada
            Thread.sleep(5);
            //Este metodo es para mostrar maxOutput en forma de items
            showFiles();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Ignora esto no recuerdo ni porque lo puse pero capaz y al final sirve para algo
     */
    public void checkThreads() {
        int i = 0;
        int max = browsers.size();
        ArrayList<File> list = new ArrayList();
        while(i<max) {
            try {
                browsers.get(i).join();
                list.addAll(browsers.get(i).getFiles());
                i++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for(File file : list) {
            try {
                Desktop.getDesktop().open(file);
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    /**
     * Metodo para setear case_insensitive, si quiere que se busquen exactas las
     * palabras o dar cierto margen
     * @param case_insensitive 
     */
    public void setCase_insensitive(boolean case_insensitive) {
        this.case_insensitive = case_insensitive;
    }
    /**
     * Regresa el numero de directorios en los que se esta buscando
     * @return int numero total de directorios
     */
    public int getCountPath() {
        return countPath;
    }
    /**
     * Setea el numero total de directorios en los que se esta buscando
     */
    private void setCountPath() {
        this.countPath = this.paths.size();
    }
    /**
     * Muestra los primeros maxOutput items en el buscador
     * crea las instancias de Item necesarias para mostrar
     */    
    private ArrayList<Item> ls = new ArrayList();
    public void showFiles(){
        //Limpia la lista en caso de que tena un objeto antes
        clearLs();
        ArrayList<File> tf = new ArrayList();
        for(BrowserThread t : browsers){
            tf.addAll(t.getFiles());
        }
        Item last = null;
        for (int i = 0; i < tf.size(); i++) {
            
            if (i == maxOutput) {
                break;
            }
            
            ls.add(new Item(b, false, tf.get(i).getName(), tf.get(i).getPath(), last));
            last = ls.get(i);
        }
    }
    /**
     * Borra todos los items que se hayan recabado
     */
    private void clearLs() {
        if(ls.isEmpty())
            return;
        for(Item i : ls){
            i.setVisible(false);
        }
        ls.clear();
    }
    
    /**
     * Metodo para obtener el nombre de lo que se esta buscando
     * @return regres un string
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Setea el nombre del archivo a estarse buscando
     * @param fileName String
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    /**
     * Agrega un path a la lista en la cual se buscaran el o los archivos
     * @param path Cadena que indica el path: Formato C:\\...\\...\\
     */
    public void addPath(String path){
        File f = new File(path);
//        if(!f.exists()){
//            throw new Exception("El directorio que introdujo no existe");
//        }
        paths.add(f);
        setCountPath();
    }
    
}
