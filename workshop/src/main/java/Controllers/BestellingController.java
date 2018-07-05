package Controllers;

import java.math.BigDecimal;
import java.util.ArrayList;

import data.BestellingDaoImplement;
import data.BestelregelDaoImplement;
import data.KlantDaoImplement;
import domein.BestelRegel;
import domein.Bestelling;
import domein.Klant;

public class BestellingController {
	
private BestellingDaoImplement bestellingDaoImplement;
private KlantDaoImplement klantDao;



	public BestellingController(){
		bestellingDaoImplement = new BestellingDaoImplement();
		klantDao = new KlantDaoImplement();
		
	}	
	public boolean voegBestellingToe(int klantId){
		Klant klant= klantDao.getKlant(klantId);
		Integer id = bestellingDaoImplement.createBestelling(new Bestelling(klant));     
		return id > 0;
	}
	


	public boolean deleteBestelling(int bestellingId, int klantId){
		Klant klant= klantDao.getKlant(klantId);
		Bestelling bestelling = bestellingDaoImplement.getBestelling(bestellingId, klantId);          
		if(bestelling == null){
			return false;
		}
		BestelregelDaoImplement brdao=new BestelregelDaoImplement();
		ArrayList<BestelRegel> bestelregellijst=brdao.getAlleBestelregelsPerBestelling(bestellingId);
		BestelregelController brContr=new BestelregelController();
		for (int index = bestelregellijst.size(); index>=0;index--) {
			int bestelregelId=bestelregellijst.get(index).getBestellingId();
			brContr.deleteBestelregel(bestelregelId);
		}
		
		return bestellingDaoImplement.deleteBestellingen(bestelling);
	}
	
	
	public String zoekBestelling(int bestellingId, int klantId){		
	Klant klant= klantDao.getKlant(klantId);
	Bestelling bestelling = bestellingDaoImplement.getBestelling(bestellingId, klantId);           
	if(bestelling == null){
		return "bestelling niet gevonden ";
	}
	if(bestelling.GetKlantId()!=klantId) {
		return "bestellingId is niet van huidige klant ";
	}
	return ("bestellingnummer: " + bestelling.getId() + " totaalprijs: " + bestelling.getTotaalPrijs() + "‚ klantnummer: " + bestelling.GetKlantId());
	
}
	

	public String[] zoekBestellingenPerKlant(int klantId){	
		Klant klant= klantDao.getKlant(klantId);
		ArrayList<Bestelling> bestellingen = bestellingDaoImplement.getAlleBestellingenPerKlant(klant);
		String[] returnArray = new String[bestellingen.size()];
		for(int i=0; i<bestellingen.size(); i++){
			Bestelling b = bestellingen.get(i);	
			returnArray[i] = "bestellingnummer: " + b.getId() + " prijs: " + b.getTotaalPrijs()+ " , klantnummer: " + b.GetKlantId(); 
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
