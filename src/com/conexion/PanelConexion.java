package com.conexion;

import javax.swing.*;
import java.util.*;

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
