package Controllers;

import java.math.BigDecimal;
import java.util.ArrayList;

import data.BestellingDaoImplement;
import domein.Bestelling;

public class BestellingConntroller {
	
private static BestellingDaoImplement bestellingDaoImplement;
	
	public BestellingConntroller(){
		bestellingDaoImplement = new BestellingDaoImplement();
	}	
	public boolean voegBestellingToe(BigDecimal totaalPrijs,int klantid){
		Integer id = bestellingDaoImplement.createBestelling(new Bestelling(totaalPrijs),klantid);     
		return id > 0;
	}
	
	public static boolean pasPrijsAan(int id,BigDecimal totaalPrijs){   
		Bestelling bestelling = bestellingDaoImplement.getBestelling(id);
		if(bestelling == null){
			return false;
		}
		bestelling.setTotaalPrijs(totaalPrijs);
		return bestellingDaoImplement.updateBestellingen(bestelling);               
	}	
	
	public static boolean deleteBestelling(int id){
		Bestelling bestelling = bestellingDaoImplement.getBestelling(id);          
		if(bestelling == null){
			return false;
		}
		bestelling.setBestellingNummer(id);
		return bestellingDaoImplement.deleteBestellingen(bestelling);
	}
	
	
	public static boolean zoekBestelling(int id){		
		bestellingDaoImplement.getBestelling(id);
		 return true;
	} 
	
	
	public static String[] getAlleBestelling(){ 
		ArrayList<Bestelling> bestelling = bestellingDaoImplement.getAlleBestelling();
		String[] returnArray = new String[bestelling.size()];
		for(int i=0; i<bestelling.size(); i++){
			Bestelling b = bestelling.get(i);	
			returnArray[i] = b.getBestellingNummer() + ": " + b.getTotaalPrijs(); 
		}
		return returnArray;
	}

}
