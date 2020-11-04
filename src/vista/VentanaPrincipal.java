package src.vista;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

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
         * Contenedor ventana dinámica
         */
        contenedorVentanas = new Inicio();
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
