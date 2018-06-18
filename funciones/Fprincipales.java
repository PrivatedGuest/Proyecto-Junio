package funciones;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Map;

import principal.Alumno;
import principal.Asignatura;
import principal.Aula;
import principal.Grupo;
import principal.Persona;
import principal.Profesor;

public class Fprincipales{
	public static Map<String,Persona> InsertaPersona(Map<String,Persona> mapaPersonas,String comando) {
		comando=comando.trim();
		comando=faux.quitarespacios(comando);
		String[] datos;
		int errores=0;
		datos=comando.split(" ");
		if(datos.length<6) {
			Escribirtxt.Avisos("Numero de argumentos incorrecto: "+comando);
			return mapaPersonas;
		}
		String dni= datos[2];
		int auxij=comando.indexOf("\"");
		int auxij2=comando.indexOf("\"", auxij+1);
		String paratamaño=comando.substring(0, auxij);
		String auxiliartamaño=comando.substring(auxij+1, auxij2);//Este e o nombre
		String auxiliar2tamaño=comando.substring(auxij2+1).trim();
		auxiliartamaño=auxiliartamaño.replace(" ", "w");
		paratamaño=paratamaño+" "+auxiliartamaño+" "+auxiliar2tamaño;
		String nombre=comando.substring(auxij+1,auxij2);
		String perfil= faux.minusculas(datos[1]);//DATOS 1 E O PERFIL
		paratamaño=faux.quitarespacios(paratamaño.trim());
		int tamaño=paratamaño.split(" ").length;
		datos=paratamaño.split(" ");
		//teremos o tamaño normal pero o nombre non
		if(perfil.equals("alumno")&&tamaño!=6) {
			Escribirtxt.Avisos("Numero de argumentos incorrecto: "+comando);
			return mapaPersonas;
		}
		else if(perfil.equals("profesor")&&tamaño!=7) {
			Escribirtxt.Avisos("Numero de argumentos incorrecto: "+comando);
			return mapaPersonas;
		}
			
		if(!Comprobar.dni(dni)) {
			errores++;
			Escribirtxt.Avisos("DNI incorrecto");
		}
		String fechaNac=datos[4];
		if(!Comprobar.fecha(fechaNac)) {
			errores++;
			Escribirtxt.Avisos("Fecha incorrecta");
		}
			if(perfil.equals("alumno")) {
					String fechaIng=datos[5];
					if(!Comprobar.fecha(fechaIng)) {
									errores++;
									Escribirtxt.Avisos("Fecha incorrecta");
									}
					if(!Comprobar.compara_fechas(fechaNac, fechaIng)) {
									errores++;
									Escribirtxt.Avisos("Fecha de ingreso incorrecta");
									}
					if(faux.existepersona(dni, mapaPersonas)) {
							errores++;
							Escribirtxt.Avisos("Alumno ya existente");
									}
					if(errores==0) {
								//temos que crear un mapa de asignaturas e agregalo no constructor, pero o mapa de doncenia?
								GregorianCalendar fechaNAC=cambios.String_GregorianCalendar(fechaNac);
								String aux= cambios.GregorianCalendar_String(fechaNAC);
								//asdasdasdasdasdasdasdasdasdasdasdasdasdSystem.out.println(cambios.GregorianCalendar_String(fechaNAC));
								GregorianCalendar fechaING= cambios.String_GregorianCalendar(fechaIng);
								mapaPersonas.put(dni, new Alumno(dni,nombre,fechaNAC,fechaING));
									}		
				     return mapaPersonas;
						}
			
			else if(perfil.equals("profesor")) {
						String categoria=datos[5];
						String departamento=datos[6];
						Iterator it=mapaPersonas.keySet().iterator();
						if(faux.existepersona(dni, mapaPersonas)) {
							errores++;
							Escribirtxt.Avisos("Profesor ya existente");
						}
					if(errores==0) {
						GregorianCalendar fechaNAC=cambios.String_GregorianCalendar(fechaNac);
						mapaPersonas.put(dni, new Profesor(dni,nombre,fechaNAC,categoria,departamento));		
					}
			
		return mapaPersonas;
		
	}
					
			
			else return mapaPersonas;
		}
	public static Map<String,Persona> Matricula(Map<String,Persona> mapaPersonas,Map<String,Asignatura> mapaAsignaturas,String comando){
		comando= comando.trim();
		comando= faux.quitarespacios(comando);
		String[] comandoi= comando.split(" ");
		int errores=0;
		if(comandoi.length!=3) {
			Escribirtxt.Avisos("Numero de argumentos incorrecto: "+comando);
			return mapaPersonas;
		}
		if(!faux.existepersona(comandoi[1].trim(), mapaPersonas)) {
			errores++;
			Escribirtxt.Avisos("Alumno inexistente: "+comandoi[1]);
		}
		if(!faux.existeasignatura(comandoi[2].trim(), mapaAsignaturas)) {
			errores++;
			Escribirtxt.Avisos("Asignatura inexistente: "+comandoi[2]);	
		}
		//obtemos o alumno cuyo dni me pasaron
		Alumno alumno=null;
		if(errores==0) {
		for(Map.Entry<String, Persona> p: mapaPersonas.entrySet()) {
			if(p.getKey().equals(comandoi[1].trim())) {
				alumno=(Alumno) p.getValue();
			}
		}
		if(faux.yaesalumno(alumno, comandoi[2])) {
			errores++;
			Escribirtxt.Avisos("Ya es alumno de la asignatura indicada: ");
		}
		Asignatura asig=null;
		//PROBAR A SUBSTITUIR ESTO POLA FUNCION GETASIGNATURA
		for(Map.Entry<String, Asignatura> a: mapaAsignaturas.entrySet()) {
			if(a.getValue().getsiglas().equals(comandoi[2].trim())) {
				asig=a.getValue();
			}
		}
		String prerequisitos=asig.getprerequisitos();
		System.out.println(prerequisitos);
		if(!faux.prerequisitos(alumno, prerequisitos)) {
			errores++;
			Escribirtxt.Avisos("No cumple requisitos");
		}}//salimos do if de que nn haxa errores
		
		if(errores==0) {
			String siglas=comandoi[2].trim()+"+"+"M";
			Asignatura auxiliar= new Asignatura(comandoi[2].trim());
			alumno.engadirasig(siglas,auxiliar);
		}
		
		return mapaPersonas;
	}
	
	
	public static Map<String,Persona> Evalua(Map<String,Persona> mapaPersonas,Map<String,Asignatura> mapaAsignaturas,String comando){
		comando=comando.trim();
		comando=faux.quitarespacios(comando);
		String[] aux=comando.split(" ");
		String A=faux.notasdearquivo("Notas CD A.txt");
		String B=faux.notasdearquivo("Notas CD B.txt");
		String alunotas=faux.agrupar(A, B);
//ACORDARSE DE PREGUNTAR SE OS FICHEROS PODEN LEVAR ESPACIOS!!!!!ACORDARSE!!!!ACORDARSE!!!! OU SE ACABAN SIEMPRE EN TXT!!! OU QUE!!		
		String siglasasig= aux[1].trim();
		int errores=0;
		if(!faux.existeasignatura(siglasasig, mapaAsignaturas)) {
			errores++;
			Escribirtxt.Avisos("Asignatura inexistente");
		}
		else {//Comprobamos que algun alumno teña a asignatura aprobada para ver se xa foi evaluada
			int evaluada=0;
			for(Map.Entry<String, Persona> p: mapaPersonas.entrySet()) {
				Alumno alu = null;
				if(p.getValue() instanceof Alumno) {
					alu= (Alumno) p.getValue();
					for(Map.Entry<String, Asignatura> asig: alu.getasignaturas().entrySet()) {
						if(asig.getKey().contains(siglasasig)&&!asig.getKey().contains("+")) {
							evaluada++;
						}
					}
				}//Buscamos no mapa dese alumno, se hai unha asignatura con esas siglas sen "+" e que xa foi evaluada
				
			}
			if(evaluada!=0) {
				errores++;
				Escribirtxt.Avisos("Asignatura ya evaluada en ese curso academico");
			}
		}//O sair de aqui temos todos os avisos das asignaturas xa xerados,faltan o dos alumnos
		if(errores==0) {
		String[] alumnoi=alunotas.split("\n");
		for(int i=0;i<alumnoi.length;i++) {//Para todas as linea da cadena que me pasa
			errores=0;
			String[] auxiliar= alumnoi[i].split(" ");
			String dniX=auxiliar[0].trim();
			String nAX=auxiliar[1].trim();
			Alumno alumnoX=faux.getalumno(dniX, mapaPersonas);
			String nBX=auxiliar[2].trim();
			float notaAX=Float.parseFloat(nAX);
			float notaBX=Float.parseFloat(nBX);
			String paraoserrores=String.valueOf(i+1);
			if(!faux.existepersona(dniX, mapaPersonas)) {
				errores++;
				Escribirtxt.Avisos("Error en linea "+paraoserrores+" Alumno inexistente: "+dniX);
			}
			if(!faux.alumnomatriculado(alumnoX,siglasasig)) {
				errores++;
				Escribirtxt.Avisos("Error en linea "+paraoserrores+" Alumno no matriculado: "+dniX);
			}
			if(notaAX<0||notaBX<0||notaAX>5||notaBX>5) {
				errores++;
				Escribirtxt.Avisos("Error en linea "+paraoserrores+" Nota grupoA/B incorrecta");
			}
			//falta facer o de alumno no matriculado e despois a funcion 
			
			if(errores==0) {
				alumnoX.eliminardocencia(siglasasig);
				float notatot=notaAX+notaBX;
				if(notatot>=5) {
					String curso=datos.getcurso();
					alumnoX.asigsuperada(siglasasig,notatot,curso);
				}
			}			
		}
		}//Todo o anterior e se non hai errores nas asignaturas		
		return mapaPersonas;
	}
	
	
	public static Map<String,Persona> AsignarGrupo(Map<String,Persona> mapaPersonas,Map<String,Asignatura> mapaAsignaturas,Map<String,Aula> mapaAula,String comando){
		int errores=0;
		comando=faux.quitarespacios(comando.trim());
		int correcion=comando.indexOf(" ");
		comando=comando.substring(correcion+1);
		String[] aux=comando.split(" ");
		String perfilX= aux[1].trim().toLowerCase();
		String dniX=aux[2].trim();
		String siglasX=aux[3].trim();
		String tipoX=aux[4].trim().toUpperCase();
		String idX=aux[5].trim();
		Asignatura asigX = null;
		Alumno alu	=	null;
		Profesor prof=null;
		int errorasig=0;
		int noexistealu=0;
		if(perfilX.equals("profesor")&&!faux.existepersona(dniX, mapaPersonas)) {
			errores++;
			Escribirtxt.Avisos("Profesor inexistente");
		}
		else if(perfilX.equals("alumno")&&!faux.existepersona(dniX, mapaPersonas)) {
			errores++;
			noexistealu++;
			Escribirtxt.Avisos("Alumno inexistente");
		}
		if(!faux.existeasignatura(siglasX, mapaAsignaturas)) {
			errorasig++;
			errores++;
			Escribirtxt.Avisos("Asignatura inexistente");
		}
		if(!tipoX.equals("A")&&!tipoX.equals("B")) {
			errores++;
			errorasig++;
			Escribirtxt.Avisos("Tipo de grupo incorrecto");
		}
		
		if(errorasig==0) {
			asigX=faux.getasignatura(siglasX, mapaAsignaturas);
			if(!faux.existegrupo(tipoX+"+"+idX, asigX.getmapagrupos())) {
				errores++;
				errorasig++;
				Escribirtxt.Avisos("Grupo inexistente");
			}
		}
		if(perfilX.equals("profesor")) {
			if(faux.grupoyaasignado(mapaPersonas, siglasX, tipoX, idX)) {
				errores++;
				Escribirtxt.Avisos("Grupo ya asignado");
			}
		}
		else if(noexistealu==0){//se non e vaca e boi
			alu=faux.getalumno(dniX, mapaPersonas);
			if(!faux.alumnomatriculado(alu, siglasX)) {
				Escribirtxt.Avisos("Alumno no matriculado");
			}
		}
		/*FALTAN
		 * DOUS
		 * AVISOS
		 * DO
		 * POD
		 * ASIQUE
		 * HAI
		 * QUE
		 * ANDAR
		 */
		//TEÑO QUE COLLER TODOS OS GRUPOS DO ALUMNO COS IDS E BUSCAR NO MAPA DAS ASIGNATURAS SE SE SOLAPA
		//TENDO EN CONTA A DURACION DE CADA GRUPO
		if(errorasig==0) {
		String horario=faux.gethorariogrupo(asigX, tipoX, idX);
		String[] auxi=horario.split(" ");
		String dia= auxi[0];
		String horainicio=auxi[1];
		String horafinal=auxi[2];
		String cuatrimestre=faux.getcuatrimestre(siglasX, mapaAsignaturas).trim();
		String curso=faux.getcurso(siglasX, mapaAsignaturas);
				if(perfilX.toLowerCase().equals("alumno")&&noexistealu==0) {
					alu=faux.getalumno(dniX, mapaPersonas);
						if(faux.existesolapealumno(alu,curso,cuatrimestre,dia, horainicio, horafinal, mapaAsignaturas)) {
							errores++;
							Escribirtxt.Avisos("Solape alumno");
						}
						String siglasAula=faux.getsiglasaula(siglasX, tipoX, idX, mapaAsignaturas);
						Aula auxil= faux.getaula(siglasAula, mapaAula);
						if(auxil.lleno()) {
							errores++;
							Escribirtxt.Avisos("Aula completa");
						}
				}
				if(perfilX.toLowerCase().equals("profesor")) {
					prof=faux.getprofesor(dniX, mapaPersonas);
					String horarioprof=prof.gethorario(mapaAsignaturas);
						if(faux.existesolapeprofesor(horarioprof,cuatrimestre, dia, horainicio, horafinal)) {
							errores++;
							Escribirtxt.Avisos("Solape profesor");
						}}	
		}//fin de errorasig=0
		
		if(errorasig==0&&errores==0) {
			if(perfilX.toLowerCase().equals("alumno")) {
				alu.asignarGrupo(siglasX, tipoX, idX);
			}
			if(perfilX.toLowerCase().equals("profesor")) {
				prof.asignarGrupo(siglasX, tipoX, idX);
			}
		}
		
		return mapaPersonas;	
	}
	
	
	public static Map<String,Asignatura> CreaGrupoAsig(Map<String,Asignatura> mapaAsignaturas,Map<String,Aula> mapaAulas,String comando){
		comando= faux.quitarespacios(comando.trim());
		String[] aux= comando.split(" ");
		String siglasX=aux[1].trim();
		String tipoX=aux[2].trim();
		String idX=aux[3].trim();
		String diaX=aux[4].trim();
		float hiniX=Float.parseFloat(aux[4].trim());
		String siglasAULAX=aux[5].trim();
		Asignatura asig = null;
		int errores=0;
		if(!faux.existeasignatura(siglasX, mapaAsignaturas)) {
			errores++;
			Escribirtxt.Avisos("Asignatura inexistente");
		}
		else asig=faux.getasignatura(siglasX, mapaAsignaturas);
		if(!tipoX.equals("A")&&!tipoX.equals("B")){
			errores++;
			Escribirtxt.Avisos("Tipo de grupo incorrecto");
		}
		String clavegrupo=tipoX+"+"+idX;
		if(!faux.existegrupo(clavegrupo, asig.getmapagrupos())) {
			errores++;
			Escribirtxt.Avisos("Grupo ya existente");
		}
		if(!diaX.equals("L")&&!diaX.equals("M")&&!diaX.equals("X")&&!diaX.equals("J")&&!diaX.equals("V")) {
			errores++;
			Escribirtxt.Avisos("Dia incorrecto");
		}
		if(!faux.existeaula(siglasAULAX, mapaAulas)) {
			errores++;
			Escribirtxt.Avisos("Aula no existente");
		}
		/*FALTA
		 * O
		 * SOLAPE
		 * DE
		 * AULAS
		 * E
		 * A
		 * FUNCION
		 * POSTERIOR
		 */
		
		return mapaAsignaturas;
	}
	public static void Expediente(Map<String,Persona> mapaPersonas,Map<String,Asignatura> mapaAsignaturas,String comando){	
		FileWriter escritura=null; 	
		if(comando.length()!=3) {
			Escribirtxt.Avisos("Numero de argumentos incorrecto: "+comando);
			return;
		}
		comando=faux.quitarespacios(comando.trim());
		String[] aux=comando.split(" ");
		String dni=aux[1];
		String salida=aux[2];
		if(!faux.existealumno(dni, mapaPersonas)){
			Escribirtxt.Avisos("Alumno inexistente");
			return;
		}
		Alumno alu=faux.getalumno(dni,mapaPersonas);
		String asignaturasaprobadas=alu.getasignaturasprobada();
		String[] auxiliar=asignaturasaprobadas.split("\r\n");
		ArrayList<String> devolver = new ArrayList<String>();
		ArrayList<String> expediente = new ArrayList<String>();
		for(int k=0; k<auxiliar.length;k++){
			auxiliar[k]=faux.quitarespacios(auxiliar[k].trim());
			auxiliar[k]=auxiliar[k].replace(" ",";");
		}
		try {
			File archivo=new File(salida+".txt");
			if(archivo.exists()) {
				escritura= new FileWriter(salida+".txt",true);
			}
			else {
				escritura= new FileWriter(salida+".txt");
			}
		
		float notatotal=0;
		String cursoimparte="";
		for(int i=0; i<(auxiliar.length);i++) {
			String siglas=auxiliar[i].split(";")[0].trim();
			for(Map.Entry<String, Asignatura> p: mapaAsignaturas.entrySet()) {
				if(p.getValue().getsiglas().equals(siglas)) {					
					cursoimparte=p.getValue().getcurso();
				}		
				
			}
			expediente.add(cursoimparte+"; "+auxiliar[i]+"\r\n");
			devolver.add(auxiliar[i].split(";")[1].trim());
			notatotal=notatotal+Float.parseFloat(auxiliar[i].split(";")[1].trim());				
		}
		Collections.sort(expediente);
		for(String s: expediente) {		
			escritura.write(s);
		}	
		float media = notatotal / devolver.size();
		escritura.write(Float.toString(media)+"\r\n");			
		escritura.close();
		}			
		catch(Exception e) {
			e.printStackTrace();
		}				
}
	
	public static void ocupacionAula(String comando,Map<String,Aula> mapaAulas,Map<String,Asignatura> mapaAsignaturas,Map<String,Persona> mapaPersonas) {
		String[] aux=comando.split(" ");
		int filas=20;
		int columnas=6;
		Aula au;
			if(aux[1].equals("*")) {
			for(Map.Entry<String, Aula> aulaX: mapaAulas.entrySet()) {
				String[][] imprimir=faux.getformatotabla(aulaX.getValue().getHorario(mapaAsignaturas, mapaPersonas));
				for(int k=0;k<filas;k++) {
					for(int j=0;j<columnas;j++) {
						System.out.print(imprimir[k][j]+"\t\t");
					}
					System.out.print("\r\n");
				}
			}
			}
			else {
				au=faux.getaula(aux[1], mapaAulas);
				String[][] imprimir=faux.getformatotabla(au.getHorario(mapaAsignaturas, mapaPersonas));
				for(int k=0;k<filas;k++) {
					for(int j=0;j<columnas;j++) {
						System.out.print(imprimir[k][j]+"\t\t");
					}
					System.out.print("\r\n");
				}
			}
		return;
	}
	
	
	}
