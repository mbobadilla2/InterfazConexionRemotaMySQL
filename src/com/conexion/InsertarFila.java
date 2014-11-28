/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author JR
 */
public class InsertarFila extends JFrame{
    private JButton agregar;
    private OyenteConexion o;
    Connection conexion;
    Statement statement;
    String database, table, url, user, password;
    ResultSet r;
    ArrayList <String> columnas = new ArrayList();
    ArrayList <String> inserciones = new ArrayList();
    
    
    public InsertarFila(String database, String table, String user, String port, String host, String password){
        this.database = database;
        this.table = table;
        this.url = "jdbc:mysql://" + host + ":" + port;
        this.user = user;
        this.password= password;
        try {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection conexion = DriverManager.getConnection(url, user, password);
        statement = conexion.createStatement();
        r = statement.executeQuery("select * from "+ database+"."+table);
            for (int x = 1; x <= r.getMetaData().getColumnCount(); x++) {
                inserciones.add(JOptionPane.showInputDialog("Dato en: "+r.getMetaData().getColumnName(x)));
                columnas.add(r.getMetaData().getColumnName(x));
            }
            
           String campos="", valores="";
            for (String columna : columnas) {
                if(campos.equals("")){
                    campos = campos+columna;
                }else{
                    campos = campos+", "+columna;
                }
            }
           
            for (String insercion : inserciones) {
                if(valores.equals("")){
                    valores = valores + "'"+insercion+"'";
                }
                else{
                    valores = valores+", '"+insercion+"'";
                }
                
            }
            
            System.out.println("insert into "+database+"."+table+"("+campos+") values ("+valores+")");
            statement.execute("insert into "+database+"."+table+"("+campos+") values ("+valores+")");
            JOptionPane.showMessageDialog(this, "Insertado!", "Exito", JOptionPane.CLOSED_OPTION);
            o.setHayCambios(true);
            
        } catch(NullPointerException npe){
            
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrio un error al insertar", "Advertencia", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        }
        
        
       
    }
}

class InsertarColumna extends JFrame{
    private JButton agregar;
    private OyenteConexion o;
    Connection conexion;
    Statement statement;
    String database, table, url, user, password;
    ResultSet r;
    ArrayList <String> columnas = new ArrayList();
    ArrayList <String> inserciones = new ArrayList();
    
    
    public InsertarColumna(String database, String table, String column, String type, String user, String port, String host, String password){
        this.database = database;
        this.table = table;
        this.url = "jdbc:mysql://" + host + ":" + port;
        this.user = user;
        this.password= password;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection conexion = DriverManager.getConnection(url, user, password);
            statement = conexion.createStatement();
            System.out.println("alter table "+database+"."+table+" add "+column+" "+type);
            statement.execute("alter table "+database+"."+table+" add "+column+" "+type);
            
            
            JOptionPane.showMessageDialog(this, "Insertado!", "Exito", JOptionPane.CLOSED_OPTION);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrio un error al insertar", "Advertencia", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
            
        }
        
        
       
    }
}
    