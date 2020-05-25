package test;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import dao.DaoDirectorMantenimiento;

@RunWith(Parameterized.class)
public class DaoDirectorMantenimientoTest {
	
	private String nombre;
	private int resultado;
	
	public DaoDirectorMantenimientoTest (String nombre, int resultado) {

		this.nombre = nombre;
		this.resultado = resultado;
	}

	@Parameters
	public static Collection<Object[]> datos() {
		return Arrays.asList(new Object[][]{{"Steven Spielberg", 2},
											{"otro director", 0}});
	}

	@Test
	public void testBuscarCodDirector() throws ClassNotFoundException, SQLException {
		DaoDirectorMantenimiento buscarCodDirector = new DaoDirectorMantenimiento();
		assertEquals(resultado, buscarCodDirector.buscarCodDirector(nombre));
	}

}
