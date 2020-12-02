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

    public String obtenerSumaDeudasPorCliente(int identificacionCliente) throws SQLException {

        Connection conexion = Conexion.conectar();
        String consultaSQL = ""
            + "SELECT S.total "
            + "FROM ( "
                + "SELECT D.id_cliente as DIDC, SUM(D.valor) AS total "
                + "FROM deuda AS D INNER JOIN cliente AS C ON D.id_cliente = C.id "
                + "WHERE C.identificacion=? AND C.habilitado=? AND D.pagada=? "
                + "GROUP BY DIDC) AS S "
            + "JOIN deuda AS D ON D.id_cliente = S.DIDC AND S.total = D.valor";

        PreparedStatement consulta = conexion.prepareStatement(consultaSQL);
        consulta.setInt(1, identificacionCliente);
        consulta.setBoolean(2, true);
        consulta.setBoolean(3, false);
        ResultSet respuesta = consulta.executeQuery();
        
        JSONObject resultado = new JSONObject();
        if(respuesta.next()) {
            resultado.append("valor", respuesta.getInt(1));
        }
        conexion.close();
        return resultado.toString();
    }
}
