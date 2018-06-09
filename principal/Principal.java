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
		mapaPersonas.putAll(leertxt.leeralumno(datos.direccionalu));
		mapaPersonas.putAll(leertxt.leerprofesor(datos.direccionprof));
		mapaAsignaturas.putAll(leertxt.leerasignaturas(datos.direccionasig));
		String comando = " ";
		while(!comando.equals("end")) {
		System.out.println("Escriba o comando aqui: ");
		Scanner teclado = new Scanner (System.in);
		comando= teclado.nextLine();
		if(!Comprobar.comando(comando)) {
			Escribirtxt.Avisos("Comando incorrecto: "+comando);
		}
		else {
		//Fprincipales.InsertaPersona(mapaPersonas, comando);
		Fprincipales.Matricula(mapaPersonas, mapaAsignaturas, comando);
		}
		Escribirtxt.Alumnos(mapaPersonas);
		Escribirtxt.Profesores(mapaPersonas);
		}
	}
	}	