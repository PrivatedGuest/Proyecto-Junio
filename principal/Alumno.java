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
	this.dni=dniX;
	this.nombre=nombreX;
	this.email=emailX;
	this.fechaNac=fechaNacX;
	this.fechaIng=fechaIngX;
	this.asignaturas.putAll(asignaturas);
	}catch(Exception e) {
		System.out.println("Esto non falla ou");
	}
}
public void imp() {
		System.out.print(dni+"\n"+nombre+"\n"+email+"\n"+cambios.GregorianCalendar_String(fechaNac)+"\n"+cambios.GregorianCalendar_String(fechaIng));

}

public Alumno(String dniX,String nombreX,GregorianCalendar fechaNacX,GregorianCalendar fechaIngX, Map<String, Asignatura> asignaturas) {
	this.dni=dniX;
	this.nombre=nombreX;
	this.fechaNac=fechaNacX;
	this.fechaIng=fechaIngX;
	this.asignaturas.putAll(asignaturas);
}


public Alumno(String dniX,String nombreX, String emailX,GregorianCalendar fechaNacX,GregorianCalendar fechaIngX) {
	this.dni=dniX;
	this.nombre=nombreX;
	this.email=emailX;
	this.fechaNac=fechaNacX;
	this.fechaIng=fechaIngX;
}

public Alumno(String dniX,String nombreX,GregorianCalendar fechaNacX,GregorianCalendar fechaIngX) {
	this.dni=dniX;
	this.nombre=nombreX;
	this.fechaNac=fechaNacX;
	this.fechaIng=fechaIngX;
}


}