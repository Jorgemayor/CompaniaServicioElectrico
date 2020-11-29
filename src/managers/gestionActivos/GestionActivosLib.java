package src.managers.gestionActivos;

import java.sql.*;

import org.json.JSONObject;

import src.control.Conexion;

public class GestionActivosLib {


    public String obtenerActivos()throws SQLException{
        Connection conexion = Conexion.conectar();

        String consultaSQL = "SELECT * FROM activo WHERE habilitado = ?";

        PreparedStatement consulta = conexion.prepareStatement(consultaSQL);
        consulta.setBoolean(1, true);
        ResultSet respuesta = consulta.executeQuery();
        JSONObject resultado = new JSONObject();
        while(respuesta.next())
                { 
                    resultado.append("id", respuesta.getString(1));
                    resultado.append("numero_serie", respuesta.getString(2));
                    resultado.append("nombre", respuesta.getString(3));
                    resultado.append("id_ciudad", respuesta.getInt(4));
                    resultado.append("estado", respuesta.getString(5));
                    resultado.append("habilitado", respuesta.getBoolean(6));
                }
        conexion.close();
        return resultado.toString();
    }
    public String obtenerActivoPorSerial(String serial)throws SQLException{
        Connection conexion = Conexion.conectar();

        String consultaSQL = "SELECT * FROM activo WHERE habilitado = ? AND numero_serie = ? ";

        PreparedStatement consulta = conexion.prepareStatement(consultaSQL);
        consulta.setBoolean(1, true);
        consulta.setString(2, serial);
        ResultSet respuesta = consulta.executeQuery();
        JSONObject resultado = new JSONObject();
        while(respuesta.next())
                { 
                    resultado.append("id", respuesta.getString(1));
                    resultado.append("numero_serie", respuesta.getString(2));
                    resultado.append("nombre", respuesta.getString(3));
                    resultado.append("id_ciudad", respuesta.getInt(4));
                    resultado.append("estado", respuesta.getString(5));
                    resultado.append("habilitado", respuesta.getBoolean(6));
                }
        conexion.close();
        return resultado.toString();
    }
    public String registrarActivo(String numeroSerie, String nombre, int ciudad, String estado)throws SQLException{
        Connection conexion = Conexion.conectar();
        String respuesta = "";

        String consultaSQL = "SELECT COUNT(numero_serie) as conteo FROM activo WHERE numero_serie=? AND habilitado = ?";
        PreparedStatement consulta = conexion.prepareStatement(consultaSQL);
        consulta.setString(1, numeroSerie);
        consulta.setBoolean(2,true);
        ResultSet resultadoConsulta = consulta.executeQuery();
        resultadoConsulta.next();
        String textoResultadoConsulta = resultadoConsulta.getString("conteo");
        int enteroResultadoConsulta = Integer.parseInt(textoResultadoConsulta);
        System.out.println(enteroResultadoConsulta);

        if(enteroResultadoConsulta>0) {
            respuesta = "-7";
        } else {
            String insercionSQL = "INSERT INTO activo(numero_serie,nombre,id_ciudad,estado,habilitado) VALUES(?,?,?,?,?)";
            PreparedStatement insercion = conexion.prepareStatement(insercionSQL);
            insercion.setString(1, numeroSerie);
            insercion.setString(2, nombre);
            insercion.setInt(3, ciudad);
            insercion.setString(4, estado);
            insercion.setBoolean(5, true);
            insercion.executeUpdate();

            respuesta = "0";
        }

        conexion.close();
        return respuesta;
    }
    public String actualizarActivo(int idActivo, String nombre, int ciudad, String estado)throws SQLException{
        Connection conexion = Conexion.conectar();
        String consultaSQL = "SELECT * FROM activo WHERE id = ?";
        PreparedStatement consultaEstado = conexion.prepareStatement(consultaSQL);
        consultaEstado.setInt(1, idActivo);
        ResultSet estadoActual = consultaEstado.executeQuery();
        
        if(estadoActual.next()) {
            String actualizacionSQL = "UPDATE activo SET (nombre=?,id_ciudad=?,estado=?)  WHERE id = ?";
            PreparedStatement actualizarActivo = conexion.prepareStatement(actualizacionSQL);
            actualizarActivo.setString(1, nombre);
            actualizarActivo.setInt(2, ciudad);
            actualizarActivo.setString(3, estado);
            actualizarActivo.setInt(4, idActivo);
            actualizarActivo.executeQuery();
        }
        conexion.close();
        return "{\"code\": 0, \"result\": " + "true" +"}";
    }
    
    public String cambiarEstadoActivo(int idActivo)throws SQLException{
        Connection conexion = Conexion.conectar();
        String consultaSQL = "SELECT habilitado FROM activo WHERE id = ?";
        PreparedStatement consultaEstado = conexion.prepareStatement(consultaSQL);
        consultaEstado.setInt(1, idActivo);
        ResultSet estadoActual = consultaEstado.executeQuery();
        
        if(estadoActual.next()) {
            String actualizacionSQL = "UPDATE activo SET habilitado = ? WHERE id = ?";
            PreparedStatement actualizarEstado = conexion.prepareStatement(actualizacionSQL);
            actualizarEstado.setBoolean(1, !estadoActual.getBoolean("habilitado"));
            actualizarEstado.setInt(2, idActivo);
            actualizarEstado.executeQuery();
        }
        conexion.close();
        return "{\"code\": 0, \"result\": " + "true" +"}";
    }
    
}
