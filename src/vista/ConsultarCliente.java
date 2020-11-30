package src.vista;

import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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

import src.managers.gestionClientes.GestionClienteApi;
import src.managers.gestionCiudad.GestionCiudadApi;

public class ConsultarCliente extends Container {

    private static final Font FUENTE_TITULO = new Font(null, Font.BOLD, 28);
    private static final Font FUENTE_ETIQUETAS = new Font(null, Font.BOLD, 22);
    private static final Color COLOR_FONDO = new Color(232,234,246);

    private JPanel panelContenido;
    private JLabel etiquetaTitulo;
    private JLabel etiquetaIdentificacion;
    private JTextField campoIdentificacion;
    private JButton botonBuscar;
    private JButton botonMostrarTodos;
    private JTable tablaDatos;
    private JScrollPane navegacionDatos;

    public ConsultarCliente(){
        iniciarComponentes();
    }
    
    private void iniciarComponentes() {

        panelContenido = new JPanel();
        etiquetaTitulo = new JLabel("Consultar Clientes");
        etiquetaIdentificacion = new JLabel("Identificación");
        campoIdentificacion = new JTextField();
        botonBuscar = new JButton("Buscar");
        botonMostrarTodos = new JButton("Mostrar Todos");
        tablaDatos = new JTable();
        navegacionDatos = new JScrollPane(tablaDatos);

        panelContenido.setLayout(null);
        panelContenido.setVisible(true);
        panelContenido.setBackground(COLOR_FONDO);

        //Titulo
        etiquetaTitulo.setFont(FUENTE_TITULO);
        etiquetaTitulo.setVisible(true);
        etiquetaTitulo.setBounds(550, 30, 400, 25);
        panelContenido.add(etiquetaTitulo);

        //Formulario
        etiquetaIdentificacion.setFont(FUENTE_ETIQUETAS);
        etiquetaIdentificacion.setVisible(true);
        etiquetaIdentificacion.setBounds(150, 100, 200, 30);
        panelContenido.add(etiquetaIdentificacion);
        campoIdentificacion.setVisible(true);
        campoIdentificacion.setBounds(330, 100, 200, 30);
        campoIdentificacion.addKeyListener(new KeyListener() {

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

        });;
        panelContenido.add(campoIdentificacion);

        botonBuscar.setFont(FUENTE_ETIQUETAS);
        botonBuscar.setVisible(true);
        botonBuscar.setBounds(540, 100, 130, 30);
        botonBuscar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JSONObject resultado = new JSONObject(GestionClienteApi.obtenerClientePorId(campoIdentificacion.getText()));
                if(resultado.getString("code").equals("0")){
                    JSONObject cliente = new JSONObject(resultado.getString("cliente"));
                    JSONArray tipoIdentificacion = cliente.getJSONArray("tipo_identificacion");
                    JSONArray identificacion = cliente.getJSONArray("identificacion");
                    JSONArray nombre = cliente.getJSONArray("nombre");
                    JSONArray direccion = cliente.getJSONArray("direccion");
                    JSONArray idCiudad = cliente.getJSONArray("id_ciudad");
                    DefaultTableModel modeloDatos = new DefaultTableModel();;
                    modeloDatos = new DefaultTableModel();
                    modeloDatos.addColumn("TIPO ID");
                    modeloDatos.addColumn("ID");
                    modeloDatos.addColumn("NOMBRE");
                    modeloDatos.addColumn("DIRECCION");
                    modeloDatos.addColumn("CIUDAD");
                    tablaDatos.setModel(modeloDatos);
                        modeloDatos.addRow(new Object[]{
                            tipoIdentificacion.getString(0),
                            identificacion.getString(0),
                            nombre.getString(0),
                            direccion.getString(0),
                            GestionCiudadApi.obtenerCiudadPorId(idCiudad.getInt(0))});
                }
                else
                {
                    JOptionPane.showMessageDialog(null, resultado.getString("mensaje"));
                }
            }
            
        });
        panelContenido.add(botonBuscar);

        botonMostrarTodos.setFont(FUENTE_ETIQUETAS);
        botonMostrarTodos.setVisible(true);
        botonMostrarTodos.setBounds(900, 100, 200, 30);
        botonMostrarTodos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JSONObject resultado = new JSONObject(GestionClienteApi.obtenerClientes());
                if(resultado.getString("code").equals("0")){
                    JSONObject cliente = new JSONObject(resultado.getString("clientes"));
                    JSONArray tipoIdentificacion = cliente.getJSONArray("tipo_identificacion");
                    JSONArray identificacion = cliente.getJSONArray("identificacion");
                    JSONArray nombre = cliente.getJSONArray("nombre");
                    JSONArray direccion = cliente.getJSONArray("direccion");
                    JSONArray idCiudad = cliente.getJSONArray("id_ciudad");
                    DefaultTableModel modeloDatos = new DefaultTableModel();;
                    modeloDatos = new DefaultTableModel();
                    modeloDatos.addColumn("TIPO ID");
                    modeloDatos.addColumn("ID");
                    modeloDatos.addColumn("NOMBRE");
                    modeloDatos.addColumn("DIRECCION");
                    modeloDatos.addColumn("CIUDAD");
                    tablaDatos.setModel(modeloDatos);
                    
                    for(int i=0; i<identificacion.length(); i++){
                        modeloDatos.addRow(new Object[]{
                            tipoIdentificacion.getString(i),
                            identificacion.getString(i),
                            nombre.getString(i),
                            direccion.getString(i),
                            GestionCiudadApi.obtenerCiudadPorId(idCiudad.getInt(i))});
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, resultado.getString("mensaje"));
                }
            }
        });
        panelContenido.add(botonMostrarTodos);

        //Tabla
        navegacionDatos.setVisible(true);
        navegacionDatos.setBounds(150,150,950,390);
        panelContenido.add(navegacionDatos);
        this.add(panelContenido, BorderLayout.CENTER);
    }  
}
