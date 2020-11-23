package src.vista;

import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class RegistrarPagoIndividual extends Container {
 
    private static final Font FUENTE_TITULO = new Font(null, Font.BOLD, 28);
    private static final Font FUENTE_ETIQUETAS = new Font(null, Font.BOLD, 22);
    private static final Color COLOR_FONDO = new Color(232,234,246);

    private JPanel contenido;
    private JLabel titulo;
    private JLabel idCliente;
    private JComboBox tipoIdCliente;
    private JTextField campoIdCliente;
    private JLabel idFactura;
    private JTextField campoFactura;
    private JButton ver;
    private JButton registrar;
    private JLabel valor;


    public RegistrarPagoIndividual(){
        iniciarComponentes();
    }
    
    private void iniciarComponentes() {

        contenido = new JPanel();
        titulo = new JLabel("Registrar Pago Individual");
        idCliente = new JLabel("ID Cliente");
        tipoIdCliente = new JComboBox();
        campoIdCliente = new JTextField();
        idFactura = new JLabel("Número Factura");
        campoFactura = new JTextField();
        ver = new JButton("Ver Valor");
        registrar = new JButton("Registrar Pago");
        valor = new JLabel("##############");

        contenido.setLayout(null);
        contenido.setVisible(true);
        contenido.setBackground(COLOR_FONDO);

        //Titulo
        titulo.setFont(FUENTE_TITULO);
        titulo.setVisible(true);
        titulo.setBounds(450, 30, 400, 35);
        contenido.add(titulo);

        //Formulario
        idCliente.setFont(FUENTE_ETIQUETAS);
        idCliente.setVisible(true);
        idCliente.setBounds(350, 100, 200, 30);
        contenido.add(idCliente);
        tipoIdCliente.setVisible(true);
        tipoIdCliente.setBounds(460, 100, 200, 30);
        contenido.add(tipoIdCliente);
        campoIdCliente.setVisible(true);
        campoIdCliente.setBounds(680, 100, 200, 30);
        contenido.add(campoIdCliente);
        idFactura.setFont(FUENTE_ETIQUETAS);
        idFactura.setVisible(true);
        idFactura.setBounds(480, 140, 200, 30);
        contenido.add(idFactura);
        campoFactura.setVisible(true);
        campoFactura.setBounds(680, 140, 200, 30);
        contenido.add(campoFactura);

        ver.setFont(FUENTE_ETIQUETAS);
        ver.setVisible(true);
        ver.setBounds(550, 200, 200,30);
        contenido.add(ver);

        valor.setFont(FUENTE_ETIQUETAS);
        valor.setVisible(true);
        valor.setBounds(580, 250, 200,30);
        contenido.add(valor);

        registrar.setFont(FUENTE_ETIQUETAS);
        registrar.setVisible(true);
        registrar.setBounds(500, 400, 250, 30);
        contenido.add(registrar);

        this.add(contenido, BorderLayout.CENTER);
    }  
}
