package modelo;

/**
 * Enum con todos los valores de sexo
 * @author Esteban Martínez
 * @author Sergio Fernández
 * @author Jose Manuel de Dios
 * @since 01/05/2020
 * @version 1.0
 */
public enum Sexo {
	
	FEMENINO("Femenino"),
	MASCULINO("");
	
	private final String descripcion;
	
	Sexo (String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
}	