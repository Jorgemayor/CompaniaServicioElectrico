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
                throw new Exception("-3");
            }

            int valorIdentificacionCliente = Integer.parseInt(identificacionCliente);
            
            if(valorIdentificacionCliente < 0) {
                throw new Exception("-6");
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
            return retornarError("-1");
        } catch(Exception excepcion) {
            return retornarError("-99");
        }
    }
    
    public static String generarFactura(String identificacionCliente) {
        
        JSONObject resultado = new JSONObject();
        String codigo = "";

        try {
            if(identificacionCliente.trim().equals("")) {
                throw new Exception("-3");
            }

            int valorIdentificacionCliente = Integer.parseInt(identificacionCliente);
            
            if(valorIdentificacionCliente < 0) {
                throw new Exception("-6");
            } else {
                codigo = lib.generarFactura(valorIdentificacionCliente);
            }

            if(!codigo.equals("0")) {
                throw new Exception(codigo);
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
            case -7:
                mensajeError.put("mensaje","");
                break;
            default:
                mensajeError.put("mensaje", "El código de error " + codigo + " no ha sido identificado");
                break;
        }
        return mensajeError.toString();
    }
}
