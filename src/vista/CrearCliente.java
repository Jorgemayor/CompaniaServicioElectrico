package src.vista;

import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import src.managers.gestionCiudad.GestionCiudadApi;

public class CrearCliente extends Container {

    private static final Font FUENTE_TITULO = new Font(null, Font.BOLD, 28);
    private static final Font FUENTE_ETIQUETAS = new Font(null, Font.BOLD, 22);
    private static final Color COLOR_FONDO = new Color(232, 234, 246);

    private JPanel contenedor;
    private JLabel etiquetaTitulo;
    private JLabel etiquetaTipoIdentificacion;
    private JComboBox<String> selectorTipoIdentificacion;
    private JLabel etiquetaIdentificacion;
    private JTextField campoIdentificacion;
    private JLabel etiquetaNombre;
    private JTextField campoNombre;
    private JLabel etiquetaDireccion;
    private JTextField campoDireccion;
    private JLabel etiquetaCiudad;
    private JComboBox<String> selectorCiudad;
    private JButton botonEnviar;

    public CrearCliente() {
        iniciarComponentes();
    }

    private void iniciarComponentes() {

        contenedor = new JPanel();
        etiquetaTitulo = new JLabel("Crear Cliente");
        etiquetaTipoIdentificacion = new JLabel("Tipo de ID");
        selectorTipoIdentificacion = new JComboBox<String>();
        etiquetaIdentificacion = new JLabel("Documento Identidad");
        campoIdentificacion = new JTextField();
        etiquetaNombre = new JLabel("Nombre");
        campoNombre = new JTextField();
        etiquetaDireccion = new JLabel("Dirección");
        campoDireccion = new JTextField();
        etiquetaCiudad = new JLabel("Ciudad");
        selectorCiudad = new JComboBox<String>();
        botonEnviar = new JButton("Enviar");

        contenedor.setLayout(null);
        contenedor.setVisible(true);
        contenedor.setBackground(COLOR_FONDO);

        // Titulo
        etiquetaTitulo.setFont(FUENTE_TITULO);
        etiquetaTitulo.setVisible(true);
        etiquetaTitulo.setBounds(550, 30, 200, 25);
        contenedor.add(etiquetaTitulo);

        // Formulario

        // Tipo de identificación
        etiquetaTipoIdentificacion.setFont(FUENTE_ETIQUETAS);
        etiquetaTipoIdentificacion.setVisible(true);
        etiquetaTipoIdentificacion.setBounds(150, 150, 200, 30);
        contenedor.add(etiquetaTipoIdentificacion);

        selectorTipoIdentificacion.setVisible(true);
        selectorTipoIdentificacion.setBounds(330, 150, 200, 30);
        selectorTipoIdentificacion.addItem("RC");
        selectorTipoIdentificacion.addItem("TI");
        selectorTipoIdentificacion.addItem("CC");
        contenedor.add(selectorTipoIdentificacion);

        // Identificación
        etiquetaIdentificacion.setFont(FUENTE_ETIQUETAS);
        etiquetaIdentificacion.setVisible(true);
        etiquetaIdentificacion.setBounds(670, 150, 250, 30);
        contenedor.add(etiquetaIdentificacion);

        campoIdentificacion.setVisible(true);
        campoIdentificacion.setBounds(890, 150, 200, 30);
        campoIdentificacion.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) ||
                (c == KeyEvent.VK_BACK_SPACE) ||
                (c == KeyEvent.VK_DELETE))) {
                    getToolkit().beep();
                    e.consume();
                    JOptionPane.showMessageDialog(null, "En este campo solo se admiten valores numéricos");
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void keyReleased(KeyEvent e) {
                // TODO Auto-generated method stub

            }
            
        });
        contenedor.add(campoIdentificacion);

        //Nombre
        etiquetaNombre.setFont(FUENTE_ETIQUETAS);
        etiquetaNombre.setVisible(true);
        etiquetaNombre.setBounds(150, 225, 150, 30);
        contenedor.add(etiquetaNombre);

        campoNombre.setVisible(true);
        campoNombre.setBounds(330, 225, 200, 30);
        contenedor.add(campoNombre);

        //Dirección
        etiquetaDireccion.setFont(FUENTE_ETIQUETAS);
        etiquetaDireccion.setVisible(true);
        etiquetaDireccion.setBounds(150, 300, 150, 30);
        contenedor.add(etiquetaDireccion);

        campoDireccion.setVisible(true);
        campoDireccion.setBounds(330, 300, 200, 30);
        contenedor.add(campoDireccion);

        //Ciudad
        etiquetaCiudad.setFont(FUENTE_ETIQUETAS);
        etiquetaCiudad.setVisible(true);
        etiquetaCiudad.setBounds(800, 225, 150, 30);
        contenedor.add(etiquetaCiudad);
        JSONObject ciudades = new JSONObject(GestionCiudadApi.obtenerCiudades());
        JSONArray arregloCiudades = ciudades.getJSONArray("nombre");
        for(int i = 0; i<arregloCiudades.length(); i++){
            selectorCiudad.addItem(arregloCiudades.getString(i));
        }
        selectorCiudad.setVisible(true);
        selectorCiudad.setBounds(890, 225, 200, 30);
        contenedor.add(selectorCiudad);
        
        botonEnviar.setFont(FUENTE_ETIQUETAS);
        botonEnviar.setVisible(true);
        botonEnviar.setBounds(550, 450, 200, 30);
        botonEnviar.addActionListener(new ActionListener() {
            @Override
             public void actionPerformed(ActionEvent event) {
 
                 String tipoIdentificacion = (String)selectorTipoIdentificacion.getSelectedItem();
                 String textoIdentificacion = (String)campoIdentificacion.getText();
                 int identificacion = Integer.parseInt(textoIdentificacion);
                 String nombre = (String)campoNombre.getText();
                 String direccion = (String)campoDireccion.getText();
                 JSONArray arregloID = ciudades.getJSONArray("id");
                 int indiceSelector = selectorCiudad.getSelectedIndex();
                 int ciudad = arregloID.getInt(indiceSelector);
 
                 String resultado = GestionClienteApi.agregarCliente(tipoIdentificacion,identificacion,nombre,direccion,ciudad);
                 JSONObject jsonResultado = new JSONObject(resultado);
                 String codigo = jsonResultado.getString("code");
 
                 if (codigo.equals("0")) {
                     JOptionPane.showMessageDialog(null, "Cliente Creado");
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
