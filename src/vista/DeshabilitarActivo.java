package src.vista;

import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JToggleButton;

import org.json.JSONObject;

import src.managers.gestionActivos.GestionActivosApi;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DeshabilitarActivo extends Container {

    private static final Font FUENTE_TITULO = new Font(null, Font.BOLD, 28);
    private static final Font FUENTE_ETIQUETAS = new Font(null, Font.BOLD, 22);
    private static final Color COLOR_FONDO = new Color(232, 234, 246);

    private JPanel contenido;
    private JLabel titulo;
    private JLabel serial;
    private JTextField serialCampo;
    private JButton ver;
    private JLabel estado;
    private JLabel elEstado;
    private JToggleButton botonCambio;
    private int idActivoActual;

    public DeshabilitarActivo() {
        iniciarComponentes();
    }

    private void iniciarComponentes() {

        contenido = new JPanel();
        titulo = new JLabel("Deshabilitar/Habilitar Activo");
        serial = new JLabel("Serial");
        serialCampo = new JTextField();
        ver = new JButton("ver");
        estado = new JLabel("Estado:");
        elEstado = new JLabel();
        botonCambio = new JToggleButton("Habilitar/Deshabilitar");
        idActivoActual = -1;

        contenido.setLayout(null);
        contenido.setVisible(true);
        contenido.setBackground(COLOR_FONDO);

        // Titulo
        titulo.setFont(FUENTE_TITULO);
        titulo.setVisible(true);
        titulo.setBounds(390, 50, 600, 50);
        contenido.add(titulo);

        // Formulario
        serial.setFont(FUENTE_ETIQUETAS);
        serial.setVisible(true);
        serial.setBounds(500, 150, 200, 30);
        contenido.add(serial);
        serialCampo.setVisible(true);
        serialCampo.setBounds(600, 150, 200, 30);
        contenido.add(serialCampo);
        ver.setFont(FUENTE_ETIQUETAS);
        ver.setVisible(true);
        ver.setBounds(810, 150, 70, 30);
        ver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JSONObject resultado = new JSONObject(GestionActivosApi.buscarEnTodosLosActivos(serialCampo.getText()));
                JSONObject activo = new JSONObject(resultado.getString("activo"));
                int id = activo.getJSONArray("id").getInt(0);
                String nombre = activo.getJSONArray("nombre").getString(0);
                String stringEstado="";
                if(activo.getJSONArray("habilitado").getBoolean(0)){
                    stringEstado = "Habilitado";
                    botonCambio.setText("Deshabilitar");
                }
                else{
                    stringEstado= "Deshabilitado";
                    botonCambio.setText("Habilitar");
                }
                idActivoActual = id;
                estado.setText("Estado: "+stringEstado);
                titulo.setText(titulo.getText()+": "+nombre);
            }
        });
        contenido.add(ver);
        

        estado.setFont(FUENTE_ETIQUETAS);
        estado.setVisible(true);
        estado.setBounds(500, 250, 300, 30);
        contenido.add(estado);
        elEstado.setFont(FUENTE_ETIQUETAS);
        elEstado.setVisible(true);
        elEstado.setBounds(600, 250, 200, 30);
        contenido.add(elEstado);

        botonCambio.setFont(FUENTE_ETIQUETAS);
        botonCambio.setVisible(true);
        botonCambio.setBounds(510, 350, 300, 30);
        botonCambio.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                String resultado = GestionActivosApi.cambiarEstadoActivo(idActivoActual);
                JSONObject jsonResultado = new JSONObject(resultado);
                String codigo = jsonResultado.getString("code");
                if (codigo.equals("0")) {
                    if(botonCambio.getText()=="Deshabilitar"){
                        botonCambio.setText("Habilitar");
                        estado.setText("Estado: "+"Deshabilitado");
                    }
                    else{
                        botonCambio.setText("Deshabilitar");
                        estado.setText("Estado: "+"Habilitado");
                    }
                } else {
                    String mensaje = jsonResultado.getString("mensaje");
                    JOptionPane.showMessageDialog(null, mensaje);
                }
            }
            
        });
        contenido.add(botonCambio);
        

        this.add(contenido, BorderLayout.CENTER);
    }
}