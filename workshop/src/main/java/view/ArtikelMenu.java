package view;

import java.math.BigDecimal;
import java.util.Scanner;

import Controllers.ArtikelController;
import validator.Validator;

public class ArtikelMenu {
	
	private  Scanner input = new Scanner(System.in);
	private ArtikelController artikelController;

	
	
	public ArtikelMenu(){
		artikelController = new ArtikelController();
	}
	public void artikelMenu() {   
	boolean logout = false;
	
	System.out.println("Kies en type in wat u wilt doen?");
	while(!logout) {
		System.out.println( "1 :Voeg Artikel toe");
		System.out.println( "2 :Wijzig Artikelgegevens");		
		System.out.println( "3 :Verwijder Artikelgegevens");
		System.out.println( "4 :Zoek een Artikel"); 
		System.out.println( "5 :Toon alle Artikelen:");
		System.out.println( "0 :Terug naar Hoofdmenu");
		
	   int actie = input.nextInt();
       switch(actie) {             
       case 1:   	   
    		voegArtikelToe();

			break;
		case 2:
			pasArtikelAan();
			break;
		case 3:
			deleteArtikel();
			break;
		case 4:
			zoekArtikel();
			break;
		case 5:
			printAlleArtikelen();
			break;
		case 0:
			logout=true;
			break;
		default:
			System.out.println("Ongeldige keuze");
		
		}       	   
	}  	
 }
	
	public void voegArtikelToe(){
		String naam="";
		while(true) {
			System.out.println("Wat is de naam van het Artikel?");
			naam = input.nextLine();
			if ((Validator.isNaamValid(naam))) {
			break;
			}
		}
		BigDecimal prijs;
		while (true) {
			System.out.println("Wat is de prijs van het Artikel?"); 
			prijs = input.nextBigDecimal();
			if(Validator.validBigDecimal(prijs)) {
				break;
			}
		}
		
		int voorraad=0;
		while(true) {
		System.out.println("Wat is de voorraad van het Artikel?");
		voorraad = input.nextInt();
		if (Validator.aantalIsPositief(voorraad)) {
			break;
			}
		}
		
		if(artikelController.voegArtikelToe(naam, prijs, voorraad)){
			System.out.println("Artikel toegevoegd!");
		}
		else{
			System.err.println("Kon Artikel niet toevoegen!");
		}
	}
	
	public void pasArtikelAan(){
		System.out.println("Welk Artikelnummer moet aangepast worden?");
		int artikelId = input.nextInt();    
		System.out.println("Wat wilt u aanpassen?");
		System.out.println("1: naam aanpassen");
		System.out.println("2: prijs aanpassen");
		System.out.println("3: voorraad aanpassen");		
		System.out.println("4: Annuleer");
		int keuze = input.nextInt();
		switch(keuze){
		case 1:
			pasNaamAan(artikelId);
			break;
		case 2:
			pasPrijsAan(artikelId);
			break;
		case 3:
			pasVoorraadAan(artikelId);
			break;
		case 4:
			break;
		default:
			System.out.println("Ongeldige keuze");
			pasArtikelAan();
		}
	}
	
	public void pasNaamAan(int artikelId){
		String naam="";
		while(true) {
			System.out.println("Vul nieuwe naam in");
			naam = input.next();
			if ((Validator.isNaamValid(naam))) {
			break;
			}
		}
		if(artikelController.pasNaamAan(artikelId,naam )){ 
			System.out.println("Naam aangepast!");
		}
		else{
			System.err.println("Naam kon niet aangepast worden!"); 
		}
		
	}
	
	public void pasPrijsAan(int artikelId){
		BigDecimal prijs;
		while(true) {
			System.out.println("Vul nieuwe prijs in");
			prijs = input.nextBigDecimal();
			if(Validator.validBigDecimal(prijs)) {
				break;
			}
		}
		if(artikelController.pasPrijsAan(artikelId, prijs)){ 
				System.out.println("Prijs aangepast!");
			}
		else{
			System.err.println("Prijs kon niet aangepast worden!"); 
		}
	
	}
	
	public void pasVoorraadAan(int artikelId){
		int voorraad=0;
		while(true) {
		System.out.println("Vul nieuwe voorraad van het Artikel in.");
		voorraad = input.nextInt();
		if (Validator.aantalIsPositief(voorraad)) {
			break;
			}
		}
		if(artikelController.pasVoorraadAan(artikelId, voorraad)){ 
			System.out.println("voorraad aangepast!");
		}
		else{
			System.err.println("voorraad kon niet aangepast worden!"); 
		}
	
	}
	
	public void zoekArtikel(){
		System.out.println("Vul  artikelnummer in om te zoeken");
		int artikelId = input.nextInt();
		String artikelInfo =artikelController.zoekArtikel(artikelId);
		if (artikelInfo!=null) {
			System.out.println( "Artikel gevonden: "+ artikelInfo  );
		}
		else {
			System.out.println("Artikel niet gevonden");
		};
		
	}
		
	public void deleteArtikel(){
		System.out.println("Wat is het artikel nummer om te verwijderen?");
		int artikelId = input.nextInt();
		if(artikelController.deleteArtikel(artikelId)){ 
			System.out.println("Artikel deleted!");
		}
		else{
			System.err.println("Kon artikel niet deleten!");
		}
		
	}
	
	public void printAlleArtikelen(){
		for(String s : artikelController.getAlleArtikelen()){
			System.out.println(s);
		}
	}

}