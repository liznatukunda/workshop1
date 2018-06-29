package structuur;

import java.util.Scanner;
import Controllers.BestellingConntroller;
import java.math.BigDecimal;


public class BestellingenMenu {
	
	private static  Scanner input = new Scanner(System.in);
	private static BestellingConntroller bestellingConntroller;
	private static Menu hoofdMenu;
	
	
	public BestellingenMenu(){
		bestellingConntroller = new BestellingConntroller();
	}	
		
	
	public static void bestellingMenu(int klantid) {
	boolean logout = false;	
	while(!logout) {	
		System.out.println("Kies en type in wat u wilt doen?");
			System.out.println( "1 :Voeg Nieuwe bestelling toe");
			System.out.println( "2 :Zoek bestelling");
			System.out.println( "0 :Terug naar Hoofdmenu");
			int actie = input.nextInt();
		       switch(actie) {             
		       case 1:   	   
		    		voegBestellingToe(klantid);

					break;
				case 2:
					zoekBestelling();
					break;
				case 0:
					hoofdMenu = new Menu();
					hoofdMenu.actie();
					break;
				default:
					System.out.println("Ongeldige keuze");
					bestellingMenu(klantid);
				}       	   
			}  	
		 }
	
	public static void zoekBestelling(){
		System.out.println("Vul  Bestelling nummer  in te zoeken");
		int nummer = input.nextInt();
		if(bestellingConntroller.zoekBestelling(nummer)){ 
		System.out.println("Bestelling gevonden: " + nummer);
		System.out.println("Wat wilt u doen?");
		System.out.println( "1 :Pas bestelling aan");		
		System.out.println( "2 :Verwijder bestelling"); 
		
		int actie = input.nextInt();
	       switch(actie) {	       
	       case 1:
	    		System.out.println("Voer het nummer in van het Bestelling dat u wilt aanpassen");
	    		pasBestellingAan(input.nextInt());
	    		break;
	    		
	    	case 2:
	    		System.out.println("Voer het nummer in van het Bestelling dat u wilt Verwijderen");
	    		deleteBestelling(input.nextInt());	default:
				System.out.println( "Kies 1 t/m 2");
				zoekBestelling(); 
	       }
		}
	}
	
	public static void voegBestellingToe(int klantid){
		System.out.println("Vul nieuwe prijs in");
		BigDecimal totaalprijs = input.nextBigDecimal();		               
		if(bestellingConntroller.voegBestellingToe(totaalprijs,klantid)){
			System.out.println("Bestelling toegevoegd!");
		}
		else{
			System.err.println("Kon Bestelling niet toevoegen!");
		}
		bestellingMenu(klantid);
	}
	
	public static void pasBestellingAan(int bestellingNummer){
		System.out.println("Wat wilt u doen?");
		System.out.println("1: totaal prijs aanpassen");	
		System.out.println("2: Annuleer");
		int keuze = input.nextInt();
		switch(keuze){
		case 1:
			pasPrijsAan(bestellingNummer);
			break;
		case 2:
			zoekBestelling();
			break;
		default:
			System.out.println("Ongeldige keuze");
			pasBestellingAan(bestellingNummer);
		}
	}	
	
	public static void pasPrijsAan(int bestellingNummer){
		System.out.println("Vul nieuwe prijs in");
		BigDecimal prijs = input.nextBigDecimal();
		if(bestellingConntroller.pasPrijsAan(bestellingNummer, prijs)){
			System.out.println("Prijs aangepast!");
		}
		else{
			System.err.println("Prijs kon niet aangepast worden!");
		}
		zoekBestelling();
	}
	
	public static void deleteBestelling(int bestellingNummer){
		System.out.println("Wat is de bestelling nummer om te verwijderen?");
		int nummer = input.nextInt();
		if(bestellingConntroller.deleteBestelling(nummer)){ 
			System.out.println("Bestelling deleted!");
		}
		else{
			System.err.println("Kon Bestelling niet delete worden!");
		}
		zoekBestelling();
	}
	
	public static void printAlleBestelling(){
		for(String s : bestellingConntroller.getAlleBestelling()){
			System.out.println(s);
		}
	}
}
