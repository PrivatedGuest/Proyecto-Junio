package principal;

public class Grupo {
	private String tipo;
	private String dia;
	private String aula;
	private float horaInicio;
	private float horaFinal;
	private String id;

public Grupo(String tipo,String ID, String dia, float HoraInicio, String Aula) {
		this.tipo=tipo;
		this.id=ID;
		this.dia=dia;
		this.horaInicio=HoraInicio;
		this.aula=Aula;
	}	
public void setHorafinal(String duracion) {
	float aux=Float.parseFloat(duracion);
	this.horaFinal=this.horaFinal+aux;
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
public float gethoraInicio(){
	return horaInicio;
}

public float gethorafinal(){
	return this.horaFinal;
}

}


