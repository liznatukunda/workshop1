package structuur;

import java.math.BigDecimal;
import java.util.Scanner;

import Controllers.ArtikelController;
import Controllers.BestelregelController;

public class BestelregelMenu {
	private   Scanner input = new Scanner(System.in);
	private  BestelregelController bestelregelController;
	private ArtikelController artikelController;

	
	
	public BestelregelMenu(){
		bestelregelController = new BestelregelController();
		artikelController = new ArtikelController();
	}	
		
	
	public  void bestelregelMenu() {
	boolean logout = false;	
	while(!logout) {	
		System.out.println("Kies en type in wat u wilt doen?");
			System.out.println( "1 :Voeg Nieuwe bestelregel toe");
			System.out.println( "2 :Zoek bestelregel per bestelling");
			System.out.println( "3 :Zoek een bestelregel ");
			System.out.println( "4 :Toon alle bestelregels");
			System.out.println( "0 :Terug naar Hoofdmenu");
			int actie = input.nextInt();
		       switch(actie) {             
		       case 1:   	   
		    		voegBestelregelToe();

					break;
				case 2:
					zoekBestelregelsPerBestelling();
					break;
				case 3:
					zoekBestelregel();
					break;
				case 4:
					printAlleBestelregels();
					break;
				case 0:
					logout=true;
					break;
				default:
					System.out.println("Ongeldige keuze");
					
				}       	   
			}  	
		 }
	public void zoekBestelregelsPerBestelling() {
		System.out.println("Vul  bestelling Id in om haar bestelregels te tonen");
		int bestellingId = input.nextInt();
		for(String s : bestelregelController.zoekBestelregelsPerBestelling(bestellingId)){
			System.out.println(s);
		}
	}
	
	public void zoekBestelregel(){
		System.out.println("Vul  Bestelregel Id in om te zoeken");
		int id = input.nextInt();
		String bestelregelInfo = bestelregelController.zoekBestelregel(id);
		if (bestelregelInfo!=null) {
		System.out.println("Bestelregel gevonden: " + bestelregelInfo);
		System.out.println("Wat wilt u doen?");
		System.out.println( "1 :Pas bestelregel aan");		
		System.out.println( "2 :Verwijder bestelregel"); 
		System.out.println( "3 :terug naar bestelregel menu"); 
		int actie = input.nextInt();
	       switch(actie) {	       
	       case 1:
	    		pasBestelregelAan(id);
	    		break;
	    		
	    	case 2:
	    		deleteBestelregel(id);
	    		break;
	    	case 3:
	    		break;
	    	default:
				System.out.println( "Kies 1 t/m 3");
				zoekBestelregel(); 
	       }
		}
		
	}
	
	public  void voegBestelregelToe(){
		System.out.println("Voer bestelling id in ");
		int bestellingId = input.nextInt();	
		for(String s : artikelController.getAlleArtikelen()){
			System.out.println(s);
		}
		System.out.println("Voer artikel id in ");
		int artikelId = input.nextInt();
		System.out.println("Voer aantal in ");
		int aantal = input.nextInt();
		if(bestelregelController.voegBestelregelToe(bestellingId, artikelId, aantal)){
			System.out.println("Bestelregel toegevoegd!");
			//prijs berekenen ook van bestelling updaten
		}
		else{
			System.err.println("Kon Bestelregel niet toevoegen!");
		}
		
	}
	
	public void pasBestelregelAan(int bestelregelId){
		System.out.println("Wat wilt u doen?");
		System.out.println("1: aantal aanpassen");	
		System.out.println("2: Annuleer");
		int keuze = input.nextInt();
		switch(keuze){
		case 1:
			pasAantalAan(bestelregelId);
			break;
		case 2:
			zoekBestelregel();
			break;
		default:
			System.out.println("Ongeldige keuze");
			pasBestelregelAan(bestelregelId);
		}
	}	
	
	public void pasAantalAan(int bestelregelId){
		System.out.println("Vul nieuwe aantal in");
		int aantal = input.nextInt();
		if(bestelregelController.pasAantalAan(bestelregelId, aantal)){
			System.out.println("Aantal aangepast!");
			//herbereken prijs
		}
		else{
			System.err.println("Aantal kon niet aangepast worden!");
		}
		
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
	public void deleteBestelregel(int bestelregelId){

		if(bestelregelController.deleteBestelregel(bestelregelId)){ 
			System.out.println("Bestelregel deleted!");
			//bestelling totaal prijs herberekenen
		}
		else{
			System.err.println("Kon Bestelregel niet deleten!");
		}
		
	}
	
	public void printAlleBestelregels(){
		for(String s : bestelregelController.getAlleBestelregels()){
			System.out.println(s);
		}
	}
}
