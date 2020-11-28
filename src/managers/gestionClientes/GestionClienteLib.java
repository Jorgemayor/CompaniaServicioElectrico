package src.managers.gestionClientes;

import java.sql.*;
import src.control.Conexion;


import java.lang.String;

public class GestionClienteLib {

    // variables para encriptacion
    private static final String KEY = "gn2byqYnYFlJMzG5";
    private static final String IV = "HFNvUwjB1KiOKtJI";
    

    public String agregarCliente(String tipoIdentificacion, int identificacion, String nombre, String direccion, int ciudad) throws SQLException {

        Connection conexion = Conexion.conectar();
        String respuesta = "";

        String consultaSQL = "SELECT COUNT(nombre) as conteo FROM cliente WHERE identificacion = ? AND habilitado = ?";
        PreparedStatement consulta = conexion.prepareStatement(consultaSQL);
        consulta.setInt(1, identificacion);
        consulta.setBoolean(2, true);
        ResultSet resultadoConsulta = consulta.executeQuery();
        resultadoConsulta.next();
        String textoResultadoConsulta = resultadoConsulta.getString("conteo");
        int enteroResultadoConsulta = Integer.parseInt(textoResultadoConsulta);
        System.out.println(enteroResultadoConsulta);


        if(enteroResultadoConsulta>0) {
            respuesta = "-7";
        } else {
            String insercionSQL = "INSERT INTO cliente (tipo_identificacion,identificacion,nombre,direccion,id_ciudad,habilitado) VALUES(?,?,?,?,?,true)";
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
    public String actualizarCliente(String tipoIdentificacion, int identificacion, String nombre, String direccion, int ciudad)throws SQLException{
        Connection conexion = Conexion.conectar();
        String consultaSQL = "SELECT * FROM cliente WHERE habilitado = ? AND identificacion = ?";
        PreparedStatement consultaEstado = conexion.prepareStatement(consultaSQL);
        consultaEstado.setBoolean(2, true);
        consultaEstado.setInt(2, identificacion);
        ResultSet respuesta = consultaEstado.executeQuery();
        
        if(respuesta.next()) {
            String actualizacionSQL = "UPDATE cliente SET (tipo_identificacion=?,identificacion=?,nombre=?,direccion=?,id_ciudad=?)  WHERE id = ?";
            PreparedStatement actualizarEstado = conexion.prepareStatement(actualizacionSQL);
            actualizarEstado.setString(1, tipoIdentificacion);
            actualizarEstado.setInt(2, identificacion);
            actualizarEstado.setString(3, nombre);
            actualizarEstado.setString(4, direccion);
            actualizarEstado.setInt(5, ciudad);
            actualizarEstado.executeUpdate();
        }
        conexion.close();
        return respuesta.toString();
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
        return estadoActual.toString();
    }
}