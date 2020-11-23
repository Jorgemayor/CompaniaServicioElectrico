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

public class ModificarUsuario extends Container {

    private static final Font FUENTE_TITULO = new Font(null, Font.BOLD, 28);
    private static final Font FUENTE_ETIQUETAS = new Font(null, Font.BOLD, 22);
    private static final Color COLOR_FONDO = new Color(232,234,246);

    private JPanel contenido;
    private JLabel titulo;
    private JLabel usuario;
    private JTextField usuarioCampo;
    private JButton ver;
    private JLabel contrasena;
    private JTextField contrasenaCampo;
    private JLabel rol;
    private JComboBox rolList;
    private JButton enviar;

    public ModificarUsuario(){
        iniciarComponentes();
    }
    
    private void iniciarComponentes() {

        contenido = new JPanel();
        titulo = new JLabel("Modificar Usuario");
        usuario = new JLabel("Usuario");
        usuarioCampo = new JTextField();
        ver = new JButton("ver");
        contrasena = new JLabel("Contraseña");
        contrasenaCampo = new JTextField();
        rol = new JLabel("Rol");
        rolList = new JComboBox();
        enviar = new JButton("Enviar Cambios");

        contenido.setLayout(null);
        contenido.setVisible(true);
        contenido.setBackground(COLOR_FONDO);

        //Titulo
        titulo.setFont(FUENTE_TITULO);
        titulo.setVisible(true);
        titulo.setBounds(510, 30, 300, 25);
        contenido.add(titulo);

        //Formulario
        usuario.setFont(FUENTE_ETIQUETAS);
        usuario.setVisible(true);
        usuario.setBounds(500, 150, 200, 30);
        contenido.add(usuario);
        usuarioCampo.setVisible(true);
        usuarioCampo.setBounds(600, 150, 200, 30);
        contenido.add(usuarioCampo);
        ver.setFont(FUENTE_ETIQUETAS);
        ver.setVisible(true);
        ver.setBounds(810, 150, 70, 30);
        contenido.add(ver);
        

        contrasena.setFont(FUENTE_ETIQUETAS);
        contrasena.setVisible(true);
        contrasena.setBounds(460, 250, 150, 30);
        contenido.add(contrasena);
        contrasenaCampo.setVisible(true);
        contrasenaCampo.setBounds(600, 250, 200, 30);
        contenido.add(contrasenaCampo);

        rol.setFont(FUENTE_ETIQUETAS);
        rol.setVisible(true);
        rol.setBounds(545, 350, 150, 30);
        contenido.add(rol);
        rolList.setVisible(true);
        rolList.setBounds(600, 350, 200, 30);
        contenido.add(rolList);
        
        enviar.setFont(FUENTE_ETIQUETAS);
        enviar.setVisible(true);
        enviar.setBounds(550, 450, 200, 30);
        contenido.add(enviar);

        this.add(contenido, BorderLayout.CENTER);
    }
}