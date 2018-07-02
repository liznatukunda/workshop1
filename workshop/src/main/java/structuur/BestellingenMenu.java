package structuur;

import java.util.Scanner;
import Controllers.BestellingController;
import java.math.BigDecimal;


public class BestellingenMenu {
	
	private   Scanner input = new Scanner(System.in);
	private  BestellingController bestellingController;
	private  Menu hoofdMenu;
	
	
	public BestellingenMenu(){
		bestellingController = new BestellingController();
	}	
		
	
	public  void bestellingMenu() {
	boolean logout = false;	
	while(!logout) {	
		System.out.println("Kies en type in wat u wilt doen?");
			System.out.println( "1 :Voeg Nieuwe bestelling toe");
			System.out.println( "2 :Zoek bestelling per klant");
			System.out.println( "3 :Zoek bestelling per bestelling_id");
			System.out.println( "4 :Toon alle bestellingen");
			System.out.println( "0 :Terug naar Hoofdmenu");
			int actie = input.nextInt();
		       switch(actie) {             
		       case 1:   	   
		    		voegBestellingToe();

					break;
				case 2:
					zoekBestellingenPerKlant();
					break;
				case 3:
					zoekBestelling();
					break;
				case 4:
					printAlleBestellingen();
					break;
				case 0:
					logout=true;
					break;
				default:
					System.out.println("Ongeldige keuze");
					
				}       	   
			}  	
		 }
	public void zoekBestellingenPerKlant() {
		System.out.println("Vul  Klant Id in om haar bestellingen te tonen");
		int klantId = input.nextInt();
		for(String s : bestellingController.zoekBestellingenPerKlant(klantId)){
			System.out.println(s);
		}
	}
	
	public void zoekBestelling(){
		System.out.println("Vul  Bestelling Id in om te zoeken");
		int id = input.nextInt();
		String bestellingInfo = bestellingController.zoekBestelling(id);
		if (bestellingInfo!=null) {
		System.out.println("Bestelling gevonden: " + bestellingInfo);
		System.out.println("Wat wilt u doen?");
		System.out.println( "1 :Pas bestelling aan");		
		System.out.println( "2 :Verwijder bestelling"); 
		System.out.println( "3 :terug naar bestellingen menu"); 
		int actie = input.nextInt();
	       switch(actie) {	       
	       case 1:
	    		pasBestellingAan(id);
	    		break;
	    		
	    	case 2:
	    		deleteBestelling(id);
	    		break;
	    	case 3:
	    		break;
	    	default:
				System.out.println( "Kies 1 t/m 3");
				zoekBestelling(); 
	       }
		}
		
	}
	
	public  void voegBestellingToe(){
		System.out.println("Voer klant_id in ");
		int klantId = input.nextInt();		               
		if(bestellingController.voegBestellingToe(klantId)){
			System.out.println("Bestelling toegevoegd!");
		}
		else{
			System.err.println("Kon Bestelling niet toevoegen!");
		}
		
	}
	
	public void pasBestellingAan(int bestellingId){
		System.out.println("Wat wilt u doen?");
		System.out.println("1: totaal prijs aanpassen");	
		System.out.println("2: Annuleer");
		int keuze = input.nextInt();
		switch(keuze){
		case 1:
			pasPrijsAan(bestellingId);
			break;
		case 2:
			zoekBestelling();
			break;
		default:
			System.out.println("Ongeldige keuze");
			pasBestellingAan(bestellingId);
		}
	}	
	
	public void pasPrijsAan(int bestellingId){
		System.out.println("Vul nieuwe prijs in");
		BigDecimal prijs = input.nextBigDecimal();
		if(bestellingController.pasPrijsAan(bestellingId, prijs)){
			System.out.println("Prijs aangepast!");
		}
		else{
			System.err.println("Prijs kon niet aangepast worden!");
		}
		zoekBestelling();
	}
	
/*	public void pasPrijsAan(int bestellingId){
		System.out.println("Vul nieuwe prijs in");
		BigDecimal prijs = input.nextBigDecimal();
		if(bestellingController.pasPrijsAan(bestellingId, prijs)){
			System.out.println("Prijs aangepast!");
		}
		else{
			System.err.println("Prijs kon niet aangepast worden!");
		}
		zoekBestelling();
	}
*/	
	public void deleteBestelling(int bestellingNummer){
		System.out.println("Wat is het bestelling nummer om te verwijderen?");
		int nummer = input.nextInt();
		if(bestellingController.deleteBestelling(nummer)){ 
			System.out.println("Bestelling deleted!");
		}
		else{
			System.err.println("Kon Bestelling niet deleten!");
		}
		zoekBestelling();
	}
	
	public void printAlleBestellingen(){
		for(String s : bestellingController.getAlleBestelling()){
			System.out.println(s);
		}
	}
}
