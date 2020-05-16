package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import vista.DialogoDirectorAlta;

public class DaoDirectorMantenimiento {
	
	private String query;
	private Connection conn;
	private Statement st;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public void darAltaDirector (DialogoDirectorAlta dAlta) throws ClassNotFoundException, SQLException {
		
		int codigo=0;
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
		String sexo = dAlta.getBg().getSelection().getActionCommand();
		String generoPreferido = dAlta.getTextGenero().getText();
		//String nacionalidad = dAlta.getComboBoxPais().getSelectedItem();
		
		
		this.setQuery("INSERT INTO PARTICIPANTE (CODIGO, NOMBRE, FECHANACIMIENTO, SEXO, NACIONALIDAD) " + 
				"VALUES (4, 'Pepito', '1984-02-13', 'MASCULINO', 32);");
		
		this.setPs(this.getConn().prepareStatement(this.getQuery()));
		this.getPs().executeQuery();
		
		Conexion.cerrar();
		
	}
	
	
	//Getter y setter

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public Statement getSt() {
		return st;
	}

	public void setSt(Statement st) {
		this.st = st;
	}

	public PreparedStatement getPs() {
		return ps;
	}

	public void setPs(PreparedStatement ps) {
		this.ps = ps;
	}

	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

}
