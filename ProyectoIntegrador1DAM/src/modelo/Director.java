package modelo;

import java.util.Date;
/**
 * Esta clase define los atributos y métodos que va a tener un director.
 * 
 * @author Sergio Fernández
 * @see GeneroPelicula
 * @since 01/05/2020
 */
public class Director extends Participante{
	
	/**
	 * Genero preferido del director
	 */
	private GeneroPelicula generoPreferido;
	
	/** 
	 * Método constructor de director
	 * @param codigo Código de director
	 * @param nombre Nombre del director
	 * @param fechaNacimiento Fecha de nacimiento 
	 * @param sexo Sexo 
	 * @param generoPreferido Género preferido
	 * @param nacionalidad País de nacimiento
	 */
	public Director(Integer codigo, String nombre, Date fechaNacimiento, Sexo sexo, GeneroPelicula generoPreferido, Pais nacionalidad) {
		super(codigo, nombre, fechaNacimiento, sexo, nacionalidad);
		this.generoPreferido = generoPreferido;
	}

	/** 
	 * Método constructor de copia de director
	 * @param director Clase Director
	 */
	public Director(Director director) {
		this(director.getCodigo(), director.getNombre(), director.getFechaNacimiento(), director.getSexo(), director.getGeneroPreferido(), director.getNacionalidad());
	}

	//Métodos Getter y Setter
	public GeneroPelicula getGeneroPreferido() {
		return generoPreferido;
	}

	public void setGeneroPreferido(GeneroPelicula generoPreferido) {
		this.generoPreferido = generoPreferido;
	}

}
