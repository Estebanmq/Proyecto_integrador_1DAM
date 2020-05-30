package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Participante;
/**
 * Clase que realiza el mantenimiento de participantes
 * 
 * @see Participante
 * 
 * @author Jose Manuel de Dios
 * @version 1.0
 * @since 10/05/2020
 */
public class DaoParticipanteMantenimiento {
	
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
	 * Método que obtiene el tipo de participante. Si el código recibido no existe devuelve null 
	 * 
	 * @param codigo Código de participante a localizar
	 * @return String Tipo de participante
	 * @throws ClassNotFoundException si la clase no es localizada 
	 * @throws SQLException si el acceso a la base de datos ha generado un error
	 */
	public String obtenerTipoParticipante(Integer codigo) throws ClassNotFoundException, SQLException {
		
		String tipo = null;
			
										// Monta la query para obtener el tipo de participante (Director o Intérprete)
		this.setQuery("select * from PARTICIPANTE_OBJ where CODIGO = ?");

										// Obtiene la conexión y ejecuta la query
		this.setConn(Conexion.getConexion());
		this.setPs(this.getConn().prepareStatement(this.getQuery()));
		this.getPs().setInt(1, codigo);
		this.setRs(this.getPs().executeQuery());
		
										// Si ha encontrado al participante guarda su tipo, si no devuelve null
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
