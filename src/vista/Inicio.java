package src.vista;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Inicio extends Container {

    public Inicio(){
        JLabel imagenBienvenida = new JLabel();
        imagenBienvenida.setIcon(new ImageIcon("src/assets/inicio.png"));
        imagenBienvenida.setVisible(true);
        this.add(imagenBienvenida, BorderLayout.CENTER);
    }
}
