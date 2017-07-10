

/*
Nota:
Entiendo que La reglaDiaMesAno podria ser representada como una reglaFranja con inicio y fin del mismo dia
(suponiendo que admitiria fecha inicio y fin puedan ser iguales que no es el caso) o una regla combinada de 
1 año mas una reglaDiaMes. 

Opte por crear esta entidad por la simpleza que posee, por el costo computacional que implicaria una doble 
comparacion si utilizara una reglaCombinada/reglaDiaMes

*/


package org.nico.Calendario;

import java.time.LocalDate;

public class ReglaDiaMesAno implements Regla {

	LocalDate fecha;

	public ReglaDiaMesAno(LocalDate fecha) {
		if(fecha==null ) throw new IllegalArgumentException("La fecha no puede ser nula");
		this.fecha = fecha;
	}

	public boolean esFeriado(LocalDate fecha) {
		return fecha.equals(this.fecha);
	}

	public LocalDate getFecha() {
		return this.fecha;
	}

	public boolean equals(Object other) {
		if (other instanceof ReglaDiaMesAno) {
			if (((ReglaDiaMesAno) other).getFecha().equals(this.fecha)) {
				return true;
			}
		}
		return false;
	}

	public int hashCode() {
		int result = 7;
		result = 31 * result + this.fecha.hashCode();
		return result;
	}

}
