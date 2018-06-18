package pojo;

public class BestelRegel {
	private int bestelRegelNummer;
	private Artikel artikel;
	private Bestelling bestelling;
	private int aantal;
	
	public void setBestelRegelNummer(int bestelRegelNummer) {
		this.bestelRegelNummer=bestelRegelNummer;
	}
	public void setArtikel(Artikel artikel) {
		this.artikel=artikel;
	}
	public void setBestelling(Bestelling bestelling) {
		this.bestelling= bestelling;
	}
	public void setAantal(int aantal) {
		this.aantal=aantal;
	}
	
	public int getBestelRegelNummer() {
		return this.bestelRegelNummer;
	}
	public Artikel getArtikel() {
		return this.artikel;
	}
	public Bestelling getBestelling() {
		return this.bestelling;
	}
	public int getAantal() {
		return this.aantal;
	}
	
}
