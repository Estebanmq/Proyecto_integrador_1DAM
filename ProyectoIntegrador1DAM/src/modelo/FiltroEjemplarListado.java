package modelo;

/**
 * 
 * <h1> Filtro de participantes
 * 
 * @author Jose Manuel
 * @version 1.0
 * @since 05/2020
 */
public class FiltroEjemplarListado {
	
	private boolean pelicula; 
	private boolean documental; 
	private String titulo;
	private Integer anyo;
	private Integer pais;

	/**
	 * Método constructor del filtro de ejemplares audiovisuales
	 * 
	 * @param pelicula boolean con la marca de película
	 * @param documental boolean con la marca de documental
	 * @param titulo Título del ejemplar audiovisual
	 * @param anyo Año de producción
	 * @param pais País de producción
	 */
	public FiltroEjemplarListado(boolean pelicula, boolean documental, String titulo, Integer anyo, Integer pais) {
		this.pelicula = pelicula;
		this.documental = documental;
		this.titulo = titulo;
		this.anyo = anyo;
		this.pais = pais;
	}
	
	
	/**
	 * Constructor sin parámetros del filtro de ejemplares audiovisuales 
	 */
	public FiltroEjemplarListado() {
		this.pelicula = false;
		this.documental = false;
		this.titulo = "";
		this.anyo = 0;
		this.pais = 0;
	}

	/**
	 * Método para validar los datos cargados en el filtro
	 * 
	 * @return String con el mensaje de error, si no hay error retorna null  
	 */
	public String validarDatos() {
		
		if (!this.pelicula && !this.documental) {
			return "Se debe marcar Película o Documental o ambos";
		}
		
		return null;
		
	}
	
	// GETTERS $ SETTERS
	public boolean isPelicula() {
		return pelicula;
	}
	
	public void setPelicula(boolean pelicula) {
		this.pelicula = pelicula;
	}
	
	public boolean isDocumental() {
		return documental;
	}
	
	public void setDocumental(boolean documental) {
		this.documental = documental;
	}

	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public Integer getAnyo() {
		return anyo;
	}

	public void setAnyo(Integer anyo) {
		this.anyo = anyo;
	}

	public Integer getPais() {
		return pais;
	}
	
	public void setPais(Integer pais) {
		this.pais = pais;
	}

}
