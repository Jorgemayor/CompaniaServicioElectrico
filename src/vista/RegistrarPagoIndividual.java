package src.vista;

import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RegistrarPagoIndividual extends Container {
 
    private static final Font FUENTE_TITULO = new Font(null, Font.BOLD, 28);
    private static final Font FUENTE_ETIQUETAS = new Font(null, Font.BOLD, 22);
    private static final Color COLOR_FONDO = new Color(232,234,246);

    private JPanel contenido;
    private JLabel titulo;
    private JLabel idCliente;
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
        idCliente = new JLabel("Identificación del Cliente");
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

        KeyListener validacionNumerica=new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) ||
                (c == KeyEvent.VK_BACK_SPACE) ||
                (c == KeyEvent.VK_DELETE))) {
                    getToolkit().beep();
                    e.consume();
                    JOptionPane.showMessageDialog(null, "En este campo solo se admiten valores numéricos");
                }

            }

            @Override
            public void keyPressed(KeyEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void keyReleased(KeyEvent e) {
                // TODO Auto-generated method stub

            }
         };

        //Formulario
        idCliente.setFont(FUENTE_ETIQUETAS);
        idCliente.setVisible(true);
        idCliente.setBounds(400, 100, 300, 30);
        contenido.add(idCliente);
        campoIdCliente.setVisible(true);
        campoIdCliente.setBounds(680, 100, 200, 30);
        campoIdCliente.addKeyListener(validacionNumerica);
        contenido.add(campoIdCliente);
        idFactura.setFont(FUENTE_ETIQUETAS);
        idFactura.setVisible(true);
        idFactura.setBounds(480, 140, 200, 30);
        contenido.add(idFactura);
        campoFactura.setVisible(true);
        campoFactura.setBounds(680, 140, 200, 30);
        campoFactura.addKeyListener(validacionNumerica);
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
