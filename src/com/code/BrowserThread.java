package com.code;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Rosa Rodriguez
 */
public class BrowserThread extends Thread{
    
    private final String lookfor;
    private final File path;
    private final boolean case_insensitive;
    private final ArrayList<File> files;
    private final Control parent;
    
    public BrowserThread(String lookfor, File path, boolean case_insensitive, Control parent) {
        this.lookfor = lookfor;
        this.path = path;
        this.case_insensitive = case_insensitive;
        files = new ArrayList();
        this.parent = parent;
    }
    
    @Override
    public void run() {
        //Si la cadena a buscar esta vacia que se rompa el hilo
        if(lookfor.equalsIgnoreCase("")){
            return;
        }
        //Si el path no existe que se rompa e; hilo
        if(!path.exists()) {
            return;
        }
        
        //Obtiene el nombre de todos los archivos del directorio
        String[] filesName = path.list();
        //Ponemos el regex o regular expresion para buscar entre los nombres
        Pattern p;
        if(case_insensitive){
            p = Pattern.compile(lookfor, Pattern.CASE_INSENSITIVE);
        } else {
            p = Pattern.compile(lookfor);
        }
        
        Matcher m;
        //Por cada String en el path se hara un regex para ver si se encuentra 
        //lo que se busca en el nombre
        for(String name : filesName) {
            if(!(this.lookfor.equalsIgnoreCase(Control.fileName))){
                this.interrupt();
                return;
            }
            m = p.matcher(name);
            if(m.find()){
                parent.addFile(new File(path+"/"+name));
            }
        }
        
    }

    public ArrayList<File> getFiles() {
        return files;
    }
    
}
