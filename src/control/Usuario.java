package src.control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.swing.JOptionPane;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.sebastian.utils.JSaveAs;

public class Usuario {

    private String usuario;
    private int id;
    private int idRol;

    public int getId(){
        return id;
    }

    public int getRol(){
        return idRol;
    }

    public String getNombreUsuario(){
        return usuario;
    }
    
    public Usuario(int id, String usuario, int idRol){
        this.usuario = usuario;
        this.id = id;
        this.idRol = idRol;
    }

    public void generarExcel() {
        DateTimeFormatter formatoLargo = DateTimeFormatter.ofPattern("dd' de 'MMMM' de 'YYYY' 'hh':'mm' 'a", new Locale("es", "CO"));
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd' 'MMMM' 'YY", new Locale("es", "CO"));
        LocalDateTime fecha = LocalDateTime.now();

        File ruta = new File("");
        ruta = JSaveAs.show(("Factura" + fecha.format(formatoFecha).toUpperCase() + ".xlsx"));

        String[] encabezadoTabla = {"CONCEPTO","Cantidad", "Valor Unitario", "Valor total"};
        String[][] datosTabla = {{"Consumo de energia activa","80", "566", "30403"},
        {"Consumo de basico hasta 173","173", "566", "37403"},
        {"Consumo de mayor al basico","66", "563", "31455"},
        {"Interés por mora","0", "0", "0"}};

        int[] anchoColumnas = new int[]{60, 30, 30, 30};

        String numFactura = fecha.format(DateTimeFormatter.ofPattern("dd'YY''mm'"));

        Workbook excel = new XSSFWorkbook();
        Sheet hoja = excel.createSheet(fecha.format(formatoFecha).toUpperCase());

        CellStyle encabezado;
        Font headerFont = excel.createFont();
        encabezado = excel.createCellStyle();
        headerFont.setBold(true);
        encabezado.setAlignment(HorizontalAlignment.CENTER);
        encabezado.setFont(headerFont);

        Row filaEmpresa = hoja.createRow(0);
        hoja.addMergedRegion(new CellRangeAddress(0, 0, 0, encabezadoTabla.length - 1));
        Cell celdaEmpresa = filaEmpresa.createCell(0);
        celdaEmpresa.setCellValue("FACTURA DE CONSUMO ELECTRICO - ELECTRICARIBE");
        celdaEmpresa.setCellStyle(encabezado);

        Row filaTitulo = hoja.createRow(1);
        hoja.addMergedRegion(new CellRangeAddress(1, 1, 0, encabezadoTabla.length - 1));
        Cell celdaTitulo = filaTitulo.createCell(0);
        String titulo ="Periodo de facturación: 01/SEPT/20 - 01/NOV/20";
        celdaTitulo.setCellValue(titulo);

        celdaTitulo.setCellStyle(encabezado);

        Row filaFecha = hoja.createRow(2);
        hoja.addMergedRegion(new CellRangeAddress(2, 2, 0, encabezadoTabla.length - 1));
        Cell celdaFecha = filaFecha.createCell(0);
        celdaFecha.setCellValue("No. de Factura: "+numFactura+" Fecha de generación: " + fecha.format(formatoFecha));
        celdaFecha.setCellStyle(encabezado);

        Row filaEncabezado = hoja.createRow(3);
        for (int j = 0; j < encabezadoTabla.length; j++) {
            filaEncabezado.createCell(j).setCellValue(encabezadoTabla[j]);
        }
        hoja.createFreezePane(0, 0, 3, encabezadoTabla.length - 1);

        for (int i = 0; i < encabezadoTabla.length; i++) {
            hoja.setColumnWidth(i, anchoColumnas[i] * 256);
        }
        int totalAPagar = 0;
        for (int i = 0; i < datosTabla.length; i++) {
            Row fila = hoja.createRow(i + 4);
            for (int j = 0; j < encabezadoTabla.length; j++) {
                fila.createCell(j).setCellValue(datosTabla[i][j]);
            }
            totalAPagar += Integer.parseInt(datosTabla[i][3]);
        }

            Row totales = hoja.createRow(datosTabla.length + 4);
            totales.createCell(5).setCellValue("TOTAL A PAGAR:");
            totales.createCell(6).setCellValue(totalAPagar); //unidades

        try {

            FileOutputStream out = new FileOutputStream(ruta, false);

            excel.write(out);
            out.close();

            excel.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo acceder a la ruta\n" + ex.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo crear el registro \n" + ex.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        } catch (NullPointerException e) {

        }
    }

    public void generarPdf() {
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
}
