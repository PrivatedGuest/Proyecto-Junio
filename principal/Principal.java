package principal;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import funciones.Escribirtxt;
import funciones.leertxt;

public abstract class Principal {

	public static void main(String [] args) {
		Map<String,Persona> mapaPersonas= new TreeMap<String,Persona>();
			mapaPersonas.putAll(leertxt.leeralumno("alumnos.txt"));
			//Escribirtxt.Alumnos(mapaPersonas);
		
	}	
	
	}