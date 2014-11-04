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
    JLabel lUsuario = new JLabel("");
    
    public Login() {
        this.setTitle("Proporcione una contraseña");
        this.setSize(315, 120);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        addComponentes();
//        lUsuario.setText(usuario);
//        this.setVisible(true);
    }
    
    private void addComponentes() {
        JPanel login = new JPanel();
        JPanel pSur = new JPanel();
        login.setLayout(new GridLayout(2, 2));
        JLabel lUser = new JLabel("Usuario");
       // JLabel lUsuario = new JLabel("");
        JLabel lPass = new JLabel("Contraseña");
        JTextField tPass = new JTextField();
        login.add(lUser);
        login.add(lUsuario);
        login.add(lPass);
        login.add(tPass);
        pSur.add(bAceptar);
        pSur.add(bCancelar);
        
        this.setAlwaysOnTop(true);
        this.add(pSur, "South");
        this.add(login, "Center");
        
    }
    
    public void addEventos(OyenteConexion o){
            bAceptar.addActionListener(o);
            bCancelar.addActionListener(o);
    }

    public JLabel getlUsuario() {
        return lUsuario;
    }

    public void setlUsuario(JLabel lUsuario) {
        this.lUsuario = lUsuario;
    }
    
}
