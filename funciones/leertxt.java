package funciones;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;
import java.util.TreeMap;

import principal.Alumno;
import principal.Asignatura;
import principal.Aula;
import principal.Persona;

public class leertxt {
	
	
	public static String[] leerejecucion(String direccion){
		//solo lee, no procesa datos. El switch se pasa a la principal
		String todo="  ";
		String[] devolver=null;
		try {
			todo=todo.trim();
			String bfRead;
			BufferedReader read= new BufferedReader(new FileReader(direccion));
			while((bfRead=read.readLine())!=null) {				
				if (bfRead.startsWith("*")){
					continue;
					//Ignora las lineas que empiezan por asterisco
				}
				else todo=todo+bfRead+"\n";			
					
			}//En todo estaria todo o fichero
			devolver= todo.split("\n");
			read.close();
			}catch(Exception e) {
			System.out.println("Fichero de ejecución no existente");
			}
		
//		todos=todo.split("[*]");
//		mapaEjecucionX.putAll(ordenar. (todos));
		return devolver;		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	public static Map<String, Persona> leeralumno(String direccion,Map<String,Asignatura> mapaAsignaturas,Map<String,Aula> mapaAulas){
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
				mapaAlumnoX.putAll(ordenar.alumnos(todos,mapaAsignaturas,mapaAulas));
				return mapaAlumnoX;				
	}
	
	public static String leercurso(String direccion) {
		String devolver="";
		try {
			String bfRead;
			BufferedReader read= new BufferedReader(new FileReader(direccion));
			while((bfRead=read.readLine())!= null) {//Fai o ciclo mentras o ficheiro teña datos
				devolver= devolver + bfRead+"\n";}
			read.close();}
		catch(Exception e) {
			System.out.println("Fichero inexistente: "+direccion);
	}
		devolver=devolver.replace("\r", " ");
		devolver=devolver.replaceAll("\n", " ");
		devolver=devolver.trim();
		devolver=faux.quitarespacios(devolver);
		return devolver;	
	}
	
	public static Map<String, Persona> leerprofesor(String direccion,Map<String,Asignatura> mapaAsignaturas){
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
			mapaProfesorX.putAll(ordenar.profesores(todos,mapaAsignaturas));
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
	
	
	public static Map<String,Aula> leeraulas(String direccion){
		Map <String,Aula> mapaAulasX= new TreeMap<String,Aula>();
		String todo=" ";
		String[] todos;
		try {
			String bfRead;
			BufferedReader read= new BufferedReader(new FileReader(direccion));
			while((bfRead=read.readLine())!= null) {//Fai o ciclo mentras o ficheiro teña datos
				todo= todo + bfRead+"\r\n";}
			read.close();
		}catch(Exception e) {
			System.out.println("Fichero inexistente: "+direccion);
		}
		todos=todo.split("[*]");
		mapaAulasX.putAll(ordenar.aulas(todos));
		return mapaAulasX;
	}
	
	
	}