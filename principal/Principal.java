package principal;

import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import funciones.Comprobar;
import funciones.Escribirtxt;
import funciones.Fprincipales;
import funciones.cambios;
import funciones.datos;
import funciones.faux;
import funciones.leertxt;

public abstract class Principal {
	public static void main(String [] args) {
		Map<String,Persona> mapaPersonas= new TreeMap<String,Persona>();
		Map<String,Asignatura> mapaAsignaturas= new TreeMap<String,Asignatura>();
		Map<String,Aula> mapaAulas=new TreeMap<String,Aula>();
		Map<String, Float> listaPOD = new TreeMap<String, Float>();
		Map<String, Float> listaPODordenada = new LinkedHashMap<String, Float>(); 
		mapaAsignaturas.putAll(leertxt.leerasignaturas(datos.direccionasig));
		mapaAulas.putAll(leertxt.leeraulas(datos.direccionaula));
		mapaPersonas.putAll(leertxt.leeralumno(datos.direccionalu,mapaAsignaturas,mapaAulas));
		mapaPersonas.putAll(leertxt.leerprofesor(datos.direccionprof,mapaAsignaturas));
		String[] linea=leertxt.leerejecucion(datos.direccionejec);
		
		for(int i=0;i<linea.length;i++) {
			linea[i]=faux.quitarespacios(linea[i].trim());
			String[] paraswitch=linea[i].split(" ");
			String clave=paraswitch[1];
			//Switch con todas las instrucciones
			switch (faux.minusculas(clave)) {
			case "insertapersona":	
				Fprincipales.InsertaPersona(mapaPersonas, linea[i].substring(2));
				break;
			case "asignagrupo":
				Fprincipales.AsignarGrupo(mapaPersonas, mapaAsignaturas, mapaAulas, linea[i]);
				System.out.println("AsignarGrupo non esta rematada     "+i);
				break;
			case "matricula":
				Fprincipales.Matricula(mapaPersonas, mapaAsignaturas, linea[i].substring(3));
				break;
			case "evalua":
				Fprincipales.AsignarGrupo(mapaPersonas, mapaAsignaturas, mapaAulas, linea[i].substring(3));
				System.out.println("Non sei se esta 100/100 rematada evalua");
				break;
			case "expediente":
				Fprincipales.Expediente(mapaPersonas, mapaAsignaturas,linea[i].substring(2));
				
				break;
			case "ocupacionaula":
				Fprincipales.ocupacionAula(linea[i].substring(2), mapaAulas, mapaAsignaturas, mapaPersonas);
				break;
				
			case "ordenarpod": //No esta en ejecucion, hablar con Paco
				System.out.println("Non hai funcion para invocar pod");
				//llamada a funcion()
				break;
			default:
				Escribirtxt.Avisos("Comando incorrecto: "+linea[i].substring(2));
				break;
			
			}	
			
		}
		
		
		
		//listaPOD.putAll(Pod.leerpod(mapaPersonas));
		//listaPODordenada.putAll(Pod.calcularPODteorico(mapaAsignaturas,listaPOD,mapaPersonas));
		//Escribirtxt.EscribirPODteorico(listaPODordenada,mapaPersonas, "QUEPASA");
		
		
		//String comando1 = "Matricula 44185060F ATR";
		//String comando2 = "Evalua ATR";
		//Profesor ejemplo =faux.getprofesor("41074273T", mapaPersonas);
		//System.out.println(ejemplo.gethorario(mapaAsignaturas));
		
		//Aula ejemplo2=faux.getaula("AS05", mapaAulas);
		//System.out.print(ejemplo2.getHorario(mapaAsignaturas, mapaPersonas));
		
		
		//String aux[][] =faux.getformatotabla(ejemplo2.getHorario(mapaAsignaturas, mapaPersonas));
		//System.out.print(ejemplo2.getHorario(mapaAsignaturas, mapaPersonas));
		/*for(int i=0;i<filas;i++) {
			for(int k=0;k<columnas;k++) {
				Escribirtxt.FicheroPrueba(aux[i][k]+"\t");
			}
			Escribirtxt.FicheroPrueba("\r\n");
		}/*
			
			
		//while(!comando.equals("end")) {
		//System.out.println("Escriba o comando aqui: ");
		//Scanner teclado = new Scanner (System.in);
		//comando= teclado.nextLine();
		//if(!Comprobar.comando(comando)) {
		//	Escribirtxt.Avisos("Comando incorrecto: "+comando);
		//}
		//else {
		/*Fprincipales.InsertaPersona(mapaPersonas, comando);
		Fprincipales.Matricula(mapaPersonas, mapaAsignaturas, comando);
		}
		Escribirtxt.Alumnos(mapaPersonas);
		Escribirtxt.Profesores(mapaPersonas);
		}*/
		//Fprincipales.Matricula(mapaPersonas, mapaAsignaturas, comando1);
		//Fprincipales.Evalua(mapaPersonas, mapaAsignaturas,comando2);
	}}
