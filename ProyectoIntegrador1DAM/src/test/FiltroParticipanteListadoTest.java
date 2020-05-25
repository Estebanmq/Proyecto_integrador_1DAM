package test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import modelo.FiltroParticipanteListado;

@RunWith(Parameterized.class)
public class FiltroParticipanteListadoTest {
	
	private static final String TEXTOERROR1 = "Se debe marcar director o participante o ambos";
	private static final String TEXTOERROR2 = "Se debe marcar al menos uno de los sexos";
	
	private boolean director; 
	private boolean interprete; 
	private Integer ejemplar;
	private String nombre;
	private Integer pais;
	private boolean sexoFemenino;
	private boolean sexoMasculino;
	
	private String resultado;
	
	public FiltroParticipanteListadoTest (boolean director, boolean interprete, Integer ejemplar, String nombre,
			Integer pais, boolean sexoFemenino, boolean sexoMasculino, String resultado) {
		this.director = director;
		this.interprete = interprete;
		this.ejemplar = ejemplar;
		this.nombre = nombre;
		this.pais = pais;
		this.sexoFemenino = sexoFemenino;
		this.sexoMasculino = sexoMasculino;
		this.resultado = resultado;
	}
	
	@Parameters
	public static Collection<Object[]> datos() {
		return Arrays.asList(new Object[][]{{true, true, 0, "nombre", 0, true, true, null},
											{true, false, 0, "nombre", 0, true, true, null},
											{false, true, 0, "nombre", 0, true, true, null},
											{false, false, 0, "nombre", 0, true, true, TEXTOERROR1},
											{true, true, 0, "nombre", 0, true, false, null},
											{true, true, 0, "nombre", 0, false, true, null},
											{true, true, 0, "nombre", 0, false, false, TEXTOERROR2}});
	}

	@Test
	public void testValidarDatos() {
		FiltroParticipanteListado filtro = new FiltroParticipanteListado(this.director, this.interprete, this.ejemplar, this.nombre, this.pais, this.sexoFemenino, this.sexoMasculino);
		assertEquals(resultado, filtro.validarDatos());
	}

}
