package src.vista;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class VentanaPrincipal extends JFrame {

    private Container contenedorPrincipal;
    private Container contenedorVentanas;
    private Container contenedorMenu;
    private Container contenedorLogo;

    private BoxLayout esquemaPrincipal;
    private BoxLayout esquemaMenu;
    private BoxLayout esquemaLogo;

    private static final Dimension DIMENSIONES_VENTANA = new Dimension(1280, 720);
    private static final Dimension DIMENSIONES_CONTENEDOR_LOGO = new Dimension(1280, 120);
    private static final Dimension DIMENSIONES_CONTENEDOR_MENU = new Dimension(1280, 70);
    private static final Dimension DIMENSIONES_CONTENEDOR_VENTANA = new Dimension(1280, 530);
    private static final Color COLOR_BOTON = new Color(197,202,233);
    private static final Color COLOR_BOTON_PRESIONADO = new Color(173,174,226);
    private static final Color COLOR_FONDO = new Color(232,234,246);

    public VentanaPrincipal(int rol) {
        this.setPreferredSize(DIMENSIONES_VENTANA);
        this.setBackground(COLOR_FONDO);
        this.setUndecorated(true);
        iniciarComponentes(rol);
    }

    private void iniciarComponentes(int rol) {        
        /**
         * Contenedor principal
         */
        contenedorPrincipal = new Container();
        esquemaPrincipal = new BoxLayout(contenedorPrincipal, BoxLayout.Y_AXIS);
        contenedorPrincipal.setLayout(esquemaPrincipal);

        /**
         * Contenedor logo
         */
        contenedorLogo = new JPanel();
        esquemaLogo = new BoxLayout(contenedorLogo, BoxLayout.X_AXIS);
        contenedorLogo.setLayout(esquemaLogo);
        contenedorLogo.setPreferredSize(DIMENSIONES_CONTENEDOR_LOGO);
        contenedorLogo.setBackground(Color.BLUE);
        JLabel iconLabel = new JLabel();
        iconLabel.setIcon(new ImageIcon("src/assets/logo.png"));
        iconLabel.setVisible(true);
        contenedorLogo.add(iconLabel, BoxLayout.X_AXIS);
        contenedorPrincipal.add(contenedorLogo);

        /**
         * Contenedor menú
         */
        contenedorMenu = new Container();
        esquemaMenu = new BoxLayout(contenedorMenu, BoxLayout.X_AXIS);
        contenedorMenu.setLayout(esquemaMenu);
        contenedorMenu.setPreferredSize(DIMENSIONES_CONTENEDOR_MENU);
        contenedorPrincipal.add(contenedorMenu);

        /**
         * Contenedor ventana dinámica
         */
        contenedorVentanas = new Inicio();
        contenedorVentanas.setLayout(new BoxLayout(contenedorVentanas, BoxLayout.X_AXIS));
        contenedorVentanas.setPreferredSize(DIMENSIONES_CONTENEDOR_VENTANA);
        contenedorPrincipal.add(contenedorVentanas);

        /**
         * Botones menú
         */
        JButton bUsuarios = new JButton("Usuarios");
        bUsuarios.setBorderPainted(false);
        bUsuarios.setBackground(COLOR_BOTON);
        bUsuarios.setFocusable(false);
        JButton bActivos = new JButton("Activos");
        bActivos.setBorderPainted(false);
        bActivos.setBackground(COLOR_BOTON);
        bActivos.setFocusable(false);
        JButton bConfiguracion = new JButton("Configuración");
        bConfiguracion.setBorderPainted(false);
        bConfiguracion.setBackground(COLOR_BOTON);
        bConfiguracion.setFocusable(false);
        JButton bFacturas = new JButton("Facturas");
        bFacturas.setBorderPainted(false);
        bFacturas.setBackground(COLOR_BOTON);
        bFacturas.setFocusable(false);
        JButton bPagos = new JButton("Pagos");
        bPagos.setBorderPainted(false);
        bPagos.setBackground(COLOR_BOTON);
        bPagos.setFocusable(false);
        JButton bReportes = new JButton("Reportes");
        bReportes.setBorderPainted(false);
        bReportes.setBackground(COLOR_BOTON);
        bReportes.setFocusable(false);
        JButton bClientes = new JButton("Clientes");
        bClientes.setBorderPainted(false);
        bClientes.setBackground(COLOR_BOTON);
        bClientes.setFocusable(false);
        JButton bSalir = new JButton("Salir");
        bSalir.setBorderPainted(false);
        bSalir.setBackground(COLOR_BOTON);
        bSalir.setFocusable(false);

        switch (rol) {
            case 1: //admin
                contenedorMenu.add(bUsuarios);
                contenedorMenu.add(bActivos);
                contenedorMenu.add(bConfiguracion);
                contenedorMenu.add(bSalir);
                break;

            case 2: //gerente
                contenedorMenu.add(bReportes);
                contenedorMenu.add(bSalir);
                break;

            case 3: //operador
            contenedorMenu.add(bFacturas);
            contenedorMenu.add(bPagos);
            contenedorMenu.add(bClientes);
            contenedorMenu.add(bSalir);
            break;
        }
        

        /**
         * Eventos de botones del menú
         */
        ActionListener escuchaBoton = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource().equals(bSalir)){
                    int salir = JOptionPane.showConfirmDialog(null, "Realmente desea salir?", "Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(salir==JOptionPane.YES_OPTION)
                    {
                        System.exit(0);
                    }
                }
                else{
                    contenedorPrincipal.remove(contenedorVentanas);
                    bFacturas.setBackground(COLOR_BOTON);
                    bPagos.setBackground(COLOR_BOTON);
                    bClientes.setBackground(COLOR_BOTON);
                    bUsuarios.setBackground(COLOR_BOTON);
                    bActivos.setBackground(COLOR_BOTON);
                    bConfiguracion.setBackground(COLOR_BOTON);
                    bReportes.setBackground(COLOR_BOTON);
                    bSalir.setBackground(COLOR_BOTON);
                }
                if(e.getSource().equals(bFacturas)){
                    contenedorVentanas = new Facturas();
                    bFacturas.setBackground(COLOR_BOTON_PRESIONADO);
                }
                else if(e.getSource().equals(bPagos)){
                    bPagos.setBackground(COLOR_BOTON_PRESIONADO);
                    contenedorVentanas = new Pagos();
                }
                else if(e.getSource().equals(bClientes)){
                    bClientes.setBackground(COLOR_BOTON_PRESIONADO);
                    contenedorVentanas = new Clientes();
                }
                else if(e.getSource().equals(bUsuarios)){
                    bUsuarios.setBackground(COLOR_BOTON_PRESIONADO);
                    contenedorVentanas = new Usuarios();
                }
                else if(e.getSource().equals(bActivos)){
                    bActivos.setBackground(COLOR_BOTON_PRESIONADO);
                    contenedorVentanas = new Activos();
                }
                else if(e.getSource().equals(bConfiguracion)){
                    bConfiguracion.setBackground(COLOR_BOTON_PRESIONADO);
                    contenedorVentanas = new Configuracion();
                }
                else if(e.getSource().equals(bReportes)){
                    bReportes.setBackground(COLOR_BOTON_PRESIONADO);
                    contenedorVentanas = new Reportes();
                }
                contenedorVentanas.setLayout(new BoxLayout(contenedorVentanas, BoxLayout.X_AXIS));
                contenedorPrincipal.add(contenedorVentanas);
                contenedorPrincipal.validate();
                contenedorPrincipal.repaint();
            }
        };
        bFacturas.addActionListener(escuchaBoton);
        bPagos.addActionListener(escuchaBoton);
        bClientes.addActionListener(escuchaBoton);
        bUsuarios.addActionListener(escuchaBoton);
        bActivos.addActionListener(escuchaBoton);
        bConfiguracion.addActionListener(escuchaBoton);
        bReportes.addActionListener(escuchaBoton);
        bSalir.addActionListener(escuchaBoton);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Compañia de servicio eléctrico");
        this.setContentPane(contenedorPrincipal);
        this.setResizable(false);
        this.setVisible(true);
        this.pack();
        this.setLocationRelativeTo(null);
    }
}
