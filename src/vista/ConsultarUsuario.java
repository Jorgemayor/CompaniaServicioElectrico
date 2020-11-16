package src.vista;

import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ConsultarUsuario extends Container {
    
    private JPanel contenido = new JPanel();
    private JLabel titulo = new JLabel("Consultar Usuarios");
    private JLabel usuario = new JLabel("Nombre usuario");
    private JTextField usuarioCampo = new JTextField();
    private JButton buscar = new JButton("Buscar");
    private JButton mostrarTodos = new JButton("Mostrar Todos");
    private JTable datos = new JTable();
    private static final Font FUENTE_TITULO = new Font(null, Font.BOLD, 28);
    private static final Font FUENTE_ETIQUETAS = new Font(null, Font.BOLD, 22);
    private static final Color COLOR_FONDO = new Color(232,234,246);

    public ConsultarUsuario(){
        iniciarComponentes();
    }
    
    private void iniciarComponentes() {
        contenido.setLayout(null);
        contenido.setVisible(true);
        contenido.setBackground(COLOR_FONDO);

        //Titulo
        titulo.setFont(FUENTE_TITULO);
        titulo.setVisible(true);
        titulo.setBounds(550, 30, 400, 25);
        contenido.add(titulo);

        //Formulario
        usuario.setFont(FUENTE_ETIQUETAS);
        usuario.setVisible(true);
        usuario.setBounds(150, 100, 200, 30);
        contenido.add(usuario);
        usuarioCampo.setVisible(true);
        usuarioCampo.setBounds(330, 100, 200, 30);
        contenido.add(usuarioCampo);

        buscar.setFont(FUENTE_ETIQUETAS);
        buscar.setVisible(true);
        buscar.setBounds(540, 100, 130, 30);
        contenido.add(buscar);

        mostrarTodos.setFont(FUENTE_ETIQUETAS);
        mostrarTodos.setVisible(true);
        mostrarTodos.setBounds(900, 100, 200, 30);
        contenido.add(mostrarTodos);

        //Tabla
        datos.setVisible(true);
        datos.setBounds(150,150,950,390);
        contenido.add(datos);
        this.add(contenido, BorderLayout.CENTER);
    }  
}
