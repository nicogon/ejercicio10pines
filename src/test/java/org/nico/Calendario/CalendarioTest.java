package org.nico.Calendario;
import static org.junit.Assert.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import org.nico.Excepciones.ReglaYaExistente;

public class CalendarioTest {


	// Arranco por el requerimiento mas simple de Si un dia es feriado

	@Test
	public void esUnDiaFeriadoyDaResultadoVerdadero() {
		// De el test deriva la nececidad de crear una regla valida
		Calendario unCalendario = new Calendario();
		Regla feriadoNueveDeJulio = new ReglaDiaMes(MonthDay.of(7, 9));
		unCalendario.agregarRegla(feriadoNueveDeJulio);
		assertTrue(unCalendario.esFeriado(LocalDate.of(2017, 7, 9)));
	}

	@Test
	public void noEsUnDiaFeriadoyDaResultadoFalso() {
		Calendario unCalendario = new Calendario();
		assertFalse(unCalendario.esFeriado(LocalDate.of(2017, 7, 10)));

	}
	@Test
	public void multiplesReglas(){
		Calendario unCalendario = new Calendario();
		Regla unaregla = new ReglaDiaDeSemana(DayOfWeek.TUESDAY);
		Regla unaRegla2 = new ReglaFranja(LocalDate.of(2017, 1, 1), LocalDate.of(2019, 1, 1));
		Set<Regla> conjuntoDeReglas = new HashSet<Regla>(Arrays.asList(unaregla, unaRegla2));
		ReglaCompuesta unaReglaCompuesta = new ReglaCompuesta(conjuntoDeReglas);
		Regla feriadoNueveDeJulio = new ReglaDiaMes(MonthDay.of(7, 9)); 
		unCalendario.agregarRegla(unaReglaCompuesta);
		unCalendario.agregarRegla(feriadoNueveDeJulio);
		assertTrue(unCalendario.esFeriado(LocalDate.of(2017, 7, 9)));
		assertTrue(unCalendario.esFeriado(LocalDate.of(2017, 7, 11)));	
	}
	@Test
	public void agregarYBorrarUnaRegla(){
		Calendario unCalendario = new Calendario();
		Regla feriadoNueveDeJulio = new ReglaDiaMes(MonthDay.of(7, 9)); 
		unCalendario.agregarRegla(feriadoNueveDeJulio);
		assertTrue(unCalendario.esFeriado(LocalDate.of(2017, 7, 9)));
		unCalendario.borrarRegla(feriadoNueveDeJulio);
		assertFalse(unCalendario.esFeriado(LocalDate.of(2017, 7, 9)));
		
	}
	
	//Test que al borrar una regla inexistente no rompe
	@Test
	public void borrarUnaReglaInexistente(){	
		Calendario unCalendario = new Calendario();
		Regla feriadoNueveDeJulio = new ReglaDiaMes(MonthDay.of(7, 9)); 
		unCalendario.borrarRegla(feriadoNueveDeJulio);	
	}
		
	//Test ingresar dos reglas iguales y esperar excepción
	@Test(expected = ReglaYaExistente.class)
	public void agregarUnaCondicionExistenteyEsperarExcepcion() throws Exception {
		Regla unaregla = new ReglaDiaDeSemana(DayOfWeek.MONDAY);
		Regla unaregla2 = new ReglaDiaDeSemana(DayOfWeek.MONDAY);
		Calendario unCalendario = new Calendario();
		unCalendario.agregarRegla(unaregla);
		unCalendario.agregarRegla(unaregla2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void insertarReglaNula() {
		Calendario unCalendario = new Calendario();
		unCalendario.agregarRegla(null);
	}
	

}
