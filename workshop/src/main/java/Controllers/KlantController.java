package Controllers;
import java.util.ArrayList;
import data.KlantDaoImplement;
import domein.Account;
import domein.Klant;

public class KlantController {
	private static KlantDaoImplement klantDao;
	
	public KlantController(){
		klantDao = new KlantDaoImplement();
	}
	
	
	public boolean voegKlantToe(String voornaam, String tussenvoegsel, String achternaam,int accountId){
		Integer id = klantDao.createKlant(new Klant(voornaam, tussenvoegsel, achternaam),accountId);     // but the DAO create inserts the accountId(foreignkey) ??      
		return id > 0;
	}
	
	public static boolean pasVoornaamAan(int id, String voornaam,int accountId){   
		Klant Klant = klantDao.getKlant(id);
		if(Klant == null){
			return false;
		}
		Klant.setVoornaam(voornaam);
		return klantDao.updateKlant(Klant, accountId);      //  accountId  ??
	}
	
	public static boolean pasTussenvoegselAan(int id, String tussenvoegsel,int accountId){   
		Klant Klant = klantDao.getKlant(id);
		if(Klant == null){
			return false;
		}
		Klant.setVoornaam(tussenvoegsel);
		return klantDao.updateKlant(Klant, accountId);     
	}
	
	public static boolean pasAchternaamAan(int id, String achternaam,int accountId){   
		Klant Klant = klantDao.getKlant(id);
		if(Klant == null){
			return false;
		}
		Klant.setVoornaam(achternaam);
		return klantDao.updateKlant(Klant, accountId);      
		
	}
	
	public static boolean deleteKlant(int id){
		Klant klant = klantDao.getKlant(id);           
		if(klant == null){
			return false;
		}
		klant.setId(id);
		return klantDao.deleteKlant(klant); 
	}
	
	public static String zoekKlant(int id){
		Klant klant = klantDao.getKlant(id);           
		if(klant == null){
			return "";
		}
		klant.setId(id);
		return klant.getId() + ": " + klant.getVoornaam() + "‚ " + klant.getTussenvoegsel() + ", " + klant.getAchternaam();
		
	}
	
	public static String[] getAlleKlanten(){ 
		ArrayList<Klant> klanten = klantDao.getAlleKlanten();
		String[] returnArray = new String[klanten.size()];
		for(int i=0; i<klanten.size(); i++){
			Klant klant = klanten.get(i);	
			returnArray[i] = klant.getId() + ": " + klant.getVoornaam() + "‚ " + klant.getTussenvoegsel() + ", " + klant.getAchternaam();
		}
		return returnArray;
	}

}
