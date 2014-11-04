/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexion;

import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 *
 * @author Jose Ruben
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame j        = new JFrame("Untittled");
        MiOyente o      = new MiOyente();
        OyenteConexion oc=new OyenteConexion(o);
        
        MiPanel p       = new MiPanel(o);
        
        
        MenuEmergente m = new MenuEmergente();
        Login log       = new Login();
        //Consultas c     = new Consultas(o);
        
        //Agregar eventos de todas las ventanas...
        m.addEventos(o);
        
        
        //Damos acceso al menú conextual al oyente...
        o.setMenuEm(m);
        o.setConexiones(p.getConexiones());
        o.setPanel(p);
        o.setLogin(log);
        // damos acceso de ventana nueva conexion al oyenteconexion
        oc.setPanel(p);        

        j.setSize(930, 470);
        j.setLocationRelativeTo(null);
        j.setResizable(false);
        j.add(p);
        j.setVisible(true);
        j.setDefaultCloseOperation(3);

    }
}
