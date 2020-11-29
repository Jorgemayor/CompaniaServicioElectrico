package src.managers.gestionConfiguracion;
import java.sql.*;
import src.control.Conexion;


import java.lang.String;

public class GestionConfiguracionLib {

    public String obtenerParametros() throws SQLException {
        Connection conexion = Conexion.conectar();

        String consultaSQL = "SELECT * FROM parametro";

        PreparedStatement consulta = conexion.prepareStatement(consultaSQL);
        ResultSet respuesta = consulta.executeQuery();

        conexion.close();

        return respuesta.toString();
    }


    public String actualizarParametros(double khw, double reconexion)throws SQLException{
        Connection conexion = Conexion.conectar();
        String consultaSQL = "SELECT * FROM parametro";
        PreparedStatement consultaEstado = conexion.prepareStatement(consultaSQL);
        ResultSet respuesta = consultaEstado.executeQuery();
        
        if(respuesta.next()) {
            String actualizacionSQL = "UPDATE parametro SET (khw=?,reconexion=?)";
            PreparedStatement actualizarEstado = conexion.prepareStatement(actualizacionSQL);
            actualizarEstado.setDouble(1, khw);
            actualizarEstado.setDouble(2, reconexion);
            actualizarEstado.executeUpdate();
        }
        conexion.close();
        return "0";
    }
    
}
