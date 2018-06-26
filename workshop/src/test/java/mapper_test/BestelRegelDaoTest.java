package mapper_test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

import data.ConnectieDatabase;
import data.KlantDao;
import domein.Account;
import data.AccountDao;
import domein.Klant;
import domein.Bestelling;
import data.BestellingMapper;
import domein.BestelRegel;
import data.BestelregelMapper;
import domein.Artikel;
import data.ArtikelDao;


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
	
	static ConnectieDatabase cdb=new ConnectieDatabase();
	AccountDao accountdao=new AccountDao();
	KlantDao klantdao=new KlantDao();
	BestellingMapper bestellingdao = new BestellingMapper();
	ArtikelDao artikeldao = new ArtikelDao();
	BestelregelMapper bestelregeldao = new BestelregelMapper();
	
	@BeforeClass
	public static void initialiseer() throws SQLException {
		cdb.maakVerbinding();
		
		
	}
	
	@Before
	public void setUp(){
		accountdao.createAccount(nieuweAccount1);
		klantdao.createKlant(nieuweKlant1,nieuweAccount1.getId());
		bestellingdao.createBestelling(bestelling1,nieuweKlant1.getId());		
		artikeldao.createArtikel(artikel1);
		
	}
	@After
	public void finish() {
		//artikeldao.deleteArtikel(artikel1);
		bestellingdao.deleteBestellingen(bestelling1);
		//klantdao.deleteKlant(nieuweKlant1);
		//accountdao.deleteAccount(nieuweAccount1);
		
	}
	@AfterClass
	public static void close() {
		
		cdb.sluitAf();
	}
	
	
	@Test
	public void testCreateBestelregel() {
		bestelregeldao.createBestelregel(bestelregel1,bestelling1.getBestellingNummer(),artikel1.getId());
		bestelregeldao.createBestelregel(bestelregel2,bestelling1.getBestellingNummer(),artikel1.getId());
		bestelregeldao.createBestelregel(bestelregel3,bestelling1.getBestellingNummer(),artikel1.getId());
		
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
