package com.conexion;

import java.awt.BorderLayout;
import java.awt.Color;
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
//    private String[] combo =new String[]{"Actualizacion","Seleccion"};
//    private JComboBox operacion=new JComboBox(combo);
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
        this.setTitle("Conexion con usuario :  " + oyente.getCon().getUser()+"   en   " + oyente.getCon().getUrl()); 
        this.oyente = oyente;
        this.setSize(640, 480);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
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
        reload = new JButton("");
        reload.setIcon(new ImageIcon("src/icon/reload.png"));
        
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
//        JPanel pEste = new JPanel();

//        pEste.add(ar);
        taConsulta = new JTextArea();
        JScrollPane despConsulta = new JScrollPane(taConsulta);
//        pSur.add(new JLabel("Tipo de operación: "));
//        pSur.add(operacion);
//        pSur.add(run);
//        pSur.setAlignmentX(JPanel.RIGHT_ALIGNMENT);
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
        
        taConsulta.setFocusable(true);
        taConsulta.addKeyListener(oyente);
    }

    public JTree crearArbol() {
        JTree arbol;
        DefaultMutableTreeNode conexion = new DefaultMutableTreeNode("Bases de datos");
        DefaultTreeModel modelo = new DefaultTreeModel(conexion);
        arbol = new JTree(modelo);
        
        DefaultTreeCellRenderer render=(DefaultTreeCellRenderer)arbol.getCellRenderer();
       
        render.setLeafIcon(new ImageIcon("src/icon/table.png"));
        render.setClosedIcon(new ImageIcon("src/icon/db.png"));
        render.setOpenIcon(new ImageIcon("src/icon/dbmain.png"));
        int cuenta = 0;

        for (String a : oyente.getCon().getNombresBD()) {
            DefaultMutableTreeNode nombreBD = new DefaultMutableTreeNode(a);
            //System.out.println(a);
            modelo.insertNodeInto(nombreBD, conexion, cuenta);
            try {
                int cuentaTabla = 0;
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection c = DriverManager.getConnection("jdbc:mysql://" + oyente.p.getInfoConexion().get(1).getText() + ":" + oyente.p.getInfoConexion().get(2).getText()+"/"+a,
                oyente.p.getInfoConexion().get(3).getText(), oyente.p.getInfoConexion().get(4).getText());
                Statement sentencia = c.createStatement();
                ResultSet rs = sentencia.executeQuery("show tables");
                while (rs.next()) {
                      
                       for (int x = 1; x <= rs.getMetaData().getColumnCount(); x++) {
                            //System.out.print(rs.getString(x) + "\t");
                            DefaultMutableTreeNode datoTable = new DefaultMutableTreeNode(rs.getString(x));
                            modelo.insertNodeInto(datoTable, nombreBD, cuentaTabla);
                            ArrayList <String> columnas = obtenerColumnas(rs.getString(x), a);
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

public ArrayList <String> obtenerColumnas(String tabla, String address){
        ArrayList <String> columnas = new ArrayList();
        try{
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection c = DriverManager.getConnection("jdbc:mysql://" + oyente.p.getInfoConexion().get(1).getText() + ":" + oyente.p.getInfoConexion().get(2).getText()+"/"+address,
            oyente.p.getInfoConexion().get(3).getText(), oyente.p.getInfoConexion().get(4).getText());
            Statement sentencia = c.createStatement();
            ResultSet rs = sentencia.executeQuery("select * from "+tabla);
            for (int x = 1; x <= rs.getMetaData().getColumnCount(); x++) {
                columnas.add(rs.getMetaData().getColumnName(x));
            }
        }catch(ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e){}
        return columnas;
        
    }
    //GETTERS AND SETTERS ----------------------------------------------------------------------------------------------------
    public JTextArea getTaConsulta() {
        return taConsulta;
    }

    public void setTaConsulta(JTextArea taConsulta) {
        this.taConsulta = taConsulta;
    }
//
//    public JComboBox getOperacion() {
//        return operacion;
//    }

    private void aagregartips() {
     run.setToolTipText("Ejecutar Consulta");
     reload.setToolTipText("Actualizar lista de bases de datos");
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

}
