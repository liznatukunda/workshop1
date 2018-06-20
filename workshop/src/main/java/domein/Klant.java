// TO DO: get&set voor ArrayList bestellingen
// TO DO: account als superklasse instellen??
// TO DO: toevoegen methode om bestelling te creëren of juist bij account???

package domein;

import java.util.ArrayList;

public class Klant {
	//private int klantNummer;Deze regel verwijder ik omdat die alleen voor de database is
	private String voornaam;
	private String tussenvoegsel;
	private String achternaam;
	private Adres postadres;
	ArrayList <Bestelling> bestellingen= new ArrayList <Bestelling>();
	
	// Hieronder volgen eerst twee constructors, eentje met en eentje zonder tussenvoegsel
	
	/**
	 * Creëert een nieuwe klant inclusief een nieuw adres van het adrestype postadres als tussenvoegsel is ingegeven
	 * @param voornaam de voornaam van de nieuwe klant
	 * @param tussenvoegsel het tussenvoegsel van de nieuwe klant
	 * @param achternaam de achternaam van de nieuwe klant
	 * @param postadres het vaste adres van de nieuwe klant
	 */
	public Klant(String voornaam, String tussenvoegsel, String achternaam, Adres postadres) {
		this.voornaam=voornaam;
		this.tussenvoegsel=tussenvoegsel;
		this.achternaam=achternaam;
		this.postadres=postadres;
	}
	
	/**
	 * Creëert een nieuwe klant inclusief een nieuw adres van het adrestype postadres als er geen tussenvoegsel is ingegeven
	 * @param voornaam de voornaam van de nieuwe klant
	 * @param achternaam de achternaam van de nieuwe klant
	 * @param postadres het vaste adres van de nieuwe klant
	 */
	public Klant(String voornaam, String achternaam, Adres postadres) {
		this.voornaam=voornaam;
		this.tussenvoegsel=null;
		this.achternaam=achternaam;
		this.postadres=postadres;
	}

	
	
	
	//public void setKlantNummer(int klantNummer) {
	//	this.klantNummer=klantNummer;
	//}
	public void setVoornaam(String voornaam) {
		this.voornaam=voornaam;
	}
	public void setTussenvoegsel(String tussenvoegsel) {
		this.tussenvoegsel=tussenvoegsel;
	}
	public void setAchternaam(String achternaam) {
		this.achternaam=achternaam;
	}
	public void setAdres(Adres adres) {
		this.postadres=adres;
	}
	
	//public int getKlantnummer() {
	//	return this.klantNummer;
	//}
	public String getvoornaam() {
		return this.voornaam;
	}
	public String getTussenvoegsel() {
		return this.tussenvoegsel;
	}
	public String getAchternaam() {
		return this.achternaam;
	}
	public Adres getAdres() {
		return this.postadres;
	}
	
}
