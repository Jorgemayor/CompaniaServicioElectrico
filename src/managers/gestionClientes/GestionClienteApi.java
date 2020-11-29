package src.managers.gestionClientes;

import java.sql.SQLException;

import org.json.JSONObject;

public class GestionClienteApi {
    
    private static GestionClienteLib lib = new GestionClienteLib();

    public static String obtenerClientes(){
        JSONObject resultado = new JSONObject();
        String clientes = "";
        try {
            clientes = lib.obtenerClientes();
        } catch(SQLException excepcion) {

            return retornarError("-1");
        }
        if(clientes.equals("{}")){

            return retornarError("-5");
        }
        else{
            resultado.put("clientes", clientes);
            resultado.put("code", "0");
            return resultado.toString();
        }
    }

    public static String obtenerClientePorId(String id) {
        if(id.equals("")){
            return retornarError("-2");
        }
        JSONObject resultado = new JSONObject();
        String cliente = "";
        try {
            cliente = lib.obtenerClientePorId(Integer.parseInt(id));
        } catch(SQLException excepcion) {
            return retornarError("-1");
        }
        if(cliente.equals("{}")){

            return retornarError("-5");
        }
        else{
            resultado.put("cliente", cliente);
            resultado.put("code", "0");
            return resultado.toString();
        }
    }

    public static String buscarEnTodosLosClientes(String id) {
        if(id.equals("")){
            return retornarError("-2");
        }
        JSONObject resultado = new JSONObject();
        String cliente = "";
        try {
            cliente = lib.buscarEnTodosLosClientes(Integer.parseInt(id));
        } catch(SQLException excepcion) {
            return retornarError("-1");
        }
        if(activo.equals("{}")){

            return retornarError("-5");
        }
        else{
            resultado.put("cliente", cliente);
            resultado.put("code", "0");
            return resultado.toString();
        }
    }
    public static String agregarCliente(String tipoIdentificacion, int identificacion, String nombre, String direccion, int ciudad){

        JSONObject resultado = new JSONObject();
        String codigo = "";

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
                codigo = lib.agregarCliente(tipoIdentificacion, identificacion, nombre, direccion, ciudad);
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


    public static String actualizarCliente(String tipoIdentificacion, int identificacion, String nombre,
            String direccion, int ciudad) {
        JSONObject resultado = new JSONObject();
        String codigo = "";

        try {
            if(identificacion < 0) {
                throw new Exception("-2");
            } else {
                codigo = lib.actualizarCliente(tipoIdentificacion, identificacion, nombre, direccion, ciudad);
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
    public static String cambiarEstadoCliente(int idCliente){
        JSONObject resultado = new JSONObject();
        String codigo = "";

        try {
            if(idCliente < 0) {
                throw new Exception("-5");
            } else {
                codigo = lib.cambiarEstadoCliente(idCliente);
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
                mensajeError.put("mensaje", "El Tipo de Identificaicón no puede ser vacio");
                break;
            case -3:
                mensajeError.put("mensaje", "La Direccion no puede ser vacio");
            case -4:
                mensajeError.put("mensaje", "Ciudad invalida");
                break;
            case -5:
                mensajeError.put("mensaje", "No existe ese Cliente");
                break;
            case -6:
                mensajeError.put("mensaje", "Nombre del cliente no puede ser vació");
                break;
            case -7:
                mensajeError.put("mensaje", "La identificación suministrada está en uso, usa uno diferente");
                break;
            default:
                mensajeError.put("mensaje", "El código de error " + codigo + " no ha sido identificado");
                break;
        }
        return mensajeError.toString();
    }


}