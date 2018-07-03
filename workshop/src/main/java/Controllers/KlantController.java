package Controllers;
import java.util.ArrayList;
import data.KlantDaoImplement;
import domein.Account;
import domein.Klant;

public class KlantController {
	private KlantDaoImplement klantDao;
	
	public KlantController(){
		klantDao = new KlantDaoImplement();
	}
	
	public Klant getKlant(int klantId) {
		Klant klant = klantDao.getKlant(klantId);           
		
		return klant;
	}
	
	public boolean voegKlantToe(String voornaam, String tussenvoegsel, String achternaam,int accountId){
		Integer id = klantDao.createKlant(new Klant(voornaam, tussenvoegsel, achternaam, accountId));        
		return id > 0;
	}
	
	public boolean pasVoornaamAan(int id, String voornaam){   
		Klant klant = klantDao.getKlant(id);
		if(klant == null){
			return false;
		}
		klant.setVoornaam(voornaam);
		return klantDao.updateKlant(klant);      
	}
	
	public boolean pasTussenvoegselAan(int id, String tussenvoegsel){   
		Klant Klant = klantDao.getKlant(id);
		if(Klant == null){
			return false;
		}
		Klant.setTussenvoegsel(tussenvoegsel);
		return klantDao.updateKlant(Klant);     
	}
	
	public boolean pasAchternaamAan(int id, String achternaam){   
		Klant Klant = klantDao.getKlant(id);
		if(Klant == null){
			return false;
		}
		Klant.setVoornaam(achternaam);
		return klantDao.updateKlant(Klant);      
		
	}
	
	public boolean deleteKlant(int id){
		Klant klant = klantDao.getKlant(id);           
		if(klant == null){
			return false;
		}
		klant.setId(id);
		return klantDao.deleteKlant(klant); 
	}
	
	public String zoekKlant(int id){
		Klant klant = klantDao.getKlant(id);           
		if(klant == null){
			return "";
		}
		klant.setId(id);
		return (klant.getId() + ": " + klant.getVoornaam() + "‚ " + klant.getTussenvoegsel() + ", " + klant.getAchternaam() + " " + klant.getAccountId());
		
	}
	
	public String[] getAlleKlanten(){ 
		ArrayList<Klant> klanten = klantDao.getAlleKlanten();
		String[] returnArray = new String[klanten.size()];
		for(int i=0; i<klanten.size(); i++){
			Klant klant = klanten.get(i);	
			returnArray[i] = klant.getId() + ": " + klant.getVoornaam() + "‚ " + klant.getTussenvoegsel() + ", " + klant.getAchternaam() + ", " + klant.getAccountId();
		}
		return returnArray;
	}

}
