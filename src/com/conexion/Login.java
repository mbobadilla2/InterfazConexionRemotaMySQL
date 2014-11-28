/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexion;

import java.awt.GridLayout;
import javax.swing.*;

/**
 * Ventana que mostrara un login para ingresar a la conexion
 *
 * @author Miguel Fernando Bobadilla Contreras
 * @author Luis Angel Pérez Muñoz
 * @author José Rubén Perez García
 * @author José Ramón Márquez Solano
 * @author Valery Abigail Cambara Gil
 * @version 6/Noviembre/2014 /A
 *
 */
public class Login extends JFrame {  //login es un frame = ventana 
    //variables de clase 

    JButton bAceptar = new JButton("Entrar");
    JButton bCancelar = new JButton("Regresar");

    JLabel lUsuario = new JLabel("");

    JPasswordField tPass = new JPasswordField(); //este cuadro de texto hara que la contraseña no se vea, en cambio pone circulitos

    /**
     * Inicializa la ventana, y le agrega sus componentes
     *
     * @param void
     */
    public Login() {
        this.setTitle("Proporcione una contraseña");
        this.setSize(315, 145);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        addComponentes();
    }

    /**
     * agrega los componentes a la ventana login
     *
     * @param void
     */
    private void addComponentes() {
        
        JPanel login = new JPanel();
        JPanel pSur = new JPanel();
        login.setLayout(new GridLayout(2, 2));
        JLabel lUser = new JLabel("Usuario");
        lUser.setBorder(BorderFactory.createLineBorder(this.getBackground(), 10));
        JLabel lPass = new JLabel("Contraseña");
        lPass.setBorder(BorderFactory.createLineBorder(this.getBackground(), 10));

        
        lUsuario.setBorder(BorderFactory.createLineBorder(this.getBackground(), 10));
//        tPass.setBorder(BorderFactory.createLineBorder(this.getBackground(), 10));
        
        login.add(lUser);
        login.add(lUsuario);
        login.add(lPass);
        login.add(tPass);
        
        pSur.add(bCancelar);
        pSur.add(bAceptar);
        
        this.setAlwaysOnTop(true);
        this.add(pSur, "South");
        this.add(login, "Center");
        
        agregartips();

    }
    /**
     * Agrega los eventos a los componentes de esta ventana que los necesitan 
     * @param o oyente de tipo OyenteConexion  
     */
    public void addEventos(OyenteConexion o) {
        bAceptar.addActionListener(o);
        bAceptar.setSelected(true);
        bCancelar.addActionListener(o);
        tPass.addKeyListener(o);
        this.addWindowListener(o);
    }
    
    private void agregartips() {
      bAceptar.setToolTipText("Clic aquí para entrar a la cuenta de conexion");
      bCancelar.setToolTipText("Clic aquí para regresar a la ventana de conexiones");
    }
    
    //GETTERS AND SETTERS ---------------------------------------------------------------------------------
    public JLabel getlUsuario() {
        return lUsuario;
    }

    public void setlUsuario(JLabel lUsuario) {
        this.lUsuario = lUsuario;
    }

    public JPasswordField gettPass() {
        return tPass;
    }

    public void settPass(JPasswordField tPass) {
        this.tPass = tPass;
    }

    

}
