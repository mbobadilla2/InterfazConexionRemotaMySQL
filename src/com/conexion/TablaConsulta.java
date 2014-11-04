/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexion;

import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JR
 */
public class TablaConsulta extends JFrame{
    
    public TablaConsulta(Statement sentencia, String consulta) throws SQLException{
        super("Tabla resultante");
        setSize(640, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLayout(new BorderLayout());
        ResultSet resultados = sentencia.executeQuery(consulta);
        JTable tabla = new JTable();
        DefaultTableModel modelo = new DefaultTableModel();
        JScrollPane desplazar = new JScrollPane(tabla);
        String [] columnas = new String[resultados.getMetaData().getColumnCount()];
        for(int i = 0; i<resultados.getMetaData().getColumnCount(); i++){
            columnas[i] = resultados.getMetaData().getColumnName(i);
        }
        
        modelo.setColumnIdentifiers(columnas);
        desplazar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        desplazar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tabla.setFillsViewportHeight(true);
        tabla.setModel(modelo);
        getContentPane().add(desplazar, BorderLayout.NORTH);
        pack();
        
        String fila [] = new String[resultados.getMetaData().getColumnCount()];
        
        while(resultados.next()){
            for (int i = 0; i < resultados.getMetaData().getColumnCount(); i++) {
                fila[i] = resultados.getString(i);
            }
            modelo.addRow(fila);
        }
                
        
       setVisible(true); 
    }
    
    
}
