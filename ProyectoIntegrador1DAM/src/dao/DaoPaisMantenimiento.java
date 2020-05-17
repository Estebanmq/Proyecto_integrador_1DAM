package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.Pais;

public class DaoPaisMantenimiento {

	private String query;
	private Connection conn;
	private Statement st;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public ArrayList<Pais> obtenerListaPaises () throws ClassNotFoundException, SQLException {
		
		Pais pais = new Pais();
		ArrayList<Pais> listaPaises =  new ArrayList<Pais>();
		
												// Monta la query a ejecutar
		this.setQuery("select * from PAIS order by CODIGO");
			
		this.setConn(Conexion.getConexion());
		this.setPs(this.getConn().prepareStatement(this.getQuery()));
		this.setRs(this.getPs().executeQuery());
		
		while (this.getRs().next()) {
			pais = new Pais(this.getRs().getInt(1), this.getRs().getString(2));
			listaPaises.add(pais);
		}
		
		Conexion.cerrar();
		
		return listaPaises;
		
	}
	
	public Pais obtenerPais(Integer codigo) throws ClassNotFoundException, SQLException {
		
		Pais pais = null;
		
												// Monta la query a ejecutar
		this.setQuery("select * from PAIS where CODIGO = ?");
			
												// Obtiene la conexión y ejecuta la query
		this.setConn(Conexion.getConexion());
		this.setPs(this.getConn().prepareStatement(this.getQuery()));
		this.getPs().setInt(1, codigo);
		this.setRs(this.getPs().executeQuery());

												// Carga en Pais los datos devuelvos
		while (this.getRs().next()) {
			pais = new Pais(this.getRs().getInt(1), this.getRs().getString(2));
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
