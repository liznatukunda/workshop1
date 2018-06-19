package pojo;

public class Account {
	
	public enum Rol {KLANT, MEDEWERKER, BEHEERDER}
	
	private String userNaam;
	private String password;
	private Rol rol;
	
	/**
	 * Creëert een nieuwe account
	 * @param userNaam de gewenste gebruikersnaam om mee in te loggen
	 * @param password het gewenste wachtwoord om mee in te loggen
	 * @param rol de rol die de betreffende gebruiker krijgt, op basis waarvan diens rechten in de app worden bepaald
	 */
	public Account(String userNaam, String password, Rol rol) {
		this.userNaam=userNaam;
		this.password=password;
		this.rol=rol;
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
	
	
	public String getUserNaam() {
		return this.userNaam;
	}
	public String getPassword() {
		return this.password;
	}
	public Rol getRol() {
		return this.rol;
	}
}
