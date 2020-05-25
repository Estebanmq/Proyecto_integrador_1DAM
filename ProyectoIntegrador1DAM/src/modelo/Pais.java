package modelo;
/**
 * Esta clase define los atributos y métodos que va a tener un país
 * @author 
 * @since 01/05/2020
 */
public class Pais {
	
	
	/**
	 * Atributos que consideramos importante para un país
	 */
	
	/**
	 * Código del país
	 */
	private Integer codigo;
	/**
	 * Descripcion del país
	 */
	private String descripcion;
	
	/**
	 * Método constructor de país sin parámetros
	 */
	public Pais() {
		this.codigo = null;
		this.descripcion = null;
	}
	/**
	 * Método constructor de país
	 * @param codigo
	 * @param descripcion
	 */
	public Pais(Integer codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	//Métodos Getter y Setter
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
