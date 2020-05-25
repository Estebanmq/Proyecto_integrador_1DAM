package modelo;

/**
 * Esta clase relaciona los interpretes con las películas.
 * @author Jose Manuel de Dios
 * @version 1.0
 * @since 04/05/2020
 * 
 * @see Interprete
 * @see Pelicula
 */
public class Actuacion {
	
	/**
	 * Apunte al Id del Interprete
	 */
	private Integer codInterprete;
	
	/**
	 * Apunte al Id de la Pelicula
	 */
	private Integer codPelicula;
	
	/**
	 * Atributo propio de la clase: contiene el nombre de personaje del Participante en la Pelicula
	 */
	private String nombrePersonaje;
	
	/** 
	 * Método constructor de Actuacion
	 * @param codInterprete Código de intérprete
	 * @param codPelicula Código de película 
	 * @param nombrePersonaje Nombre de personaje
	 */
	public Actuacion(Integer codInterprete, Integer codPelicula, String nombrePersonaje) {
		this.setCodInterprete(codInterprete);
		this.setCodPelicula(codPelicula);
		this.setNombrePersonaje(nombrePersonaje);
	}

	//Métodos Getter y Setter
	public Integer getCodInterprete() {
		return codInterprete;
	}

	public void setCodInterprete(Integer codInterprete) {
		this.codInterprete = codInterprete;
	}

	public Integer getCodPelicula() {
		return codPelicula;
	}

	public void setCodPelicula(Integer codPelicula) {
		this.codPelicula = codPelicula;
	}

	public String getNombrePersonaje() {
		return nombrePersonaje;
	}

	public void setNombrePersonaje(String nombrePersonaje) {
		this.nombrePersonaje = nombrePersonaje;
	}

}
