package src.vista;

import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.JSONArray;
import org.json.JSONObject;

import src.managers.gestionClientes.*;
import src.managers.gestionCiudad.GestionCiudadApi;

public class ModificarCliente extends Container {

    private static final Font FUENTE_TITULO = new Font(null, Font.BOLD, 28);
    private static final Font FUENTE_ETIQUETAS = new Font(null, Font.BOLD, 22);
    private static final Color COLOR_FONDO = new Color(232,234,246);

    private JPanel panelContenido;
    private JLabel etiquetaTitulo;
    private JLabel etiquetaTipoId;
    private JComboBox selectorTipoIdentificacion;
    private JLabel etiquetaIdentificacion;
    private JTextField campoIdentificacion;
    private JButton botonVer;
    private JLabel etiquetaNombre;
    private JTextField campoNombre;
    private JLabel etiquetaDireccion;
    private JTextField campoDireccion;
    private JLabel etiquetaCiudad;
    private JComboBox selectorCiudad;
    private JButton botonEnviar;
    private int idClienteActual;

    public ModificarCliente(){
        iniciarComponentes();
    }
    
    private void iniciarComponentes() {

        panelContenido = new JPanel();
        etiquetaTitulo = new JLabel("Modificar Cliente");
        etiquetaTipoId = new JLabel("Tipo de ID");
        selectorTipoIdentificacion = new JComboBox();
        etiquetaIdentificacion = new JLabel("Documento Identidad");
        campoIdentificacion = new JTextField();
        botonVer = new JButton("ver");
        etiquetaNombre = new JLabel("Nombre");
        campoNombre = new JTextField();
        etiquetaDireccion = new JLabel("Dirección");
        campoDireccion = new JTextField();
        etiquetaCiudad = new JLabel("ciudad");
        selectorCiudad = new JComboBox();
        botonEnviar = new JButton("Enviar Cambios");

        panelContenido.setLayout(null);
        panelContenido.setVisible(true);
        panelContenido.setBackground(COLOR_FONDO);

        //Titulo
        etiquetaTitulo.setFont(FUENTE_TITULO);
        etiquetaTitulo.setVisible(true);
        etiquetaTitulo.setBounds(550, 30, 300, 25);
        panelContenido.add(etiquetaTitulo);

        //Formulario

        etiquetaIdentificacion.setFont(FUENTE_ETIQUETAS);
        etiquetaIdentificacion.setVisible(true);
        etiquetaIdentificacion.setBounds(100, 150, 250, 30);
        panelContenido.add(etiquetaIdentificacion);
        campoIdentificacion.setVisible(true);
        campoIdentificacion.setBounds(330, 150, 200, 30);
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
        panelContenido.add(campoIdentificacion);

        botonVer.setFont(FUENTE_ETIQUETAS);
        botonVer.setVisible(true);
        botonVer.setBounds(540, 150, 70, 30);
        botonVer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JSONObject resultado = new JSONObject(GestionClienteApi.obtenerClientePorId(campoIdentificacion.getText()));
                if(resultado.getString("code").equals("0") & botonVer.getText()=="ver"){
                    JSONObject cliente = new JSONObject(resultado.getString("cliente"));
                    int id = cliente.getJSONArray("id").getInt(0);
                    String tipoIdentificacion = cliente.getJSONArray("tipo_identificacion").getString(0);
                    int identificacion = cliente.getJSONArray("identificacion").getInt(0);
                    String nombre = cliente.getJSONArray("nombre").getString(0);
                    String direccion = cliente.getJSONArray("direccion").getString(0);
                    int idCiudad = cliente.getJSONArray("id_ciudad").getInt(0);
                    String stringCiudad = GestionCiudadApi.obtenerCiudadPorId(idCiudad);
                    idClienteActual = id;
                    selectorTipoIdentificacion.setSelectedItem(tipoIdentificacion);
                    campoIdentificacion.setText(String.valueOf(identificacion));
                    campoNombre.setText(nombre);
                    campoDireccion.setText(direccion);
                    selectorCiudad.setSelectedItem(stringCiudad);
                    botonVer.setText("limpiar");
                }
                else if(botonVer.getText()=="limpiar"){
                    idClienteActual = -1;
                    selectorTipoIdentificacion.setSelectedIndex(0);
                    campoIdentificacion.setText("");
                    campoNombre.setText("");
                    campoDireccion.setText("");
                    selectorCiudad.setSelectedIndex(0);
                    botonVer.setText("ver");
                }
                else{
                    JOptionPane.showMessageDialog(null, resultado.getString("mensaje"));
                }
            }
            
        });
        panelContenido.add(botonVer);

        etiquetaTipoId.setFont(FUENTE_ETIQUETAS);
        etiquetaTipoId.setVisible(true);
        etiquetaTipoId.setBounds(720, 150, 200, 30);
        panelContenido.add(etiquetaTipoId);
        selectorTipoIdentificacion.setVisible(true);
        selectorTipoIdentificacion.setBounds(830, 150, 200, 30);
        selectorTipoIdentificacion.addItem("RC");
        selectorTipoIdentificacion.addItem("TI");
        selectorTipoIdentificacion.addItem("CC");
        panelContenido.add(selectorTipoIdentificacion);


        etiquetaNombre.setFont(FUENTE_ETIQUETAS);
        etiquetaNombre.setVisible(true);
        etiquetaNombre.setBounds(150, 225, 150, 30);
        panelContenido.add(etiquetaNombre);
        campoNombre.setVisible(true);
        campoNombre.setBounds(330, 225, 200, 30);
        panelContenido.add(campoNombre);

        etiquetaDireccion.setFont(FUENTE_ETIQUETAS);
        etiquetaDireccion.setVisible(true);
        etiquetaDireccion.setBounds(150, 300, 150, 30);
        panelContenido.add(etiquetaDireccion);
        campoDireccion.setVisible(true);
        campoDireccion.setBounds(330, 300, 200, 30);
        panelContenido.add(campoDireccion);

        etiquetaCiudad.setFont(FUENTE_ETIQUETAS);
        etiquetaCiudad.setVisible(true);
        etiquetaCiudad.setBounds(800, 225, 150, 30);
        panelContenido.add(etiquetaCiudad);
        JSONObject ciudades = new JSONObject(GestionCiudadApi.obtenerCiudades());
        JSONArray arregloCiudades = ciudades.getJSONArray("nombre");
        for(int i = 0; i<arregloCiudades.length(); i++){
            selectorCiudad.addItem(arregloCiudades.getString(i));
        }
        selectorCiudad.setVisible(true);
        selectorCiudad.setBounds(890, 225, 220, 30);
        panelContenido.add(selectorCiudad);
        
        botonEnviar.setFont(FUENTE_ETIQUETAS);
        botonEnviar.setVisible(true);
        botonEnviar.setBounds(550, 450, 200, 30);
        botonEnviar.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent e) {
                String resultado = GestionClienteApi.actualizarCliente(selectorTipoIdentificacion.getSelectedItem().toString(),Integer.parseInt(campoIdentificacion.getText()),campoNombre.getText(),campoDireccion.getText(),selectorCiudad.getSelectedIndex()+1);
                JSONObject jsonResultado = new JSONObject(resultado);
                String codigo = jsonResultado.getString("code");
                if (codigo.equals("0")) {
                    JOptionPane.showMessageDialog(null, "Cliente Modificado");
                }
                else{
                    String mensaje = jsonResultado.getString("mensaje");
                    JOptionPane.showMessageDialog(null, mensaje);
                }
            }
        });
        panelContenido.add(botonEnviar);

        this.add(panelContenido, BorderLayout.CENTER);
    }
}

