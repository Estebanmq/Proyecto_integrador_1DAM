package dao;


import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	private static final String CLASECONEXION = "org.apache.derby.jdbc.EmbeddedDriver"; 
	private static String proyecto;
	private static String user;
	private static String pass;
	private static String url;
	private static Connection conexion; 
	
	static {
		user = "root";
		pass = "root";
		proyecto = System.getProperty("user.dir").concat(File.separator).concat("dbDerby");
		url = "jdbc:derby:".concat(proyecto);
	}
	
	public static Connection conectar() throws ClassNotFoundException, SQLException {
		
		Class.forName(CLASECONEXION);
		conexion = DriverManager.getConnection(url,user,pass);
		return conexion;
		
	}
	
	public static void cerrar() throws SQLException {
		conexion.close();
	}

}