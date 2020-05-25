package modelo;

import java.util.Date;

/**
 * Esta clase define los atributos y métodos que va a tener un participante.
 * @author Esteban Martínez
 * @author Sergio Fernández
 * @author Jose Manuel de Dios
 * @since 01/05/2020
 */
public class Interprete extends Participante{

	/**
	 * Caché del actor
	 */
	private double cache;
	
	/** 
	 * Método constructor de interprete
	 * @param codigo Código de intérprete
	 * @param nombre Nombre de intérprete
	 * @param fechaNacimiento Fecha de nacimiento
	 * @param sexo Sexo
	 * @param cache Caché 
	 * @param nacionalidad País de nacimiento
	 */
	public Interprete(int codigo, String nombre, Date fechaNacimiento, Sexo sexo, double cache, Pais nacionalidad) {
		super(codigo, nombre, fechaNacimiento, sexo, nacionalidad);
		this.cache = cache;
	}
	
	/**
	 * Método constructor de copia
	 * 
	 * @param interprete Intérprete
	 */
	public Interprete(Interprete interprete) {
		this(interprete.getCodigo(), interprete.getNombre(), interprete.getFechaNacimiento(), interprete.getSexo(), interprete.getCache(), interprete.getNacionalidad());
	}

	//Métodos Getter y Setter
	public double getCache() {
		return cache;
	}

	public void setCache(double cache) {
		this.cache = cache;
	}

}
