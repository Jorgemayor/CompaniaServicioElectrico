package src.managers.gestionUsuario;

import java.sql.*;
import src.control.Principal;
import src.control.Usuario;

import org.sebastian.utils.AESEncryptor;

import src.control.Conexion;

public class GestionUsuarioLib {
  
    // variables para encriptacion
    private static final String KEY = "gn2byqYnYFlJMzG5";
    private static final String IV = "HFNvUwjB1KiOKtJI";

    public int intentarIngresar(String intentoUsuario, String intentoClave) throws SQLException{
        PreparedStatement consulta;
        int correcto = -4; // arrancar suponiendo que las credenciales son incorrectas, solo se cambia si sí coinciden
        Connection conexion = Conexion.conectar();
        consulta = conexion.prepareStatement("SELECT id, contrasena, id_rol, habilitado FROM usuario WHERE usuario = ?");
        consulta.setString(1, intentoUsuario);
        ResultSet respuesta = consulta.executeQuery();
		if (respuesta.next()) {
                String encrypted = AESEncryptor.encrypt(KEY, IV, intentoClave);
                System.out.println(encrypted);
                if(!respuesta.getBoolean("habilitado")){
                    correcto = -5;
                }else if(respuesta.getString("contrasena").equals(encrypted)){
                    correcto = 0;
                    Principal.arrancarVentanaPrincipal(new Usuario(respuesta.getInt("id"), intentoUsuario, respuesta.getInt("id_rol")));
                }else{
                    correcto = -4;
                }
			}else{
                return -4; // error de credenciales
            }
        conexion.close();
		return correcto;
    }

    public String obtenerUsuarios() throws SQLException {

        Connection conexion = Conexion.conectar();

        String consultaSQL = "SELECT * FROM usuario WHERE habilitado = true;";

        PreparedStatement consulta = conexion.prepareStatement(consultaSQL);
        ResultSet respuesta = consulta.executeQuery();

        Conexion.cerrar();

        return respuesta.toString();
    }

    public String crearUsuario(String nombre, String contrasena, int id_rol) {

        //Insercion

        return "";
    }

    public String actualizarUsuarios(int id_usuario) {

        return "";
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
        String consultaSQL = "SELECT habilitado FROM usuario WHERE id=?";
        PreparedStatement consultaEstado = conexion.prepareStatement(consultaSQL);
        consultaEstado.setInt(1, idUsuario);
        ResultSet estadoActual = consultaEstado.executeQuery();
        if(estadoActual.next()){
            String actualizacionSQL = "UPDATE usuario SET habilitado=? WHERE id=?";
            PreparedStatement actualizarEstado = conexion.prepareStatement(actualizacionSQL);
            actualizarEstado.setBoolean(1, !estadoActual.getBoolean("habilitado"));
            actualizarEstado.setInt(2, idUsuario);
            actualizarEstado.executeQuery();
        }
        Conexion.cerrar();
        return "";
    }
}