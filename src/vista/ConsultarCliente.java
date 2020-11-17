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

public class ConsultarCliente extends Container {

    private static final Font FUENTE_TITULO = new Font(null, Font.BOLD, 28);
    private static final Font FUENTE_ETIQUETAS = new Font(null, Font.BOLD, 22);
    private static final Color COLOR_FONDO = new Color(232,234,246);

    private JPanel contenido;
    private JLabel titulo;
    private JLabel identificacion;
    private JTextField identificacionCampo;
    private JButton buscar;
    private JButton mostrarTodos;
    private JTable datos;

    public ConsultarCliente(){
        iniciarComponentes();
    }
    
    private void iniciarComponentes() {

        contenido = new JPanel();
        titulo = new JLabel("Consultar Clientes");
        identificacion = new JLabel("Identificación");
        identificacionCampo = new JTextField();
        buscar = new JButton("Buscar");
        mostrarTodos = new JButton("Mostrar Todos");
        datos = new JTable();

        contenido.setLayout(null);
        contenido.setVisible(true);
        contenido.setBackground(COLOR_FONDO);

        //Titulo
        titulo.setFont(FUENTE_TITULO);
        titulo.setVisible(true);
        titulo.setBounds(550, 30, 400, 25);
        contenido.add(titulo);

        //Formulario
        identificacion.setFont(FUENTE_ETIQUETAS);
        identificacion.setVisible(true);
        identificacion.setBounds(150, 100, 200, 30);
        contenido.add(identificacion);
        identificacionCampo.setVisible(true);
        identificacionCampo.setBounds(330, 100, 200, 30);
        contenido.add(identificacionCampo);

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