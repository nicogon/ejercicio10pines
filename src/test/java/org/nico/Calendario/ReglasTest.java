package org.nico.Calendario;
import static org.junit.Assert.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import org.nico.Excepciones.*;


public class ReglasTest {

	LocalDate fechaPrueba = LocalDate.of(2017, 2, 20); // 20 de febrero del 2017

	// Requerimiento Dia a la semana (test clase valida clase invalida)
	// Prueba que el dia sea feriado
	@Test
	public void reglaDiaDeLaSemanaValida() {
		Regla unaRegla = new ReglaDiaDeSemana(DayOfWeek.MONDAY);
		assertTrue(unaRegla.esFeriado(fechaPrueba));

	}

	// Prueba que el dia no sea feriado
	@Test
	public void reglaDiaDeLaSemanaInvalida() {
		Regla unaRegla = new ReglaDiaDeSemana(DayOfWeek.SATURDAY);
		assertFalse(unaRegla.esFeriado(fechaPrueba));
	}

	// Requerimiento dia al año
	// Prueba que el dia al año sea feriado
	@Test
	public void reglaDiaMesValida() {
		Regla unaRegla = new ReglaDiaMes(MonthDay.of(2, 20));
		assertTrue(unaRegla.esFeriado(fechaPrueba));
	}

	// Prueba que el dia al año no sea feriado
	@Test
	public void reglaDiaMesInvalida() {
		Regla unaRegla = new ReglaDiaMes(MonthDay.of(3, 12));
		assertFalse(unaRegla.esFeriado(fechaPrueba));

	}

	// Requerimiento dia particular feriado
	// Prueba fecha es feriado
	@Test
	public void reglaDiaMesAnoValida() {
		Regla unaRegla = new ReglaDiaMesAno(LocalDate.of(2017, 2, 20));
		assertTrue(unaRegla.esFeriado(fechaPrueba));

	}

	// Prueba fecha no es feriado
	@Test
	public void reglaDiaMesAnoInvalida() {
		Regla unaRegla = new ReglaDiaMesAno(LocalDate.of(2017, 4, 1));
		assertFalse(unaRegla.esFeriado(fechaPrueba));

	}

	// Prueba fecha esta dentro de un rango (Usada en ReglaCompuesta)
	// Clase valida
	@Test
	public void reglaFranjaValida() {
		Regla unaRegla = new ReglaFranja(LocalDate.of(2017, 1, 1), LocalDate.of(2019, 1, 1));
		assertTrue(unaRegla.esFeriado(fechaPrueba));
	}

	// Clase invalida inferior
	@Test
	public void reglaFranjaInValidaPrevia() {
		Regla unaRegla = new ReglaFranja(LocalDate.of(2019, 1, 1), LocalDate.of(2020, 1, 1));
		assertFalse(unaRegla.esFeriado(fechaPrueba));
	}

	// Clase invalida superior
	@Test
	public void reglaFranjaInValidaPosterior() {
		Regla unaRegla = new ReglaFranja(LocalDate.of(2016, 1, 1), LocalDate.of(2017, 1, 1));
		assertFalse(unaRegla.esFeriado(fechaPrueba));
	}

	// Clase borde
	@Test
	public void reglaFranjaBordeValida() {
		Regla unaRegla = new ReglaFranja(LocalDate.of(2017, 2, 20), LocalDate.of(2017, 2, 20));
		assertTrue(unaRegla.esFeriado(fechaPrueba));
	}

	// Clase borde invalida
	@Test
	public void reglaFranjaBordeInValida() {
		Regla unaRegla = new ReglaFranja(LocalDate.of(2017, 2, 21), LocalDate.of(2017, 2, 25));
		assertFalse(unaRegla.esFeriado(fechaPrueba));
	}

	//Clase invalida Fechas invertidas
	
	@Test(expected = FechasInvertidas.class)
	public void reglaFranjaInvertidaInvalida() {
		@SuppressWarnings("unused")
		Regla unaRegla = new ReglaFranja(LocalDate.of(2019, 2, 21), LocalDate.of(2017, 2, 25));
		assertTrue(false);
	}
	
	

	
	
	// Requerimiento dia a la semana dentro de una franja

	// Clase valida
	@Test
	public void reglaCompuestaValida() {
		Regla unaRegla1 = new ReglaDiaDeSemana(DayOfWeek.MONDAY);
		Regla unaRegla2 = new ReglaFranja(LocalDate.of(2017, 1, 1), LocalDate.of(2019, 1, 1));
		Set<Regla> conjuntoDeReglas = new HashSet<Regla>(Arrays.asList(unaRegla1, unaRegla2));
		ReglaCompuesta unaReglaCompuesta = new ReglaCompuesta(conjuntoDeReglas);
		assertTrue(unaReglaCompuesta.esFeriado(fechaPrueba));
	}

	// Clase invalida
	@Test
	public void reglaCompuestaInvalida() {
		Regla unaRegla1 = new ReglaDiaDeSemana(DayOfWeek.TUESDAY);
		Regla unaRegla2 = new ReglaFranja(LocalDate.of(2017, 1, 1), LocalDate.of(2019, 1, 1));
		Set<Regla> conjuntoDeReglas = new HashSet<Regla>(Arrays.asList(unaRegla1, unaRegla2));
		ReglaCompuesta unaReglaCompuesta = new ReglaCompuesta(conjuntoDeReglas);
		assertFalse(unaReglaCompuesta.esFeriado(fechaPrueba));
	}

	// Requerimiento dia al año dentro de una franja
	// Clase valida
	@Test
	public void reglaCompuestaValidaRequerimientoDos() {
		Regla unaRegla = new ReglaDiaMes(MonthDay.of(2, 20));
		Regla unaRegla2 = new ReglaFranja(LocalDate.of(2017, 1, 1), LocalDate.of(2019, 1, 1));
		Set<Regla> conjuntoDeReglas = new HashSet<Regla>(Arrays.asList(unaRegla, unaRegla2));
		ReglaCompuesta unaReglaCompuesta = new ReglaCompuesta(conjuntoDeReglas);
		assertTrue(unaReglaCompuesta.esFeriado(fechaPrueba));
	}

	// Clase valida
	@Test
	public void reglaCompuestaInvalidaRequerimientoDos() {
		Regla unaRegla = new ReglaDiaMes(MonthDay.of(2, 20));
		Regla unaRegla2 = new ReglaFranja(LocalDate.of(2020, 1, 1), LocalDate.of(2030, 1, 1));
		Set<Regla> conjuntoDeReglas = new HashSet<Regla>(Arrays.asList(unaRegla, unaRegla2));
		ReglaCompuesta unaReglaCompuesta = new ReglaCompuesta(conjuntoDeReglas);
		assertFalse(unaReglaCompuesta.esFeriado(fechaPrueba));
	}

	// Condicion borde no hay reglas en regla combinada
	@Test
	public void reglaCompuestaSinReglas() {
		Set<Regla> conjuntoDeReglas = new HashSet<Regla>();
		ReglaCompuesta unaReglaCompuesta = new ReglaCompuesta(conjuntoDeReglas);
		assertFalse(unaReglaCompuesta.esFeriado(fechaPrueba));
	}

	// De la prueba "ya esta insertado" nace la nececidad de armar y testear el
	// equals

	@Test
	public void reglaDiaDeLaSemanaEqualsValida() {
		Regla unaRegla = new ReglaDiaDeSemana(DayOfWeek.MONDAY);
		Regla unaRegla2 = new ReglaDiaDeSemana(DayOfWeek.MONDAY);
		assertEquals(unaRegla, unaRegla2);
	}

	@Test
	public void reglaDiaDeLaSemanaEqualsInvalida() {
		Regla unaRegla = new ReglaDiaDeSemana(DayOfWeek.MONDAY);
		Regla unaRegla3 = new ReglaDiaDeSemana(DayOfWeek.SATURDAY);
		assertNotEquals(unaRegla, unaRegla3);
	}

	@Test
	public void reglaDiaMesEqualsValida() {
		Regla unaRegla = new ReglaDiaMes(MonthDay.of(3, 12));
		Regla unaRegla2 = new ReglaDiaMes(MonthDay.of(3, 12));
		assertEquals(unaRegla, unaRegla2);
	}

	@Test
	public void reglaDiaMesEqualsInValida() {
		Regla unaRegla = new ReglaDiaMes(MonthDay.of(4, 17));
		Regla unaRegla2 = new ReglaDiaMes(MonthDay.of(3, 12));
		Regla unaRegla3 = new ReglaDiaMesAno(LocalDate.of(2017, 2, 20));
		assertNotEquals(unaRegla, unaRegla2);
		assertNotEquals(unaRegla, unaRegla3);
	}

	@Test
	public void reglaDiaMesAnoEqualsValida() {
		Regla unaRegla = new ReglaDiaMesAno(LocalDate.of(2017, 3, 20));
		Regla unaRegla2 = new ReglaDiaMesAno(LocalDate.of(2017, 3, 20));
		assertEquals(unaRegla, unaRegla2);
	}

	@Test
	public void reglaDiaMesAnoEqualsInvalida() {
		Regla unaRegla = new ReglaDiaMesAno(LocalDate.of(2017, 3, 20));
		Regla unaRegla2 = new ReglaDiaMesAno(LocalDate.of(2018, 2, 2));
		assertNotEquals(unaRegla, unaRegla2);
	}

	@Test
	public void reglaFranjaEqualsValida() {
		Regla unaRegla = new ReglaFranja(LocalDate.of(2000, 1, 1), LocalDate.of(2012, 1, 1));
		Regla unaRegla2 = new ReglaFranja(LocalDate.of(2000, 1, 1), LocalDate.of(2012, 1, 1));
		assertEquals(unaRegla, unaRegla2);
	}

	@Test
	public void reglaFranjaEqualsInvalida() {
		Regla unaRegla = new ReglaFranja(LocalDate.of(2000, 1, 1), LocalDate.of(2012, 1, 1));
		Regla unaRegla2 = new ReglaFranja(LocalDate.of(2001, 1, 1), LocalDate.of(2012, 1, 1));
		assertNotEquals(unaRegla, unaRegla2);
	}

	@Test
	public void reglaCompuestaEqualsValida() {
		Regla unaRegla1 = new ReglaDiaDeSemana(DayOfWeek.MONDAY);
		Regla unaRegla2 = new ReglaFranja(LocalDate.of(2017, 1, 1), LocalDate.of(2019, 1, 1));
		Set<Regla> conjuntoDeReglas = new HashSet<Regla>(Arrays.asList(unaRegla1, unaRegla2));
		ReglaCompuesta unaReglaCompuesta = new ReglaCompuesta(conjuntoDeReglas);
		Regla unaRegla3 = new ReglaDiaDeSemana(DayOfWeek.MONDAY);
		Regla unaRegla4 = new ReglaFranja(LocalDate.of(2017, 1, 1), LocalDate.of(2019, 1, 1));
		Set<Regla> conjuntoDeReglas2 = new HashSet<Regla>(Arrays.asList(unaRegla3, unaRegla4));
		ReglaCompuesta unaReglaCompuesta2 = new ReglaCompuesta(conjuntoDeReglas2);
		assertEquals(unaReglaCompuesta, unaReglaCompuesta2);
	}

	@Test
	public void reglaCompuestaEqualsInvalida() {
		Regla unaRegla1 = new ReglaDiaDeSemana(DayOfWeek.MONDAY);
		Regla unaRegla2 = new ReglaFranja(LocalDate.of(2017, 1, 1), LocalDate.of(2019, 1, 1));
		Set<Regla> conjuntoDeReglas = new HashSet<Regla>(Arrays.asList(unaRegla1, unaRegla2));
		ReglaCompuesta unaReglaCompuesta = new ReglaCompuesta(conjuntoDeReglas);
		Regla unaRegla3 = new ReglaDiaDeSemana(DayOfWeek.MONDAY);
		Regla unaRegla4 = new ReglaFranja(LocalDate.of(2018, 2, 2), LocalDate.of(2019, 2, 2));
		Set<Regla> conjuntoDeReglas2 = new HashSet<Regla>(Arrays.asList(unaRegla3, unaRegla4));
		ReglaCompuesta unaReglaCompuesta2 = new ReglaCompuesta(conjuntoDeReglas2);
		assertNotEquals(unaReglaCompuesta, unaReglaCompuesta2);
	}

	
	
	//Casos de conjetura de errores de datos ingresados nulos
	
	@Test(expected = IllegalArgumentException.class)
	public void reglaDiaMesAnoNull() {
		@SuppressWarnings("unused")
		Regla unaRegla = new ReglaDiaMesAno(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void reglaDiaSemanaNull() {
		@SuppressWarnings("unused")
		Regla unaRegla = new ReglaDiaDeSemana(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void reglaFranjaNull() {
		@SuppressWarnings("unused")
		Regla unaRegla = new ReglaFranja(null,null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void reglaDiaMesNull() {
		@SuppressWarnings("unused")
		Regla unaRegla = new ReglaDiaMes(null);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void reglaCompuestaNull() {
		@SuppressWarnings("unused")
		Regla unaRegla = new ReglaCompuesta(null);
	}
	
	
	
	
}



