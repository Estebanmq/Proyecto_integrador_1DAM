package test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import modelo.FiltroEjemplarListado;

@RunWith(Parameterized.class)
public class FiltroEjemplarListadoTest {
	
	private static final String TEXTOERROR = "Se debe marcar Película o Documental o ambos";;
	
	private boolean pelicula; 
	private boolean documental; 
	private String titulo;
	private Integer anyo;
	private Integer pais;
	
	private String resultado;
	
	public FiltroEjemplarListadoTest (boolean pelicula, boolean documental, String titulo,
										Integer anyo, Integer pais, String resultado) {
		this.pelicula = pelicula;
		this.documental = documental;
		this.titulo = titulo;
		this.anyo = anyo;
		this.pais = pais;
		this.resultado = resultado;
	}
	
	@Parameters
	public static Collection<Object[]> datos() {
		return Arrays.asList(new Object[][]{{true, true, "titulo", 0, 0, null},
											{true, false, "titulo", 0, 0, null},
											{false, true, "titulo", 0, 0, null},
											{false, false, "titulo", 0, 0, TEXTOERROR}});
	}

	@Test
	public void testValidarDatos() {
		FiltroEjemplarListado filtro = new FiltroEjemplarListado(this.pelicula, this.documental, this.titulo, this.anyo, this.pais);
		assertEquals(resultado, filtro.validarDatos());
	}

}
