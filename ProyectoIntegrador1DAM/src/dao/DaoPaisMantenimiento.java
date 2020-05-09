package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.Pais;

public class DaoPaisMantenimiento {

	private String query;
	private Connection conn;
	private Statement st;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public ArrayList<Pais> obtenerListaPaises () {
		
		Pais pais = new Pais();
		ArrayList<Pais> listaPaises =  new ArrayList<Pais>();
		
		this.setQuery("select * from PAIS order by CODIGO");
		
		try  {
			
			this.setConn(Conexion.getConexion());
			this.setPs(this.getConn().prepareStatement(this.getQuery()));
			this.setRs(this.getPs().executeQuery());
			
			while (this.getRs().next()) {
				pais = new Pais(this.getRs().getInt(1), this.getRs().getString(2));
				listaPaises.add(pais);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(null, "Error de conexión.", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} finally {
			try {
				Conexion.cerrar();
				this.getConn().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return listaPaises;
		
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
