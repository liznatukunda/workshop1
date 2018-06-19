package pojo;

public class Adres {
	
	public enum AdresType {POSTADRES, FACTUURADRES, BEZORGADRES}
	
	private AdresType adresType;
	// private int adresNummer;
	private String straatnaam;
	private int huisnummer;
	private String toevoeging;
	private String postcode;
	private String woonplaats;
	
	/**
	 * Creëert een adres als er geen toevoeging bij het huisnummer hoort
	 * @param adresType het soort adres
	 * @param straatnaam de naam van de straat
	 * @param huisnummer het huisnummer
	 * @param postcode de postcode het adres
	 * @param woonplaats het dorp of de stad
	 */
	public Adres (AdresType adresType, String straatnaam, int huisnummer, String postcode, String woonplaats) {
		this.adresType=adresType;
		this.straatnaam=straatnaam;
		this.huisnummer=huisnummer;
		this.toevoeging="";
		this.postcode=postcode;
		this.woonplaats=woonplaats;
	}
	
	/**
	 * Creëert een adres als er wel een toevoeging bij het huisnummer hoort
	 * @param adresType het soort adres
	 * @param straatnaam de naam van de straat
	 * @param huisnummer het huisnummer
	 * @param toevoeging de toevoeging behorende bij het huisnummer
	 * @param postcode de postcode het adres
	 * @param woonplaats het dorp of de stad
	 */
	public Adres (AdresType adresType, String straatnaam, int huisnummer, String toevoeging, String postcode, String woonplaats) {
		this.adresType=adresType;
		this.straatnaam=straatnaam;
		this.huisnummer=huisnummer;
		this.toevoeging=toevoeging;
		this.postcode=postcode;
		this.woonplaats=woonplaats;
	}

	//public void setAdresNummer(int adresNummer) {
	//	this.adresNummer=adresNummer;
	//}
	public void setStraatnaam(String straatnaam) {
		this.straatnaam=straatnaam;
	}
	public void sethuisnummer(int huisnummer) {
		this.huisnummer=huisnummer;
	}
	public void setToevoeging(String toevoeging) {
		this.toevoeging=toevoeging;
	}
	public void setPostcode(String postcode) {
		this.postcode=postcode;
	}
	public void setWoonplaats(String woonplaats) {
		this.woonplaats=woonplaats;
	}
	//public int getAdresNummer() {
	//	return this.adresNummer;
	//}
	public String getStraatnaam() {
		return this.straatnaam;
	}
	public int getHuisnummer() {
		return this.huisnummer;
	}
	public String getToevoeging() {
		return this.toevoeging;
	}
	public String getPostcode () {
		return this.postcode;
	}
	public String getWoonplaats() {
		return this.woonplaats;
	}
}
