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
	
	public static boolean existegrupo(String linea,Map<String,Grupo> mapaGrupos) {
		for(Map.Entry<String, Grupo> g: mapaGrupos.entrySet()) {
			if(g.getValue().getid().equals(linea)) {
				return true;
			}
		}
		return false;	
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
	
	
	public static boolean grupoyaasignado(Map<String,Persona> mapaPersonas,String siglas,String tipo,String id) {
		//No mapa dos grupos de cada porfesor usamos como clave "Siglas"+"+"+"tipo"+"+"+"id"
		//Enton se a clave conten as siglas, o tipo, e o ID, e que xa hai outro profesor que a imparte
		for(Map.Entry<String, Persona> p:mapaPersonas.entrySet()) {
			Profesor profi;
			if(p.getValue() instanceof Profesor) {
				profi=(Profesor) p.getValue();//ahora temos un profesor da lista(e asi con todos)
				for(Map.Entry<String, Asignatura> asig:profi.getasignaturas().entrySet()) {
					if(asig.getKey().contains(siglas)&&asig.getKey().contains(tipo)&&asig.getKey().contains(id)) {
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
					if(iniX==hini||(iniX>hini&&iniX<hfin)) {
						return true;//hai solape
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
