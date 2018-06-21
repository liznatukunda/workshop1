// TO DO: bijvoegen opties voor klant verwijderen
// TO DO: toevoegen methode om bestelling te creëren of juist bij klasse klant???

package domein;

public class Account {
	
	public enum Rol {KLANT, MEDEWERKER, BEHEERDER}
	private int id;
	private String userNaam;
	private String password;
	private Rol rol;
	private Klant klant;
	
	/**
	 * Creëert een nieuwe account
	 * @param userNaam de gewenste gebruikersnaam om mee in te loggen
	 * @param password het gewenste wachtwoord om mee in te loggen
	 * @param rol de rol die de betreffende gebruiker krijgt, op basis waarvan diens rechten in de app worden bepaald
	 */

	
	public Account( String userNaam, String password, Rol rol) {
	//	this.id = Id;
		this.userNaam=userNaam;
		this.password=password;
		this.rol=rol;  
	}
	
	public void setId(int id) {
		this.id=id;
	}
	
	public void setUserNaam(String userNaam) {
		this.userNaam=userNaam;
	}
	
	public void setPassword(String password) {
		this.password=password;
	}
	
	public void setRol(Rol rol) {
		this.rol=rol;
	}
	public int getId() {
		return this.id;
	}
	
	
	public String getUserNaam() {
		return this.userNaam;
	}
	public String getPassword() {
		return this.password;
	}
	public Rol getRol() {
		return this.rol;
	}
	
	public void voegKlantToe(Klant klant) {
		this.klant=klant;
	}
	
	public void wijzigKlant(Klant klant, String voornaam, String tussenvoegsel, String achternaam) {
		this.klant.setVoornaam(voornaam);
		this.klant.setTussenvoegsel(tussenvoegsel);
		this.klant.setAchternaam(achternaam);
	}
	
	public void wijzigKlant(Klant klant, String voornaam, String achternaam) {
		this.klant.setVoornaam(voornaam);
		this.klant.setTussenvoegsel(null);
		this.klant.setAchternaam(achternaam);
	}
	
}
