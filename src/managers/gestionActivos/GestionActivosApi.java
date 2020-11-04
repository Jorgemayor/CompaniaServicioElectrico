package src.managers.gestionActivos;

import java.sql.SQLException;

public class GestionActivosApi {
    
    private static GestionActivosLib lib = new GestionActivosLib();

    public static String obtenerActivos(){
        String resultado = "";
        try {
            resultado = lib.obtenerActivos();
        } catch(SQLException excepcion) {

            return retornarError("-1");
        }

        return resultado;
    }
    public static String registrarActivo(String numeroSerie, String nombre, int ciudad, String estado){
        String resultado = "";

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
                resultado = lib.registrarActivo(numeroSerie, nombre,ciudad,estado);
            }

            if(!resultado.equals("0")) {
                throw new Exception(resultado);
            }
        } catch(SQLException excepcion) {
            return retornarError("-1");
        } catch(Exception excepcion) {
            return retornarError(excepcion.getMessage());
        }
        return resultado;
    }
    public static String actualizarActivo(){
        return "";
    }
    public static String cambiarEstadoActivo(int idActivo){
        String resultado = "";

        try {
            if(idActivo < 0) {
                throw new Exception("-5");
            } else {
                resultado = lib.cambiarEstadoActivo(idActivo);
            }
        } catch(SQLException excepcion) {
            return retornarError("-1");
        } catch(Exception excepcion) {
            return retornarError(excepcion.getMessage());
        }
        return resultado;
    }
    public static String retornarError(String codigoExcepcion){
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
                mensajeError += "El numero de serie no puede ser vacio}";
                break;
            case -3:
                mensajeError += "El Estado no puede ser vacio}";
            case -4:
                mensajeError += "Ciudad invalida}";
                break;
            case -5:
                mensajeError += "No existe un Actibo con ese ID}";
                break;
            case -6:
                mensajeError += "Nombre del Activo no puede ser vació}";
                break;
            default:
                mensajeError += "El código de error " + codigo + " no ha sido identificado}";
                break;
        }
        return mensajeError;
    }
}
