/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JLabel;

/**
 *
 * @author Jose Ruben
 */
public class OyenteConexion extends KeyAdapter implements ActionListener {

    private MiOyente oyente;
    private MiPanel panel;
    private NuevaConexion nc;
    private ArrayList<PanelConexion> conx;
    private PanelConexion p; //conexion actual
    private Consulta consultas;
    private ConexionSQL con;
    ConexionSQL c;
    private Login l;
    private TablaConsulta tc;

    public OyenteConexion(MiOyente oyente) {
        this.oyente = oyente;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String etiq = ae.getActionCommand();

        switch (etiq) {
            case "Aceptar":

                if (validardatos(nc.tNombreConexion.getText(), nc.tPuerto.getText(), nc.tHost.getText(),
                        nc.tUsuario.getText(), nc.tpContrasenia.getPassword())) {
                    p = new PanelConexion();
                    p.getInfoConexion().get(0).setText(nc.tNombreConexion.getText());
                    p.getInfoConexion().get(1).setText(nc.tHost.getText());
                    p.getInfoConexion().get(2).setText(nc.tPuerto.getText());
                    p.getInfoConexion().get(3).setText(nc.tUsuario.getText());
                    p.getInfoConexion().get(4).setText(nc.tpContrasenia.getText());

                    p.add(new JLabel("Nombre de la conexión: "));
                    p.add(p.getInfoConexion().get(0));
                    p.add(new JLabel("Host: "));
                    p.add(p.getInfoConexion().get(1));
                    p.add(new JLabel("Puerto: "));
                    p.add(p.getInfoConexion().get(2));
                    p.add(new JLabel("Usuario: "));
                    p.add(p.getInfoConexion().get(3));
                    p.add(new JLabel("Contraseña: "));
                    //p.add(p.getDatosConx().get(4));

                    p.addEventos(oyente);

                    conx.add(p);
                    panel.agregarConexion(p);

                    try {
                        Archivo.escribirConexiones(conx, "src/cbd/conx.txt");
                    } catch (IOException ex) {
                        Logger.getLogger(OyenteConexion.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    nc.setVisible(false);
                    borrarCeldas();

                } else {
                    JOptionPane.showMessageDialog(nc, "DATOS INCORRECTOS");
                }

                break;
            case "Cancelar":
                if (JOptionPane.showConfirmDialog(panel, "Seguro que quieres Cancelar, Se elminaran los datos escritos") == 0) {
                    nc.setVisible(false);
                    borrarCeldas();

                }

                break;
            case "Probar Conexion":
                //Testea conexion 
                c = new ConexionSQL(nc.tUsuario.getText(),
                        nc.tPuerto.getText(),
                        nc.tHost.getText(), nc.tpContrasenia.getText());
               
                if (c.probarConexion()) {
                    JOptionPane.showMessageDialog(panel, "Los parámetros de la conexión son correctos");
                    c.cerrarConexion();
                    
                } else {
                    JOptionPane.showMessageDialog(panel, "Conexion Fallida", "Error en la conexion", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "Entrar":
                     //validar la contraseña
                if (validarcontrasenia(l.tPass.getPassword())) {
                    //manejar un estado ventana consulta para que no haya mas entanas 
                   
                    con = new ConexionSQL(p.getInfoConexion().get(3).getText(), p.getInfoConexion().get(2).getText(), p.getInfoConexion().get(1).getText(), p.getInfoConexion().get(4).getText());
                    con.crearConexion();
                    consultas = new Consulta(this);
                    l.setVisible(false);

                } else {
                    JOptionPane.showMessageDialog(l, "Contraseña Incorrecta", "Error al inicio de sesion", JOptionPane.ERROR_MESSAGE);
                    l.tPass.setText("");
                }
                break;
            case "Regresar":
                l.setVisible(false);
                l.tPass.setText("");

                break;
            case "Ejecutar":
                String query = consultas.getTaConsulta().getText();
                
                if ("".equals(query)) {
                    JOptionPane.showMessageDialog(null, "CONSULTA VACIA", "ERROR", JOptionPane.ERROR_MESSAGE);
                } else {
                    String queries[] = query.split(";");
                    
                    for(String qry: queries){
                        tc = new TablaConsulta(con.getStament(), qry);
                    }
                }
                break;
                
            case "Nuevo":
                if(!consultas.getTaConsulta().getText().equals("")){
                    int eleccion = JOptionPane.showConfirmDialog(consultas, "La consulta actual se perderá. ¿Está seguro?", 
                            "Advertencia", JOptionPane.OK_CANCEL_OPTION);
                    
                    if(eleccion == JOptionPane.OK_OPTION){
                        consultas.getTaConsulta().setText("");
                    }
                
                }
                break;
                
            case "Abrir":
                try {
                    if(!consultas.getTaConsulta().getText().equals("")){

                        int eleccion = JOptionPane.showConfirmDialog(consultas, "La consulta actual se perderá. ¿Está seguro?", 
                                "Advertencia", JOptionPane.OK_CANCEL_OPTION);

                        if(eleccion == JOptionPane.OK_OPTION){
                            String consultaLeida = Archivo.abrirConsulta();

                            if(!consultaLeida.equals("--1")){
                                consultas.getTaConsulta().setText(consultaLeida);
                            }
                        }
                    }
                } catch (IOException ex) {
                        JOptionPane.showMessageDialog(consultas, "Hubo un error al abrir la cosulta", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
                break;
                
            case "Guardar como...":
                    try {
                        Archivo.guardarConsultaComo(consultas.getTaConsulta().getText());
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(consultas, "Hubo un error al guardar la cosulta", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                break;
                
                // Deshabilitado temporalmente...
            case "Salir":
                    int eleccion = JOptionPane.showConfirmDialog(consultas, "¿Seguro que desea cerrar la conexión?", 
                            "Advertencia", JOptionPane.OK_CANCEL_OPTION);
                
                if(eleccion == JOptionPane.OK_OPTION){
                    consultas = null;
                    con.cerrarConexion();
                }
                
                break;
        }
    }

    public MiPanel getP() {
        return panel;
    }

    public void setPanel(MiPanel p) {
        this.panel = p;
    }

    public NuevaConexion getNc() {
        return nc;
    }

    public void setNc(NuevaConexion nc) {
        this.nc = nc;
    }

    public ArrayList<PanelConexion> getConx() {
        return conx;
    }

    public void setConx(ArrayList<PanelConexion> conx) {
        this.conx = conx;
    }

    public Login getL() {
        return l;
    }

    public void setL(Login l) {
        this.l = l;
    }

    public PanelConexion getPca() {
        return p;
    }

    public void setP(PanelConexion p) {
        this.p = p;
    }

    public ConexionSQL getCon() {
        return con;
    }
    

    private boolean validardatos(String nombcon, String puerto, String host, String usuario, char[] pass) {
        int cont = 0;
        //INVESTIGAR COMO VALIDAR EL DOMINIO Y EL HOST
        if (!"".equals(nombcon)) {
            cont++;
        }
        if (!"".equals(puerto)) {
            cont++;

        }
        if (!"".equals(host)) {
            cont++;
        }
        if (!"".equals(usuario)) {
            cont++;
        }
        //ME FALTA CONTRASEÑA
        return cont == 4;
    }

    private void borrarCeldas() {
        nc.tNombreConexion.setText("");
        nc.tHost.setText("");
        nc.tPuerto.setText("");
        nc.tUsuario.setText("");
        nc.tpContrasenia.setText("");
    }

    private boolean validarcontrasenia(char[] password) {
        boolean isCorrect = true;
        char[] correctPassword = p.getInfoConexion().get(4).getText().toCharArray();

        if (password.length != correctPassword.length) {
            isCorrect = false;
        } else {
            isCorrect = Arrays.equals(password, correctPassword);
        }

        //Zero out the password.
        Arrays.fill(correctPassword, '0');

        return isCorrect;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
    }

}
