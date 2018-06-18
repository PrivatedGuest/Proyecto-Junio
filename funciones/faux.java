package funciones;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;
import java.util.Scanner;

import principal.Alumno;
import principal.Asignatura;
import principal.Aula;
import principal.Grupo;
import principal.Persona;
import principal.Profesor;

public class faux {
	
	
	
	public static boolean existepersona(String linea, Map<String,Persona> mapaPersonas) {
		for(Map.Entry<String, Persona> p: mapaPersonas.entrySet()) {
			if(p.getValue().getdni().equals(linea)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean existegrupo(String tipoMASid,Map<String,Grupo> mapaGrupos) {
		for(Map.Entry<String, Grupo> g: mapaGrupos.entrySet()) {
			if(g.getKey().equals(tipoMASid)) {
				return true;
			}
		}
		return false;	
	}
	
	
	public static String[][] getformatotabla(String horario) {
		//siglasasignatura grupo identificaor;SiglasProfesor;Cuatrimestre Dia Horaini Horafin
		String[] linea=horario.split("\n");
		String[][] devolver = new String[20][6];
		for(int i=0;i<=19;i++) {
			//vamos imprimir os bordes do horario
			for(int k=0;k<=5;k++) {
			devolver[i][k]="vacio";				
			}}
			devolver[0][0]="hora";
			devolver[0][1]="L";
			devolver[0][2]="M";
			devolver[0][3]="X";
			devolver[0][4]="J";
			devolver[0][5]="V";
			for(int i=2;i<=10;i=i+2) {
				devolver[i][0]=" ";
			}
			devolver[1][0]="9-10";
			devolver[3][0]="10-11";
			devolver[5][0]="11-12";
			devolver[7][0]="12-13";
			devolver[9][0]="13-14";
			devolver[11][0]="14-15";	
			for(int i=1;i<=5;i++) {
				devolver[11][i]="XXXXXX";
			}
			for(int i=13;i<=19;i=i+2) {
				devolver[i][0]=" ";
			}
			devolver[12][0]="15-16";
			devolver[14][0]="16-17";
			devolver[16][0]="17-18";
			devolver[18][0]="18-19";
			//Ata aqui imprime os bordes
			for(int i=0;i<=19;i++) {
				for(int k=0;k<=5;k++) {
					devolver[i][k]=devolver[i][k].trim();
					if(devolver[i][k].equals("vacio")) {
						devolver[i][k]=devolver[i][k].replace("vacio", " ");
					}
				}
			}
			//Tiñamos o formato ahora escribimos asignaturas
		for(int i=0;i<linea.length;i++) {
			String[] datos=linea[i].split(";");
			datos[0]=faux.quitarespacios(datos[0].trim());
			datos[1]=faux.quitarespacios(datos[1].trim());
			datos[2]=faux.quitarespacios(datos[2].trim());
			String[] aux1=datos[0].trim().split(" ");
			String[] aux2=datos[2].split(" ");
			String siglasASIG=aux1[0].trim();
			String tipoGRUPO=aux1[1].trim();
			String identificadorGRUPO=aux1[2].trim();
			String siglasPROF=datos[1];	
			String cuatrimestre=aux2[0];
			String dia=aux2[1];
			String horainicio=aux2[2];
			String horafinal=aux2[3];
			float duracion=Float.parseFloat(horafinal)-Float.parseFloat(horainicio);
			int columna=0;
			int fila=0;
			switch (dia) {
				case "L":
					columna=1;
					break;
				case "M":
					columna=2;
					break;
				case "X":
					columna=3;
					break;
				case "J":
					columna=4;
					break;
				case "V":
					columna=5;
					break;
				default :
					System.out.println("Dia incorrecto");
			}
			switch(horainicio) {
			case "9.0":
				fila=1;
				break;
			case "10.0":
				fila=3;
				break;
			case "11.0":
				fila=5;
				break;
			case "12.0":
				fila=7;
				break;
			case "13.0":
				fila=9;
				break;
			case "15.0":
				fila=12;
				break;
			case "16.0":
				fila=14;
				break;
			case "17.0":
				fila=16;
				break;
			case "18.0":
				fila=18;
				break;
			}
			//Ahora xa timos a fila e a columna onde queremos meter todo
			//cuidado con mais de unespacio en blanco
		devolver[fila][columna]=siglasASIG+"-"+tipoGRUPO+identificadorGRUPO;
		devolver[fila+1][columna]=datos[1].trim();
		if(duracion==2) {
			devolver[fila+2][columna]=siglasASIG+"-"+tipoGRUPO+identificadorGRUPO;
			devolver[fila+3][columna]=datos[1].trim();
		}
			
		}//Fin de para todas as asignaturas
			
			
			
			
		return devolver;
	}
	
	public static boolean existeasignatura(String linea,Map<String,Asignatura> mapaAsignaturas) {
		linea=linea.trim();
		for(Map.Entry<String,Asignatura> a: mapaAsignaturas.entrySet()) {
			if(a.getValue().getsiglas().equals(linea)) {
				return true;
			}
		}
		return false;
	}
	public static boolean existeaula(String siglas, Map<String,Aula>mapaAulas) {
		siglas=siglas.trim();
		for(Map.Entry<String, Aula> au: mapaAulas.entrySet()) {
			if(au.getKey().equals(siglas)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean existealumno(String linea, Map<String,Persona> mapaPersonas) {
		for(Map.Entry<String, Persona> p: mapaPersonas.entrySet()) {
			if(p.getValue().getdni().equals(linea) && p.getValue() instanceof Alumno) {				
				return true;
			}
		}
		return false;
	}
	
	public static String getsiglasprofesor(String SiglasASIG,String tipoGrupo,String idGRUPO,Map<String,Persona> mapaPersonas) {
		String devolver="Non existe o profesor";
		for(Map.Entry<String, Persona> p:mapaPersonas.entrySet()) {
			if(p.getValue() instanceof Profesor) {
				Profesor prof=(Profesor) p.getValue();
				for(Map.Entry<String, Asignatura> a:prof.getasignaturas().entrySet()) {
					if(a.getKey().contains(SiglasASIG)&&a.getKey().contains(tipoGrupo)&&a.getKey().contains(idGRUPO)) {
						devolver=faux.getiniciales(prof.getnombre());
					}
				}//Para todas as asignaturas(encontrar o profesor que da nese grupo)
			}
		}//Para todos os profesores
		return devolver;
		}
	
	public static String getiniciales(String nombre){
		String[] aux1=nombre.split(",");
		String SiglaNombre=String.valueOf(aux1[1].trim().charAt(0));
		String[] aux2=aux1[0].split(" ");
		String SiglaApellido1=String.valueOf(aux2[0].trim().charAt(0));
		String SiglaApellido2=String.valueOf(aux2[1].trim().charAt(0));
		return SiglaNombre+SiglaApellido1+SiglaApellido2;
	}
		
	
	
	public static boolean grupoyaasignado(Map<String,Persona> mapaPersonas,String siglas,String tipo,String id) {
		//No mapa dos grupos de cada porfesor usamos como clave "Siglas"+"+"+"tipo"+"+"+"id"
		//Enton se a clave conten as siglas, o tipo, e o ID, e que xa hai outro profesor que a imparte
		for(Map.Entry<String, Persona> p:mapaPersonas.entrySet()) {
			Profesor profi;
			if(p.getValue() instanceof Profesor) {
				profi=(Profesor) p.getValue();//ahora temos un profesor da lista(e asi con todos)
				for(Map.Entry<String, Asignatura> asig:profi.getasignaturas().entrySet()) {
					int aux= asig.getKey().indexOf("+");
					String auxiliar=asig.getKey().substring(0, aux);
					if(asig.getKey().equals(auxiliar)&&asig.getKey().contains(tipo)&&asig.getKey().contains(id)) {
						return true;
					}
				}//fin do bucle para todos os grupos do profesor
				
			}
		}//fin do bucle para todos os profesores
		
		return false;
	}
	
	public static Asignatura getasignatura(String siglas, Map<String,Asignatura> mapaAsignaturas) {
		Asignatura seguroqueexiste = null;
		for(Map.Entry<String, Asignatura> a: mapaAsignaturas.entrySet()) {
			if(a.getValue().getsiglas().equals(siglas.trim())) {
				seguroqueexiste=a.getValue();
			}
		}
		return seguroqueexiste;
	}
	
	public static Alumno getalumno(String dni,Map<String, Persona> mapaPersonas ) {
		Alumno seguroqueexiste=null;
		for(Map.Entry<String, Persona> p: mapaPersonas.entrySet()) {
			if(p.getValue() instanceof Alumno) {
			if(p.getKey().equals(dni.trim())) {
				seguroqueexiste=(Alumno) p.getValue();
			}}
	}
		return seguroqueexiste;
	}
	
	public static Profesor getprofesor(String dni,Map<String, Persona> mapaPersonas ) {
		Profesor seguroqueexiste=null;
		for(Map.Entry<String, Persona> p: mapaPersonas.entrySet()) {
			if(p.getValue() instanceof Profesor) {
			if(p.getKey().equals(dni.trim())) {
				seguroqueexiste=(Profesor) p.getValue();
			}}
	}
		return seguroqueexiste;
	}
	
	public static Persona getprofesorporNombre(String Nombre,Map<String, Persona> mapaPersonas) {
		Profesor seguroqueexiste=null;
		for(Map.Entry<String, Persona> p: mapaPersonas.entrySet()) {
			if(p.getValue() instanceof Profesor) {
			if(p.getValue().getnombre().equals(Nombre.trim())) {
				seguroqueexiste=(Profesor) p.getValue();
			}}
		
	}
		return seguroqueexiste;
}
	
	public static Aula getaula(String siglasAULA,Map<String, Aula> mapaAula) {
		Aula seguroqueexiste=null;
		for(Map.Entry<String, Aula> au: mapaAula.entrySet()) {
			if(au.getKey().equals(siglasAULA)) {
				seguroqueexiste=au.getValue();
			}
		}
		return seguroqueexiste;
	}
	
	public static String getsiglasaula(String siglasASIGNATURA,String tipo,String ID,Map<String, Asignatura> mapaAsignaturas){
		String devolver="Non se atopo nada en getsiglasaula";
		for(Map.Entry<String, Asignatura> a:mapaAsignaturas.entrySet()) {
			if(a.getValue().getsiglas().equals(siglasASIGNATURA)) {
				Asignatura asig=a.getValue();//A asignatura correspondente
				//Facemos un bucle para os grupos desa asignatura,cando coincidan collemos a aula
				for(Map.Entry<String, Grupo>g:asig.getmapagrupos().entrySet()) {
					Grupo grup= g.getValue();
					if(grup.gettipo().equals(tipo)&&grup.getid().equals(ID)) {
						devolver=grup.getaula();
						return devolver;
					}
				}
			}
		}
		return devolver;
	}
	
	
	
	public static String quitarespacios(String linea) {
		while(linea.contains("  ")) {
			linea=linea.replace("  ", " ");
		}
		return linea;
	}
	//Matricula 45004720X SEG
	
	public static String minusculas(String linea){
		linea=linea.toLowerCase();
		return linea;
	}
	
	
	 public static boolean yaesalumno(Alumno alumno,String asignatura) {
		 //Esta funcion e a de abaixo poden parecer iguales,esta devolveche true se o alumno
		 //superou ou esta cursando a asignatura(linea 5 ou 6) a de abaixo solo se a esta cursando(linea 6)
		 for(Map.Entry<String, Asignatura> a: alumno.getasignaturas().entrySet() ) {
			 if(a.getKey().contains(asignatura)) {
				 return true;
			 }
		 }
		 return false;		 
	 }
	
	 public static boolean alumnomatriculado(Alumno alu,String siglas) {
		 for(Map.Entry<String, Asignatura> asig: alu.getasignaturas().entrySet()) {
			 if(asig.getKey().contains(siglas)&&asig.getKey().contains("+")) {
				 return true;
			 }
		 }
		 return false;
	 }
	
	 
	 public static boolean profesordaclase(Profesor prof,String siglas) {
		 for(Map.Entry<String, Asignatura> asig: prof.getasignaturas().entrySet()) {
			 if(asig.getKey().contains(siglas)) {
				 return true;
			 }
		 }
		 return false;
	 }
	 
	 public static String gethorariogrupo(Asignatura asignatura,String tipo,String id){
		 String devolver="";
		 for(Map.Entry<String, Grupo> g: asignatura.getmapagrupos().entrySet()) {
			 if(g.getKey().contains(tipo)&&g.getKey().contains(id)) {
				 devolver=g.getValue().getdia()+" "+g.getValue().gethoraInicio()+" "+g.getValue().gethorafinal();
			 }
		 }
		 return devolver;
	 }
	 
	 public static String getcuatrimestre(String siglas,Map<String,Asignatura> mapaAsignaturas) {
		 for(Map.Entry<String, Asignatura> asig: mapaAsignaturas.entrySet()) {
			 if(asig.getValue().getsiglas().equals(siglas)) {
				 return asig.getValue().getcuatrimestre();
			 }
		 }
		 return "MAL EN GETCUATRIMESTRE";
	 }
	 
	 public static String getcurso(String siglas,Map<String,Asignatura> mapaAsignaturas) {
		 for(Map.Entry<String, Asignatura> asig: mapaAsignaturas.entrySet()) {
			 if(asig.getValue().getsiglas().equals(siglas)) {
				 return asig.getValue().getcurso();
			 }
		 }
		 return "MAL EN GETCURSO";
	 }
	 
	 public static boolean existesolapealumno(Alumno alu,String curso,String cuatrimestre,String dia,String horainici,String horafina,Map<String,Asignatura> mapaAsignaturas) {
		 float hini=Float.parseFloat(horainici);
		 float hfin=Float.parseFloat(horafina);
		 //String horario
		 for(Map.Entry<String, Asignatura> a: mapaAsignaturas.entrySet()) {
			 Asignatura asig=a.getValue();
/*A clave de asigalu sera Sigla+"+"+"A/B/M", o bucle fai que se repita esto para quec oincida nalgunha
asignatura e non se solape.A clave do mapa global solo sera as siglas, asique se coinciden as siglas deberan
estar dentro de diglas+"+"...A maiores temos que asegurarnos qu esten no miso cuatrimestre
 */
				 if(faux.alumnomatriculado(alu, a.getKey())&&a.getValue().getcuatrimestre().equals(cuatrimestre)&&a.getValue().getcurso().equals(curso)){
					 //Se esta matriculado nesa asignaturas e coindice o cuatrimestre
			 		for(Map.Entry<String, Grupo> g: asig.getmapagrupos().entrySet()) {
			 			//iramos todos os grupos de esa asignatura
			 				Grupo grup=g.getValue();
			 				float hiniX=grup.gethoraInicio();
			 				float hfinX=grup.gethorafinal();
			 				if(grup.getdia().trim().equals(dia.trim())){
			 					if(hiniX==hini||(hiniX>hini&&hiniX<hfin)) {
			 						return true;
			 					}
			 				}//Fin do if do dia(para solape)	 
			 		}//Fin do for para todos os grupos			 
		 }//Fin da condicional de coincidir cuatrimestre e estar matriculado
		 }//for para todas as asignaturas
		 return false;
	 }
	public static boolean existesolapealumno2(String horario,String curso,String cuatrimestre,String dia,String  horainicio,String  horafinal) {
		String[] linea=horario.split("\n");
		float hini=Float.parseFloat(horainicio);
		float hfin=Float.parseFloat(horafinal);
		for(int i=0; i<linea.length;i++) {
			String[] datos=linea[i].split(" ");
			String cursoX=datos[0].trim();
			String cuatriX=datos[1].trim();
			String diaX=datos[2].trim();
			float iniX=Float.parseFloat(datos[3].trim());
			float finX=Float.parseFloat(datos[4].trim());
			if(cursoX.equals(curso)&&cuatriX.equals(cuatrimestre)&&diaX.equals(dia)){
				//mismo curso cuatri e dia
				if(iniX==hini||(iniX>hini&&iniX<hfin)) {
					return true;//hai solape
				}
			}
		}//para todas as linea para ver se hai solape nalgunha
		return false;
	}
	 
	 public static boolean existesolapeprofesor(String horario,String cuatrimestre,String dia,String horainici,String horafina) {
		 float hini=Float.parseFloat(horainici);
		 float hfin=Float.parseFloat(horafina);
		 String[] linea=horario.split("\n");
		 for(int i=0; i<linea.length;i++) {
				String[] datos=linea[i].split(" ");
				String cuatriX=datos[1].trim();
				String diaX=datos[2].trim();
				float iniX=Float.parseFloat(datos[3].trim());
				float finX=Float.parseFloat(datos[4].trim());
				if(cuatriX.equals(cuatrimestre)&&diaX.equals(dia)){
					//mismo cuatri e dia
					if(iniX==hini) {
						return true;//hai solape
					}
					if((iniX>hini&&iniX<hfin)) {
						return true;
					}
				}
			}//para todas as linea para ver se hai solape nalgunha
			return false;
		}
	 
	 
	 public static boolean prerequisitos(Alumno alumno,String prerequisitos) {
		 //prerequisitos e o que debe cumplir o alumno
		 if(prerequisitos.equals("\r\n")) {
			 return true;
		 }
		 Map<String,Asignatura> asigalumno = null;
		 asigalumno=alumno.getasignaturas();
		 String[] aux=prerequisitos.split(";");
		 for(int i=0;i<aux.length;i++) {//pra todos os prerequisitos
			 int verdad=0;
			 for(Map.Entry<String, Asignatura> a: asigalumno.entrySet()) {
				 if(a.getValue().getsiglas().contains(aux[i])&&!a.getKey().contains("+")) {
					 verdad++;
				 }
			 }//O sair de aqui verdad sera 1 se ten a asignatura aprobada
			 
			 if(verdad==0) {
				 return false;
			 }
		 } 
		 return true;
	 }
	 
	 public static String notasdearquivo(String arquivo) {
			String aux="";	
		 try {
				String bfRead;
				BufferedReader read= new BufferedReader(new FileReader(arquivo));
				while((bfRead=read.readLine())!= null) {//Fai o ciclo mentras o ficheiro teña datos
					aux= aux + bfRead+"\r\n";}
				read.close();}
			catch(Exception e) {
				System.out.println("Fichero inexistente: "+arquivo);
		}
			return aux;
	 }
	 
	 public static String agrupar(String alunotasA,String alunotasB) {
		 alunotasA=alunotasA.trim();
		 alunotasB=alunotasB.trim();
		 alunotasA=alunotasA.replace("\r\n", ";");
		 alunotasB=alunotasB.replace("\r\n", ";");
		 alunotasA=faux.quitarespacios(alunotasA);
		 alunotasB=faux.quitarespacios(alunotasB);
		 String devolver="";
		 String[] auxA=alunotasA.split(";");
		 String[] auxB=alunotasB.split(";");
		 for(int i=0;i<auxA.length;i++) {
			 int ben=0;
			 String alumnoA=auxA[i].substring(0, 9);
			 for(int k=0;k<auxB.length;k++) {
				 String alumnoB=auxB[k].substring(0, 9);
				 String notasB=auxB[k].substring(9, auxB[k].length());
				 notasB=notasB.trim();
				 if(alumnoB.equals(alumnoA)) {
					 ben++;
					 devolver=devolver+auxA[i].substring(0, auxA[i].length())+" "+notasB+"\r\n";
				 }	 
			 }
			 if(ben==0) {
				 devolver=devolver+auxA[i].substring(0, auxA[i].length()-2)+" "+ben+"\r\n";
			 } 
		 }
		 return devolver;
	 }
}
