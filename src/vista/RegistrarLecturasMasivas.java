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

public class RegistrarLecturasMasivas extends Container {

    private static final Font FUENTE_TITULO = new Font(null, Font.BOLD, 28);
    private static final Font FUENTE_ETIQUETAS = new Font(null, Font.BOLD, 22);
    private static final Color COLOR_FONDO = new Color(232,234,246);

    private JPanel contenido;
    private JLabel titulo;
    private JTextField lecturas;
    private JButton seleccionar;
    private JButton registrar;

    public RegistrarLecturasMasivas(){
        iniciarComponentes();
    }
    
    private void iniciarComponentes() {

        contenido = new JPanel();
        titulo = new JLabel("Registrar Lecturas");
        lecturas = new JTextField("Archivo de Lecturas");
        seleccionar = new JButton("Seleccionar");
        registrar = new JButton("Registrar");
        contenido.setLayout(null);
        contenido.setVisible(true);
        contenido.setOpaque(true);
        contenido.setBackground(COLOR_FONDO);

        //Titulo
        titulo.setFont(FUENTE_TITULO);
        titulo.setVisible(true);
        titulo.setBounds(500, 30, 400, 35);
        contenido.add(titulo);

        //Formulario
        lecturas.setVisible(true);
        lecturas.setBounds(370, 150, 250, 30);
        contenido.add(lecturas);
        seleccionar.setFont(FUENTE_ETIQUETAS);
        seleccionar.setVisible(true);
        seleccionar.setBounds(630, 150, 200, 30);
        contenido.add(seleccionar);
        
        registrar.setFont(FUENTE_ETIQUETAS);
        registrar.setVisible(true);
        registrar.setBounds(550, 450, 200, 30);
        contenido.add(registrar);

        this.add(contenido, BorderLayout.CENTER);
    }        
}