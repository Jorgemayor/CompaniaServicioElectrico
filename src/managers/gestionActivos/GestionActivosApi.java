package src.managers.gestionActivos;

import java.sql.SQLException;

import org.json.JSONObject;

public class GestionActivosApi {
    
    private static GestionActivosLib lib = new GestionActivosLib();

    public static String obtenerActivos(){
        JSONObject resultado = new JSONObject();
        String activos = "";
        try {
            activos = lib.obtenerActivos();
        } catch(SQLException excepcion) {

            return retornarError("-1");
        }

        if(activos.equals("{}")){

            return retornarError("-5");
        }
        else{

            resultado.put("activos", activos);
            resultado.put("code", "0");
            return resultado.toString();
        }
    }
    public static String obtenerActivosPorSerial(String serial){
        JSONObject resultado = new JSONObject();
        String activos = "";
        try {
            activos = lib.obtenerActivoPorSerial(serial);
        } catch(SQLException excepcion) {

            return retornarError("-1");
        }
        if(activos.equals("{}")){

            return retornarError("-5");


        }
        else{

            resultado.put("activos", activos);
            resultado.put("code", "0");
            return resultado.toString();
        }
    }
    
    public static String registrarActivo(String numeroSerie, String nombre, int ciudad, String estado){
        JSONObject resultado = new JSONObject();
        String codigo = "";

        try {
            if(numeroSerie.trim().equals("")) {
                throw new Exception("-2");
            } else if(nombre.trim().equals("")) {
                throw new Exception("-6");
            } else if(estado.trim().equals("")) {
                throw new Exception("-3");
            } else if(ciudad < 0) {
                throw new Exception("-4");
            } else {
                codigo = lib.registrarActivo(numeroSerie, nombre,ciudad,estado);
            }

            if(!codigo.equals("0")) {
                throw new Exception(codigo);
            }
        } catch(SQLException excepcion) {
            System.out.println(excepcion.getMessage());
            return retornarError("-1");
        } catch(Exception excepcion) {
            return retornarError(excepcion.getMessage());
        }
        resultado.put("code", codigo);
        return resultado.toString();
    }

    public static String actualizarActivo(int idActivo, String nombre, int ciudad, String estado){
        JSONObject resultado = new JSONObject();
        String codigo = "";

        try {
            if(idActivo < 0) {
                throw new Exception("-5");
            } else 
            {

                codigo = lib.actualizarActivo(idActivo, nombre,ciudad, estado);
            }
            if(!codigo.equals("0")) {
                throw new Exception(codigo);

            }
        } catch(SQLException excepcion) {
            return retornarError("-1");
        } catch(Exception excepcion) {
            return retornarError(excepcion.getMessage());
        }
        resultado.put("code", codigo);
        return resultado.toString();
    }
    public static String cambiarEstadoActivo(int idActivo){
        JSONObject resultado = new JSONObject();
        String codigo = "";

        try {
            if(idActivo < 0) {
                throw new Exception("-5");
            } else {
                codigo = lib.cambiarEstadoActivo(idActivo);
            }
            if(!codigo.equals("0")) {
                throw new Exception(codigo);
            }
        } catch(SQLException excepcion) {
            return retornarError("-1");
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
                mensajeError.put("mensaje", "Error al conectarse a la base de datos");
                break;
            case -2:
                mensajeError.put("mensaje", "El numero de serie no puede ser vacio");
                break;
            case -3:
                mensajeError.put("mensaje", "El Estado no puede ser vacio");
                break;
            case -4:
                mensajeError.put("mensaje","Ciudad invalida");
                break;
            case -5:
                mensajeError.put("mensaje", "No existe ese Activo");
                break;
            case -6:
                mensajeError.put("mensaje", "Nombre del Activo no puede ser vació");
                break;
            case -7:
                mensajeError.put("mensaje","Nombre del Activo en uso, usa uno diferente");
                break;
            default:
                mensajeError.put("mensaje", "El código de error " + codigo + " no ha sido identificado");
                break;
        }
        return mensajeError.toString();
    }
}
