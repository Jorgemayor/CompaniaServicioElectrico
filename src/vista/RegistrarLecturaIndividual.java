package src.vista;

import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import org.json.JSONObject;

import src.managers.gestionLectura.GestionLecturaApi;

public class RegistrarLecturaIndividual extends Container {

    private static final Font FUENTE_TITULO = new Font(null, Font.BOLD, 28);
    private static final Font FUENTE_ETIQUETAS = new Font(null, Font.BOLD, 22);
    private static final Color COLOR_FONDO = new Color(232, 234, 246);

    private JPanel contenido;
    private JLabel titulo;
    private JLabel etiquetaFecha;
    private JDateChooser selectorFecha;
    private JLabel etiquetaID;
    private JTextField campoID;
    private JLabel etiquetaConsumo;
    private JTextField campoConsumo;

    private JButton registrar;

    public RegistrarLecturaIndividual() {
        iniciarComponentes();
    }

    private void iniciarComponentes() {

        contenido = new JPanel();
        titulo = new JLabel("Registrar Lectura Individual");
        etiquetaFecha = new JLabel("Fecha de Realización");
        selectorFecha = new JDateChooser();
        etiquetaID = new JLabel("Identificación del Cliente");
        campoID = new JTextField();
        etiquetaConsumo = new JLabel("Consumo en KWH");
        campoConsumo = new JTextField();
        registrar = new JButton("Registrar Lectura");

        contenido.setLayout(null);
        contenido.setVisible(true);
        contenido.setBackground(COLOR_FONDO);

        // Titulo
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

        // Formulario
        etiquetaFecha.setFont(FUENTE_ETIQUETAS);
        etiquetaFecha.setVisible(true);
        etiquetaFecha.setBounds(445, 100, 300, 30);
        contenido.add(etiquetaFecha);
        selectorFecha.setVisible(true);
        selectorFecha.setBounds(680, 100, 200, 30);
        selectorFecha.setVisible(true);
        contenido.add(selectorFecha);

        etiquetaID.setFont(FUENTE_ETIQUETAS);
        etiquetaID.setVisible(true);
        etiquetaID.setBounds(420, 200, 300, 30);
        contenido.add(etiquetaID);
        campoID.setVisible(true);
        campoID.setBounds(680, 200, 200, 30);
        campoID.addKeyListener(validacionNumerica);
        contenido.add(campoID);

        etiquetaConsumo.setFont(FUENTE_ETIQUETAS);
        etiquetaConsumo.setVisible(true);
        etiquetaConsumo.setBounds(445, 300, 300, 30);
        contenido.add(etiquetaConsumo);
        campoConsumo.setVisible(true);
        campoConsumo.setBounds(680, 300, 200, 30);
        campoConsumo.addKeyListener(validacionNumerica);
        contenido.add(campoConsumo);

        registrar.setFont(FUENTE_ETIQUETAS);
        registrar.setVisible(true);
        registrar.setBounds(500, 400, 250, 30);
        registrar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Date fecha = selectorFecha.getDate();
                java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime()); 
                String resultado =  GestionLecturaApi.ingresarLectura(campoID.getText(), fechaSQL, campoConsumo.getText());
                JSONObject jsonResultado = new JSONObject(resultado);
                String codigo = jsonResultado.getString("code");
                if (codigo.equals("0")) {
                    JOptionPane.showMessageDialog(null, "Lectura Registrada");
                } else {
                    String mensaje = jsonResultado.getString("mensaje");
                    JOptionPane.showMessageDialog(null, mensaje);
                }
            }
            
        });
        contenido.add(registrar);

        this.add(contenido, BorderLayout.CENTER);
    }  
}
