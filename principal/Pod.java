package principal;

import java.io.FileInputStream;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Stream;

import funciones.faux;

public class Pod {
	public static Map<String, Float> leerpod(Map<String, Persona> mapaPersonas) {		 
		Map<String, Float> listaPOD = new TreeMap<String, Float>();
		Scanner devolver = null;
		
		try {
			devolver = new Scanner (new FileInputStream("pod_compactado.txt"));
		} catch(Exception e) {
			System.out.println("Error al leer el fichero pod.txt");
		}
		String [] contenidoPOD;
		while(devolver.hasNextLine()) {
		devolver.useDelimiter("[*]"); //Para separar entre los diferentes inputs
		contenidoPOD = devolver.next().trim().split("\n");
		String dni = contenidoPOD[0].trim();
		//A partir del dni obtenemos el nombre del profesor
		String nombre = faux.getprofesor(dni,mapaPersonas).getnombre();
		String[] stringPOD=contenidoPOD[1].trim().split(";");
		for (int i=0; i<stringPOD.length; i++) {
			String[] POD=stringPOD[i].replaceAll(" +", " ").trim().split(" ");
			String profesorAsignaturaTipo = nombre+"-"+POD[0]+"-"+POD[1];
			Float numGrupos= (float) Float.parseFloat(POD[2]);			
			listaPOD.put(profesorAsignaturaTipo,numGrupos);
			}
		}
		devolver.close();
		//Aqui tiene que arrojar listaPOD
		return listaPOD;
	}
	
	public static Map<String, Float> calcularPODteorico(Map<String,Asignatura> mapaAsignaturas,Map<String, Float> listaPOD,Map<String, Persona> mapaPersonas) {
		Map<String, Float> Aux = new TreeMap<String, Float>();
		for(Map.Entry<String, Float> p: listaPOD.entrySet()) {
			String[] camposProfesor= p.getKey().split("-");
			float numGrupos=p.getValue();
			Asignatura auxiliar=faux.getasignatura(camposProfesor[1].trim(), mapaAsignaturas);
			float duracion = 0;
			if(camposProfesor[2].trim().equals("A")) {
				duracion=Float.parseFloat(auxiliar.getduracionA());
			}
			if(camposProfesor[2].trim().equals("B")) {
				duracion=Float.parseFloat(auxiliar.getduracionB());			
			}
			//Falla lo de duracion, 
			int existe=0;
			float duracionTotal=duracion*numGrupos;
			float horastotales=duracionTotal;
			for(Map.Entry<String, Float> au: Aux.entrySet()) {
				if(au.getKey().equals(camposProfesor[0].trim())) {
					existe++;
					horastotales=au.getValue()+duracionTotal;									
				}
			}
			Aux.put(camposProfesor[0].trim(), horastotales);			
			}	
			 

		Map<String, Float> listaPODordenada = new LinkedHashMap<String, Float>();        
        Stream<Map.Entry<String, Float>> stream = Aux.entrySet()
                .stream();
        stream.sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).forEachOrdered(
                c -> listaPODordenada.put(c.getKey(), c.getValue()));
		stream.close();
		
		return listaPODordenada;	
				

}
}