package Controllers;

import java.math.BigDecimal;
import java.util.ArrayList;

import data.BestellingDaoImplement;
import data.KlantDaoImplement;
import domein.Account;
import domein.Bestelling;
import domein.Klant;

public class BestellingController {
	
private BestellingDaoImplement bestellingDaoImplement;
private KlantDaoImplement klantDao;

private ArrayList <Bestelling> bestellingen;


	public BestellingController(){
		bestellingDaoImplement = new BestellingDaoImplement();
		klantDao = new KlantDaoImplement();
		
	}	
	public boolean voegBestellingToe(int klantId){
		Klant klant= klantDao.getKlant(klantId);
		Integer id = bestellingDaoImplement.createBestelling(new Bestelling(klant));     
		return id > 0;
	}
	

/*	public boolean pasPrijsAan(int id,BigDecimal totaalPrijs){   
		Bestelling bestelling = bestellingDaoImplement.getBestelling(id);
		if(bestelling == null){
			return false;
		}
		bestelling.bepaalTotaalPrijs();
	// totaalprijs moet berekend worden met bepaaltotaalprijs waarbij alle bestelregels doorlopen worden
		return bestellingDaoImplement.updateBestellingen(bestelling);               
	}	
*/	
	public boolean deleteBestelling(int bestellingId, int klantId){
		Klant klant= klantDao.getKlant(klantId);
		Bestelling bestelling = bestellingDaoImplement.getBestelling(klant);          
		if(bestelling == null){
			return false;
		}
		return bestellingDaoImplement.deleteBestellingen(bestelling);
	}
	
	
	public String zoekBestelling(int bestellingId, int klantId){		
	Klant klant= klantDao.getKlant(klantId);
	Bestelling bestelling = bestellingDaoImplement.getBestelling(klant);           
	if(bestelling == null){
		return "bestelling niet gevonden ";
	}
	if(bestelling.GetKlantId()!=klantId) {
		return "bestellingId is niet van huidige klant ";
	}
	return (bestelling.getId() + ": " + bestelling.getTotaalPrijs() + "‚ " + bestelling.GetKlantId());
	
}
	

	public String[] zoekBestellingenPerKlant(int klantId){	
		Klant klant= klantDao.getKlant(klantId);
		ArrayList<Bestelling> bestellingen = bestellingDaoImplement.getAlleBestellingenPerKlant(klant);
		String[] returnArray = new String[bestellingen.size()];
		for(int i=0; i<bestellingen.size(); i++){
			Bestelling b = bestellingen.get(i);	
			returnArray[i] = b.getId() + ": " + b.getTotaalPrijs()+ " " + b.GetKlantId(); 
		}
		return returnArray;
	}	
	
	
	
/*	public String[] getAlleBestelling(){ 
		bestellingen = bestellingDaoImplement.getAlleBestelling();
		String[] returnArray = new String[bestellingen.size()];
		for(int i=0; i<bestellingen.size(); i++){
			Bestelling b = bestellingen.get(i);	
			returnArray[i] = i + ": " + b.getTotaalPrijs() + ", " + b.GetKlantId(); 
		}
		return returnArray;
	}
*/
}
