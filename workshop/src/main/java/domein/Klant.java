package domein;

import java.util.ArrayList;

import domein.Adres.AdresType;

public class Klant {
	
	private int id;
	private String voornaam;
	private String tussenvoegsel;
	private String achternaam;
	private ArrayList <Bestelling> bestellingen= new ArrayList <Bestelling>();
	private Adres postadres;
	private Adres factuuradres;
	private Adres bezorgadres;
	

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
		this.postadres=adres;
	}
	
	public void setFactuurAdres(Adres adres) {
		this.factuuradres=adres;
	}
	
	public void setBezorgAdres (Adres adres) {
		this.bezorgadres=adres;
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
	
	public Adres getAdres(AdresType adrestype) {
		if (adrestype.equals(AdresType.POSTADRES)) {
			return postadres;
		}
		if (adrestype.equals(AdresType.FACTUURADRES)) {
			return factuuradres;
		}
		if (!adrestype.equals(AdresType.BEZORGADRES)) {
			return bezorgadres;
		}
		else return null;
	}
	
	
	public void maakAdresAan(AdresType adrestype, String straatnaam, int huisnummer, String toevoeging, String postcode, String woonplaats) {
		Adres adres = new Adres (adrestype, straatnaam, huisnummer, toevoeging, postcode, woonplaats);
		if (!adrestype.equals(AdresType.POSTADRES)) {
			postadres=adres;
		}
		if (!adrestype.equals(AdresType.FACTUURADRES)) {
			factuuradres=adres;
		}
		if (!adrestype.equals(AdresType.BEZORGADRES)) {
			bezorgadres=adres;
		}
	}
	
	public void wijzigAdres (AdresType adrestype, String straatnaam, int huisnummer, String toevoeging, String postcode, String woonplaats) {
		Adres adres=null;
		if (adrestype.equals(AdresType.POSTADRES)) {
			adres=this.postadres;
		}
		if (adrestype.equals(AdresType.FACTUURADRES)) {
			adres=this.factuuradres;
		}
		if (adrestype.equals(AdresType.BEZORGADRES)) {
			adres=this.bezorgadres;
		}		
		adres.setStraatnaam(straatnaam);
		adres.sethuisnummer(huisnummer);
		adres.setToevoeging(toevoeging);
		adres.setPostcode(postcode);
		adres.setWoonplaats(woonplaats);
		if (adrestype.equals(AdresType.POSTADRES)) {
			this.postadres=adres;
		}
		if (adrestype.equals(AdresType.FACTUURADRES)) {
			this.factuuradres=adres;
		}
		if (adrestype.equals(AdresType.BEZORGADRES)) {
			this.bezorgadres=adres;
		}	
	}
	
	public void verwijderAdres (AdresType adrestype) {
		if (adrestype.equals(AdresType.POSTADRES)) {
			this.postadres=null;
		}
		if (adrestype.equals(AdresType.FACTUURADRES)) {
			this.factuuradres=null;
		}
		if (adrestype.equals(AdresType.BEZORGADRES)) {
			this.bezorgadres=null;
		}		
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
	
	public Bestelling getBestelling(int index) {
		return bestellingen.get(index);
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
		if (!this.postadres.equals(klant.getAdres(AdresType.POSTADRES))) {
			return false;
		}
		if (!this.factuuradres.equals(klant.getAdres(AdresType.FACTUURADRES))) {
			return false;
		}
		if (!this.bezorgadres.equals(klant.getAdres(AdresType.BEZORGADRES))) {
			return false;
		}
		for (int index=0; index<bestellingen.size();index++)
			if (!this.bestellingen.get(index).equals(klant.leesAlleBestellingen().get(index))) {
				return false;
			}
		return true;
	}

	
}
