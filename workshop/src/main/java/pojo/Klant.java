package pojo;

public class Klant {
	private int klantNummer;
	private String voornaam;
	private String tussenvoegsel;
	private String achternaam;
	private Adres adres;		
	
	public void setKlantNummer(int klantNummer) {
		this.klantNummer=klantNummer;
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
	public void setAdres(Adres adres) {
		this.adres=adres;
	}
	
	public int getKlantnummer() {
		return this.klantNummer;
	}
	public String getvoornaam() {
		return this.voornaam;
	}
	public String getTussenvoegsel() {
		return this.tussenvoegsel;
	}
	public String getAchternaam() {
		return this.achternaam;
	}
	public Adres getAdres() {
		return this.adres;
	}
	
}
