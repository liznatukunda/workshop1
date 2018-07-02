package Controllers;

import java.util.ArrayList;

import data.ArtikelDaoImplement;
import data.BestelregelDaoImplement;
import data.KlantDaoImplement;
import domein.Artikel;
import domein.BestelRegel;
import domein.Bestelling;

public class BestelregelController {
private BestelregelDaoImplement bestelregelDao;
private ArtikelDaoImplement artikelDao;
	
	public BestelregelController(){
		bestelregelDao = new BestelregelDaoImplement();
		artikelDao = new ArtikelDaoImplement(); // mag je hiet wel artikeldao aanroepen
	}
	
	
	public boolean voegBestelregelToe(int bestellingId, int artikelId, int aantal){
		Artikel artikel= artikelDao.getArtikel(artikelId);// moet dit niet anders
		Integer id = bestelregelDao.createBestelregel(new BestelRegel(bestellingId, artikel, aantal));        
		return id > 0;
	}
	
	public boolean pasAantalAan(int id,int aantal){   
		BestelRegel bestelregel = bestelregelDao.getBestelRegel(id);
		if(bestelregel == null){
			return false;
		}
		bestelregel.setAantal(aantal);
		return bestelregelDao.updateBestelRegel(bestelregel);      
	}
	
	
	public boolean deleteBestelregel(int id){
		BestelRegel bestelregel = bestelregelDao.getBestelRegel(id);           
		if(bestelregel == null){
			return false;
		}
		bestelregel.setId(id);
		return bestelregelDao.deleteBestelRegel(bestelregel); 
	}
	
	public String zoekBestelregel(int id){
		BestelRegel bestelregel = bestelregelDao.getBestelRegel(id);          
		if(bestelregel == null){
			return "";
		}
		bestelregel.setId(id);
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

}
