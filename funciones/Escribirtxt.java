package funciones;

import java.io.*;
import java.util.Map;
import principal.Alumno;
import principal.Persona;
import principal.Profesor;



public class Escribirtxt {
 public static void Alumnos(Map<String,Persona> mapaPersonas) {
			int contadoralu=0;
			int axudaarquivo=0;
			FileWriter escritura=null;
				for(Map.Entry<String, Persona> p: mapaPersonas.entrySet()){
					if(p.getValue() instanceof Alumno) {
						contadoralu++;
						Alumno auxiliar=(Alumno)p.getValue();
						try {
						
						File archivo=new File("alumnosfinal.txt");
								if(archivo.exists()&&axudaarquivo!=0) {
										escritura= new FileWriter("alumnosfinal.txt",true);
									}
									else {
										escritura= new FileWriter("alumnosfinal.txt");
									}
						axudaarquivo++;
						escritura.write(auxiliar.imp());		
					    escritura.write(auxiliar.impasig());
						if(contadoralu<Alumno.getalumnostotal()) {
							escritura.write("*\r\n");
						}
						escritura.close();
						}catch(Exception e) {
							e.printStackTrace();
						}	
					}
					}
	}
 
 public static void Profesores(Map<String,Persona> mapaPersonas) {
		int contadorprof=0;
		int axudaarquivo=0;
		FileWriter escritura=null;
			for(Map.Entry<String, Persona> p: mapaPersonas.entrySet()) {
				if(p.getValue() instanceof Profesor) {
					contadorprof++;
					Profesor auxiliar=(Profesor) p.getValue();
					try {
					File archivo=new File("profesoresfinal.txt");
							if(archivo.exists()&&axudaarquivo!=0) {
									escritura= new FileWriter("profesoresfinal.txt",true);
								}
								else {
									escritura= new FileWriter("profesoresfinal.txt");
								}
					axudaarquivo++;
					escritura.write(auxiliar.imp());		
				    escritura.write(auxiliar.impasig());
					if(contadorprof<Profesor.getprofesorestotal()) {
						escritura.write("*\r\n");
					}
					escritura.close();
					}catch(Exception e) {
						e.printStackTrace();
					}	
				}
				}
} 
 
 
 
 public static void Avisos(String imprimir) {
	 FileWriter escritura=null;
	 try {
		 File archivo=new File("avisosfinal.txt");
		 if(archivo.exists()) {
			 escritura= new FileWriter("avisosfinal.txt",true);
		 }
		 else escritura= new FileWriter("avisosfinal.txt");
		 escritura.write(imprimir+"\r\n");
		 escritura.close();
		 }catch(Exception e) {
			 System.out.println(e.getMessage());
		 }		 
 }
 
 
}//cerra a clase
