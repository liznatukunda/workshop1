package Controllers;

import java.math.BigDecimal;
import java.util.List;

import domein.Artikel;
import data.ArtikelDao;

public class ArtikelController {
private ArtikelDao artikelDao;
	
	public ArtikelController(){
		artikelDao = new ArtikelDao();
	}
	
	public String[] getAlleArtikelen(){
		List<Artikel> artikelen = artikelDao.getAlleArtikelen();
		String[] returnArray = new String[artikelen.size()];
		for(int i=0; i<artikelen.size(); i++){
			Artikel a = artikelen.get(i);
			String inVoorraad = a.getVoorraad() > 0 ? a.getVoorraad() + " in voorraad." : "UITVERKOCHT!";
			returnArray[i] = a.getId() + ": " + a.getNaam() + ". €" + a.getPrijs().toPlainString() + " " + inVoorraad;
		}
		return returnArray;
	}
	
	public boolean voegArtikelToe(String naam, BigDecimal prijs, Integer voorraad){
		Integer id = artikelDao.createArtikel(new Artikel(naam, prijs, voorraad));
		return id > 0;
	}
	
	public boolean pasNaamAan(int id, String naam){
		Artikel artikel = artikelDao.getArtikel(id);
		if(artikel == null){
			return false;
		}
		artikel.setNaam(naam);
		return artikelDao.updateArtikel(artikel);
	}
	
	public boolean pasPrijsAan(int id, BigDecimal prijs){
		Artikel artikel = artikelDao.getArtikel(id);
		if(artikel == null){
			return false;
		}
		artikel.setPrijs(prijs);
		return artikelDao.updateArtikel(artikel);
	}
	
	public boolean pasVoorraadAan(int id, int aantal){
		Artikel artikel = artikelDao.getArtikel(id);
		if(artikel == null){
			return false;
		}
		artikel.setVoorraad(aantal);
		return artikelDao.updateArtikel(artikel);
	}
}
