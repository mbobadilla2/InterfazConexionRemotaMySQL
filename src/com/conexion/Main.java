package com.conexion;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Esta es la clase main desde donde se iniciarán todos los enlaces necesarios
 * para el comienzo del programa y el mismo programa.
 *
 * @author Miguel Fernando Bobadilla Contreras
 * @author Luis Angel Pérez Muñoz
 * @author José Rubén Perez García
 * @author José Ramón Márquez Solano
 * @author Valery Abigail Cambara Gil
 * @version 6/Noviembre/2014 /A
 *
 */
public class Main {         //Clase principal 

    public static void main(String[] args) { //Método main 
        // Colocamos el look and feel del so que esté corriendo la aplicación
        setLookAndFeel();

        JFrame ventanaPrincipal = new JFrame("Proyecto fundamentos de base de datos"); //Instanciamos una Ventana tipo JFrame Con su nombre en el Constructor
        MiOyente oyente = new MiOyente();  //Instanciamos un oyente de la clase Mi Oyente  
        OyenteConexion oyenteConexion = new OyenteConexion(oyente); //Instanciamos un segundo oyente de la calse OyenteConexion
        MiPanel panel = new MiPanel(oyente);   // Creamos un panel de tipo MiPanel y le pasamos el primer oyente 
        MenuEmergente menuEmergente = new MenuEmergente(); //creamos un menu de tipo MenuEmergente 
//        Login log = new Login();

        //-------------------------------------------------------------------------------------------------
        //Agregar eventos de todas los componentes iniciales...
        menuEmergente.addEventos(oyente);   //Asignamos el Oyente al menu Emergente 

        //Damos acceso al menú conextual al oyente...
        oyente.setMenuEm(menuEmergente);  //Este menu emergente es el mismo que el que se manejara en oyente
        oyente.setConexiones(panel.getConexiones()); //Oyente va a tener las conexiones que tiene panel cuando se inicia
        oyente.setPanel(panel);  //este panel es el mismo que se manejara en oyente 
//        oyente.setLogin(log);

        // damos acceso de ventana nueva conexion al oyenteconexion
        oyenteConexion.setPanel(panel); //este panel es el mismo que se usa en oyente conexion

        //--------------------------------------------------------------------------------
        ventanaPrincipal.setSize(930, 500); //Definimos el tamaño de la ventana principal
        ventanaPrincipal.setLocationRelativeTo(null);  //Colocamos la ventana principal en medio de la pantalla
        ventanaPrincipal.setResizable(false); //Restringimos que la ventana principal pueda modificar su tamaño
        ventanaPrincipal.add(panel);        //Agregamos el panel a la ventana 
        ventanaPrincipal.setVisible(true);  //Ponemos visible a la ventana principal
        ventanaPrincipal.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);   //Definimos el comportamiento de la ventana principal cuando le apretamos el boton cerrar
        ventanaPrincipal.addWindowListener(oyenteConexion);
    }
    
    public static void setLookAndFeel(){
       try{
            //Poner el nombre de la aplicacion en la barra de tareas (OS X)
            System.setProperty( "com.apple.mrj.application.apple.menu.about.name", "PinaProject" );
            
            //Poner el JMenuBar en la barra de tareas de OS X
            System.setProperty( "com.apple.macos.useScreenMenuBar", "true" );
            
            //Poner el JMenuBar en la barra de tareas de OS X -Versiones antiguas de Java
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            
            //Colocar el look and feel del sistema operativo que se esté usando
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            
        }catch(UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException
                | IllegalAccessException e){
            
        }
         
    }
}
