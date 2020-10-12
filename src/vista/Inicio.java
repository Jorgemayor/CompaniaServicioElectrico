package vista;

import java.awt.*;
import javax.swing.*;


public class Inicio extends JFrame {

    Color bgColor = new Color(232, 234, 246);
    // Para crear una nueva pestaña
    JPanel crearUsuarioPanel = new JPanel();
    JPanel nuevaTabPanel = new JPanel();
    JPanel[] paneles = { crearUsuarioPanel, nuevaTabPanel };

    JLabel agregarUsuario = new JLabel("Agregar usuraio");
    JLabel nuevoPanel = new JLabel("Nuevo panel");

    JTabbedPane tabbedPane = new JTabbedPane();

    ImageIcon img = new ImageIcon("src/assets/icono.png");

    public Inicio() {

        for (int i = 0; i < paneles.length; i++)
            {
             paneles[i].setBackground(bgColor);
            }
        crearUsuarioPanel.add(agregarUsuario);
        nuevaTabPanel.add(nuevoPanel);


        tabbedPane.add("Agregar usuario", crearUsuarioPanel);
        tabbedPane.add("Nueva Tab", nuevaTabPanel);
        add(tabbedPane);
        setIconImage(img.getImage());
        setTitle("Electricaribe");

        }

    }


