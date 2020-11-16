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
    private static final Color COLOR_FONDO = new Color(232,234,246);

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
    private JMenuItem salir;

    private JMenuItem accionConsultarUsuario;
    private JMenuItem accionModificarUsuario;
    private JMenuItem accionDeshabilitarUsuario;

    private JMenuItem accionConsultarCliente;
    private JMenuItem accionModificarCliente;
    private JMenuItem accionDeshabilitarCliente;

    private JMenuItem accionConsultarActivo;
    private JMenuItem accionModificarActivo;
    private JMenuItem accionDeshabilitarActivo;

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
        accionConsultarUsuario = new JMenuItem("Consultar usuarios");
        accionModificarUsuario = new JMenuItem("Modificar usuarios");
        accionDeshabilitarUsuario = new JMenuItem("Deshabilitar usuarios");
        accionConsultarUsuario.addActionListener(escucha);
        accionModificarUsuario.addActionListener(escucha);
        accionDeshabilitarUsuario.addActionListener(escucha);
        menuUsuarios.add(accionConsultarUsuario);
        menuUsuarios.add(accionModificarUsuario);
        menuUsuarios.add(accionDeshabilitarUsuario);
        
        menuClientes = new JMenu("Clientes");
        accionConsultarCliente = new JMenuItem("Consultar clientes");
        accionModificarCliente = new JMenuItem("Modificar clientes");
        accionDeshabilitarCliente = new JMenuItem("Deshabilitar clientes");
        accionConsultarCliente.addActionListener(escucha);
        accionModificarCliente.addActionListener(escucha);
        accionDeshabilitarCliente.addActionListener(escucha);
        menuClientes.add(accionConsultarCliente);
        menuClientes.add(accionModificarCliente);
        menuClientes.add(accionDeshabilitarCliente);

        menuPagos = new JMenu("Pagos");
        
        menuFacturas = new JMenu("Facturas");
        
        menuReportes = new JMenu("Reportes");

        menuActivos = new JMenu("Activos");
        accionConsultarActivo = new JMenuItem("Consultar activos");
        accionModificarActivo = new JMenuItem("Modificar activos");
        accionDeshabilitarActivo = new JMenuItem("Deshabilitar activos");
        accionConsultarActivo.addActionListener(escucha);
        accionModificarActivo.addActionListener(escucha);
        accionDeshabilitarActivo.addActionListener(escucha);
        menuActivos.add(accionConsultarActivo);
        menuActivos.add(accionModificarActivo);
        menuActivos.add(accionDeshabilitarActivo);

        menuConfiguracion = new JMenu("Configuración");

        salir = new JMenuItem("Salir");
        salir.addActionListener(escucha);

        switch (rol) {
            case 1: //admin
                menuDesplegable.add(menuUsuarios);
                menuDesplegable.add(menuActivos);
                menuDesplegable.add(menuConfiguracion);
                menuDesplegable.add(salir);
                break;

            case 2: //gerente
                menuDesplegable.add(menuReportes);
                menuDesplegable.add(salir);
                break;

            case 3: //operador
                menuDesplegable.add(menuFacturas);
                menuDesplegable.add(menuPagos);
                menuDesplegable.add(menuClientes);
                menuDesplegable.add(salir);
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

            if(itemPresionado.equals(salir)) {
                int respuesta = JOptionPane.showConfirmDialog(null, "Realmente desea salir?", "Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(respuesta == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
            else{
                contenedorPrincipal.remove(contenedorVentanas);
            }

            if(itemPresionado.equals(accionConsultarActivo)){
                contenedorVentanas = new ConsultarActivos();
                contenedorVentanas.setLayout(new BoxLayout(contenedorVentanas, BoxLayout.X_AXIS));
                contenedorVentanas.setPreferredSize(DIMENSIONES_CONTENEDOR_VENTANA);
            }
            else if(itemPresionado.equals(accionModificarActivo)){
                contenedorVentanas = new ModificarActivos();
                contenedorVentanas.setLayout(new BoxLayout(contenedorVentanas, BoxLayout.X_AXIS));
                contenedorVentanas.setPreferredSize(DIMENSIONES_CONTENEDOR_VENTANA);
            }
            else if(itemPresionado.equals(accionDeshabilitarActivo)){
                contenedorVentanas = new Clientes();
                contenedorVentanas.setLayout(new BoxLayout(contenedorVentanas, BoxLayout.X_AXIS));
                contenedorVentanas.setPreferredSize(DIMENSIONES_CONTENEDOR_VENTANA);
            }
            contenedorVentanas.setLayout(new BoxLayout(contenedorVentanas, BoxLayout.X_AXIS));
            contenedorPrincipal.add(contenedorVentanas);
            contenedorPrincipal.validate();
            contenedorPrincipal.repaint();
        }
    }
}
