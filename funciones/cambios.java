package funciones;

import java.sql.Date;
import java.util.GregorianCalendar;

public class cambios {
	public static GregorianCalendar String_GregorianCalendar(String cadena) {

        String[]  fechaArray=cadena.split("/");
        int k=0;
        int dia=1,mes=1,anio=1;
        for(k=0;k<fechaArray.length;k++) {
        	fechaArray[k]=fechaArray[k].trim();
        }
        if(fechaArray.length==3) {
        dia = Integer.valueOf(fechaArray[0]);
        mes = Integer.valueOf(fechaArray[1])-1;
        anio = Integer.valueOf(fechaArray[2]);
        }
        else {
        	System.out.println("NON ESTA EN FORMATO\" DIA/MES/ANO\"");
        }
		GregorianCalendar c1=new GregorianCalendar(anio,mes,dia); 
		return c1;
	}
	
	public static String GregorianCalendar_String(GregorianCalendar fecha) {
		int diaX=fecha.getTime().getDay();
		String dia,mes;
			if(diaX<10) {
				dia="0"+String.valueOf(diaX);
			}
			else {
				dia=String.valueOf(diaX);
			}
		int mesX=fecha.getTime().getMonth()+1;
			if(mesX<10) {
				mes="0"+String.valueOf(mesX);
			}
			else {
				mes=String.valueOf(mesX);
			}
		int anoX=fecha.getTime().getYear();
			if(anoX<10) {
				anoX=anoX+2000;
			}
			else {
				anoX=anoX+1900;
			}
		String ano=String.valueOf(anoX);
		String respuesta=dia+ "\\"+ mes + "\\"+ano;
		return respuesta;
	}
	

	public static Date String_Date(String cadena) {

        String[]  fechaArray=cadena.split("/");
        int k=0;
        int dia=1,mes=1,anio=1;
        for(k=0;k<fechaArray.length;k++) {
        	fechaArray[k]=fechaArray[k].trim();
        }
        if(fechaArray.length==3) {
        dia = Integer.valueOf(fechaArray[0]);
        mes = Integer.valueOf(fechaArray[1])-1;
        anio = Integer.valueOf(fechaArray[2]);
        }
        else {
        	System.out.println("NON ESTA EN FORMATO\" DIA/MES/ANO\"");
        }
		Date c1=new Date(anio,mes,dia); 
		return c1;	
}	
}
