package principal;

import java.util.Map;
import java.util.TreeMap;

public class Asignatura {
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
 //IdentificadorM usarase para as asignaturas que nn teñan ningun grupo asignado, non podemos usar SOLO a asignaturas
 //de clave porque daría problemas coas asignaturas xa superadas, asique usamos 'Sigla+M' e sabemos que se refire a que non ten grupo
 
 
 public void imp1() {
	 System.out.println(siglas+" "+curso+" "+nota);
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
 public String getcuatrimestre() {
	 return cuatrimestre;
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
 
 
 public Asignatura(String siglasX, String cursoX, double notaX) {
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
}

