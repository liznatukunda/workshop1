package pojo;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Bestelling {
	// private int bestellingNummer; Deze regel verwijder ik omdat die alleen voor de database is
	// private Klant klant; Deze regel verwijder ik, omdat Klant dan juist beter een ArrayList<Bestelling> kan hebben
	private BigDecimal totaalPrijs;
	private ArrayList<BestelRegel> bestelregels;
	
	public Bestelling() {
		bestelregels=new ArrayList<BestelRegel>();
	}
	
	// public void setBestellingNummer(int bestellingNummer) {
	//	this.bestellingNummer=bestellingNummer;
	//}
	//public void setKlant(Klant klant) {
	//	this.klant=klant;
	//}
	public BigDecimal bepaalTotaalPrijs() {
		BigDecimal voorlopigePrijs=new BigDecimal(0);
		for (int i=0;i<bestelregels.size();i++) {
			BestelRegel huidigeBestelregel=bestelregels.get(i);
			BigDecimal BestelregelPrijs=huidigeBestelregel.getPrijs();
			voorlopigePrijs.add(BestelregelPrijs);
		}
		return voorlopigePrijs;
	}
	
	
	//public int getBestellingNummer() {
	//	return this.bestellingNummer;
	//}
	//public Klant getKlant() {
	//	return this.klant;
	//}
	public BigDecimal getTotaalPrijs() {
		return this.totaalPrijs;
	}
	
	/**
	 * Voegt aan de bestelling nog een extra regel toe
	 * @param artikel het te bestellen artikel
	 * @param aantal de gewenste hoeveelheid van het te bestellen artikel
	 */
	public void voegBestelRegelToe(Artikel artikel, int aantal) {
		// Pascal: Moeten we hier nog een keer zorgen dat aantal minimaal 0 is, aangezien de constructor van BestelRegel die wordt aangeroepen dit al doet...
		BestelRegel nieuweBestelRegel=new BestelRegel (artikel, aantal);
		bestelregels.add(nieuweBestelRegel);
		// TO DO: Hier moet daarna de totaalprijs nog geüpdated worden
	}
	
	/**
	 * Geeft een totaaloverzicht van alle BestelRegels
	 * @return de ArrayList met alle BestelRegels daarin
	 */
	public ArrayList<BestelRegel> leesAlleBestelRegels() {
		return bestelregels;
	}
	
	// Pascal: Moeten we hier ook nog extra de setters voor de BestelRegels opnieuw creëren?
}

