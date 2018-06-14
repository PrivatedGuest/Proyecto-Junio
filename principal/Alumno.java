package principal;

import java.util.GregorianCalendar;
import java.util.Map;
import java.util.TreeMap;

import funciones.cambios;
import funciones.faux;

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


public String gethorario(Map<String,Asignatura>mapaAsigGLOB) {
	String devolver="";
	for(Map.Entry<String, Asignatura> asigX:this.asignaturas.entrySet()) {
		//Para todas as asignaturas do alumno
		if(asigX.getKey().contains("+")&&!asigX.getKey().contains("+M")) {
			/*Se esta en aprobada non a queremos, senon ten grupo tampoco(xa que nn sabemos o horario)*/
			for(Map.Entry<String, Asignatura> asigGLOB:mapaAsigGLOB.entrySet()) {
//A clave no mapa do alumno sera siglas+tipo e as do mapa global solo siglas
				if(asigX.getKey().contains(asigGLOB.getValue().getsiglas())) {
					Asignatura auxiliar=asigGLOB.getValue();
					//Ahora estamos na mesma asignatura!!
					//Ahora miramos todos os grupos!!
					String identificador = null;
					if(asigX.getKey().contains("A")) {
						identificador=asigX.getValue().getidentificadorA();
					}
					else if(asigX.getKey().contains("B")) {
						identificador=asigX.getValue().getidentificadorB();
					}
					//Co codigo de arriba obtemos o identificador da asignatura
					else System.out.println("FALLO EN GETHORARIO, SALTA UN IDENTIFICADOR QUE NON E A NIN B");
					for(Map.Entry<String, Grupo> grupX:auxiliar.getmapagrupos().entrySet()) {
						//se coincide o TIPO e o ID
						
						if(asigX.getKey().contains(grupX.getValue().gettipo())&&identificador.equals(grupX.getValue().getid())){
							Grupo paradatos=grupX.getValue();
							devolver=devolver+auxiliar.getcurso()+" "+auxiliar.getcuatrimestre()+" "+paradatos.getdia()+" "+paradatos.gethoraInicio()+" "+paradatos.gethorafinal()+" "+paradatos.getaula()+"\r\n";
							//Formato: cuatrimestre dia horainicio horafinal aula		
						}
					}//Fin do for para todos os grupos da asignatura que coincidiu
				}//fin do if(se e esa asignatura)			
			}//Fin do for para todas as asignaturas	do arquivo	
		}//fin do if	
	}//fin do for para todas as asignaturas do alumno
	return devolver;
}


public static int getalumnostotal() {
	return ContadorAlumnos;
}

public void setcuatrimestres(Map<String,Asignatura> mapaAsignaturas) {
	for(Map.Entry<String, Asignatura> asig: asignaturas.entrySet()) {
		String aux=faux.getcuatrimestre(asig.getValue().getsiglas(), mapaAsignaturas);
		asig.getValue().setcuatrimestre(aux);
	}
}

public void asigsuperada(String siglas,float nota, String curso) {
	this.asignaturas.put(siglas, new Asignatura(siglas,curso,nota));
	this.Gruposlinea5++;
}

public  void eliminardocencia(String siglas){//Se coinciden as claves, elimina a asignatura do grupo
	int auxiliar=0;
	String eliminar=" ";
	for(Map.Entry<String, Asignatura> asig: this.asignaturas.entrySet()) {
		if(asig.getKey().contains(siglas)&&asig.getKey().contains("+")) {
			auxiliar++;
			eliminar=eliminar+asig.getKey()+";";
		}
	}
	eliminar=eliminar.trim();
	String[] aux=eliminar.split(";");
	for(int k=0; k<aux.length;k++) {
		this.asignaturas.remove(aux[k].trim());
	}
	this.Gruposlinea6=this.Gruposlinea6-auxiliar;
}


public void engadirasig(String siglas,Asignatura asig) {
	this.Gruposlinea6++;
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
	for(Map.Entry<String, Asignatura> a: asignaturas.entrySet()) {
		if(a.getKey().contains("+")) {
			this.Gruposlinea6++;
		}
	}
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
	for(Map.Entry<String, Asignatura> a: asignaturas.entrySet()) {
		if(a.getKey().contains("+")) {
			this.Gruposlinea6++;
		}
	}
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
	for(Map.Entry<String, Asignatura> a: asignaturas.entrySet()) {
		if(a.getKey().contains("+")) {
			this.Gruposlinea6++;
		}
	}
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
						aux=aux+a.getValue().getsiglas()+" "+a.getValue().gettipo()+" "+a.getValue().getidentificadorA();
					}
					else aux=aux+a.getValue().getsiglas()+" "+a.getValue().gettipo()+" "+a.getValue().getidentificadorB();
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