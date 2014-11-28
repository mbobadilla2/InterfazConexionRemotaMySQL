package com.conexion;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * @author Luis
 * Created on 25/11/2014, 08:44:51 PM
 * Ventana que hará actualizaciones de diferente tipo
 */

public class Modificaciones extends JFrame{ 
    
    private JComboBox basesDatos;
    private JComboBox tablas;
    private JPanel content;
    private OyenteConexion o;
    private JButton agregar;
    JTextField tt=new JTextField();

    Modificaciones(ArrayList<String> nombresBD, ArrayList<String> nombresTablasm,OyenteConexion o) {
       basesDatos=new JComboBox(nombresBD.toArray());
       this.o=o;
      // tablas =new JComboBox(nombresTablas.toArray());
       addComponentes();
       this.setSize(600,150);
       this.setResizable(false);
       this.setLocation(500,500);
       this.setDefaultCloseOperation(HIDE_ON_CLOSE);
       this.setVisible(true);
       addEventos();
                  }
    
    
    

    private void addComponentes() {
        content=new JPanel(new BorderLayout());
        JPanel pCentro=new JPanel(new GridLayout(2,1));
        JPanel pSur =new JPanel();
        JLabel b=new JLabel("Escoje la base de datos a la que deseas agregar una tabla");
        JLabel t=new JLabel("Escribe el nombre de la tabla a crear");
       
    
        agregar=new JButton("Agregar");
        
        pCentro.add(b);
        pCentro.add(basesDatos);
        pCentro.add(t);
        pCentro.add(tt);
        
        pSur.add(agregar);
        content.add(pSur,"South");
              
        content.add(pCentro,"Center");
        this.add(content);
        
     
    }

    private void addEventos() {
        agregar.addActionListener(o);
       
    }

    public JComboBox getBasesDatos() {
        return basesDatos;
    }

    public JComboBox getTablas() {
        return tablas;
    }

    public JPanel getContent() {
        return content;
    }

    public OyenteConexion getO() {
        return o;
    }

    public JButton getAgregar() {
        return agregar;
    }

    public JTextField getTt() {
        return tt;
    }
    
    

}
