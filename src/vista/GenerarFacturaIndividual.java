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

public class GenerarFacturaIndividual extends Container {
 
    private static final Font FUENTE_TITULO = new Font(null, Font.BOLD, 28);
    private static final Font FUENTE_ETIQUETAS = new Font(null, Font.BOLD, 22);
    private static final Color COLOR_FONDO = new Color(232,234,246);

    private JPanel contenido;
    private JLabel titulo;
    private JLabel id;
    private JComboBox tipoId;
    private JTextField campoId;
    private JLabel consumo;
    private JTextField campoConsumo;
    private JButton buscar;
    private JButton generar;
    private JLabel facturasAnteriores;
    private JTable datos;

    public GenerarFacturaIndividual(){
        iniciarComponentes();
    }
    
    private void iniciarComponentes() {

        contenido = new JPanel();
        titulo = new JLabel("Generar Factura Individual");
        id = new JLabel("ID Cliente");
        tipoId = new JComboBox();
        campoId = new JTextField();
        consumo = new JLabel("Consumo (KWH)");
        campoConsumo = new JTextField();
        buscar = new JButton("Buscar");
        generar = new JButton("Generar Factura Actual");
        facturasAnteriores = new JLabel("Facturas Anteriores");
        datos = new JTable();

        contenido.setLayout(null);
        contenido.setVisible(true);
        contenido.setBackground(COLOR_FONDO);

        //Titulo
        titulo.setFont(FUENTE_TITULO);
        titulo.setVisible(true);
        titulo.setBounds(410, 30, 400, 25);
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
        consumo.setFont(FUENTE_ETIQUETAS);
        consumo.setVisible(true);
        consumo.setBounds(280, 140, 200, 30);
        contenido.add(consumo);
        campoConsumo.setVisible(true);
        campoConsumo.setBounds(480, 140, 200, 30);
        contenido.add(campoConsumo);

        buscar.setFont(FUENTE_ETIQUETAS);
        buscar.setVisible(true);
        buscar.setBounds(540, 100, 130, 30);
        contenido.add(buscar);

        generar.setFont(FUENTE_ETIQUETAS);
        generar.setVisible(true);
        generar.setBounds(750, 120, 300, 30);
        contenido.add(generar);

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
