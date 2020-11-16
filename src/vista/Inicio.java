package src.vista;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.BorderLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Inicio extends Container {

    public Inicio() {
        JLabel imagenBienvenida = new JLabel();
        imagenBienvenida.setSize(new Dimension(1280, 575));
        ImageIcon laImagen = new ImageIcon("src/assets/inicio.png");
        Icon imagenAjustada = new ImageIcon(laImagen.getImage().getScaledInstance(imagenBienvenida.getWidth(),imagenBienvenida.getHeight(),Image.SCALE_DEFAULT));
        imagenBienvenida.setIcon(imagenAjustada);
        imagenBienvenida.setVisible(true);
        this.add(imagenBienvenida, BorderLayout.CENTER);
    }
}
