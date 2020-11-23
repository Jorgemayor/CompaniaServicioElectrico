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

public class GenerarReporteIndividual extends Container {
 
    private static final Font FUENTE_TITULO = new Font(null, Font.BOLD, 28);
    private static final Font FUENTE_ETIQUETAS = new Font(null, Font.BOLD, 22);
    private static final Color COLOR_FONDO = new Color(232,234,246);

    private JPanel contenido;
    private JLabel titulo;
    private JLabel id;
    private JComboBox tipoId;
    private JTextField campoId;
    private JButton generar;
    private JPanel reporte;

    public GenerarReporteIndividual(){
        iniciarComponentes();
    }
    
    private void iniciarComponentes() {

        contenido = new JPanel();
        titulo = new JLabel("Generar Reporte Individual");
        id = new JLabel("ID Cliente");
        tipoId = new JComboBox();
        campoId = new JTextField();
        generar = new JButton("Generar Reporte de Cliente");
        reporte = new JPanel();

        contenido.setLayout(null);
        contenido.setVisible(true);
        contenido.setBackground(COLOR_FONDO);

        //Titulo
        titulo.setFont(FUENTE_TITULO);
        titulo.setVisible(true);
        titulo.setBounds(400, 30, 400, 25);
        contenido.add(titulo);

        //Formulario
        id.setFont(FUENTE_ETIQUETAS);
        id.setVisible(true);
        id.setBounds(150, 100, 200, 30);
        contenido.add(id);
        tipoId.setVisible(true);
        tipoId.setBounds(260, 100, 200, 30);
        contenido.add(tipoId);
        campoId.setVisible(true);
        campoId.setBounds(480, 100, 200, 30);
        contenido.add(campoId);

        generar.setFont(FUENTE_ETIQUETAS);
        generar.setVisible(true);
        generar.setBounds(750, 100, 350, 30);
        contenido.add(generar);

        //Contenedor Reporte
        reporte.setVisible(true);
        reporte.setBounds(150,150,950,400);
        contenido.add(reporte);
        this.add(contenido, BorderLayout.CENTER);
    }  
}
