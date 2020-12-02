package src.vista;

import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.JSONArray;
import org.json.JSONObject;

import src.managers.gestionConfiguracion.GestionConfiguracionApi;

public class Configurar extends Container {

    private static final Font FUENTE_TITULO = new Font(null, Font.BOLD, 28);
    private static final Font FUENTE_ETIQUETAS = new Font(null, Font.BOLD, 22);
    private static final Color COLOR_FONDO = new Color(232, 234, 246);

    private GestionConfiguracionApi gestionConfiguracionApi;

    private JButton enviar;
    private JButton ver;
    private JPanel contenido;
    private JTextField kwhCampo;
    private JTextField reconexionCampo;
    private JLabel precioReconexion;
    private JLabel precioKWH;
    private JLabel titulo;

    public Configurar() {
        gestionConfiguracionApi = new GestionConfiguracionApi();
        iniciarComponentes();
    }

    private void iniciarComponentes() {

        contenido = new JPanel();
        titulo = new JLabel("Configuración");
        precioKWH = new JLabel("Precio KWH");
        kwhCampo = new JTextField();
        precioReconexion = new JLabel("Precio Reconexión");
        reconexionCampo = new JTextField();
        enviar = new JButton("Enviar Cambios");
        ver = new JButton("Ver Valores Actuales");

        contenido.setLayout(null);
        contenido.setVisible(true);
        contenido.setBackground(COLOR_FONDO);

        // Titulo
        titulo.setFont(FUENTE_TITULO);
        titulo.setVisible(true);
        titulo.setBounds(525, 30, 200, 40);
        contenido.add(titulo);

        // Formulario
        KeyListener validacionNumerica=new KeyListener(){
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
         };
         
        precioKWH.setFont(FUENTE_ETIQUETAS);
        precioKWH.setVisible(true);
        precioKWH.setBounds(460, 150, 200, 30);
        contenido.add(precioKWH);
        kwhCampo.setVisible(true);
        kwhCampo.setBounds(615, 150, 200, 30);
        kwhCampo.addKeyListener(validacionNumerica);
        contenido.add(kwhCampo);

        precioReconexion.setFont(FUENTE_ETIQUETAS);
        precioReconexion.setVisible(true);
        precioReconexion.setBounds(400, 250, 200, 30);
        contenido.add(precioReconexion);
        reconexionCampo.setVisible(true);
        reconexionCampo.setBounds(615, 250, 200, 30);
        reconexionCampo.addKeyListener(validacionNumerica);
        contenido.add(reconexionCampo);
        
        ver.setFont(FUENTE_ETIQUETAS);
        ver.setVisible(true);
        ver.setBounds(500, 80, 250, 30);
        ver.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
                JSONObject resultado = new JSONObject(gestionConfiguracionApi.obtenerParametros());
                JSONObject parametros = new JSONObject(resultado.getString("parametros"));
                JSONArray kwh = new JSONArray(parametros.getJSONArray("kwh")) ;
                JSONArray reconexion = new JSONArray(parametros.getJSONArray("reconexion")) ;
                kwhCampo.setText(kwh.getString(0));
                reconexionCampo.setText(reconexion.getString(0));
				
			}

        });
        contenido.add(ver);
        enviar.setFont(FUENTE_ETIQUETAS);
        enviar.setVisible(true);
        enviar.setBounds(550, 450, 200, 30);
        enviar.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                JSONObject resultado = new JSONObject(gestionConfiguracionApi.actualizarParametros(Integer.parseInt(kwhCampo.getText()), Integer.parseInt(reconexionCampo.getText())));
                if(resultado.getString("code").equals("0")){
                    JOptionPane.showMessageDialog(null, "Configuración Actualizada");
                    kwhCampo.setText("");
                    reconexionCampo.setText("");
                }
                else{
                    JOptionPane.showMessageDialog(null, resultado.getString("mensaje"));
                }
            }
            
        });
        contenido.add(enviar);

        this.add(contenido, BorderLayout.CENTER);
    }
}