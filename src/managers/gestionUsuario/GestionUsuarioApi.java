package managers.gestionUsuario;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestionUsuarioApi {
    
    private static GestionUsuarioLib lib;

    /**
     * logearUsuario
     * 
     * 
     * @return
     */

    public static String loguearUsuario(String usuarioIntento, String claveIntento){
        if (claveIntento.trim().equals("") || usuarioIntento.trim().equals("")) {
            return retornarError(-3);
        }
        int resultado = -1000;
        try{
            resultado = GestionUsuarioLib.intentarIngresar(usuarioIntento, claveIntento);
        } catch (SQLException ex) {
            Logger.getLogger(GestionUsuarioApi.class.getName()).log(Level.SEVERE, null, ex);
            return retornarError(-1);
        }
        if(resultado == 0){
            return "OK";
        }
        return retornarError(resultado);
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

            return retornarError(-1);
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

        if(nombre == "") {
            
        } else if(contrasena == "") {

        } else if(idRol < 0 && idRol > 2) {

        } else {

            resultado = lib.crearUsuario(nombre, contrasena, idRol);
        }

        return resultado;
    }

    /**
     * actualizarUsuarios
     * 
     * Función encargada de validar los datos para 
     * actualizar un usuario.
     * 
     * @param idUsuario identificador del usuario a inhabilitar.
     * 
     * @return booleano indicando si el usuario se actualizó (true)
     * o no (false).
     */
    public static boolean actualizarUsuarios(int idUsuario) {

        return true;
    }
    
    /**
     * inhabilitarUsuarios
     * 
     * Función encargada validar los datos para inhabilitar un usuario.
     * 
     * @see lib.inhabilitarUsuarios()
     * 
     * @param idUsuario identificador del usuario a inhabilitar.
     * 
     * @return string indicando si el usuario se inhabilitó (true) o no (false).
     */
    public static String inhabilitarUsuarios(int idUsuario) {

        try {
            if(idUsuario < 0) {
                throw new Exception("-2");
            } else {
                lib.inhabilitarUsuarios(idUsuario);
            }
        } catch(SQLException excepcion) {

            return retornarError(-1);

        } catch(Exception excepcion) {

            return retornarError(-2);
        }

        return "";
    }

    /**
     * retornarError
     * 
     * Función encargada de recibir un código determinando
     * el error que ocurrió.
     * 
     * @param codigo código especificando el error ocurrido.
     * 
     * @return JSON con las características del error.
     */
    private static String retornarError(int codigo) {

        String mensajeError = "";

        switch(codigo) {
            case -1:
                mensajeError = "Error al conectarse a la base de datos";
                break;
            case -2:
                mensajeError = "No existe un usuario con es id";
                break;
            case -3:
                mensajeError = "Campo vacios";
            case -4:
                mensajeError = "Credenciales incorrectas";
                break;
            case -5:
                mensajeError = "Usuario desactivado";
                break;
            default:
                mensajeError = "Error inesperado "+codigo;
                break;
        }
        return mensajeError;
    }
}
