package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.Participante;
/**
 * Clase que realiza el mantenimiento de ejemplares audiovisuales
 * 
 * @see Participante
 * 
 * @author Jose Manuel de Dios
 * @version 1.0
 * @since 24/05/2020
 */
public class DaoEjemplarMantenimiento {
	
	/**
	 * Query a ejecutar 
	 */
	private String query;

	/**
	 * Conexión a la BBDD
	 * @see java.sql.Connection
	 */
	private Connection conn;

	/**
	 * Statement para ejecutar sentencias SQL
	 * @see java.sql.Statement 
	 */
	private Statement st;
	
	/**
	 * PreparedStatement para ejecutar comandos SQL ya precompilados
	 * @see java.sql.PreparedStatement
	 */
	private PreparedStatement ps;
	
	/**
	 * ResultSet para almacenar el resultado de la sentencia SQL
	 * @see java.sql.ResultSet
	 */
	private ResultSet rs;
	
	/**
	 * Método que obtiene el tipo de ejemplar. Si el código recibido no existe devuelve null 
	 * 
	 * @param codigo Código de ejemplar a localizar
	 * @return String Tipo de ejemplar: P para película, D para documental
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public String obtenerTipoEjemplar(Integer codigo) throws ClassNotFoundException, SQLException {
		
		String tipo = null;
			
										// Monta la query para obtener el tipo de ejemplar (Película o Documental)
		this.setQuery("select * from EJEMPLAR_OBJ where CODIGO = ?");

										// Obtiene la conexión y ejecuta la query
		this.setConn(Conexion.getConexion());
		this.setPs(this.getConn().prepareStatement(this.getQuery()));
		this.getPs().setInt(1, codigo);
		this.setRs(this.getPs().executeQuery());
		
										// Si ha encontrado al ejemplar guarda su tipo, si no devuelve null
		if  (this.getRs().next()) {
			tipo = this.getRs().getString(1);
		}
		
		Conexion.cerrar();
		
		return tipo;
		
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
