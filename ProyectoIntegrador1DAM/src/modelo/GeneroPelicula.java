package modelo;
/**
 * Enum con todos los valores de los generos de la película
 * @author Esteban Martínez
 * @author Sergio Fernández
 * @author Jose Manuel de Dios
 * @since 01/05/2020
 * @version 1.0
 */
public enum GeneroPelicula {
	
	ACCION("Acción"),
	BELICO("Bélico"),
	COMEDIA("Comedia"),
	DRAMA("Drama"),
	SUSPENSE("Suspense"),
	TERROR("Terror"),
	MUSICAL("Musical"),
	ANIMACION("Animación"),
	CIENCIAFICCION("Ciencia Ficción");

	private final String descripcion;
	
	GeneroPelicula (String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
}