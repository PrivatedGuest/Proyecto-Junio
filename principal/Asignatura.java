package principal;

import java.util.Map;
import java.util.TreeMap;

public class Asignatura {
 private String siglas;
 private String cursoAcademico;
 private String nombre;
 private double nota;
 private String curso;
 private String cuatrimestre;
 private String prerequisitos;
 private String duracionA;
 private String duracionB;
 private String coordinador;
 private Map<String,Grupo> grupos= new TreeMap<String,Grupo>();

 public String getsiglas() {
	 return siglas;
 }
 public String getcursoAcademico() {
	 return cursoAcademico;
 }
 public String getnombre() {
	 return nombre;
 }
 public double getnota() {
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
 public Asignatura(String siglasX, String cursoX, double notaX) {
	 this.siglas=siglasX;
	 this.curso=cursoX;
	 this.nota=notaX;
}
 public Asignatura(String siglasX,Map<String, Grupo> gruposX) {
	 this.siglas=siglasX;
	 grupos.putAll(gruposX);
}
}

