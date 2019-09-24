package ui;

import model.*;
import java.util.*;
import exceptions.RepeatedName;
import exceptions.NameNotFound;;

public class Main {
	private Controller system;
	private Scanner scan;
	
	public static void main(String[] args) {
		Main m = new Main();
		m.showMenu();
	}
	
	public Main() {
		system = new Controller();
		scan = new Scanner(System.in);
	}
	
	public void showMenu() {
		int userSelection = 0;
		
		do {
			options();
			userSelection =  scan.nextInt();
			scan.nextLine();
			try {
				switch(userSelection) {
				case 1:
					System.out.println("Digite el nombre del clan");
					String clanName = scan.nextLine();
					Clan newClan = new Clan(clanName);
					try {
						system.addClan(newClan);
					}catch(RepeatedName e) {
						System.out.println("" + e.getMessage());
					}
				break;
				case 2:
					System.out.println("Digite el nombre del clan");
					clanName = scan.nextLine();
					System.out.println("Digite el nombre del ninja");
					String ninjaName = scan.nextLine();
					System.out.println("Digite la personalidad del ninja");
					String ninjaPersonality = scan.nextLine();
					System.out.println("Digite la fecha actual");
					String actualDate = scan.nextLine();
					System.out.println("Digite el puntaje del ninja");
					double ninjaScore = scan.nextDouble();
					scan.nextLine();
					System.out.println("Digite el nombre de una tecnica del ninja");
					String ninjaTechnique = scan.nextLine();
					System.out.println("Digite el factor de la tecnica");
					double techniqueFactor = scan.nextDouble();
					scan.nextLine();
					
					Ninja newNinja = new Ninja(ninjaName, ninjaPersonality, actualDate, ninjaScore, 
												new Technique(ninjaTechnique, techniqueFactor, null), null, null);
					try {
						system.addNinja(clanName, newNinja);
					}catch(RepeatedName e) {
						System.out.println(e.getMessage());
					}catch(NameNotFound e) {
						System.out.println(e.getMessage());
					}
				break;
				case 3:
					System.out.println("Digite el nombre del clan");
					clanName = scan.nextLine();
					System.out.println("Digite el nombre del ninja");
					ninjaName = scan.nextLine();
					System.out.println("Digite el nombre de la tecnica");
					ninjaTechnique = scan.nextLine();
					System.out.println("Digite el factor de la tecnica");
					techniqueFactor = scan.nextDouble();
					scan.hasNextLine();
					
					Technique nwTechnique = new Technique(ninjaTechnique, techniqueFactor, null);
					
					try {
						system.addTechnique(clanName, ninjaName, nwTechnique);
					}catch(RepeatedName e) {
						System.out.println(e.getMessage());
					}catch(NameNotFound e) {
						System.out.println(e.getMessage());
					}
				break;
				case 4:
					System.out.println("Digite el nombre del clan");
					clanName = scan.nextLine();
					
					try {
						system.deleteClan(clanName);
					}catch(NameNotFound e) {
						System.out.println(e.getMessage());
					}
				break;
				case 5:
					System.out.println("Digite el nombre del clan");
					clanName = scan.nextLine();
					System.out.println("Digite el nombre del personaje");
					ninjaName = scan.nextLine();
					
					try {
						system.deleteNinja(clanName, ninjaName);
					}catch(NameNotFound e) {
						System.out.println(e.getMessage());
					}
				break;
				case 6:
					System.out.println("Digite el nombre del clan");
					clanName = scan.nextLine();
					System.out.println("Digite el nombre del personaje");
					ninjaName = scan.nextLine();
					System.out.println("Digite el nombre de la tecnica");
					ninjaTechnique = scan.nextLine();
					
					try {
						system.deleteTechnique(clanName, ninjaName, ninjaTechnique);
					}catch(NameNotFound e) {
						System.out.println(e.getMessage());
					}
				break;
				case 7:
					system.sortClansByName();
					System.out.println(system.showSortedInfo());
				break;
				case 8:
					
				break;
				case 9:
					
				break;
				case 10:
					
				break;
				case 11:
					
				break;
				case 12:
					
				break;
				case 13:
					System.out.println("Gracias por usar nuestro programa. Regrese pronto");
				break;
				}
			}catch(InputMismatchException e){
				System.out.println("DIGITE UNA OPCION VALIDA");
				scan.nextLine();
			}
			
		}while(userSelection != 13);
	}
	
	public void options() {
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("**********************************************************************************************************************************************");
		System.out.println("Escoja una de las siguientes opciones:");
		System.out.println("1. Crear clan");
		System.out.println("2. Crear personaje");
		System.out.println("3. Agregar tecnica");
		System.out.println("4. Eliminar clan");
		System.out.println("5. Eliminar personaje");
		System.out.println("6. Eliminar tecnica");
		System.out.println("7. Mostrar los clanes");
		System.out.println("8. Mostrar los personajes por clan");
		System.out.println("9. Mostrar las tecnicas de un personaje");
		System.out.println("10. Cambiar atributos de un clan");		
		System.out.println("11. Cambiar atributos de un personaje");
		System.out.println("12. Cambiar atributos de una tecnica");
		System.out.println("13. Salir");
		System.out.println("**********************************************************************************************************************************************");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}
}
