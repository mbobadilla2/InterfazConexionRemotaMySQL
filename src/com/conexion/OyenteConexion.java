/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexion;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.IllegalComponentStateException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author Jose Ruben
 */
public class OyenteConexion extends KeyAdapter implements ActionListener, WindowListener {

    private MiOyente oyente;
    private MiPanel panel;
    private NuevaConexion nc;
    private CrearTabla m;
    private ArrayList<PanelConexion> conx;
    protected PanelConexion p; //conexion actual
    private Consulta consultas = null;
    private ArrayList<TablaConsulta> arrayConsultas = new ArrayList<>();
    private ConexionSQL con;
    private ConexionSQL c;
    private Login l;
    private TablaConsulta tc;
    private int[] teclasPresionadas = new int[2];
    private boolean combinacionTeclas = false;
    private int tipoCombinacion = -1;
    private boolean hayCambios = false;
    private AtributosCrearTabla a;

    public OyenteConexion(MiOyente oyente) {
        this.oyente = oyente;
    }

    @Override
    public void actionPerformed(ActionEvent ae) throws IllegalComponentStateException {
        String etiq = ae.getActionCommand();

        switch (etiq) {
            // Botones para manipulación de las bd...
            case "":

                // Recargar árbol
                if (ae.getSource() == consultas.getReload()) {
                    if (hayCambios) {
                        System.out.println("arbol");
                        hayCambios = false;
                        recargarArbol();
                        consultas.setVisible(false);
                        consultas.setVisible(true);
                    }

                    // Agregar columnas...
                } else if (ae.getSource() == consultas.getAgregarColumna()) {
                    System.out.println("AgregarColumna");
                    String database = JOptionPane.showInputDialog("Base de datos que se usara");
                    String tabla = JOptionPane.showInputDialog("Tabla a la que se agregara");
                    String columna = JOptionPane.showInputDialog("Columna nueva: ");
                    String tipo = JOptionPane.showInputDialog("Tipo de dato: ");
                    InsertarColumna c = new InsertarColumna(database, tabla, columna, tipo, p.getInfoConexion().get(3).getText(), p.getInfoConexion().get(2).getText(), p.getInfoConexion().get(1).getText(), p.getInfoConexion().get(4).getText());
                    

                    // Agregar filas...
                } else if (ae.getSource() == consultas.getAgregarFila()) {
                    System.out.println("agregarfila");
  
                    String database = JOptionPane.showInputDialog("Base de datos que se usara");
                    String tabla = JOptionPane.showInputDialog("Tabla a la que se agregara");
                    InsertarFila i = new InsertarFila(database, tabla, p.getInfoConexion().get(3).getText(), p.getInfoConexion().get(2).getText(), p.getInfoConexion().get(1).getText(), p.getInfoConexion().get(4).getText());

                    // Agregar tablas...
                } else if (ae.getSource() == consultas.getAgregarTabla()) {
                    m = new CrearTabla(con.getNombresBD(), con.getNombresTablas(), this);
                    System.out.println("agregartabla");

                    // Agregar base de datos...
                } else if (ae.getSource() == consultas.getAgregarbd()) {
                    String nombrebd = "";

                    try {
                        nombrebd = JOptionPane.showInputDialog(consultas, "Ingresa el nombre de la nueva base de datos",
                                "Nueva base de datos", JOptionPane.INFORMATION_MESSAGE);
                    } catch (NullPointerException npe) {}

                    try {
                        if (!nombrebd.equals("")) {

                            con.getStament().executeUpdate("CREATE DATABASE " + nombrebd);
                            JOptionPane.showMessageDialog(consultas, "Base de datos creada con éxito");
                            //                        recargarArbol();
                            hayCambios = true;
                        }

                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(consultas, e, "Ocurrió un error", JOptionPane.ERROR_MESSAGE);

                    } catch (NullPointerException npe) {
                    }

                    // Borrar base de datos
                } else if (ae.getSource() == consultas.getBorrarbd()) {
                    System.out.println("Borrar bd");
                    try {
                        String nombrebd = JOptionPane.showInputDialog(consultas, "Ingresa el nombre de la base de datos a borrar", "");
                        if(!nombrebd.equals("")){
                            con.getStament().executeUpdate("DROP DATABASE " + nombrebd);

                            JOptionPane.showMessageDialog(consultas, "Base de datos borrada correctamente");

                            hayCambios = true;
                        }
                    } catch(SQLException e) {
                        JOptionPane.showMessageDialog(consultas, e.getMessage(), "Ocurrió un error", JOptionPane.ERROR_MESSAGE);
                    
                    } catch(NullPointerException e){}

                    // Borrar tablas
                } else if (ae.getSource() == consultas.getBorrarTabla()) {
                    System.out.println("Borrar tabla");

                    try {
                        String nombrebd = JOptionPane.showInputDialog(consultas, "¿En qué base de datos se encuentra la tabla a borrar?", "");
                        if(!nombrebd.equals("")){
                            String nombretab = JOptionPane.showInputDialog(consultas, "Ingresa el nombre de la tabla a borrar", "");
                            if(!nombretab.equals("")){
                            con.getStament().executeUpdate("USE " + nombrebd);
                            con.getStament().executeUpdate("DROP TABLE " + nombretab);

                            JOptionPane.showMessageDialog(consultas, "Tabla borrada correctamente");

                            hayCambios = true;
                            }
                        }
                    } catch(SQLException  e) {
                        JOptionPane.showMessageDialog(consultas, e.getMessage(), "Ocurrió un error", JOptionPane.ERROR_MESSAGE);
                    
                    } catch(NullPointerException e){}

                    // Borrar filas
                } else if (ae.getSource() == consultas.getBorrarFila()) {
                    System.out.println("Borrar fila");

                    try {
                        String nombrebd = JOptionPane.showInputDialog(consultas, "¿En qué base de datos se encuentra la fila?", "");
                        
                        if(!nombrebd.equals("")){
                            String nombretab = JOptionPane.showInputDialog(consultas, "¿En qué tabla se encuentra la fila?", "");
                            if(!nombretab.equals("")){
                                String nombrepk = JOptionPane.showInputDialog(consultas, "Ingresa la PK de la tabla", "Ejemplo: idLibro");
                                if(!nombrepk.equals("")){
                                    String nombrefil = JOptionPane.showInputDialog(consultas, "Ingresa el valor de la PK de la fila a borrar", "");
                                    if(!nombrefil.equals("")){
                                        con.getStament().executeUpdate("USE " + nombrebd);
                                        con.getStament().executeUpdate("DELETE FROM " + nombretab + " WHERE " + nombrepk + " = " + nombrefil);

                                        JOptionPane.showMessageDialog(consultas, "Columna borrada correctamente");

                                        hayCambios = true;
                                    }
                                }
                            }
                        }
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(consultas, e.getMessage(), "Ocurrió un error", JOptionPane.ERROR_MESSAGE);
                    } catch(NullPointerException npe){}
                    
                    // Borrar columnas
                } else if (ae.getSource() == consultas.getBorrarColumna()) {
                    
                    System.out.println("Borrar columna");

                    try {
                        String nombrebd = JOptionPane.showInputDialog(consultas, "¿En qué base de datos se encuentra la columna?", "");
                        if(!nombrebd.equals("")){
                            String nombretab = JOptionPane.showInputDialog(consultas, "¿En qué tabla se encuentra la columna a borrar?", "");
                            if(!nombretab.equals("")){
                                String nombrecol = JOptionPane.showInputDialog(consultas, "Ingresa el nombre de la columna a borrar");
                                if(!nombrecol.equals("")){
                                    con.getStament().executeUpdate("USE " + nombrebd);
                                    con.getStament().executeUpdate("ALTER TABLE " + nombretab + " DROP " + nombrecol);
                            
                                    JOptionPane.showMessageDialog(consultas, "Columna borrada correctamente");

                                    hayCambios = true;
                                }
                            }
                        }
                    } catch(SQLException  e) {
                        JOptionPane.showMessageDialog(consultas, e.getMessage(), "Ocurrió un error", JOptionPane.ERROR_MESSAGE);
                    
                    } catch(NullPointerException e){}

                }
                break;

            case "Agregar columnas":
                int contadordellaves = 0;
                int seleccionado = 0;
                int cont = 0;

                for (JCheckBox keys : a.getPk()) {
                    if (keys.isSelected()) {
                        seleccionado = cont;
                        contadordellaves++;
                    }
                    cont++;

                }
                if (contadordellaves == 0) {

                    JOptionPane.showMessageDialog(a, "Seleccione una llave primaria ");

                } else if (contadordellaves > 1) {
                    JOptionPane.showMessageDialog(a, "Solo puedes agregar una llave primaria");

                } else {

                    String agregartablaquery;
                    agregartablaquery = "CREATE TABLE " + m.getTt().getText() + " (";
                    for (int i = 0; i < a.getNumero(); i++) {
                        if (a.getNombres().get(i).getText().equals("")) {
                        } else {
                            agregartablaquery = agregartablaquery + " " + a.getNombres().get(i).getText() + " " + a.getAtipos().get(i).getSelectedItem() + " " + ((a.getNulos().get(i).isSelected()) ? "NOT NULL" : "NULL") + " "
                                    + ((a.getAutos().get(i).isSelected()) ? "AUTO_INCREMENT" : "");
                        }
                        if ((i + 1) != a.getNumero()) {
                            agregartablaquery = agregartablaquery + ",";
                        }

                    }
                    agregartablaquery = agregartablaquery + ",PRIMARY KEY(" + a.getNombres().get(seleccionado).getText() + "));";
                    try {
                        con.getStament().executeQuery("USE " + m.getBasesDatos().getSelectedItem());
                        con.getStament().executeUpdate(agregartablaquery);
                        System.out.println("EXITO AL AGREGAR ");
                        JOptionPane.showMessageDialog(consultas, "La tabla se agregó correctamente");
                        m.setVisible(false);
                        a.setVisible(false);
                        hayCambios = true;
                        //falta limpiar los textfield de a
                    } catch (SQLException | HeadlessException e) {
                        JOptionPane.showMessageDialog(consultas, e.getMessage(), "Ocurrió un error al crear las tablas", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;

            case "Agregar tabla":
                if (m.getTt().getText().equals("")) {
                    JOptionPane.showMessageDialog(m, "Debes especificar un nombre para la tabla");

                } else {
                    try {
                        int numero = Integer.parseInt(JOptionPane.showInputDialog(m, "¿Cuántas columnas quieres agregar?", "1"));

                        if (numero > 0) {
                            a = new AtributosCrearTabla(numero, this);
                        } else {
                            JOptionPane.showMessageDialog(m, "Escoge un valor positivo");
                        }

                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(m, "Ingresa un valor numérico");
                    }
                }

                break;

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

                    oyente.habilitarBotones();

                    try {
                        Archivo.escribirConexiones(conx, "src/cbd/conx.txt");
                    } catch (IOException ex) {
                        Logger.getLogger(OyenteConexion.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    nc.setVisible(false);
                    borrarCeldas();

                } else {
                    JOptionPane.showMessageDialog(nc, "Debes llenar todos los campos");
                }

                break;

            case "Cancelar":
                cancelarNuevaConexion();
                break;

            case "Probar Conexion":
                //Testea conexion 
                if (validardatos(nc.tNombreConexion.getText(), nc.tPuerto.getText(), nc.tHost.getText(),
                        nc.tUsuario.getText(), nc.tpContrasenia.getPassword())) {

                    c = new ConexionSQL(nc.tUsuario.getText(),
                            nc.tPuerto.getText(),
                            nc.tHost.getText(), nc.tpContrasenia.getText());

                    if (c.probarConexion()) {
                        JOptionPane.showMessageDialog(panel, "Los parámetros de la conexión son correctos");
                        c.cerrarConexion();

                    } else {
                        JOptionPane.showMessageDialog(panel, "Conexion Fallida", "Error en la conexion", JOptionPane.ERROR_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(panel, "Debes llenar todos los campos");
                }
                break;

            case "Entrar":
                //validar la contraseña
                con = new ConexionSQL(p.getInfoConexion().get(3).getText(), p.getInfoConexion().get(2).getText(), p.getInfoConexion().get(1).getText(), p.getInfoConexion().get(4).getText());

                if (con.probarConexion()) {
                    if (validarcontrasenia(l.tPass.getPassword())) {
                        //manejar un estado ventana consulta para que no haya mas entanas 

                        con.crearConexion();

                        consultas = new Consulta(this);

//                        l.setVisible(false);
                        l.dispose();
                        oyente.deshabilitarBotones();

                    } else {
                        JOptionPane.showMessageDialog(l, "Contraseña Incorrecta", "Error al inicio de sesion", JOptionPane.ERROR_MESSAGE);
                        l.tPass.setText("");
                    }
                } else {
                    JOptionPane.showMessageDialog(l, "Usuario no existe en Mysql", "Error al inicio de sesion", JOptionPane.ERROR_MESSAGE);
                    l.tPass.setText("");
                }
                break;

            case "Regresar":
                cerrarLogin();
                break;

            case "Ejecutar":
                ejecutarConsulta();
                break;

            case "Nuevo":
                if (!consultas.getTaConsulta().getText().equals("")) {
                    int eleccion = JOptionPane.showConfirmDialog(consultas, "La consulta actual se perderá. ¿Está seguro?",
                            "Advertencia", JOptionPane.OK_CANCEL_OPTION);

                    if (eleccion == JOptionPane.OK_OPTION) {
                        consultas.getTaConsulta().setText("");
                        Archivo.rutaGuardada = "";
                    }

                }
                break;

            case "Abrir":
                try {
                    if (!consultas.getTaConsulta().getText().equals("")) {

                        int eleccion = JOptionPane.showConfirmDialog(consultas, "La consulta actual se perderá. ¿Está seguro?",
                                "Advertencia", JOptionPane.OK_CANCEL_OPTION);

                        if (eleccion == JOptionPane.OK_OPTION) {
                            String consultaLeida = Archivo.abrirConsulta();
                            consultas.getTaConsulta().setText(consultaLeida);
                        }
                    } else {
                        String consultaLeida = Archivo.abrirConsulta();
                        consultas.getTaConsulta().setText(consultaLeida);
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(consultas, "Hubo un error al abrir la cosulta", "Error", JOptionPane.ERROR_MESSAGE);
                }

                break;

            case "Guardar como...":
                try {
                    Archivo.guardarConsultaComo(consultas.getTaConsulta().getText());
                    Archivo.rutaGuardada = "";
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(consultas, "Hubo un error al guardar la cosulta", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;

            case "Salir":
                cerrarConsultas();

                break;

            case "Guardar":

                try {
                    if (Archivo.guardar(consultas.getTaConsulta().getText())) {
                        break;

                    } else {
                        Archivo.guardarConsultaComo(consultas.getTaConsulta().getText());
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(consultas, "Hubo un error al guardar la cosulta", "Error", JOptionPane.ERROR_MESSAGE);
                }

                break;
        }
    }

    private void ejecutarConsulta() throws IllegalComponentStateException {
        String query = consultas.getTaConsulta().getText();
        String queries[];
        String consulta;

        boolean use = false;

        // Esto es para la combinacion de teclas en el área de consultas...
        combinacionTeclas = false;

        if ("".equals(query)) {
            JOptionPane.showMessageDialog(null, "Área de consulta vacía", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // Si el string empieza con use, asuminos que se especifica base de datos
            // si no, se hace consulta sin especificarla con use...
            if (query.toUpperCase().trim().startsWith("USE")) {
                use = true;
            }

            try {

                if (use) {
                    queries = query.trim().split(";");
                    con.getStament().executeQuery(queries[0]);

                    consulta = queries[1];
                } else {
                    consulta = query.trim();
                }

                // Tipo 0 = Actualización
                // Tipo 1 = Selección
                int tipoCons;

                if (con.getStament().execute(consulta)) {
                    tipoCons = 1;
                } else {
                    tipoCons = 0;
                    hayCambios = true;
                }

                System.out.println("Tipo cons " + tipoCons);
                if (tipoCons == 0) {
                    JOptionPane.showMessageDialog(null, "Consulta realizada con exito", "Exito", JOptionPane.CLOSED_OPTION);
                }
//                        tc = new TablaConsulta(con.getStament(), queries[1], consultas.getOperacion().getSelectedIndex());
                tc = new TablaConsulta(con.getStament(), consulta, tipoCons, this);

            } catch (SQLException ex) {
//                    
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error en la instrucción SQL", JOptionPane.ERROR_MESSAGE);
            } catch (IndexOutOfBoundsException ie) {
                JOptionPane.showMessageDialog(null, "No se seleccionó base de datos", "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println("Te saliste!");
            }
        }
    }

    public void recargarArbol() {
        JTree arbolito = consultas.crearArbol();
        consultas.setArbol(arbolito);
        JScrollPane scrollito = new JScrollPane(arbolito);
        scrollito.setPreferredSize(new Dimension(185, 385));
        consultas.setAr(scrollito);
        consultas.getContent().remove(3);

        consultas.getContent().add(scrollito, "West");
        consultas.getContent().updateUI();
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

    public void cancelarNuevaConexion() {
        int opcion = JOptionPane.showConfirmDialog(panel, "Se eliminará la nueva conexión", "Cancelar nueva conexión", JOptionPane.OK_CANCEL_OPTION);

        if (opcion == JOptionPane.OK_OPTION) {
            nc.dispose();
            borrarCeldas();
            oyente.habilitarBotones();
        }
    }

    public void cerrarLogin() {
        l.dispose();
        oyente.habilitarBotones();
    }

    public void cerrarConsultas() {
        int eleccion = JOptionPane.showConfirmDialog(consultas, "¿Seguro que desea cerrar la conexión?",
                "Advertencia", JOptionPane.OK_CANCEL_OPTION);

        if (eleccion == JOptionPane.OK_OPTION) {
            for (TablaConsulta tablaCons : arrayConsultas) {
                tablaCons.dispose();
            }
            arrayConsultas = new ArrayList<>();
            consultas.dispose();
            con.cerrarConexion();
            oyente.habilitarBotones();
        }
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
        if (e.getKeyCode() == 10 && l.isVisible()) {
            //validar la contraseña

            con = new ConexionSQL(p.getInfoConexion().get(3).getText(), p.getInfoConexion().get(2).getText(), p.getInfoConexion().get(1).getText(), p.getInfoConexion().get(4).getText());

            if (con.probarConexion()) {
                if (validarcontrasenia(l.tPass.getPassword())) {
                    //manejar un estado ventana consulta para que no haya mas entanas 

                    con.crearConexion();

                    consultas = new Consulta(this);

                    l.setVisible(false);

                } else {
                    JOptionPane.showMessageDialog(l, "Contraseña Incorrecta", "Error al inicio de sesion", JOptionPane.ERROR_MESSAGE);
                    l.tPass.setText("");
                }
            } else {
                JOptionPane.showMessageDialog(l, "Usuario no existe en Mysql", "Error al inicio de sesion", JOptionPane.ERROR_MESSAGE);
                l.tPass.setText("");
            }
        } else if (e.getSource().equals(tc)) {

            if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
                teclasPresionadas[0] = e.getKeyCode();

            } else if (e.getKeyCode() == KeyEvent.VK_W) {
                teclasPresionadas[1] = e.getKeyCode();
            }

            if (teclasPresionadas[0] == KeyEvent.VK_CONTROL && teclasPresionadas[1] == KeyEvent.VK_W) {
                combinacionTeclas = true;
                tipoCombinacion = 2;
            }

        } else if (!l.isVisible() && consultas != null) {

            if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
                teclasPresionadas[0] = e.getKeyCode();

            } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                teclasPresionadas[1] = e.getKeyCode();
            }

            if (teclasPresionadas[0] == KeyEvent.VK_SHIFT && teclasPresionadas[1] == KeyEvent.VK_ENTER) {
                combinacionTeclas = true;
                tipoCombinacion = 1;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        teclasPresionadas[0] = 1;
        teclasPresionadas[1] = 1;

        if (combinacionTeclas) {
            combinacionTeclas = false;

            if (tipoCombinacion == 1) {
                System.out.println("-----Control + Enter! ");
                ejecutarConsulta();

            }//else if(tipoCombinacion == 2){
//                System.out.println("----- Control + W!");
//                e.getComponent().setVisible(false);
//            }

            tipoCombinacion = -1;
        }
//        System.out.println("Teclas liberadas");
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("Vas a cerrar la ventana de nueva consulta!");

        // Si el evento proviene de un objeto de la clase de nc (NuevaConexion)...
        if (e.getSource().getClass().isInstance(nc)) {
            cancelarNuevaConexion();

            // Si proviene de un objeto de la clase de l (Login)...
        } else if (e.getSource().getClass().isInstance(l)) {
            cerrarLogin();

            // Si proviene de un objeto de la clase de consultas (Consulta)...
        } else if (e.getSource().getClass().isInstance(consultas)) {
            cerrarConsultas();

            // Si proviene de un objeto JFrame (El principal)
        } else if (e.getSource().getClass().isInstance(new JFrame())) {
            int opcion = JOptionPane.showConfirmDialog(null, "Se cerrará el programa", "Advertencia", JOptionPane.OK_CANCEL_OPTION);

            if (opcion == JOptionPane.OK_OPTION) {
                System.exit(-1);
            }
        }

    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    public MiPanel getP() {
        return panel;
    }

    public void setHayCambios(boolean hayCambios) {
        this.hayCambios = hayCambios;
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

    public ArrayList<TablaConsulta> getArrayConsultas() {
        return arrayConsultas;
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
}
