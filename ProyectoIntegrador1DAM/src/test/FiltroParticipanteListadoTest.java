package test;

import static org.junit.Assert.*;

import org.junit.Test;

public class FiltroParticipanteListadoTest {
	
	private boolean director; 
	private boolean interprete; 
	private Integer ejemplar;
	private String nombre;
	private Integer pais;
	private boolean sexoFemenino;
	private boolean sexoMasculino;
	
	private String resultado;
	
	public FiltroParticipanteListadoTest (boolean director, boolean participipante, Integer ejemplar, String nombre,
			Integer pais, boolean sexoFemenino, boolean sexoMasculino, String resultado) {
		this.director = director;
		this.interprete = participipante;
		this.ejemplar = ejemplar;
		this.nombre = nombre;
		this.pais = pais;
		this.sexoFemenino = sexoFemenino;
		this.sexoMasculino = sexoMasculino;
		this.resultado = resultado;
	}

	@Test
	public void testValidarDatos() {
		fail("Not yet implemented");
	}

}
