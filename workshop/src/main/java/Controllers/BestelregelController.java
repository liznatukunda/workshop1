package Controllers;

import java.math.BigDecimal;
import java.util.ArrayList;

import data.ArtikelDaoImplement;
import data.BestellingDaoImplement;
import data.BestelregelDaoImplement;
import data.KlantDaoImplement;
import domein.Artikel;
import domein.BestelRegel;
import domein.Bestelling;

public class BestelregelController {
private BestelregelDaoImplement bestelregelDao;
private ArtikelDaoImplement artikelDao;
private BestellingDaoImplement bestellingdao;
private ArrayList<Artikel>artikelen;

	public BestelregelController(){
		bestelregelDao = new BestelregelDaoImplement();
		artikelDao = new ArtikelDaoImplement();
		artikelen = artikelDao.getAlleArtikelen();
		bestellingdao= new BestellingDaoImplement();
	}
	
	
	public String voegBestelregelToe(int bestellingId, int artikelIndex, int aantal){
		Artikel artikel =artikelen.get(artikelIndex);
		BestelRegel bestelregel = new BestelRegel(aantal, bestellingId, artikel);
		bestelregel.setPrijs(artikel.getPrijs().multiply(new BigDecimal(aantal)));
		Integer id = bestelregelDao.createBestelregel(bestelregel);
		if (id>0) {
		 if (bestellingTotaalPrijsUpdate(bestelregel.getBestelling().getId())) {;
		 return "bestelregel aangemaakt en bestellingprijs bijgewerkt";
		 }
		return "Bestelregel aangemaakt bestellingprijs update mislukt";
		}
		return "Bestelregel aanmaken mislukt";
		
	}
	
	public boolean bestellingTotaalPrijsUpdate(int bestellingId){
		BigDecimal totaalPrijs = bepaalBestelregelsTotaalprijs(bestellingId);
		if (bestellingdao.updateBestellingen(totaalPrijs, bestellingId)) {
			return true;
		}
		return false;
	}
	
	public String pasBestelregelAan(int bestelregelId,int aantal, int artikelIndex){   
		BestelRegel bestelregel = bestelregelDao.getBestelRegel(bestelregelId);
		if(bestelregel == null){
			return "bestelregel niet gevonden";
		}
		Artikel artikel =artikelen.get(artikelIndex);
		bestelregel.setAantal(aantal);
		bestelregel.setArtikel(artikel);
		bestelregel.setPrijs(artikel.getPrijs().multiply(new BigDecimal(aantal)));
		if(bestelregelDao.updateBestelRegel(bestelregel)) {      
			 if (bestellingTotaalPrijsUpdate(bestelregel.getBestelling().getId())) {;
			 return "bestelregel aangemaakt en bestellingprijs bijgewerkt";
			 }
			 return "Bestelregel aangemaakt bestellingprijs update mislukt";
		}
		return "Bestelregel aanmaken mislukt";
			
	}
	
	
	
	public String deleteBestelregel(int bestelregelId){
		BestelRegel bestelregel = bestelregelDao.getBestelRegel(bestelregelId);           
		if(bestelregel == null){
			return "bestelregel niet gevonden";
		}
		int bestellingId=bestelregel.getBestelling().getId();
		if (bestelregelDao.deleteBestelRegel(bestelregel)) {
			if (bestellingTotaalPrijsUpdate(bestelregel.getBestelling().getId())) {;
			 return "bestelregel  verwijderd en bestellingprijs bijgewerkt";
			 }
			return "Bestelregel verwijderd bestellingprijs update mislukt";
		}
		return "Bestelregel verwijderen mislukt";
	}

	
	public String zoekBestelregel(int bestelregelId, int bestellingId){
		BestelRegel bestelregel = bestelregelDao.getBestelRegel(bestelregelId);          
		if(bestelregel == null){
			return null;
		}
		if (bestelregel.getBestelling().getId()!=bestellingId) {
			return null;
		}
		return (bestelregel.getId() + ": " + bestelregel.getAantal() + "‚ " + bestelregel.getPrijs() + ", " + bestelregel.getBestelling().getId()+ " " + bestelregel.getArtikel().getId());
		
	}
	
	public String[] getAlleBestelregels(){ 
		ArrayList<BestelRegel> bestelregels = bestelregelDao.getAlleBestelRegel();
		String[] returnArray = new String[bestelregels.size()];
		for(int i=0; i<bestelregels.size(); i++){
			BestelRegel bestelregel = bestelregels.get(i);	
			returnArray[i] = (bestelregel.getId() + ": " + bestelregel.getAantal() + "‚ " + bestelregel.getPrijs() + ", " + bestelregel.getBestelling().getId()+ " " + bestelregel.getArtikel().getId());
				}
		return returnArray;
	}
	public String[] zoekBestelregelsPerBestelling(int bestellingId){		
		ArrayList<BestelRegel> bestelregels = bestelregelDao.getAlleBestelregelsPerBestelling(bestellingId);
		String[] returnArray = new String[bestelregels.size()];
		for(int i=0; i<bestelregels.size(); i++){
			BestelRegel b = bestelregels.get(i);	
			returnArray[i] = (b.getId() + ": " + b.getAantal() + "‚ " + b.getPrijs() + ", " + b.getBestelling().getId()+ " " + b.getArtikel().getId());
		}
		return returnArray;
	}	
	public BigDecimal bepaalBestelregelsTotaalprijs(int bestellingId){		
		ArrayList<BestelRegel> bestelregels = bestelregelDao.getAlleBestelregelsPerBestelling(bestellingId);
		BigDecimal totaalprijs = new BigDecimal ("0");
		for(int i=0; i<bestelregels.size(); i++){
			totaalprijs = totaalprijs.add(bestelregels.get(i).getPrijs());
			
		}
		return totaalprijs;
	}	
	
}
