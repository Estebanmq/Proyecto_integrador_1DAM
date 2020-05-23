package modelo;

/** 
 * Esta clase define una visión parcial de los personajes de una pelicula
 * 
 * @author Jose Manuel 
 * @see Interprete
 * @since 16/05/2020
 */
public class ListaPersonaje {

	/** 
	 * Código único de cada interprete
	 */
	private Integer codigo;

	/**
	 * Nombre del interprete
	 */
	private String nombre;

	/**
	 * Nombre del personaje que interpreta
	 */
	private String nombrePersonaje;
	
	/**
	 * Método constructor de ListaPersonaje
	 * @param codigo Código de intérprete
	 * @param nombre Nombre del intérprete
	 * @param nombre Nombre del personaje que interpreta
	 */	
	public ListaPersonaje(Integer codigo, String nombre, String nombrePersonaje) {
		this.setCodigo(codigo);
		this.setNombre(nombre);
		this.setNombrePersonaje(nombrePersonaje);
	}
	/**
	 * Método constructor de ListaParticipante sin parámetros
	 */	
	public ListaPersonaje() {
		this(0, null, null);
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
	
	public String getNombrePersonaje() {
		return nombrePersonaje;
	}
	public void setNombrePersonaje(String nombrePersonaje) {
		this.nombrePersonaje = nombrePersonaje;
	}
	
}
