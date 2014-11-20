package com.conexion;

import java.io.*;
import java.util.*;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
/**
 * Clase que lee y borra conexiones del archivo conx.txt
 *
 * @author Miguel Fernando Bobadilla Contreras
 * @author Luis Angel Pérez Muñoz
 * @author José Rubén Perez García
 * @author José Ramón Márquez Solano
 * @author Valery Abigail Cambara Gil
 * @version 6/Noviembre/2014 /A
 *
 */
public class Archivo { //clase archivo
    private String ruta = "";
    static String rutaGuardada;
    
    /**
     * Metodo que lee linea por liena un archivo y devuelve cada liena en un elemento nuevo
     * de un arraylist de string 
     * @param ruta ruta de donde se lee la informacion 
     * @return lista arrayList de strings que guarda cada linea del archivo pasado como ruta 
     */
    public static ArrayList<String> leerCadenas (String ruta){
        ArrayList<String> lista = new ArrayList();
        
        try{
            BufferedReader archivo = new BufferedReader(new FileReader(ruta));
            
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
    
    /**
     * reescribe todas las conexiones pasadas en el arraylist de conexiones
     * @param conexiones Arraylist de conexiones que se reescribiran
     * @param ruta ruta del archivo desde donde se borrara la conexion 
     */
    public static void escribirConexiones(ArrayList<PanelConexion> conexiones, String ruta) throws IOException {
         BufferedWriter bw=new BufferedWriter(new FileWriter(ruta));//flujo de escritura
        try {
           
            for (int i = 0; i < conexiones.size(); i++) {
                for (int j = 0; j <5; j++) {
                    /*escribe linea por linea en el archivo,primero entra a un panel y despues a cada
                     uno de elementos que tiene el array de labels*/
                   bw.write(conexiones.get(i).getInfoConexion().get(j).getText()+"\n");
                    
                }
            }
        } catch (Exception e) {
        }finally{
            bw.close();//cierro el flujo
            
        }
    }
    
    public static void guardarConsultaComo(String texto)throws IOException{
        JFileChooser jfc = new JFileChooser();
        //jfc.showSaveDialog(null);
        
        int eleccion = jfc.showSaveDialog(null);
        try{
            rutaGuardada = jfc.getSelectedFile().getPath();
        }catch(NullPointerException e){
            rutaGuardada="";
        }
        if(eleccion == JFileChooser.APPROVE_OPTION){
        
            FileWriter fw = new FileWriter(new File(jfc.getSelectedFile().getPath()));
        
            fw.write(texto);
        
            fw.close();
        
        }else{
            System.out.println("Mejor no lo guardo...");
        }
    }
    
    public static boolean guardar(String texto)throws IOException{
        
            if(rutaGuardada == null){
                return false;
            }
            System.out.println(rutaGuardada);
            File guardar = new File(rutaGuardada);
            
        
            FileWriter fw = new FileWriter(guardar);
        
            fw.write(texto);
        
            fw.close();
        
        System.out.println("Guardado : "+rutaGuardada);
        
        return true;
        
    }
    
    public static String abrirConsulta() throws IOException{
        String leido = "";
        
        JFileChooser jfc = new JFileChooser();
        int eleccion = jfc.showOpenDialog(null);
        
        if(eleccion == JFileChooser.APPROVE_OPTION){
            rutaGuardada = jfc.getSelectedFile().getAbsolutePath();
        
            FileReader fr = new FileReader(new File(rutaGuardada));
            BufferedReader br = new BufferedReader(fr);
            String aux;

            while((aux = br.readLine()) != null){
                leido += aux + "\n";
            }
            
            return leido;
            
        }else{
            return null;
        }
    }

}//Class Archivo
