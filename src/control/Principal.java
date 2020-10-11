package control;

import vista.Login;
import vista.Inicio;

public class Principal {
    public static void main(String[] args){
        Login login = new Login();
        login.setVisible(true);
    }

    public static void arrancarVentanaPrincipal(Usuario usuario){
        new Inicio();
    }
}