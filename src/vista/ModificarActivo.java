package src.vista;

import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.JSONArray;
import org.json.JSONObject;

import src.managers.gestionActivos.GestionActivosApi;
import src.managers.gestionCiudad.GestionCiudadApi;

public class ModificarActivo extends Container {

    private static final Font FUENTE_TITULO = new Font(null, Font.BOLD, 28);
    private static final Font FUENTE_ETIQUETAS = new Font(null, Font.BOLD, 22);
    private static final Color COLOR_FONDO = new Color(232, 234, 246);

    private JPanel contenido;
    private JLabel titulo;
    private JLabel numSerie;
    private JTextField numSerieCampo;
    private JButton ver;
    private JLabel nombre;
    private JTextField nombreCampo;
    private JLabel ciudad;
    private JComboBox ciudadList;
    private JLabel estado;
    private JComboBox estadoList;
    private JButton enviar;
    private int idActivoActual;

    public ModificarActivo() {
        iniciarComponentes();
    }

    private void iniciarComponentes() {

        contenido = new JPanel();
        titulo = new JLabel("Modificar Activos");
        numSerie = new JLabel("Número de Serie");
        numSerieCampo = new JTextField();
        ver = new JButton("ver");
        nombre = new JLabel("Nombre");
        nombreCampo = new JTextField();
        ciudad = new JLabel("Ciudad");
        ciudadList = new JComboBox();
        estado = new JLabel("Estado");
        estadoList = new JComboBox();
        enviar = new JButton("Enviar Cambios");

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
        numSerie.setBounds(150, 150, 200, 30);
        contenido.add(numSerie);
        numSerieCampo.setVisible(true);
        numSerieCampo.setBounds(330, 150, 200, 30);
        contenido.add(numSerieCampo);
        ver.setFont(FUENTE_ETIQUETAS);
        ver.setVisible(true);
        ver.setBounds(540, 150, 100, 30);
        ver.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JSONObject resultado = new JSONObject(GestionActivosApi.obtenerActivosPorSerial(numSerieCampo.getText()));
                if(resultado.getString("code").equals("0") & ver.getText()=="ver"){
                    JSONObject activo = new JSONObject(resultado.getString("activos"));
                    int id = activo.getJSONArray("id").getInt(0);
                    String nombre = activo.getJSONArray("nombre").getString(0);
                    int idCiudad = activo.getJSONArray("id_ciudad").getInt(0);
                    String stringCiudad = GestionCiudadApi.obtenerCiudadPorId(idCiudad);
                    String estadoActivo = activo.getJSONArray("estado").getString(0);
                    idActivoActual = id;
                    nombreCampo.setText(nombre);
                    ciudadList.setSelectedItem(stringCiudad);
                    estadoList.setSelectedItem(estadoActivo);
                    numSerieCampo.setEditable(false);
                    ver.setText("limpiar");
                }
                else if(ver.getText()=="limpiar"){
                    idActivoActual = -1;
                    nombreCampo.setText("");
                    ciudadList.setSelectedIndex(0);
                    estadoList.setSelectedIndex(0);
                    numSerieCampo.setText("");
                    numSerieCampo.setEditable(true);
                    ver.setText("ver");
                }
                else{
                    JOptionPane.showMessageDialog(null, resultado.getString("mensaje"));
                }
            }
            
        });
        contenido.add(ver);

        nombre.setFont(FUENTE_ETIQUETAS);
        nombre.setVisible(true);
        nombre.setBounds(800, 150, 150, 30);
        contenido.add(nombre);
        nombreCampo.setVisible(true);
        nombreCampo.setBounds(890, 150, 200, 30);
        contenido.add(nombreCampo);

        ciudad.setFont(FUENTE_ETIQUETAS);
        ciudad.setVisible(true);
        ciudad.setBounds(150, 300, 150, 30);
        contenido.add(ciudad);
        JSONObject ciudades = new JSONObject(GestionCiudadApi.obtenerCiudades());
        JSONArray arregloCiudades = ciudades.getJSONArray("nombre");
        for(int i = 0; i<arregloCiudades.length(); i++){
            ciudadList.addItem(arregloCiudades.getString(i));
        }
        ciudadList.setVisible(true);
        ciudadList.setBounds(330, 300, 200, 30);
        contenido.add(ciudadList);

        estado.setFont(FUENTE_ETIQUETAS);
        estado.setVisible(true);
        estado.setBounds(800, 300, 150, 30);
        contenido.add(estado);
        estadoList.setVisible(true);
        estadoList.setBounds(890, 300, 200, 30);
        estadoList.addItem("Buen estado");
        estadoList.addItem("Dañado");
        estadoList.addItem("En reparación");
        contenido.add(estadoList);
        
        enviar.setFont(FUENTE_ETIQUETAS);
        enviar.setVisible(true);
        enviar.setBounds(550, 450, 200, 30);
        enviar.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                String resultado = GestionActivosApi.actualizarActivo(idActivoActual, nombreCampo.getText(), ciudadList.getSelectedIndex()+1, estadoList.getSelectedItem().toString());
                JSONObject jsonResultado = new JSONObject(resultado);
                String codigo = jsonResultado.getString("code");
                if (codigo.equals("0")) {
                    JOptionPane.showMessageDialog(null, "Usuario Modificado");
                }
                else{
                    String mensaje = jsonResultado.getString("mensaje");
                    JOptionPane.showMessageDialog(null, mensaje);
                }
            }
            
        });
        contenido.add(enviar);

        this.add(contenido, BorderLayout.CENTER);
    }        
}