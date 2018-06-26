// TO DO: BigDecimal

package domein;

import java.math.BigDecimal;

public class BestelRegel {
	
	private int id;
	private Artikel artikel;
	// private Bestelling bestelling; Door een ArrayList in Bestelling te genereren, is dit niet meer nodig
	private int aantal;
	private BigDecimal prijs;
	
	/**
	 * Creëert een extra bestelregel voor een bestelling op basis van het gewenste artikel en aantal.
	 * Berekent tevens de prijs voor deze bestelregel.
	 * @param artikel het artikel dat besteld moet worden
	 * @param aantal het aantal stuks van het artikel dat besteld moet worden
	 */
	public BestelRegel ( Artikel artikel,int aantal) {
		this.artikel=artikel;
		this.aantal=aantal;
		setPrijs();
	}
	
	public void setId(int id) {
		this.id=id;
	}
	

	
	
	// public void setBestelling(Bestelling bestelling) {
	// 	this.bestelling= bestelling;
	// }
	
	public void setAantal(int aantal) {
		this.aantal=aantal;
		// als er een ander aantal is gekozen, dan heeft dat ook consequenties voor de prijs, dus volgt:
		setPrijs();
	}
	
	public void setPrijs() {
		this.prijs=getPrijs();
	}
	
	public int getId() {
		return this.id;
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
