package com.conexion;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.*;
import javax.swing.JTextField;

/**
 * @author Miguel Fernando Bobadilla Contreras
 * @author Luis Angel Pérez Muñoz
 * @author José Rubén Perez Rodriguez
 * @author José Ramón Márquez Solano
 * @author Valery Abigail Cambara Gil
 * Created on 25/11/2014, 10:02:44 PM
 */
public class AtributosCrearTabla extends JFrame {
    private OyenteConexion o;
    private int numero;
    private ArrayList<String> columna;
    private JPanel content = new JPanel();
    private String[] tdato = llenarLista();
    private JButton agregar=new JButton("Agregar columnas");
    private ArrayList <JTextField> nombres;
    private ArrayList <JComboBox> atipos;
    private ArrayList <JCheckBox> nulos;
    private ArrayList <JCheckBox> autos;
    private ArrayList <JCheckBox> pk;

    public AtributosCrearTabla(int numero,OyenteConexion o) {
        this.setTitle("Agregar columnas");
        this.o = o;
        nombres=new ArrayList<>();
        atipos=new ArrayList<>();
        nulos=new ArrayList<>();
        autos=new ArrayList<>();
        pk=new ArrayList<>();
        this.numero = numero;
        
        if(numero <= 5){
            this.setSize(835, (52*numero)+65);   
        }else{
            this.setSize(835, 300);   
        }
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addComponentes();
        addEventos();
        this.setVisible(true);
    }

    private void addComponentes() {
        content.setLayout(new BorderLayout());
        JPanel grilla = new JPanel();
//        pCentro.setLayout(new GridLayout(numero, 7, 5, 5));
        grilla.setLayout(new GridLayout(numero, 1));
        
        JPanel pCentro;
        JPanel pSur = new JPanel();
        
        JScrollPane scroll;
        
        for (int i = 0; i < numero; i++) {
            pCentro = new JPanel();
            pCentro.setBorder(BorderFactory.createLineBorder(this.getBackground(), 5));
            JLabel l = new JLabel("Atributo #" + (i + 1) + "  Nombre: ");
            JTextField nombre = new JTextField(7);
            nombre.setName((i + 1) + "");
            JLabel tipo = new JLabel("Tipo :");
            JComboBox tipos = new JComboBox(tdato);
            JCheckBox nonull = new JCheckBox("Not null");
            JCheckBox auto = new JCheckBox("Auto incremental");
            JCheckBox pks=new JCheckBox("Primary key");
            nonull.setName((i + 1) + "");
            auto.setName((i + 1) + "");
            tipos.setName((i + 1) + "");
            pCentro.add(l);
            pCentro.add(nombre);
            pCentro.add(tipo);
            pCentro.add(tipos);
            pCentro.add(nonull);
            pCentro.add(auto);
            pCentro.add(pks);
            nombres.add(nombre);
            atipos.add(tipos);
            nulos.add(nonull);
            autos.add(auto);
            pk.add(pks);

            grilla.add(pCentro);
        }
        
        scroll = new JScrollPane(grilla);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        pSur.add(agregar);
        content.add(pSur,"South");

        content.add(scroll, "Center");
        
        this.add(content);

    }

    public String[] llenarLista(){
        return new String[]{"INT","TINYINT","SMALLINT","MEDIUMINT","INTEGER","BIGINT","FLOAT","DOUBLE","REAL",
                "DATE","DATETIME","TIMESTAMP","TIME","YEAR","CHAR(20)","VARCHAR(30)","TINYBLOB",
                "TINYTEXT","BLOB","TEXT","MEDIUMBLOB","MEDIUMTEXT","LONGBLOB","LONGTEXT"};
    }
    
    private void addEventos() {
        agregar.addActionListener(o);
    }

    public OyenteConexion getO() {
        return o;
    }

    public void setO(OyenteConexion o) {
        this.o = o;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public ArrayList<String> getColumna() {
        return columna;
    }

    public void setColumna(ArrayList<String> columna) {
        this.columna = columna;
    }

    public JPanel getContent() {
        return content;
    }

    public void setContent(JPanel content) {
        this.content = content;
    }

    public String[] getTdato() {
        return tdato;
    }

    public void setTdato(String[] tdato) {
        this.tdato = tdato;
    }

    public JButton getAgregar() {
        return agregar;
    }

    public void setAgregar(JButton agregar) {
        this.agregar = agregar;
    }

    public ArrayList<JTextField> getNombres() {
        return nombres;
    }

    public ArrayList<JComboBox> getAtipos() {
        return atipos;
    }

    public ArrayList<JCheckBox> getNulos() {
        return nulos;
    }

    public ArrayList<JCheckBox> getAutos() {
        return autos;
    }

    public ArrayList<JCheckBox> getPk() {
        return pk;
    }
    
    

}
