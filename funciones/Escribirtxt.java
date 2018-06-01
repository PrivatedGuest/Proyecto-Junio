package funciones;

import java.io.*;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import principal.Alumno;
import principal.Asignatura;
import principal.Persona;

import java.util.TreeMap;

public class Escribirtxt {
 public static void Alumnos(Map<String,Persona> mapaPersonas) {
	 FileWriter fichero=null;
		Map<String,Asignatura> asignaturas=new TreeMap<String,Asignatura>();
	 try {
		 fichero= new FileWriter("alumnosfinal.txt");
			Iterator it=mapaPersonas.keySet().iterator();	
			while(it.hasNext()) {
				Iterator itASIG=asignaturas.keySet().iterator();
				//con esto CREO reseteamos o mapa
				while(itASIG.hasNext()) {
					asignaturas.remove(itASIG.next());
				}
						Persona p= mapaPersonas.get(it.next());
						if(p instanceof Alumno){
							String dni= p.getdni();
							String nombre= p.getnombre();
							String email= ((Alumno) p).getemail();
							GregorianCalendar fechaNac= ((Alumno) p).getfechaNac();
							GregorianCalendar fechaIng= ((Alumno) p).getfechaIng();
							asignaturas.putAll(((Alumno) p).getasignaturas());
							
							fichero.write(dni+"\n");
							fichero.write(nombre+"\n");
							fichero.write(email+"\n");
							fichero.write(fechaNac.toString());
							fichero.write(fechaIng.toString());
							String aux = " ";//Gradamos todo nun String para sobreescribilo e eliminar o ; final
							for(Entry<String, Asignatura> auxiliar: asignaturas.entrySet()) {//Esto para as asignaturas superadas
								String claveaux=auxiliar.getKey();
								Asignatura asigAux=auxiliar.getValue();
								aux=aux.trim();
								if(!claveaux.contains("+")) {
								aux=aux+asigAux.getsiglas()+" "+asigAux.getcursoAcademico()+" "+asigAux.getnota()+";";
								}
							}//en paraimprimir esta todo o que temos que imprimir na 5 linea +";" que nn queremos, pero ollo que pode nn haber 5 linea
						if(aux.length()==0) {
							fichero.write("\n");
						}
						else {
							String paraimprimir=aux.substring(0, aux.length()-1);
							paraimprimir=paraimprimir+"\n";
							fichero.write(paraimprimir);
						}
						//Ahora facemos o mismo para a 6 linea, non se pode facer todo no mesmo mapa
						//Porque o Treemap ordena por clave, podemos encontrarnos as cousas desordenadas e imprimir cousas da 6 na 5
						for(Map.Entry<String, Asignatura> auxiliar: asignaturas.entrySet()){
							String claveaux=auxiliar.getKey();
							Asignatura asigAux= auxiliar.getValue();
							if(claveaux.contains("+")) {
								String aux2=" ";
								aux2=aux2.trim();
								String identificador=" ";
								identificador.trim();
								if(asigAux.getgrupo().equals("A")) {
									identificador=asigAux.getidentificadorA();
								}
								else if(asigAux.getgrupo().equals("B")) {
									identificador=asigAux.getidentificadorB();
								}
							aux2=aux2+asigAux.getsiglas()+" "+asigAux.getgrupo()+" "+identificador+";";
							if(aux2.length()==0) {
								fichero.write("\n");
							}
							else {
								String paraimprimir=aux2.substring(0, aux2.length()-1);
								paraimprimir=paraimprimir+"\n";
								fichero.write(paraimprimir);
							}}//cerramos a linea 6	
						}//cerramos o mapa que fai todas as asignaturas	
						System.out.println("*\n");
						}//cerramos a condicional de "se esto e un alumno"
			}//cerramos o mapa dos alumnos
	 }catch(Exception ex){
		 System.out.println("Mensaje de la excepción: asdasdasd"+ex.getMessage());
	 }}
 
 
 public static void Avisos(String imprimir) {
	 FileWriter fichero=null;
	 try {
		 fichero= new FileWriter("Avisos.txt");
		 fichero.write(imprimir+"\n");
		 }catch(Exception e) {
			 System.out.println(e.getMessage());
		 }		 
 }
 
 
}//cerra a clase
