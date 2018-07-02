package structuur;
import java.util.Scanner;

import Controllers.AccountController;
import Controllers.KlantController;
import logger.menu;


public class KlantgegevensMenu {
	private  Scanner input = new Scanner(System.in);
	private KlantController klantController;
	private Menu hoofdMenu;
	
	
	public KlantgegevensMenu(){
		klantController = new KlantController();
	}	
	
	
	
	
	
	public void klantGegevensMenu() {   
	boolean logout = false;
	
	System.out.println("Kies en type in wat u wilt doen?");
	while(!logout) {
		System.out.println( "1 :Voeg klantgegevens toe");
		System.out.println( "2 :Wijzig klantgegevens");		
		System.out.println( "3 :Verwijder klantgegevens");
		System.out.println( "4 :Zoek een klant"); 
		System.out.println( "5 :Toon alle klanten:");
		System.out.println( "0 :Terug naar Hoofdmenu");
		
	   int actie = input.nextInt();
       switch(actie) {             
       case 1:   	   
    		voegKlantToe();

			break;
		case 2:
			pasKlantAan();
			break;
		case 3:
			deleteKlant();
			break;
		case 4:
			zoekKlant();
			break;
		case 5:
			printAlleKlanten();
			break;
		case 0:
			logout=true;
			break;
		default:
			System.out.println("Ongeldige keuze");
		
		}       	   
	}  	
 }
	
	public void voegKlantToe(){
		
		System.out.println("Welk accountnummer moet een klant aan toegevoegd worden?");
		int accountId = input.nextInt();    
		System.out.println("Wat is de voornaam van deze klant?");
		String voornaam = input.next();
		System.out.println("Wat is de tussenvoegsel van deze klant?"); // als niets ingevoerd wordt dan gaat het mis.
		String tussenvoegsel = input.next();
		System.out.println("Wat is de achternaam van deze klant?");
		String achternaam = input.next();
		//adres info vragen en aanmaken postadres
		if(klantController.voegKlantToe(voornaam, tussenvoegsel, achternaam,accountId)){
			System.out.println("Klant toegevoegd!");
		}
		else{
			System.err.println("Kon klant niet toevoegen!");
		}
	}
	
	public void pasKlantAan(){
		System.out.println("Welk klantid aangepast worden?");
		int klantId = input.nextInt();    
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
		
		default:
			System.out.println("Ongeldige keuze");
			pasKlantAan();
		}
	}
	
	public void pasVoornaamAan(int klantid){
		System.out.println("Vul nieuwe naam in");
		String voornaam = input.next();
		if(klantController.pasVoornaamAan(klantid, voornaam)){ 
			System.out.println("VoorNaam aangepast!");
		}
		else{
			System.err.println("voornaam kon niet aangepast worden!"); 
		}
		
	}
	
	public void pasTussenvoegselAan(int id){
		System.out.println("Vul nieuwe tussenvoegsel in");
		String tussenvoegsel = input.next();
		if(klantController.pasTussenvoegselAan(id, tussenvoegsel)){ 
			System.out.println("Tussenvoegsel aangepast!");
		}
		else{
			System.err.println("Tussenvoegsel kon niet aangepast worden!"); 
		}
	
	}
	
	public void pasAchternaamAan(int id){
		System.out.println("Vul nieuwe achternaam  in");
		String achternaam = input.next();
		if(klantController.pasAchternaamAan(id, achternaam)){ 
			System.out.println("achternaam aangepast!");
		}
		else{
			System.err.println("achternaam kon niet aangepast worden!"); 
		}
	
	}
	
	public void zoekKlant(){
		System.out.println("Vul  klant id in te zoeken");
		int klantid = input.nextInt();
		String klantInfo =klantController.zoekKlant(klantid);
		if (klantInfo!=null) {
			System.out.println( "klant gevonden: "+ klantInfo  );
		}
		else {
			System.out.println("Account niet gevonden");
		};
		
	}
		
	public void deleteKlant(){
		System.out.println("Wat is de klant id om te verwijderen?");
		int Id = input.nextInt();
		if(klantController.deleteKlant(Id)){ 
			System.out.println("klant deleted!");
		}
		else{
			System.err.println("Kon Klant niet delete worden!");
		}
		
	}
	
	public void printAlleKlanten(){
		for(String s : klantController.getAlleKlanten()){
			System.out.println(s);
		}
	}

}
