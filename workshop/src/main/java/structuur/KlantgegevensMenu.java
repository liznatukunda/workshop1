package structuur;
import java.util.Scanner;

import Controllers.AccountController;
import Controllers.KlantController;


public class KlantgegevensMenu {
	private static  Scanner input = new Scanner(System.in);
	private static KlantController klantController;
	private static menu hoofdMenu;
	
	
	public KlantgegevensMenu(){
		klantController = new KlantController();
	}	
	
	
	public static void start(){
		System.out.println("Alle klanten:");
		printAlleKlanten();
		klantGegevensMenu();
	}
	
	
	public static void klantGegevensMenu() {
	boolean logout = false;
	
	System.out.println("Kies en type in wat u wilt doen?");
	while(!logout) {
		System.out.println( "1 :Voeg klantgegevens toe");
		System.out.println( "2 :Wijzig klantgegevens");		
		System.out.println( "3 :Verwijder klantgegevens");
		System.out.println( "4 :Zoek klant"); 
		System.out.println( "5 :Terug naar Hoofdmenu");
		
	   int actie = input.nextInt();
       switch(actie) {             
       case 1:   	   
    		voegKlantToe();

			break;
		case 2:
			System.out.println("Voer het nummer in van het klant dat u wilt aanpassen");
			pasKlantAan(input.nextInt());
			break;
		case 3:
			System.out.println("Voer het nummer in van het klant dat u wilt Verwijderen");
			deleteKlant(input.nextInt());
			break;
		case 4:
			System.out.println("Voer het nummer in van het klant dat u wilt zoeken");
			zoekKlant(input.nextInt());
			break;
		case 5:
			hoofdMenu = new menu();
			hoofdMenu.actie();
			break;
		default:
			System.out.println("Ongeldige keuze");
			printKeuzeKlant();
		}       	   
	}  	
 }
	
	public static void voegKlantToe(){
		System.out.println("Wat is de voornaam van deze klant?");
		String voornaam = input.next();
		System.out.println("Wat is de tussenvoegsel van deze klant?");
		String tussenvoegsel = input.next();
		System.out.println("Wat is de achternaam van deze klant?");
		String achternaam = input.next();
		int accountId = input.nextInt();                  
		if(klantController.voegKlantToe(voornaam, tussenvoegsel, achternaam,accountId)){
			System.out.println("Klant toegevoegd!");
		}
		else{
			System.err.println("Kon klant niet toevoegen!");
		}
		start();
	}
	
	public void pasKlantAan(int klantId){
		System.out.println("Wat wilt u aanpassen?");
		System.out.println("1: voornaam aanpassen");
		System.out.println("2: tussenvoegsel aanpassen");
		System.out.println("3: achternaam aanpassen");		
		System.out.println("4: Annuleer");
		int keuze = input.nextInt();
		switch(keuze){
		case 1:
			pasVoornaamAan(klantId);
			break;
		case 2:
			pasTussenvoegselAan(klantId);
			break;
		case 3:
			pasAchternaamAan(klantId);
			break;
		case 4:
			start();
			break;
		default:
			System.out.println("Ongeldige keuze");
			pasKlantAan(klantId);
		}
	}
	
	public static void pasVoornaamAan(int id, int accountId){
		System.out.println("Vul nieuwe naam in");
		String voornaam = input.next();
		if(KlantController.pasVoornaamAan(id, voornaam, accountId)){ 
			System.out.println("VoorNaam aangepast!");
		}
		else{
			System.err.println("voornaam kon niet aangepast worden!"); 
		}
		start();
	}
	
	public void pasTussenvoegselAan(int id,int accountId){
		System.out.println("Vul nieuwe tussenvoegsel in");
		String tussenvoegsel = input.next();
		if(KlantController.pasTussenvoegselAan(id, tussenvoegsel,accountId)){ 
			System.out.println("Tussenvoegsel aangepast!");
		}
		else{
			System.err.println("Tussenvoegsel kon niet aangepast worden!"); 
		}
		start();
	}
	
	public void pasAchternaamAan(int id, int accountId){
		System.out.println("Vul nieuwe achternaam  in");
		String achternaam = input.next();
		if(KlantController.pasAchternaamAan(id, achternaam, accountId)){ 
			System.out.println("achternaam aangepast!");
		}
		else{
			System.err.println("achternaam kon niet aangepast worden!"); 
		}
		start();
	}
	
	public static void zoekKlant(int id){
		System.out.println("Vul  achternaam  te zoeken");
		String achternaam = input.next();
		if(klantController.zoekKlant(id)){ 
			System.out.println("achternaam gevonden!");
		}
		else{
			System.err.println("achternaam is niet gevonden!"); 
		}
		start();
	}
	
	public static void deleteKlant(int id){
		System.out.println("Wat is de Account id om te verwijderen?");
		int Id = input.nextInt();
		if(KlantController.deleteKlant(Id)){ 
			System.out.println("klant deleted!");
		}
		else{
			System.err.println("Kon Klant niet delete worden!");
		}
		start();
	}
	
	public static void printAlleKlanten(){
		for(String s : KlantController.getAlleKlanten()){
			System.out.println(s);
		}
	}

}
