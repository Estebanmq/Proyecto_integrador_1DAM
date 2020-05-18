package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import modelo.FiltroListadoParticipantes;
import modelo.ListaParticipante;
import modelo.Pais;
/**
 * 
 * @author Jose Manuel
 * @version 1.0
 * @see 
 */
public class DaoListadoParticipantes {
	
	private String query;
	private Connection conn;
	private Statement st;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public DaoListadoParticipantes(Connection conn) {
		this.setConn(conn);
	}

	public ArrayList<ListaParticipante> obtenerListaParticipantes() throws ClassNotFoundException, SQLException {
		
		ListaParticipante listaParticipante = new ListaParticipante();
		ArrayList<ListaParticipante> arrayList = new ArrayList<ListaParticipante>();
		HashMap<Integer, String> listaPaises = new HashMap<Integer, String>();
		
		listaPaises = this.obtenerPaises(conn);
		
		// Monta la query a ejecutar
		this.setQuery("SELECT codigo, nombre, nacionalidad FROM participante");

		this.setPs(this.getConn().prepareStatement(this.getQuery()));
		this.setRs(this.getPs().executeQuery());
		
		while (this.getRs().next()) {
			listaParticipante = new ListaParticipante(this.getRs().getInt(1), this.getRs().getString(2), listaPaises.get(this.getRs().getInt(3)));
			arrayList.add(listaParticipante);
		}
		
		return arrayList;
		
	}

	public ArrayList<ListaParticipante> obtenerListaParticipantes(FiltroListadoParticipantes filtro) throws ClassNotFoundException, SQLException {
		
		ListaParticipante listaParticipante = new ListaParticipante();
		ArrayList<ListaParticipante> arrayList = new ArrayList<ListaParticipante>();
		HashMap<Integer, String> listaPaises = new HashMap<Integer, String>();
		
		listaPaises = this.obtenerPaises(conn);
		
												// Monta la query a ejecutar
		if (filtro.isDirector() && filtro.isParticipipante()) {
			this.setQuery("SELECT codigo, nombre, nacionalidad FROM participante");
		} else if (filtro.isDirector() && !filtro.isParticipipante()) {
			this.setQuery("SELECT participante.codigo, participante.nombre, participante.nacionalidad FROM director INNER JOIN participante ON director.codigo = participante.codigo");
		} else {
			this.setQuery("SELECT participante.codigo, participante.nombre, participante.nacionalidad FROM interprete INNER JOIN participante ON interprete.codigo = participante.codigo");
		}
		query = query.concat(" WHERE TRUE");
			
		if (!filtro.getNombre().isEmpty()) {
			query = query.concat(" AND participante.nombre = '").concat(filtro.getNombre()).concat("'");			
		}
		if (filtro.getPais() != 0) {
			query = query.concat(" AND participante.nacionalidad = ").concat(filtro.getPais().toString());
		}
		if (!filtro.isSexoFemenino() || !filtro.isSexoMasculino()) {
			query = query.concat(" AND participante.sexo = ");
			if (filtro.isSexoFemenino()) {
				query = query.concat("'FEMENINO'");				
			} else {
				query = query.concat("'MASCULINO'");				
			}			
		}
		this.setPs(this.getConn().prepareStatement(this.getQuery()));
		this.setRs(this.getPs().executeQuery());
		
		while (this.getRs().next()) {
			listaParticipante = new ListaParticipante(this.getRs().getInt(1), this.getRs().getString(2), listaPaises.get(this.getRs().getInt(3)));
			arrayList.add(listaParticipante);
		}
		
		return arrayList;
		
	}
	
	public HashMap<Integer, String> obtenerPaises(Connection conn) throws ClassNotFoundException, SQLException {
		
		HashMap<Integer, String> listaPaises = new HashMap<Integer, String>();
		ArrayList<Pais> arrayPaises = new ArrayList<Pais>(); 
		
									// 	Instancia al DAO y obtiene el array de países
		DaoPaisMantenimiento daoPaisMantenimiento = new DaoPaisMantenimiento();
		arrayPaises = daoPaisMantenimiento.obtenerListaPaises();
		
		for (Pais p : arrayPaises) {
			listaPaises.put(p.getCodigo(), p.getDescripcion());
		}

		return listaPaises;

	}

	// GETTERS & SETTERS
	private String getQuery() {
		return query;
	}

	private void setQuery(String query) {
		this.query = query;
	}

	private Connection getConn() {
		return conn;
	}

	private void setConn(Connection conn) {
		this.conn = conn;
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
