package src.managers.gestionClientes;

import java.sql.SQLException;

public class GestionClienteApi {
    
    private static GestionClienteLib lib = new GestionClienteLib();

    public static String obtenerClientes(){
        String resultado = "";
        try {
            resultado = lib.obtenerClientes();
        } catch(SQLException excepcion) {

            return retornarError("-1");
        }

        return resultado;
    }
    public static String agregarCliente(String tipoIdentificacion, int identificacion, String nombre, String direccion, int ciudad){

        String resultado = "";

        try {
            if(tipoIdentificacion.trim().equals("")) {
                throw new Exception("-2");
            } else if(nombre.trim().equals("")) {
                throw new Exception("-6");
            } else if(direccion.trim().equals("")) {
                throw new Exception("-3");
            } else if(ciudad < 0) {
                throw new Exception("-4");
            } else {
                resultado = lib.agregarCliente(tipoIdentificacion, identificacion, nombre, direccion, ciudad);
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


    public static boolean actualizarCliente(int idCliente){
        return true;
    }
    public static String cambiarEstadoCliente(int idCliente){
        String resultado = "";

        try {
            if(idCliente < 0) {
                throw new Exception("-5");
            } else {
                resultado = lib.cambiarEstadoCliente(idCliente);
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
                mensajeError += "El Tipo de Identificaicón no puede ser vacio}";
                break;
            case -3:
                mensajeError += "La Direccion no puede ser vacio}";
            case -4:
                mensajeError += "Ciudad invalida}";
                break;
            case -5:
                mensajeError += "No existe un Cliente con ese ID}";
                break;
            case -6:
                mensajeError += "Nombre del cliente no puede ser vació}";
                break;
            case -7:
                mensajeError += "Nombre del cliente en uso, usa uno diferente}";
                break;
            default:
                mensajeError += "El código de error " + codigo + " no ha sido identificado}";
                break;
        }
        return mensajeError;
    }


}