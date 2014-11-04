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
public class NuevaConexion extends JFrame {
//faltan modificadores de acceso y encapsulacion
    private final JPanel p = new JPanel();
    JLabel nomConx = new JLabel("Nombre de la conexion");
    JLabel usuario = new JLabel("Usuario");
    JLabel contrasenia = new JLabel("contrasenia");
    JLabel host = new JLabel("Host");
    JLabel puerto = new JLabel("Puerto");
    JTextField tnomConx =new JTextField();
    JTextField tusuario =new JTextField();
    JTextField tconrasenia =new JTextField();//ojo
    JTextField thost =new JTextField();
    JTextField tpuerto =new JTextField();
    JButton bAceptar =new JButton("Aceptar");
    JButton bCancelar =new JButton("Cancelar");
    JButton bPrueba =new JButton("Probar Conexion");
    
    public NuevaConexion() {
        this.setTitle("Nueva Conexion");
        this.setSize(610, 200);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.add(p);
        this.addComponentes();
        this.setVisible(true);
    }

    private void addComponentes() {
       p.setLayout(new GridLayout(5, 2));
       JPanel pSur=new JPanel();
       
       p.add(nomConx);
       p.add(tnomConx);
       p.add(host);
       p.add(thost);
       p.add(puerto);
       p.add(tpuerto);
       p.add(usuario);
       p.add(tusuario);
       p.add(contrasenia);
       p.add(tconrasenia);
       pSur.add(bCancelar);
       pSur.add(bPrueba);
       pSur.add(bAceptar);
       
       this.add(p,"Center");
       this.add(pSur,"South");
    }
    
    public void addEventos(OyenteConexion o){
         bAceptar.addActionListener(o);
         bCancelar.addActionListener(o);
         bPrueba.addActionListener(o);
    }
}
