package com.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta Clase simula una Conexion SQL
 *
 * @author Miguel Fernando Bobadilla Contreras
 * @author Luis Angel Pérez Muñoz
 * @author José Rubén Perez García
 * @author José Ramón Márquez Solano
 * @author Valery Abigail Cambara Gil
 * @version 6/Noviembre/2014 /A
 *
 */
public class ConexionSQL {

    //variables de clase 
    private String bd = "";
    private String user;
    private String port;
    private String host;
    private String password;
    private String url;
    private Connection conexion = null;
    private Statement stament;
    private ArrayList<String> nombresBD = new ArrayList();
    private ArrayList<String> nombresTablas = new ArrayList();

    /**
     * Constructor que inicializa el string que se pasara para hacer la conexion
     *
     * @param user Usuario con el que se conectara a la base de datos , debe
     * estar registrado en la base de datos mysql.user
     * @param port Puerto con el que se conetara a la base de datos, usualmente
     * siempre es 3306
     * @param host Direccion ip del servidor al cual nos vamos a conectar
     * @param password Es la contraseña con la que esta registrado el usuario
     */
    public ConexionSQL(String user, String port, String host, String password) {

        this.user = user;
        this.port = port;
        this.host = host;
        this.password = password;
        this.url = "jdbc:mysql://" + host + ":" + port;
    }

    /**
     * Prueba si la conexion con los datos es correta
     *
     * @return boolean true para conexion exitosa, false para fallida
     */
    public boolean probarConexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conexion = DriverManager.getConnection(url, user, password);

            return conexion != null;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            System.out.println(e);
            
        }
        return false;

    }

    public void cerrarConexion() {
        try {
            conexion.close();
        } catch (SQLException ex) {
            System.out.println("Error" + ex);
        }

    }

    public void crearConexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conexion = DriverManager.getConnection(url, user, password);
            stament = conexion.createStatement();
            guardarNombresBD();
        } catch (ClassNotFoundException ex) {
            System.out.println("Error" + ex);
        } catch (InstantiationException | IllegalAccessException | SQLException ex) {
            System.out.println("Error" + ex);
        }
    }

    private void guardarNombresBD() {
        try {
            ResultSet db = stament.executeQuery("show databases");
            ResultSet tablas;

            while (db.next()) {
                for (int i = 1; i <= db.getMetaData().getColumnCount(); i++) {
                    nombresBD.add(db.getString(i));
                    Statement stbd = conexion.createStatement();
                    stbd.executeQuery("use " + db.getString(i) + ";");
                    Statement sta = conexion.createStatement();
                    tablas = sta.executeQuery("show tables");
                    while (tablas.next()) {
                        for (int j = 1; j <= tablas.getMetaData().getColumnCount(); j++) {
                            nombresTablas.add(tablas.getString(j));
                        }
                      
                    }
                      nombresTablas.add("*");
                }
            }
           
        } catch (SQLException ex) {
            System.out.println("Error fja" + ex);
        }

    }

    //GETTERS Y SETTERS ------------------------------------------------------------------------------------------------
    public Statement getStament() {
        return stament;
    }

    public void setStament(Statement stament) {
        this.stament = stament;
    }

    public ArrayList<String> getNombresBD() {
        return nombresBD;
    }

    public ArrayList<String> getNombresTablas() {
        return nombresTablas;
    }

    public String getUser() {
        return user;
    }

    public String getPort() {
        return port;
    }

    public String getHost() {
        return host;
    }

    public String getUrl() {
        return url;
    }
   
}
