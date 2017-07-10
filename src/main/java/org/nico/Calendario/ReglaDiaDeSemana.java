package org.nico.Calendario;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class ReglaDiaDeSemana implements Regla {
	DayOfWeek diaDeSemana;

	public ReglaDiaDeSemana(DayOfWeek dia) {
		if(dia==null ) throw new IllegalArgumentException("El dia de la semana no puede ser nulo");
		this.diaDeSemana = dia;
	}

	public boolean esFeriado(LocalDate fecha) {
		return fecha.getDayOfWeek() == this.diaDeSemana;

	}

	public DayOfWeek getDiaDeSemana() {
		return this.diaDeSemana;
	}

	public boolean equals(Object other) {
		if (other instanceof ReglaDiaDeSemana) {
			if (((ReglaDiaDeSemana) other).getDiaDeSemana().equals(this.diaDeSemana)) {
				return true;
			}
		}
		return false;
	}

	public int hashCode() {
		int result = 17;
		result = 31 * result + this.diaDeSemana.hashCode();
		return result;
	}

}
