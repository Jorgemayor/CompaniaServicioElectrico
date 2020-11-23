package src.managers.gestionClientes;

import java.sql.*;
import src.control.Conexion;

public class GestionClienteLib {

    // variables para encriptacion
    private static final String KEY = "gn2byqYnYFlJMzG5";
    private static final String IV = "HFNvUwjB1KiOKtJI";
    

    public String agregarCliente(String tipoIdentificacion, int identificacion, String nombre, String direccion, int ciudad) throws SQLException {

        Connection conexion = Conexion.conectar();
        String respuesta = "";

        String consultaSQL = "SELECT * FROM cliente WHERE habilitado = ?";
        PreparedStatement consulta = conexion.prepareStatement(consultaSQL);
        consulta.setString(1, nombre);
        ResultSet resultadoConsulta = consulta.executeQuery();

        if(resultadoConsulta.next()) {
            respuesta = "-7";
        } else {
            String insercionSQL = "INSERT INTO cliente(tipo_identificacion,identificacion,nombre,direccion,ciudad) VALUES(?,?,?,?,?)";
            PreparedStatement insercion = conexion.prepareStatement(insercionSQL);
            insercion.setString(1, tipoIdentificacion);
            insercion.setInt(2, identificacion);
            insercion.setString(3, nombre);
            insercion.setString(4, direccion);
            insercion.setInt(5, ciudad);
            insercion.executeUpdate();

            respuesta = "0";
        }

        conexion.close();
        return respuesta;
    }
    public String obtenerClientes() throws SQLException {
        Connection conexion = Conexion.conectar();

        String consultaSQL = "SELECT * FROM cliente WHERE habilitado = ?";

        PreparedStatement consulta = conexion.prepareStatement(consultaSQL);
        consulta.setBoolean(1, true);
        ResultSet respuesta = consulta.executeQuery();

        conexion.close();

        return respuesta.toString();
    }
    public String actualizarCliente(){
        return "";
    }
    public String cambiarEstadoCliente(int idCliente) throws SQLException {
        Connection conexion = Conexion.conectar();
        String consultaSQL = "SELECT habilitado FROM cliente WHERE id = ?";
        PreparedStatement consultaEstado = conexion.prepareStatement(consultaSQL);
        consultaEstado.setInt(1, idCliente);
        ResultSet estadoActual = consultaEstado.executeQuery();
        
        if(estadoActual.next()) {
            String actualizacionSQL = "UPDATE cliente SET habilitado = ? WHERE id = ?";
            PreparedStatement actualizarEstado = conexion.prepareStatement(actualizacionSQL);
            actualizarEstado.setBoolean(1, !estadoActual.getBoolean("habilitado"));
            actualizarEstado.setInt(2, idCliente);
            actualizarEstado.executeQuery();
        }
        conexion.close();
        return "{\"code\": 0, \"result\": " + "true" +"}";
    }
}