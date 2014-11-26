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
 * @author Luis Created on 25/11/2014, 10:02:44 PM
 */
public class Atributos extends JFrame {
    private OyenteConexion o;
    private int numero;
    private ArrayList<String> columna;
    private JPanel content = new JPanel();
    private String[] tdato = new String[]{"INT", "CHAR(20)", "DATE", "TINY"};
    private JButton agregar=new JButton("Agregar Columnas");
    private ArrayList <JTextField> nombres;
    private ArrayList <JComboBox> atipos;
    private ArrayList <JCheckBox> nulos;
    private ArrayList <JCheckBox> autos;
    private ArrayList <JCheckBox> pk;

    public Atributos(int numero,OyenteConexion o) {
        this.o=o;
        nombres=new ArrayList<>();
        atipos=new ArrayList<>();
        nulos=new ArrayList<>();
        autos=new ArrayList<>();
        pk=new ArrayList<>();
        this.numero = numero;
        this.setSize(600, 600);
        this.setLocation(40, 20);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        addComponentes();
        addEventos();
        this.setVisible(true);
    }

    private void addComponentes() {

        content.setLayout(new BorderLayout());
        JPanel pCentro = new JPanel();
        pCentro.setLayout(new GridLayout(numero, 7, 5, 5));
       JPanel pSur=new JPanel();
        for (int i = 0; i < numero; i++) {
            JLabel l = new JLabel("Atributo #" + (i + 1) + "  Nombre: ");
            JTextField nombre = new JTextField();
            nombre.setName((i + 1) + "");
            JLabel tipo = new JLabel("Tipo :");
            JComboBox tipos = new JComboBox(tdato);
            JCheckBox nonull = new JCheckBox("No null");
            JCheckBox auto = new JCheckBox("AutoInclemental");
            JCheckBox pks=new JCheckBox("PrimaryKey");
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

        }
        pSur.add(agregar);
        content.add(pSur,"South");

        content.add(pCentro, "Center");
        this.add(content);

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
