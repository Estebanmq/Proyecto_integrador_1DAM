package modelo;

/** 
 * Esta clase define una visión parcial de los intérpretes 
 * 
 * @author Jose Manuel 
 * @see Participante
 * @since 16/05/2020
 */
public class ListaInterprete {

	/** 
	 * Código único de cada intérprete
	 */
	private Integer codigo;
	/**
	 * Nombre del participante
	 */
	private String nombre;
	
	/**
	 * Método constructor de ListaInterprete
	 * @param codigo Código de intérprete
	 * @param nombre Nombre del intérprete
	 */	
	public ListaInterprete(Integer codigo, String nombre) {
		this.setCodigo(codigo);
		this.setNombre(nombre);
	}
	/**
	 * Método constructor de ListaParticipante sin parámetros
	 */	
	public ListaInterprete() {
		this(0, null);
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
	
}
