package modelo;

public class PaisComboBox extends Pais {
	
	public PaisComboBox(Integer codigo, String descripcion) {
		super.setCodigo(codigo);
		super.setDescripcion(descripcion);
	}
	
	public PaisComboBox(Pais pais) {
		this(pais.getCodigo(), pais.getDescripcion());
	}

	@Override
	public String toString() {
		return getDescripcion();
	}

}
