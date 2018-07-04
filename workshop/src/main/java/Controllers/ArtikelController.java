package Controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import domein.Artikel;
import data.ArtikelDaoImplement;

public class ArtikelController {
private ArtikelDaoImplement artikelDao;
private ArrayList<Artikel>artikelen;
	
	public ArtikelController(){
		artikelDao = new ArtikelDaoImplement();
		artikelen = artikelDao.getAlleArtikelen();
	}
	
	public String[] getAlleArtikelen(){
		ArrayList<Artikel> artikelen = artikelDao.getAlleArtikelen();
		String[] returnArray = new String[artikelen.size()];
		for(int i=0; i<artikelen.size(); i++){
			Artikel a = artikelen.get(i);
			String inVoorraad = a.getVoorraad() > 0 ? a.getVoorraad() + " in voorraad." : "UITVERKOCHT!";
			returnArray[i] = "artikelnummer: "+ i + ": " + a.getNaam() + ". €" + a.getPrijs().toPlainString() + " " + inVoorraad;
		}
		return returnArray;
	}
	
	public boolean voegArtikelToe(String naam, BigDecimal prijs, int voorraad){
		Integer id = artikelDao.createArtikel(new Artikel(naam, prijs, voorraad));
		artikelen = artikelDao.getAlleArtikelen();
		return id > 0;
	}
	
	public boolean pasNaamAan(int index, String naam){
		Artikel artikel = artikelen.get(index);
		artikel = artikelDao.getArtikel(artikel.getId());
		if(artikel == null){
			return false;
		}
		artikel.setNaam(naam);
		return artikelDao.updateArtikel(artikel);
	}
	
	public boolean pasPrijsAan(int index, BigDecimal prijs){
		Artikel artikel = artikelen.get(index);
		artikel = artikelDao.getArtikel(artikel.getId());
		if(artikel == null){
			return false;
		}
		artikel.setPrijs(prijs);
		return artikelDao.updateArtikel(artikel);
	}
	
	public boolean pasVoorraadAan(int index, int aantal){
		Artikel artikel = artikelen.get(index);
		artikel = artikelDao.getArtikel(artikel.getId());
		if(artikel == null){
			return false;
		}
		artikel.setVoorraad(aantal);
		return artikelDao.updateArtikel(artikel);
	}


	public String zoekArtikel(int index) {
		Artikel artikel = artikelen.get(index);
		artikel = artikelDao.getArtikel(artikel.getId());
		if (artikel==null) {
			return null;
		}
		else {
		String  returnstring = ("" + index +" "+ artikel.getNaam()+" " + artikel.getPrijs()+" " + artikel.getVoorraad());
		return returnstring;
		}
	}

	public  boolean deleteArtikel(int index){
		Artikel artikel = artikelen.get(index);
		artikel = artikelDao.getArtikel(artikel.getId());
		if(artikel == null){
			return false;
		}
		if( artikelDao.deleteArtikel(artikel)) {
			artikelen = artikelDao.getAlleArtikelen();
			return true; 
		}
		return false;
	}
}
