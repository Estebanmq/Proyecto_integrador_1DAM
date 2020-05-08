package crearDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dao.Conexion;

public class ProbarBD {

	public static void main(String[] args) {

		Connection conexion;
		
		String query;
		Statement st;
		ResultSet rs;
		
		try {
			conexion = Conexion.getConexion();
			
			query = "select * from pais";
			
			st = conexion.createStatement();
			rs = st.executeQuery(query);
			
			Integer codigo;
			String descripcion;
			
			while (rs.next()) {
				codigo = rs.getInt(1);
				descripcion = rs.getString(2);
				System.out.format("%d :: %s\n", codigo, descripcion);
			}
			
			 Conexion.cerrar();

			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
