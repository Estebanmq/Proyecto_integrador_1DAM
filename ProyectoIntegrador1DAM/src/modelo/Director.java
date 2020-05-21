package modelo;

import java.util.Date;
/**
 * Esta clase define los atributos y métodos que va a tener un director.
 * @author
 * @see GeneroPelicula
 * @since 01/05/2020
 */
public class Director extends Participante{

	/**
	 * Atributos que consideramos importantes para los directores.
	 */
	
	/**
	 * Genero preferido del director
	 */
	private GeneroPelicula generoPreferido;
	
	/** 
	 * Método constructor de director
	 * @param codigo
	 * @param nombre
	 * @param fechaNacimiento
	 * @param sexo
	 * @param generoPreferido
	 */
	public Director(Integer codigo, String nombre, Date fechaNacimiento, Sexo sexo, GeneroPelicula generoPreferido, Pais nacionalidad) {
		super(codigo, nombre, fechaNacimiento, sexo, nacionalidad);
		this.generoPreferido = generoPreferido;
	}

	/** 
	 * Método constructor de copia de director
	 * @param Director
	 */
	public Director(Director director) {
		this(director.getCodigo(), director.getNombre(), director.getFechaNacimiento(), director.getSexo(), director.getGeneroPreferido(), director.getNacionalidad());
	}

	/**
	 * Método para ver los atributos
	 * @returns una cadena de caracteres con todos los atributos
	 */
	@Override
	public String toString() {
		return super.toString() + " Director [generoPreferido=" + generoPreferido + "]";
	}

	//Métodos Getter y Setter
	public GeneroPelicula getGeneroPreferido() {
		return generoPreferido;
	}

	public void setGeneroPreferido(GeneroPelicula generoPreferido) {
		this.generoPreferido = generoPreferido;
	}

}
