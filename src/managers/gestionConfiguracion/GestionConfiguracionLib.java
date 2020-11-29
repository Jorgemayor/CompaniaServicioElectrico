package src.managers.gestionConfiguracion;

import java.sql.*;

import org.json.JSONObject;

import src.control.Conexion;


import java.lang.String;

public class GestionConfiguracionLib {

    public String obtenerParametros() throws SQLException {
        Connection conexion = Conexion.conectar();
        String consultaSQL = "SELECT * FROM parametro";
        PreparedStatement consulta = conexion.prepareStatement(consultaSQL);
        ResultSet respuesta = consulta.executeQuery();
        JSONObject resultado = new JSONObject();
        respuesta.next();
        resultado.append("kwh",respuesta.getString(2));
        resultado.append("reconexion",respuesta.getString(3));
        conexion.close();
        return resultado.toString();
    }


    public String actualizarParametros(int khw, int reconexion)throws SQLException{
        Connection conexion = Conexion.conectar();
        String consultaSQL = "SELECT * FROM parametro";
        PreparedStatement consultaEstado = conexion.prepareStatement(consultaSQL);
        ResultSet respuesta = consultaEstado.executeQuery();
        
        if(respuesta.next()) {
            String actualizacionSQL = "UPDATE parametro SET kwh=?,reconexion=?";
            PreparedStatement actualizarEstado = conexion.prepareStatement(actualizacionSQL);
            actualizarEstado.setDouble(1, khw);
            actualizarEstado.setDouble(2, reconexion);
            actualizarEstado.executeUpdate();
        }
        conexion.close();
        return "0";
    }
    
}
