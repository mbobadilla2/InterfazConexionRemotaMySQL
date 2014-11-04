package com.conexion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

class MiPanel extends JPanel {
    private final MiOyente oyente;
    private ArrayList<PanelConexion> conexiones;
    private JButton agregarConexion;
    private JButton eliminarConexion;
    private ArrayList<String> conx = new ArrayList();
    private JPanel centro;
    private int numconx;
    

    public MiPanel(MiOyente oyente) {
        this.oyente = oyente;
        agregarComponentes();
    }

    private void agregarComponentes() {
        this.setLayout(new BorderLayout());
        int contador = 0; //cuenta cuantas lineas hay en el archivo de conexiones
        ArrayList <String> etiquetas=new ArrayList<>();
            etiquetas.add("Nombre de la Conexion: ");
            etiquetas.add("Host: ");
            etiquetas.add("Puerto: ");
            etiquetas.add("Usuario: ");
            etiquetas.add("Contraseña: ");

        JPanel norte = new JPanel();
        centro = new JPanel();
        
        agregarConexion = new JButton("Agregar Conexion");
        eliminarConexion = new JButton("Eliminar Conexion");
        norte.add(agregarConexion);
        norte.add(eliminarConexion);
        conx = Archivo.leerCadenas("src/cbd/conx.txt");
        
        conexiones = new ArrayList<>();
        
        
        for (String string : conx) {
            contador++;
        }
        
        numconx = contador / 5;
        
        centro.setLayout(new GridLayout(calcularfilas(numconx), 3));
        //Agregando paneles con conexiones
        int corrida = 0;
        for (int i = 0; i < numconx; i++) {
            PanelConexion p = new PanelConexion();
            p.setLayout(new GridLayout(5,2));
            if (i % 2 == 0) {
                p.setBackground(Color.lightGray);
            } else {
                p.setBackground(Color.cyan);
            }
            for (int j = 0; j < 5; j++) {
                
                p.add(new JLabel(etiquetas.get(j)));
                p.getDatosConx().get(j).setText(conx.get(corrida));
                p.add(p.getDatosConx().get(j));
                // p.add(new JLabel(conx.get(corrida)));
                
                corrida++;
            }

            p.addEventos(oyente);
            p.setName(i+"");
            conexiones.add(p);
            centro.add(p);

           
        }

        // agregar paneles de relleno
        if (numconx % 3 > 0) {
            for (int i = numconx % 3; i < 3; i++) {
                System.out.println(i);
                centro.add(new JPanel());
            }
        }


        this.add(norte, "North");
        this.add(centro, "Center");
        
        addEventos();
    }
    
    public void actualizarLista(int index){
        centro.remove(index);
        centro.add(new JPanel());
        
        // Reasignamos las posiciones...
        for(int i = 0; i < conexiones.size(); i++){
            conexiones.get(i).setName(i+"");
            if (i % 2 == 0) {
                conexiones.get(i).setBackground(Color.lightGray);
            } else {
                conexiones.get(i).setBackground(Color.cyan);
            }            
        }
        
        this.updateUI();
    }

    private int calcularfilas(int numconx) {
        double b = 3;
        double c = (double) numconx;
        int a = (int) Math.ceil(c / b);

        return a;
    }
    
    public void addEventos(){
        agregarConexion.addActionListener(this.oyente);
        eliminarConexion.addActionListener(this.oyente);
    }

    public ArrayList<PanelConexion> getConexiones() {
        return conexiones;
    }
    
}
