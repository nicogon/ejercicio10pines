package org.nico.Calendario;

import java.time.LocalDate;

public interface Regla {

	public boolean equals(Object other);
	public int hashCode();
	public boolean	esFeriado(LocalDate fecha);

}
