package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.Pais;
/**
 * 
 * Clase que realiza el mantenimiento de países en BD
 * 
 * @author Jose Manuel de Dios
 * @version 1.0
 * @since 10/05/2020
 */
public class DaoPaisMantenimiento {

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
	 * 
	 * Método que obtiene la lista de países de la BD  
	 * 
	 * @see ArrayList
	 * @see Pais
	 * 
	 * @return ArrayList<Pais>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ArrayList<Pais> obtenerListaPaises () throws ClassNotFoundException, SQLException {
		
		Pais pais = new Pais();
		ArrayList<Pais> listaPaises =  new ArrayList<Pais>();
		
												// Monta la query a ejecutar
		this.setQuery("select * from PAIS order by CODIGO");
		
												// Obtiene la conexión y ejecuta la query
		this.setConn(Conexion.getConexion());
		this.setPs(this.getConn().prepareStatement(this.getQuery()));
		this.setRs(this.getPs().executeQuery());
											
												// Carga los paises devueltos por la select
		while (this.getRs().next()) {
			pais = new Pais(this.getRs().getInt(1), this.getRs().getString(2));
			listaPaises.add(pais);
		}
		
		Conexion.cerrar();
		
		return listaPaises;
		
	}
	
	/**
	 * 
	 * Método que obtiene un país en función del código recibido por parámetros
	 * 
	 * @param codigo Código del país a localizar 
	 * @return Pais
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Pais obtenerPais(Integer codigo) throws ClassNotFoundException, SQLException {
		
		Pais pais = null;
		
												// Monta la query a ejecutar
		this.setQuery("select * from PAIS where CODIGO = ?");
			
												// Obtiene la conexión y ejecuta la query
		this.setConn(Conexion.getConexion());
		this.setPs(this.getConn().prepareStatement(this.getQuery()));
		this.getPs().setInt(1, codigo);
		this.setRs(this.getPs().executeQuery());

												// Carga en País los datos devuelvos
		if (this.getRs().next()) {
			pais = new Pais(this.getRs().getInt(1), this.getRs().getString(2));			
		} else {
			pais = new Pais(codigo, "País no existe en BD");
		}
		
		Conexion.cerrar();
		
		return pais;
		
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
