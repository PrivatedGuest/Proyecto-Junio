package principal;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.TreeMap;

public class Alumno extends Persona{
private String dni;
private String email;
private String nombre;
private GregorianCalendar fechaNac;
private GregorianCalendar fechaIng;
private Map<String,Asignatura> asignaturasSuperadas= new TreeMap<String,Asignatura>();
private Map<String,Asignatura> docenciaRecibida= new TreeMap<String,Asignatura>();


public GregorianCalendar getfechaNac() {
	return fechaNac;
}
public GregorianCalendar getfechaIng() {
	return fechaIng;
}
public String getemail() {
	return email;
}
public Map<String,Asignatura> getasignaturasSuperadas(){
	return asignaturasSuperadas;
}
public Map<String,Asignatura> getdocenciaRecibida(){
	return docenciaRecibida;
}

public Alumno(String dniX,String nombreX, String emailX,GregorianCalendar fechaNacX,GregorianCalendar fechaIngX, Map<String, Asignatura> asignaturasSuperadasX,Map<String, Asignatura> docenciaRecibidaX) {
	this.dni=dniX;
	this.nombre=nombreX;
	this.email=emailX;
	this.fechaNac=fechaNacX;
	this.fechaIng=fechaIngX;
	asignaturasSuperadas.putAll(asignaturasSuperadasX);
	docenciaRecibida.putAll(docenciaRecibidaX);
}

}