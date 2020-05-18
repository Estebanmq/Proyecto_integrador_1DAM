package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Normalizer;

import modelo.Director;
import modelo.GeneroPelicula;
import modelo.Pais;
import modelo.Pelicula;
import modelo.Sexo;
/**
 * Esta clase está dedicada al manejo de datos de películas en la base de datos
 * @author Esteban Martínez
 * @since 15/05/2020
 * @version 1.0
 *
 */
public class DaoPeliculaMantenimiento {

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
	 * Método para insertar una película en la BBDD
	 * @param titulo Titulo de la película
	 * @param anyo Año de estreno de la película
	 * @param director Director de la película
	 * @param nacionalidad Nacionalidad de la película
	 * @param sinopsis Sinopsis de la película
	 * @param genero Genero al que pertenece la película
	 * @return True cuando inserta todos los datos correctamente
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public boolean insertarPelicula(String titulo,int anyo,String director,String nacionalidad,String sinopsis,String genero) throws SQLException, ClassNotFoundException {
		int maxCod;
		
		genero.toUpperCase(); //El genero lo muestro en minuscula pero se almacena en mayuscula
		
		String selMaxCod = "SELECT COALESCE(max(codigo),0)+1 FROM EJEMPLARAUDIOVISUAL"; //Recupero el código mas alto + 1, si es 0 asigna 1
		conn=Conexion.getConexion();
		st=conn.createStatement();
		rs = st.executeQuery(selMaxCod);
		rs.next();
		maxCod = rs.getInt(1); //Almaceno el código
		
//		String query = " insert into users (first_name, last_name, date_created, is_admin, num_points)"
//		        + " values (?, ?, ?, ?, ?)";
//	
//	      // create the mysql insert preparedstatement
//	      PreparedStatement preparedStmt = conn.prepareStatement(query);
//	      preparedStmt.setString (1, "Barney");
//	      preparedStmt.setString (2, "Rubble");
//	      preparedStmt.setDate   (3, startDate);
//	      preparedStmt.setBoolean(4, false);
//	      preparedStmt.setInt    (5, 5000);
//	
//	      // execute the preparedstatement
//	      preparedStmt.execute();
	      
		//Para poder almacenar una película antes tengo que almacenar un ejemplar audiovisual
		String insertGenePeli = "INSERT INTO EJEMPLARAUDIOVISUAL VALUES ("+maxCod+",'"+titulo+"',"+anyo+",(SELECT DIRECTOR.CODIGO FROM PARTICIPANTE,DIRECTOR WHERE DIRECTOR.CODIGO = PARTICIPANTE.CODIGO AND participante.NOMBRE = '"+director+"'),(SELECT codigo FROM PAIS WHERE descripcion='"+nacionalidad+"'),'"+sinopsis+"')";
		String insertPeli = "INSERT INTO PELICULA VALUES("+maxCod+",'"+genero+"')";
		//Ejecuto ambos insert
		st.executeUpdate(insertGenePeli);
		st.executeUpdate(insertPeli);
		
		Conexion.cerrar();
		return true;
	}
	
	/** 
	 * Método que devuelve una película a partir de su código
	 * @param cod Código de la película
	 * @return El objeto de tipo pelicula con los datos recuperados de la BBDD
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Pelicula buscarPeli(int cod) throws ClassNotFoundException, SQLException {
		DaoPaisMantenimiento daoPaisMantenimiento = new DaoPaisMantenimiento();
		DaoDirectorMantenimiento daoDirectorMantenimiento = new DaoDirectorMantenimiento();
		Pelicula p = new Pelicula();
		int codDirec;
		String selPeli;
		String aux;
		
		conn=Conexion.getConexion();
		st=conn.createStatement();
		selPeli = "SELECT ejemplaraudiovisual.TITULO , ejemplaraudiovisual.ANYO , participante.CODIGO, ejemplaraudiovisual.SIPNOSIS , "+
				"ejemplaraudiovisual.NACIONALIDAD, pelicula.GENEROPELICULA FROM EJEMPLARAUDIOVISUAL " + 
				"INNER JOIN PELICULA ON ejemplaraudiovisual.CODIGO = PELICULA.CODIGO " + 
				"INNER JOIN PARTICIPANTE ON participante.CODIGO = ejemplaraudiovisual.DIRECTOR WHERE ejemplaraudiovisual.codigo="+cod;
		rs = st.executeQuery(selPeli);
		rs.next();
		
		
		p.setCodigo(cod);
		p.setTitulo(rs.getString(1));
		p.setAnyo(rs.getInt(2));
		codDirec = rs.getInt(3);
		p.setSinopsis(rs.getString(4));
		
		//Formateo el String del genero pelicula para quitar todas las tildes
		aux = Normalizer.normalize(rs.getString(6).toUpperCase(), Normalizer.Form.NFD);
	    aux = aux.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
		p.setGenero(GeneroPelicula.valueOf(aux));
		p.setNacionalidad(daoPaisMantenimiento.obtenerPais(rs.getInt(5)));
		p.setDirector(daoDirectorMantenimiento.obtenerDirector(codDirec));
		Conexion.cerrar();
		
		return p;
	}
	
	public void borrarPelicula(int cod) {
		System.out.format("%s\n", cod);
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
