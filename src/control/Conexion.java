package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
    public static Connection conexion;

    public static Connection conectar() throws SQLException {
		
        String url = "";
        String usuario = "";
        String contrasena = "";
        
        conexion = DriverManager.getConnection(url, usuario, contrasena);
        if (conexion != null) {
            System.out.println("Conectado");
        }
		return conexion;
    }
    
    public static void cerrar() {
		try {
			conexion.close();
		} catch (SQLException ex) {
			System.out.println("No se pudo cerrar normalmente la conexión con la base de datos");
		}
	}
}
