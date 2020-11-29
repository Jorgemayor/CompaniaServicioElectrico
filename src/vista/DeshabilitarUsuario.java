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

import src.managers.gestionUsuario.GestionUsuarioApi;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DeshabilitarUsuario extends Container {

    private static final Font FUENTE_TITULO = new Font(null, Font.BOLD, 28);
    private static final Font FUENTE_ETIQUETAS = new Font(null, Font.BOLD, 22);
    private static final Color COLOR_FONDO = new Color(232, 234, 246);

    private JPanel contenido;
    private JLabel titulo;
    private JLabel usuario;
    private JTextField nombreCampo;
    private JButton ver;
    private JLabel estado;
    private JLabel elEstado;
    private JToggleButton botonCambio;
    private int idUsuarioActual;

    public DeshabilitarUsuario() {
        iniciarComponentes();
    }

    private void iniciarComponentes() {

        contenido = new JPanel();
        titulo = new JLabel("Deshabilitar/Habilitar Usuario");
        usuario = new JLabel("Usuario");
        nombreCampo = new JTextField();
        ver = new JButton("ver");
        estado = new JLabel("Estado: ");
        elEstado = new JLabel();
        botonCambio = new JToggleButton("Habilitar/Deshabilitar");

        contenido.setLayout(null);
        contenido.setVisible(true);
        contenido.setBackground(COLOR_FONDO);

        // Titulo
        titulo.setFont(FUENTE_TITULO);
        titulo.setVisible(true);
        titulo.setBounds(390, 50, 600, 50);
        contenido.add(titulo);

        // Formulario
        usuario.setFont(FUENTE_ETIQUETAS);
        usuario.setVisible(true);
        usuario.setBounds(500, 150, 200, 30);
        contenido.add(usuario);
        nombreCampo.setVisible(true);
        nombreCampo.setBounds(600, 150, 200, 30);
        contenido.add(nombreCampo);
        ver.setFont(FUENTE_ETIQUETAS);
        ver.setVisible(true);
        ver.setBounds(810, 150, 70, 30);
        ver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JSONObject resultado = new JSONObject(GestionUsuarioApi.buscarEnTodosLosUsuarios(nombreCampo.getText()));
                if(resultado.getString("code").equals("0")){
                    JSONObject usuario = new JSONObject(resultado.getString("usuario"));
                    int id = usuario.getJSONArray("id").getInt(0);
                    String nombre = usuario.getJSONArray("nombre").getString(0);
                    String stringEstado="";
                    if(usuario.getJSONArray("habilitado").getBoolean(0)){
                        stringEstado = "Habilitado";
                        botonCambio.setText("Deshabilitar");
                    }
                    else{
                        stringEstado= "Deshabilitado";
                        botonCambio.setText("Habilitar");
                    }
                    idUsuarioActual = id;
                    estado.setText("Estado: "+stringEstado);
                    titulo.setText(titulo.getText()+": "+nombre);
                }
                else{
                    JOptionPane.showMessageDialog(null, resultado.getString("mensaje"));
                }
            }
            
        });
        contenido.add(ver);
        

        estado.setFont(FUENTE_ETIQUETAS);
        estado.setVisible(true);
        estado.setBounds(500, 250, 400, 30);
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
                GestionUsuarioApi.cambiarEstadoUsuario(idUsuarioActual);
                if(botonCambio.getText()=="Deshabilitar"){
                    botonCambio.setText("Habilitar");
                    estado.setText("Estado: "+"Deshabilitado");
                }
                else{
                    botonCambio.setText("Deshabilitar");
                    estado.setText("Estado: "+"Habilitado");
                }
            }
            
        });
        contenido.add(botonCambio);

        this.add(contenido, BorderLayout.CENTER);
    }
}