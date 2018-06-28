package mapper_test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

import data.ConnectieDatabase;
import data.KlantDaoImplement;
import domein.Account;
import data.AccountDaoImplement;
import domein.Klant;
import domein.Bestelling;
import data.BestellingDaoImplement;
import domein.BestelRegel;
import data.BestelregelDaoImplement;
import domein.Artikel;
import data.ArtikelDaoImplement;


import static org.junit.Assert.*;
import org.junit.*;

public class BestelRegelDaoTest {

	
	Account nieuweAccount1=new Account ("klant 1", "simpel", Account.Rol.klant);
	Klant nieuweKlant1=new Klant ("Jan", "der", "Boy");
	Bestelling bestelling1 = new Bestelling (new BigDecimal ("100.10"));
	Artikel artikel1 = new Artikel ("oude kaas", new BigDecimal("10.10"), 100);
	BestelRegel bestelregel1 = new BestelRegel(artikel1, 10);
	BestelRegel bestelregel2 = new BestelRegel(artikel1, 20);
	BestelRegel bestelregel3 = new BestelRegel(artikel1, 30);
	

	AccountDaoImplement accountdao=new AccountDaoImplement();
	KlantDaoImplement klantdao=new KlantDaoImplement();
	BestellingDaoImplement bestellingdao = new BestellingDaoImplement();
	ArtikelDaoImplement artikeldao = new ArtikelDaoImplement();
	BestelregelDaoImplement bestelregeldao = new BestelregelDaoImplement();
	
	
	
	@Before
	public void setUp(){
		accountdao.createAccount(nieuweAccount1);
		klantdao.createKlant(nieuweKlant1,nieuweAccount1.getId());
		bestellingdao.createBestelling(bestelling1,nieuweKlant1.getId());		
		artikeldao.createArtikel(artikel1);
		
	}
	@After
	public void finish() {
		artikeldao.deleteArtikel(artikel1);
		accountdao.deleteAccount(nieuweAccount1);
		//klantdao.deleteKlant(nieuweKlant1);
		//accountdao.deleteAccount(nieuweAccount1);
		
	}
	
	
	@Test
	public void testCreateBestelregel() {
		bestelregeldao.createBestelregel(bestelregel1,bestelling1.getId(),artikel1.getId());
		bestelregeldao.createBestelregel(bestelregel2,bestelling1.getId(),artikel1.getId());
		bestelregeldao.createBestelregel(bestelregel3,bestelling1.getId(),artikel1.getId());
		
		BestelRegel actueleBestelRegel1=bestelregeldao.getBestelRegel(bestelregel1.getId());
		BestelRegel actueleBestelRegel2=bestelregeldao.getBestelRegel(bestelregel2.getId());
		BestelRegel actueleBestelRegel3=bestelregeldao.getBestelRegel(bestelregel3.getId());
		
		
		assertEquals("Bestelregel1 artikelid niet juist opgeslagen", actueleBestelRegel1.getArtikel().getId() ,bestelregel1.getArtikel().getId());
		assertEquals("Bestelregel1 aantal niet juist opgeslagen", actueleBestelRegel1.getAantal(),bestelregel1.getAantal());

		assertEquals("Bestelregel2 artikelid niet juist opgeslagen", actueleBestelRegel2.getArtikel().getId() ,bestelregel2.getArtikel().getId());
		assertEquals("Bestelregel2 aantal niet juist opgeslagen", actueleBestelRegel2.getAantal(),bestelregel2.getAantal());

		assertEquals("Bestelregel3 artikelid niet juist opgeslagen", actueleBestelRegel3.getArtikel().getId() ,bestelregel3.getArtikel().getId());
		assertEquals("Bestelregel3 aantal niet juist opgeslagen", actueleBestelRegel3.getAantal(),bestelregel3.getAantal());
		
		//bestelregeldao.deleteBestelRegel(bestelregel1);
		//bestelregeldao.deleteBestelRegel(bestelregel2);
		//bestelregeldao.deleteBestelRegel(bestelregel3);
}
/*
	@Test
	public void testGetBestelRegel() {
		
	}

	@Test
	public void testGetAlleBestelRegel() {
		
	}

	@Test
	public void testUpdateBestelRegelIntInt() {
	
	}

	@Test
	public void testUpdateBestelRegelBestelRegel() {
		
	}

	@Test
	public void testDeleteBestelRegelInt() {
		
	}

	@Test
	public void testDeleteBestelRegelBestelRegel() {
		
	}
*/
}
