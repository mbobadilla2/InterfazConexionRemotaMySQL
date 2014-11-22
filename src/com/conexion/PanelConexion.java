package com.conexion;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.*;
import javax.swing.*;
/**
 * panel que guarda de manera temporal la informacion de las conexiones 
 *
 * @author Miguel Fernando Bobadilla Contreras
 * @author Luis Angel Pérez Muñoz
 * @author José Rubén Perez García
 * @author José Ramón Márquez Solano
 * @author Valery Abigail Cambara Gil
 * @version 6/Noviembre/2014 /A
 *
 */
public class PanelConexion extends JPanel{ //clase panelConexion es un JPanel
    private ArrayList<JLabel> infoConexion;  //Array de etiquetas para giardas la informacion de la conexion 
    
    /**
     * Inicializa el panel y agrega los componentes
     */
    public PanelConexion(){
        addComponentes();
    }
     
    /**
     * Agrega al panel las etiquetas con la informacion de la conexion
     */
    public final void addComponentes(){
        this.setLayout(new GridLayout(5, 3));
        this.setPreferredSize(new Dimension(300, 176));
        this.setBorder(BorderFactory.createLineBorder(this.getBackground(), 5));
        infoConexion = new ArrayList<>();
        infoConexion.add(new JLabel());
        infoConexion.add(new JLabel());
        infoConexion.add(new JLabel());
        infoConexion.add(new JLabel());
        infoConexion.add(new JLabel());
    }
    
    /**
     * Agrega el evento al panelConexion 
     * @param oyente oyente que hara sus eventos , de tipo MiOyente 
     */
    public void addEventos(MiOyente oyente){
        this.addMouseListener(oyente);
    }
 
    //GETTERS AND SETTERS -------------------------------------------------------------------------
    public ArrayList<JLabel> getInfoConexion() {
        return infoConexion;
    }
}
