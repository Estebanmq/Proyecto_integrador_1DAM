package dao;


import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * 
 * Clase para realizar las conexiones
 * 
 * @author Jose Manuel
 * @version 1.0
 */
public class Conexion {

	private static final String CLASECONEXION = "org.apache.derby.jdbc.EmbeddedDriver"; 
	private static final String proyecto;
	private static final String user;
	private static final String pass;
	private static final String url;
	private static Connection conexion; 
	
	static {
		user = "root";
		pass = "root";
		proyecto = System.getProperty("user.dir").concat(File.separator).concat("dbDerby");
		url = "jdbc:derby:".concat(proyecto);
		conexion = null;
	}
	/**
	 * Método que genera una nueva conexión y la retorna
	 * 
	 * @return Retorna la conexión
	 * @throws ClassNotFoundException si la clase no es localizada 
	 * @throws SQLException si el acceso a la base de datos ha generado un error
	 */
	public static Connection getConexion() throws ClassNotFoundException, SQLException {
		
		Class.forName(CLASECONEXION);
		conexion = DriverManager.getConnection(url,user,pass);
		return conexion;
		
	}
	/**
	 * Método para cerrar la conexión
	 * 
	 * @throws SQLException si el acceso a la base de datos ha generado un error
	 */
	public static void cerrar() throws SQLException {
		conexion.close();
	}

}