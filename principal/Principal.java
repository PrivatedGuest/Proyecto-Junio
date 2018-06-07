package principal;

import java.util.Map;
import java.util.TreeMap;

import funciones.Escribirtxt;
import funciones.leertxt;

public abstract class Principal {
public static final String direccionalu="alumnos.txt";
public static final String direccionprof="profesores.txt";
public static final String direccionasig="asignaturas.txt";
	public static void main(String [] args) {
		Map<String,Persona> mapaPersonas= new TreeMap<String,Persona>();
		Map<String,Asignatura> mapaAsignaturas= new TreeMap<String,Asignatura>();
		mapaPersonas.putAll(leertxt.leeralumno(direccionalu));
		mapaPersonas.putAll(leertxt.leerprofesor(direccionprof));
		mapaAsignaturas.putAll(leertxt.leerasignaturas(direccionasig));
		Escribirtxt.Alumnos(mapaPersonas);
		Escribirtxt.Profesores(mapaPersonas);


			
		}
		/*for(Map.Entry<String, Asignatura> p: mapaAsignaturas.entrySet()) {
			System.out.print(p.getValue().imprimirasignatura());
			System.out.print(p.getValue().imprimirGruposAsignatura());
			System.out.println("********************************************************");
		}*/
	
	}	