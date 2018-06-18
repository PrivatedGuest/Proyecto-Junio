package funciones;

import java.io.*;
import java.util.Map;
import java.util.Map.*;

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
 
 public static void FicheroPrueba(String imprimir) {
	 FileWriter escritura=null;
	 try {
		 File archivo=new File("archivoprueba.txt");
		 if(archivo.exists()) {
			 escritura= new FileWriter("archivoprueba.txt",true);
		 }
		 else escritura= new FileWriter("archivoprueba.txt");
		 escritura.write(imprimir);
		 escritura.close();
		 }catch(Exception e) {
			 System.out.println(e.getMessage());
		 }		 
 }
 
 public static void EscribirPODteorico(Map<String, Float> listaPODordenada, Map<String,Persona> mapaPersonas,String salida) {
	 FileWriter escritura=null;
	 try {
		 File archivo=new File(salida);
		 if(archivo.exists()) {
			 escritura= new FileWriter(salida,true);
		 }
		 else escritura= new FileWriter(salida);
	        for (Entry<String, Float> entry : listaPODordenada.entrySet()) {
	            escritura.write(entry.getKey()+"; "+(faux.getprofesorporNombre(entry.getKey(), mapaPersonas)).getdni()+"; "+entry.getValue()+"\r\n");
	            System.out.println(entry.getKey()+"; "+(faux.getprofesorporNombre(entry.getKey(), mapaPersonas)).getdni()+"; "+entry.getValue()+"\r\n");
	        } 
		  		escritura.close();
		 }catch(Exception e) {
			 System.out.println(e.getMessage());
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
