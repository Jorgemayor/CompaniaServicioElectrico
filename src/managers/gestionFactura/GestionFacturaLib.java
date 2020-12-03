package src.managers.gestionFactura;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONObject;

import src.control.Conexion;
import src.managers.gestionConfiguracion.GestionConfiguracionApi;
import src.managers.gestionDeuda.GestionDeudaLib;

public class GestionFacturaLib {

    private GestionConfiguracionApi gestionConfiguracionApi = new GestionConfiguracionApi();
    private GestionDeudaLib gestionDeudaLib = new GestionDeudaLib();

    public String obtenerFacturasPorCliente(int identificacionCliente) throws SQLException {
        Connection conexion = Conexion.conectar();

        String consultaSQL = ""
            + "SELECT F.id, C.nombre, F.fecha_generacion, F.fecha_pago, L.consumo, F.valor "
            + "FROM factura AS F "
            + "INNER JOIN cliente AS C ON F.id_cliente = C.id "
            + "INNER JOIN lectura AS L ON F.id_lectura = L.id "
            + "WHERE C.identificacion=? AND C.habilitado=?";

        PreparedStatement consulta = conexion.prepareStatement(consultaSQL);
        consulta.setInt(1, identificacionCliente);
        consulta.setBoolean(2, true);
        ResultSet respuesta = consulta.executeQuery();

        String deuda = gestionDeudaLib.obtenerSumaDeudasPorCliente(identificacionCliente);
        JSONObject JSONDeuda = new JSONObject(deuda);
        int valorDeuda = JSONDeuda.getInt("valor");

        JSONObject resultado = new JSONObject();
        while(respuesta.next()) {
            resultado.append("id", respuesta.getInt(1));
            resultado.append("nombre", respuesta.getString(2));
            resultado.append("fecha_generacion", respuesta.getString(3));
            resultado.append("fecha_pago", respuesta.getString(4));
            resultado.append("consumo", respuesta.getInt(5));
            resultado.append("deuda", valorDeuda);
            resultado.append("valor", respuesta.getInt(6));
        }
        conexion.close();
        return resultado.toString();
    }

    public String generarFactura(int identificacionCliente) throws SQLException {
        
        Connection conexion = Conexion.conectar();
        String respuesta = "";

        String parametros = gestionConfiguracionApi.obtenerParametros();
        JSONObject parametrosJSON = new JSONObject(parametros);
        String precioKWH = parametrosJSON.getString("kwh");
        int valorPrecioKWH = Integer.parseInt(precioKWH);

        String consultaSQL = ""
            + "SELECT L.id, L.id_cliente, L.consumo "
            + "FROM lectura AS L INNER JOIN cliente AS C "
            + "ON L.id_cliente = C.id "
            + "WHERE C.id=? AND C.habilitado=?";
        PreparedStatement consulta = conexion.prepareStatement(consultaSQL);
        consulta.setInt(1, identificacionCliente);
        consulta.setBoolean(2, true);
        ResultSet resultadoConsulta = consulta.executeQuery();


        if(resultadoConsulta.next()) {
            int idLectura = resultadoConsulta.getInt(1);
            int idCliente = resultadoConsulta.getInt(2);
            int consumo = resultadoConsulta.getInt(3);

            int valorFactura = consumo * valorPrecioKWH;

            long millis = System.currentTimeMillis();  
            Date fechaActual = new Date(millis);

            String insercionSQL = "INSERT INTO factura(id_cliente, id_lectura, fecha_generacion, valor) VALUES(?,?,?,?)";
            PreparedStatement insercion = conexion.prepareStatement(insercionSQL);
            insercion.setInt(1, idCliente);
            insercion.setInt(2, idLectura);
            insercion.setDate(3, fechaActual);
            insercion.setInt(4, valorFactura);
            insercion.executeUpdate();

            respuesta = "0";
        } else {
            respuesta = "-2";
        }

        conexion.close();
        return respuesta;
    }
}
