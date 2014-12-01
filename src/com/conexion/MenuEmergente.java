package com.conexion;

import javax.swing.*;

/**
 * @author Miguel Fernando Bobadilla Contreras
 * @author Luis Angel Pérez Muñoz
 * @author José Rubén Perez Rodriguez
 * @author José Ramón Márquez Solano
 * @author Valery Abigail Cambara Gil
 * Created on Oct 28, 2014, 6:25:33 PM
 */

public class MenuEmergente extends JPopupMenu{
    private JMenuItem eliminarConx;
    private JMenuItem modificarConx;
    
    public MenuEmergente(){
        addComponentes();
    }
    
    public final void addComponentes(){
        eliminarConx    = new JMenuItem("Borrar");
        modificarConx   = new JMenuItem("Modificar...");
        
//        this.add(modificarConx);
//        this.add(new JSeparator());
        this.add(eliminarConx);
    }
    
    public void addEventos(MiOyente oyente){
        eliminarConx.addActionListener(oyente);
        modificarConx.addActionListener(oyente);
    }
}
