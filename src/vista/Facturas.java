package src.vista;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Facturas extends Container {

    public Facturas(){
        JLabel textLabel = new JLabel("Facturas", SwingConstants.CENTER);
        textLabel.setVisible(true);
        textLabel.setPreferredSize(new Dimension(300, 100));
        this.add(textLabel, BorderLayout.CENTER);
    }
}