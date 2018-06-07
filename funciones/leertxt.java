package funciones;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;
import java.util.TreeMap;

import principal.Alumno;
import principal.Asignatura;
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
	
	
	public static Map<String, Persona> leerprofesor(String direccion){
		Map<String,Persona> mapaProfesorX= new TreeMap<String,Persona>();
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
			mapaProfesorX.putAll(ordenar.profesores(todos));
			return mapaProfesorX;	

}
	
	
	
	public static Map <String,Asignatura> leerasignaturas(String direccion){
		Map <String,Asignatura> mapaAsignaturasX= new TreeMap<String,Asignatura>();
		String todo=" ";
		String[] todos;
		try {
			todo=todo.trim();
			String bfRead;
			BufferedReader read= new BufferedReader(new FileReader(direccion));
			while((bfRead=read.readLine())!=null) {//Co if else facemos que toda a linea de asteriscos pase a ser un asterisco solo
					if(bfRead.contains("**")) {//E asi podemos facer split(asterisco)
						todo=todo+"*"+"\n";
					}
					else todo=todo+bfRead+"\n";
			}//En todo estaria todo o fichero
			read.close();
			}catch(Exception e) {
			System.out.println("Fichero inexistente: "+direccion);
			}
		todos=todo.split("[*]");
		mapaAsignaturasX.putAll(ordenar.asignaturas(todos));
		return mapaAsignaturasX;
		
		}
	}