package pojo;

import java.math.BigDecimal;

public class Artikel {
	private int artikelNummer;
	private String naam;
	private String type;
	private BigDecimal prijs;
	
	
	
	public void setArtikelNummer (int artikelNummer) {
		this.artikelNummer=artikelNummer;
	}
	public void setNaam (String naam) {
		this.naam=naam;
	}
	public void setType (String type) {
		this.type=type;
	}
	public void setPrijs (BigDecimal prijs) {
		this.prijs=prijs;
	}
	public int getArtikelNummer () {
		return this.artikelNummer;
	}
	public String getNaam() {
		return this.naam;
	}
	public String getType() {
		return this.type;
	}
	public BigDecimal getPrijs() {
		return this.prijs;
	}
}
	
