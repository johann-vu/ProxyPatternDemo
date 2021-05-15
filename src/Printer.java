import java.util.Scanner;

public class Printer {
	
	Scanner keyboard = new Scanner(System.in);
	
	public void PrintWelcome() {
		System.out.println("Willkommen im Sandwich-Laden!\nDas Menü wird geladen...");
	}
	
	public String sandwichSelection(ISandwich[] sandwiches) {
		System.out.println("Unser Menü:");
		System.out.println("ID\tNAME\t\tPREIS");
		for (ISandwich sandwich : sandwiches) {
			System.out.println(sandwich.ID() + " \t" + sandwich.Name()+ " \t" + sandwich.Price());
		}
		
		System.out.print("Bitte wählen Sie ein Sandwich anhand der ID: ");
		return keyboard.nextLine();
	}
	
	public void PrintOrderStart(String id) {
		System.out.println("Sie haben das Sandwich mit der ID " + id + " ausgewählt.\nDie Bestellung wird jetzt aufgegeben. Bitte warten...");
	}
	
	public void PrintOrderPlaces(String id) {
		System.out.println("Ihre Bestellung wurde erfolgreich aufgegeben. Ihre Bestellnummer lautet "+ id + ".\nDrücken Sie ENTER um den Vorgang abzuschließen.");
		keyboard.nextLine();
	}
	
	public void PrintError() {
		System.out.println("Ein Serverseitiger Fehler ist aufgetreten. Drücken Sie ENTER um neu zu starten.");
		keyboard.nextLine();
	}
	
	public static void PrintDeveloperInfos(String s, boolean isVisible) {
		if (isVisible) {
			System.out.println("# DEV # "+s);
		}
	}
}
