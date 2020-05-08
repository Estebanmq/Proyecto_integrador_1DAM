	package crearDB;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CrearBD {
	
	public Connection crearDB() throws ClassNotFoundException, SQLException {
		
		Connection conexion;
		
		String barra = File.separator;
		String proyecto = System.getProperty("user.dir").concat(barra).concat("dbDerby");
		File url = new File(proyecto);
		
		if (!url.exists()) {
			
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			String db = "jdbc:derby:".concat(proyecto).concat(";create=true");
			conexion = DriverManager.getConnection(db);

			conexion.close();
			
			System.out.format("Proceso finalizado.\n");
			return conexion;
			
		} else {
			System.out.format("La base de datos ya existe.\n");
			
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		CrearBD creaDB = new CrearBD();
		try {
			creaDB.crearDB();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
