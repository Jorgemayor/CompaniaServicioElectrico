package src.vista;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class VentanaPrincipal extends JFrame {

    private static final Dimension DIMENSIONES_VENTANA = new Dimension(1280, 720);
    private static final Dimension DIMENSIONES_CONTENEDOR_LOGO = new Dimension(1280, 120);
    private static final Dimension DIMENSIONES_CONTENEDOR_MENU = new Dimension(1280, 25);
    private static final Dimension DIMENSIONES_CONTENEDOR_VENTANA = new Dimension(1280, 575);

    private Container contenedorPrincipal;
    private Container contenedorVentanas;
    private Container contenedorMenu;
    private Container contenedorLogo;

    private BoxLayout esquemaPrincipal;
    private BoxLayout esquemaMenu;
    private BoxLayout esquemaLogo;

    private Escucha escucha;

    private JLabel iconLabel;

    private JMenuBar menuDesplegable;
    private JMenu menuUsuarios;
    private JMenu menuClientes;
    private JMenu menuPagos;
    private JMenu menuFacturas;
    private JMenu menuReportes;
    private JMenu menuActivos;
    private JMenu menuConfiguracion;
    private JMenu menuSalir;

    private JMenuItem accionCrearUsuario;
    private JMenuItem accionConsultarUsuario;
    private JMenuItem accionModificarUsuario;
    private JMenuItem accionDeshabilitarUsuario;

    private JMenuItem accionCrearCliente;
    private JMenuItem accionConsultarCliente;
    private JMenuItem accionModificarCliente;
    private JMenuItem accionDeshabilitarCliente;

    private JMenuItem accionRegistrarPagoIndividual;
    private JMenuItem accionRegistrarPagosMasivos;

    private JMenuItem accionGenerarFacturaIndividual;
    private JMenuItem accionGenerarFacturasMasivas;

    private JMenuItem accionGenerarReporteIndividual;
    private JMenuItem accionGenerarReportesMasivos;

    private JMenuItem accionCrearActivo;
    private JMenuItem accionConsultarActivo;
    private JMenuItem accionModificarActivo;
    private JMenuItem accionDeshabilitarActivo;

    private JMenuItem accionConfigurar;

    private JMenuItem accionCerrarSesion;
    private JMenuItem accionSalir;

    public VentanaPrincipal(int rol) {
        this.setPreferredSize(DIMENSIONES_VENTANA);
        this.setBackground(Color.WHITE);
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

        // Logo
        iconLabel = new JLabel();
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

        escucha = new Escucha();

        /**
         * Menú desplegable
         */
        menuDesplegable = new JMenuBar();

        menuUsuarios = new JMenu("Usuarios");
        accionCrearUsuario = new JMenuItem("Crear usuario");
        accionConsultarUsuario = new JMenuItem("Consultar usuarios");
        accionModificarUsuario = new JMenuItem("Modificar usuarios");
        accionDeshabilitarUsuario = new JMenuItem("Deshabilitar usuarios");
        accionCrearUsuario.addActionListener(escucha);
        accionConsultarUsuario.addActionListener(escucha);
        accionModificarUsuario.addActionListener(escucha);
        accionDeshabilitarUsuario.addActionListener(escucha);
        menuUsuarios.add(accionCrearUsuario);
        menuUsuarios.add(accionConsultarUsuario);
        menuUsuarios.add(accionModificarUsuario);
        menuUsuarios.add(accionDeshabilitarUsuario);
        
        menuClientes = new JMenu("Clientes");
        accionCrearCliente = new JMenuItem("Crear cliente");
        accionConsultarCliente = new JMenuItem("Consultar clientes");
        accionModificarCliente = new JMenuItem("Modificar clientes");
        accionDeshabilitarCliente = new JMenuItem("Deshabilitar clientes");
        accionCrearCliente.addActionListener(escucha);
        accionConsultarCliente.addActionListener(escucha);
        accionModificarCliente.addActionListener(escucha);
        accionDeshabilitarCliente.addActionListener(escucha);
        menuClientes.add(accionCrearCliente);
        menuClientes.add(accionConsultarCliente);
        menuClientes.add(accionModificarCliente);
        menuClientes.add(accionDeshabilitarCliente);

        menuPagos = new JMenu("Pagos");
        accionRegistrarPagoIndividual = new JMenuItem("Registrar pago individual");
        accionRegistrarPagosMasivos = new JMenuItem("Registrar pagos masivos");
        accionRegistrarPagoIndividual.addActionListener(escucha);
        accionRegistrarPagosMasivos.addActionListener(escucha);
        menuPagos.add(accionRegistrarPagoIndividual);
        menuPagos.add(accionRegistrarPagosMasivos);
        
        menuFacturas = new JMenu("Facturas");
        accionGenerarFacturaIndividual = new JMenuItem("Generar facturas individual");
        accionGenerarFacturasMasivas = new JMenuItem("Generar facturas masivos");
        accionGenerarFacturaIndividual.addActionListener(escucha);
        accionGenerarFacturasMasivas.addActionListener(escucha);
        menuFacturas.add(accionGenerarFacturaIndividual);
        menuFacturas.add(accionGenerarFacturasMasivas);
        
        menuReportes = new JMenu("Reportes");
        accionGenerarReporteIndividual = new JMenuItem("Generar reporte individual");
        accionGenerarReportesMasivos = new JMenuItem("Generar reportes masivos");
        accionGenerarReporteIndividual.addActionListener(escucha);
        accionGenerarReportesMasivos.addActionListener(escucha);
        menuReportes.add(accionGenerarReporteIndividual);
        menuReportes.add(accionGenerarReportesMasivos);

        menuActivos = new JMenu("Activos");
        accionCrearActivo = new JMenuItem("Crear activo");
        accionConsultarActivo = new JMenuItem("Consultar activos");
        accionModificarActivo = new JMenuItem("Modificar activos");
        accionDeshabilitarActivo = new JMenuItem("Deshabilitar activos");
        accionCrearActivo.addActionListener(escucha);
        accionConsultarActivo.addActionListener(escucha);
        accionModificarActivo.addActionListener(escucha);
        accionDeshabilitarActivo.addActionListener(escucha);
        menuActivos.add(accionCrearActivo);
        menuActivos.add(accionConsultarActivo);
        menuActivos.add(accionModificarActivo);
        menuActivos.add(accionDeshabilitarActivo);

        menuConfiguracion = new JMenu("Configuración");
        accionConfigurar = new JMenuItem("Configurar");
        accionConfigurar.addActionListener(escucha);
        menuConfiguracion.add(accionConfigurar);

        menuSalir = new JMenu("Salir");
        accionCerrarSesion = new JMenuItem("Cerrar sesión");
        accionSalir = new JMenuItem("Salir");
        accionCerrarSesion.addActionListener(escucha);
        accionSalir.addActionListener(escucha);
        menuSalir.add(accionCerrarSesion);
        menuSalir.add(accionSalir);

        switch (rol) {
            case 1: //admin
                menuDesplegable.add(menuUsuarios);
                menuDesplegable.add(menuActivos);
                menuDesplegable.add(menuConfiguracion);
                menuDesplegable.add(menuSalir);
                break;

            case 2: //gerente
                menuDesplegable.add(menuReportes);
                menuDesplegable.add(menuSalir);
                break;

            case 3: //operador
                menuDesplegable.add(menuFacturas);
                menuDesplegable.add(menuPagos);
                menuDesplegable.add(menuClientes);
                menuDesplegable.add(menuSalir);
            break;
        }

        contenedorMenu.add(menuDesplegable);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Compañia de servicio eléctrico");
        this.setContentPane(contenedorPrincipal);
        this.setResizable(false);
        this.setVisible(true);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    private class Escucha implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent evento) {

            JMenuItem itemPresionado = (JMenuItem) evento.getSource();

            if(itemPresionado.equals(accionSalir)) {
                int respuesta = JOptionPane.showConfirmDialog(null, "Realmente desea salir?", "Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(respuesta == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            } else if(itemPresionado.equals(accionCerrarSesion)) {
                System.out.print("Cerrando sesión");
            } else {
                contenedorPrincipal.remove(contenedorVentanas);
            }

            if(itemPresionado.equals(accionCrearUsuario)) {
                contenedorVentanas = new CrearUsuario();
            } else if(itemPresionado.equals(accionConsultarUsuario)) {
                contenedorVentanas = new ConsultarUsuario();
            } else if(itemPresionado.equals(accionModificarUsuario)) {
                contenedorVentanas = new ModificarUsuario();
            } else if(itemPresionado.equals(accionDeshabilitarUsuario)) {
                contenedorVentanas = new DeshabilitarUsuario();
            } else if(itemPresionado.equals(accionCrearCliente)) {
                contenedorVentanas = new CrearCliente();
            } else if(itemPresionado.equals(accionConsultarCliente)) {
                contenedorVentanas = new ConsultarCliente();
            } else if(itemPresionado.equals(accionModificarCliente)) {
                contenedorVentanas = new ModificarCliente();
            } else if(itemPresionado.equals(accionDeshabilitarCliente)) {
                contenedorVentanas = new DeshabilitarCliente();
            } else if(itemPresionado.equals(accionRegistrarPagoIndividual)) {
                contenedorVentanas = new RegistrarPagoIndividual();
            } else if(itemPresionado.equals(accionRegistrarPagosMasivos)) {
                contenedorVentanas = new RegistrarPagosMasivos();
            } else if(itemPresionado.equals(accionGenerarFacturaIndividual)) {
                contenedorVentanas = new GenerarFacturaIndividual();
            } else if(itemPresionado.equals(accionGenerarFacturasMasivas)) {
                contenedorVentanas = new GenerarFacturasMasivas();
            } else if(itemPresionado.equals(accionGenerarReporteIndividual)) {
                contenedorVentanas = new GenerarReporteIndividual();
            } else if(itemPresionado.equals(accionGenerarReportesMasivos)) {
                contenedorVentanas = new GenerarReportesMasivos();
            } else if(itemPresionado.equals(accionCrearActivo)) {
                contenedorVentanas = new CrearActivo();
            } else if(itemPresionado.equals(accionConsultarActivo)) {
                contenedorVentanas = new ConsultarActivo();
            } else if(itemPresionado.equals(accionModificarActivo)) {
                contenedorVentanas = new ModificarActivo();
            } else if(itemPresionado.equals(accionDeshabilitarActivo)) {
                contenedorVentanas = new DeshabilitarActivo();
            } else if(itemPresionado.equals(accionConfigurar)) {
                contenedorVentanas = new Configurar();
            }

            contenedorVentanas.setLayout(new BoxLayout(contenedorVentanas, BoxLayout.X_AXIS));
            contenedorVentanas.setPreferredSize(DIMENSIONES_CONTENEDOR_VENTANA);
            contenedorPrincipal.add(contenedorVentanas);
            contenedorPrincipal.validate();
            contenedorPrincipal.repaint();
        }
    }
}
