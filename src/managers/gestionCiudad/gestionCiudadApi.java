package src.managers.gestionCiudad;
import java.sql.SQLException;

public class gestionCiudadApi {
    private static gestionCiudadLib lib = new gestionCiudadLib();
    
    public static String obtenerCiudad(){
        String resultado  = "";
        try {
            resultado = lib.obtenerCiudades();
        } catch(SQLException excepcion) {
            return retornarError("-1");
        }
        return resultado;

    }

    private static String retornarError(String codigoExcepcion) {
        String mensajeError = "{ \"code\": ";
        int codigo;

        try {
            codigo = Integer.parseInt(codigoExcepcion);
        } catch (NumberFormatException excepcion) {
            return "Error inesperado: " + codigoExcepcion;
        }

        mensajeError += codigoExcepcion + ",  \"mensaje\": ";

        switch(codigo) {
            case -1:
                mensajeError += "Error al conectarse a la base de datos}";
                break;
            case -2:
                mensajeError += "La ciudad no existe}";
                break;
            default:
                mensajeError += "El código de error " + codigo + " no ha sido identificado}";
                break;
        }
        return mensajeError;
    }
}
