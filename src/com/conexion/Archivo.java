package com.conexion;

import java.util.*;
import java.io.*;
/**
 * @author Fernando2
 * Created on Feb 21, 2014, 1:31:20 PM
 */

public class Archivo {
    
    public static ArrayList<String> leerCadenas (String nombre){
        ArrayList<String> lista = new ArrayList();
        
        try{
            BufferedReader archivo = new BufferedReader(new FileReader(nombre));
            
            String linea = null;
            
            do{
                linea = archivo.readLine();
                if(linea != null){
                    lista.add(linea);
                }
            }while(linea != null);
            
            archivo.close();//Cerrar el flujo de lectura
        }catch(IOException ioe){
            System.out.println("Hubo un error: " + ioe);
            System.exit(-1);
        }
        
        
        return lista;
    }//leerCadena
    
//    public static void main(String[] args){
//        String archivoPrueba = "src/ejemplos/Ejemplo.txt";
//        ArrayList<String> prueba = Archivo.leerCadenas(archivoPrueba);
//        
//        //For Each
//        for(String linea:prueba){
//            System.out.println("*****" + linea);
//        }
//    }//main

}//Class Archivo
