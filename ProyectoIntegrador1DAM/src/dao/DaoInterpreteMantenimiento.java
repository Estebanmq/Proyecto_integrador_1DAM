package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

import modelo.Interprete;
import modelo.ListaInterprete;
import modelo.Sexo;

/**
 * Esta clase está dedicada al manejo de datos de intérpretes en la base de datos
 * @author Sergio Fernández Rivera
 * @since 22/05/2020
 * @version 1.0
 *
 */
public class DaoInterpreteMantenimiento {

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
	 * Método que obtiene de BD la lista de intérpretes
	 * 
	 * @see ListaInterprete
	 * 
	 * @return HashSet<ListaInterprete>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public HashSet<ListaInterprete> obtenerListaInterpretes() throws ClassNotFoundException, SQLException {

		
		ListaInterprete listaInterprete = new ListaInterprete();
		HashSet<ListaInterprete> hashset = new HashSet<ListaInterprete>();
		
																	// Monta la query a ejecutar
		this.setQuery("SELECT participante.codigo, participante.nombre FROM participante INNER JOIN interprete ON participante.codigo = interprete.codigo");

																	// Obtiene la conexión y ejecuta la query
		this.setConn(Conexion.getConexion());
		this.setPs(this.getConn().prepareStatement(this.getQuery()));
		this.setRs(this.getPs().executeQuery());
		
		while (this.getRs().next()) {
			listaInterprete = new ListaInterprete(this.getRs().getInt(1), this.getRs().getString(2));
			hashset.add(listaInterprete);
		}
		
		Conexion.cerrar();
		
		return hashset;
		
	}
	
	/** 
	 * Método que busca el intérprete con un código específico y lo devuelve creando un objeto
	 * @param codigo Código del intérprete
	 * @return Un objeto interprete con los datos de la consulta
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Interprete obtenerInterprete(Integer codigo) throws ClassNotFoundException, SQLException {
		DaoPaisMantenimiento daoPaisMantenimiento = new DaoPaisMantenimiento();
		Interprete interprete;
		
		conn = Conexion.getConexion();
		st=conn.createStatement();
		rs = st.executeQuery("SELECT participante.NOMBRE, participante.FECHANACIMIENTO , participante.SEXO, interprete.cache, participante.NACIONALIDAD "
				+ "FROM participante INNER JOIN interprete ON participante.codigo = interprete.CODIGO WHERE interprete.codigo="+codigo);
		rs.next();
		interprete = new Interprete(codigo, rs.getString(1),rs.getDate(2),Sexo.valueOf(rs.getString(3).toUpperCase()),rs.getDouble(4) ,daoPaisMantenimiento.obtenerPais(rs.getInt(5)));
		
		Conexion.cerrar();
		
		return interprete;
		
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
