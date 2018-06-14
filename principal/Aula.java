package principal;

public class Aula {
	private String siglas;
	private String tipo;
	private float capacidade;
	private float nalumnos;
	private boolean lleno;
	
	
	public Aula(String sigla,String tipo,String capacidade) {
		this.siglas=sigla;
		this.tipo=tipo;
		this.capacidade=Float.parseFloat(capacidade);
	}
	
	public void nuevoalumno() {
		this.nalumnos=this.nalumnos+1;
		if(nalumnos>=capacidade) {
			this.lleno=true;
		}
	}
	
	public boolean lleno() {
		return lleno;
	}
	
	public String getsiglas() {
		return siglas;
	}
	public float getnalumnos() {
		return nalumnos;
	}
	public String gettipo() {
		return tipo;
	}
	
	public float capacidade() {
		return capacidade;
	}
	
	
}
