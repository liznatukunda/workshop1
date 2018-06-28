
package domein;

import java.util.ArrayList;

import domein.Adres.AdresType;

public class Klant {
	int id;
	private String voornaam;
	private String tussenvoegsel;
	private String achternaam;
	private ArrayList <Bestelling> bestellingen= new ArrayList <Bestelling>();
	private ArrayList <Adres> adressen= new ArrayList <Adres>();
	

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
	
	public Klant(String voornaam, String achternaam) {
		this.voornaam=voornaam;
		this.tussenvoegsel=null;
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
	
	public void setPostadres(Adres adres) {
		adressen.set(0, adres);
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
	
	
	public void maakAdresAan(AdresType adrestype, String straatnaam, int huisnummer, String toevoeging, String postcode, String woonplaats) {
		Adres adres = new Adres (adrestype, straatnaam, huisnummer, toevoeging, postcode, woonplaats);
		if (!adrestype.equals(AdresType.POSTADRES)) {
			adressen.add(0,adres);
		}
		if (!adrestype.equals(AdresType.FACTUURADRES)) {
			adressen.add(1,adres);
		}
		if (!adrestype.equals(AdresType.BEZORGADRES)) {
			adressen.add(2,adres);
		}
	}
	
	public void wijzigAdres (AdresType adrestype, String straatnaam, int huisnummer, String toevoeging, String postcode, String woonplaats) {
		Adres adres=null;
		if (adrestype.equals(AdresType.POSTADRES)) {
			adres=adressen.get(0);
		}
		if (adrestype.equals(AdresType.FACTUURADRES)) {
			adres=adressen.get(1);
		}
		if (adrestype.equals(AdresType.BEZORGADRES)) {
			adres=adressen.get(2);
		}		
		adres.setStraatnaam(straatnaam);
		adres.sethuisnummer(huisnummer);
		adres.setToevoeging(toevoeging);
		adres.setPostcode(postcode);
		adres.setWoonplaats(woonplaats);
		if (adrestype.equals(AdresType.POSTADRES)) {
			adressen.set(0, adres);
		}
		if (adrestype.equals(AdresType.FACTUURADRES)) {
			adressen.set(1, adres);
		}
		if (adrestype.equals(AdresType.BEZORGADRES)) {
			adressen.set(2, adres);
		}	
	}
	
	public void verwijderAdres (int index) {
		adressen.set(index, null);
	}
	
	public void maakBestellingAan() {
		Bestelling bestelling=new Bestelling();
		bestellingen.add(bestelling);
	}
	
	public void verwijderBestelling(int index) {
		bestellingen.remove(index);
	}
	
	// Moet hier nog een wijzigBestelling, waarschijnlijk niet?!?
	
	public ArrayList <Bestelling> leesAlleBestellingen() {
		return bestellingen;
	}
	
	public ArrayList <Adres> leesAlleAdressen() {
		return adressen;
	}
	

	
	
	
	public boolean equals(Klant klant) {
		if (this.id!=klant.getId()) {
			return false;
		}
		if (!this.voornaam.equals(klant.getVoornaam())){
			return false;
		}
		if (!this.tussenvoegsel.equals(klant.getTussenvoegsel())){
			return false;
		}
		if (!this.achternaam.equals(klant.getAchternaam())){
			return false;
		}
		for (int index=0; index<adressen.size(); index++) {
			if (!this.adressen.get(index).equals(klant.getAdres(index)))
			{
				return false;
			}
		}
		for (int index=0; index<bestellingen.size();index++)
			if (!this.bestellingen.get(index).equals(klant.leesAlleBestellingen().get(index))) {
				return false;
			}
		return true;
	}

	
}
