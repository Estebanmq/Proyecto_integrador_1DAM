package modelo;

/** 
 * Esta clase define los atributos y métodos comunes a los documental y pelicula
 * 
 * @author Esteban Martinez Quintanar
 * @since 01/05/2020
 * @version 1.0
 * 
 * @see Director
 */
public abstract class EjemplarAudiovisual {
		
	/**
	 * Código único del ejemplar
	 */
	private Integer codigo;
	
	/**
	 * Titulo del ejemplar
	 */
	private String titulo;
	
	/**
	 * Año de estreno del ejemplar
	 */
	private int anyo;
	
	/**
	 * Director del ejemplar
	 * @see Director
	 */
	private Director director;
	
	/**
	 * Resumen del ejemplar
	 */
	private String sinopsis;
	
	/**
	 * Nacionalidad de origen del ejemplar
	 * @see Pais
	 */
	private Pais nacionalidad;

	/**
	 * Método constructor de ejemplar sin parametros
	 */
	public EjemplarAudiovisual() {
		this.codigo = 0;
		this.titulo = "";
		this.anyo = 0;
		this.director = null;
		this.sinopsis = "";
		this.nacionalidad = null;
	}
	
	/**
	 * Método constructor de ejemplar
	 * @param codigo Código de ejemplar
	 * @param titulo Título de ejemplar
	 * @param anyo Anyo de producción
	 * @param director Director del ejemplar
	 * @param sinopsis Sipnosis
	 * @param nacionalidad País de producción
	 */
	public EjemplarAudiovisual(Integer codigo, String titulo, int anyo, Director director, String sinopsis,
			Pais nacionalidad) {
		this.codigo = codigo;
		this.titulo = titulo;
		this.anyo = anyo;
		this.director = director;
		this.sinopsis = sinopsis;
		this.nacionalidad = nacionalidad;
	}
	
	// Métodos Getter y Setter
	public Integer getCodigo() {
		return codigo;
	}


	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public int getAnyo() {
		return anyo;
	}


	public void setAnyo(int anyo) {
		this.anyo = anyo;
	}


	public Director getDirector() {
		return director;
	}


	public void setDirector(Director director) {
		this.director = director;
	}


	public String getSinopsis() {
		return sinopsis;
	}


	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}


	public Pais getNacionalidad() {
		return nacionalidad;
	}


	public void setNacionalidad(Pais nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	
}
