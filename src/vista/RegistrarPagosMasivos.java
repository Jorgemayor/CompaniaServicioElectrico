package src.vista;

import java.awt.Container;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class RegistrarPagosMasivos extends Container {

    public RegistrarPagosMasivos() {
        JLabel imagenBienvenida = new JLabel();
        imagenBienvenida.setIcon(new ImageIcon("src/assets/inicio.png"));
        imagenBienvenida.setVisible(true);
        this.add(imagenBienvenida, BorderLayout.CENTER);
    }
}
