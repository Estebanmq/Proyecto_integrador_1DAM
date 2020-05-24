package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Normalizer;

import modelo.Documental;
import modelo.GeneroDocumental;
import modelo.Pelicula;
/**
 * Esta clase está dedicada al manejo de datos de documentales en la base de datos
 * @author Esteban Martínez
 * @since 24/05/2020
 * @version 1.0
 *
 */
public class DaoDocumentalMantenimiento {

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
	 * Método para insertar un documental en la BBDD
	 * @param titulo Titulo del documental
	 * @param anyo Año de estreno del documental
	 * @param director Director del documental
	 * @param nacionalidad Nacionalidad del documental
	 * @param sinopsis Sinopsis del documental
	 * @param genero Genero al que pertenece el documental
	 * 
	 * @return True cuando inserta todos los datos correctamente
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public boolean insertarDocumental(String titulo,int anyo,String director,String nacionalidad,String sinopsis,String genero) throws SQLException, ClassNotFoundException {
		int maxCod;
		
		genero.toUpperCase(); //El genero lo muestro en minuscula pero se almacena en mayuscula
		
		String selMaxCod = "SELECT COALESCE(max(codigo),0)+1 FROM EJEMPLARAUDIOVISUAL"; //Recupero el código mas alto + 1, si es 0 asigna 1
		conn=Conexion.getConexion();
		st=conn.createStatement();
		rs = st.executeQuery(selMaxCod);
		rs.next();
		maxCod = rs.getInt(1); //Almaceno el código
		
		//Para poder almacenar una película antes tengo que almacenar un ejemplar audiovisual
		String insertGeneDocu = "INSERT INTO EJEMPLARAUDIOVISUAL VALUES ("+maxCod+",'"+titulo+"',"+anyo+",(SELECT DIRECTOR.CODIGO FROM PARTICIPANTE,DIRECTOR WHERE DIRECTOR.CODIGO = PARTICIPANTE.CODIGO AND participante.NOMBRE = '"+director+"'),(SELECT codigo FROM PAIS WHERE descripcion='"+nacionalidad+"'),'"+sinopsis+"')";
		String insertDocu = "INSERT INTO DOCUMENTAL VALUES("+maxCod+",'"+genero+"')";
		//Ejecuto ambos insert
		st.executeUpdate(insertGeneDocu);
		st.executeUpdate(insertDocu);
		
		Conexion.cerrar();
		st.close();
		return true;
	}
	
	/** 
	 * Método que devuelve un documental a partir de su código
	 * @param codigo Código de la documental
	 * @return El objeto de tipo documental con los datos recuperados de la BBDD
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Documental buscarDocu(Integer codigo) throws ClassNotFoundException, SQLException {
		
		DaoPaisMantenimiento daoPaisMantenimiento = new DaoPaisMantenimiento();
		DaoDirectorMantenimiento daoDirectorMantenimiento = new DaoDirectorMantenimiento();
		Documental d = new Documental();
		int codDirec;
		String selDocu;
		String aux;
		
		conn=Conexion.getConexion();
		st=conn.createStatement();
		selDocu = "SELECT ejemplaraudiovisual.TITULO , ejemplaraudiovisual.ANYO , participante.CODIGO, ejemplaraudiovisual.SIPNOSIS , "+
				"ejemplaraudiovisual.NACIONALIDAD, documental.GENERODOCUMENTAL FROM EJEMPLARAUDIOVISUAL " + 
				"INNER JOIN DOCUMENTAL ON ejemplaraudiovisual.CODIGO = DOCUMENTAL.CODIGO " + 
				"INNER JOIN PARTICIPANTE ON participante.CODIGO = ejemplaraudiovisual.DIRECTOR WHERE ejemplaraudiovisual.codigo="+codigo;
		rs = st.executeQuery(selDocu);
		if (rs.next()) {
			d.setCodigo(codigo);
			d.setTitulo(rs.getString(1));
			d.setAnyo(rs.getInt(2));
			codDirec = rs.getInt(3);
			d.setSinopsis(rs.getString(4));
			
			//Formateo el String del genero pelicula para quitar todas las tildes
			aux = Normalizer.normalize(rs.getString(6).toUpperCase(), Normalizer.Form.NFD);
		    aux = aux.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
			aux =  aux.replace(" ","");
			d.setGenero(GeneroDocumental.valueOf(aux));
			d.setNacionalidad(daoPaisMantenimiento.obtenerPais(rs.getInt(5)));
			d.setDirector(daoDirectorMantenimiento.obtenerDirector(codDirec));
		} else {
			d.setTitulo("Documental no existe");;
		}
		
		Conexion.cerrar();
		st.close();
		return d;
	}

	/** 
	 * Método que elimina un documental a partir de su código
	 * @param codigo Código del documental
	 * @return El número de filas actualizadas
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int borrarPelicula(int codigo) throws ClassNotFoundException, SQLException {
		int result = 0;
		conn=Conexion.getConexion();
		st=conn.createStatement();
		result += st.executeUpdate("DELETE FROM DOCUMENTAL WHERE CODIGO = "+codigo);
		result += st.executeUpdate("DELETE FROM EJEMPLARAUDIOVISUAL WHERE CODIGO = "+codigo);
		conn.commit();
		Conexion.cerrar();
		st.close();
		return result;
	}

	/**
	 * Método que actualiza un documental a partir de su código
	 * 
	 * @param d Documental
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int actualizarDocumental(Documental d) throws ClassNotFoundException, SQLException {
		DaoPaisMantenimiento daoPaisMantenimiento = new DaoPaisMantenimiento();
		DaoDirectorMantenimiento daoDirectorMantenimiento = new DaoDirectorMantenimiento();
		int result = 0;
		conn=Conexion.getConexion();
		st=conn.createStatement();
		result += st.executeUpdate("UPDATE EJEMPLARAUDIOVISUAL SET TITULO = '"+d.getTitulo()+"', SIPNOSIS = '"+ d.getSinopsis()+"', ANYO = "+d.getAnyo()
		+", DIRECTOR = "+d.getDirector().getCodigo()
		+", NACIONALIDAD = "+d.getNacionalidad().getCodigo()+" WHERE CODIGO = "+d.getCodigo());
		result += st.executeUpdate("UPDATE DOCUMENTAL SET GENERODOCUMENTAL = '"+d.getGenero().getDescripcion()+"' WHERE CODIGO ="+d.getCodigo());
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
