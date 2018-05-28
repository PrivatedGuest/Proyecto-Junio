package principal;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import funciones.leertxt;

public abstract class Principal {

	public static void main(String [] args) {
		Map<String,Persona> mapaPersonas= new TreeMap<String,Persona>();
			mapaPersonas.putAll(leertxt.leeralumno("alumnos.txt"));
			Iterator it=mapaPersonas.keySet().iterator();	
			while(it.hasNext()) {
				
				Persona p= mapaPersonas.get(it.next());
						if(p instanceof Alumno) {
							System.out.println(((Alumno) p).getemail());
							
						}
			}	
	}
}