package control;

import vista.Login;

import javax.swing.JFrame;

import vista.Inicio;

public class Principal {
    public static void main(String[] args){
        Login login = new Login();
        login.setVisible(true);
    }

    public static void arrancarVentanaPrincipal(Usuario usuario){
        Inicio tp = new Inicio();
        tp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tp.setSize(600,400);
        tp.setVisible(true);

    }
}