package src.managers.gestionLectura;

import java.sql.SQLException;

import org.json.JSONObject;

public class GestionLecturaApi {
    
    private static GestionLecturaLib lib = new GestionLecturaLib();

    public static String obtenerLecturasPorCliente(String identificacionCliente) {

        JSONObject resultado = new JSONObject();
        String lecturas = "";

        try {
            if(identificacionCliente.trim().equals("")) {
                throw new Exception("-2");
            }

            int valorIdentificacionCliente = Integer.parseInt(identificacionCliente);

            if(valorIdentificacionCliente < 0) {
                throw new Exception("-6");
            } else {
                lecturas = lib.obtenerLecturasPorCliente(valorIdentificacionCliente);
            }

            if(lecturas.equals("{}")) {
                throw new Exception("-7");
            }
        } catch(SQLException excepcion) {
            System.out.println(excepcion.getMessage());
            return retornarError("-1");
        } catch (NumberFormatException excepcion) {
            System.out.println(excepcion.getMessage());
            return retornarError("-5");
        } catch(Exception excepcion) {
            return retornarError(excepcion.getMessage());
        }

        resultado.put("code", "0");
        return resultado.toString();
    }

    public static String obtenerUltimaLecturaPorCliente(String identificacionCliente) {

        JSONObject resultado = new JSONObject();
        String lectura = "";

        try {
            if(identificacionCliente.trim().equals("")) {
                throw new Exception("-2");
            }

            int valorIdentificacionCliente = Integer.parseInt(identificacionCliente);

            if(valorIdentificacionCliente < 0) {
                throw new Exception("-6");
            } else {
                lectura = lib.obtenerUltimaLecturaPorCliente(valorIdentificacionCliente);
            }

            if(lectura.equals("{}")) {
                throw new Exception("-7");
            }
        } catch(SQLException excepcion) {
            System.out.println(excepcion.getMessage());
            return retornarError("-1");
        } catch (NumberFormatException excepcion) {
            System.out.println(excepcion.getMessage());
            return retornarError("-5");
        } catch(Exception excepcion) {
            return retornarError(excepcion.getMessage());
        }

        resultado.put("code", "0");
        return resultado.toString();
    }

    /**
     * ingresarLectura
     * 
     * Función encargada de registrar una lectura de consumo
     * realizada.
     * 
     * @param identificacionCliente identificación del cliente.
     * @param fecha fecha de realización de la lectura.
     * @param consumo valor del consumo leído.
     * 
     * @return string con el código del resultado de la operación.
     */
    public static String ingresarLectura(String identificacionCliente, String fecha, String consumo) {

        JSONObject resultado = new JSONObject();
        String codigo = "";

        try {
            if(identificacionCliente.trim().equals("")) {
                throw new Exception("-2");
            } else if(fecha.trim().equals("")) {
                throw new Exception("-3");
            } else if(consumo.trim().equals("")) {
                throw new Exception("-4");
            }
            
            int valorConsumo = Integer.parseInt(consumo);
            int valorIdentificacionCliente = Integer.parseInt(identificacionCliente);

            if(valorConsumo < 0) {
                throw new Exception("-6");
            } else {
                codigo = lib.ingresarLectura(valorIdentificacionCliente, fecha, valorConsumo);
            }

            if(!codigo.equals("0")) {
                throw new Exception(codigo);
            }
        } catch(SQLException excepcion) {
            System.out.println(excepcion.getMessage());
            return retornarError("-1");
        } catch (NumberFormatException excepcion) {
            System.out.println(excepcion.getMessage());
            return retornarError("-5");
        } catch(Exception excepcion) {
            return retornarError(excepcion.getMessage());
        }

        resultado.put("code", codigo);
        return resultado.toString();
    }

    /**
     * retornarError
     * 
     * Función encargada de recibir un código determinando
     * el error que ocurrió.
     * 
     * @param codigoExcepcion código especificando el error ocurrido.
     * 
     * @return String en formato JSON con las características del error.
     */
    private static String retornarError(String codigoExcepcion) {

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
                mensajeError.put("mensaje", "Identificación del cliente inválida");
                break;
            case -3:
                mensajeError.put("mensaje", "Debe introducir una fecha de lectura");
                break;
            case -4:
                mensajeError.put("mensaje", "Debe introducir un consumo");
                break;
            case -5:
                mensajeError.put("mensaje", "El consumo debe ser numérico");
                break;
            case -6:
                mensajeError.put("mensaje", "El consumo no puede ser un valor negativo");
                break;
            case -7:
                mensajeError.put("mensaje", "No se encontraron resultados");
                break;
            default:
                mensajeError.put("mensaje", "El código de error " + codigo + " no ha sido identificado");
                break;
        }
        return mensajeError.toString();
    }
}
