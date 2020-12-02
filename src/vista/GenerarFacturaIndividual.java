package src.vista;

import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class GenerarFacturaIndividual extends Container {

    private static final Font FUENTE_TITULO = new Font(null, Font.BOLD, 28);
    private static final Font FUENTE_ETIQUETAS = new Font(null, Font.BOLD, 22);
    private static final Color COLOR_FONDO = new Color(232, 234, 246);

    private JPanel contenido;
    private JLabel titulo;
    private JLabel documentoIdentidad;
    private JTextField campoDocumentoIdentidad;
    private JButton generar;
    private JButton ver;
    private JLabel facturasAnteriores;
    private JTable datos;

    public GenerarFacturaIndividual() {
        iniciarComponentes();
    }

    private void iniciarComponentes() {

        contenido = new JPanel();
        titulo = new JLabel("Generar Factura Individual");
        documentoIdentidad = new JLabel("Documento de Identidad");
        campoDocumentoIdentidad = new JTextField();
        generar = new JButton("Generar Factura Actual");
        ver = new JButton("Ver Facturas Anteriores");
        facturasAnteriores = new JLabel("Facturas Anteriores");
        datos = new JTable();

        contenido.setLayout(null);
        contenido.setVisible(true);
        contenido.setBackground(COLOR_FONDO);

        // Titulo
        titulo.setFont(FUENTE_TITULO);
        titulo.setVisible(true);
        titulo.setBounds(410, 30, 400, 25);
        contenido.add(titulo);

        // Formulario
        documentoIdentidad.setFont(FUENTE_ETIQUETAS);
        documentoIdentidad.setVisible(true);
        documentoIdentidad.setBounds(220, 100, 400, 30);
        contenido.add(documentoIdentidad);
        campoDocumentoIdentidad.setVisible(true);
        campoDocumentoIdentidad.setBounds(480, 100, 200, 30);
        campoDocumentoIdentidad.addKeyListener(new KeyListener() {

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
            
        });
        contenido.add(campoDocumentoIdentidad);


        generar.setFont(FUENTE_ETIQUETAS);
        generar.setVisible(true);
        generar.setBounds(750, 100, 300, 30);
        contenido.add(generar);

        ver.setFont(FUENTE_ETIQUETAS);
        ver.setVisible(true);
        ver.setBounds(750, 140, 300, 30);
        contenido.add(ver);

        //Tabla
        facturasAnteriores.setFont(FUENTE_ETIQUETAS);
        facturasAnteriores.setVisible(true);
        facturasAnteriores.setBounds(500, 200, 200,30);
        contenido.add(facturasAnteriores);
        datos.setVisible(true);
        datos.setBounds(150,230,950,310);
        contenido.add(datos);
        this.add(contenido, BorderLayout.CENTER);
    }  
}
