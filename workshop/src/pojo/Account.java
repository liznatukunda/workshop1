package pojo;

public class Account {

	private String userNaam;
	private String password;
	private String rol;
	
	// enum voor rol moet nog gemaakt worden klant medewerker en beheerder respectievelijk
	
	public void setUserNaam(String userNaam) {
		this.userNaam=userNaam;
	}
	
	public void setPassword(String password) {
		this.password=password;
	}
	
	public void setRol(String rol) {
		this.rol=rol;
	}
	
	
	public String getUserNaam() {
		return this.userNaam;
	}
	public String getPassword() {
		return this.password;
	}
	public String getRol() {
		return this.rol;
	}
}
//heel byzonder