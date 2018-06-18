package principal;

import java.util.Map;

import funciones.faux;

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
	
	public String getHorario(Map<String,Asignatura> mapaAsignaturas,Map<String,Persona> mapaPersonas) {
		String devolver="";
		//Hai que facer un for que recorra todos os grupos de todas as asignaturas e ver en que aula se imparten 
		for(Map.Entry<String, Asignatura> h: mapaAsignaturas.entrySet()) {
		Asignatura asig= h.getValue();
			for(Map.Entry<String, Grupo> j: asig.getmapagrupos().entrySet()) {
				Grupo grup=j.getValue();
				if(grup.getaula().equals(this.siglas)) {
					//devolve SglasAsignatura TipoGrupo IdGrupo;siglasProfesor;Cuatrimestre dia horainicio horafinal
					String auxiliar=faux.getsiglasprofesor(asig.getsiglas(), grup.gettipo(), grup.getid(), mapaPersonas);
					if(auxiliar.equals("Non existe o profesor")) auxiliar="No asignado";
					
					devolver=devolver+asig.getsiglas()+" "+grup.gettipo()+" "+grup.getid()+";"+auxiliar+";"+asig.getcuatrimestre()+" "+grup.getdia()+" "+grup.gethoraInicio()+" "+grup.gethorafinal()+"\r\n";
				}
			}
		}
		return devolver;
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
