package modelo;

import java.util.HashSet;

/** 
 * Esta clase define los atributos y métodos que va a tener cada pelicula 
 * 
 * @author 
 * @see EjemplarAudiovisual
 * @see GeneroPelicula
 * @see Interprete
 * @since 01/05/2020
 */
public class Pelicula extends EjemplarAudiovisual {
	/** 
	 * Atributos que consideramos importantes para identificar una película
	 */
	
	/** 
	 * Género de la película
	 * @see GeneroPelicula
	 */
	private GeneroPelicula genero;
	
	/**
	 * ArrayList con los interpretes de una película
	 * @see java.util.HashSet
	 * @see Interprete
	 */
	private HashSet<Interprete> interpretes;

	/**
	 * Método constructor de película sin parametros
	 */
	public Pelicula() {
		super();
		this.genero = null;
		this.interpretes = null;
	}
	
	/**
	 * Método constructor de copia
	 * @param p Objeto de tipo pelicula
	 */
	public Pelicula(Pelicula p) {
		super(p.getCodigo(),p.getTitulo(),p.getAnyo(),p.getDirector(),p.getSinopsis(),p.getNacionalidad());
		this.genero = p.genero;
		this.interpretes = p.interpretes;
	}
	
	/**
	 * Método constructor de película
	 * @param codigo
	 * @param titulo
	 * @param anyo
	 * @param director
	 * @param sinopsis
	 * @param nacionalidad
	 * @param genero
	 */
	public Pelicula(Integer codigo, String titulo, int anyo, Director director, String sinopsis, Pais nacionalidad, GeneroPelicula genero) {
		super(codigo, titulo, anyo, director, sinopsis, nacionalidad);
		this.genero = genero;
		this.interpretes = new HashSet<Interprete>();
	}

	//Métodos Getter y Setter
	public GeneroPelicula getGenero() {
		return genero;
	}

	public void setGenero(GeneroPelicula genero) {
		this.genero = genero;
	}

	public HashSet<Interprete> getInterpretes() {
		return interpretes;
	}

	public void setInterpretes(HashSet<Interprete> interpretes) {
		this.interpretes = interpretes;
	}
	
}
