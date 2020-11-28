package src.managers.gestionCiudad;
import java.sql.SQLException;

public class GestionCiudadApi {
    private static GestionCiudadLib lib = new GestionCiudadLib();
    
    public static String obtenerCiudades(){
        String resultado  = "";
        try {
            resultado = lib.obtenerCiudades();
        } catch(SQLException excepcion) {
            return excepcion.getMessage();
        }
        return resultado;

    }
    public static String obtenerCiudadPorId(int id){
        String resultado  = "";
        try {
            resultado = lib.obtenerCiudadPorId(id);
        } catch(SQLException excepcion) {
            return excepcion.getMessage();
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
            default:
                mensajeError += "El código de error " + codigo + " no ha sido identificado}";
                break;
        }
        return mensajeError;
    }
}
