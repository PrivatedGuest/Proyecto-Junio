package funciones;

import java.util.Map;

import principal.Alumno;
import principal.Asignatura;
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
	
	
	public static boolean existeasignatura(String linea,Map<String,Asignatura> mapaAsignaturas) {
		linea=linea.trim();
		for(Map.Entry<String,Asignatura> a: mapaAsignaturas.entrySet()) {
			if(a.getValue().getsiglas().equals(linea)) {
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
	//Matricula 45004720X SEG
	
	public static String minusculas(String linea){
		linea=linea.toLowerCase();
		return linea;
	}
	
	
	 public static boolean yaesalumno(Alumno alumno,String asignatura) {
		 
		 for(Map.Entry<String, Asignatura> a: alumno.getasignaturas().entrySet() ) {
			 if(a.getKey().contains(asignatura)) {
				 return true;
			 }
		 }
		 return false;		 
	 }
	
	 
	 public static boolean prerequisitos(Alumno alumno,String prerequisitos) {
		 //prerequisitos e o que debe cumplir o alumno
		 if(prerequisitos.equals("\r\n")) {
			 return true;
		 }
		 Map<String,Asignatura> asigalumno = null;
		 asigalumno=alumno.getasignaturas();
		 String[] aux=prerequisitos.split(";");
		 for(int i=0;i<aux.length;i++) {//pra todos os prerequisitos
			 int verdad=0;
			 for(Map.Entry<String, Asignatura> a: asigalumno.entrySet()) {
				 if(a.getValue().getsiglas().contains(aux[i])&&!a.getKey().contains("+")) {
					 verdad++;
				 }
			 }//O sair de aqui verdad sera 1 se ten a asignatura aprobada
			 
			 if(verdad==0) {
				 return false;
			 }
		 } 
		 return true;
	 }
	
}
