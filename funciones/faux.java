package funciones;

import java.util.Map;

import principal.Persona;

public class faux {
	
	
	public static boolean existepersona(String linea, Map<String,Persona> mapaPersonas) {
		for(Map.Entry<String, Persona> p: mapaPersonas.entrySet()) {
			if(p.getValue().getdni().equals(linea)) {
				return true;
			}
		}
		return false;
	}
			
	public static String quitarespacios(String linea) {
		while(linea.contains("  ")) {
			linea=linea.replace("  ", " ");
		}
		return linea;
	}
	
	public static String minusculas(String linea){
		linea=linea.toLowerCase();
		return linea;
	}
	
	
	
}
