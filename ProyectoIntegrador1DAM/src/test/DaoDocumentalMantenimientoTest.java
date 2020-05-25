package test;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import dao.DaoDocumentalMantenimiento;
import modelo.Documental;

@RunWith(Parameterized.class)
public class DaoDocumentalMantenimientoTest {
	
	private final static String CORRECTO = "Alas y garras";
	private final static String INCORRECTO = "Documental no existe";
	
	private Integer codigo;
	private Documental resultado;
	
	public DaoDocumentalMantenimientoTest (Integer codigo, Documental resultado) {

		this.codigo = codigo;
		this.resultado = resultado;
	}
	
	@Parameters
	public static Collection<Object[]> datos() {
		return Arrays.asList(new Object[][]{{4, new Documental(0, CORRECTO, 0, null, null, null, null), },{99, new Documental(0, INCORRECTO, 0, null, null, null, null)}});
	}

	@Test
	public void testBuscarDocu() throws ClassNotFoundException, SQLException {
		DaoDocumentalMantenimiento buscarDocu = new DaoDocumentalMantenimiento();
		assertEquals(resultado.getTitulo(), buscarDocu.buscarDocu(codigo).getTitulo());

	}

}
