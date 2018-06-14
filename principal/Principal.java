package principal;

import java.util.GregorianCalendar;
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
		mapaAsignaturas.putAll(leertxt.leerasignaturas(datos.direccionasig));
		mapaAulas.putAll(leertxt.leeraulas(datos.direccionaula));
		mapaPersonas.putAll(leertxt.leeralumno(datos.direccionalu,mapaAsignaturas,mapaAulas));
		mapaPersonas.putAll(leertxt.leerprofesor(datos.direccionprof,mapaAsignaturas));

		
		String comando1 = "Matricula 44185060F ATR";
		String comando2 = "Evalua ATR";
		Profesor ejemplo =faux.getprofesor("41074273T", mapaPersonas);
		System.out.println(ejemplo.gethorario(mapaAsignaturas));
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
		Fprincipales.Matricula(mapaPersonas, mapaAsignaturas, comando1);
		Fprincipales.Evalua(mapaPersonas, mapaAsignaturas,comando2);
		Escribirtxt.Alumnos(mapaPersonas);
	}//}
}