package src.control;

import src.vista.Login;
import src.vista.VentanaPrincipal;

public class Principal {
    public static void main(String[] args){
        Login login = new Login();
        login.setVisible(true);
    }

    public static void arrancarVentanaPrincipal(Usuario usuario){
        new VentanaPrincipal();
        //usuario.generarPdf();
    }
}