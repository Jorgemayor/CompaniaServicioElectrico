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

public class ModificarCliente extends Container {

    private JPanel contenido = new JPanel();
    private JLabel titulo = new JLabel("Modificar Cliente");
    private JLabel tipoId = new JLabel("Tipo de ID");
    private JComboBox tipoIdList = new JComboBox();
    private JLabel identificacion = new JLabel("ID");
    private JTextField identificacionCampo = new JTextField();
    private JButton ver = new JButton("ver");
    private JLabel nombre = new JLabel("Nombre");
    private JTextField nombreCampo = new JTextField();
    private JLabel direccion = new JLabel("Dirección");
    private JTextField direccionCampo = new JTextField();
    private JLabel ciudad = new JLabel("ciudad");
    private JComboBox ciudadList = new JComboBox();
    private JButton enviar = new JButton("Enviar Cambios");
    private static final Font FUENTE_TITULO = new Font(null, Font.BOLD, 28);
    private static final Font FUENTE_ETIQUETAS = new Font(null, Font.BOLD, 22);
    private static final Color COLOR_FONDO = new Color(232,234,246);

    public ModificarCliente(){
        iniciarComponentes();
    }
    
    private void iniciarComponentes() {
        contenido.setLayout(null);
        contenido.setVisible(true);
        contenido.setBackground(COLOR_FONDO);

        //Titulo
        titulo.setFont(FUENTE_TITULO);
        titulo.setVisible(true);
        titulo.setBounds(550, 30, 300, 25);
        contenido.add(titulo);

        //Formulario
        tipoId.setFont(FUENTE_ETIQUETAS);
        tipoId.setVisible(true);
        tipoId.setBounds(150, 150, 200, 30);
        contenido.add(tipoId);
        tipoIdList.setVisible(true);
        tipoIdList.setBounds(330, 150, 200, 30);
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
        nombreCampo.setVisible(true);
        nombreCampo.setBounds(330, 225, 200, 30);
        contenido.add(nombreCampo);

        direccion.setFont(FUENTE_ETIQUETAS);
        direccion.setVisible(true);
        direccion.setBounds(150, 300, 150, 30);
        contenido.add(direccion);
        direccionCampo.setVisible(true);
        direccionCampo.setBounds(330, 300, 200, 30);
        contenido.add(direccionCampo);

        ciudad.setFont(FUENTE_ETIQUETAS);
        ciudad.setVisible(true);
        ciudad.setBounds(800, 225, 150, 30);
        contenido.add(ciudad);
        ciudadList.setVisible(true);
        ciudadList.setBounds(890, 225, 220, 30);
        contenido.add(ciudadList);
        
        enviar.setFont(FUENTE_ETIQUETAS);
        enviar.setVisible(true);
        enviar.setBounds(550, 450, 200, 30);
        contenido.add(enviar);

        this.add(contenido, BorderLayout.CENTER);
    }
}

