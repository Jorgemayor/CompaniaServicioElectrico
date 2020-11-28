package src.vista;

import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.JSONArray;
import org.json.JSONObject;

import src.managers.gestionClientes.GestionClienteApi;
import src.managers.gestionActivos.GestionActivosApi;
import src.managers.gestionCiudad.GestionCiudadApi;

public class CrearActivo extends Container {

    private static final Font FUENTE_TITULO = new Font(null, Font.BOLD, 28);
    private static final Font FUENTE_ETIQUETAS = new Font(null, Font.BOLD, 22);
    private static final Color COLOR_FONDO = new Color(232,234,246);

    private JPanel contenedor;
    private JLabel etiquetaTitulo;
    private JLabel EtiquetaNumSerie;
    private JTextField campoNumSerie;
    private JLabel etiquetaNombre;
    private JTextField campoNombre;
    private JLabel etiquetaCiudad;
    private JComboBox<String> selectorCiudad;
    private JLabel etiquetaEstado;
    private JComboBox<String> selectorEstado;
    private JButton botonEnviar;

    public CrearActivo(){
        iniciarComponentes();
    }
    
    private void iniciarComponentes() {

        contenedor = new JPanel();
        etiquetaTitulo = new JLabel("Crear Activos");
        EtiquetaNumSerie = new JLabel("Número de Serie");
        campoNumSerie = new JTextField();
        etiquetaNombre = new JLabel("Nombre");
        campoNombre = new JTextField();
        etiquetaCiudad = new JLabel("Ciudad");
        selectorCiudad = new JComboBox();
        etiquetaEstado = new JLabel("Estado");
        selectorEstado = new JComboBox();
        botonEnviar = new JButton("Enviar");

        contenedor.setLayout(null);
        contenedor.setVisible(true);
        contenedor.setOpaque(true);
        contenedor.setBackground(COLOR_FONDO);

        //Titulo
        etiquetaTitulo.setFont(FUENTE_TITULO);
        etiquetaTitulo.setVisible(true);
        etiquetaTitulo.setBounds(550, 30, 200, 25);
        contenedor.add(etiquetaTitulo);

        //Formulario
        EtiquetaNumSerie.setFont(FUENTE_ETIQUETAS);
        EtiquetaNumSerie.setVisible(true);
        EtiquetaNumSerie.setBounds(150, 150, 200, 30);
        contenedor.add(EtiquetaNumSerie);
        campoNumSerie.setVisible(true);
        campoNumSerie.setBounds(330, 150, 200, 30);
        contenedor.add(campoNumSerie);

        etiquetaNombre.setFont(FUENTE_ETIQUETAS);
        etiquetaNombre.setVisible(true);
        etiquetaNombre.setBounds(800, 150, 150, 30);
        contenedor.add(etiquetaNombre);
        campoNombre.setVisible(true);
        campoNombre.setBounds(890, 150, 200, 30);
        contenedor.add(campoNombre);

        etiquetaCiudad.setFont(FUENTE_ETIQUETAS);
        etiquetaCiudad.setVisible(true);
        etiquetaCiudad.setBounds(150, 300, 150, 30);
        contenedor.add(etiquetaCiudad);
        JSONObject ciudades = new JSONObject(GestionCiudadApi.obtenerCiudades());
        JSONArray arregloCiudades = ciudades.getJSONArray("nombre");
        for(int i = 0; i<arregloCiudades.length(); i++){
            selectorCiudad.addItem(arregloCiudades.getString(i));
        }
        selectorCiudad.setVisible(true);
        selectorCiudad.setBounds(330, 300, 200, 30);
        contenedor.add(selectorCiudad);

        etiquetaEstado.setFont(FUENTE_ETIQUETAS);
        etiquetaEstado.setVisible(true);
        etiquetaEstado.setBounds(800, 300, 150, 30);
        contenedor.add(etiquetaEstado);
        selectorEstado.setVisible(true);
        selectorEstado.setBounds(890, 300, 200, 30);
        selectorEstado.addItem("Buen estado");
        selectorEstado.addItem("Dañado");
        selectorEstado.addItem("En reparación");
        contenedor.add(selectorEstado);
        
        botonEnviar.setFont(FUENTE_ETIQUETAS);
        botonEnviar.setVisible(true);
        botonEnviar.setBounds(550, 450, 200, 30);
        botonEnviar.addActionListener(new ActionListener() {
            @Override
             public void actionPerformed(ActionEvent event) {
  
                 String numSerie = (String)campoNumSerie.getText();
                 String nombre = (String)campoNombre.getText();
                 JSONArray arregloID = ciudades.getJSONArray("id");
                 int indiceSelector = selectorCiudad.getSelectedIndex();
                 int ciudad = arregloID.getInt(indiceSelector);
                 String estado = (String)selectorEstado.getSelectedItem();
 
                 String resultado = GestionActivosApi.registrarActivo(numSerie, nombre, ciudad, estado);
                 JSONObject jsonResultado = new JSONObject(resultado);
                 String codigo = jsonResultado.getString("code");
 
                 if (codigo.equals("0")) {
                     JOptionPane.showMessageDialog(null, "Activo Creado");
                 } else {
                     String mensaje = jsonResultado.getString("mensaje");
                     JOptionPane.showMessageDialog(null, mensaje);
                 }
             }
         });
        contenedor.add(botonEnviar);

        this.add(contenedor, BorderLayout.CENTER);
    }        
}