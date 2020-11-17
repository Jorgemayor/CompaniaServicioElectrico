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
import javax.swing.JToggleButton;

public class DeshabilitarCliente extends Container {

    private static final Font FUENTE_TITULO = new Font(null, Font.BOLD, 28);
    private static final Font FUENTE_ETIQUETAS = new Font(null, Font.BOLD, 22);
    private static final Color COLOR_FONDO = new Color(232,234,246);

    private JPanel contenido;
    private JLabel titulo;
    private JLabel tipoId;
    private JComboBox tipoIdList;
    private JLabel identificacion;
    private JTextField identificacionCampo;
    private JButton ver;
    private JLabel nombre;
    private JLabel elNombre;
    private JLabel direccion;
    private JLabel laDireccion;
    private JLabel ciudad;
    private JLabel laCiudad;
    private JToggleButton estado;
    private JButton enviar;

    public DeshabilitarCliente(){
        iniciarComponentes();
    }
    
    private <ActionListener> void iniciarComponentes() {

        contenido = new JPanel();
        titulo = new JLabel("Deshabilitar Cliente");
        tipoId = new JLabel("Tipo de ID");
        tipoIdList = new JComboBox();
        identificacion = new JLabel("ID");
        identificacionCampo = new JTextField();
        ver = new JButton("ver");
        nombre = new JLabel("Nombre:");
        elNombre = new JLabel("Carlos Mauricio Tovar Parra");
        direccion = new JLabel("Dirección:");
        laDireccion = new JLabel("Calle 33F #24B-39 Sta. Mónica Popular");
        ciudad = new JLabel("Ciudad:");
        laCiudad = new JLabel("Santiago de Cali");
        estado = new JToggleButton("Hablitar/Deshabilitar");
        enviar = new JButton("Enviar Cambios");

        contenido.setLayout(null);
        contenido.setVisible(true);
        contenido.setBackground(COLOR_FONDO);

        //Titulo
        titulo.setFont(FUENTE_TITULO);
        titulo.setVisible(true);
        titulo.setBounds(530, 30, 300, 25);
        contenido.add(titulo);

        //Formulario
        tipoId.setFont(FUENTE_ETIQUETAS);
        tipoId.setVisible(true);
        tipoId.setBounds(150, 150, 200, 30);
        contenido.add(tipoId);
        tipoIdList.setVisible(true);
        tipoIdList.setBounds(300, 150, 200, 30);
        contenido.add(tipoIdList);

        identificacion.setFont(FUENTE_ETIQUETAS);
        identificacion.setVisible(true);
        identificacion.setBounds(800, 150, 150, 30);
        contenido.add(identificacion);
        identificacionCampo.setVisible(true);
        identificacionCampo.setBounds(830, 150, 200, 30);
        contenido.add(identificacionCampo);
        ver.setFont(FUENTE_ETIQUETAS);
        ver.setVisible(true);
        ver.setBounds(1035, 150, 70, 30);
        contenido.add(ver);

        nombre.setFont(FUENTE_ETIQUETAS);
        nombre.setVisible(true);
        nombre.setBounds(150, 225, 150, 30);
        contenido.add(nombre);
        elNombre.setVisible(true);
        elNombre.setBounds(300, 225, 200, 30);
        contenido.add(elNombre);

        direccion.setFont(FUENTE_ETIQUETAS);
        direccion.setVisible(true);
        direccion.setBounds(150, 300, 150, 30);
        contenido.add(direccion);
        laDireccion.setVisible(true);
        laDireccion.setBounds(300, 300, 200, 30);
        contenido.add(laDireccion);

        ciudad.setFont(FUENTE_ETIQUETAS);
        ciudad.setVisible(true);
        ciudad.setBounds(800, 225, 150, 30);
        contenido.add(ciudad);
        laCiudad.setVisible(true);
        laCiudad.setBounds(890, 225, 220, 30);
        contenido.add(laCiudad);

        estado.setFont(FUENTE_ETIQUETAS);
        estado.setVisible(true);
        estado.setBounds(800, 300, 250, 30);
        contenido.add(estado);
        
        enviar.setFont(FUENTE_ETIQUETAS);
        enviar.setVisible(true);
        enviar.setBounds(550, 450, 200, 30);
        contenido.add(enviar);

        this.add(contenido, BorderLayout.CENTER);
    }
}