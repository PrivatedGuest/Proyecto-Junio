package funciones;

import java.io.File;
import java.io.FileWriter;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.TreeMap;

import principal.Alumno;
import principal.Asignatura;
import principal.Persona;

public class ordenar {
	
	public static Map<String,Persona> alumnos(String[] todos) {
		Map<String,Persona> mapaPersonaX= new TreeMap<String,Persona>();
		int contadorAlumnos;
		Map<String,Asignatura> asignaturas = null;
		GregorianCalendar fechaNacX = (GregorianCalendar) GregorianCalendar.getInstance() ;
		GregorianCalendar fechaIngX = (GregorianCalendar) GregorianCalendar.getInstance() ;
		for(contadorAlumnos=0;contadorAlumnos<todos.length;contadorAlumnos++) {//bucle repitese para todos os alumnos
			asignaturas= new TreeMap<String,Asignatura>();
		asignaturas.clear();
		
		//while(it.hasNext()) {
			
		//											asignaturas.remove(it.next());
		//}
		int existeemail=0;
		int mapaasignaturas=0;
		
		String[] alumnoi;
		int contador=0;
		String dniX="",nombreX="",emailX = "";
		int contadorcorreo=0;
		String siglaX;String cursoX;double notaX;
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
								auxiliar1case5[k]=cambios.quitarespacios(auxiliar1case5[k]);
							}
							for(k=0;k<auxiliar1case5.length;k++) {
								auxiliar2case5=auxiliar1case5[k].split(" ");//separamos as palabras
								siglaX=auxiliar2case5[0].trim();
								cursoX=auxiliar2case5[1].trim();
								notaX=Double.parseDouble(auxiliar2case5[2].trim());
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
								auxiliar1case6[k]=cambios.quitarespacios(auxiliar1case6[k]);
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
										asignaturas.put(aux, new Asignatura(siglaX,tipoX,identificadorX));		
									}
							}
						}	//salimos do switch
					}//salida do "if" para asegurar que a linea nn esta en blanco
				}//salimos do for do alumno, e dicir o alumno esta listo
			//temos diferentes construtores porque poden faltar campos na construccion
				if(existeemail>0&&mapaasignaturas>0) {
					mapaPersonaX.put(dniX, new Alumno(dniX,nombreX,emailX,fechaNacX,fechaIngX,asignaturas));
				}
				else if(existeemail==0&&mapaasignaturas>0) {
					mapaPersonaX.put(dniX, new Alumno(dniX,nombreX,fechaNacX,fechaIngX,asignaturas));
				}
				else if(existeemail>0&&mapaasignaturas==0) {
					mapaPersonaX.put(dniX, new Alumno(dniX,nombreX,emailX,fechaNacX,fechaIngX));
				}
				else if(existeemail==0&&mapaasignaturas==0) {
					mapaPersonaX.put(dniX, new Alumno(dniX,nombreX,fechaNacX,fechaIngX,asignaturas));
				}
				else System.out.println("Que pasa aqui");
					}//salimos do for de todos os alumnos
		
		//Esto son comprobacios que se poden borrar
		int contadoralu=0;
		int axudaarquivo=0;
		FileWriter escritura=null;
			for(Map.Entry<String, Persona> p: mapaPersonaX.entrySet()) {
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
					//System.out.print(auxiliar.imp());
				    escritura.write(auxiliar.impasig());
					//System.out.print(auxiliar.impasig());
					if(contadoralu<Alumno.getalumnostotal()) {
						escritura.write("*\r\n");
					//	System.out.print("*\n");
					}
					escritura.close();
					}catch(Exception e) {
						e.printStackTrace();
					}	
				}
				}
			//Ata aqui podes borrar
					return mapaPersonaX;
					
				}

	public static void profesores(String[] todos) {

		
	}
}
