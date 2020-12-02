package src.managers.gestionLectura;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONObject;

import src.control.Conexion;

public class GestionLecturaLib {

    public String obtenerLecturasPorCliente(int identificacionCliente) throws SQLException {
        
        Connection conexion = Conexion.conectar();
        String consultaSQL = ""
            + "SELECT L.fecha, L.consumo "
            + "FROM lectura AS L INNER JOIN cliente AS C ON L.id_cliente = C.id "
            + "WHERE C.identificacion=? AND C.habilitado=? "
            + "ORDER BY L.fecha ASC";

        PreparedStatement consulta = conexion.prepareStatement(consultaSQL);
        consulta.setInt(1, identificacionCliente);
        consulta.setBoolean(2, true);
        ResultSet respuestaConsulta = consulta.executeQuery();
        JSONObject resultado = new JSONObject();

        while(respuestaConsulta.next()) {
            resultado.append("fecha", respuestaConsulta.getString(1));
            resultado.append("consumo", respuestaConsulta.getString(2));
        }

        conexion.close();
        return resultado.toString();
    }

    public String obtenerUltimaLecturaPorCliente(int identificacionCliente) throws SQLException {
        
        Connection conexion = Conexion.conectar();
        String consultaSQL = ""
            + "SELECT S.fecha, L.consumo "
            + "FROM (SELECT L.id_cliente as LIDC, MAX(L.fecha) AS fecha "
                + "FROM lectura AS L INNER JOIN cliente AS C ON L.id_cliente = C.id "
                + "WHERE C.identificacion=? AND C.habilitado=? GROUP BY LIDC) "
            + "AS S JOIN lectura AS L ON L.id_cliente = S.LIDC AND S.fecha = L.fecha";

        PreparedStatement consulta = conexion.prepareStatement(consultaSQL);
        consulta.setInt(1, identificacionCliente);
        consulta.setBoolean(2, true);
        ResultSet respuestaConsulta = consulta.executeQuery();
        JSONObject resultado = new JSONObject();

        if(respuestaConsulta.next()) {
            resultado.append("fecha", respuestaConsulta.getString(1));
            resultado.append("consumo", respuestaConsulta.getString(2));
        }

        conexion.close();
        return resultado.toString();
    }

    public String ingresarLectura(int identificacionCliente, Date fecha, int consumo) throws SQLException {

        Connection conexion = Conexion.conectar();
        String resultado = "";

        String consultaSQL = "SELECT id FROM cliente WHERE identificacion=? AND habilitado=?";
        PreparedStatement consulta = conexion.prepareStatement(consultaSQL);
        consulta.setInt(1, identificacionCliente);
        consulta.setBoolean(2, true);
        ResultSet respuestaConsulta = consulta.executeQuery();

        if(!respuestaConsulta.next()) {
            resultado = "-7";
        } else {

            int idCliente = respuestaConsulta.getInt(1);
            String insercionSQL = "INSERT INTO lectura (id_cliente, fecha, consumo) VALUES (?, ?, ?)";
            PreparedStatement insercion = conexion.prepareStatement(insercionSQL);
            insercion.setInt(1, idCliente);
            insercion.setDate(2, fecha);
            insercion.setInt(3, consumo);
            insercion.executeUpdate();
            
            resultado = "0";
        }

        conexion.close();
        return resultado;
    }
}
