package com.conexion;

import java.awt.*;
import static java.awt.Frame.HAND_CURSOR;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Esta clase crea el panel de la ventana principal , que es el que contendra la
 * lista de conexiones tomadas desde el archivo conx.txt, ademas a traves de sus
 * eventos , es el enlace a varias ventanas
 *
 * @author Miguel Fernando Bobadilla Contreras
 * @author Luis Angel Pérez Muñoz
 * @author José Rubén Perez García
 * @author José Ramón Márquez Solano
 * @author Valery Abigail Cambara Gil
 * @version 6/Noviembre/2014 /A
 *
 */
public class MiPanel extends JPanel { //Clase mi panel es un JPanel 
    // Variables de clase 

    private final MiOyente oyente;
    private ArrayList<PanelConexion> conexiones;
    private JScrollPane scroll;
    private JButton bAgregarConexion;
    private ArrayList<String> conexionesArchivo = new ArrayList();
    private JPanel pCentro;
    private int numeroConexiones;
    private final Color color1 = new Color(106, 90, 205);//Colores para los paneles
    private final Color color2 = new Color(0, 191, 255);
    private JLabel lNumeroConexiones;
//---------------------------------------------------------------------------------------------------

    /**
     * Crea un panel el cual inicializa su oyente y agrega sus componentes
     *
     * @param oyente El oyente que activara sus eventos
     *
     */
    public MiPanel(MiOyente oyente) {
        this.oyente = oyente;
        agregarComponentes();

    }

    /**
     * Agrega los componentes visuales al panel
     *
     * @return void
     */
    private void agregarComponentes() {
     
        
        this.setLayout(new BorderLayout()); // El layout del panel es un borderlayout(n,s,e,w,c)

        // Declaraciones -------------------------------------------------------------------------------------------------
        int contador = 0;                   //cuenta cuantas lineas hay en el archivo de conexiones
        JPanel pNorte = new JPanel();       //Panel que estara en la parte norte del panel principal
        JPanel pSur = new JPanel();         //Panel que estara en la parte sur del panel principal
        pCentro = new JPanel();             //Panel al centro del panel principal
        ArrayList<String> etiquetasPaneles = new ArrayList<>(); //Array de String para las etiquetas de los paneles de conexion
        lNumeroConexiones = new JLabel();    //inicializamos la etiqueta que mostrara el numero de conexiones 
        bAgregarConexion = new JButton("Agregar Conexion");  //inicializamos el boton agregar del panel principal
        conexionesArchivo = Archivo.leerCadenas("src/cbd/conx.txt");  //inicializamos este array con cada una de las lineas del archivo conx.txt
        conexiones = new ArrayList<>(); //arrayList de tipo  panelenesConexion 
        //-----------------------------------------------------------------------------------------------------------------

        //nombre de las etiqetas acomodados 
        etiquetasPaneles.add("Nombre de la Conexion: ");
        etiquetasPaneles.add("Host: ");
        etiquetasPaneles.add("Puerto: ");
        etiquetasPaneles.add("Usuario: ");
        etiquetasPaneles.add("Contraseña: ");

        pNorte.add(bAgregarConexion); //agrego el boton  agregar conexion a el panel norte 

        for (String string : conexionesArchivo) {     //cuenta las lineas del archivo
            contador++;
        }

        numeroConexiones = contador / etiquetasPaneles.size();  //Obtiene el numero de conexiones (el numero de lineas entre el numero de datos que piden)

        pCentro.setLayout(new GridLayout(calcularfilas(numeroConexiones), 3)); //panel que estara el cento debe tener un layout grid(layout de tipo tabla sencilla)
        //Agregando paneles con conexiones
        int corrida = 0; //es la guia de los datos dentro del array 
        for (int i = 0; i < numeroConexiones; i++) {

            PanelConexion panelConexion = new PanelConexion();   //creamos un obejto de la clase panelConexion que es un panel 
            panelConexion.setLayout(new GridLayout(5, 2));       //dividemos el panelConexion en un grid de 5*2
            if (i % 2 == 0) {                        //si el panel es non lleva un color si es par lleva otro 
                panelConexion.setBackground(color1);
            } else {
                panelConexion.setBackground(color2);
            }

            for (int j = 0; j < 5; j++) {       //ponemos el contenido en el panel que se creo

                panelConexion.add(new JLabel(etiquetasPaneles.get(j)));    //obtenetmos la etiqueta del array de strings de nombre de etiquetas 
                panelConexion.getInfoConexion().get(j).setText(conexionesArchivo.get(corrida)); //obtenemos la info del panel Conexion y la ponemos en la label del panel

                if (j == 4) {   //si j es 4 es la contraseña por lo que no se muestra en el panelConexion, pero si se cuenta en corrida para no alterar el flujo 
                    corrida++;
                    continue;

                }

                panelConexion.add(panelConexion.getInfoConexion().get(j));   //si j no es 4 ,ponemos la informacion en el panel 
                corrida++;     //lleva la cuenta de la linea en el archivo cbd.txt                    
            }

            panelConexion.addEventos(oyente); //le agrega el evento a cada panel 
            panelConexion.setName(i + ""); // le ponemos un numero identificador a cada panel
            conexiones.add(panelConexion); //agregamos cada panel a un array de panelConexion
            pCentro.add(panelConexion); //agregamos el panel que esta en el centro del panel del frame principal 

        }

        // agregar paneles de relleno
        if (numeroConexiones % 3 > 0) { //si numero de conexion modelo 3 es 0 quiere decir que faltan paneles para llenar la grilla 
            for (int i = numeroConexiones % 3; i < 3; i++) {    //desde el panel que nos quedamos hasta llenar la final , pon paneles vacios

                pCentro.add(new JPanel());
            }
        }
        //-------------------------------------------------------------------------------------------
        scroll = new JScrollPane(pCentro);
        scroll.setBorder(BorderFactory.createLineBorder(pCentro.getBackground(), 15));
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //--------------------------------------------------------------------------------------------

        lNumeroConexiones.setText("Numero de Conexiones: " + numeroConexiones); //le ponemos a la etiqueta el numero de conexiones que hay
        pSur.add(lNumeroConexiones);  //agregamos la etiqueta con el numero de conexiones al panel que va a estar en la parte sur del panel principal del frame
        this.add(pSur, "South");      //panel sur al sur del panel principal
        this.add(pNorte, "North");    //panel norte al norte del panel pricipal
        this.add(scroll, "Center");   //scroll en el centro de este panel

        addEventos(); //agregamos eventos a los elementos del panel 
        
        agregartips();

    }
    /**
     * Elimina la conexion de manera visual , es decir solo en la pantalla, ademàs 
     * vuleve a poner los paneles que quedaron de manera acomodada de nuevo, y si es 
     * necesario agrega paneles vacios para que no se distorsionen los paneles
     * @param void
     */
    public void eliminarConexion() {

        numeroConexiones--;

        pCentro.removeAll();
        pCentro.setLayout(new GridLayout(calcularfilas(numeroConexiones), 3));

       

        for (int i = 0; i < conexiones.size(); i++) {
            conexiones.get(i).setName(i + "");

            if (i % 2 == 0) {
                conexiones.get(i).setBackground(color1);
            } else {
                conexiones.get(i).setBackground(color2);
            }
            pCentro.add(conexiones.get(i));
        }

        if (conexiones.size() % 3 > 0) {
            for (int i = conexiones.size() % 3; i < 3; i++) {
                pCentro.add(new JPanel());
            }
        }

        redimensionarPanel();
    }
    /**
     * Agregara de manera visual, es decir, en la ventana, un nuevo panel 
     * con la informacion proporcionada en el panel conexion recibido, ademas
     * se actualizara las medidas del panel centro y del scroll para que se 
     * ajuste a todas las conexiones 
     * @param pc el panel conexion con los datos de la nuevaConexion
     */
    public void agregarConexion(PanelConexion pc) {
        numeroConexiones++;

        pCentro.removeAll();
        pCentro.setLayout(new GridLayout(calcularfilas(numeroConexiones), 3));

        System.out.println("Conexiones" + conexiones.size());

        for (int i = 0; i < conexiones.size(); i++) {
            conexiones.get(i).setName(i + "");

            if (i % 2 == 0) {
                conexiones.get(i).setBackground(color1);
            } else {
                conexiones.get(i).setBackground(color2);
            }
            pCentro.add(conexiones.get(i));
        }

        if (conexiones.size() % 3 > 0) {
            for (int i = conexiones.size() % 3; i < 3; i++) {
                pCentro.add(new JPanel());
            }
        }
        redimensionarPanel();
    }
    /**
     * Acualiza el panel centro y el scroll con los nuevos tamaños necesarios
     * para que quepan los paneles que quedaron despues de agregar o quitar un panel 
     * 
     * @param void
     */
    public void redimensionarPanel() {
        int alto = (int) Math.ceil(conexiones.size() / 3);

        pCentro.setSize(900, 176 * alto);
        scroll.setSize(900, 176 * alto);

        lNumeroConexiones.setText("Numero de Conexiones: " + conexiones.size());
        this.updateUI();
        scroll.updateUI();
    }
    
    
    /**
     * calcula el numero de filas necesario de acuerdo a cuantas columnas 
     * necesitamos en este caso es 3 por eso dividimos entre 3 
     * @param numconx numero de conexiones , al dividirlo entre 3 obtenemos cuantas filas necesitamos
     * @return a numero de filas necesarias deacuerdo con el numero de conexiones
     */
    private int calcularfilas(int numconx) {
        double b = 3;
        double c = (double) numconx;
        int a = (int) Math.ceil(c / b);

        return a;
    }
   /**
    * Agrega los eventos a los componentes que lo necesitan en este panel 
    * @param void
    */
    public void addEventos() {
        bAgregarConexion.addActionListener(this.oyente);
       
    }
    
     private void agregartips() {
        bAgregarConexion.setToolTipText("Haz clic aqui para agregar una nueva conexión a la lista de conexiones");
        bAgregarConexion.setCursor(new Cursor(Cursor.HAND_CURSOR));
         for (PanelConexion conexione : conexiones) {
             conexione.setToolTipText("Conexion #" +conexione.getName());
             conexione.setCursor(new Cursor(Cursor.HAND_CURSOR));
         }
    }
    
     //GETTERS Y SETTERS --------------------------------------------------------------------------------------------------------------
    
    public ArrayList<PanelConexion> getConexiones() {
        return conexiones;
    }

    public ArrayList<String> getConexionesArchivo() {
        return conexionesArchivo;
    }

    public void setConexionesArchivo(ArrayList<String> ConexionesArchivo) {
        this.conexionesArchivo = ConexionesArchivo;
    }

    public JButton getbAgregarConexion() {
        return bAgregarConexion;
    }

    public JScrollPane getScroll() {
        return scroll;
    }

}
