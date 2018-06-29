package Controllers;
import java.util.ArrayList;
import data.KlantDaoImplement;
import domein.Klant;

public class KlantController {
	private static KlantDaoImplement klantDaoImplement;
	
	public KlantController(){
		klantDaoImplement = new KlantDaoImplement();
	}
	
	public boolean voegKlantToe(String voornaam, String tussenvoegsel, String achternaam,int accountId){
		Integer id = klantDaoImplement.createKlant(new Klant(voornaam, tussenvoegsel, achternaam),accountId);     
		return id > 0;
	}
	
	public static boolean pasVoornaamAan(int id, String voornaam,int accountId){   
		Klant Klant = klantDaoImplement.getKlant(id);
		if(Klant == null){
			return false;
		}
		Klant.setVoornaam(voornaam);
		return klantDaoImplement.updateKlant(Klant,accountId);      
	}
	
	public static boolean pasTussenvoegselAan(int id, String tussenvoegsel,int accountId){   
		Klant Klant = klantDaoImplement.getKlant(id);
		if(Klant == null){
			return false;
		}
		Klant.setVoornaam(tussenvoegsel);
		return klantDaoImplement.updateKlant(Klant,accountId);     
	}
	
	public static boolean pasAchternaamAan(int id, String achternaam,int accountId){   
		Klant Klant = klantDaoImplement.getKlant(id);
		if(Klant == null){
			return false;
		}
		Klant.setVoornaam(achternaam);
		return klantDaoImplement.updateKlant(Klant,accountId);      
		
	}
	
	public static boolean deleteKlant(int id){
		Klant klant = klantDaoImplement.getKlant(id);           
		if(klant == null){
			return false;
		}
		klant.setId(id);
		return klantDaoImplement.deleteKlant(klant); 
	}
	
	
	public static boolean zoekKlant(int id,int accountId){				
		klantDaoImplement.getKlant(id);
		 return true;
	} 
	
	
	public static String[] getAlleKlanten(){ 
		ArrayList<Klant> klanten = klantDaoImplement.getAlleKlanten();
		String[] returnArray = new String[klanten.size()];
		for(int i=0; i<klanten.size(); i++){
			Klant klant = klanten.get(i);	
			returnArray[i] = klant.getId() + ": " + klant.getVoornaam() + "‚ " + klant.getTussenvoegsel() + ", " + klant.getAchternaam();
		}
		return returnArray;
	}

}
