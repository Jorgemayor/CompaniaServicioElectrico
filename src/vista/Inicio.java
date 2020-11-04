package src.vista;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Inicio extends Container {

    public Inicio(){
        JLabel textLabel = new JLabel("holap", SwingConstants.CENTER);
        textLabel.setPreferredSize(new Dimension(300, 100));
        this.add(textLabel, BorderLayout.CENTER);
    }
}
