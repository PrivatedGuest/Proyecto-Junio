package principal;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.TreeMap;

import funciones.cambios;

public class Profesor extends Persona{
private String categoria;
private String departamento;
private GregorianCalendar fechaNac;
private Map<String,Asignatura> asignaturas=new TreeMap<String,Asignatura>();
private int Gruposlinea5=0;
private int Gruposlinea6=0;
private static int ContadorProfesores=0;


public String getcategoria() {
	return categoria;
}
public String getdepartamento() {
	return departamento;
}
public static int getprofesorestotal() {
	return ContadorProfesores;
}
public GregorianCalendar getfechaNac() {
	return fechaNac;
}
public Map<String,Asignatura> getasignaturas(){
	return asignaturas;
}

public Profesor(String dniX,String nombreX,GregorianCalendar fechaNacX,String categoriaX,String departamentoX,Map<String, Asignatura> asignaturas) {
	try {
		ContadorProfesores++;
	this.dni=dniX;
	this.nombre=nombreX;
	this.fechaNac=fechaNacX;
	this.categoria=categoriaX;
	this.departamento=departamentoX;
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

public Profesor(String dniX,String nombreX,GregorianCalendar fechaNacX,String categoriaX,String departamentoX) {
	ContadorProfesores++;
	this.dni=dniX;
	this.nombre=nombreX;
	this.fechaNac=fechaNacX;
	this.categoria=categoriaX;
	this.departamento=departamentoX;

	for(Map.Entry<String, Asignatura> a: asignaturas.entrySet()) {
		if(!a.getKey().contains("+")) {
			this.Gruposlinea5++;
		}
	}
	Gruposlinea6=asignaturas.size()-Gruposlinea5;
}


public Profesor(String dniX,String nombreX,GregorianCalendar fechaNacX,String categoriaX) {
	ContadorProfesores++;
	this.dni=dniX;
	this.nombre=nombreX;
	this.fechaNac=fechaNacX;
	this.categoria=categoriaX;
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
	String aux=dni+"\r\n"+nombre+"\r\n"+cambios.GregorianCalendar_String(fechaNac)+"\r\n"+categoria+"\r\n"+departamento;
		return aux;
//falta imprimir o mapa,podemos crear un metodo en asignatura que imprima da forma que queremos, e aqui un bucle para que o faga con todas as asignaturas.
}

public String impasig() {
	int contador=0;
	String aux=" ";
	aux=aux.trim();
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