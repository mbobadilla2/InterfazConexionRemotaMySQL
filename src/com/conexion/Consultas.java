
package com.conexion;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import static javax.swing.WindowConstants.HIDE_ON_CLOSE;



public class Consultas extends JFrame {
    private MiOyente oyente;
    private JButton Run = new JButton("Ejecutar");
    private JMenuItem nuevo;
    private JMenuItem abrir;
    private JMenuItem guardar;
    private JMenuItem guardarComo;
    private JMenuItem salir;
    
    public Consultas(MiOyente oyente){
        this.oyente = oyente;
        this.setSize(886,605);
         this.setLocationRelativeTo(null);
        //this.setResizable(false);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        addComponentes();
        this.setVisible(true);
}
    private void addComponentes(){
        JMenuBar menu = new JMenuBar();
        nuevo = new JMenuItem("Nuevo");
        abrir = new JMenuItem("Abrir");
        guardar = new JMenuItem("Guardar");
        guardarComo = new JMenuItem("Guardar como...");
        salir = new JMenuItem("Salir");
        
        JMenu archivo = new JMenu("Archivo");
        archivo.add(nuevo);
        archivo.add(abrir);
        archivo.add(guardar);
        archivo.add(guardarComo);
        archivo.add(salir);
        
        menu.add(archivo);
        
        JPanel pNorte=new JPanel(); 
        JPanel pSur = new JPanel();
        JPanel pEste= new JPanel();
        JTextArea lConsulta= new JTextArea(100, 100);    
        pSur.add(Run);
        
        this.setJMenuBar(menu);
        this.add(pNorte,"North");
        this.add(lConsulta,"Center");
        this.add(pSur, "South");
        
        addEventos();
       }
    
    public void addEventos(){
        nuevo.addActionListener(oyente);
    }
}
