package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.Director;
import modelo.GeneroPelicula;
import modelo.Sexo;
import vista.DialogoDirectorAlta;
import vista.DialogoDirectorBaja;

/**
 * Esta clase está dedicada al manejo de datos de directores en la base de datos
 * @author Sergio Fernández Rivera
 * @since 16/05/2020
 * @version 1.0
 * 
 */

public class DaoDirectorMantenimiento {
	
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
	 * Método para insertar un director en la BBDD
	 * @param nombre nombre del director
	 * @param fecha fecha en que nació
	 * @param sexo sexo del director
	 * @param generoPreferido su genero preferido de peliculas
	 * @param nacionalidad país de nacimiento
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void darAltaDirector (DialogoDirectorAlta dAlta) throws ClassNotFoundException, SQLException {
		
		int codigo = 0;
		//monta la query
		this.setQuery("select max(codigo) from participante");
		
		this.setConn(Conexion.getConexion());
		this.setPs(this.getConn().prepareStatement(this.getQuery()));
		this.setRs(this.getPs().executeQuery());
		
		if(this.getRs().next()) {
			codigo=this.getRs().getInt(1)+1;
		}
		
		String nombre = dAlta.getTextNombre().getText();
		String fecha = dAlta.getTextFecha().getText();
		//Date date = Date.valueOf(fecha);
		String sexo = dAlta.getBg().getSelection().getActionCommand().toUpperCase();
		String generoPreferido = dAlta.getTextGenero().getText();
		String nacionalidad = dAlta.getComboBoxPais().getSelectedItem().toString();
		
		String insertParticipante = "INSERT INTO PARTICIPANTE VALUES ("+codigo+ ",'" +nombre+ "',DATE('" + fecha + "'),'" +sexo+ "', (SELECT codigo FROM PAIS WHERE descripcion='"+nacionalidad+"'))";
		this.setQuery(insertParticipante);
		st = conn.createStatement();
		st.executeUpdate(this.getQuery());
		
		String insertDirector = "INSERT INTO DIRECTOR VALUES ("+codigo+ ",'" +generoPreferido+ "')";
		this.setQuery(insertDirector);
		st.executeUpdate(this.getQuery());
		conn.commit();
		st.close();
		Conexion.cerrar();
		
	}
	
	/** 
	 * Método que busca el código del director y lo guarda
	 * @param direc 
	 * @param directores array donde se almacena
	 * @return Array con los directores
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ArrayList<String> obtenerNombreDirectores () throws ClassNotFoundException, SQLException {
		
		String direc;
		ArrayList<String> directores =  new ArrayList<String>();
		
												// Monta la query a ejecutar
		this.setQuery("SELECT participante.nombre FROM director INNER JOIN participante ON director.codigo = participante.codigo");
			
		this.setConn(Conexion.getConexion());
		this.setPs(this.getConn().prepareStatement(this.getQuery()));
		this.setRs(this.getPs().executeQuery());
		
		while (this.getRs().next()) {
			direc=this.getRs().getString(1);
			directores.add(direc);
		}
		
		Conexion.cerrar();
		this.st.close();
		return directores;
		
	}
	/** 
	 * Método que busca el director con un codigo especifico y lo devuelve creando un objeto
	 * @param codigo del director
	 * @return Un objeto director con los datos de la consulta
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Director obtenerDirector(int codigo) throws ClassNotFoundException, SQLException {
		DaoPaisMantenimiento daoPaisMantenimiento = new DaoPaisMantenimiento();
		Director direc;
		
		conn = Conexion.getConexion();
		st=conn.createStatement();
		rs = st.executeQuery("SELECT participante.NOMBRE, participante.FECHANACIMIENTO , participante.SEXO , director.GENEROPREFERIDO, participante.NACIONALIDAD "
				+ "FROM participante INNER JOIN director ON participante.codigo = director.CODIGO WHERE director.codigo="+codigo);
		rs.next();
		direc = new Director(codigo,rs.getString(1),rs.getDate(2),Sexo.valueOf(rs.getString(3).toUpperCase()),GeneroPelicula.valueOf(rs.getString(4).toUpperCase()),daoPaisMantenimiento.obtenerPais(rs.getInt(5)));
		
		Conexion.cerrar();
		st.close();
		return direc;
		
	}
	
	/**
	 * Método para dar de baja a un director
	 * @param dialogo
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void darBajaDirector (DialogoDirectorBaja dialogo) throws ClassNotFoundException, SQLException{
	
		int codigo = Integer.parseInt(dialogo.getTextFieldCodigo().getText());

		this.setConn(Conexion.getConexion());
		st = conn.createStatement();
		String borrarDirector = "DELETE FROM DIRECTOR WHERE CODIGO=" + codigo;
		st.executeUpdate(borrarDirector);
		String borrarParticipante = "DELETE FROM PARTICIPANTE WHERE CODIGO=" + codigo;
		st.executeUpdate(borrarParticipante);
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
