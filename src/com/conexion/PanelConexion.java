package com.conexion;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.*;
import javax.swing.*;

/**
 * @author Fernando2
 * Created on Oct 28, 2014, 7:21:52 PM
 */

public class PanelConexion extends JPanel{
    private ArrayList<JLabel> datosConx;
    
    public PanelConexion(){
        addComponentes();
    }
    
    public final void addComponentes(){
        this.setLayout(new GridLayout(5, 3));
        this.setPreferredSize(new Dimension(300, 176));
        datosConx = new ArrayList<>();
        datosConx.add(new JLabel());
        datosConx.add(new JLabel());
        datosConx.add(new JLabel());
        datosConx.add(new JLabel());
        datosConx.add(new JLabel());
    }
    
    public void addEventos(MiOyente oyente){
        this.addMouseListener(oyente);
    }

    public ArrayList<JLabel> getDatosConx() {
        return datosConx;
    }
}
