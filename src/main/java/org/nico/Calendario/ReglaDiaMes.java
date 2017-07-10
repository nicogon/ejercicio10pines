package org.nico.Calendario;

import java.time.LocalDate;
import java.time.MonthDay;

public class ReglaDiaMes implements Regla {
	MonthDay diaAlAno;

	public ReglaDiaMes(MonthDay fecha) {
		if(fecha==null ) throw new IllegalArgumentException("El dia del año no puede ser nulo");
		this.diaAlAno = fecha;

	}

	public boolean esFeriado(LocalDate fecha) {

		return (fecha.getMonth() == this.diaAlAno.getMonth())
				&& (fecha.getDayOfMonth() == this.diaAlAno.getDayOfMonth());
	}

	public MonthDay getDiaMes() {
		return diaAlAno;
	}

	public boolean equals(Object other) {
		if (other instanceof ReglaDiaMes) {
			if (((ReglaDiaMes) other).getDiaMes().equals(this.diaAlAno)) {
				return true;
			}
		}
		return false;
	}

	public int hashCode() {
		int result = 5;
		result = 31 * result + this.diaAlAno.hashCode();
		return result;
	}

}
