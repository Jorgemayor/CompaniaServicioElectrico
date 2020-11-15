package src.vista;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Configuracion extends Container {

    public Configuracion(){
        JLabel textLabel = new JLabel("Configuracion", SwingConstants.CENTER);
        textLabel.setVisible(true);
        textLabel.setPreferredSize(new Dimension(300, 100));
        this.add(textLabel, BorderLayout.CENTER);
    }
}