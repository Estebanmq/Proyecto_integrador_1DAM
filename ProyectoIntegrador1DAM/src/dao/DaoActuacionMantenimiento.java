package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import modelo.Actuacion;
import modelo.ListaPersonaje;

public class DaoActuacionMantenimiento {

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
	 * Método que obtiene de BD la lista de actuaciones de un código de película
	 * 
	 * @see Actuacion
	 * 
	 * @param codigo Código de película
	 * @return Colección de ListaPersonaje
	 * @throws ClassNotFoundException si la clase no es localizada 
	 * @throws SQLException si el acceso a la base de datos ha generado un error
	 */
	public HashSet<ListaPersonaje> obtenerActuaciones(Integer codigo) throws ClassNotFoundException, SQLException {

		HashSet<ListaPersonaje> hashset = new HashSet<ListaPersonaje>();
		
																	// Monta la query a ejecutar
		this.setQuery("SELECT participante.codigo, participante.nombre, actuacion.nombrepersonaje FROM participante "
				+ "INNER JOIN actuacion ON participante.codigo = actuacion.codigointerprete"
				+ " WHERE actuacion.codigopelicula = ?"
				+ " ORDER BY participante.codigo");

																	// Obtiene la conexión y ejecuta la query
		this.setConn(Conexion.getConexion());
		this.setPs(this.getConn().prepareStatement(this.getQuery()));
		this.getPs().setInt(1, codigo);
		this.setRs(this.getPs().executeQuery());
		
		while (this.getRs().next()) {
			hashset.add(new ListaPersonaje(this.getRs().getInt(1), this.getRs().getString(2), this.getRs().getString(3)));
		}
		
		Conexion.cerrar();
		
		return hashset;
		
	}
	
	public boolean guardarActuaciones(Integer codigo, HashSet<ListaPersonaje> listaPersonajes) throws ClassNotFoundException, SQLException {
		
																// Obtiene la conexión y quita el autocommit
		this.setConn(Conexion.getConexion());
		this.getConn().setAutoCommit(false);
		
		
																// Monta la query para borrar las actuaciones de la película y la ejecuta 
		this.setQuery("delete from ACTUACION where CODIGOPELICULA = ?");
		this.setPs(this.getConn().prepareStatement(this.getQuery()));
		this.getPs().setInt(1, codigo);
		this.getPs().execute();


		
																// Monta la query para dar de alta las actuaciones y la ejecuta tantas 
																// veces como ocurrencias haya en el hashset recibido por parámetros 
		this.setQuery("insert into ACTUACION values (?, ?, ?)");
		this.setPs(this.getConn().prepareStatement(this.getQuery()));
		if (listaPersonajes.size() > 0) {
			for (ListaPersonaje l : listaPersonajes) {
				this.getPs().setInt(1, codigo);
				this.getPs().setInt(2, l.getCodigo());
				this.getPs().setString(3, l.getNombrePersonaje());
				this.getPs().execute();
			}
			
		}

		this.getConn().commit();
		this.getConn().setAutoCommit(true);
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
