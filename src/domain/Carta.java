package domain;

import java.io.IOException;
import java.util.*;


public class Carta {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> miComanda = new ArrayList<String>();
		String plats[] = new String[10];
		int preus[] = new int[10];
		HashMap<String, Integer> preuPlat = new HashMap<String, Integer>();
		
		int cinco =5;
		int diez=10;
		int vinte=20;
		int cincuenta=50;
		int cien=100;
		int doscientos=200;
		int quinientos=500;
		double preu=0.0;
		int totalMenu=0;
		int contador=0;
		
		
		preuPlat.put("Bufalina", 12);
		preuPlat.put("4 Formaggi", 12);
		preuPlat.put("Pino Daniele", 18);
		preuPlat.put("Margherita", 9);
		preuPlat.put("Prosciutto", 10);
		preuPlat.put("Parmiggiana", 11);
		preuPlat.put("Al Tonno", 13);
		preuPlat.put("Carbonara", 11);
		preuPlat.put("Massimo Troise", 17);
		preuPlat.put("Ortolana", 12);
		
		Iterator<String> it= preuPlat.keySet().iterator();
		
		
		while (it.hasNext()) {
			plats[contador]= it.next();
			preus[contador] = preuPlat.get(plats[contador]);
			contador++;
			
		}
		
		imprimirMenu(plats, preus);
		try {
			llistaComanda(miComanda);
		} catch (IOException e) {
			System.out.println("Error E/S");
				
		}catch (InputMismatchException e1) {
			System.out.println(e1.getMessage()); 
		}
		
		
		for (String e:miComanda) {
			System.out.println(e);
		}
		
		//Fase 3 del exercici
		try {
			revisarMenu(miComanda, plats, preuPlat, totalMenu);
		}catch(NullPointerException ex) {
			System.out.println(ex.getMessage());
		}
		
		
		
		
}
	
	public static void imprimirMenu(String auxPlats[], int auxPreus[]) {
		for (int i=0; i< auxPlats.length; i++) {
			System.out.println(auxPlats[i] + " " +auxPreus[i]);
		}
	}
	
	public static void llistaComanda(List<String> auxComanda) throws  IOException {
		
		Scanner sc= new Scanner(System.in);
		Scanner isExit= new Scanner(System.in);
		int continuar=1;
		String plat;
		
		
		System.out.println("Selecciona els plats que voleu de la carta ");
		
		while(continuar==1){
			System.out.println("Quin plat voleu?");
			plat= sc.nextLine();
			auxComanda.add(plat);
			System.out.println("Prem 1 si voleu continuar demanant, o 0 si voleu aceptar la vostra seleccio");
			continuar= (int) isExit.nextInt();
			
		}
	}
	//Funcion que compara los elementos de la lista y el array
	public static boolean isPlatCarta(String auxComanda, String auxPlats) {
		boolean existeix=false;
		
		if ( auxComanda.compareToIgnoreCase(auxPlats)==0) {
			existeix=true;
		}
		
		return existeix;
	}
	
	//creams una funcion que recorre la lista de la comanda y el array con los platos que disponemos. Si existe sumamos el precio, sino, avisamos
	public static void revisarMenu(List<String> auxList, String[] auxPlats, HashMap<String, Integer> auxDiccCarta, int auxTotalPreu ) throws RuntimeException {
		boolean trovat=false;
		for (String e:auxList) {
			for (int i=0; i<auxPlats.length-1; i++) {
				if (!isPlatCarta(e, auxPlats[i])) {
					trovat=false;
				}else {
					trovat=true;
					break;
				}
			}
			if (trovat) {
				auxTotalPreu+= auxDiccCarta.get(e);
			}else {
				System.out.println("El plat "+ e +" que heu introduit no existeix a la carta, torna a llegir la carta, borinot");
			}
		}
		System.out.println(auxTotalPreu);
	}
	

}
