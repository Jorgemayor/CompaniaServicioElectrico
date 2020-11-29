package src.vista;

import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONObject;

import src.managers.gestionActivos.GestionActivosApi;
import src.managers.gestionCiudad.GestionCiudadApi;

public class ConsultarActivo extends Container {

    private static final Font FUENTE_TITULO = new Font(null, Font.BOLD, 28);
    private static final Font FUENTE_ETIQUETAS = new Font(null, Font.BOLD, 22);
    private static final Color COLOR_FONDO = new Color(232, 234, 246);

    private JPanel contenido;
    private JLabel titulo;
    private JLabel numSerie;
    private JTextField numSerieCampo;
    private JButton buscar;
    private JButton mostrarTodos;
    private JTable datos;
    private DefaultTableModel modeloDatos;
    private JScrollPane navegacionDatos;

    public ConsultarActivo() {
        iniciarComponentes();
    }

    private void iniciarComponentes() {

        contenido = new JPanel();
        titulo = new JLabel("Consultar Activos");
        numSerie = new JLabel("Número de Serie");
        numSerieCampo = new JTextField();
        buscar = new JButton("Buscar");
        mostrarTodos = new JButton("Mostrar Todos");
        datos = new JTable();
        navegacionDatos = new JScrollPane(datos);

        contenido.setLayout(null);
        contenido.setVisible(true);
        contenido.setBackground(COLOR_FONDO);

        // Titulo
        titulo.setFont(FUENTE_TITULO);
        titulo.setVisible(true);
        titulo.setBounds(550, 30, 400, 25);
        contenido.add(titulo);

        // Formulario
        numSerie.setFont(FUENTE_ETIQUETAS);
        numSerie.setVisible(true);
        numSerie.setBounds(150, 100, 200, 30);
        contenido.add(numSerie);
        numSerieCampo.setVisible(true);
        numSerieCampo.setBounds(330, 100, 200, 30);
        contenido.add(numSerieCampo);

        buscar.setFont(FUENTE_ETIQUETAS);
        buscar.setVisible(true);
        buscar.setBounds(540, 100, 130, 30);
        buscar.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                JSONObject resultado = new JSONObject(GestionActivosApi.obtenerActivosPorSerial(numSerieCampo.getText()));
                if(resultado.getString("code").equals("0")){
                    JSONObject activo = new JSONObject(resultado.getString("activos"));
                    JSONArray id = activo.getJSONArray("id");
                    JSONArray numero_serie = activo.getJSONArray("numero_serie");
                    JSONArray nombre = activo.getJSONArray("nombre");
                    JSONArray id_ciudad = activo.getJSONArray("id_ciudad");
                    JSONArray estado = activo.getJSONArray("estado");
                    JSONArray habilitado = activo.getJSONArray("habilitado");
                    DefaultTableModel modeloDatos = new DefaultTableModel();;
                    modeloDatos = new DefaultTableModel();
                    modeloDatos.addColumn("ID");
                    modeloDatos.addColumn("NUMERO DE SERIE");
                    modeloDatos.addColumn("NOMBRE");
                    modeloDatos.addColumn("CIUDAD");
                    modeloDatos.addColumn("ESTADO");
                    datos.setModel(modeloDatos);
                        modeloDatos.addRow(new Object[]{
                            id.getString(0),
                            numero_serie.getString(0),
                            nombre.getString(0),
                            GestionCiudadApi.obtenerCiudadPorId(id_ciudad.getInt(0)),
                            estado.getString(0)});
                }
                else
                {
                    JOptionPane.showMessageDialog(null, resultado.getString("mensaje"));
                }

            }
            
        });
        contenido.add(buscar);

        mostrarTodos.setFont(FUENTE_ETIQUETAS);
        mostrarTodos.setVisible(true);
        mostrarTodos.setBounds(900, 100, 200, 30);
        mostrarTodos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JSONObject resultado = new JSONObject(GestionActivosApi.obtenerActivos());
                if(resultado.getString("code").equals("0")){
                    JSONObject activos = new JSONObject(resultado.getString("activos"));
                    JSONArray id = activos.getJSONArray("id");
                    JSONArray numero_serie = activos.getJSONArray("numero_serie");
                    JSONArray nombre = activos.getJSONArray("nombre");
                    JSONArray id_ciudad = activos.getJSONArray("id_ciudad");
                    JSONArray estado = activos.getJSONArray("estado");
                    JSONArray habilitado = activos.getJSONArray("habilitado");
                    DefaultTableModel modeloDatos = new DefaultTableModel();;
                    modeloDatos = new DefaultTableModel();
                    modeloDatos.addColumn("ID");
                    modeloDatos.addColumn("NUMERO DE SERIE");
                    modeloDatos.addColumn("NOMBRE");
                    modeloDatos.addColumn("CIUDAD");
                    modeloDatos.addColumn("ESTADO");
                    datos.setModel(modeloDatos);
                    for(int i=0; i<id.length(); i++){
                        modeloDatos.addRow(new Object[]{
                            id.getString(i),
                            numero_serie.getString(i),
                            nombre.getString(i),
                            GestionCiudadApi.obtenerCiudadPorId(id_ciudad.getInt(i)),
                            estado.getString(i)});
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, resultado.getString("mensaje"));
                }
            }
        });
        contenido.add(mostrarTodos);

        //Tabla
        navegacionDatos.setVisible(true);
        navegacionDatos.setBounds(150,150,950,390);
        contenido.add(navegacionDatos);

        this.add(contenido, BorderLayout.CENTER);
    }        
}