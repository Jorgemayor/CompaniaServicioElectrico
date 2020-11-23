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

import org.json.JSONObject;

import src.managers.gestionUsuario.GestionUsuarioApi;

public class CrearUsuario extends Container {

    private static final Font FUENTE_TITULO = new Font(null, Font.BOLD, 28);
    private static final Font FUENTE_ETIQUETAS = new Font(null, Font.BOLD, 22);
    private static final Color COLOR_FONDO = new Color(232, 234, 246);

    private JPanel contenedor;
    private JLabel etiquetaTitulo;
    private JLabel etiquetaUsuario;
    private JTextField campoUsuario;
    private JLabel etiquetaContrasena;
    private JTextField campoContrasena;
    private JLabel etiquetaRol;
    private JComboBox<String> selectorRol;
    private JButton botonEnviar;

    public CrearUsuario() {
        iniciarComponentes();
    }

    private void iniciarComponentes() {

        contenedor = new JPanel();
        etiquetaTitulo = new JLabel("Crear Usuario");
        etiquetaUsuario = new JLabel("Usuario");
        campoUsuario = new JTextField();
        etiquetaContrasena = new JLabel("Contraseña");
        campoContrasena = new JTextField();
        etiquetaRol = new JLabel("Rol");
        selectorRol = new JComboBox<String>();
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

        // Nombre de usuario
        etiquetaUsuario.setFont(FUENTE_ETIQUETAS);
        etiquetaUsuario.setVisible(true);
        etiquetaUsuario.setBounds(500, 150, 200, 30);
        contenedor.add(etiquetaUsuario);

        campoUsuario.setVisible(true);
        campoUsuario.setBounds(600, 150, 200, 30);
        contenedor.add(campoUsuario);

        // Contrasena
        etiquetaContrasena.setFont(FUENTE_ETIQUETAS);
        etiquetaContrasena.setVisible(true);
        etiquetaContrasena.setBounds(460, 250, 150, 30);
        contenedor.add(etiquetaContrasena);

        campoContrasena.setVisible(true);
        campoContrasena.setBounds(600, 250, 200, 30);
        contenedor.add(campoContrasena);

        // Rol
        etiquetaRol.setFont(FUENTE_ETIQUETAS);
        etiquetaRol.setVisible(true);
        etiquetaRol.setBounds(545, 350, 150, 30);
        contenedor.add(etiquetaRol);

        selectorRol.setVisible(true);
        selectorRol.setBounds(600, 350, 200, 30);
        selectorRol.addItem("Administrador");
        selectorRol.addItem("Gerente");
        selectorRol.addItem("Operador");
        contenedor.add(selectorRol);

        botonEnviar.setFont(FUENTE_ETIQUETAS);
        botonEnviar.setVisible(true);
        botonEnviar.setBounds(550, 450, 200, 30);
        botonEnviar.addActionListener(new ActionListener() {
           @Override
            public void actionPerformed(ActionEvent event) {

                String nombreUsuario = campoUsuario.getText();
                String contrasena = campoContrasena.getText();
                String rol = (String)selectorRol.getSelectedItem();
                int idRol = 0;
                
                if(rol.equals("Administrador"))
                    idRol = 1;
                else if(rol.equals("Gerente"))
                    idRol = 2;
                else if(rol.equals("Operador"))
                    idRol = 3;

                String resultado = GestionUsuarioApi.crearUsuario(nombreUsuario, contrasena, idRol);
                JSONObject jsonResultado = new JSONObject(resultado);
                String codigo = jsonResultado.getString("code");

                if (codigo.equals("0")) {
                    JOptionPane.showMessageDialog(null, "Usuario Creado");
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