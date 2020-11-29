package src.vista;

import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.json.JSONObject;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JOptionPane;


import src.managers.gestionClientes.GestionClienteApi;

public class DeshabilitarCliente extends Container {

    private static final Font FUENTE_TITULO = new Font(null, Font.BOLD, 28);
    private static final Font FUENTE_ETIQUETAS = new Font(null, Font.BOLD, 22);
    private static final Color COLOR_FONDO = new Color(232,234,246);

    private JPanel panelContenido;
    private JLabel etiquetaTitulo;
    private JLabel etiquetaTipoId;
    private JComboBox selectorTipoId;
    private JLabel etiquetaIdentificacion;
    private JTextField campoIdentificacion;
    private JButton botonVer;
    private JLabel etiquetaNombre;
    private JLabel etiquetaNombreActual;
    private JLabel etiquetaEstado;
    private JLabel etiquetaEstadoActual;
    private JToggleButton botonCambio;
    


    public DeshabilitarCliente(){
        iniciarComponentes();
    }
    
    private void iniciarComponentes() {

        panelContenido = new JPanel();
        etiquetaTitulo = new JLabel("Deshabilitar/Habilitar Cliente");
        etiquetaTipoId = new JLabel("Tipo de ID");
        selectorTipoId = new JComboBox();
        etiquetaIdentificacion = new JLabel("ID");
        campoIdentificacion = new JTextField();
        botonVer = new JButton("ver");
        etiquetaNombre = new JLabel("Nombre:");
        etiquetaNombreActual = new JLabel();

        etiquetaEstado = new JLabel("Estado:");
        etiquetaEstadoActual = new JLabel("");
        botonCambio = new JToggleButton("Hablitar/Deshabilitar");

        panelContenido.setLayout(null);
        panelContenido.setVisible(true);
        panelContenido.setBackground(COLOR_FONDO);

        //Titulo
        etiquetaTitulo.setFont(FUENTE_TITULO);
        etiquetaTitulo.setVisible(true);
        etiquetaTitulo.setBounds(530, 30, 300, 25);
        panelContenido.add(etiquetaTitulo);

        //Formulario
        etiquetaTipoId.setFont(FUENTE_ETIQUETAS);
        etiquetaTipoId.setVisible(true);
        etiquetaTipoId.setBounds(150, 150, 200, 30);
        panelContenido.add(etiquetaTipoId);
        selectorTipoId.setVisible(true);
        selectorTipoId.setBounds(300, 150, 200, 30);
        selectorTipoId.addItem("RC");
        selectorTipoId.addItem("TI");
        selectorTipoId.addItem("CC");
        panelContenido.add(selectorTipoId);

        etiquetaIdentificacion.setFont(FUENTE_ETIQUETAS);
        etiquetaIdentificacion.setVisible(true);
        etiquetaIdentificacion.setBounds(800, 150, 150, 30);
        panelContenido.add(etiquetaIdentificacion);
        campoIdentificacion.setVisible(true);
        campoIdentificacion.setBounds(830, 150, 200, 30);
        panelContenido.add(campoIdentificacion);
        botonVer.setFont(FUENTE_ETIQUETAS);
        botonVer.setVisible(true);
        botonVer.setBounds(1035, 150, 70, 30);
        botonVer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JSONObject resultado = new JSONObject(GestionClienteApi.buscarEnTodosLosClientes(campoIdentificacion.getText()));
                JSONObject cliente = new JSONObject(resultado.getString("cliente"));
                String tipoIdentificacion = cliente.getJSONArray("tipo_identificacion").getString(0);
                int identificador = cliente.getJSONArray("identificador").getInt(0);
                String nombre = cliente.getJSONArray("nombre").getString(0);
                String stringEstado="";
                if(cliente.getJSONArray("habilitado").getBoolean(0)){
                    stringEstado = "Habilitado";
                    botonCambio.setText("Deshabilitar");
                }
                else{
                    stringEstado= "Deshabilitado";
                    botonCambio.setText("Habilitar");
                }
                selectorTipoId.setSelectedItem(tipoIdentificacion);
                campoIdentificacion.setText(String.valueOf(identificador));
                etiquetaEstado.setText("Estado: "+stringEstado);
                etiquetaTitulo.setText(etiquetaTitulo.getText()+": "+nombre);
            }
        });
        panelContenido.add(botonVer);

        etiquetaNombre.setFont(FUENTE_ETIQUETAS);
        etiquetaNombre.setVisible(true);
        etiquetaNombre.setBounds(150, 225, 150, 30);
        panelContenido.add(etiquetaNombre);
        etiquetaNombreActual.setVisible(true);
        etiquetaNombreActual.setBounds(300, 225, 200, 30);
        panelContenido.add(etiquetaNombreActual);

        etiquetaEstado.setFont(FUENTE_ETIQUETAS);
        etiquetaEstado.setVisible(true);
        etiquetaEstado.setBounds(800, 225, 150, 30);
        panelContenido.add(etiquetaEstado);
        etiquetaEstadoActual.setVisible(true);
        etiquetaEstadoActual.setBounds(890, 225, 220, 30);
        panelContenido.add(etiquetaEstadoActual);

        botonCambio.setFont(FUENTE_ETIQUETAS);
        botonCambio.setVisible(true);
        botonCambio.setBounds(800, 300, 250, 30);
        botonCambio.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                String resultado = GestionClienteApi.cambiarEstadoCliente(Integer.parseInt(campoIdentificacion.toString()));
                JSONObject jsonResultado = new JSONObject(resultado);
                String codigo = jsonResultado.getString("code");
                if (codigo.equals("0")) {
                    if(botonCambio.getText()=="Deshabilitar"){
                        botonCambio.setText("Habilitar");
                        etiquetaEstado.setText("Estado: "+"Deshabilitado");
                    }
                    else{
                        botonCambio.setText("Deshabilitar");
                        etiquetaEstado.setText("Estado: "+"Habilitado");
                    }
                } else {
                    String mensaje = jsonResultado.getString("mensaje");
                    JOptionPane.showMessageDialog(null, mensaje);
                }
            }
            
        });
        panelContenido.add(botonCambio);
        

        this.add(panelContenido, BorderLayout.CENTER);
    }
}