package managers.gestionUsuario;

import java.sql.*;
import control.Principal;
import control.Usuario;

import org.sebastian.utils.AESEncryptor;

import control.Conexion;

public class GestionUsuarioLib {
  
    // variables para encriptacion
    private static final String KEY = "gn2byqYnYFlJMzG5";
    private static final String IV = "HFNvUwjB1KiOKtJI";

    public static int intentarIngresar(String intentoUsuario, String intentoClave) throws SQLException{
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
     * inhabilitarUsuarios
     * 
     * Función encargada de inhabilitar un usuario.
     * 
     * @param id_usuario identificador del usuario a inhabilitar.
     * 
     * @return booleano indicando si se inhabilitó (true) o no (false).
     */
    public String inhabilitarUsuarios(int id_usuario) {

        return "";
    }

}
