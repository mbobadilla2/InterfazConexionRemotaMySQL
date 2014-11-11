package com.conexion;

import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
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
    private JButton run = new JButton("Ejecutar");
    private JMenuItem nuevo;
    private JMenuItem abrir;
    private JMenuItem guardar;
    private JMenuItem guardarComo;
    private JMenuItem salir;
    private JTextArea taConsulta;
    private JTree arbol = null;

    /**
     * Incializa la ventana agregando sus componentes , ademas llama al metodo
     * addeventos,y a addComponentes
     *
     * @param oyente oyente que le dara eventos al los elementos de esta ventana
     * que los necesiten
     */
    public Consulta(OyenteConexion oyente) {
        this.setTitle("Conexion");  //HAY QUE MOSTRAR AQUI EL NOMBRE DE LA CONEXION 
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
        JMenuBar menu = new JMenuBar();
        nuevo = new JMenuItem("Nuevo");
        abrir = new JMenuItem("Abrir");
        guardar = new JMenuItem("Guardar");
        guardarComo = new JMenuItem("Guardar como...");
        salir = new JMenuItem("Salir");

        JMenu archivo = new JMenu("Archivo");
        archivo.add(nuevo);
        archivo.add(abrir);
        archivo.add(guardar);
        archivo.add(guardarComo);
        archivo.add(salir);

        menu.add(archivo);

        arbol = crearArbol();
        JScrollPane ar = new JScrollPane(arbol);

        JPanel pNorte = new JPanel();
        JPanel pSur = new JPanel();
        JPanel pEste = new JPanel();

        pEste.add(ar);
        taConsulta = new JTextArea();
        JScrollPane despConsulta = new JScrollPane(taConsulta);
        pSur.add(run);

        this.add(pEste, BorderLayout.WEST);
        this.setJMenuBar(menu);
        this.add(pNorte, "North");
        this.add(despConsulta, "Center");
        this.add(pSur, "South");
        this.add(pEste, "West");

        addEventos();
    }

    /**
     * agrega los eventos a los elementos de esta ventana que los necesiten
     *
     * @param void
     */
    public void addEventos() {
        nuevo.addActionListener(oyente);
        run.addActionListener(oyente);
    }

    private JTree crearArbol() {
        JTree arbol;
        DefaultMutableTreeNode conexion = new DefaultMutableTreeNode("Bases de datos");
        DefaultTreeModel modelo = new DefaultTreeModel(conexion);
        arbol = new JTree(modelo);
        int cuenta = 0;

        for (String a : oyente.getCon().getNombresBD()) {
            DefaultMutableTreeNode nombreBD = new DefaultMutableTreeNode(a);
            modelo.insertNodeInto(nombreBD, conexion, cuenta);
            try {
                int cuentaTabla = 0;

            } catch (Exception e) {
                System.out.println("Error " + e);
            }

        }
        return arbol;
    }

    //GETTERS AND SETTERS ----------------------------------------------------------------------------------------------------
    public JTextArea getTaConsulta() {
        return taConsulta;
    }

    public void setTaConsulta(JTextArea taConsulta) {
        this.taConsulta = taConsulta;
    }

}