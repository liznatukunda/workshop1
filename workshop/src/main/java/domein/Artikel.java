// TO DO: BigDecimal
// TO DO: type verwijderen???
// TO DO: exception voor negatieve prijs

package domein;

import java.math.BigDecimal;

public class Artikel {
	// private int artikelNummer; Deze regel verwijder ik omdat die alleen voor de database is
	private String naam;
	private String type;
	private BigDecimal prijs;
	// Moeten we de BigDecimal niet nog verder definiëren, zodat het daadwerkelijk een prijs met 2 cijfers achter de komma wordt??
	private int voorraad;
	
	
	/**
	 * Creëert een nieuw artikel op basis van opgegeven artikelnaam, het type van het artikel, de artikelprijs en de voorraad
	 * @param naam de naam voor het nieuwe artikel
	 * @param type de naam van het type van het artikel
	 * @param prijs de stukprijs van het artikel, die niet negatief mag zijn
	 * @param voorraad de hoeveelheid artikelen die op voorraad zijn, die niet negatief mag zijn
	 */
	public Artikel(String naam, String type, BigDecimal prijs, int voorraad) {
		if (voorraad>=0) 
		// Hier moet nog aan toegevoegd worden dat prijs ook groter of gelijk aan 0 is
		{
			this.naam=naam;
			this.type=type;
			this.prijs=prijs;
			this.voorraad=voorraad;
		}
		
	}
	
	// public void setArtikelNummer (int artikelNummer) {
	//	this.artikelNummer=artikelNummer;
	//}
	
	public void setNaam (String naam) {
		this.naam=naam;
	}
	public void setType (String type) {
		this.type=type;
	}
	
	/**
	 * Wijzigt de prijs naar de gewenste stuksprijs van het artikel
	 * @param prijs de nieuwe prijs, die minimaal 0 moet zijn
	 */
	public void setPrijs (BigDecimal prijs) {
		// hier moet nog een if formulering komen om af te dwingen dat de prijs minimaal 0 is
		this.prijs=prijs;
	}
	
	/**
	 * Wijzigt de voorraad naar de gewenste hoeveelheid artikelen
	 * @param voorraad de gewenste nieuwe hoeveelheid, die minimaal 0 moet zijn
	 */
	public void setVoorraad (int voorraad) throws Exception {
		if (voorraad>=0) {
			this.voorraad=voorraad;
		}
		else new Exception("Voorraad kan niet negatief zijn");
	}
	
	//public int getArtikelNummer () {
	//	return this.artikelNummer;
	//}
	public String getNaam() {
		return this.naam;
	}
	public String getType() {
		return this.type;
	}
	public BigDecimal getPrijs() {
		return this.prijs;
	}
	public int getVoorraad() {
		return this.voorraad;
	}
	
	/**
	 * Verlaagt de voorraad mits de gewenste verlaging een positief aantal is en de verlaging niet groter is dan de huidige voorraad
	 * @param aantal de hoeveelheid waarmee de voorraad verlaagt moet worden
	 */
	public void verlaagVoorraad(int aantal) throws Exception {
		if (aantal>0) {
			setVoorraad(getVoorraad()-aantal);
		}
		else {
			throw new Exception ("aantal mag niet negatief zijn");
		}
		
	}
}
	
