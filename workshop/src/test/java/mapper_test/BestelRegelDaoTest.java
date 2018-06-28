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
		bestelregeldao.createBestelregel(bestelregel1,bestelling1.getBestellingNummer(),artikel1.getId());
		bestelregeldao.createBestelregel(bestelregel2,bestelling1.getBestellingNummer(),artikel1.getId());
		bestelregeldao.createBestelregel(bestelregel3,bestelling1.getBestellingNummer(),artikel1.getId());
		
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

	
	@Test
	public void testGetAlleBestelRegel() {
		ArrayList<BestelRegel> actueleWaarden = bestelregeldao.getAlleBestelRegel();
		ArrayList <BestelRegel> testlijst=new ArrayList <>();
		testlijst.add(bestelregel1);
		testlijst.add(bestelregel2);
		testlijst.add(bestelregel3);
		
		for (int i=0; i<testlijst.size();i++) {	
			assertEquals("bestelling id niet juist opgeslagen", actueleWaarden.get(i).getArtikel().getId(),testlijst.get(i).getArtikel().getId());
			assertEquals("bestelling aantal niet juist opgeslagen", actueleWaarden.get(i).getAantal(),testlijst.get(i).getAantal());
		}
		
	}

	@Test
	public void testUpdateBestelRegelIntInt() {
		boolean updatesucces = bestelregeldao.updateBestelRegel(10005, bestelregel1.getId());
		assertTrue("klant niet ge-update", updatesucces);
	}

	@Test
	public void testUpdateBestelRegelBestelRegel() {
		bestelregel1.setAantal(50);
		boolean updatesucces = bestelregeldao.updateBestelRegel(bestelregel1);
		assertTrue("bestelregel niet ge-update", updatesucces);
	}

	@Test
	public void testDeleteBestelRegelInt() {
		bestelregeldao.createBestelregel(bestelregel1, bestelling1.getBestellingNummer(),artikel1.getId());
		boolean deletesucces = bestelregeldao.deleteBestelRegel(bestelregel1.getId());
		 assertTrue("bestelling 1 niet deleted",deletesucces);
	}

	@Test
	public void testDeleteBestelRegelBestelRegel() {
		bestelregeldao.createBestelregel(bestelregel1, bestelling1.getBestellingNummer(),artikel1.getId());
		boolean deletesucces = bestelregeldao.deleteBestelRegel(bestelregel1);
		 assertTrue("bestelling 1 niet deleted",deletesucces);
	}

}
