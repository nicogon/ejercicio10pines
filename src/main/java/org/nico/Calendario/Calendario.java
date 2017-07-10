package org.nico.Calendario;

import java.time.LocalDate;
import java.util.HashSet;

import java.util.Set;

import org.nico.Excepciones.ReglaYaExistente;

public class Calendario {
	Set<Regla> reglas = new HashSet<Regla>();	
	public void agregarRegla(Regla unaRegla){	
		if(unaRegla==null ) throw new IllegalArgumentException("La regla no puede ser nula");
		if(this.reglas.contains(unaRegla)) throw new ReglaYaExistente("Estas ingresando una regla exactamente igual");		
		reglas.add(unaRegla);
	}
	
	public void borrarRegla(Regla unaRegla){
		this.reglas.remove(unaRegla);
	}
	
	public  boolean esFeriado(LocalDate fecha){
		return reglas.stream().anyMatch(regla -> regla.esFeriado(fecha));
	}
}
