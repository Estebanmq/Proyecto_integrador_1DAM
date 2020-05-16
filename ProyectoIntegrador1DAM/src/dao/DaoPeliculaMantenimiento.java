package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.Pais;

public class DaoPeliculaMantenimiento {

	private String query;
	private Connection conn;
	private Statement st;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public boolean insertarPelicula(String titulo,int anyo,String director,String nacionalidad,String sinopsis,String genero) throws SQLException, ClassNotFoundException {
		int result;
		int maxCod;
		genero.toUpperCase();
		String selMaxCod = "SELECT COALESCE(max(codigo),0)+1 FROM EJEMPLARAUDIOVISUAL";
		conn=Conexion.getConexion();
		st=conn.createStatement();
		rs = st.executeQuery(selMaxCod);
		rs.next();
		maxCod = rs.getInt(1);
		System.out.format("%s\n", maxCod);
//		String query = " insert into users (first_name, last_name, date_created, is_admin, num_points)"
//		        + " values (?, ?, ?, ?, ?)";
//	
//	      // create the mysql insert preparedstatement
//	      PreparedStatement preparedStmt = conn.prepareStatement(query);
//	      preparedStmt.setString (1, "Barney");
//	      preparedStmt.setString (2, "Rubble");
//	      preparedStmt.setDate   (3, startDate);
//	      preparedStmt.setBoolean(4, false);
//	      preparedStmt.setInt    (5, 5000);
//	
//	      // execute the preparedstatement
//	      preparedStmt.execute();
	      
		String insertGenePeli = "INSERT INTO EJEMPLARAUDIOVISUAL VALUES ("+maxCod+",'"+titulo+"',"+anyo+",(SELECT DIRECTOR.CODIGO FROM PARTICIPANTE,DIRECTOR WHERE DIRECTOR.CODIGO = PARTICIPANTE.CODIGO AND participante.NOMBRE = '"+director+"'),(SELECT codigo FROM PAIS WHERE descripcion='"+nacionalidad+"'),'"+sinopsis+"')";
		String insertPeli = "INSERT INTO PELICULA VALUES("+maxCod+",'"+genero+"')";
		result = st.executeUpdate(insertGenePeli);
		st.executeUpdate(insertPeli);
		System.out.format("Filas actualizada: %d\n", result);
		Conexion.cerrar();
		return true;
	}
	
	// GETTERS & SETTERS
	private Connection getConn() {
		return conn;
	}

	private void setConn(Connection conn) {
		this.conn = conn;
	}

	private String getQuery() {
		return query;
	}

	private void setQuery(String query) {
		this.query = query;
	}

	private Statement getSt() {
		return st;
	}

	private void setSt(Statement st) {
		this.st = st;
	}

	private PreparedStatement getPs() {
		return ps;
	}

	private void setPs(PreparedStatement ps) {
		this.ps = ps;
	}

	private ResultSet getRs() {
		return rs;
	}

	private void setRs(ResultSet rs) {
		this.rs = rs;
	}
	
}
