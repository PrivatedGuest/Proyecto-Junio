package funciones;

import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Map;

import principal.Alumno;
import principal.Asignatura;
import principal.Persona;
import principal.Profesor;

public class Fprincipales{
	public static Map<String,Persona> InsertaPersona(Map<String,Persona> mapaPersonas,String persona) {
		persona=persona.trim();
		persona=faux.quitarespacios(persona);
		String[] datos;
		int errores=0;
		datos=persona.split(" ");
		if(datos.length<6) {
			Escribirtxt.Avisos("Numero de argumentos incorrecto: "+persona);
			return mapaPersonas;
		}
		String dni= datos[2];
		String nombre=datos[3];
		String perfil= faux.minusculas(datos[1]);//DATOS 1 E O PERFIL
		if(perfil.equals("alumno")&&datos.length!=6) {
			Escribirtxt.Avisos("Numero de argumentos incorrecto: "+persona);
			return mapaPersonas;
		}
		else if(perfil.equals("profesor")&&datos.length!=7) {
			Escribirtxt.Avisos("Numero de argumentos incorrecto: "+persona);
			return mapaPersonas;
		}
			
		if(!Comprobar.dni(dni)) {
			errores++;
			Escribirtxt.Avisos("DNI incorrecto");
		}
		String fechaNac=datos[4];
		if(!Comprobar.fecha(fechaNac)) {
			errores++;
			Escribirtxt.Avisos("Fecha incorrecta");
		}
			if(perfil.equals("alumno")) {
					String fechaIng=datos[5];
					if(!Comprobar.fecha(fechaIng)) {
									errores++;
									Escribirtxt.Avisos("Fecha incorrecta");
									}
					if(!Comprobar.compara_fechas(fechaNac, fechaIng)) {
									errores++;
									Escribirtxt.Avisos("Fecha de ingreso incorrecta");
									}
					if(faux.existepersona(dni, mapaPersonas)) {
							errores++;
							Escribirtxt.Avisos("Alumno ya existente");
									}
					if(errores==0) {
								//temos que crear un mapa de asignaturas e agregalo no constructor, pero o mapa de doncenia?
								GregorianCalendar fechaNAC=cambios.String_GregorianCalendar(fechaNac);
								String aux= cambios.GregorianCalendar_String(fechaNAC);
								//asdasdasdasdasdasdasdasdasdasdasdasdasdSystem.out.println(cambios.GregorianCalendar_String(fechaNAC));
								GregorianCalendar fechaING= cambios.String_GregorianCalendar(fechaIng);
								mapaPersonas.put(dni, new Alumno(dni,nombre,fechaNAC,fechaING));
									}		
				     return mapaPersonas;
						}
			
			else if(perfil.equals("profesor")) {
						String categoria=datos[5];
						String departamento=datos[6];
						Iterator it=mapaPersonas.keySet().iterator();
						if(faux.existepersona(dni, mapaPersonas)) {
							errores++;
							Escribirtxt.Avisos("Profesor ya existente");
						}
					if(errores==0) {
						GregorianCalendar fechaNAC=cambios.String_GregorianCalendar(fechaNac);
						mapaPersonas.put(dni, new Profesor(dni,nombre,fechaNAC,categoria,departamento));		
					}
			
		return mapaPersonas;
		
	}
					
			
			else return mapaPersonas;
		}
	public static Map<String,Persona> Matricula(Map<String,Persona> mapaPersonas,Map<String,Asignatura> mapaAsignaturas,String comando){
		comando= comando.trim();
		comando= faux.quitarespacios(comando);
		String[] comandoi= comando.split(" ");
		int errores=0;
		if(comandoi.length!=3) {
			Escribirtxt.Avisos("Numero de argumentos incorrecto: "+comando);
			return mapaPersonas;
		}
		if(!faux.existepersona(comandoi[1].trim(), mapaPersonas)) {
			errores++;
			Escribirtxt.Avisos("Alumno inexistente: "+comandoi[1]);
		}
		if(!faux.existeasignatura(comandoi[2].trim(), mapaAsignaturas)) {
			errores++;
			Escribirtxt.Avisos("Asignatura inexistente: "+comandoi[2]);	
		}
		//obtemos o alumno cuyo dni me pasaron
		Alumno alumno=null;
		if(errores==0) {
		for(Map.Entry<String, Persona> p: mapaPersonas.entrySet()) {
			if(p.getKey().equals(comandoi[1].trim())) {
				alumno=(Alumno) p.getValue();
			}
		}
		if(faux.yaesalumno(alumno, comandoi[2])) {
			errores++;
			Escribirtxt.Avisos("Ya es alumno de la asignatura indicada: ");
		}
		Asignatura asig=null;
		//Ahora vamos obter a asignatura en cuestion
		for(Map.Entry<String, Asignatura> a: mapaAsignaturas.entrySet()) {
			if(a.getValue().getsiglas().equals(comandoi[2].trim())) {
				asig=a.getValue();
			}
		}
		String prerequisitos=asig.getprerequisitos();
		System.out.println(prerequisitos);
		if(!faux.prerequisitos(alumno, prerequisitos)) {
			errores++;
			Escribirtxt.Avisos("No cumple requisitos");
		}}//salimos do if de que nn haxa errores
		
		if(errores==0) {
			String siglas=comandoi[2].trim()+"+"+"M";
			Asignatura auxiliar= new Asignatura(comandoi[2].trim());
			alumno.engadirasig(siglas,auxiliar);
		}
		
		return mapaPersonas;
	}
	}






















