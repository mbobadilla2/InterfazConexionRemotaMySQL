package com.conexion;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Luis
 * Created on 25/11/2014, 08:44:51 PM
 * Ventana que hará actualizaciones de diferente tipo
 */

public class CrearTabla extends JFrame{ 
    
    private JComboBox basesDatos;
    private JComboBox tablas;
    private JPanel content;
    private OyenteConexion o;
    private JButton agregar;
    JTextField tt=new JTextField();

<<<<<<< HEAD:src/com/conexion/CrearTabla.java
    CrearTabla(ArrayList<String> nombresBD, ArrayList<String> nombresTablasm, OyenteConexion o) {
        this.setTitle("Crear nueva tabla");
       basesDatos = new JComboBox(nombresBD.toArray());
       this.o = o;
=======
    Modificaciones(ArrayList<BaseDatos> nombresBD, ArrayList<Tablas> nombresTablasm,OyenteConexion o) {
       basesDatos=new JComboBox(nombresBD.toArray());
       this.o=o;
>>>>>>> FETCH_HEAD:src/com/conexion/Modificaciones.java
      // tablas =new JComboBox(nombresTablas.toArray());
       addComponentes();
       this.setSize(430, 150);
       this.setResizable(false);
       this.setLocationRelativeTo(null);
       this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       this.setVisible(true);
       addEventos();
    }
    
    
    

    private void addComponentes() {
        content = new JPanel(new BorderLayout());
        content.setBorder(BorderFactory.createLineBorder(this.getBackground(), 10));
        JPanel pCentro = new JPanel(new GridLayout(2,1));
        JPanel pSur = new JPanel();
        JLabel b = new JLabel("Agregar tabla en: ");
        JLabel t = new JLabel("Nombre de la tabla: ");
       
    
        agregar = new JButton("Agregar tabla");
        
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
