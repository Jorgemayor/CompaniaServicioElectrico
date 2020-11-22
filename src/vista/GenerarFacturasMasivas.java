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

public class GenerarFacturasMasivas extends Container {

    private static final Font FUENTE_TITULO = new Font(null, Font.BOLD, 28);
    private static final Font FUENTE_ETIQUETAS = new Font(null, Font.BOLD, 22);
    private static final Color COLOR_FONDO = new Color(232,234,246);

    private JPanel contenido;
    private JLabel titulo;
    private JTextField lecturas;
    private JButton seleccionar;
    private JButton generar;

    public GenerarFacturasMasivas(){
        iniciarComponentes();
    }
    
    private void iniciarComponentes() {

        contenido = new JPanel();
        titulo = new JLabel("Generar Facturas");
        lecturas = new JTextField("Archivo de Lecturas");
        seleccionar = new JButton("Seleccionar");
        generar = new JButton("Generar");
        contenido.setLayout(null);
        contenido.setVisible(true);
        contenido.setOpaque(true);
        contenido.setBackground(COLOR_FONDO);

        //Titulo
        titulo.setFont(FUENTE_TITULO);
        titulo.setVisible(true);
        titulo.setBounds(500, 30, 400, 25);
        contenido.add(titulo);

        //Formulario
        lecturas.setVisible(true);
        lecturas.setBounds(370, 150, 250, 30);
        contenido.add(lecturas);
        seleccionar.setFont(FUENTE_ETIQUETAS);
        seleccionar.setVisible(true);
        seleccionar.setBounds(630, 150, 200, 30);
        contenido.add(seleccionar);
        
        generar.setFont(FUENTE_ETIQUETAS);
        generar.setVisible(true);
        generar.setBounds(550, 450, 200, 30);
        contenido.add(generar);

        this.add(contenido, BorderLayout.CENTER);
    }        
}