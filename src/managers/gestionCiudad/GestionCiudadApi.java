package src.managers.gestionCiudad;

import java.sql.SQLException;

import org.json.JSONObject;

public class GestionCiudadApi {
    private static GestionCiudadLib lib = new GestionCiudadLib();
    
    public static String obtenerCiudades(){
        String resultado  = "";
        try {
            resultado = lib.obtenerCiudades();
        } catch(SQLException excepcion) {
            System.out.print(excepcion.getMessage());
            return retornarError("-1");
        }
        return resultado;
    }

    public static String obtenerCiudadPorId(int id){
        String resultado  = "";
        try {
            resultado = lib.obtenerCiudadPorId(id);
        } catch(SQLException excepcion) {
            System.out.print(excepcion.getMessage());
            return retornarError("-1");
        }
        return resultado;
    }

    public static String retornarError(String codigoExcepcion){

        JSONObject mensajeError = new JSONObject();
        mensajeError.put("code", codigoExcepcion);
        int codigo;
        
        try {
            codigo = Integer.parseInt(codigoExcepcion);
        } catch (NumberFormatException excepcion) {
            codigo = -99;
        }

        switch(codigo) {
            case -1:
                mensajeError.put("mensaje", "Error en la base de datos");
                break;
            default:
                mensajeError.put("mensaje", "El código de error " + codigo + " no ha sido identificado");
                break;
        }
        return mensajeError.toString();
    }
}
