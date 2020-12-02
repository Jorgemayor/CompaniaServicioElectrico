package src.vista;

import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.json.JSONArray;
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
    private JLabel etiquetaIdentificacion;
    private JTextField campoIdentificacion;
    private JButton botonVer;
    private JLabel etiquetaEstado;
    private JLabel etiquetaNombre;
    private JButton botonCambio;
    private int idClienteActual;
    


    public DeshabilitarCliente(){
        iniciarComponentes();
    }
    
    private void iniciarComponentes() {

        panelContenido = new JPanel();
        etiquetaTitulo = new JLabel("Deshabilitar/Habilitar Cliente");
        etiquetaIdentificacion = new JLabel("Identificación");
        campoIdentificacion = new JTextField();
        botonVer = new JButton("ver");
        idClienteActual = -1;
        etiquetaEstado = new JLabel("Estado:");
        etiquetaNombre = new JLabel("Nombre:");
        botonCambio = new JButton("Hablitar/Deshabilitar");
        panelContenido.setLayout(null);
        panelContenido.setVisible(true);
        panelContenido.setBackground(COLOR_FONDO);

        //Titulo
        etiquetaTitulo.setFont(FUENTE_TITULO);
        etiquetaTitulo.setVisible(true);
        etiquetaTitulo.setBounds(450, 20, 600, 50);
        panelContenido.add(etiquetaTitulo);

        //Formulario

        etiquetaIdentificacion.setFont(FUENTE_ETIQUETAS);
        etiquetaIdentificacion.setVisible(true);
        etiquetaIdentificacion.setBounds(385, 130, 150, 30);
        panelContenido.add(etiquetaIdentificacion);
        campoIdentificacion.setVisible(true);
        campoIdentificacion.setBounds(530, 130, 200, 30);
        campoIdentificacion.addKeyListener(new KeyListener(){

            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) ||
                (c == KeyEvent.VK_BACK_SPACE) ||
                (c == KeyEvent.VK_DELETE))) {
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
        panelContenido.add(campoIdentificacion);
        botonVer.setFont(FUENTE_ETIQUETAS);
        botonVer.setVisible(true);
        botonVer.setBounds(735, 130, 100, 30);
        botonVer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(botonVer.getText()=="ver"){
                    JSONObject resultado = new JSONObject(GestionClienteApi.buscarEnTodosLosClientes(campoIdentificacion.getText()));
                    String codigo = resultado.getString("code");
                    if(codigo.equals("0")){
                        campoIdentificacion.setEditable(false);
                        botonVer.setText(("limpiar"));
                        JSONObject cliente = new JSONObject(resultado.getString("cliente"));
                        int id = cliente.getJSONArray("id").getInt(0);
                        String nombreCliente = cliente.getJSONArray("nombre").getString(0);
                        String stringEstado = "";
                        if(cliente.getJSONArray("habilitado").getBoolean(0)){
                            stringEstado = "Habilitado";
                            botonCambio.setText("Deshabilitar");
                        }
                        else{
                            System.out.print(codigo+"\n");
                            stringEstado= "Deshabilitado";
                            botonCambio.setText("Habilitar");
                        }
                        idClienteActual = id;
                        etiquetaNombre.setText("Nombre: "+nombreCliente);
                        etiquetaEstado.setText("Estado: "+stringEstado);
                    }
                    else{
                        String mensaje = resultado.getString("mensaje");
                        JOptionPane.showMessageDialog(null, mensaje);
                    }    
                }
                else{
                    botonVer.setText("ver");
                    idClienteActual = -1;
                    campoIdentificacion.setText("");
                    campoIdentificacion.setEditable(true);
                    botonCambio.setText("Habilitar/Deshabilitar");
                    etiquetaNombre.setText("Nombre:");
                    etiquetaEstado.setText("Estado:");
                }            
            }
        });
        panelContenido.add(botonVer);

        etiquetaNombre.setFont(FUENTE_ETIQUETAS);
        etiquetaNombre.setVisible(true);
        etiquetaNombre.setBounds(500, 205, 300, 30);
        panelContenido.add(etiquetaNombre);
        etiquetaEstado.setFont(FUENTE_ETIQUETAS);
        etiquetaEstado.setVisible(true);
        etiquetaEstado.setBounds(500, 270, 300, 30);
        panelContenido.add(etiquetaEstado);

        botonCambio.setFont(FUENTE_ETIQUETAS);
        botonCambio.setVisible(true);
        botonCambio.setBounds(500, 350, 250, 30);
        botonCambio.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                String resultado = GestionClienteApi.cambiarEstadoCliente(idClienteActual);
                JSONObject jsonResultado = new JSONObject(resultado);
                String codigo = jsonResultado.getString("code");
                System.out.print(codigo+"\n");
                if (codigo.equals("0")) {
                    if(botonCambio.getText()=="Deshabilitar"){
                        System.out.print(codigo+"\n");
                        botonCambio.setText("Habilitar");
                        etiquetaEstado.setText("Estado: "+"Deshabilitado");
                    }
                    else{
                        System.out.print(codigo+"\n");
                        botonCambio.setText("Deshabilitar");
                        etiquetaEstado.setText("Estado: "+"Habilitado");
                    }
                } else {
                    System.out.print(codigo+"\n");
                    String mensaje = jsonResultado.getString("mensaje");
                    JOptionPane.showMessageDialog(null, mensaje);
                }
            }
            
        });
        panelContenido.add(botonCambio);
        

        this.add(panelContenido, BorderLayout.CENTER);
    }
}