package src.managers.gestionConfiguracion;

import java.sql.SQLException;

import org.json.JSONObject;

public class GestionConfiguracionApi {

    private static GestionConfiguracionLib lib = new GestionConfiguracionLib();

    public static String obtenerParametros(){
        JSONObject resultado = new JSONObject();
        String parametros = "";
        try {
            parametros = lib.obtenerParametros();
        } catch(SQLException excepcion) {
            return retornarError("-3");
        }
        if(parametros.equals("{}")){
            return retornarError("-1");
        }
        else{
            resultado.put("code", "0");
            resultado.put("parametros", parametros);
            return resultado.toString();
        }
    }


    public static String actualizarParametros(int kwh, int reconexion) {
        JSONObject resultado = new JSONObject();
        String codigo = "";

        try {
            if(kwh < 0 || reconexion < 0) {
                throw new Exception("-2");
            } else {
                codigo = lib.actualizarParametros(kwh,reconexion);
            }
            if(!codigo.equals("0")) {
                throw new Exception(codigo);
            }
        } catch(SQLException excepcion) {
            System.out.println(excepcion);
            return retornarError("-3");
        } catch(Exception excepcion) {
            return retornarError(excepcion.getMessage());
        }
        resultado.put("code", codigo);
        return resultado.toString();
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
                mensajeError.put("mensaje", "No hay parametros en la base de datos");
                break;
            case -2:
                mensajeError.put("mensaje", "Los parametros no pueden ser vacios");
                break;
            case -3:
                mensajeError.put("mensaje", "Error al conectarse a la base de datos");
                break;
            default:
                mensajeError.put("mensaje", "El código de error " + codigo + " no ha sido identificado");
                break;
        }
        return mensajeError.toString();
    }
    
}
