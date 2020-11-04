package src.vista;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class VentanaPrincipal extends JFrame {

    private Container contenedorPrincipal;
    private Container contenedorVentanas;
    private Container contenedorMenu;
    private Container contenedorLogo;

    private BoxLayout esquemaPrincipal;
    private BoxLayout esquemaMenu;
    private BoxLayout esquemaLogo;

    private static final Dimension DIMENSIONES_CONTENEDOR_LOGO = new Dimension(700, 120);
    private static final Dimension DIMENSIONES_CONTENEDOR_MENU = new Dimension(700, 70);
    private static final Dimension DIMENSIONES_CONTENEDOR_VENTANA = new Dimension(700, 300);

    public VentanaPrincipal() {

        iniciarComponentes();

    }

    private void iniciarComponentes() {

        /**
         * Contenedor principal
         */
        contenedorPrincipal = new Container();
        esquemaPrincipal = new BoxLayout(contenedorPrincipal, BoxLayout.PAGE_AXIS);
        contenedorPrincipal.setLayout(esquemaPrincipal);

        /**
         * Contenedor logo
         */
        contenedorLogo = new Container();
        esquemaLogo = new BoxLayout(contenedorLogo, BoxLayout.X_AXIS);
        contenedorLogo.setLayout(esquemaLogo);
        contenedorLogo.setPreferredSize(DIMENSIONES_CONTENEDOR_LOGO);
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
         * Botones menú
         */
        JButton bFacturas = new JButton("Facturas");
 
        JButton bPagos = new JButton("Pagos");
        JButton bClientes = new JButton("Clientes");
        JButton bReportes = new JButton("Reportes");
        JButton bUsuarios = new JButton("Usuarios");
        JButton bActivos = new JButton("Activos");
        contenedorMenu.add(bFacturas);
        contenedorMenu.add(bPagos);
        contenedorMenu.add(bClientes);
        contenedorMenu.add(bReportes);
        contenedorMenu.add(bUsuarios);
        contenedorMenu.add(bActivos);

        /**
         * Eventos de botones del menú
         */
        ActionListener escuchaBoton = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                contenedorPrincipal.remove(contenedorVentanas);
                if(e.getSource().equals(bFacturas)){
                    contenedorVentanas = new Facturas();
                }
                if(e.getSource().equals(bPagos)){
                    contenedorVentanas = new Pagos();
                }
                if(e.getSource().equals(bClientes)){
                    contenedorVentanas = new Clientes();
                }
                if(e.getSource().equals(bReportes)){
                    contenedorVentanas = new Reportes();
                }
                if(e.getSource().equals(bUsuarios)){
                    contenedorVentanas = new Usuarios();
                }
                if(e.getSource().equals(bActivos)){
                    contenedorVentanas = new Activos();
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
        bReportes.addActionListener(escuchaBoton);
        bUsuarios.addActionListener(escuchaBoton);
        bActivos.addActionListener(escuchaBoton);

        /**
         * Contenedor ventana dinámica
         */
        contenedorVentanas = new Inicio();
        contenedorVentanas.setLayout(new BoxLayout(contenedorVentanas, BoxLayout.X_AXIS));
        contenedorVentanas.setPreferredSize(DIMENSIONES_CONTENEDOR_VENTANA);
        contenedorPrincipal.add(contenedorVentanas);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Compañia de servicio eléctrico");
        this.setContentPane(contenedorPrincipal);
		this.setLocationRelativeTo(this);
        this.setResizable(false);
        this.setVisible(true);
		this.pack();
    }
}
