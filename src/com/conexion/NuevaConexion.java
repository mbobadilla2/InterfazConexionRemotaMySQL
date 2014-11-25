package com.conexion;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Esta es la ventana que muestra el formulario para agregar una nueva conexion
 *
 * @author Miguel Fernando Bobadilla Contreras
 * @author Luis Angel Pérez Muñoz
 * @author José Rubén Perez García
 * @author José Ramón Márquez Solano
 * @author Valery Abigail Cambara Gil
 * @version 6/Noviembre/2014 /A
 *
 */
public class NuevaConexion extends JFrame {
//faltan modificadores de acceso y encapsulacion
    //variables de clase 

    private final JPanel panel = new JPanel();
    //etiquetas que se muestran del lado izquierdo de esta ventana 
    JLabel lNombreConexion = new JLabel("Nombre de la conexion :");
    JLabel lUsuario = new JLabel("Usuario :");
    JLabel lContrasenia = new JLabel("Contraseña :");
    JLabel lHost = new JLabel("Host :");
    JLabel lPuerto = new JLabel("Puerto :");
    //cuadros de texto que se muestran de lado derecho de la ventana 
    JTextField tNombreConexion = new JTextField();
    JTextField tUsuario = new JTextField();
    JPasswordField tpContrasenia = new JPasswordField(); //este tipo de textfield es el que pone a la contraseña con circulitos 
    JTextField tHost = new JTextField("127.0.0.1");
    JTextField tPuerto = new JTextField("3306");
    //botones que aparecen en la parte inferior de esta ventana 
    JButton bAceptar = new JButton("Aceptar");
    JButton bCancelar = new JButton("Cancelar");
    JButton bPrueba = new JButton("Probar Conexion");

    public NuevaConexion() {
        this.setTitle("Nueva Conexion");
        this.setSize(610, 200);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.add(panel);
        this.addComponentes();
        this.setVisible(true);
    }
    
    /**Agrega los componentes a esta ventana
     *@param void 
     */
    private void addComponentes() {
        panel.setLayout(new GridLayout(5, 2));
        JPanel pSur = new JPanel();

        panel.add(lNombreConexion);
        panel.add(tNombreConexion);
        panel.add(lHost);
        panel.add(tHost);
        panel.add(lPuerto);
        panel.add(tPuerto);
        panel.add(lUsuario);
        panel.add(tUsuario);
        panel.add(lContrasenia);
        panel.add(tpContrasenia);
        pSur.add(bCancelar);
        pSur.add(bPrueba);
        pSur.add(bAceptar);

        this.add(panel, "Center");
        this.add(pSur, "South");
        agregartips();
    }

    /**
     * Da los eventos a esta ventana a los elementos que lo necesitan
     *
     * @param oyente oyente que le dara sus eventos, de tipo OyenteConexion
     */
    public void addEventos(OyenteConexion oyente) {
        bAceptar.addActionListener(oyente);
        bCancelar.addActionListener(oyente);
        bPrueba.addActionListener(oyente);
    }

    private void agregartips() {
      bAceptar.setToolTipText("Clic aquí para guardar la conexión");
      bCancelar.setToolTipText("Regresar a la ventana de conexiones");
      bPrueba.setToolTipText("Clic aqui para porbar si los datos son reconocidos por MYSQL");
      tNombreConexion.setToolTipText("Escribe el nombre con el que reconocerás esta conexion");
      tHost.setToolTipText("Escribe la dirección del servidor MySQL");
      tPuerto.setToolTipText("Escribe el puerto de conexión");
      tUsuario.setToolTipText("Escribe el nombre del usuario agregado a MySQL");
      tpContrasenia.setToolTipText("Escribe la contraseña del usuario");
           
    }
}
