package principal;

import java.io.BufferedReader;
import java.io.FileReader;

public class prueba {

		public static int leeralumnos(String direccion){
			try {
				String[] DatosPropiedad;
				String bfRead;
				BufferedReader read= new BufferedReader(new FileReader(direccion));
				while((bfRead=read.readLine()) != null) {//Fai o ciclo mentras o ficheiro teña datos
					DatosPropiedad=bfRead.split("\\*");
					String[] Auxiliar=DatosPropiedad[4].split("/");
					String ClaveMapaDNI =DatosPropiedad[2]+Auxiliar[2]+Auxiliar[1]+Auxiliar[0];
					}}
			
			catch(Exception e) {
				System.out.println("Fichero inexistente: "+direccion);		
		}
			return 1;
	}
}
