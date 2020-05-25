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
import vista.DialogoInterpreteAlta;
import vista.DialogoInterpreteBaja;
import vista.DialogoInterpreteModificacion;

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
     * Método para dar de alta a un interprete
     * @param nombre nombre del interprete
     * @param fecha fecha en que nació
     * @param sexo sexo del interprete
     * @param cache el salario que cobra de base por película
     * @param nacionalidad país de nacimiento
     * @throws SQLException
     * @throws ClassNotFoundException
     */
	public void darAltaInterprete (DialogoInterpreteAlta iAlta) throws ClassNotFoundException, SQLException {
		
		int codigo = 0;
		double cache = 0;
		//monta la query
		this.setQuery("select max(codigo) from participante");
		
		this.setConn(Conexion.getConexion());
		this.setPs(this.getConn().prepareStatement(this.getQuery()));
		this.setRs(this.getPs().executeQuery());
		
		if(this.getRs().next()) {
			codigo=this.getRs().getInt(1)+1;
		}
		
		String nombre = iAlta.getTextNombre().getText();
		String fecha = iAlta.getTextFecha().getText();
		String sexo = iAlta.getBg().getSelection().getActionCommand().toUpperCase();
		String nacionalidad = iAlta.getComboBoxPais().getSelectedItem().toString();
		String cacheString = iAlta.getTextCache().getText();
		if(!cacheString.isEmpty())
			cache = Double.parseDouble(iAlta.getTextCache().getText());

		String insertParticipante = "INSERT INTO PARTICIPANTE VALUES ("+codigo+ ",'" +nombre+ "',DATE('" + fecha + "'),'" +sexo+ "', (SELECT codigo FROM PAIS WHERE descripcion='"+nacionalidad+"'))";
		this.setQuery(insertParticipante);
		st = conn.createStatement();
		st.executeUpdate(this.getQuery());
		
		String insertInterprete = "INSERT INTO INTERPRETE VALUES ("+codigo+ "," +cache+ ")";
		this.setQuery(insertInterprete);
		st.executeUpdate(this.getQuery());
		conn.commit();
		Conexion.cerrar();
		
	}
	
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
	

	/**
	 * Método para dar de baja a un interprete
	 * @param dialogo
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void darBajaInterprete (DialogoInterpreteBaja dialogo) throws ClassNotFoundException, SQLException{
		
		int codigo = Integer.parseInt(dialogo.getTextFieldCodigo().getText());
		
		this.setConn(Conexion.getConexion());
		st = conn.createStatement();
		String borrarInterprete = "DELETE FROM INTERPRETE WHERE CODIGO=" + codigo;
		st.executeUpdate(borrarInterprete);
		String borrarParticipante = "DELETE FROM PARTICIPANTE WHERE CODIGO=" + codigo;
		st.executeUpdate(borrarParticipante);
		st.close();
		Conexion.cerrar();
	}
	
	/**
	 * Método para modificar datos a un interprete
	 * @param dialogo
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void modificarInterprete(DialogoInterpreteModificacion dialogo) throws ClassNotFoundException, SQLException{
		
		int codigo = Integer.parseInt(dialogo.getTextFieldCodigo().getText());
		
		String nombre = dialogo.getTextFieldNombre().getText();
		String fecha = dialogo.getTextFieldFecha().getText();
		String sexo = dialogo.getBg().getSelection().getActionCommand().toUpperCase();
		String cacheString = dialogo.getTextFieldCache().getText();
		String nacionalidad = dialogo.getComboBoxPais().getSelectedItem().toString();
		
		this.setConn(Conexion.getConexion());
		st = conn.createStatement();
		String modificarParticipante = "UPDATE PARTICIPANTE SET NOMBRE = '" +nombre+ "', FECHANACIMIENTO = DATE('" + fecha + "'),SEXO = '" +sexo+ "', NACIONALIDAD = (SELECT codigo FROM PAIS WHERE descripcion='"+nacionalidad+"') WHERE CODIGO ="+ codigo;
		st.executeUpdate(modificarParticipante);
		String modificarDirector = "UPDATE INTERPRETE SET CACHE = '" + cacheString + "' WHERE CODIGO =" + codigo;
		st.executeUpdate(modificarDirector);
		st.close();
		Conexion.cerrar();
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
