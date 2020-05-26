package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Normalizer;

import modelo.GeneroPelicula;
import modelo.Pelicula;
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
	 * @throws ClassNotFoundException si la clase no es localizada 
	 * @throws SQLException si el acceso a la base de datos ha generado un error
	 */
	public boolean insertarPelicula(String titulo,int anyo,String director,String nacionalidad,String sinopsis,String genero) throws SQLException, ClassNotFoundException {
		int maxCod;
		
		String selMaxCod = "SELECT COALESCE(max(codigo),0)+1 FROM EJEMPLARAUDIOVISUAL"; //Recupero el código mas alto + 1, si es 0 asigna 1
		conn=Conexion.getConexion();
		st=conn.createStatement();
		rs = st.executeQuery(selMaxCod);
		rs.next();
		maxCod = rs.getInt(1); //Almaceno el código
		
		//Para poder almacenar una película antes tengo que almacenar un ejemplar audiovisual
		String insertGenePeli = "INSERT INTO EJEMPLARAUDIOVISUAL VALUES ("+maxCod+",'"+titulo+"',"+anyo+",(SELECT DIRECTOR.CODIGO FROM PARTICIPANTE,DIRECTOR WHERE DIRECTOR.CODIGO = PARTICIPANTE.CODIGO AND participante.NOMBRE = '"+director+"'),(SELECT codigo FROM PAIS WHERE descripcion='"+nacionalidad+"'),'"+sinopsis+"')";
		String insertPeli = "INSERT INTO PELICULA VALUES("+maxCod+",'"+GeneroPelicula.valueOfDescripcion(genero)+"')";
		//Ejecuto ambos insert
		st.executeUpdate(insertGenePeli);
		st.executeUpdate(insertPeli);
		
		Conexion.cerrar();
		st.close();
		return true;
	}
	
	/** 
	 * Método que devuelve una película a partir de su código
	 * @param cod Código de la película
	 * @return El objeto de tipo pelicula con los datos recuperados de la BBDD
	 * @throws ClassNotFoundException si la clase no es localizada 
	 * @throws SQLException si el acceso a la base de datos ha generado un error
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
		if (rs.next()) {
			p.setCodigo(cod);
			p.setTitulo(rs.getString(1));
			p.setAnyo(rs.getInt(2));
			codDirec = rs.getInt(3);
			p.setSinopsis(rs.getString(4));
			
			//Formateo el String del genero pelicula para quitar todas las tildes
			aux = Normalizer.normalize(rs.getString(6).toUpperCase(), Normalizer.Form.NFD);
		    aux = aux.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
			aux =  aux.replace(" ","");
			p.setGenero(GeneroPelicula.valueOf(aux));
			p.setNacionalidad(daoPaisMantenimiento.obtenerPais(rs.getInt(5)));
			p.setDirector(daoDirectorMantenimiento.obtenerDirector(codDirec));
		} else {
			p.setTitulo("Pelicula no existe");;
		}
		
		Conexion.cerrar();
		st.close();
		return p;
	}

	/** 
	 * Método que elimina una película a partir de su código
	 * @param cod Código de la película∫
	 * @return El número de filas actualizadas
	 * @throws ClassNotFoundException si la clase no es localizada 
	 * @throws SQLException si el acceso a la base de datos ha generado un error
	 */
	public int borrarPelicula(int cod) throws ClassNotFoundException, SQLException {
		int result = 0;
		conn=Conexion.getConexion();
		st=conn.createStatement();
		result += st.executeUpdate("DELETE FROM PELICULA WHERE CODIGO = "+cod);
		result += st.executeUpdate("DELETE FROM EJEMPLARAUDIOVISUAL WHERE CODIGO = "+cod);
		conn.commit();
		Conexion.cerrar();
		st.close();
		return result;
	}

	/**
	 * Método que actualiza un película
	 * 
	 * @param p Película a actualizar
	 * @return número de películas actualizadas
	 * @throws ClassNotFoundException si la clase no es localizada 
	 * @throws SQLException si el acceso a la base de datos ha generado un error
	 */
	public int actualizarPelicula(Pelicula p) throws ClassNotFoundException, SQLException {

		int result = 0;
		conn=Conexion.getConexion();
		st=conn.createStatement();
		result += st.executeUpdate("UPDATE EJEMPLARAUDIOVISUAL SET TITULO = '"+p.getTitulo()+"', SIPNOSIS = '"+ p.getSinopsis()+"', ANYO = "+p.getAnyo()
		+", DIRECTOR = "+p.getDirector().getCodigo()
		+", NACIONALIDAD = "+p.getNacionalidad().getCodigo()+" WHERE CODIGO = "+p.getCodigo());
		result += st.executeUpdate("UPDATE PELICULA SET GENEROPELICULA = '"+p.getGenero()+"' WHERE CODIGO ="+p.getCodigo());
		conn.commit();
		Conexion.cerrar();
		st.close();
		return result;
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
