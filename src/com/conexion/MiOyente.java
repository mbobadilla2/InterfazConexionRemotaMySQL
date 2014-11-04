/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexion;

import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;
import java.util.*;

/**
 *
 * @author Jose Ruben,Miguel Fernando,Jose Ramon,Luis Angel
 */
public class MiOyente extends MouseAdapter implements ActionListener {
    private MiPanel panel;
    private MenuEmergente menuEm;
    private PanelConexion conxActual;
    private ArrayList<PanelConexion> conexiones;
    private OyenteConexion oc = new OyenteConexion(this);
    private boolean loginActivo = false;//comprueba que no hay otra ventana de login
    private NuevaConexion nc;
    private Login login;
    
    // Todos los botones;
    @Override
    public void actionPerformed(ActionEvent e) {
        String etiq = e.getActionCommand();
        // Dependiendo del botón que se haya pulsado...
        switch (etiq) {

            case "Agregar Conexion":
                nc = new NuevaConexion();
                nc.addEventos(oc);
                oc.setConx(conexiones);
                oc.setPanel(panel);
                oc.setNc(nc);
                break;

            case "Borrar":
                if (JOptionPane.showConfirmDialog(panel, "Seguro que quieres eliminar la conexion") == 0) {
                    try {
                        //confirmacion
                        borrarConexion();
                    } catch (IOException ex) {
                        System.out.println("Error" + ex);
                        System.exit(-1);
                    }
                }

                break;
        }
    }

    // Eventos para las conexiones guardadas;
    @Override
    public void mouseClicked(MouseEvent me) {
        // El click derecho del ratón es el #3;

        conxActual = (PanelConexion) me.getSource();
        Login log = null;
       // System.out.println("Hola mama, soy el panel #" + conxActual.getName());
        
        loginActivo = login.isVisible();
        
        if (me.getButton() == 1 && !loginActivo) {
//            log = new Login(conxActual.getDatosConx().get(3).getText());
            login.getlUsuario().setText(conxActual.getDatosConx().get(3).getText());
        
            login.setVisible(true);
            
            loginActivo = true;
            // Si se oprimió click derecho en un panel, se muestra el menú emergente...
        } else if (me.getButton() == 3) {
            //System.out.println("Botón derecho!");
            mostrarMenuEmergente(me);
        } else if (loginActivo = true) {
           
            //Hacer que la ventana parpadee
        }
    }

    // EN ALGUNAS OCASIONES LANZA UNA EXCEPCION IllegalComponentStateException
    // Para mostrar el menú contextual de las conexiones...
    public void mostrarMenuEmergente(MouseEvent e) {
        //System.out.println("Funciona");
        menuEm.show(e.getComponent(), e.getX(), e.getY());
    }

    // Borrar una conexión...
    public void borrarConexion() throws IOException {
        conexiones.remove(Integer.parseInt(conxActual.getName()));
        panel.eliminarConexion();
        /*Mando el array "conexciones" al metodo que reecribirá el 
         archivo donde se encuentran todas las conexciones registradas*/
        Archivo.escribirCadenas(conexiones, "src/cbd/conx.txt");

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

    public NuevaConexion getNc() {
        return nc;
    }

    public void setNc(NuevaConexion nc) {
        this.nc = nc;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public boolean isLoginActivo() {
        return loginActivo;
    }

    public void setLoginActivo(boolean loginActivo) {
        this.loginActivo = loginActivo;
    }
}
