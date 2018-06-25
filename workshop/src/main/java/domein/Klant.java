// TO DO: get&set voor ArrayList bestellingen
// TO DO: account als superklasse instellen??
// TO DO: toevoegen methode om bestelling te creëren of juist bij account???

package domein;

import java.util.ArrayList;

public class Klant {
	int id;
	private String voornaam;
	private String tussenvoegsel;
	private String achternaam;
	ArrayList <Bestelling> bestellingen= new ArrayList <Bestelling>();
	ArrayList <Adres> adressen= new ArrayList <Adres>();
	
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
		adressen.set(0, postadres);
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
		adressen.set(0, postadres);
	}

	/**
	 * Creëert een nieuwe klant zonder een nieuw adres 
	 * @param voornaam de voornaam van de nieuwe klant
	 * @param tussenvoegsel
	 * @param achternaam de achternaam van de nieuwe klant
	 */
	public Klant(String voornaam, String tussenvoegsel, String achternaam) {
		this.voornaam=voornaam;
		this.tussenvoegsel=tussenvoegsel;
		this.achternaam=achternaam;
		
	}
	
	
	public void setId(int id) {
		this.id=id;
	}
	public void setVoornaam(String voornaam) {
		this.voornaam=voornaam;
	}
	public void setTussenvoegsel(String tussenvoegsel) {
		this.tussenvoegsel=tussenvoegsel;
	}
	public void setAchternaam(String achternaam) {
		this.achternaam=achternaam;
	}
	
	public void setFactuurAdres(Adres adres) {
		adressen.set(1, adres);
	}
	
	public void setBezorgAdres (Adres adres) {
		adressen.set(2, adres);
	}

	
	public int getId() {
		return this.id;
	}
	public String getVoornaam() {
		return this.voornaam;
	}
	public String getTussenvoegsel() {
		return this.tussenvoegsel;
	}
	public String getAchternaam() {
		return this.achternaam;
	}
	
	/**
	 * 
	 * @param type 0=postadres, 1=factuuradres, 2=bezorgadres
	 * @return
	 */
	public Adres getAdres(int type) {
		return adressen.get(type);
	}

	
}
