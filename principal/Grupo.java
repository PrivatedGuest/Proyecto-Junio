package principal;

public class Grupo {
	private String tipo;
	private String dia;
	private String aula;
	private double horaInicio;
	private String id;

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
public Grupo(String tipoX, String idX) {
	this.tipo=tipoX;
	this.id=idX;
}
public Grupo(String tipoX) {
	this.tipo=tipoX;
}

}
