/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexion;

import java.util.ArrayList;

/**
 *
 * @author JR
 */
public class BaseDatos {
    private ArrayList <Tablas> tablas = new ArrayList();
    private String nombre;

    public BaseDatos() {
    }

    public ArrayList<Tablas> getTablas() {
        return tablas;
    }

    public void setTablas(ArrayList<Tablas> tablas) {
        this.tablas = tablas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
}

class Tablas{
    
    private ArrayList <String> columnas = new ArrayList();
    private String nombre;

    public Tablas() {
       
    }

    public ArrayList<String> getColumnas() {
        return columnas;
    }

    public void setColumnas(ArrayList<String> columnas) {
        this.columnas = columnas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
    
}

