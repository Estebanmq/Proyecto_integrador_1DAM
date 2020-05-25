package modelo;

/** 
 * Esta clase define los atributos y métodos que va a tener cada documental 
 * 
 * @author Esteban Martinez Quintanar
 * @since 01/05/2020
 * @version 1.0
 *  
 * @see EjemplarAudiovisual
 * @see GeneroDocumental

 */

public class Documental extends EjemplarAudiovisual{
	
	/** 
	 * Genero del documental
	 * @see GeneroDocumental
	 */
	private GeneroDocumental genero;

	//Constructores
	
	/**
	 * Método constructor de documental
	 * @param codig Código de documental
	 * @param titulo Título del documental
	 * @param anyo Año de producción
	 * @param director Director que lo ha dirigido
	 * @param sinopsis Sipnosis acerca del documental
	 * @param nacionalidad País de producción
	 * @param genero Género al que pertenece el documental
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
	 * Método constructor de copia
	 * @param d Objeto de tipo documental
	 */
	public Documental(Documental d) {
		super(d.getCodigo(),d.getTitulo(),d.getAnyo(),d.getDirector(),d.getSinopsis(),d.getNacionalidad());
		this.genero = d.genero;
	}

	//Métodos Getter y Setter
	public GeneroDocumental getGenero() {
		return genero;
	}

	public void setGenero(GeneroDocumental genero) {
		this.genero = genero;
	}
		
}
