package src.vista;

import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.awt.Color;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import org.sebastian.utils.JSaveAs;

import src.managers.gestionFactura.GestionFacturaApi;
import src.managers.gestionLectura.GestionLecturaApi;
import src.managers.*;
import src.managers.gestionClientes.GestionClienteApi;



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
                if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
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
        generar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JSONObject resultado = new JSONObject(GestionFacturaApi.obtenerFacturasPorCliente(campoDocumentoIdentidad.getText()));
                if(resultado.getString("code").equals("0")){
                    JSONObject facturas = new JSONObject(resultado.getString("facturas"));
                    JSONArray valor = facturas.getJSONArray("valor");
                     
                }
                else{
                    JOptionPane.showMessageDialog(null, resultado.getString("mensaje"));
                }

                JSONObject resultado2 = new JSONObject(GestionClienteApi.obtenerClientePorId(campoDocumentoIdentidad.getText()));
                if(resultado.getString("code").equals("0")){
                    JSONObject cliente = new JSONObject(resultado.getString("cliente"));
                    JSONArray nombre = cliente.getJSONArray("nombre");
                     
                }
                else{
                    JOptionPane.showMessageDialog(null, resultado.getString("mensaje"));
                }
                JSONObject resultado3 = new JSONObject(GestionLecturaApi.obtenerLecturasPorCliente(campoDocumentoIdentidad.getText()));
                if(resultado.getString("code").equals("0")){
                    JSONObject lecturas = new JSONObject(resultado.getString("lecturas"));
                    JSONArray consumo = lecturas.getJSONArray("consumo");
                     
                }
                else{
                    JOptionPane.showMessageDialog(null, resultado.getString("mensaje"));
                }

                


                DateTimeFormatter formatoLargo = DateTimeFormatter.ofPattern("dd' de 'MMMM' de 'YYYY' 'hh':'mm' 'a", new Locale("es", "CO"));
                DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd' 'MMMM' 'YY", new Locale("es", "CO"));
                LocalDateTime hoy = LocalDateTime.now();
                
                File ruta = new File("");
                ruta = JSaveAs.show(("Factura " + hoy.format(formatoFecha).toUpperCase() + ".pdf"));

                String[] encabezadoTabla = {"CONCEPTO","Cantidad", "Valor Unitario", "Valor total"};
                String[][] datosTabla = {{"Consumo de energia activa","80", "566", "30403"},
                {"Consumo de basico hasta 173","173", "566", "37403"},
                {"Consumo de mayor al basico","66", "563", "31455"},
                {"Interés por mora","0", "0", "0"}};

                int[] anchoColumnas = new int[]{60, 30, 30, 30};

                Document pdf = new Document();

                try {
                    PdfWriter.getInstance(pdf, new FileOutputStream(ruta));

                    Paragraph titulo = new Paragraph("FACTURA DE CONSUMO ELECTRICO - ELECTRICARIBE", FontFactory.getFont("Arial", 16));
                    titulo.setSpacingAfter(5);
                    titulo.setAlignment(1);

                    Paragraph fecha = new Paragraph("Periodo de facturación: 01/SEPT/20 - 01/NOV/20", FontFactory.getFont("Arial", 12));
                    fecha.setSpacingAfter(15);
                    fecha.setAlignment(1);

                    String numFactura = hoy.format(DateTimeFormatter.ofPattern("ssddYYmm"));
                    Paragraph numeroFact = new Paragraph("No. de Factura: "+numFactura+" Fecha de generación: " + hoy.format(formatoFecha), FontFactory.getFont("Arial", 12));
                    numeroFact.setSpacingAfter(15);
                    numeroFact.setAlignment(1);

                    com.itextpdf.text.Font font = FontFactory.getFont("Arial", 10);

                    PdfPTable pdfTabla = new PdfPTable(datosTabla[0].length);
                    for (int j = 0; j < encabezadoTabla.length; j++) {
                        Paragraph par = new Paragraph(encabezadoTabla[j], font);
                        pdfTabla.addCell(par);
                    }

                    int totalAPagar = 0;
                    for (int i = 0; i < datosTabla.length; i++) {
                        for (int j = 0; j < encabezadoTabla.length; j++) {
                            Paragraph par = new Paragraph(datosTabla[i][j], font);
                            pdfTabla.addCell(par);
                        }
                        totalAPagar += Integer.parseInt(datosTabla[i][3]);
                    }
                        pdfTabla.addCell(new Paragraph("", font));
                        pdfTabla.addCell(new Paragraph("", font));
                        pdfTabla.addCell(new Paragraph("TOTAL A PAGAR:", font));
                        pdfTabla.addCell(new Paragraph("" + totalAPagar, font));

                    pdf.open();
                    pdf.add(titulo);
                    pdf.add(fecha);
                    pdf.add(numeroFact);
                    pdf.add(pdfTabla);
                    pdf.close();
                } catch (DocumentException ex) {
                    JOptionPane.showMessageDialog(null, "El documento no pudo generarse\n" + ex.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    System.out.print(ex.getMessage());
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "No se pudo acceder a la ruta\n" + ex.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }


                    }
                }); 
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
