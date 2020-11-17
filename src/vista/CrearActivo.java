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

public class CrearActivo extends Container {

    private static final Font FUENTE_TITULO = new Font(null, Font.BOLD, 28);
    private static final Font FUENTE_ETIQUETAS = new Font(null, Font.BOLD, 22);
    private static final Color COLOR_FONDO = new Color(232,234,246);

    private JPanel contenido;
    private JLabel titulo;
    private JLabel numSerie;
    private JTextField numSerieCampo;
    private JLabel nombre;
    private JTextField nombreCampo;
    private JLabel ciudad;
    private JComboBox ciudadList;
    private JLabel estado;
    private JComboBox estadoList;
    private JButton enviar;

    public CrearActivo(){
        iniciarComponentes();
    }
    
    private void iniciarComponentes() {

        contenido = new JPanel();
        titulo = new JLabel("Crear Activos");
        numSerie = new JLabel("Número de Serie");
        numSerieCampo = new JTextField();
        nombre = new JLabel("Nombre");
        nombreCampo = new JTextField();
        ciudad = new JLabel("Ciudad");
        ciudadList = new JComboBox();
        estado = new JLabel("Estado");
        estadoList = new JComboBox();
        enviar = new JButton("Enviar");

        contenido.setLayout(null);
        contenido.setVisible(true);
        contenido.setOpaque(true);
        contenido.setBackground(COLOR_FONDO);

        //Titulo
        titulo.setFont(FUENTE_TITULO);
        titulo.setVisible(true);
        titulo.setBounds(550, 30, 200, 25);
        contenido.add(titulo);

        //Formulario
        numSerie.setFont(FUENTE_ETIQUETAS);
        numSerie.setVisible(true);
        numSerie.setBounds(150, 150, 200, 30);
        contenido.add(numSerie);
        numSerieCampo.setVisible(true);
        numSerieCampo.setBounds(330, 150, 200, 30);
        contenido.add(numSerieCampo);

        nombre.setFont(FUENTE_ETIQUETAS);
        nombre.setVisible(true);
        nombre.setBounds(800, 150, 150, 30);
        contenido.add(nombre);
        nombreCampo.setVisible(true);
        nombreCampo.setBounds(890, 150, 200, 30);
        contenido.add(nombreCampo);

        ciudad.setFont(FUENTE_ETIQUETAS);
        ciudad.setVisible(true);
        ciudad.setBounds(150, 300, 150, 30);
        contenido.add(ciudad);
        ciudadList.setVisible(true);
        ciudadList.setBounds(330, 300, 200, 30);
        contenido.add(ciudadList);

        estado.setFont(FUENTE_ETIQUETAS);
        estado.setVisible(true);
        estado.setBounds(800, 300, 150, 30);
        contenido.add(estado);
        estadoList.setVisible(true);
        estadoList.setBounds(890, 300, 200, 30);
        contenido.add(estadoList);
        
        enviar.setFont(FUENTE_ETIQUETAS);
        enviar.setVisible(true);
        enviar.setBounds(550, 450, 200, 30);
        contenido.add(enviar);

        this.add(contenido, BorderLayout.CENTER);
    }        
}