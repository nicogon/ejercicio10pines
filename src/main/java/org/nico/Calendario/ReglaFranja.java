package org.nico.Calendario;

import java.time.LocalDate;

import org.nico.Excepciones.FechasInvertidas;

public class ReglaFranja implements Regla {

	LocalDate fechaInicio;
	LocalDate fechaFin;

	public ReglaFranja(LocalDate fechaInicio, LocalDate fechaFin) {
		if(fechaInicio == null || fechaFin == null ) throw new IllegalArgumentException("Las fechas no pueden ser nulas");
		if (fechaFin.isBefore(fechaInicio)) throw new FechasInvertidas("La fecha de inicio y fin se encuentran invertidas");	
		this.fechaFin = fechaFin;
		this.fechaInicio = fechaInicio;
	}

	public boolean esFeriado(LocalDate fecha) {
		return (fecha.isAfter(this.fechaInicio) && fecha.isBefore(this.fechaFin)) || this.fechaFin.equals(fecha)
				|| this.fechaInicio.equals(fecha);
	}

	public LocalDate getFechaInicio() {
		return this.fechaInicio;
	}

	public LocalDate getFechaFin() {
		return this.fechaFin;
	}

	public boolean equals(Object other) {
		if (other instanceof ReglaFranja) {
			if (((ReglaFranja) other).getFechaInicio().equals(this.fechaInicio)
					&& ((ReglaFranja) other).getFechaFin().equals(this.fechaFin)) {
				return true;
			}
		}
		return false;
	}

	public int hashCode() {
		int result = 11;
		result = 31 * result + this.fechaFin.hashCode();
		result = 31 * result + this.fechaInicio.hashCode();
		return result;
	}

}
