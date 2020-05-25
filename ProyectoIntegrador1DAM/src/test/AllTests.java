package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DaoDirectorMantenimientoTest.class, DaoDocumentalMantenimientoTest.class,
		FiltroEjemplarListadoTest.class, FiltroParticipanteListadoTest.class })
public class AllTests {

}
