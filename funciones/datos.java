package funciones;

public class datos {
	public static final String direccionalu="alumnos.txt";
	public static final String direccionprof="profesores.txt";
	public static final String direccionasig="asignaturas.txt";
	public static final String direccioncurso="curso academico.txt";
	public static final String direccionaula="aulas.txt";
	public static final String direccionpod="podordenado.txt";
	public static final String direccionejec="ejecucion.txt";
	private static String curso="";
	private static String semanainicio="";
	public static void iniciocurso(String linea) {
		String[] aux= linea.split(" ");
		curso= aux[0].trim();
		semanainicio=aux[1].trim();
	}
	
	
	public static void setcurso(String linea){
	curso=linea;
	}
	public static String getcurso() {
		return curso;
	}
	
	public static String semanainicio() {
		return semanainicio;
	}
}
