package pojo;

public class Adres {
	private int adresNummer;
	private String straatnaam;
	private int huisnummer;
	private String toevoeging;
	private String postcode;
	private String woonplaats;
	
	//Constructors is ook nog een goed idee
	//wow
	public void setAdresNummer(int adresNummer) {
		this.adresNummer=adresNummer;
	}
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
	public int getAdresNummer() {
		return this.adresNummer;
	}
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
