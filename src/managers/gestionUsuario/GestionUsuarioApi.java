package src.managers.gestionUsuario;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestionUsuarioApi {
    
    private static GestionUsuarioLib lib = new GestionUsuarioLib();

    /**
     * logearUsuario
     * 
     * 
     * @return
     */

    public static String loguearUsuario(String usuarioIntento, String claveIntento) {

        String resultado = "-99";

        if(claveIntento.trim().equals("")) {
            return retornarError("-7");
        } else if(usuarioIntento.trim().equals("")) {
            return retornarError("-6");
        }
        
        try {
            resultado = lib.ingresar(usuarioIntento, claveIntento);
        } catch (SQLException excepcion) {
            Logger.getLogger(GestionUsuarioLib.class.getName()).log(Level.SEVERE, null, excepcion);
            return retornarError("-1");
        }

        if(resultado.equals("0")) {
            return "OK";
        }
        return retornarError("-99");
    }

    /**
     * crearUsuario
     * 
     * Función encargada de validar los datos para consultar todos
     * los usuario.
     * 
     * @param id_usuario identificador del usuario a inhabilitar.
     * 
     * @return booleano indicando si se actualizó (true) o no (false).
     */
    public static String obtenerUsuarios() {

        String resultado = "";
        try {
            resultado = lib.obtenerUsuarios();
        } catch(SQLException excepcion) {

            return retornarError("-1");
        }

        return resultado;
    }

    /**
     * crearUsuario
     * 
     * Función encargada de validar los datos para crear
     * un usuario.
     * 
     * @param nombre nombre del usuario.
     * @param contrasena contraseña del usuario.
     * @param idRol rol del usuario.
     * 
     * @return booleano indicando si el usuario se creó (true) o no (false).
     */
    public static String crearUsuario(String nombre, String contrasena, int idRol) {

        String resultado = "";

        try {
            if(nombre.trim().equals("")) {
                throw new Exception("-6");
            } else if(contrasena.trim().equals("")) {
                throw new Exception("-7");
            } else if(idRol < 1 && idRol > 3) {
                throw new Exception("-3");
            } else {
                resultado = lib.crearUsuario(nombre, contrasena, idRol);
            }

            if(!resultado.equals("0")) {
                throw new Exception(resultado);
            }
        } catch(SQLException excepcion) {
            System.out.println(excepcion.getMessage());
            return retornarError("-1");
        } catch(Exception excepcion) {
            return retornarError(excepcion.getMessage());
        }
        return resultado;
    }

    /**
     * actualizarUsuario
     * 
     * Función encargada de validar los datos para 
     * actualizar un usuario.
     * 
     * @param idUsuario identificador del usuario a inhabilitar.
     * 
     * @return booleano indicando si el usuario se actualizó (true)
     * o no (false).
     */
    public static boolean actualizarUsuario(int idUsuario) {

        return true;
    }
    
    /**
     * cambiarEstadoUsuario
     * 
     * Función encargada validar los datos para cambiar el estado de un usuario.
     * 
     * @see lib.inhabilitarUsuarios()
     * 
     * @param idUsuario identificador del usuario al que se le cambiará el estado
     * 
     * @return string indicando el resultado del cambio de estado.
     */
    public static String cambiarEstadoUsuario(int idUsuario) {

        String resultado = "";

        try {
            if(idUsuario < 0) {
                throw new Exception("-2");
            } else {
                resultado = lib.cambiarEstadoUsuario(idUsuario);
            }
        } catch(SQLException excepcion) {
            return retornarError("-1");
        } catch(Exception excepcion) {
            return retornarError(excepcion.getMessage());
        }
        return resultado;
    }

    /**
     * retornarError
     * 
     * Función encargada de recibir un código determinando
     * el error que ocurrió.
     * 
     * @param codigo código especificando el error ocurrido.
     * 
     * @return String con las características del error.
     */
    private static String retornarError(String codigoExcepcion) {

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
                mensajeError += "No existe un usuario con es id}";
                break;
            case -3:
                mensajeError += "Rol inválido}";
            case -4:
                mensajeError += "Credenciales incorrectas}";
                break;
            case -5:
                mensajeError += "Usuario desactivado}";
                break;
            case -6:
                mensajeError += "Nombre de usuario no puede ser vacio}";
                break;
            case -7:
                mensajeError += "Contraseña no puede ser vacía}";
                break;
            case -8:
                mensajeError += "Nombre de usuario en uso}";
                break;
            default:
                mensajeError += "El código de error " + codigo + " no ha sido identificado}";
                break;
        }
        return mensajeError;
    }
}
