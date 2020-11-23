package src.managers.gestionCiudad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import src.control.Conexion;

public class gestionCiudadLib {

	public String obtenerCiudades() throws SQLException {
        Connection conexion = Conexion.conectar();
        String consultaSQL = "SELECT * FROM ciudad";
        PreparedStatement consulta = conexion.prepareStatement(consultaSQL);
        consulta.setBoolean(1, true);
        ResultSet respuesta = consulta.executeQuery();
        conexion.close();
        return respuesta.toString();
	}
    
}
