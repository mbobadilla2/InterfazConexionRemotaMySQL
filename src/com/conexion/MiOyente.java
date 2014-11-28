/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexion;

import java.awt.IllegalComponentStateException;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import javax.swing.*;

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
    private boolean ncActivo = false;
    private boolean ventanaActiva = false;

    // Todos los botones;
    @Override
    public void actionPerformed(ActionEvent e) throws IllegalComponentStateException{
        String etiq = e.getActionCommand();
        // Dependiendo del botón que se haya pulsado...
        switch (etiq) {

            case "Agregar Conexion":
                if(!ventanaActiva){
                    nc = new NuevaConexion();
                    nc.addEventos(oc);
                    oc.setConx(conexiones);
                    oc.setPanel(panel);
                    oc.setNc(nc);
                    deshabilitarBotones();
                    ncActivo = true;
                }
                break;

            case "Borrar":
                int opcion = JOptionPane.showConfirmDialog(panel, "¿Seguro que quieres eliminar la conexión?", "Eliminar conexión", JOptionPane.OK_CANCEL_OPTION);
                
                if (opcion == JOptionPane.OK_OPTION) {
                    try {
                        //confirmacion
                        borrarConexion();
                    } catch (IOException ex) {
                        System.out.println("Error" + ex);
                        System.exit(-1);
                    }
                }

                break;
                
            case "":
                if(e.getSource().equals(panel.getInfo())){
                    JOptionPane.showMessageDialog(null, "\nEste programa fue desarrollado por " +
                            "\n\nº Miguel Fernando Bobadilla Contreras" +
                            "\n\nº Luis Ángel Pérez Muñoz" + 
                            "\n\nº José Ramón Márquez Solano" +
                            "\n\nº José Rubén Pérez Rodríguez" +
                            "\n\nº Valery Abigail Cambara Gil" +
                            "\n\nv.1.0.",
                            "Proyecto fundamentos de base de datos", JOptionPane.INFORMATION_MESSAGE);
                }
        }
    }

    public void deshabilitarBotones(){
        panel.getbAgregarConexion().setEnabled(false);
        panel.getInfo().setEnabled(false);
        ventanaActiva = true;
    }
    
    public void habilitarBotones(){
        panel.getbAgregarConexion().setEnabled(true);
        panel.getInfo().setEnabled(true);
        ventanaActiva = false;
    }
    
    // Eventos para las conexiones guardadas;
    @Override
    public void mouseClicked(MouseEvent me) throws IllegalComponentStateException{
        // El click derecho del ratón es el #3;
        
        conxActual = (PanelConexion) me.getSource();

       // System.out.println("Hola mama, soy el panel #" + conxActual.getName());
        this.login = new Login();
        loginActivo = login.isVisible();

        if (me.getButton() == 1 && !loginActivo && !ventanaActiva) {
//            log = new Login(conxActual.getDatosConx().get(3).getText());
            deshabilitarBotones();
            login.getlUsuario().setText(conxActual.getInfoConexion().get(3).getText());

            login.setVisible(true);
            oc.setL(login);
            oc.setP(conxActual);
            login.addEventos(oc);

            loginActivo = true;
            

            // Si se oprimió click derecho en un panel, se muestra el menú emergente...
        } else if (me.getButton() == 3 && !ventanaActiva) {
            //System.out.println("Botón derecho!");
            
            // También deshabilitar botones cuando se escoja modificar conexion...
            
            mostrarMenuEmergente(me);
        } else if (loginActivo  || ventanaActiva) {

            //Hacer que la ventana parpadee
        }
    }
    
    // EN ALGUNAS OCASIONES LANZA UNA EXCEPCION IllegalComponentStateException
    // Para mostrar el menú contextual de las conexiones...
    public void mostrarMenuEmergente(MouseEvent e) throws IllegalComponentStateException{
        //System.out.println("Funciona");
        menuEm.show(e.getComponent(), e.getX(), e.getY());
    }

    // Borrar una conexión...
    public void borrarConexion() throws IOException {
        conexiones.remove(Integer.parseInt(conxActual.getName()));
        panel.eliminarConexion();
        /*Mando el array "conexciones" al metodo que reecribirá el 
         archivo donde se encuentran todas las conexciones registradas*/
        Archivo.escribirConexiones(conexiones, "src/cbd/conx.txt");

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

    public boolean isVentanaActiva() {
        return ventanaActiva;
    }

    public void setVentanaActiva(boolean ventanaActiva) {
        this.ventanaActiva = ventanaActiva;
    }
    
    public void setLoginActivo(boolean loginActivo) {
        this.loginActivo = loginActivo;
    }

}
