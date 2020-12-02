package src.managers.gestionCiudad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONObject;

import src.control.Conexion;

public class GestionCiudadLib {

	public String obtenerCiudades() throws SQLException {
        Connection conexion = Conexion.conectar();
        String consultaSQL = "SELECT * FROM ciudad";
        PreparedStatement consulta = conexion.prepareStatement(consultaSQL);
        ResultSet respuesta = consulta.executeQuery();
        JSONObject resultado = new JSONObject();
        while(respuesta.next()) {
            resultado.append("id", respuesta.getString(1));
            resultado.append("nombre", respuesta.getString(2)); 
        }
        conexion.close();
        return resultado.toString();
    }
    
    public String obtenerCiudadPorId(int id) throws SQLException {
        Connection conexion = Conexion.conectar();
        String consultaSQL = "SELECT * FROM ciudad WHERE id =?";
        PreparedStatement consulta = conexion.prepareStatement(consultaSQL);
        consulta.setInt(1, id);
        ResultSet respuesta = consulta.executeQuery();
        respuesta.next();
        conexion.close();
        return respuesta.getString(2);
    }   
}
