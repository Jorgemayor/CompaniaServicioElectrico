package src.control;

import src.vista.Login;
import src.vista.VentanaPrincipal;

import src.managers.PruebasLib;

public class Principal {
    public static void main(String[] args){
        Login login = new Login();
        login.setVisible(true);
        // pruebas
        //new PruebasLib();
    }

    public static void arrancarVentanaPrincipal(Usuario usuario){
        new VentanaPrincipal(usuario.getRol());
        //usuario.generarPdf();
    }
}