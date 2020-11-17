package src.vista;

import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Configurar extends Container {

    private JPanel contenido = new JPanel();
    private JLabel titulo = new JLabel("Configuración");
    private JLabel precioKWH = new JLabel("Precio KWH");
    private JTextField kwhCampo = new JTextField();
    private JLabel precioReconexion = new JLabel("Precio Reconexión");
    private JTextField reconexionCampo = new JTextField();
    private JLabel rol = new JLabel("Rol");
    private JComboBox rolList = new JComboBox();
    private JButton enviar = new JButton("Enviar Cambios");
    private static final Font FUENTE_TITULO = new Font(null, Font.BOLD, 28);
    private static final Font FUENTE_ETIQUETAS = new Font(null, Font.BOLD, 22);
    private static final Color COLOR_FONDO = new Color(232,234,246);

    public Configurar(){
        iniciarComponentes();
    }
    
    private void iniciarComponentes() {
        contenido.setLayout(null);
        contenido.setVisible(true);
        contenido.setBackground(COLOR_FONDO);

        //Titulo
        titulo.setFont(FUENTE_TITULO);
        titulo.setVisible(true);
        titulo.setBounds(525, 30, 200, 40);
        contenido.add(titulo);

        //Formulario
        precioKWH.setFont(FUENTE_ETIQUETAS);
        precioKWH.setVisible(true);
        precioKWH.setBounds(460, 150, 200, 30);
        contenido.add(precioKWH);
        kwhCampo.setVisible(true);
        kwhCampo.setBounds(615, 150, 200, 30);
        contenido.add(kwhCampo);

        precioReconexion.setFont(FUENTE_ETIQUETAS);
        precioReconexion.setVisible(true);
        precioReconexion.setBounds(400, 250, 200, 30);
        contenido.add(precioReconexion);
        reconexionCampo.setVisible(true);
        reconexionCampo.setBounds(615, 250, 200, 30);
        contenido.add(reconexionCampo);
        
        enviar.setFont(FUENTE_ETIQUETAS);
        enviar.setVisible(true);
        enviar.setBounds(550, 450, 200, 30);
        contenido.add(enviar);

        this.add(contenido, BorderLayout.CENTER);
    }
}