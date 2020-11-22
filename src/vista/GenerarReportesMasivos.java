package src.vista;

import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class GenerarReportesMasivos extends Container {
 
    private static final Font FUENTE_TITULO = new Font(null, Font.BOLD, 28);
    private static final Font FUENTE_ETIQUETAS = new Font(null, Font.BOLD, 22);
    private static final Color COLOR_FONDO = new Color(232,234,246);

    private JPanel contenido;
    private JLabel titulo;
    private JPanel reporte;

    public GenerarReportesMasivos(){
        iniciarComponentes();
    }
    
    private void iniciarComponentes() {

        contenido = new JPanel();
        titulo = new JLabel("Reporte General de Clientes");
        reporte = new JPanel();

        contenido.setLayout(null);
        contenido.setVisible(true);
        contenido.setBackground(COLOR_FONDO);

        //Titulo
        titulo.setFont(FUENTE_TITULO);
        titulo.setVisible(true);
        titulo.setBounds(450, 30, 400, 25);
        contenido.add(titulo);

        

        //Contenedor Reporte
        reporte.setVisible(true);
        reporte.setBounds(150,100,950,450);
        contenido.add(reporte);
        this.add(contenido, BorderLayout.CENTER);
    }  
}
