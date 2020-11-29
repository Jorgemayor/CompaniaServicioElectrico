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

import src.managers.gestionUsuario.GestionUsuarioApi;

public class ConsultarUsuario extends Container {

    private static final Font FUENTE_TITULO = new Font(null, Font.BOLD, 28);
    private static final Font FUENTE_ETIQUETAS = new Font(null, Font.BOLD, 22);
    private static final Color COLOR_FONDO = new Color(232, 234, 246);

    private JPanel contenido;
    private JLabel titulo;
    private JLabel usuario;
    private JTextField usuarioCampo;
    private JButton buscar;
    private JButton mostrarTodos;
    private JTable datos;
    private JScrollPane navegacionDatos;

    public ConsultarUsuario() {
        iniciarComponentes();
    }

    private void iniciarComponentes() {

        contenido = new JPanel();
        titulo = new JLabel("Consultar Usuarios");
        usuario = new JLabel("Nombre usuario");
        usuarioCampo = new JTextField();
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
        titulo.setBounds(510, 30, 400, 25);
        contenido.add(titulo);

        // Formulario
        usuario.setFont(FUENTE_ETIQUETAS);
        usuario.setVisible(true);
        usuario.setBounds(150, 100, 200, 30);
        contenido.add(usuario);
        usuarioCampo.setVisible(true);
        usuarioCampo.setBounds(330, 100, 200, 30);
        contenido.add(usuarioCampo);

        buscar.setFont(FUENTE_ETIQUETAS);
        buscar.setVisible(true);
        buscar.setBounds(540, 100, 130, 30);
        buscar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JSONObject resultado = new JSONObject(GestionUsuarioApi.obtenerUsuarioPorNombre(usuarioCampo.getText()));
                if(resultado.getString("code").equals("0")){
                    JSONObject usuario = new JSONObject(resultado.getString("usuario"));
                    JSONArray id = usuario.getJSONArray("id");
                    JSONArray nombre = usuario.getJSONArray("nombre");
                    JSONArray rol = usuario.getJSONArray("rol");
                    DefaultTableModel modeloDatos = new DefaultTableModel();;
                    modeloDatos = new DefaultTableModel();
                    modeloDatos.addColumn("ID");
                    modeloDatos.addColumn("NOMBRE");
                    modeloDatos.addColumn("ROL");
                    datos.setModel(modeloDatos);
                    datos.setModel(modeloDatos);
                    String rolString = "";
                        switch (rol.getInt(0)) {
                            case 0:
                                rolString = "Super Usuario";
                                break;
                            case 1:
                                rolString = "Administrador";
                                break;
                            case 2:
                                rolString = "Gerente";
                                break;
                            
                            case 3:
                                rolString = "Operador";
                                break;
                        }
                        modeloDatos.addRow(new Object[]{
                                                        id.getString(0),
                                                        nombre.getString(0),
                                                        rolString});
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
                JSONObject resultado = new JSONObject(GestionUsuarioApi.obtenerUsuarios());
                if(resultado.getString("code").equals("0")){
                    JSONObject usuarios = new JSONObject(resultado.getString("usuarios"));
                    JSONArray id = usuarios.getJSONArray("id");
                    JSONArray nombre = usuarios.getJSONArray("nombre");
                    JSONArray rol = usuarios.getJSONArray("rol");
                    DefaultTableModel modeloDatos = new DefaultTableModel();
                    modeloDatos = new DefaultTableModel();
                    modeloDatos.addColumn("ID");
                    modeloDatos.addColumn("NOMBRE");
                    modeloDatos.addColumn("ROL");
                    datos.setModel(modeloDatos);
                    datos.setModel(modeloDatos);
                    for(int i=0; i<id.length(); i++){
                        String rolString = "";
                        switch (rol.getInt(i)) {
                            case 0:
                                rolString = "Super Usuario";
                                break;
                            case 1:
                                rolString = "Administrador";
                                break;
                            case 2:
                                rolString = "Gerente";
                                break;
                            
                            case 3:
                                rolString = "Operador";
                                break;
                        }
                        modeloDatos.addRow(new Object[]{
                                                        id.getString(i),
                                                        nombre.getString(i),
                                                        rolString});
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
