// TO DO: zie tweede constructor
// TO DO: moet iedere constructor ook aanroepen de methode voegBestelRegelToe(Artikel artikel, int aantal)??
// TO DO: moet niet meteen het adrestype opgeslagen worden als een afwijkend adres wordt ingevoerd??


package pojo;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Bestelling {
	
	// private int bestellingNummer; Deze regel verwijder ik omdat die alleen voor de database is
	// private Klant klant; Deze regel verwijder ik, omdat Klant dan juist beter een ArrayList<Bestelling> kan hebben
	private BigDecimal totaalPrijs;
	private ArrayList<BestelRegel> bestelregels=new ArrayList<BestelRegel>();
	private Adres afwijkendFactuurAdres;
	private Adres afwijkendBezorgAdres;
	
	
	// hieronder volgen drie constructors, eentje waarbij geen sprake is van een afwijkend adres, eentje waarbij slechts één adres gebruikt wordt, tenslotte eentje waarbij twee afwijkende adressen worden gebruikt 
	
	/**
	 * creëert een nieuwe bestelling zonder afwijkend factuur- of bezorgadres
	 */
	public Bestelling() {
		this.afwijkendFactuurAdres=null;
		this.afwijkendBezorgAdres=null;
	}
	
	/**
	 * creëert een nieuwe bestelling waarbij slechts één adres gebruikt wordt.
	 * Dit ene adres kan gebruikt worden voor het afwijkend factuuradres, het afwijkend bezorgadres of voor beide.
	 * @param enkelAfwijkendAdres true als er alleen een factuuradres of alleen een bezorgadres wordt toegevoegd, false als bezorgadres en factuuradres moeten worden toegevoegd
	 * @param afwijkendAdresIsFactuurAdres true als dat enkele afwijkende adres het factuuradres betreft, false als het enkele afwijkende adres het bezorgadres betreft
	 * @param afwijkendAdres het betreffende adres dat als bezorgadres en/of factuuradres moet worden gebruikt
	 */
	public Bestelling (boolean enkelAfwijkendAdres,boolean afwijkendAdresIsFactuurAdres, Adres afwijkendAdres) {
		// TO DO: tweede boolean doet niets indien eerste boolean false is
		
		// als slechts een van de adressen afwijkend moet zijn
		if (enkelAfwijkendAdres) {
			// indien bovendien het afwijkende adres gaat om het factuuradres, factuuradres aanpassen
			if (afwijkendAdresIsFactuurAdres) {
				this.afwijkendFactuurAdres=afwijkendAdres;
				this.afwijkendBezorgAdres=null;
			}
			// anders is het enkele afwijkende adres het bezorgadres, waardoor dit aangepast moet worden
			else {
				this.afwijkendBezorgAdres=afwijkendAdres;
				this.afwijkendFactuurAdres=null;
			}
		}
		// als beide adressen aangepast moeten worden naar één en hetzelfde adres, dan factuuradres en bezorgadres aanpassen
		else {
			this.afwijkendFactuurAdres=afwijkendAdres;
			this.afwijkendBezorgAdres=afwijkendAdres;
		}		
	}
	
	/**
	 * Creëert een nieuwe bestelling waarbij er verschillende adressen gebruikt worden voor factuur- en bezorgadres
	 * @param afwijkendFactuurAdres het afwijkende adres dat gebruikt moet worden als factuuradres
	 * @param afwijkendBezorgAdres het afwijkende adres dat gebruikt moet worden als bezorgadres
	 */
	public Bestelling (Adres afwijkendFactuurAdres, Adres afwijkendBezorgAdres) {
		this.afwijkendFactuurAdres=afwijkendFactuurAdres;
		this.afwijkendBezorgAdres=afwijkendBezorgAdres;
	}
	
	// public void setBestellingNummer(int bestellingNummer) {
	//	this.bestellingNummer=bestellingNummer;
	//}
	//public void setKlant(Klant klant) {
	//	this.klant=klant;
	//}
	
	public void bepaalTotaalPrijs() {
		BigDecimal voorlopigePrijs=new BigDecimal(0);
		for (int i=0;i<bestelregels.size();i++) {
			BestelRegel huidigeBestelregel=bestelregels.get(i);
			BigDecimal BestelregelPrijs=huidigeBestelregel.getPrijs();
			voorlopigePrijs.add(BestelregelPrijs);
		}
		totaalPrijs=voorlopigePrijs;
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
		// als er een extra bestelregel is, levert dat ook een nieuwe totaalprijs op, dus die moet ook opnieuw ingesteld worden
		bepaalTotaalPrijs();
	}
	
	public void verwijderBestelRegel(int index) {
		bestelregels.remove(index);
		// als een bestelregel verwijderd is, levert dat ook een nieuwe totaalprijs op, dus die moet ook opnieuw ingesteld worden
		bepaalTotaalPrijs();
	}
	
	public void wijzigBestelRegel(int index, Artikel artikel, int aantal) {
		BestelRegel aanTePassenBestelRegel=bestelregels.get(index);
		aanTePassenBestelRegel.setAantal(aantal);
		aanTePassenBestelRegel.setArtikel(artikel);
		// als een artikel en/of aantal gewijzigd is, levert dat ook een nieuwe totaalprijs op, dus die moet ook opnieuw ingesteld worden
		bepaalTotaalPrijs();
	}
	
	/**
	 * Geeft een totaaloverzicht van alle BestelRegels
	 * @return de ArrayList met alle BestelRegels daarin
	 */
	public ArrayList<BestelRegel> leesAlleBestelRegels() {
		return bestelregels;
	}
	
	public Adres getAfwijkendFactuurAdres() {
		return afwijkendFactuurAdres;
	}
	
	public Adres getAfwijkendBezorgAdres() {
		return afwijkendBezorgAdres;
	}
	
	public void setAfwijkendFactuurAdres(Adres adres) {
		this.afwijkendFactuurAdres=adres;		
	}
	
	public void setAfwijkendBezorgAdres(Adres adres) {
		this.afwijkendBezorgAdres=adres;
	}
	
}

