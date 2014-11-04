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
    }
    
    static void escribirCadenas(ArrayList<PanelConexion> conexiones, String ruta) throws IOException {
         BufferedWriter bw=new BufferedWriter(new FileWriter(ruta));//flujo de escritura
        try {
           
            for (int i = 0; i < conexiones.size(); i++) {
                for (int j = 0; j <5; j++) {
                    /*escribe linea por linea en el archivo,primero entra a un panel y despues a cada
                     uno de elementos que tiene el array de labels*/
                   bw.write(conexiones.get(i).getDatosConx().get(j).getText()+"\n");
                    
                }
            }
        } catch (Exception e) {
        }finally{
            bw.close();//cierro el flujo
            
        }
    }

}//Class Archivo
