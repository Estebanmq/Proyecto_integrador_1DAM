package modelo;

import java.util.Date;
/**
 * Esta clase define los atributos y métodos que va a tener un participante.
 * @author
 * @see GeneroPelicula
 * @since 01/05/2020
 */

public class Interprete extends Participante{

	/**
	 * Atributos que consideramos importantes para los participantes.
	 */
	
	/**
	 * Cache del actor
	 */
	private double cache;
	
	/** 
	 * Método constructor de interprete
	 * @param codigo
	 * @param nombre
	 * @param fechaNacimiento
	 * @param sexo
	 * @param cache
	 */
	public Interprete(int codigo, String nombre, Date fechaNacimiento, Sexo sexo, double cache, Pais nacionalidad) {
		super(codigo, nombre, fechaNacimiento, sexo, nacionalidad);
		this.cache = cache;
	}


	/**
	 * Método para ver los atributos
	 * @returns una cadena de caracteres con todos los atributos
	 */
	@Override
	public String toString() {
		return super.toString() + "Interprete [cache=" + cache + "]";
	}

	//Métodos Getter y Setter
	public double getCache() {
		return cache;
	}

	public void setCache(double cache) {
		this.cache = cache;
	}

}
