package modelo;

import java.util.HashSet;
import java.util.LinkedList;

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
	 * Método constructor de película
	 * @param codigo
	 * @param titulo
	 * @param anyo
	 * @param director
	 * @param sinopsis
	 * @param nacionalidad
	 * @param genero
	 */
	public Pelicula(Integer codigo, String titulo, int anyo, Director director, String sinopsis, Pais nacionalidad,
			GeneroPelicula genero) {
		super(codigo, titulo, anyo, director, sinopsis, nacionalidad);
		this.genero = genero;
		this.interpretes = new HashSet<Interprete>();
	}
	
	/**
	 * Método para añadir un intérprete
	 * @param i
	 * @see Interprete
	 */
	public void incluirInterprete(Interprete i) {
		
	}
	
	/**
	 * Método para eliminar un intérprete
	 * @param codInter
	 */
	public void eliminarInterprete(Integer codInter) {
		
	}

	/**
	 * Método para ver los atributos
	 * @returns una cadena de caracteres con todos los atributos
	 */
	@Override
	public String toString() {
		return super.toString() + " Pelicula [genero=" + genero + ", interpretes=" + interpretes + "]";
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
