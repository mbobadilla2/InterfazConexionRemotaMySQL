/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JLabel;

/**
 *
 * @author Jose Ruben
 */
public class OyenteConexion implements ActionListener {
    private MiOyente oyente;
    private MiPanel panel;
    private NuevaConexion nc;
    private ArrayList<PanelConexion> conx;
    private PanelConexion p;
    ConexionSQL c;
    
    public OyenteConexion(MiOyente oyente){
        this.oyente = oyente;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        String etiq = ae.getActionCommand();

        switch (etiq) {
            case "Aceptar":

                if (validardatos(nc.tnomConx.getText(), nc.tpuerto.getText(), nc.thost.getText(),
                        nc.tusuario.getText(), nc.tconrasenia.getText())) {
                    p = new PanelConexion();
                    p.getDatosConx().get(0).setText(nc.tnomConx.getText());
                    p.getDatosConx().get(1).setText(nc.thost.getText());
                    p.getDatosConx().get(2).setText(nc.tpuerto.getText());
                    p.getDatosConx().get(3).setText(nc.tusuario.getText());
                    p.getDatosConx().get(4).setText(nc.tconrasenia.getText());
                    
                    p.add(new JLabel("Nombre de la conexión: "));
                    p.add(p.getDatosConx().get(0));
                    p.add(new JLabel("Host: "));
                    p.add(p.getDatosConx().get(1));
                    p.add(new JLabel("Puerto: "));
                    p.add(p.getDatosConx().get(2));
                    p.add(new JLabel("Usuario: "));
                    p.add(p.getDatosConx().get(3));
                    p.add(new JLabel("Contraseña: "));
//                    p.add(p.getDatosConx().get(4));
                    
                    p.addEventos(oyente);
                    
                    conx.add(p);
                    panel.agregarConexion(p);
                    
                    try {
                        Archivo.escribirCadenas(conx, "src/cbd/conx.txt");
                    } catch (IOException ex) {
                        Logger.getLogger(OyenteConexion.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    nc.setVisible(false);
                    borrarCeldas();

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
                 c = new ConexionSQL(nc.tusuario.getText(),
                        nc.tpuerto.getText(),
                        nc.thost.getText(),nc.tconrasenia.getText());
                if (c.probarConexion()) {
                    JOptionPane.showMessageDialog(panel, "Los parámetros de la conexión son correctos");
                }else
                     JOptionPane.showMessageDialog(panel,"Conexion Fallida", "Error en la conexion", JOptionPane.ERROR_MESSAGE);
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

    private boolean validardatos(String nombcon, String puerto, String host, String usuario, String pass) {
        System.out.println(nombcon);
        System.out.println(puerto); //HAY QUE VALIDAR
        System.out.println(host);
        System.out.println(usuario);
        System.out.println(pass);
        return true;
    }

    private void borrarCeldas() {
        nc.tnomConx.setText("");
        nc.thost.setText("");
        nc.tpuerto.setText("");
        nc.tusuario.setText("");
        nc.tconrasenia.setText("");
    }

}
