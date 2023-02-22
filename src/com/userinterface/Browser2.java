package com.userinterface;

import com.code.Control;
import java.awt.event.KeyEvent;

/**
 *
 * @author Alan Rodriguez
 */
public class Browser2 extends javax.swing.JFrame {
    
    /**
     * Creates new form Browser2
     */
    public Browser2() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JPanel();
        textField = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setMaximumSize(new java.awt.Dimension(500, 50));
        setMinimumSize(new java.awt.Dimension(500, 50));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(500, 50));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        background.setBackground(new java.awt.Color(255, 255, 255));

        textField.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        textField.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textField, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                .addContainerGap())
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        //Metodo abreviado para entrar a la configuracion
        if (evt.getExtendedKeyCode() == KeyEvent.VK_P){
            //Si presiona la tecla P y CTRL esta siendo presionada entonces
            //Creara una instancia de Settings para poder configurar el buscador
            //A gusto
            //Para gustos colores seniores 
            if (this.ctrlPressed) {
                Settings s = new Settings(null, false);
                this.ctrlPressed = false;
                return;
            }
        }
        //Esto nomas es para confimar que la tecla CONTROL fue presionada
        if(evt.getExtendedKeyCode() == KeyEvent.VK_CONTROL){
            this.ctrlPressed = true;
        }
        //El texto que guarda el textFieldze
        String typed = textField.getText();
        
        if(evt.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE) {
            if( typed.length() <= 0 ){
                return;
            }
            textField.setText(typed.substring(0, typed.length()-1));
        }else{
            if( !Character.isDefined(evt.getKeyChar()) ){
                //Si el caracter no esta definido se sale del metodo
                return;
            }
            textField.setText(typed+evt.getKeyChar());
        }
        c.setFileName(textField.getText());
        c.makeSearch();
        this.requestFocus();
    }//GEN-LAST:event_formKeyPressed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        if(evt.getExtendedKeyCode() == KeyEvent.VK_P){
            this.pPressed = false;
        }
        if(evt.getExtendedKeyCode() == KeyEvent.VK_CONTROL){
            this.ctrlPressed = false;
        }
    }//GEN-LAST:event_formKeyReleased

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JLabel textField;
    // End of variables declaration//GEN-END:variables
    private final Control c = new Control(this);
    private boolean ctrlPressed = false, pPressed = false;
}
