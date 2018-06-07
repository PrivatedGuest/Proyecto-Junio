package principal;

public class Grupo {
	private String tipo;
	private String dia;
	private String aula;
	private double horaInicio;
	private String id;

	public Grupo(String ID, String dia, double HoraInicio, String Aula) {
		this.id=ID;
		this.dia=dia;
		this.horaInicio=HoraInicio;
		this.aula=Aula;
	}
	
	
public String imprimirGrupo() {
	String aux=id+" "+dia+"  "+horaInicio+"  "+aula;
	return aux;
}
	
public String gettipo() {
	return tipo;
}
public String getdia() {
	return dia;
}
public String getaula() {
	return aula;
}
public String getid() {
	return id;
}
public double gethoraInicio(){
	return horaInicio;
}
}


