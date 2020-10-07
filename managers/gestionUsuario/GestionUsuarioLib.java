package managers.gestionUsuario;

import java.sql.*;

import control.Conexion;

public class GestionUsuarioLib {
    

    public String obtenerUsuarios() throws SQLException {

        Connection conexion = Conexion.conectar();

        String consultaSQL = "SELECT * FROM usuario WHERE habilitado = true;";

        PreparedStatement consulta = conexion.prepareStatement(consultaSQL);
        ResultSet respuesta = consulta.executeQuery();

        Conexion.cerrar();

        return respuesta.toString();
    }

    public String crearUsuario(String nombre, String contrasena, int id_rol) {

        //Insersion

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
