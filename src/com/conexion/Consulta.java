package com.conexion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

/**
 * Esta es la ventana donde se haran las consultas
 *
 * @author Miguel Fernando Bobadilla Contreras
 * @author Luis Angel Pérez Muñoz
 * @author José Rubén Perez García
 * @author José Ramón Márquez Solano
 * @author Valery Abigail Cambara Gil
 * @version 6/Noviembre/2014 /A
 *
 */
public class Consulta extends JFrame { //clase consulta es una ventana 
    //Variables de clase 

    private OyenteConexion oyente;
    private JButton run;
    private JButton reload;
    private JButton agregarbd;
    private JButton agregarTabla;
    private JButton agregarFila;
    private JButton agregarColumna;
    private JButton borrarbd;
    private JButton borrarTabla;
    private JButton borrarColumna;
    private JButton borrarFila;
    private JMenuItem nuevo;
    private JMenuItem abrir;
    private JMenuItem guardar;
    private JMenuItem guardarComo;
    private JMenuItem salir;
    private JTextArea taConsulta;
    private JPanel content;
    private JScrollPane ar;
    private JTree arbol = null;

    /**
     * Incializa la ventana agregando sus componentes , ademas llama al metodo
     * addeventos,y a addComponentes
     *
     * @param oyente oyente que le dara eventos al los elementos de esta ventana
     * que los necesiten
     */
    public Consulta(OyenteConexion oyente) {
        this.setTitle("Conexión con usuario :  " + oyente.getCon().getUser() + "   en   " + oyente.getCon().getUrl());
        this.oyente = oyente;
        this.setSize(800, 480);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addComponentes();
        this.setVisible(true);
    }

    /**
     * Agrega los componentes a esta ventana
     *
     * @param void
     */
    private void addComponentes() {
        run = new JButton("Ejecutar");
        agregarbd = new JButton("");
        agregarbd.setIcon(new ImageIcon("src/icon/add_database.png"));
        agregarTabla = new JButton("");
        agregarTabla.setIcon(new ImageIcon("src/icon/table_add.png"));
        agregarFila = new JButton("");
        agregarFila.setIcon(new ImageIcon("src/icon/add_row.png"));
        agregarColumna = new JButton("");
        agregarColumna.setIcon(new ImageIcon("src/icon/add_column.png"));
        reload = new JButton("");
        reload.setIcon(new ImageIcon("src/icon/reload.png"));
        agregarColumna.setName("agregarcolumna");
        agregarFila.setName("agregarfila");
        agregarTabla.setName("agregatabla");
        agregarbd.setName("agregarbd");

        borrarbd = new JButton("");
        borrarbd.setIcon(new ImageIcon("src/icon/delete_database.png"));
        borrarTabla = new JButton("");
        borrarTabla.setIcon(new ImageIcon("src/icon/table_delete.png"));
        borrarColumna = new JButton("");
        borrarColumna.setIcon(new ImageIcon("src/icon/delete_column.png"));
        borrarFila = new JButton("");
        borrarFila.setIcon(new ImageIcon("src/icon/delete_row.png"));

        JMenuBar menu = new JMenuBar();
        nuevo = new JMenuItem("Nuevo");
        abrir = new JMenuItem("Abrir");

        guardar = new JMenuItem("Guardar");
        guardarComo = new JMenuItem("Guardar como...");
        salir = new JMenuItem("Salir");

        JMenu archivo = new JMenu("Archivo");
        archivo.add(nuevo);
        archivo.add(abrir);
        archivo.add(new JSeparator());
        archivo.add(guardar);
        archivo.add(guardarComo);
        archivo.add(new JSeparator());
        archivo.add(salir);

        menu.add(archivo);

        arbol = crearArbol();
        ar = new JScrollPane(arbol);
        ar.setPreferredSize(new Dimension(185, 385));

        JPanel pNorte = new JPanel();
        JPanel pSur = new JPanel();
        pSur.setLayout(new BorderLayout());
        JPanel pOeste = new JPanel();
        pOeste.setPreferredSize(new Dimension(48, 385));
        JScrollPane scrollOeste;
//        pOeste.setLayout(new GridLayout(4, 1));

        pOeste.add(agregarbd);
        pOeste.add(agregarTabla);
        pOeste.add(agregarFila);
        pOeste.add(agregarColumna);
        pOeste.add(new JSeparator());
        pOeste.add(borrarbd);
        pOeste.add(borrarTabla);
        pOeste.add(borrarColumna);
        pOeste.add(borrarFila);

        scrollOeste = new JScrollPane(pOeste);
        scrollOeste.setPreferredSize(new Dimension(65, 385));
//        scrollOeste.setBorder(BorderFactory.createLineBorder(this.getBackground(), 5));
        scrollOeste.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollOeste.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        taConsulta = new JTextArea();
        JScrollPane despConsulta = new JScrollPane(taConsulta);

        JPanel pSubSur = new JPanel();
        JPanel pSubSur2 = new JPanel();

        pSubSur.add(reload);
        pSubSur2.add(run);

        pSur.add(pSubSur, "West");
        pSur.add(pSubSur2, "Center");

        content = new JPanel();
        content.setLayout(new BorderLayout());
        content.setBorder(BorderFactory.createLineBorder(this.getBackground(), 10));

        this.setJMenuBar(menu);
        content.add(pNorte, "North");
        content.add(despConsulta, "Center");
        content.add(pSur, "South");
        content.add(ar, "West");
        content.add(scrollOeste, "East");

        this.add(content);

        addEventos();
        aagregartips();
    }

    /**
     * agrega los eventos a los elementos de esta ventana que los necesiten
     *
     * @param void
     */
    public void addEventos() {
        nuevo.addActionListener(oyente);
        run.addActionListener(oyente);
        reload.addActionListener(oyente);
        abrir.addActionListener(oyente);
        guardar.addActionListener(oyente);
        guardarComo.addActionListener(oyente);
        salir.addActionListener(oyente);
        agregarColumna.addActionListener(oyente);
        agregarFila.addActionListener(oyente);
        agregarTabla.addActionListener(oyente);
        agregarbd.addActionListener(oyente);

        taConsulta.setFocusable(true);
        taConsulta.addKeyListener(oyente);

        borrarbd.addActionListener(oyente);
        borrarTabla.addActionListener(oyente);
        borrarColumna.addActionListener(oyente);
        borrarFila.addActionListener(oyente);

        this.addWindowListener(oyente);
    }

    public JTree crearArbol() {
        JTree arbol;
        DefaultMutableTreeNode conexion = new DefaultMutableTreeNode("Bases de datos");
        DefaultTreeModel modelo = new DefaultTreeModel(conexion);
        arbol = new JTree(modelo);

        DefaultTreeCellRenderer render = (DefaultTreeCellRenderer) arbol.getCellRenderer();

        render.setLeafIcon(new ImageIcon("src/icon/table.png"));
        render.setClosedIcon(new ImageIcon("src/icon/db.png"));
        render.setOpenIcon(new ImageIcon("src/icon/dbmain.png"));
        int cuenta = 0;

        for (String a : oyente.getCon().getNombresBD()) {
            DefaultMutableTreeNode nombreBD = new DefaultMutableTreeNode(a);
        
            modelo.insertNodeInto(nombreBD, conexion, cuenta);
            try {
                int cuentaTabla = 0;
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection c = DriverManager.getConnection("jdbc:mysql://" + oyente.p.getInfoConexion().get(1).getText() + ":" + oyente.p.getInfoConexion().get(2).getText() + "/" + a,
                        oyente.p.getInfoConexion().get(3).getText(), oyente.p.getInfoConexion().get(4).getText());
                Statement sentencia = c.createStatement();
                ResultSet rs = sentencia.executeQuery("show tables");
                while (rs.next()) {

                    for (int x = 1; x <= rs.getMetaData().getColumnCount(); x++) {
                        //System.out.print(rs.getString(x) + "\t");
                        DefaultMutableTreeNode datoTable = new DefaultMutableTreeNode(rs.getString(x));
                        modelo.insertNodeInto(datoTable, nombreBD, cuentaTabla);
                        ArrayList<String> columnas = obtenerColumnas(rs.getString(x), a);
                        int cuentaColumna = 0;
                        for (String col : columnas) {
                            DefaultMutableTreeNode datoColumna = new DefaultMutableTreeNode(col);
                            modelo.insertNodeInto(datoColumna, datoTable, cuentaColumna);
                            cuentaColumna++;
                        }

                        cuentaTabla++;

                    }
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
                System.out.println("Error " + e);
            }

        }
        arbol.expandRow(0);
        return arbol;
    }

    public ArrayList<String> obtenerColumnas(String tabla, String address) {
        ArrayList<String> columnas = new ArrayList();
        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection c = DriverManager.getConnection("jdbc:mysql://" + oyente.p.getInfoConexion().get(1).getText() + ":" + oyente.p.getInfoConexion().get(2).getText() + "/" + address,
                    oyente.p.getInfoConexion().get(3).getText(), oyente.p.getInfoConexion().get(4).getText());
            Statement sentencia = c.createStatement();
            ResultSet rs = sentencia.executeQuery("select * from " + tabla);
            for (int x = 1; x <= rs.getMetaData().getColumnCount(); x++) {
                columnas.add(rs.getMetaData().getColumnName(x));
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
        }
        return columnas;

    }

     //GETTERS AND SETTERS ----------------------------------------------------------------------------------------------------


    public JTextArea getTaConsulta() {
        return taConsulta;
    }

    public void setTaConsulta(JTextArea taConsulta) {
        this.taConsulta = taConsulta;
    }

    public OyenteConexion getOyente() {
        return oyente;
    }

    public JButton getRun() {
        return run;
    }

    public JButton getReload() {
        return reload;
    }

    public JButton getAgregarbd() {
        return agregarbd;
    }

    public JButton getAgregarTabla() {
        return agregarTabla;
    }

    public JButton getAgregarFila() {
        return agregarFila;
    }

    public JButton getAgregarColumna() {
        return agregarColumna;
    }

    public JMenuItem getNuevo() {
        return nuevo;
    }

    public JMenuItem getAbrir() {
        return abrir;
    }

    public JMenuItem getGuardar() {
        return guardar;
    }

    public JMenuItem getGuardarComo() {
        return guardarComo;
    }

    public JMenuItem getSalir() {
        return salir;
    }

    public JScrollPane getAr() {
        return ar;
    }

    public JTree getArbol() {
        return arbol;
    }

    private void aagregartips() {
        run.setToolTipText("Ejecutar Consulta ----- shift+Enter");
        reload.setToolTipText("Actualizar lista de bases de datos");
        agregarColumna.setToolTipText("Agrega una nueva columna a una tabla");
        agregarFila.setToolTipText("Agrega una nueva fila de datos a una tabla");
        agregarTabla.setToolTipText("Agrega una nueva tabla una base de datos");
        agregarbd.setToolTipText("Crea una nueva base de datos");

        borrarbd.setToolTipText("Elimina una base de datos");
        borrarTabla.setToolTipText(("Elimina una tabla de una base de datos"));
        borrarColumna.setToolTipText("Elimina una columna de una tabla");
        borrarFila.setToolTipText("Elimina una fila de una tabla");
    }

    public void setAr(JScrollPane ar) {
        this.ar = ar;
    }

    public void setArbol(JTree arbol) {
        this.arbol = arbol;
    }

    public JPanel getContent() {
        return content;
    }

    public JButton getBorrarbd() {
        return borrarbd;
    }

    public JButton getBorrarTabla() {
        return borrarTabla;
    }

    public JButton getBorrarColumna() {
        return borrarColumna;
    }

    public JButton getBorrarFila() {
        return borrarFila;
    }
    

}
