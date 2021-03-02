package nivell3;

import java.io.IOException;
import java.util.*;

public class Nivell3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
				List<String> miComanda = new ArrayList<String>();
				List<Integer> listSubtotalComanda = new ArrayList<Integer>();
				String plats[] = new String[10];
				int preus[] = new int[10];
				
				HashMap<String, Integer> carta = new HashMap<String, Integer>();
				
				
				
				Carta_Restaurant(plats, preus, carta);
				imprimirMenu(plats, preus);
				
				
				try {
					FerComanda(miComanda, plats, preus, listSubtotalComanda);
				} catch (IOException e) {
					System.out.println("Error E/S");
						
				}
				
				imprirComanda(miComanda, listSubtotalComanda);		
				
		}
			
			public static void revisarBilletes(int total) {
				
				int cinco =5;
				int diez=10;
				int vinte=20;
				int cincuenta=50;
				int cien=100;
				int doscientos=200;
				int quinientos=500;
				double preu=0.0;
				
				
				
			}
			
			public static void Carta_Restaurant(String crearPlats[], int crearPreus[], HashMap<String, Integer> auxCarta) {
				
				int contador=0;
				auxCarta.put("Bufalina", 12);
				auxCarta.put("Quattro Formaggi", 12);
				auxCarta.put("Pino Daniele", 18);
				auxCarta.put("Margherita", 9);
				auxCarta.put("Prosciutto", 10);
				auxCarta.put("Parmiggiana", 11);
				auxCarta.put("Al Tonno", 13);
				auxCarta.put("Carbonara", 11);
				auxCarta.put("Massimo Troise", 17);
				auxCarta.put("Ortolana", 12);
				
				Iterator<String> it= auxCarta.keySet().iterator();
				
				
				while (it.hasNext()) {
					crearPlats[contador]= it.next();
					crearPreus[contador] = auxCarta.get(crearPlats[contador]);
					contador++;
					
				}
			}
			
			public static void imprimirMenu(String auxPlats[], int auxPreus[]) {
				for (int i=0; i< auxPlats.length; i++) {
					System.out.println(auxPlats[i] + " " +auxPreus[i]);
				}
			}
			
			public static void FerComanda(List<String> auxComanda, String ArrayPlats[], int arrayPreus[], List<Integer> listCalSubtotal ) throws  IOException {
				
				Scanner sc= new Scanner(System.in);
				
				int continuar=1;
				String plat;
				
				
				System.out.println("Selecciona els plats que voleu de la carta ");
				
				while(continuar==1){
					System.out.println("Quin plat voleu?");
					plat= sc.nextLine();
					
					try {
							controlEntradaUsuari(plat);
					}catch(Error_Entrada ex) {
							System.out.println(ex.getMessage());
					}
					
					try {
												
						revisarMenu(auxComanda, plat, ArrayPlats, arrayPreus,  listCalSubtotal );
						
					} catch (Control_Errores e) {
						
						System.out.println( e.getMessage());
					}
					try {
						continuar=Continuar_Demanant();
					}catch(Control_Errores e1) {
						System.out.println(e1.getMessage());
						continuar=0;
					}catch(InputMismatchException e2) {
						System.out.println(e2.getMessage());
					}
					
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
			
			//creamos una funcion que recorre la lista de la comanda y el array con los platos que disponemos. Si existe sumamos el precio y lo añadimos al pedido, sino, avisamos
			
			public static void revisarMenu(List<String> auxLlistaComanda, String scannPlat,  String auxPlats[], int auxPreu[], List<Integer> listSubtotalComanada ) throws Control_Errores {
				int index=-1;
				
				for (int i=0; i<auxPlats.length-1;i++) {
					
					if (isPlatCarta(scannPlat, auxPlats[i])) {
							index=i;
							break;
					}
				}
				if (index!=-1) {
					auxLlistaComanda.add(auxPlats[index]);
					listSubtotalComanada.add(auxPreu[index]) ;
				}else {
					
						throw  new Control_Errores("El plat "+ scannPlat +" que heu introduit no existeix a la carta, torna a llegir la carta, borinot");
				}
				
				
			}
			
			public static void imprirComanda(List<String> listaPedido, List<Integer> auxListPreusComanda) {
				int auxTotalPed=0;
				for (String e:listaPedido) {
					System.out.println(e);
				}
				
				for (Integer ele:auxListPreusComanda) {
					auxTotalPed+=ele;
				}
				
				System.out.println("El total de su pedido es: " + auxTotalPed);
			}
			
			
			public static int Continuar_Demanant() throws Control_Errores, InputMismatchException  {
				
				Scanner isExit= new Scanner(System.in);
				
				System.out.println("Prem 1 si voleu continuar demanant, o 0 si voleu aceptar la vostra seleccio");
				int continuar = (int) isExit.nextInt();
				if (continuar==0 || continuar==1) {
					return continuar;
				}else {
					throw new Control_Errores("Solo se acepta el valor 0 para salir, o 1 para continuar con el pedido");
				}

			}
			
			public static void controlEntradaUsuari(String platAfegit) throws Error_Entrada{
				
				String sinEspacios=platAfegit.trim();
				
				
				for (int i=0; i<=sinEspacios.length()-1;i++) {
					int c = sinEspacios.charAt(i);
					
					if (c==32) {
						
					}else if ((c>=65 && c<=97) || (c>=97 && c<=122)){
						
					}else {
						throw new Error_Entrada("Solo se permiten letras");
					}
				}
				
			}
}

class Error_Entrada extends Exception{
	public  Error_Entrada() {}
	
	public  Error_Entrada(String misatge_err) {
		super(misatge_err);
	}
}

class Control_Errores extends Exception{
	public Control_Errores() {}
	
	public Control_Errores(String misatge_err) {
		super(misatge_err);
	}
}
