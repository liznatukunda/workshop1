package Controllers;

import java.math.BigDecimal;
import java.util.ArrayList;

import data.BestellingDaoImplement;
import domein.Account;
import domein.Bestelling;
import domein.Klant;

public class BestellingController {
	
private BestellingDaoImplement bestellingDaoImplement;
private ArrayList <Bestelling> bestellingen;


	public BestellingController(){
		bestellingDaoImplement = new BestellingDaoImplement();
	}	
	public boolean voegBestellingToe(int klantid){
		Integer id = bestellingDaoImplement.createBestelling(new Bestelling(klantid));     
		return id > 0;
	}
	
	public boolean pasPrijsAan(int id,BigDecimal totaalPrijs){   
		Bestelling bestelling = bestellingDaoImplement.getBestelling(id);
		if(bestelling == null){
			return false;
		}
		bestelling.bepaalTotaalPrijs();
	// totaalprijs moet berekend worden met bepaaltotaalprijs waarbij alle bestelregels doorlopen worden
		return bestellingDaoImplement.updateBestellingen(bestelling);               
	}	
	
	public boolean deleteBestelling(int id){
		Bestelling bestelling = bestellingDaoImplement.getBestelling(id);          
		if(bestelling == null){
			return false;
		}
		bestelling.setId(id);
		return bestellingDaoImplement.deleteBestellingen(bestelling);
	}
	
	
	public String zoekBestelling(int id){		
		
	Bestelling bestelling = bestellingDaoImplement.getBestelling(id);           
	if(bestelling == null){
		return "";
	}
	return (bestelling.getId() + ": " + bestelling.getTotaalPrijs() + "‚ " + bestelling.GetKlantId());
	
}
	

	public String[] zoekBestellingenPerKlant(int klantId){		
		ArrayList<Bestelling> bestellingen = bestellingDaoImplement.getAlleBestellingenPerKlant(klantId);
		String[] returnArray = new String[bestellingen.size()];
		for(int i=0; i<bestellingen.size(); i++){
			Bestelling b = bestellingen.get(i);	
			returnArray[i] = b.getId() + ": " + b.getTotaalPrijs()+ " " + b.GetKlantId(); 
		}
		return returnArray;
	}	
	
	
	
	public String[] getAlleBestelling(){ 
		bestellingen = bestellingDaoImplement.getAlleBestelling();
		String[] returnArray = new String[bestellingen.size()];
		for(int i=0; i<bestellingen.size(); i++){
			Bestelling b = bestellingen.get(i);	
			returnArray[i] = i + ": " + b.getTotaalPrijs() + ", " + b.GetKlantId(); 
		}
		return returnArray;
	}

}
