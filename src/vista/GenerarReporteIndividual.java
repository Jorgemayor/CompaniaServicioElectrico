package src.vista;

import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;
import javax.swing.JScrollPane;
import org.jfree.chart.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GenerarReporteIndividual extends Container {
 
    private static final Font FUENTE_TITULO = new Font(null, Font.BOLD, 28);
    private static final Font FUENTE_ETIQUETAS = new Font(null, Font.BOLD, 22);
    private static final Color COLOR_FONDO = new Color(232,234,246);

    private JPanel panelContenido;
    private JLabel etiquetaId;
    private JTextField campoId;
    private JButton botonGenerar;
    private JButton botonLimpiar;
    private JScrollPane reporte;
    private DefaultCategoryDataset datos;
    private JFreeChart Grafica;
    private JPanel panelDelPanel;
    private ChartPanel panel;
    
    public GenerarReporteIndividual(){
        iniciarComponentes();

    }

    private void iniciarComponentes() {

        datos = new DefaultCategoryDataset();
        Grafica = ChartFactory.createLineChart("CONSUMO MENSUAL","Días", "Visitas", datos,PlotOrientation.VERTICAL, true, true, false);
        panel = new ChartPanel(Grafica);
        panelDelPanel = new JPanel();
        panelDelPanel.add(panel);
        panelContenido = new JPanel();
        etiquetaId = new JLabel("ID Cliente");
        campoId = new JTextField();
        botonGenerar = new JButton("Generar");
        botonLimpiar = new JButton("Limpiar");
        reporte = new JScrollPane(panelDelPanel);

        panelContenido.setLayout(null);
        panelContenido.setVisible(true);
        panelContenido.setBackground(COLOR_FONDO);

        //Formulario
        etiquetaId.setFont(FUENTE_ETIQUETAS);
        etiquetaId.setVisible(true);
        etiquetaId.setBounds(150, 10, 200, 30);
        panelContenido.add(etiquetaId);
        campoId.setVisible(true);
        campoId.setBounds(300, 10, 200, 30);
        panelContenido.add(campoId);
        datos.addValue(100, "124563", "1/1/2020");
        datos.addValue(25, "124563", "1/5/2020");
        datos.addValue(36, "124563", "1/6/2020");
        botonGenerar.setFont(FUENTE_ETIQUETAS);
        botonGenerar.setVisible(true);
        botonGenerar.setBounds(750, 10, 150, 30);
        botonGenerar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                datos.removeValue("124563","1/1/2020");
                datos.removeValue("124563","1/5/2020");
                datos.removeValue("124563","1/6/2020");
            
            }

        });
        
        panelContenido.add(botonGenerar);

        botonLimpiar.setFont(FUENTE_ETIQUETAS);
        botonLimpiar.setVisible(true);
        botonLimpiar.setBounds(920, 10, 150, 30);
        panelContenido.add(botonLimpiar);

        //Contenedor Reporte
        reporte.setVisible(true);
        reporte.setBounds(0,50,1280,525);
        panelContenido.add(reporte);
        this.add(panelContenido, BorderLayout.CENTER);



        
    }  
    
}
