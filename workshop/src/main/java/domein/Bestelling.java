package domein;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Bestelling {
	

	private BigDecimal totaalPrijs;
	private int bestellingNummer;
	private ArrayList<BestelRegel> bestelregels=new ArrayList<BestelRegel>();

	public Bestelling(BigDecimal totaalPrijs) {
	}
	
	
	public void setBestellingNummer(int bestellingNummer) {
		this.bestellingNummer=bestellingNummer;
	}

	
	public void bepaalTotaalPrijs() {
		BigDecimal voorlopigePrijs=new BigDecimal(0);
		for (int i=0;i<bestelregels.size();i++) {
			BestelRegel huidigeBestelregel=bestelregels.get(i);
			BigDecimal BestelregelPrijs=huidigeBestelregel.getPrijs();
			voorlopigePrijs.add(BestelregelPrijs);
		}
		totaalPrijs=voorlopigePrijs;
	}
	
	
	public int getBestellingNummer() {
		return this.bestellingNummer;
	}

	
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
	

	
}

