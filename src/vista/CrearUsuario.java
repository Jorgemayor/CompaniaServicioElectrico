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

public class CrearUsuario extends Container {

    private JPanel contenido = new JPanel();
    private JLabel titulo = new JLabel("Crear Usuario");
    private JLabel usuario = new JLabel("Usuario");
    private JTextField usuarioCampo = new JTextField();
    private JLabel contrasena = new JLabel("Contraseña");
    private JTextField contrasenaCampo = new JTextField();
    private JLabel rol = new JLabel("Rol");
    private JComboBox rolList = new JComboBox();
    private JButton enviar = new JButton("Enviar");
    private static final Font FUENTE_TITULO = new Font(null, Font.BOLD, 28);
    private static final Font FUENTE_ETIQUETAS = new Font(null, Font.BOLD, 22);
    private static final Color COLOR_FONDO = new Color(232,234,246);

    public CrearUsuario(){
        iniciarComponentes();
    }
    
    private void iniciarComponentes() {
        contenido.setLayout(null);
        contenido.setVisible(true);
        contenido.setBackground(COLOR_FONDO);

        //Titulo
        titulo.setFont(FUENTE_TITULO);
        titulo.setVisible(true);
        titulo.setBounds(550, 30, 200, 25);
        contenido.add(titulo);

        //Formulario
        usuario.setFont(FUENTE_ETIQUETAS);
        usuario.setVisible(true);
        usuario.setBounds(500, 150, 200, 30);
        contenido.add(usuario);
        usuarioCampo.setVisible(true);
        usuarioCampo.setBounds(600, 150, 200, 30);
        contenido.add(usuarioCampo);

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