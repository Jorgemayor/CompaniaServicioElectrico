package src.vista;

import java.awt.*;
import javax.swing.*;

public class Inicio extends JFrame {

    public Inicio(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel textLabel = new JLabel("holap", SwingConstants.CENTER);
        textLabel.setPreferredSize(new Dimension(300, 100));
        getContentPane().add(textLabel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        pack(); 
        setVisible(true);
    }
}
