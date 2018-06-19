package pojo;

import java.math.BigDecimal;

public class BestelRegel {
	
	
	// 
	private Artikel artikel;
	// private Bestelling bestelling;
	// De voorgaande regel verwijder ik, omdat ik een ArrayList bestelregels declareer in Bestelling die juist alle bestelregels bijhoudt.
	// Dat betekent dat de betreffende setters en getters verderop ook verwijderd worden
	private int aantal;
	private BigDecimal prijs;
	
	/**
	 * Creëert een extra bestelregel voor een bestelling op basis van het gewenste artikel en aantal.
	 * Berekent tevens de prijs voor deze bestelregel.
	 * @param artikel het artikel dat besteld moet worden
	 * @param aantal het aantal stuks van het artikel dat besteld moet worden
	 */
	public BestelRegel (Artikel artikel, int aantal) {
		this.artikel=artikel;
		this.aantal=aantal;
		this.prijs=getPrijs();
	}
	

	public void setArtikel(Artikel artikel) {
		this.artikel=artikel;
	}
	// public void setBestelling(Bestelling bestelling) {
	// 	this.bestelling= bestelling;
	// }
	public void setAantal(int aantal) {
		this.aantal=aantal;
	}
	

	public Artikel getArtikel() {
		return this.artikel;
	}
	// public Bestelling getBestelling() {
	// 	return this.bestelling;
	// }
	public int getAantal() {
		return this.aantal;
	}
	
	/**
	 * Haalt de stukprijs op van het artikel en bepaalt de totaalprijs voor deze bestelregel op basis van het aantal
	 * @return de vermenigvuldiging van de stukprijs met het aantal bestelde artikelen
	 */
	public BigDecimal getPrijs() {
		BigDecimal stukprijs=artikel.getPrijs();
		return stukprijs.multiply(new BigDecimal(aantal));
	}
	
}
