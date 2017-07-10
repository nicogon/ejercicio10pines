package org.nico.Calendario;

import java.time.LocalDate;
import java.util.Set;

public class ReglaCompuesta implements Regla {
	Set<Regla> reglas;

	// Paso el set por constructor para no tener una instancia de una regla
	// inconsistente.
	public ReglaCompuesta(Set<Regla> reglas) {
		if(reglas==null ) throw new IllegalArgumentException("El set de reglas no puede ser nulo");
		this.reglas = reglas;
	}

	public boolean esFeriado(LocalDate fecha) {
		return (this.reglas.size() > 0) && this.reglas.stream().allMatch(regla -> regla.esFeriado(fecha));
	}

	public Set<Regla> getReglas() {
		return this.reglas;

	}

	public boolean equals(Object other) {
		if (other instanceof ReglaCompuesta) {
			return this.reglas.equals(((ReglaCompuesta) other).getReglas());
		}
		return false;
	}

	public int hashCode() {
		int result = 13;
		result = result * 31 + this.reglas.stream().mapToInt(regla -> regla.hashCode() * 31).sum();
		return result;
	}

}
