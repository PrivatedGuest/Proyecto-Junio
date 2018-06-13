package funciones;

import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Map;

import principal.Alumno;
import principal.Asignatura;
import principal.Grupo;
import principal.Persona;
import principal.Profesor;

public class Fprincipales{
	public static Map<String,Persona> InsertaPersona(Map<String,Persona> mapaPersonas,String persona) {
		persona=persona.trim();
		persona=faux.quitarespacios(persona);
		String[] datos;
		int errores=0;
		datos=persona.split(" ");
		if(datos.length<6) {
			Escribirtxt.Avisos("Numero de argumentos incorrecto: "+persona);
			return mapaPersonas;
		}
		String dni= datos[2];
		String nombre=datos[3];
		String perfil= faux.minusculas(datos[1]);//DATOS 1 E O PERFIL
		if(perfil.equals("alumno")&&datos.length!=6) {
			Escribirtxt.Avisos("Numero de argumentos incorrecto: "+persona);
			return mapaPersonas;
		}
		else if(perfil.equals("profesor")&&datos.length!=7) {
			Escribirtxt.Avisos("Numero de argumentos incorrecto: "+persona);
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
	
	
	public static Map<String,Persona> AsignarGrupo(Map<String,Persona> mapaPersonas,Map<String,Asignatura> mapaAsignaturas,String comando){
		int errores=0;
		String[] aux=comando.split(" ");
		String perfilX= aux[1].trim().toLowerCase();
		String dniX=aux[2].trim();
		String siglasX=aux[3].trim();
		String tipoX=aux[4].trim().toUpperCase();
		String idX=aux[5].trim();
		Asignatura asigX = null;
		Alumno alu	=	null;
		int errorasig=0;
		if(perfilX.equals("profesor")&&!faux.existepersona(dniX, mapaPersonas)) {
			errores++;
			Escribirtxt.Avisos("Profesor inexistente");
		}
		else if(perfilX.equals("alumno")&&!faux.existepersona(dniX, mapaPersonas)) {
			errores++;
			Escribirtxt.Avisos("Profesor inexistente");
		}
		if(faux.existeasignatura(siglasX, mapaAsignaturas)) {
			errorasig++;
			errores++;
			Escribirtxt.Avisos("Asignatura inexistente");
		}
		if(!tipoX.equals("A")&&!tipoX.equals("B")) {
			errores++;
			Escribirtxt.Avisos("Tipo de grupo incorrecto");
		}
		
		if(errorasig==0) {
			asigX=faux.getasignatura(siglasX, mapaAsignaturas);
			if(!faux.existegrupo(tipoX, asigX.getmapagrupos())) {
				errores++;
				Escribirtxt.Avisos("Grupo inexistente");
			}
		}
		if(perfilX.equals("profesor")) {
			if(faux.grupoyaasignado(mapaPersonas, siglasX, tipoX, idX)) {
				errores++;
				Escribirtxt.Avisos("Grupo ya asignado");
			}
		}
		else {//se non e vaca e boi
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
		if(asigX!=null) {
		String horario=faux.obterhorariogrupo(asigX, tipoX, idX);
		String[] auxi=horario.split(" ");
		String dia= auxi[0];
		String horainicio=auxi[1];
		String horafinal=auxi[2];
		String cuatrimestre=faux.getcuatrimestre(siglasX, mapaAsignaturas).trim();
		if(faux.existesolape(alu,cuatrimestre,dia, horainicio, horafinal, mapaAsignaturas)) {
			errores++;
			//Escribirtxt.Avisos(imprimir);
		}
		}
		return mapaPersonas;
		
	}
	
	
	}






















