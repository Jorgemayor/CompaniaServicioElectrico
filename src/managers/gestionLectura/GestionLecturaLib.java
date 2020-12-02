package src.managers.gestionLectura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONObject;

import src.control.Conexion;

public class GestionLecturaLib {

    public String obtenerLecturasPorCliente(int identificacionCliente) throws SQLException {
        Connection conexion = Conexion.conectar();

        String consultaSQL = ""
            + "SELECT *"
            + "FROM lectura AS L INNER JOIN cliente AS C ON L.id_cliente = C.id"
            + "WHERE C.identificacion=? AND C.habilitado=?";

        PreparedStatement consulta = conexion.prepareStatement(consultaSQL);
        consulta.setInt(1, identificacionCliente);
        consulta.setBoolean(2, true);
        ResultSet respuesta = consulta.executeQuery();
        JSONObject resultado = new JSONObject();

        while(respuesta.next()) {
            resultado.append("fecha", respuesta.getString(1));
            resultado.append("consumo", respuesta.getString(2));
        }

        conexion.close();
        return resultado.toString();
    }

    public String obtenerUltimaLecturaPorCliente(int identificacionCliente) throws SQLException {
        Connection conexion = Conexion.conectar();

        String consultaSQL = ""
            + "SELECT S.fecha, L.consumo" 
            + "FROM (SELECT L.id_cliente as LIDC, MAX(L.fecha) AS fecha"
                + "FROM lectura AS L INNER JOIN cliente AS C ON L.id_cliente = C.id"
                + "WHERE C.identificacion=? AND C.habilitado=? GROUP BY LIDC)"
            + "AS S JOIN lectura AS L ON L.id_cliente = S.LIDC AND S.fecha = L.fecha ";

        PreparedStatement consulta = conexion.prepareStatement(consultaSQL);
        consulta.setInt(1, identificacionCliente);
        consulta.setBoolean(2, true);
        ResultSet respuesta = consulta.executeQuery();
        JSONObject resultado = new JSONObject();

        if(respuesta.next()) {
            resultado.append("fecha", respuesta.getString(1));
            resultado.append("consumo", respuesta.getString(2));
        }

        conexion.close();
        return resultado.toString();
    }

    public String ingresarLectura(int idCliente, String fecha, int consumo) throws SQLException {

        Connection conexion = Conexion.conectar();
        String respuesta = "";

        String consultaSQL = "SELECT * FROM cliente WHERE id=? AND habilitado=?";
        PreparedStatement consulta = conexion.prepareStatement(consultaSQL);
        consulta.setInt(1, idCliente);
        consulta.setBoolean(2, true);
        ResultSet resultadoConsulta = consulta.executeQuery();

        if(resultadoConsulta.next()) {
            respuesta = "-7";
        } else {
            String insercionSQL = "INSERT INTO lectura (id_cliente, fecha, consumo) VALUES (?, ?, ?)";
            PreparedStatement insercion = conexion.prepareStatement(insercionSQL);
            insercion.setInt(1, idCliente);
            insercion.setString(2, fecha);
            insercion.setInt(3, consumo);
            insercion.executeUpdate();
            
            respuesta = "0";
        }

        conexion.close();
        return respuesta;
    }
}
