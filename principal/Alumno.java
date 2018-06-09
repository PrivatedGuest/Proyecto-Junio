package principal;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.TreeMap;

import funciones.cambios;

public class Alumno extends Persona{
private String email=" ";
private GregorianCalendar fechaNac;
private GregorianCalendar fechaIng;
private Map<String,Asignatura> asignaturas=new TreeMap<String,Asignatura>();
private int Gruposlinea5=0;
private int Gruposlinea6=0;
private static int ContadorAlumnos=0;
//A clave do mapa de asignaturas sera siglas+tipoGrupo se nos referimos as que esta cursando ou
//solo as siglas que nos referimos a docencia que xa cursou e aprobou

public static int getalumnostotal() {
	return ContadorAlumnos;
}

public void engadirasig(String siglas,Asignatura asig) {
	Gruposlinea6++;
	this.asignaturas.put(siglas,asig);
}

public GregorianCalendar getfechaNac() {
	return fechaNac;
}
public GregorianCalendar getfechaIng() {
	return fechaIng;
}
public String getemail() {
	return email;
}
public Map<String,Asignatura> getasignaturas(){
	return asignaturas;
}


public Alumno(String dniX,String nombreX, String emailX,GregorianCalendar fechaNacX,GregorianCalendar fechaIngX, Map<String, Asignatura> asignaturas) {
	try {
		ContadorAlumnos++;
	this.dni=dniX;
	this.nombre=nombreX;
	this.email=emailX;
	this.fechaNac=fechaNacX;
	this.fechaIng=fechaIngX;
	this.asignaturas.putAll(asignaturas);
	for(Map.Entry<String, Asignatura> a: asignaturas.entrySet()) {
		if(!a.getKey().contains("+")) {
			this.Gruposlinea5++;
		}
	}
	Gruposlinea6=asignaturas.size()-Gruposlinea5;
	}catch(Exception e) {
		System.out.println("Esto non falla ou");
	}
}

public Alumno(String dniX,String nombreX,GregorianCalendar fechaNacX,GregorianCalendar fechaIngX, Map<String, Asignatura> asignaturas) {
	ContadorAlumnos++;
	this.dni=dniX;
	this.nombre=nombreX;
	this.fechaNac=fechaNacX;
	this.fechaIng=fechaIngX;
	this.asignaturas.putAll(asignaturas);
	for(Map.Entry<String, Asignatura> a: asignaturas.entrySet()) {
		if(!a.getKey().contains("+")) {
			this.Gruposlinea5++;
		}
	}
	Gruposlinea6=asignaturas.size()-Gruposlinea5;
}


public Alumno(String dniX,String nombreX, String emailX,GregorianCalendar fechaNacX,GregorianCalendar fechaIngX) {
	ContadorAlumnos++;
	this.dni=dniX;
	this.nombre=nombreX;
	this.email=emailX;
	this.fechaNac=fechaNacX;
	this.fechaIng=fechaIngX;
	for(Map.Entry<String, Asignatura> a: asignaturas.entrySet()) {
		if(!a.getKey().contains("+")) {
			this.Gruposlinea5++;
		}
	}
	Gruposlinea6=asignaturas.size()-Gruposlinea5;
}


public Alumno(String dniX,String nombreX,GregorianCalendar fechaNacX,GregorianCalendar fechaIngX) {
	ContadorAlumnos++;
	this.dni=dniX;
	this.nombre=nombreX;
	this.fechaNac=fechaNacX;
	this.fechaIng=fechaIngX;
	for(Map.Entry<String, Asignatura> a: asignaturas.entrySet()) {
		if(!a.getKey().contains("+")) {
			this.Gruposlinea5++;
		}
	}
	Gruposlinea6=asignaturas.size()-Gruposlinea5;
}






public void setasignaturas(Map<String,Asignatura> asignaturasX) {
	asignaturas.putAll(asignaturasX);
}

public String imp() {
	String aux=dni+"\r\n"+nombre+"\r\n"+email+"\r\n"+cambios.GregorianCalendar_String(fechaNac)+"\r\n"+cambios.GregorianCalendar_String(fechaIng)+"\r\n";
		return aux;
//falta imprimir o mapa,podemos crear un metodo en asignatura que imprima da forma que queremos, e aqui un bucle para que o faga con todas as asignaturas.
}

public String impasig() {
	int contador=0;
	String aux=" ";
	aux=aux.trim();
	for(Map.Entry<String, Asignatura> a: asignaturas.entrySet()) {//Esto repitese para todas as asignaturas dese alumno da linea 5
		if(!a.getKey().contains("+")) {
			contador++;
			aux=aux+a.getValue().getsiglas()+" "+a.getValue().getcurso()+" "+a.getValue().getnota();	
			if(contador<this.Gruposlinea5) {
				aux=aux+" ; ";
			}
			else aux=aux+"\r\n";
			}
}
		if(contador<1) {
				aux=aux+"\r\n";
		}
	contador=0;
	for(Map.Entry<String, Asignatura> a: asignaturas.entrySet()) {//Esto repitese para todas as asignaturas dese alumno da linea 6
		if(a.getKey().contains("+")) {
			contador++;
			if(a.getKey().contains("+M")) {
				aux=aux+a.getValue().getsiglas();
			}
			else {
					if(a.getKey().contains("+A")) {
						aux=aux+a.getValue().getsiglas()+" "+a.getValue().getgrupo()+" "+a.getValue().getidentificadorA();
					}
					else aux=aux+a.getValue().getsiglas()+" "+a.getValue().getgrupo()+" "+a.getValue().getidentificadorB();
			}
			if(contador<this.Gruposlinea6) {
				aux=aux+" ; ";
			}
			else aux=aux+"\r\n";
			}//Salida do if
	}//Salida do for da linea 6
			if(contador<1) {
					aux=aux+"\r\n";
				}
	return aux;
}

}