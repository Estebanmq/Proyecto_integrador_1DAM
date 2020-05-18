package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.Director;
import modelo.GeneroPelicula;
import modelo.Interprete;
import modelo.Pais;
import modelo.Participante;
import modelo.Sexo;

public class DaoParticipanteMantenimiento {
	
	private String query;
	private Connection conn;
	private Statement st;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public Participante obtenerParticipante(Integer codigo) throws ClassNotFoundException, SQLException {
		
		Participante participante = null;
		Pais pais = null;
		Sexo sexo = null;
		GeneroPelicula genero = null;
		
										// Monta la query a ejecutar
		this.setQuery("select * from PARTICIPANTE where CODIGO = ?");

										// Obtiene la conexión y ejecuta la query
		this.setConn(Conexion.getConexion());
		this.setPs(this.getConn().prepareStatement(this.getQuery()));
		this.getPs().setInt(1, codigo);
		this.setRs(this.getPs().executeQuery());

										// Carga en Participante los datos devueltos
		this.getRs().next();
		pais = new DaoPaisMantenimiento().obtenerPais(this.getRs().getInt(6));
		if (this.getRs().getString(4).equalsIgnoreCase("MASCULINO")) {
			sexo = Sexo.MASCULINO;
		} else {
			sexo = Sexo.FEMENINO;				
		}
//		if (this.getRs().getString(5).equals(anObject)) {
//			
//		}
		if (this.getRs().getString(1).equalsIgnoreCase("D")) {
			participante = new Director(this.getRs().getInt(1), this.getRs().getString(2), this.getRs().getDate(3), sexo, genero, pais);				
		} else {
			participante = new Interprete(this.getRs().getInt(1), this.getRs().getString(2), this.getRs().getDate(3), sexo, this.getRs().getDouble(4), pais);				
			
		}

			Conexion.cerrar();

			return participante;
		
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
