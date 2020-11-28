package src.managers.gestionUsuario;

import java.sql.*;
import src.control.Principal;
import src.control.Usuario;

import org.json.JSONObject;
import org.sebastian.utils.AESEncryptor;

import src.control.Conexion;

public class GestionUsuarioLib {
  
    // variables para encriptacion
    private static final String KEY = "gn2byqYnYFlJMzG5";
    private static final String IV = "HFNvUwjB1KiOKtJI";

    public String ingresar(String intentoUsuario, String intentoClave) throws SQLException {
        PreparedStatement consulta;
        String resultado = "-4"; // arrancar suponiendo que las credenciales son incorrectas, solo se cambia si sí coinciden
        Connection conexion = Conexion.conectar();

        consulta = conexion.prepareStatement("SELECT id, contrasena, id_rol, habilitado FROM usuario WHERE nombre=?");
        consulta.setString(1, intentoUsuario);
        ResultSet respuesta = consulta.executeQuery();

		if(respuesta.next()) {
            String encrypted = AESEncryptor.encrypt(KEY, IV, intentoClave);

            if(!respuesta.getBoolean("habilitado")){
                resultado = "-5";
            } else if(respuesta.getString("contrasena").equals(encrypted)) {
                resultado = "0";
                Principal.arrancarVentanaPrincipal(new Usuario(respuesta.getInt("id"), intentoUsuario, respuesta.getInt("id_rol")));
            } else {
                resultado = "-4";
            }
		} else {
            return "-4"; // error de credenciales
        }

        conexion.close();
		return resultado;
    }

    public String obtenerUsuarios() throws SQLException {

        Connection conexion = Conexion.conectar();

        String consultaSQL = "SELECT * FROM usuario WHERE habilitado = ?";

        PreparedStatement consulta = conexion.prepareStatement(consultaSQL);
        consulta.setBoolean(1, true);
        ResultSet respuesta = consulta.executeQuery();
        JSONObject resultado = new JSONObject();
        while(respuesta.next())
                { 
                    resultado.append("id", respuesta.getString(1));
                    resultado.append("nombre", respuesta.getString(2));
                    resultado.append("rol", respuesta.getString(4));
                    resultado.append("habilitado", respuesta.getBoolean(5));
                }
        conexion.close();
        return resultado.toString();
    }

    public String obtenerUsuarioPorNombre(String nombre) throws SQLException {

        Connection conexion = Conexion.conectar();

        String consultaSQL = "SELECT * FROM usuario WHERE habilitado = ? AND nombre = ?";

        PreparedStatement consulta = conexion.prepareStatement(consultaSQL);
        consulta.setBoolean(1, true);
        consulta.setString(2, nombre);
        ResultSet respuesta = consulta.executeQuery();
        JSONObject resultado = new JSONObject();
        while(respuesta.next())
                { 
                    resultado.append("id", respuesta.getString(1));
                    resultado.append("nombre", respuesta.getString(2));
                    resultado.append("contraseña", respuesta.getString(3));
                    resultado.append("rol", respuesta.getString(4));
                    resultado.append("habilitado", respuesta.getBoolean(5));
                }
        conexion.close();
        return resultado.toString();
    }

    public String crearUsuario(String nombre, String contrasena, int idRol) throws SQLException {

        Connection conexion = Conexion.conectar();
        String respuesta = "";

        String consultaSQL = "SELECT * FROM usuario WHERE nombre=? AND habilitado=?";
        PreparedStatement consulta = conexion.prepareStatement(consultaSQL);
        consulta.setString(1, nombre);
        consulta.setBoolean(2, true);
        ResultSet resultadoConsulta = consulta.executeQuery();

        if(resultadoConsulta.next()) {
            respuesta = "-8";
        } else {
            String contrasenaEncriptada = AESEncryptor.encrypt(KEY, IV, contrasena);
            String insercionSQL = "INSERT INTO usuario (nombre, contrasena, id_rol) VALUES (?, ?, ?)";
            PreparedStatement insercion = conexion.prepareStatement(insercionSQL);
            insercion.setString(1, nombre);
            insercion.setString(2, contrasenaEncriptada);
            insercion.setInt(3, idRol);
            insercion.executeUpdate();
            
            respuesta = "0";
        }

        conexion.close();
        return respuesta;
    }

    public String actualizarUsuario(int id, String nombre, String contrasena,int idRol) throws SQLException{

        Connection conexion = Conexion.conectar();
        String consultaSQL = "SELECT habilitado FROM usuario WHERE id = ? AND habilitado = =";
        PreparedStatement consultaEstado = conexion.prepareStatement(consultaSQL);
        consultaEstado.setInt(1, id);
        consultaEstado.setBoolean(2, true);
        ResultSet estadoActual = consultaEstado.executeQuery();
        
        if(estadoActual.next()) {
            String actualizacionSQL = "UPDATE usuario SET nombre = ?,contrasena = ?, id_rol = ? WHERE id = ?";
            PreparedStatement actualizarEstado = conexion.prepareStatement(actualizacionSQL);
            actualizarEstado.setString(1, nombre);
            actualizarEstado.setString(2, contrasena);
            actualizarEstado.setInt(3, idRol);
            actualizarEstado.setInt(4, id);
            actualizarEstado.executeUpdate();
        }
        conexion.close();
        return "0";
    }
    
    /**
     * cambiarEstadoUsuarios
     * 
     * Función encargada de inhabilitar un usuario.
     * 
     * @param id_usuario identificador del usuario a inhabilitar.
     * 
     * @return booleano indicando el nuevo estado del usuario.
     * 
     * @throws SQLException
     */
    public String cambiarEstadoUsuario(int idUsuario) throws SQLException {
        Connection conexion = Conexion.conectar();
        String consultaSQL = "SELECT habilitado FROM usuario WHERE id = ?";
        PreparedStatement consultaEstado = conexion.prepareStatement(consultaSQL);
        consultaEstado.setInt(1, idUsuario);
        ResultSet estadoActual = consultaEstado.executeQuery();
        
        if(estadoActual.next()) {
            String actualizacionSQL = "UPDATE usuario SET habilitado = ? WHERE id = ?";
            PreparedStatement actualizarEstado = conexion.prepareStatement(actualizacionSQL);
            actualizarEstado.setBoolean(1, !estadoActual.getBoolean("habilitado"));
            actualizarEstado.setInt(2, idUsuario);
            actualizarEstado.executeQuery();
        }
        conexion.close();
        return "0";
    }
}
