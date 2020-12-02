package src.managers.gestionDeuda;

import java.sql.SQLException;

import org.json.JSONObject;

public class GestionDeudaApi {
    
    private static GestionDeudaLib lib = new GestionDeudaLib();

    public String registrarDeuda(String identificacionCliente) {

        JSONObject resultado = new JSONObject();
        String codigo = "";

        try {
            if(identificacionCliente.trim().equals("")) {
                throw new Exception("-2");
            }

            int valorIdentificacionCliente = Integer.parseInt(identificacionCliente);
            
            if(valorIdentificacionCliente < 0) {
                throw new Exception("-3");
            }

            codigo = lib.registrarDeuda(valorIdentificacionCliente);

            if(!codigo.equals("0")) {
                return retornarError(codigo);
            } else {
                resultado.put("code", codigo);
                return resultado.toString();
            }
        } catch(SQLException excepcion) {
            return retornarError("-1");
        } catch(Exception excepcion) {
            return retornarError("-99");
        }
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
                mensajeError.put("mensaje", "Debe especificar una identificación");
                break;
            case -3:
                mensajeError.put("mensaje", "Identificación inválida");
                break;
            case -4:
                mensajeError.put("mensaje","El cliente no existe");
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
