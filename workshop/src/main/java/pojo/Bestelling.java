package pojo;

import java.math.BigDecimal;

public class Bestelling {
	private int bestellingNummer;
	private Klant klant;
	private BigDecimal totaalPrijs;
	
	public void setBestellingNummer(int bestellingNummer) {
		this.bestellingNummer=bestellingNummer;
	}
	public void setKlant(Klant klant) {
		this.klant=klant;
	}
	public void setTotaalPrijs(BigDecimal totaalPrijs) {
		this.totaalPrijs=totaalPrijs;
	}
	
	
	public int getBestellingNummer() {
		return this.bestellingNummer;
	}
	public Klant getKlant() {
		return this.klant;
	}
	public BigDecimal getTotaalPrijs() {
		return this.totaalPrijs;
	}
}

