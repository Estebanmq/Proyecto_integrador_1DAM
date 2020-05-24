package modelo;

/** 
 * Esta clase define los atributos y métodos que va a tener cada documental 
 * 
 * @author 
 * @see EjemplarAudiovisual
 * @see GeneroDocumental
 * @since 01/05/2020
 */

public class Documental extends EjemplarAudiovisual{

	/** 
	 * Atributos que consideramos importantes para identificar un documental
	 */
	
	/** 
	 * Genero del documental
	 * @see GeneroDocumental
	 */
	private GeneroDocumental genero;

	//Constructores
	
	/**
	 * Método constructor de documental
	 * @param codigo
	 * @param titulo
	 * @param anyo
	 * @param director
	 * @param sinopsis
	 * @param nacionalidad
	 * @param genero
	 */
	public Documental(Integer codigo, String titulo, int anyo, Director director, String sinopsis, Pais nacionalidad,
			GeneroDocumental genero) {
		super(codigo, titulo, anyo, director, sinopsis, nacionalidad);
		this.genero = genero;
	}
	
	/**
	 * Método constructor sin parámetros de documental
	 */
	public Documental() {
		this(0, null, 0, null, null, null, null);
	}

	/**
	 * Método para ver los atributos
	 * @returns una cadena de caracteres con todos los atributos
	 */
	@Override
	public String toString() {
		return super.toString() + " Documental []";
	}

	//Métodos Getter y Setter
	public GeneroDocumental getGenero() {
		return genero;
	}

	public void setGenero(GeneroDocumental genero) {
		this.genero = genero;
	}
	
	
	
}
