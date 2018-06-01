package funciones;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;
import java.util.TreeMap;

import principal.Alumno;
import principal.Persona;

public class leertxt {
	public static Map<String, Persona> leeralumno(String direccion){
			Map<String,Persona> mapaAlumnoX= new TreeMap<String,Persona>();	
			String todo="     ";
			String[] todos;
			try {
				todo=todo.trim();
				String bfRead;
				BufferedReader read= new BufferedReader(new FileReader(direccion));
				while((bfRead=read.readLine())!= null) {//Fai o ciclo mentras o ficheiro teña datos
					todo= todo + bfRead+"\n";}
				read.close();}
			catch(Exception e) {
				System.out.println("Fichero inexistente: "+direccion);
		}
			todos=todo.split("[*]");
				mapaAlumnoX.putAll(ordenar.alumnos(todos));
				return mapaAlumnoX;				
	}
	}
