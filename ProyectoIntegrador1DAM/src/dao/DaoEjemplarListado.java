package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import modelo.FiltroEjemplarListado;
import modelo.ListaEjemplar;
import modelo.Pais;
import modelo.Participante;
/**
 * Clase que obtiene los participantes para mostrar en pantalla
 * 
 * @see Participante
 * 
 * @author Jose Manuel de Dios
 * @version 1.0
 * @since 24/05/2020
 */
public class DaoEjemplarListado {
	
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
	 * Método constructor al que se le pasa la conexión a utilizar en la instancia
	 * 
	 * @param conn
	 */
	public DaoEjemplarListado(Connection conn) {
		this.setConn(conn);
	}

	/**
	 * Método que obtiene de BD la lista de ejemplares
	 * 
	 * @see ListaEjemplar
	 * 
	 * @return Colección de ListaEjemplar
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ArrayList<ListaEjemplar> obtenerListaEjemplares() throws ClassNotFoundException, SQLException {
		
		ListaEjemplar listaEjemplar = new ListaEjemplar();
		ArrayList<ListaEjemplar> arrayList = new ArrayList<ListaEjemplar>();
		HashMap<Integer, String> listaPaises = new HashMap<Integer, String>();
		
		listaPaises = this.obtenerPaises(conn);
		
		// Monta la query a ejecutar
		this.setQuery("select CODIGO, TITULO, NACIONALIDAD from EJEMPLARAUDIOVISUAL order by EJEMPLARAUDIOVISUAL.CODIGO");

		this.setPs(this.getConn().prepareStatement(this.getQuery()));
		this.setRs(this.getPs().executeQuery());
		
		while (this.getRs().next()) {
			listaEjemplar = new ListaEjemplar(this.getRs().getInt(1), this.getRs().getString(2), listaPaises.get(this.getRs().getInt(3)));
			arrayList.add(listaEjemplar);
		}
		
		return arrayList;
		
	}

	/**
	 * 
	 * Método que obtiene de BD la lista de ejemplares en función del filtro recibido por parámetros
	 * 
	 * @param filtro Filtro aplicado en pantalla por el usuario
	 * 
	 * @return Colección de ListaEjemplar
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ArrayList<ListaEjemplar> obtenerListaEjemplares(FiltroEjemplarListado filtro) throws ClassNotFoundException, SQLException {
		
		ListaEjemplar listaEjemplar = new ListaEjemplar();
		ArrayList<ListaEjemplar> arrayList = new ArrayList<ListaEjemplar>();
		HashMap<Integer, String> listaPaises = new HashMap<Integer, String>();
		
		listaPaises = this.obtenerPaises(conn);
		
												// Monta la query a ejecutar
		if (filtro.isPelicula() && filtro.isDocumental()) {
			this.setQuery("select CODIGO, TITULO, NACIONALIDAD from EJEMPLARAUDIOVISUAL");
		} else if (filtro.isPelicula() && !filtro.isDocumental()) {
			this.setQuery("select EJEMPLARAUDIOVISUAL.CODIGO, EJEMPLARAUDIOVISUAL.TITULO, EJEMPLARAUDIOVISUAL.NACIONALIDAD from PELICULA inner join EJEMPLARAUDIOVISUAL on PELICULA.CODIGO = EJEMPLARAUDIOVISUAL.CODIGO");
		} else {
			this.setQuery("select EJEMPLARAUDIOVISUAL.CODIGO, EJEMPLARAUDIOVISUAL.TITULO, EJEMPLARAUDIOVISUAL.NACIONALIDAD from DOCUMENTAL inner join EJEMPLARAUDIOVISUAL on DOCUMENTAL.CODIGO = EJEMPLARAUDIOVISUAL.CODIGO");
		}
		query = query.concat(" WHERE TRUE");
		
		if (!filtro.getAnyo().equals(0)) {
			query = query.concat(" and EJEMPLARAUDIOVISUAL.ANYO = ").concat(filtro.getAnyo().toString());			
		}
			
		if (!filtro.getTitulo().isEmpty()) {
			query = query.concat(" and EJEMPLARAUDIOVISUAL.TITULO = '").concat(filtro.getTitulo()).concat("'");			
		}
		if (filtro.getPais() != 0) {
			query = query.concat(" and EJEMPLARAUDIOVISUAL.nacionalidad = ").concat(filtro.getPais().toString());
		}
		
		query = query.concat(" order by EJEMPLARAUDIOVISUAL.CODIGO");

		this.setPs(this.getConn().prepareStatement(this.getQuery()));
		this.setRs(this.getPs().executeQuery());
		
		while (this.getRs().next()) {
			listaEjemplar = new ListaEjemplar(this.getRs().getInt(1), this.getRs().getString(2), listaPaises.get(this.getRs().getInt(3)));
			arrayList.add(listaEjemplar);
		}
		
		return arrayList;
		
	}
	
	/**
	 * 
	 * Método que obtiene de BD la lista de países existentes en base de datos
	 * 
	 * @param conn Conexión
	 * @return Coleccion de paises
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
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
