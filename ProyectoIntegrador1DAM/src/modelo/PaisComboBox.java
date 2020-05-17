package modelo;

public class PaisComboBox extends Pais {
	
	public PaisComboBox(Pais pais) {
		super.setCodigo(pais.getCodigo());
		super.setDescripcion(pais.getDescripcion());
	}
	
	public PaisComboBox(Integer codigo, String descripcion) {
		super.setCodigo(codigo);
		super.setDescripcion(descripcion);
	}

	@Override
	public String toString() {
		return getDescripcion();
	}

}
