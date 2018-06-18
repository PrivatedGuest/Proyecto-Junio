package funciones;

import java.io.File;
import java.io.FileWriter;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.TreeMap;

import principal.Alumno;
import principal.Asignatura;
import principal.Aula;
import principal.Grupo;
import principal.Persona;
import principal.Profesor;

public class ordenar {
	
	public static Map<String,Persona> alumnos(String[] todos,Map<String,Asignatura> mapaAsignaturas,Map<String,Aula> mapaAulas) {
		Map<String,Persona> mapaPersonaX= new TreeMap<String,Persona>();
		int contadorAlumnos;
		Map<String,Asignatura> asignaturas = null;
		GregorianCalendar fechaNacX = (GregorianCalendar) GregorianCalendar.getInstance() ;
		GregorianCalendar fechaIngX = (GregorianCalendar) GregorianCalendar.getInstance() ;
		for(contadorAlumnos=0;contadorAlumnos<todos.length;contadorAlumnos++) {//bucle repitese para todos os alumnos
			asignaturas= new TreeMap<String,Asignatura>();
		asignaturas.clear();
		
		int existeemail=0;
		int mapaasignaturas=0;
		
		String[] alumnoi;
		int contador=0;
		String dniX="",nombreX="",emailX = "";
		int contadorcorreo=0;
		//antes o float era double, se hai fallos inexplicables pode ser deso(?)
		String siglaX;String cursoX;float notaX;
		alumnoi=null;
		alumnoi=todos[contadorAlumnos].split("[\n]");//Dividimos os campos do alumnoi
				int lineaj=0;
				int w=1;
			
		
			if(contadorAlumnos>0) {
				for(w=1;w<alumnoi.length;w++)
				alumnoi[w-1]=alumnoi[w];//Indice W pasa a ser W+1
				alumnoi[w-1]=" ";//o salir do for sale w+1, para eliminar o ultimo eliminamos w-1 xa que sale incrementada, tamen se pode facer co length
				alumnoi[w-1]=alumnoi[w-1].trim();
			}
				
				for(lineaj=0;lineaj<alumnoi.length;lineaj++) {//En cada alumno, asegurase de que haxa contido nunha linea 
					alumnoi[lineaj]=alumnoi[lineaj].trim();//dendendo da linea na que estemos, farase unha cousa ou outra(switch);
					if(alumnoi[lineaj].length()!=0) {//os casos indican en que linea do ficheiro estamos
						switch (lineaj) {//o contador é un axuste para eliminar as linea en blanco
						
						case 0:	
							dniX=alumnoi[lineaj];
							break;
						case 1:
							nombreX=alumnoi[lineaj];
							break;
						case 2:
							existeemail++;
							emailX=alumnoi[lineaj];
							break;
						case 3:
							fechaNacX=cambios.String_GregorianCalendar(alumnoi[lineaj]);
							break;
						case 4:
							fechaIngX=cambios.String_GregorianCalendar(alumnoi[lineaj]);
							break;
						case 5:
							mapaasignaturas++;
							String[] auxiliar1case5=alumnoi[lineaj].split(";");//dentro da 5 linea, separamos as asignaturas para facer un mapa
							String[] auxiliar2case5;
							int k;
							for(k=0;k<auxiliar1case5.length;k++) {//aqui solo elimino os espacios iniciales das asignaturas
								auxiliar1case5[k]=auxiliar1case5[k].trim();
								auxiliar1case5[k]=faux.quitarespacios(auxiliar1case5[k]);
							}
							for(k=0;k<auxiliar1case5.length;k++) {
								auxiliar2case5=auxiliar1case5[k].split(" ");//separamos as palabras
								siglaX=auxiliar2case5[0].trim();
								cursoX=auxiliar2case5[1].trim();
								notaX=Float.parseFloat(auxiliar2case5[2].trim());
								asignaturas.put(siglaX,new Asignatura(siglaX,cursoX,notaX));
							}//Gardamos todo en asignaturas, buscamos por nombre e a docencia recibida chamaremoslle
							
							break;//"asignatura+grupo" deste xeito para separar faremos .cointain"+"
						
						case 6:
							mapaasignaturas++;
							String[] auxiliar1case6=alumnoi[lineaj].split(";");
							String[] auxiliar2case6;
							k=0;
							for(k=0;k<auxiliar1case6.length;k++) {//aqui solo elimino os espacios iniciales das asignaturas
								auxiliar1case6[k]=auxiliar1case6[k].trim();
								auxiliar1case6[k]=faux.quitarespacios(auxiliar1case6[k]);
							}//Auxiliar1case6 ten separadas as asignaturas
							for(k=0;k<auxiliar1case6.length;k++) {
								auxiliar2case6=auxiliar1case6[k].split(" ");
								siglaX =auxiliar2case6[0].trim();
									if(auxiliar2case6.length==1) {
										asignaturas.put(siglaX+"+"+"M",new Asignatura(siglaX));
									}	
									else {
										String tipoX =auxiliar2case6[1].trim();
										String identificadorX=auxiliar2case6[2].trim();
										String aux=siglaX+"+"+tipoX;
										asignaturas.put(aux, new Asignatura(siglaX,tipoX,identificadorX,mapaAsignaturas,mapaAulas));		
									}
							}
						}	//salimos do switch
					}//salida do "if" para asegurar que a linea nn esta en blanco
				}//salimos do for do alumno, e dicir o alumno esta listo
			//temos diferentes construtores porque poden faltar campos na construccion
				if(existeemail>0&&mapaasignaturas>0) {
					Alumno auxiliar=new Alumno(dniX,nombreX,emailX,fechaNacX,fechaIngX,asignaturas);
					mapaPersonaX.put(dniX, auxiliar);
					auxiliar.setcuatrimestres(mapaAsignaturas);
				}
				else if(existeemail==0&&mapaasignaturas>0) {
					Alumno auxiliar= new Alumno(dniX,nombreX,fechaNacX,fechaIngX,asignaturas);
					mapaPersonaX.put(dniX, auxiliar);
					auxiliar.setcuatrimestres(mapaAsignaturas);
				}
				else if(existeemail>0&&mapaasignaturas==0) {
					Alumno auxiliar= new Alumno(dniX,nombreX,emailX,fechaNacX,fechaIngX);
					mapaPersonaX.put(dniX, auxiliar);
					auxiliar.setcuatrimestres(mapaAsignaturas);
				}
				else if(existeemail==0&&mapaasignaturas==0) {
					Alumno auxiliar=new Alumno(dniX,nombreX,fechaNacX,fechaIngX,asignaturas);
					mapaPersonaX.put(dniX,auxiliar);
					auxiliar.setcuatrimestres(mapaAsignaturas);
				}
				else System.out.println("Que pasa aqui");
					}//salimos do for de todos os alumnos
	return mapaPersonaX;		
	}
	
	
	
	
	public static Map<String,Persona> profesores(String[] todos,Map<String,Asignatura> mapaAsignaturas) {
		Map<String,Persona> mapaPersonaX= new TreeMap<String,Persona>();
		int contadorProfesores;
		Map<String,Asignatura> asignaturas = null;
		GregorianCalendar fechaNacX = (GregorianCalendar) GregorianCalendar.getInstance() ;
		for(contadorProfesores=0;contadorProfesores<todos.length;contadorProfesores++) {//bucle repitese para todos os profesores
			asignaturas= new TreeMap<String,Asignatura>();
		asignaturas.clear();
		
		int mapaasignaturas=0;
		
		String[] profesori;
		String dniX="",nombreX="",categoriaX="",departamentoX="";
		String siglaX;String cursoX;double notaX;
		profesori=null;
		profesori=todos[contadorProfesores].split("[\n]");//Dividimos os campos do alumnoi
				int lineaj=0;
				int w=1;
			
		
			if(contadorProfesores>0) {
				for(w=1;w<profesori.length;w++)
				profesori[w-1]=profesori[w];//Indice W pasa a ser W+1
				profesori[w-1]=" ";//o salir do for sale w+1, para eliminar o ultimo eliminamos w-1 xa que sale incrementada, tamen se pode facer co length
				profesori[w-1]=profesori[w-1].trim();
			}
				
				for(lineaj=0;lineaj<profesori.length;lineaj++) {//En cada alumno, asegurase de que haxa contido nunha linea 
					profesori[lineaj]=profesori[lineaj].trim();//dendendo da linea na que estemos, farase unha cousa ou outra(switch);
					if(profesori[lineaj].length()!=0) {//os casos indican en que linea do ficheiro estamos
						switch (lineaj) {//o contador é un axuste para eliminar as linea en blanco
						
						case 0:	
							dniX=profesori[lineaj];
							break;
						case 1:
							nombreX=profesori[lineaj];
							break;
						case 2:
							fechaNacX=cambios.String_GregorianCalendar(profesori[lineaj]);
							break;
						case 3:
							categoriaX=profesori[lineaj];
							break;
						case 4:
							departamentoX=profesori[lineaj];
							break;						
						case 5:
							mapaasignaturas++;
							String[] auxiliar1case6=profesori[lineaj].split(";");
							String[] auxiliar2case6;
							int k=0;
							for(k=0;k<auxiliar1case6.length;k++) {//aqui solo elimino os espacios iniciales das asignaturas
								auxiliar1case6[k]=auxiliar1case6[k].trim();
								auxiliar1case6[k]=faux.quitarespacios(auxiliar1case6[k]);
							}//Auxiliar1case6 ten separadas as asignaturas
							for(k=0;k<auxiliar1case6.length;k++) {
								auxiliar2case6=auxiliar1case6[k].split(" ");
								siglaX =auxiliar2case6[0].trim();
									if(auxiliar2case6.length==1) {
										asignaturas.put(siglaX+"+"+"M",new Asignatura(siglaX));
									}	
									else {
										String tipoX =auxiliar2case6[1].trim();
										String identificadorX=auxiliar2case6[2].trim();
										String aux=siglaX+"+"+tipoX+"+"+identificadorX;
										asignaturas.put(aux, new Asignatura(siglaX,tipoX,identificadorX));		
									}
							}
						}	//salimos do switch
					}//salida do "if" para asegurar que a linea nn esta en blanco
				}//salimos do for do alumno, e dicir o alumno esta listo
			//temos diferentes construtores porque poden faltar campos na construccion
				if(mapaasignaturas>0) {
					Profesor auxi= new Profesor(dniX,nombreX,fechaNacX,categoriaX,departamentoX,asignaturas);
					mapaPersonaX.put(dniX,auxi);
					auxi.setcuatrimestres(mapaAsignaturas);
				}
				else if(mapaasignaturas==0) {
					Profesor auxi=new Profesor(dniX,nombreX,fechaNacX,categoriaX,departamentoX);
					mapaPersonaX.put(dniX,auxi );
					auxi.setcuatrimestres(mapaAsignaturas);
				}					
				else System.out.println("Que pasa aqui");
					}//salimos do for de todos os profesores
	return mapaPersonaX;		
	}
	
	
	

	public static Map<String,Asignatura> asignaturas(String[] todos) {
		Map<String,Asignatura> mapaAsignaturasX= new TreeMap<String,Asignatura>();
		int contadorAsignaturas;
		Map<String,Grupo> mapaGruposX = null;
		String siglasX= "";
		String nombreX= "";
		String cursoX= "";
		String cuatrimestreX= "";
		String coordinadorX = "";
		String prerequisitosX = " ";
		String idX,diaX,aulaX;float horainicioX;
		prerequisitosX=prerequisitosX.trim();
		String duracionAX= "";
		String duracionBX= "";
		int existePrerequisitos=0;
		for(contadorAsignaturas=0;contadorAsignaturas<todos.length;contadorAsignaturas++) {//Repitese para todas as asignaturas
			prerequisitosX="\r\n";
			mapaGruposX= new TreeMap<String,Grupo>();
			mapaGruposX.clear();
			String[] asignaturai=todos[contadorAsignaturas].split("[\n]");
			int lineaj=0;
			int w=1;
			String[] aux=null;
	
		if(contadorAsignaturas>0) {
			for(w=1;w<asignaturai.length;w++)
			asignaturai[w-1]=asignaturai[w];//Indice W pasa a ser W+1
			asignaturai[w-1]=" ";//o salir do for sale w+1, para eliminar o ultimo eliminamos w-1 xa que sale incrementada, tamen se pode facer co length
			asignaturai[w-1]=asignaturai[w-1].trim();
		}
		
		for(lineaj=0;lineaj<asignaturai.length;lineaj++) {
			asignaturai[lineaj]=asignaturai[lineaj].trim();		
			if(asignaturai[lineaj].length()!=0) {
				switch (lineaj) {
				
				case 0:
					siglasX= asignaturai[lineaj];
					break;
				case 1:
					nombreX=asignaturai[lineaj];
					break;
				case 2:
					cursoX=asignaturai[lineaj];
					break;
				case 3:
					cuatrimestreX=asignaturai[lineaj];
					break;
				case 4:
					coordinadorX=asignaturai[lineaj];
					break;
				case 5:
					existePrerequisitos++;
					aux= asignaturai[lineaj].split(";");
					for(int i=0;i<aux.length;i++) {
						aux[i]=aux[i].trim();
						aux[i]=faux.quitarespacios(aux[i]);
								if(i==0) {//Este if e para eliminar o ; do principio
										prerequisitosX=aux[i];
									}
								else prerequisitosX=prerequisitosX+";"+aux[i];
					}
					break;
				case 6:
					duracionAX=asignaturai[lineaj];
					break;
				case 7:
					duracionBX= asignaturai[lineaj];
					break;
				case 8:
					String[] auxiliar1case8=asignaturai[lineaj].split(";");//dentro da 5 linea, separamos as asignaturas para facer un mapa
					String[] auxiliar2case8;
					int k;
					for(k=0;k<auxiliar1case8.length;k++) {//aqui solo elimino os espacios iniciales das asignaturas
						auxiliar1case8[k]=auxiliar1case8[k].trim();
						auxiliar1case8[k]=faux.quitarespacios(auxiliar1case8[k]);
					}
					for(k=0;k<auxiliar1case8.length;k++) {
						auxiliar2case8=auxiliar1case8[k].split(" ");//separamos as palabras
						idX=auxiliar2case8[0].trim();
						diaX=auxiliar2case8[1].trim();
						horainicioX=Float.parseFloat(auxiliar2case8[2].trim());
						aulaX=auxiliar2case8[3].trim();
						mapaGruposX.put("A"+"+"+idX,new Grupo("A",idX,diaX,horainicioX,aulaX));
					}//O mapa dos grupos ira dentro de cada asignatura
					
					break;//"asignatura+grupo" deste xeito para separar faremos .cointain"+"
				case 9:
					String[] auxiliar1case9=asignaturai[lineaj].split(";");//dentro da 5 linea, separamos as asignaturas para facer un mapa
					String[] auxiliar2case9;
					for(k=0;k<auxiliar1case9.length;k++) {//aqui solo elimino os espacios iniciales das asignaturas
						auxiliar1case9[k]=auxiliar1case9[k].trim();
						auxiliar1case9[k]=faux.quitarespacios(auxiliar1case9[k]);
					}
					for(k=0;k<auxiliar1case9.length;k++) {
						auxiliar2case9=auxiliar1case9[k].split(" ");//separamos as palabras
						idX=auxiliar2case9[0].trim();
						diaX=auxiliar2case9[1].trim();
						horainicioX=Float.parseFloat(auxiliar2case9[2].trim());
						aulaX=auxiliar2case9[3].trim();
						mapaGruposX.put("B"+"+"+idX,new Grupo("B",idX,diaX,horainicioX,aulaX));
					}//O mapa dos grupos ira dentro de cada asignatura
					
					break;
				}//fin do switch
			}//Fin do if por se a linea esta vacia
		}//Fin do for para ler todas as lineas
		if(existePrerequisitos>0) {
			//Aqui cambiouse, antes estaba o new directamente no put
			Asignatura auxi=new Asignatura(siglasX,nombreX,cursoX,cuatrimestreX,coordinadorX,prerequisitosX,duracionAX,duracionBX,mapaGruposX);
			mapaAsignaturasX.put(nombreX,auxi);
			auxi.setHorafinalGrupos();
		}
		else {
			Asignatura auxi=new Asignatura(siglasX,nombreX,cursoX,cuatrimestreX,coordinadorX,duracionAX,duracionBX,mapaGruposX);
			mapaAsignaturasX.put(nombreX,auxi );
			auxi.setHorafinalGrupos();
		}
		}//Fin do ford para todas as asignaturas
		return mapaAsignaturasX;
	}//Fin do metodo

	
	
	public static Map<String,Aula> aulas(String[] todos){
		Map<String,Aula> mapaAsignaturasX= new TreeMap<String,Aula>();
		int contador=0;
		for(int i=0;i<todos.length;i++) {	
			
			String[] aux=todos[i].split("\n");
			if(contador>0) {
				int w=1;
				for(w=1;w<aux.length;w++)
					aux[w-1]=aux[w];//Indice W pasa a ser W+1
					aux[w-1]=" ";//o salir do for sale w+1, para eliminar o ultimo eliminamos w-1 xa que sale incrementada, tamen se pode facer co length
					aux[w-1]=aux[w-1].trim();
			}
			contador++;
			String siglas=faux.quitarespacios(aux[0].trim());
			String tipo=faux.quitarespacios(aux[1].trim());
			String capacidad=faux.quitarespacios(aux[2].trim());
			
			
			
			mapaAsignaturasX.put(siglas, new Aula(siglas,tipo,capacidad));						
		}
		
		return mapaAsignaturasX;
	}}
