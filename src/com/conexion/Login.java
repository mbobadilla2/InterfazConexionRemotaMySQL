/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexion;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Jose Ruben
 */
public class Login extends JFrame {

    JButton bAceptar = new JButton("Aceptar");
    JButton bCancelar = new JButton("Cancelar");
    
    public Login() {
        this.setTitle("Proporcione una contraseña");
        this.setSize(315, 137);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        addComponentes();
        this.setVisible(true);
        
    }
    
    private void addComponentes() {
        JPanel login = new JPanel();
        JPanel pSur = new JPanel();
        login.setLayout(new GridLayout(2, 2));
        JLabel lUser = new JLabel("Usuario");
        JLabel lUsuario = new JLabel("");
        JLabel lPass = new JLabel("Contraseña");
        JTextField tPass = new JTextField();
        login.add(lUser);
        login.add(lUsuario);
        login.add(lPass);
        login.add(tPass);
        pSur.add(bAceptar);
        pSur.add(bCancelar);
        this.add(pSur, "South");
        this.add(login, "Center");
        
    }
}
