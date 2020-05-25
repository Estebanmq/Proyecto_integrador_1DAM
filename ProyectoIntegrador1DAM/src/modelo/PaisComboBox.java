package modelo;
/**
 * Clase utilizada para montar los ComboBox que utilizan países
 * 
 * @author Jose Manuel de Dios
 * @version 1.0
 * @since 10/05/2020
 *
 */
public class PaisComboBox extends Pais {
	
	/**
	 * Método conscructor para instanciar los objetos
	 * @param codigo Código de país
	 * @param descripcion Descripción del país
	 */
	public PaisComboBox(Integer codigo, String descripcion) {
		super.setCodigo(codigo);
		super.setDescripcion(descripcion);
	}
	
	/**
	 * Método constructor de copia
	 * @param pais Objeto Pais para realizar la copia
	 */
	public PaisComboBox(Pais pais) {
		this(pais.getCodigo(), pais.getDescripcion());
	}

	/**
	 * Método utilizado por el componente JComboBox para mostrar las descripciones
	 */
	@Override
	public String toString() {
		return getDescripcion();
	}

}
