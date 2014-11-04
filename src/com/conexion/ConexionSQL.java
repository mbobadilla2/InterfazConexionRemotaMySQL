package com.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * @author Luis Created on 31/10/2014, 10:18:45 AM
 */
public class ConexionSQL {

    private String bd="biblioteca";
    private String user;
    private String port;
    private String host;
    private String password;
    private String url;
    private Connection c = null;

    public ConexionSQL(String user, String port, String host, String password) {

        this.user = user;
        this.port = port;
        this.host = host;
        this.password = password;
        this.url = "jdbc:mysql://" + host + ":" + port ;
    }

    public boolean probarConexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            c = DriverManager.getConnection(url, user, password);
            return c != null;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            System.out.println(e);
        }
        return false;

    }
    //SOLO PARA PROBAR 
    public void Consultaprueba(){
      
    }

}
