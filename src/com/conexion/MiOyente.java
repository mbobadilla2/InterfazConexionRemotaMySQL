/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexion;

import java.awt.event.*;
import javax.swing.*;
import java.util.*;

/**
 *
 * @author Jose Ruben
 */
public class MiOyente extends MouseAdapter implements ActionListener{
    private MiPanel panel;
    private MenuEmergente menuEm;
    private PanelConexion conxActual;
    private ArrayList<PanelConexion> conexiones;
    
    // Todos los botones;
    @Override
    public void actionPerformed(ActionEvent e){
        String etiq = e.getActionCommand();
        System.out.println(etiq);
        
        // Dependiendo del botón que se haya pulsado...
        switch(etiq){
            case "Agregar Conexion":
                NuevaConexion nc = new NuevaConexion();
                break;
                
            case "Eliminar Conexion":
                // Etc...
                break;
                
            case "Borrar":
                borrarConexion();
                break;
        }
    }
    
    // Eventos para las conexiones guardadas;
    @Override
    public void mouseClicked(MouseEvent me) {
        // El click derecho del ratón es el #3;
        
        conxActual = (PanelConexion) me.getSource();
        
        System.out.println("Hola mama, soy el panel #" + conxActual.getName());
        if(me.getButton() == 1){
            Login log = new Login();
        
        // Si se oprimió click derecho en un panel, se muestra el menú emergente...
        }else if(me.getButton() == 3){
            System.out.println("Botón derecho!");
            mostrarMenuEmergente(me);
        }
    }
    
    // EN ALGUNAS OCASIONES LANZA UNA EXCEPCION IllegalComponentStateException
    // Para mostrar el menú contextual de las conexiones...
    public void mostrarMenuEmergente(MouseEvent e){
        System.out.println("Funciona");
        menuEm.show(e.getComponent(), e.getX(), e.getY());
    }

    // Borrar una conexión...
    public void borrarConexion(){
        int index = Integer.parseInt(conxActual.getName());
        
        conexiones.remove(index);
        panel.actualizarLista(index);
    }
    
    public MenuEmergente getMenuEm() {
        return menuEm;
    }

    public void setMenuEm(MenuEmergente menuEm) {
        this.menuEm = menuEm;
    }

    public ArrayList<PanelConexion> getConexiones() {
        return conexiones;
    }

    public void setConexiones(ArrayList<PanelConexion> conexiones) {
        this.conexiones = conexiones;
    }

    public MiPanel getPanel() {
        return panel;
    }

    public void setPanel(MiPanel panel) {
        this.panel = panel;
    }
    
    
}
