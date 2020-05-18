package modelo;

/**
 * 
 * <h1> Filtro de participantes
 * 
 * @author Jose Manuel
 * @version 1.0
 * @since 05/2020
 */
public class FiltroListadoParticipantes {
	
	private boolean director; 
	private boolean participipante; 
	private Integer ejemplar;
	private String nombre;
	private Integer pais;
	private boolean sexoFemenino;
	private boolean sexoMasculino;
	/**
	 * 
	 * Constructor del filtro de participantes 
	 * 
	 * @param director Booleano que indica si se ha seleccioando director
	 * @param participipante Booleano que indica si se ha seleccioando participante
	 * @param ejemplar Código de ejemplar audiovisual seleccionado
	 * @param nombre Nombre de participante informado 
	 * @param pais Código de país seleccionado
	 * @param sexoFemenino Booleano que indica si se ha seleccionado el sexo femenino
	 * @param sexoMasculino Booleano que indica si se ha seleccionado el sexo masculino
	 */
	public FiltroListadoParticipantes(boolean director, boolean participipante, Integer ejemplar, String nombre,
			Integer pais, boolean sexoFemenino, boolean sexoMasculino) {
		this.director = director;
		this.participipante = participipante;
		this.ejemplar = ejemplar;
		this.nombre = nombre;
		this.pais = pais;
		this.sexoFemenino = sexoFemenino;
		this.sexoMasculino = sexoMasculino;
	}
	
	/**
	 * 
	 * Constructor sin parámetros del filtro de participantes 
	 * 
	 */
	public FiltroListadoParticipantes() {
		this.director = true;
		this.participipante = true;
		this.ejemplar = 0;
		this.nombre = "";
		this.pais = 0;
		this.sexoFemenino = true;
		this.sexoMasculino = true;
	}

	/**
	 * Método para validar los datos cargados en el filtro
	 * 
	 * @return String con el mensaje de error, si no hay error retorna null  
	 */
	public String validarDatos() {
		
		if (!this.director && !this.participipante) {
			return "Se debe marcar director o participante o ambos";
		}
		
		if (!this.sexoFemenino && !this.sexoMasculino) {
			return "Se debe marcar al menos uno de los sexos";
		}
		
		return null;
		
	}
	
	// GETTERS $ SETTERS
	public boolean isDirector() {
		return director;
	}
	public void setDirector(boolean director) {
		this.director = director;
	}
	public boolean isParticipipante() {
		return participipante;
	}
	public void setParticipipante(boolean participipante) {
		this.participipante = participipante;
	}
	public Integer getEjemplar() {
		return ejemplar;
	}
	public void setEjemplar(Integer ejemplar) {
		this.ejemplar = ejemplar;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getPais() {
		return pais;
	}
	public void setPais(Integer pais) {
		this.pais = pais;
	}
	public boolean isSexoFemenino() {
		return sexoFemenino;
	}
	public void setSexoFemenino(boolean sexoFemenino) {
		this.sexoFemenino = sexoFemenino;
	}
	public boolean isSexoMasculino() {
		return sexoMasculino;
	}
	public void setSexoMasculino(boolean sexoMasculino) {
		this.sexoMasculino = sexoMasculino;
	}

}
