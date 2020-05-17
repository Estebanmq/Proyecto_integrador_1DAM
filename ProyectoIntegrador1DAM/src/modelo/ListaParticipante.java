package modelo;

/** 
 * Esta clase define una visión parcial de los participantes junto con
 * el número de películas en las que participa.
 * 
 * @author Jose Manuel 
 * @see Participante
 * @since 16/05/2020
 */
public class ListaParticipante {

	/** 
	 * Código unico de cada persona
	 */
	private Integer codigo;
	/**
	 * Nombre del participante
	 */
	private String nombre;
	/**
	 * Nombre del participante
	 */
	private String nacionalidad;
	
	/**
	 * Método constructor de ListaParticipante
	 * @param codigo Código de participante
	 * @param nombre Nombre del participante
	 * @param nacionalidad Nacionalidad
	 */	
	public ListaParticipante(Integer codigo, String nombre, String nacionalidad) {
		this.setCodigo(codigo);
		this.setNombre(nombre);
		this.setNacionalidad(nacionalidad);
	}
	/**
	 * Método constructor de ListaParticipante sin parámetros
	 */	
	public ListaParticipante() {
		this.setCodigo(0);
		this.setNombre(null);
		this.setNacionalidad(null);
	}

	//GETTERS & SETTERS
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	
}
