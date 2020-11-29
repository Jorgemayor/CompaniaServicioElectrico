package src.managers.gestionUsuario;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import org.json.JSONObject;

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
     * obtenerUsuarios
     * 
     * Función encargada de validar los datos para consultar todos
     * los usuario.
     * 
     * @param id_usuario identificador del usuario a inhabilitar.
     * 
     * @return booleano indicando si se actualizó (true) o no (false).
     */
    public static String obtenerUsuarios() {

        JSONObject resultado = new JSONObject();
        String usuarios = "";
        try {
            usuarios = lib.obtenerUsuarios();
        } catch(SQLException excepcion) {
            return retornarError("-1");
        }
        if(usuarios.equals("{}")){

            return retornarError("-2");
        }
        else{
            resultado.put("usuarios", usuarios);
            resultado.put("code", "0");
            return resultado.toString();
        }
    }
    /**
     * obtenerUsuarioPorId
     * 
     * Función encargada de validar los datos para consultar todos
     * los usuario.
     * 
     * @param id_usuario identificador del usuario a inhabilitar.
     * 
     * @return booleano indicando si se actualizó (true) o no (false).
     */
    public static String obtenerUsuarioPorNombre(String nombre) {
        if(nombre.equals("")){
            return retornarError("-6");
        }
        JSONObject resultado = new JSONObject();
        String usuario = "";
        try {
            usuario = lib.obtenerUsuarioPorNombre(nombre);
        } catch(SQLException excepcion) {
            return retornarError("-1");
        }
        if(usuario.equals("{}")){

            return retornarError("-2");
        }
        else{
            resultado.put("usuario", usuario);
            resultado.put("code", "0");
            return resultado.toString();
        }
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

        JSONObject resultado = new JSONObject();
        String codigo = "";

        try {
            if(nombre.trim().equals("")) {
                throw new Exception("-6");
            } else if(contrasena.trim().equals("")) {
                throw new Exception("-7");
            } else if(idRol < 1 && idRol > 3) {
                throw new Exception("-3");
            } else {
                codigo = lib.crearUsuario(nombre, contrasena, idRol);
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
    public static String actualizarUsuario(int idUsuario, String nombre, String contrasena,int idRol) {

        JSONObject resultado = new JSONObject();
        String codigo = "";

        try {
            if(idUsuario < 0) {
                throw new Exception("-2");
            } else {
                codigo = lib.actualizarUsuario(idUsuario, nombre, contrasena, idRol);
            }
            if(!codigo.equals("0")) {
                throw new Exception(codigo);
            }
        } catch(SQLException excepcion) {
            System.out.print(excepcion.getMessage());
            return retornarError("-1");
        } catch(Exception excepcion) {
            return retornarError(excepcion.getMessage());
        }
        resultado.put("code", codigo);
        return resultado.toString();
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

        JSONObject resultado = new JSONObject();
        String codigo = "";

        try {
            if(idUsuario < 0) {
                throw new Exception("-2");
            } else {
                codigo = lib.cambiarEstadoUsuario(idUsuario);
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
                mensajeError.put("mensaje", "Error al conectarse a la base de datos");
                break;
            case -2:
                mensajeError.put("mensaje", "No existe un usuario con es id");
                break;
            case -3:
                mensajeError.put("mensaje", "Rol inválido");
                break;
            case -4:
                mensajeError.put("mensaje", "Credenciales incorrectas");
                break;
            case -5:
                mensajeError.put("mensaje", "Usuario desactivado");
                break;
            case -6:
                mensajeError.put("mensaje", "El nombre de usuario no puede estar vacio");
                break;
            case -7:
                mensajeError.put("mensaje", "La contraseña no puede estar vacía");
                break;
            case -8:

                mensajeError.put("mensaje", "Nombre de usuario en uso");

                break;
            default:
                mensajeError.put("mensaje", "El código de error " + codigo + " no ha sido identificado");
                break;
        }
        return mensajeError.toString();
    }
}
