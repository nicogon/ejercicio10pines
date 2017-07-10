package org.nico.Excepciones;

//Extiendo de runtimeException para tener un codigo mas limpio 
public class ReglaYaExistente  extends RuntimeException {

	public ReglaYaExistente(String string) {
		super(string);	// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
