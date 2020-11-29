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


import org.json.JSONObject;

import src.managers.gestionUsuario.GestionUsuarioApi;

public class ModificarUsuario extends Container {

    private static final Font FUENTE_TITULO = new Font(null, Font.BOLD, 28);
    private static final Font FUENTE_ETIQUETAS = new Font(null, Font.BOLD, 22);
    private static final Color COLOR_FONDO = new Color(232, 234, 246);

    private JPanel contenido;
    private JLabel titulo;
    private JLabel nombre;
    private JTextField nombreCampo;
    private JButton ver;
    private JLabel contrasena;
    private JTextField contrasenaCampo;
    private JLabel rol;
    private JComboBox selectorRol;
    private JButton enviar;
    private int idUsuarioActual;

    public ModificarUsuario() {
        iniciarComponentes();
    }

    private void iniciarComponentes() {

        contenido = new JPanel();
        titulo = new JLabel("Modificar Usuario");
        nombre = new JLabel("Nombre");
        nombreCampo = new JTextField();
        ver = new JButton("ver");
        contrasena = new JLabel("Contraseña");
        contrasenaCampo = new JTextField();
        rol = new JLabel("Rol");
        selectorRol = new JComboBox();
        enviar = new JButton("Enviar Cambios");
        idUsuarioActual = -1;

        contenido.setLayout(null);
        contenido.setVisible(true);
        contenido.setBackground(COLOR_FONDO);

        // Titulo
        titulo.setFont(FUENTE_TITULO);
        titulo.setVisible(true);
        titulo.setBounds(490, 30, 500, 25);
        contenido.add(titulo);

        // Formulario
        nombre.setFont(FUENTE_ETIQUETAS);
        nombre.setVisible(true);
        nombre.setBounds(500, 150, 200, 30);
        contenido.add(nombre);
        nombreCampo.setVisible(true);
        nombreCampo.setBounds(600, 150, 200, 30);
        contenido.add(nombreCampo);
        ver.setFont(FUENTE_ETIQUETAS);
        ver.setVisible(true);
        ver.setBounds(810, 150, 70, 30);
        ver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JSONObject resultado = new JSONObject(GestionUsuarioApi.obtenerUsuarioPorNombre(nombreCampo.getText()));
                if(resultado.getString("code").equals("0")){
                    JSONObject usuario = new JSONObject(resultado.getString("usuario"));
                    int id = usuario.getJSONArray("id").getInt(0);
                    String nombre = usuario.getJSONArray("nombre").getString(0);
                    String contrasenia = usuario.getJSONArray("contraseña").getString(0);
                    int rol = usuario.getJSONArray("rol").getInt(0);
                    idUsuarioActual = id;
                    nombreCampo.setText(nombre);
                    contrasenaCampo.setText(contrasenia);
                    selectorRol.setSelectedIndex(rol-1);
                    titulo.setText(titulo.getText()+": "+nombre);
                }
                else{
                    JOptionPane.showMessageDialog(null, resultado.getString("mensaje"));
                }

            }
            
        });
        contenido.add(ver);

        contrasena.setFont(FUENTE_ETIQUETAS);
        contrasena.setVisible(true);
        contrasena.setBounds(460, 250, 150, 30);
        contenido.add(contrasena);
        contrasenaCampo.setVisible(true);
        contrasenaCampo.setBounds(600, 250, 200, 30);
        contenido.add(contrasenaCampo);

        rol.setFont(FUENTE_ETIQUETAS);
        rol.setVisible(true);
        rol.setBounds(545, 350, 150, 30);
        contenido.add(rol);
        selectorRol.setVisible(true);
        selectorRol.setBounds(600, 350, 200, 30);
        selectorRol.addItem("Administrador");
        selectorRol.addItem("Gerente");
        selectorRol.addItem("Operador");
        contenido.add(selectorRol);
        
        enviar.setFont(FUENTE_ETIQUETAS);
        enviar.setVisible(true);
        enviar.setBounds(550, 450, 200, 30);
        enviar.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreUsuario = nombreCampo.getText();
                String contrasena = contrasenaCampo.getText();
                String rol = (String)selectorRol.getSelectedItem();
                int idRol = 0;
                
                if(rol.equals("Administrador"))
                    idRol = 1;
                else if(rol.equals("Gerente"))
                    idRol = 2;
                else if(rol.equals("Operador"))
                    idRol = 3;

                String resultado = GestionUsuarioApi.actualizarUsuario(idUsuarioActual, nombreUsuario, contrasena, idRol);
                JSONObject jsonResultado = new JSONObject(resultado);
                String codigo = jsonResultado.getString("code");
                if (codigo.equals("0")) {
                    JOptionPane.showMessageDialog(null, "Usuario Modificado");
                } else {
                    String mensaje = jsonResultado.getString("mensaje");
                    JOptionPane.showMessageDialog(null, mensaje);
                }
                titulo.setText("Modificar Usuario");
                nombreCampo.setText("");
                contrasenaCampo.setText("");
                selectorRol.setSelectedItem("");
            }
            
        });
        contenido.add(enviar);

        this.add(contenido, BorderLayout.CENTER);
    }
}