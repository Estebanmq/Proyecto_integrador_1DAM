package modelo;

import java.util.ArrayList;

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
	 * Atributos que consideramos importantes para identificar una pelicula
	 */
	
	/** 
	 * Genero de la película
	 * @see GeneroPelicula
	 */
	private GeneroPelicula genero;
	
	/**
	 * ArrayList con los interpretes de una película
	 * @see java.util.ArrayList
	 * @see Interprete
	 */
	private ArrayList<Interprete> interpretes;

	/**
	 * Método constructor de pelicula
	 * @param codigo
	 * @param titulo
	 * @param anyo
	 * @param director
	 * @param sinopsis
	 * @param nacionalidad
	 * @param genero
	 * @param interpretes
	 */
	public Pelicula(Integer codigo, String titulo, int anyo, Director director, String sinopsis, Pais nacionalidad,
			GeneroPelicula genero, ArrayList<Interprete> interpretes) {
		super(codigo, titulo, anyo, director, sinopsis, nacionalidad);
		this.genero = genero;
		this.interpretes = interpretes;
	}
	
	/**
	 * Método para añadir un interprete
	 * @param i
	 * @see Interprete
	 */
	public void incluirInterprete(Interprete i) {
		
	}
	
	/**
	 * Método para eliminar un interprete
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

	public ArrayList<Interprete> getInterpretes() {
		return interpretes;
	}

	public void setInterpretes(ArrayList<Interprete> interpretes) {
		this.interpretes = interpretes;
	} 
	
}
