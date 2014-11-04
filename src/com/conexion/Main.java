/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexion;

import javax.swing.JFrame;
import javax.swing.JPanel;
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
        MiOyente o      = new MiOyente();
        MiPanel p       = new MiPanel(o);
        
        MenuEmergente m = new MenuEmergente();
        JFrame j        = new JFrame("Untittled");
        Consultas c     = new Consultas(o);
        
        //Agregar eventos de todas las ventanas...
        m.addEventos(o);
        
        //Damos acceso al menú conextual al oyente...
        o.setMenuEm(m);
        o.setConexiones(p.getConexiones());
        o.setPanel(p);
        
//        Login l = new Login();
        j.setSize(931, 547);
        j.setLocationRelativeTo(null);
        j.setResizable(false);
        j.add(p);
        j.setVisible(true);
        j.setDefaultCloseOperation(3);

    }
}
