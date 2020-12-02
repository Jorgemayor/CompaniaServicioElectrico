package src.managers.gestionDeuda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONObject;

import src.control.Conexion;
import src.managers.gestionConfiguracion.GestionConfiguracionApi;

public class GestionDeudaLib {

    private GestionConfiguracionApi gestionConfiguracionApi = new GestionConfiguracionApi();
    
    public String registrarDeudaPorReconexion(int identificacionCliente) throws SQLException {

        String codigo = "0";

        Connection conexion = Conexion.conectar();
        String consultaSQL = "SELECT id FROM cliente WHERE identificacion=?";
        PreparedStatement consulta = conexion.prepareStatement(consultaSQL);
        consulta.setInt(1, identificacionCliente);
        ResultSet respuestaConsulta = consulta.executeQuery();

        if(!respuestaConsulta.next()) {
            codigo = "-4";
        }

        int idCliente = respuestaConsulta.getInt("id");

        String parametros = gestionConfiguracionApi.obtenerParametros();
        JSONObject parametrosJSON = new JSONObject(parametros);
        String precioReconexion = parametrosJSON.getString("reconexion");
        int valorPrecioReconexion = Integer.parseInt(precioReconexion);

        String insercionSQL = "INSERT INTO deuda(id_cliente, descripcion, valor) VALUES(?,?,?)";
        PreparedStatement insercion = conexion.prepareStatement(insercionSQL);
        insercion.setInt(1, idCliente);
        insercion.setString(2, "Reconexion");
        insercion.setInt(3, valorPrecioReconexion);
        insercion.executeUpdate();
        
        conexion.close();
        return codigo;
    }

    public String obtenerDeudasPorCliente(int identificacionCliente) throws SQLException {

        Connection conexion = Conexion.conectar();
        String consultaSQL = ""
            + "SELECT *"
            + "FROM deuda AS D"
            + "INNER JOIN cliente AS C ON D.id_cliente = C.id"
            + "WHERE C.identificacion=? AND C.habilitado=?";

        PreparedStatement consulta = conexion.prepareStatement(consultaSQL);
        consulta.setInt(1, identificacionCliente);
        consulta.setBoolean(2, true);
        ResultSet respuesta = consulta.executeQuery();
        
        JSONObject resultado = new JSONObject();
        while(respuesta.next()) {
            resultado.append("descripcion", respuesta.getString(3));
            resultado.append("valor", respuesta.getInt(4));
        }
        conexion.close();
        return resultado.toString();
    }
}
