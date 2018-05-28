package funciones;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Comprobar {
	
	public static boolean dni(String dni){
		if(dni.length()!=9)return false;
			for(int i=0;i<8;i++) {
			if(!Character.isDigit(dni.charAt(i)))return false;
		}
	if(!Character.isLetter(dni.charAt(8))||Character.isLowerCase(dni.charAt(8)))return false;
	return true;
	}
	
	public static boolean validarFecha(String fechaX) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            formatoFecha.setLenient(false);
            formatoFecha.parse(fechaX);
        } catch (ParseException e) {
            return false;
        }
       Date cotainf = new Date(1960,01,01);
       Date cotasup =new Date(2018,01,01);
    Date fecha=cambios.String_Date(fechaX);
    int aux=fecha.compareTo(cotasup);//Fecha e anterior o argumento, devolve un negativo
    int aux2=fecha.compareTo(cotainf);//Fecha e posterior o argumento,devolve un positivo
    System.out.println("AUX    "+fecha.compareTo(cotasup));
    System.out.println("AUX2   "+aux2);
    	if(aux<0&&aux2>0) {
    		System.out.println("OKEY ASI VALE");
    		return true;
    	}
    	return false;
    }
	
	
}
