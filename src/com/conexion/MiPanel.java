package com.conexion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.*;

class MiPanel extends JPanel {

    private final MiOyente oyente;
    private ArrayList<PanelConexion> conexiones;
    private JScrollPane scr;
    private JButton agregarConexion;
    private ArrayList<String> conx = new ArrayList();
    private JPanel centro;
    private int numconx;
    private final Color c1 = new Color(106, 90, 205);//Colores para los paneles
    private final Color c2 = new Color(0, 191, 255);
    private JLabel nConexiones;

    public MiPanel(MiOyente oyente) {
        this.oyente = oyente;
        agregarComponentes();

    }

    private void agregarComponentes() {
        this.setLayout(new BorderLayout());
        int contador = 0; //cuenta cuantas lineas hay en el archivo de conexiones
//        JPanel norte = new JPanel();
//        agregarConexion=new JButton("Agregar Conexion");
//         norte.add(agregarConexion);//Panel del norte
//         centro=hacerPaneles();
        ArrayList<String> etiquetas = new ArrayList<>(); //Array de etiquetas para los paneles
        etiquetas.add("Nombre de la Conexion: ");
        etiquetas.add("Host: ");
        etiquetas.add("Puerto: ");
        etiquetas.add("Usuario: ");
        etiquetas.add("Contraseña: ");
        JPanel pSur=new JPanel();
        nConexiones=new JLabel();
        JPanel norte = new JPanel();    //Panel del norte
        centro = new JPanel();

        agregarConexion = new JButton("Agregar Conexion");
        //eliminarConexion = new JButton("Eliminar Conexion");
        norte.add(agregarConexion);
        //norte.add(eliminarConexion);
        conx = Archivo.leerCadenas("src/cbd/conx.txt");

        conexiones = new ArrayList<>(); //arrayList de panelenesConexion 

        for (String string : conx) { //cuenta las lineas del archivo
            contador++;
        }

        numconx = contador / etiquetas.size();  //obtiene el numero de conexiones 
        
        //System.out.println(etiquetas.size());
        //System.out.println(numconx);
        centro.setLayout(new GridLayout(calcularfilas(numconx), 3));
        //Agregando paneles con conexiones
        int corrida = 0;

        for (int i = 0; i < numconx; i++) {
            PanelConexion p = new PanelConexion(); //creamos un panelConexion
            p.setLayout(new GridLayout(5, 2));       //dividemos el panelConexion
            if (i % 2 == 0) {                   //seleccion de color
                p.setBackground(c1);
            } else {
                p.setBackground(c2);
            }
            for (int j = 0; j < 5; j++) {       //ponemos el contenido en los paneles, PUSE 4 para no mostrar la contraseña
                
                p.add(new JLabel(etiquetas.get(j)));    //obtenetmos la etiqueta del array
                p.getDatosConx().get(j).setText(conx.get(corrida)); //obtenemos la info del panel Conexion y la ponemos en la label del panale
                if(j==4){
                     corrida++;
                    continue;
                   
                }
                p.add(p.getDatosConx().get(j));   //ponemos la informacion en el panel 
                // p.add(new JLabel(conx.get(corrida)));

                corrida++;     //lleva la cuenta de la linea en el archivo cbd.txt                    
            }

            p.addEventos(oyente); //le agrega el evento a cada panel 
            p.setName(i + ""); // le ponemos un numero identificador a cada panel
            conexiones.add(p); //agregamso cada panel a un array de paneleConexion
            centro.add(p); //agregamos el panel a la grilla que esta en el centro del frame

        }

        // agregar paneles de relleno
        if (numconx % 3 > 0) {
            for (int i = numconx % 3; i < 3; i++) {
                //System.out.println(i);
                centro.add(new JPanel());
            }
        }
        
        
        scr = new JScrollPane(centro);
        scr.setBorder(BorderFactory.createLineBorder(centro.getBackground(), 15));
        scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        nConexiones.setText("Numero de Conexiones: "+numconx);
        pSur.add(nConexiones);
        this.add(pSur,"South");
        this.add(norte, "North");
        this.add(scr, "Center");

        addEventos(); //agregamos el evento a la ventana
        
    }

    public void eliminarConexion() {
//        centro.remove(index);
//        centro.add(new JPanel());
//
//        // Reasignamos las posiciones...
//        for (int i = 0; i < conexiones.size(); i++) {
//            conexiones.get(i).setName(i + "");
//            if (i % 2 == 0) {
//                conexiones.get(i).setBackground(c1);
//            } else {
//                conexiones.get(i).setBackground(c2);
//            }
//        }   
        numconx--;
        
        centro.removeAll();
        centro.setLayout(new GridLayout(calcularfilas(numconx), 3));
        
        System.out.println("Conexiones" + conexiones.size());
        
        for (int i = 0; i < conexiones.size(); i++) {
            conexiones.get(i).setName(i + "");
            
            if (i % 2 == 0) {
                conexiones.get(i).setBackground(c1);
            } else {
                conexiones.get(i).setBackground(c2);
            }
            centro.add(conexiones.get(i));
        }
        
        if(conexiones.size() % 3 > 0){
            for(int i = conexiones.size() % 3; i < 3; i++){
                centro.add(new JPanel());
            }
        }

//        this.updateUI();
        redimensionarPanel();
    }
    
    public void agregarConexion(PanelConexion pc){
        numconx++;
        
        centro.removeAll();
        centro.setLayout(new GridLayout(calcularfilas(numconx), 3));
        
        System.out.println("Conexiones" + conexiones.size());
        
        for (int i = 0; i < conexiones.size(); i++) {
            conexiones.get(i).setName(i + "");
            
            if (i % 2 == 0) {
                conexiones.get(i).setBackground(c1);
            } else {
                conexiones.get(i).setBackground(c2);
            }
            centro.add(conexiones.get(i));
        }
        
        if(conexiones.size() % 3 > 0){
            for(int i = conexiones.size() % 3; i < 3; i++){
                centro.add(new JPanel());
            }
        }
        
//        this.updateUI();
        redimensionarPanel();
    }
    
    public void redimensionarPanel(){
        int alto = (int) Math.ceil(conexiones.size() / 3);
        
        centro.setSize(900, 176 * alto);
        scr.setSize(900, 176 * alto);
        
        nConexiones.setText("Numero de Conexiones: "+conexiones.size());
        this.updateUI();
        scr.updateUI();
    }
    
//    public JPanel hacerPaneles(){
//        JPanel pCentro=new JPanel();
//         ArrayList<String> etiquetas = new ArrayList<>(); //Array de etiquetas para los paneles
//        etiquetas.add("Nombre de la Conexion: ");
//        etiquetas.add("Host: ");
//        etiquetas.add("Puerto: ");
//        etiquetas.add("Usuario: ");
//        etiquetas.add("Contraseña: ");
//        int contador=0;
//        conx = Archivo.leerCadenas("src/cbd/conx.txt");
//
//        conexiones = new ArrayList<>(); //arrayList de panelenesConexion 
//
//        for (String string : conx) { //cuenta las lineas del archivo
//            contador++;
//        }
//
//        numconx = contador / etiquetas.size();  //obtiene el numero de conexiones 
//        
//        pCentro.setLayout(new GridLayout(calcularfilas(numconx), 3));
//        //Agregando paneles con conexiones
//        int corrida = 0;
//
//        for (int i = 0; i < numconx; i++) {
//            PanelConexion p = new PanelConexion(); //creamos un panelConexion
//            p.setLayout(new GridLayout(5, 2));       //dividemos el panelConexion
//            if (i % 2 == 0) {                   //seleccion de color
//                p.setBackground(c1);
//            } else {
//                p.setBackground(c2);
//            }
//            for (int j = 0; j < 5; j++) {       //ponemos el contenido en los paneles, PUSE 4 para no mostrar la contraseña
//                
//                p.add(new JLabel(etiquetas.get(j)));    //obtenetmos la etiqueta del array
//                p.getDatosConx().get(j).setText(conx.get(corrida)); //obtenemos la info del panel Conexion y la ponemos en la label del panale
//                if(j==4){
//                     corrida++;
//                    continue;
//                   
//                }
//                p.add(p.getDatosConx().get(j));   //ponemos la informacion en el panel 
//                // p.add(new JLabel(conx.get(corrida)));
//
//                corrida++;     //lleva la cuenta de la linea en el archivo cbd.txt                    
//            }
//
//            p.addEventos(oyente); //le agrega el evento a cada panel 
//            p.setName(i + ""); // le ponemos un numero identificador a cada panel
//            conexiones.add(p); //agregamso cada panel a un array de paneleConexion
//            pCentro.add(p); //agregamos el panel a la grilla que esta en el centro del frame
//
//        }
//
//        // agregar paneles de relleno
//        if (numconx % 3 > 0) {
//            for (int i = numconx % 3; i < 3; i++) {
//                //System.out.println(i);
//                pCentro.add(new JPanel());
//            }
//        }
//        this.updateUI();
//        return pCentro;
//        
//    }

    private int calcularfilas(int numconx) {
        double b = 3;
        double c = (double) numconx;
        int a = (int) Math.ceil(c / b);

        return a;
    }

    public void addEventos() {
        agregarConexion.addActionListener(this.oyente);
        // eliminarConexion.addActionListener(this.oyente);
    }

    public ArrayList<PanelConexion> getConexiones() {
        return conexiones;
    }

    public ArrayList<String> getConx() {
        return conx;
    }

    public void setConx(ArrayList<String> conx) {
        this.conx = conx;
    }
    
}
