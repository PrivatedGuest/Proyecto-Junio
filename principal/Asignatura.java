package principal;

import java.util.Map;
import java.util.TreeMap;

public class Asignatura {
 private int numeroGruposA=0;
 private int numeroGruposB=0;
 private String siglas;
 private String nombre;
 private String nota;
 private String curso;
 private String cuatrimestre;
 private String prerequisitos;
 private String duracionA;
 private String duracionB;
 private String coordinador;
 private String grupo;
 private String identificadorA;
 private String identificadorB;
 private String identificadorM;
 private Map<String,Grupo> mapagrupos=new TreeMap<String,Grupo>();//A key sera "A"+"+"+idX ou "B"+"+"+idX
 //IdentificadorM usarase para as asignaturas que nn teñan ningun grupo asignado, non podemos usar SOLO a asignaturas
 //de clave porque daría problemas coas asignaturas xa superadas, asique usamos 'Sigla+M' e sabemos que se refire a que non ten grupo
 
 public Asignatura(String siglasX,String nombreX, String cursoX, String CuatrimestreX, String CoordinadorX, String PrerequisitosX, String duracionAX, String duracionBX,Map<String,Grupo> gruposX) {
	 this.siglas=siglasX;
	 this.nombre=nombreX;
	 this.curso=cursoX;
	 this.cuatrimestre=CuatrimestreX;
	 this.coordinador=CoordinadorX;
	 this.prerequisitos=PrerequisitosX;
	 this.duracionA=duracionAX;
	 this.duracionB=duracionBX;
	 this.mapagrupos.putAll(gruposX);
	 int aux=0;
	 for(Map.Entry<String, Grupo> p: mapagrupos.entrySet()) {
		 if(p.getKey().contains("A")) {
			 aux++;
		 }
 }//salimos do for
	 this.numeroGruposA=aux;
	 this.numeroGruposB=mapagrupos.size()-numeroGruposA;
 }
 
 
 public Asignatura(String siglasX,String nombreX, String cursoX, String CuatrimestreX, String CoordinadorX, String duracionAX, String duracionBX,Map<String,Grupo> gruposX) {
	 this.siglas=siglasX;
	 this.nombre=nombreX;
	 this.curso=cursoX;
	 this.cuatrimestre=CuatrimestreX;
	 this.coordinador=CoordinadorX;
	 this.prerequisitos="";
	 this.duracionA=duracionAX;
	 this.duracionB=duracionBX;
	 this.mapagrupos.putAll(gruposX);
	 int aux=0;
	 for(Map.Entry<String, Grupo> p: mapagrupos.entrySet()) {
		 if(p.getKey().contains("A")) {
			 aux++;
		 }
 }//salimos do for
	 this.numeroGruposA=aux;
	 this.numeroGruposB=mapagrupos.size()-numeroGruposA;
 }
 
 
 public Asignatura(String siglasX, String cursoX, float notaX) {
	 this.siglas=siglasX;
	 this.curso=cursoX;
	 this.nota=String.valueOf(notaX);
}
 public Asignatura(String siglasX,String grupoX,String identificadorX) {
	 this.siglas=siglasX;
	 this.grupo=grupoX;
	 if(grupoX.equals("A")){
		 this.identificadorA=identificadorX;
	 }
	 else  if(grupoX.equals("B")){
		 this.identificadorB=identificadorX;
	 }
	 else System.out.println("Fallo no constructor de asignatura, non se reconoce o tipo do grupo    "+grupoX);
 }
 
 public void setHorafinalGrupos() {
	 if(mapagrupos!=null) {
	 for(Map.Entry<String, Grupo> g: this.mapagrupos.entrySet()) {
		 if(g.getValue().gettipo().equals("A")) {
			 g.getValue().setHorafinal(this.duracionA);
		 }
		 else if(g.getValue().gettipo().equals("B")) {
			 g.getValue().setHorafinal(this.duracionB);
		 }
		 else {
			 System.out.println("FALLO EN SETHORAFINALGRUPOS");
		 }
	 }
 }}
 
 public String imprimirasignatura() {
	 String aux;
	 aux=siglas+"\r\n"+nombre+"\r\n"+curso+"\r\n"+cuatrimestre+"\r\n"+coordinador+"\r\n"+prerequisitos+"\r\n"+duracionA+"\r\n"+duracionB+"\r\n";
	 return aux;
 }
 public String imprimirGruposAsignatura() {
	 String aux = "";
	 int contadorgrupos=1;
	 	for(Map.Entry<String, Grupo> p: mapagrupos.entrySet()) {//este for e para os grupos A
	 		if(p.getKey().contains("A")) {
	 			aux=aux+p.getValue().imprimirGrupo();
	 				if(contadorgrupos<this.numeroGruposA) {//se hai mas grupos para imprimir, poñemos un ;
	 					aux=aux+";";
	 					contadorgrupos++;
	 				}
	 				else aux=aux+"\r\n";
	 		}	
	 	}
	 	contadorgrupos=1;
	 	for(Map.Entry<String, Grupo> p: mapagrupos.entrySet()) {
	 		if(p.getKey().contains("B")) {
	 			aux=aux+p.getValue().imprimirGrupo();
	 				if(contadorgrupos<this.numeroGruposB) {
	 					aux=aux+";";
	 		 			contadorgrupos++;
	 				}
	 				else aux=aux+"\r\n";
	 		}	
	 	}
	 		
	 return aux;
 }
 
 public String getsiglas() {
	 return siglas;
 }

 public String getgrupo() {
	 return grupo;
 }
 public String getnombre() {
	 return nombre;
 }
 public String getnota() {
	 return nota;
 }
 public String getcurso() {
	 return curso;
 }
 public Map<String, Grupo> getmapagrupos(){
	 return this.mapagrupos;
 }
 public String getcuatrimestre() {
	 return cuatrimestre;
 }
 public void setcuatrimestre(String linea) {
	 this.cuatrimestre=linea;
 }
 public String getprerequisitos() {
	 return prerequisitos;
 }
 public String getduracionA() {
	 return duracionA;
 }
 public String getduracionB() {
	 return duracionB;
 }
 public String getcoordinador() {
	 return coordinador;
 }
 public String getidentificadorA(){
	 return identificadorA;
 }
 public String getidentificadorB(){
	 return identificadorB;
 }
 public String getidentificadorM() {
	 return identificadorM;
 }
 
 public Asignatura(String siglasX) {//Este constructor e para cando chegue unha asignatura sen grupo
	 this.siglas=siglasX;
	 }
 
}

