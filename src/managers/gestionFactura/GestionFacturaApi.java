package src.managers.gestionFactura;

import java.sql.SQLException;

import org.json.JSONObject;

public class GestionFacturaApi {
    
    private static GestionFacturaLib lib = new GestionFacturaLib();

    public static String obtenerFacturasPorCliente(String identificacionCliente) {

        JSONObject resultado = new JSONObject();
        String facturas = "";

        try {
            if(identificacionCliente.trim().equals("")) {
                return retornarError("-3");
            }

            int valorIdentificacionCliente = Integer.parseInt(identificacionCliente);
            
            if(valorIdentificacionCliente < 0) {
                return retornarError("-6");
            }

            facturas = lib.obtenerFacturasPorCliente(valorIdentificacionCliente);

            if(facturas.equals("{}")) {
                return retornarError("-2");
            } else {
                resultado.put("facturas", facturas);
                resultado.put("code", "0");
                return resultado.toString();
            }
        } catch(SQLException excepcion) {
            System.out.println(excepcion.getMessage());
            return retornarError("-1");
        } catch(Exception excepcion) {
            System.out.println(excepcion.getMessage());
            return retornarError("-99");
        }
    }
    
    public static String generarFactura(String identificacionCliente) {
        
        JSONObject resultado = new JSONObject();
        String codigo = "";

        try {
            if(identificacionCliente.trim().equals("")) {
                return retornarError("-3");
            }

            int valorIdentificacionCliente = Integer.parseInt(identificacionCliente);
            
            if(valorIdentificacionCliente < 0) {
                return retornarError("-6");
            } else {
                codigo = lib.generarFactura(valorIdentificacionCliente);
            }

            if(!codigo.equals("0")) {
                return retornarError(codigo);
            }
        } catch(SQLException excepcion) {
            System.out.println(excepcion.getMessage());
            return retornarError("-1");
        } catch(Exception excepcion) {
            System.out.println(excepcion.getMessage());
            return retornarError("-99");
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
                mensajeError.put("mensaje", "Error en la base de datos");
                break;
            case -2:
                mensajeError.put("mensaje", "No se encontraron resultados");
                break;
            case -3:
                mensajeError.put("mensaje", "Debe especificar un cliente");
                break;
            case -4:
                mensajeError.put("mensaje","Debe seleccionar una lectura");
                break;
            case -5:
                mensajeError.put("mensaje", "Lectura inválida");
                break;
            case -6:
                mensajeError.put("mensaje", "Cliente inválido");
                break;
            default:
                mensajeError.put("mensaje", "Error inesperado");
                break;
        }
        return mensajeError.toString();
    }
}
