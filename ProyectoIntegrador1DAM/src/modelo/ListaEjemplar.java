package modelo;

/** 
 * Esta clase define una visión parcial de los ejemplares audiovisuales junto
 * 
 * @author Jose Manuel 
 * @see Participante
 * @since 24/05/2020
 */
public class ListaEjemplar {

	/** 
	 * Código unico de cada ejemplar
	 */
	private Integer codigo;
	/**
	 * Título del ejemplar
	 */
	private String titulo;
	/**
	 * Nombre del participante
	 */
	private String nacionalidad;
	
	/**
	 * Método constructor de ListaEjemplar
	 * 
	 * @param codigo Código de ejemplar
	 * @param titulo Título del ejemplar
	 * @param nacionalidad Nacionalidad
	 */	
	public ListaEjemplar(Integer codigo, String titulo, String nacionalidad) {
		this.setCodigo(codigo);
		this.setTitulo(titulo);
		this.setNacionalidad(nacionalidad);
	}
	/**
	 * Método constructor sin parámetros
	 */	
	public ListaEjemplar() {
		this.setCodigo(0);
		this.setTitulo(null);
		this.setNacionalidad(null);
	}

	//GETTERS & SETTERS
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	
}
