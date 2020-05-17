package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import vista.DialogoDirectorAlta;

public class DaoDirectorMantenimiento {
	
	private String query;
	private Connection conn;
	private Statement st;
	private PreparedStatement ps;
	private ResultSet rs;
	
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
		String sexo = dAlta.getBg().getSelection().getActionCommand();
		String generoPreferido = dAlta.getTextGenero().getText();
		String nacionalidad = dAlta.getComboBoxPais().getSelectedItem().toString();
		
		String insertParticipante = "INSERT INTO PARTICIPANTE VALUES ("+codigo+ ",'" +nombre+ "',DATE('" + fecha + "'),'" +sexo+ "', (SELECT codigo FROM PAIS WHERE descripcion='"+nacionalidad+"'))";
		this.setQuery(insertParticipante);
		st = conn.createStatement();
		st.executeUpdate(this.getQuery());
		
		String insertDirector = "INSERT INTO DIRECTOR VALUES ("+codigo+ ",'" +generoPreferido+ "')";
		this.setQuery(insertDirector);
		st.executeUpdate(this.getQuery());
		Conexion.cerrar();
		
	}
	
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
		
		return directores;
		
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
