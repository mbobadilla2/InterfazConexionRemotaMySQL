/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexion;

import java.awt.BorderLayout;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Ventana que mostrara el resultado de una consulta
 *
 * @author Miguel Fernando Bobadilla Contreras
 * @author Luis Angel Pérez Muñoz
 * @author José Rubén Perez García
 * @author José Ramón Márquez Solano
 * @author Valery Abigail Cambara Gil
 * @version 6/Noviembre/2014 /A
 *
 */
public class TablaConsulta extends JFrame { //tablaConsulta es una ventana 

    /**
     * Constructor que inicializa la venta , realiza la consulta y la muestra en
     * una ventana
     *
     * @param consulta la consulta en un string
     * @param sentencia clase requerida para ajecutar la sentencia
     * @see Statement
     * @see ConexionSQL
     *
     */
    public TablaConsulta(Statement sentencia, String consulta) {
        super("Consulta");
        this.setSize(640, 480);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setLayout(new BorderLayout());

        ResultSet resultados;
        try { // SI todo sale bien muestra la consulta si no atrapa la excepcion y la muestra en la ventana 
            resultados = sentencia.executeQuery(consulta); //ejecuta la consulta

            JTable tabla = new JTable();
            DefaultTableModel modelo = new DefaultTableModel();
            JScrollPane desplazar = new JScrollPane(tabla);
            String[] columnas = new String[resultados.getMetaData().getColumnCount()];

            for (int i = 0; i < resultados.getMetaData().getColumnCount(); i++) {
                columnas[i] = resultados.getMetaData().getColumnName(i + 1);
            }

            modelo.setColumnIdentifiers(columnas);
            desplazar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            desplazar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            tabla.setFillsViewportHeight(true);
            tabla.setModel(modelo);
            getContentPane().add(desplazar, BorderLayout.NORTH);
            pack();

            String fila[] = new String[resultados.getMetaData().getColumnCount()];

            while (resultados.next()) {
                for (int i = 0; i < resultados.getMetaData().getColumnCount(); i++) {
                    fila[i] = resultados.getString(i + 1);
                }
                
                modelo.addRow(fila);
            }

            setVisible(true);

        } catch (SQLException ex) {
            // Si hubo un error, se muestra el error y se elimina la tabla creada...
            // Esta parte queda a discusión...
//            JLabel lError = new JLabel("Error "+ex);
//            this.add(lError);
//            this.setVisible(true);
            this.dispose();
        }

    }

}
