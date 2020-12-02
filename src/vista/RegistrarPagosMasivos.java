package src.vista;

import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RegistrarPagosMasivos extends Container {

    private static final Font FUENTE_TITULO = new Font(null, Font.BOLD, 28);
    private static final Font FUENTE_ETIQUETAS = new Font(null, Font.BOLD, 22);
    private static final Color COLOR_FONDO = new Color(232, 234, 246);

    private JPanel contenido;
    private JLabel titulo;
    private JTextField pagos;
    private JButton seleccionar;
    private JButton registrar;
    private JFileChooser archivoPagos;

    public RegistrarPagosMasivos() {
        iniciarComponentes();
    }

    private void iniciarComponentes() {

        contenido = new JPanel();
        titulo = new JLabel("Registrar Pagos");
        pagos = new JTextField("Archivo de Pagos");
        seleccionar = new JButton("Seleccionar");
        registrar = new JButton("Registrar");
        archivoPagos = new JFileChooser();
        contenido.setLayout(null);
        contenido.setVisible(true);
        contenido.setOpaque(true);
        contenido.setBackground(COLOR_FONDO);

        // Titulo
        titulo.setFont(FUENTE_TITULO);
        titulo.setVisible(true);
        titulo.setBounds(500, 30, 400, 35);
        contenido.add(titulo);

        // Formulario
        pagos.setVisible(true);
        pagos.setBounds(370, 150, 250, 30);
        contenido.add(pagos);
        seleccionar.setFont(FUENTE_ETIQUETAS);
        seleccionar.setVisible(true);
        seleccionar.setBounds(630, 150, 200, 30);
        seleccionar.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                archivoPagos.showOpenDialog(null);
                pagos.setText(archivoPagos.getSelectedFile().getAbsolutePath());
            }
            
        });
        contenido.add(seleccionar);
        
        registrar.setFont(FUENTE_ETIQUETAS);
        registrar.setVisible(true);
        registrar.setBounds(550, 450, 200, 30);
        contenido.add(registrar);

        this.add(contenido, BorderLayout.CENTER);
    }        
}