package funciones;

import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import principal.Alumno;
import principal.Asignatura;
import principal.Grupo;

public class ordenar {
	
	public static Map<String, Alumno> alumnos(String[] todos) {
		Map<String,Alumno> mapaAlumnoX= new TreeMap<String,Alumno>();
		Map<String,Grupo> mapaAux= new TreeMap<String,Grupo>();
		Map<String,Asignatura> docenciaRecibidaX= new TreeMap<String,Asignatura>();
		Map<String,Asignatura> asignaturasSuperadasX= new TreeMap<String,Asignatura>();
		int contadorAlumnos;
		GregorianCalendar fechaNacX = (GregorianCalendar) GregorianCalendar.getInstance() ;
		GregorianCalendar fechaIngX = (GregorianCalendar) GregorianCalendar.getInstance() ;
		for(contadorAlumnos=0;contadorAlumnos<todos.length;contadorAlumnos++) {//bucle repitese para todos os alumnos
		String[] alumnoi;
		int contador=0;
		String dniX="",nombreX="",emailX = "";
		int contadorcorreo=0;
		alumnoi=todos[contadorAlumnos].split("[\n]");//Dividimos os campos do alumnoi
				int lineaj=0;
				int w=1;
			if(contadorAlumnos>0) {
				for(w=1;w<alumnoi.length;w++)
				alumnoi[w-1]=alumnoi[w];//Indice W pasa a ser W+1
			}
				for(lineaj=0;lineaj<alumnoi.length;lineaj++) {//En cada alumno, asegurase de que haxa contido nunha linea 
					alumnoi[lineaj]=alumnoi[lineaj].trim();//dendendo da linea na que estemos, farase unha cousa ou outra(switch);
					if(alumnoi[lineaj].length()!=0) {//os casos indican en que linea do ficheiro estamos
							//System.out.println(lineaj);
							//System.out.println(alumnoi[lineaj]);
						switch (lineaj+contador) {//o contador é un axuste para eliminar as linea en blanco
						
						case 0:	
							//System.out.println("Esto escribese0");
							dniX=alumnoi[lineaj];
							break;
						case 1:
							//System.out.println("Esto escribese1");
							nombreX=alumnoi[lineaj];
							break;
						case 2:
							//System.out.println(alumnoi[lineaj]);Realmente solo colle os 3 que hai sen repetir...
							emailX=alumnoi[lineaj];
							break;
						case 3:
							//System.out.println("Esto escribese3");
							//System.out.println("1"+alumnoi[lineaj]);
							fechaNacX=cambios.String_GregorianCalendar(alumnoi[lineaj]);
							break;
						case 4:
							//System.out.println("Esto escribese4");
							//System.out.println("2"+alumnoi[lineaj]);
							fechaIngX=cambios.String_GregorianCalendar(alumnoi[lineaj]);
							break;
						case 5:
							String[] auxiliar1=alumnoi[lineaj].split(";");//dentro da 5 linea, separamos as asignaturas para facer un mapa
							String[] auxiliar2;
							int k;
							for(k=0;k<auxiliar1.length;k++) {
								auxiliar1[k]=auxiliar1[k].trim();
							}
							String siglaX;String cursoX;double notaX;
							for(k=0;k<auxiliar1.length;k++) {
								auxiliar2=auxiliar1[k].split(" ");//separamos as palabras
								siglaX=auxiliar2[0].trim();
								cursoX=auxiliar2[1].trim();
								notaX=Double.parseDouble(auxiliar2[2].trim());
								asignaturasSuperadasX.put(siglaX,new Asignatura(siglaX,cursoX,notaX));
							}
							break;
						
						case 6:
							k=0;
							String tipoGrupoX = "";
							String idX;
							auxiliar1=alumnoi[lineaj].split(";");//dentro da 6 linea, separamos a docencia recibida para facer un mapa
							String[][] auxiliar3={{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},};
						for(k=0;k<auxiliar1.length;k++) {//copiamos na matriz bidimensional
							auxiliar1[k]=auxiliar1[k].trim();
							auxiliar3[k]=auxiliar1[k].split(" ");
							}
						k=-1;//para que quede a 0 ó entrar no bucle
						int j=0;
						for(j=0;j<auxiliar1.length;j++) {
						do {
							k++;
							if(auxiliar3[k].length==2){
							tipoGrupoX=auxiliar3[k][1];
							mapaAux.put(Integer.toString(k), new Grupo(tipoGrupoX) );
							}
							if(auxiliar3[k].length>2) {
								idX=auxiliar3[k][2];
								mapaAux.put(Integer.toString(k), new Grupo(tipoGrupoX,idX) );
							}
						}while(auxiliar3[k][0]==auxiliar3[k+1][0]);
						docenciaRecibidaX.put(auxiliar3[k][0],new Asignatura(auxiliar3[k][0],mapaAux));			
						}
								break;		
			}//Cerramos o switch
			}	
						
					else if(contadorcorreo<2) {
						int x=0;
						for(x=0;x<alumnoi.length;x++) {
							if(alumnoi[x].contains("@"))contadorcorreo++;
							if(contadorcorreo==0)contador ++;
						}}
					else	contador +=1;
				}//Pecha o for	das lineas
				mapaAlumnoX.put(dniX, new Alumno(dniX,nombreX,emailX,fechaNacX,fechaIngX,asignaturasSuperadasX,docenciaRecibidaX));
	}	//pecha o for que repite todo o de arriba para todos os alumnos
	return mapaAlumnoX;	
	
	}

	public static void profesores(String[] todos) {

		
	}
}
