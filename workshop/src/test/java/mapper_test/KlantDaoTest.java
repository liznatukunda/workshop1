package mapper_test;

import java.sql.SQLException;
import java.util.ArrayList;

import data.ConnectieDatabase;
import domein.Account;
import data.AccountDao;
import domein.Klant;
import data.KlantDao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


import org.junit.*;

public class KlantDaoTest {

	Account nieuweAccount1=new Account ("klant 1", "simpel", Account.Rol.klant);
	Klant nieuweKlant1=new Klant ("Jan", "der", "Boy");
	Klant nieuweKlant2=new Klant ("Piet", "van", "Smit");
	Klant nieuweKlant3=new Klant ("Joris", "de", "Ridder");
	Klant nieuweKlant4=new Klant ("Corneel", "Graaf");
	
	
	static ConnectieDatabase cdb=new ConnectieDatabase();
	AccountDao adao=new AccountDao();
	KlantDao kdao=new KlantDao();
	
	@BeforeClass
	public static void initialiseer() throws SQLException {
		cdb.maakVerbinding();
		
		
	}
	
	@Before
	public void setUp(){
		adao.createAccount(nieuweAccount1);
		kdao.createKlant(nieuweKlant1,nieuweAccount1.getId());
		kdao.createKlant(nieuweKlant2,nieuweAccount1.getId());
		kdao.createKlant(nieuweKlant3,nieuweAccount1.getId());
		kdao.createKlant(nieuweKlant4,nieuweAccount1.getId());
	}
	
	@After
	public void finish() {
		adao.deleteAccount(nieuweAccount1);
		//kdao.deleteKlant(nieuweKlant1);
		//kdao.deleteKlant(nieuweKlant2);
		//kdao.deleteKlant(nieuweKlant3);
		//kdao.deleteKlant(nieuweKlant4);
		
		
	}
	
	@AfterClass
	public static void close() {
		
		cdb.sluitAf();
		
	}
	
	@Test
	public void testCreateKlant() {
		Klant actueleKlant1=kdao.getKlant(nieuweKlant1.getId());
		Klant actueleKlant2=kdao.getKlant(nieuweKlant2.getId());
		Klant actueleKlant3=kdao.getKlant(nieuweKlant3.getId());
		Klant actueleKlant4=kdao.getKlant(nieuweKlant4.getId());
		
		assertEquals("Klant1 voornaam niet juist opgeslagen", actueleKlant1.getVoornaam(),nieuweKlant1.getVoornaam());
		assertEquals("Klant1 tussenvoegsel niet juist opgeslagen", actueleKlant1.getTussenvoegsel(),nieuweKlant1.getTussenvoegsel());
		assertEquals("Klant1 achternaam niet juist opgeslagen", actueleKlant1.getAchternaam() ,nieuweKlant1.getAchternaam());
		assertEquals("Klant1 id niet juist opgeslagen", actueleKlant1.getId(),nieuweKlant1.getId());
		
		assertEquals("Klant2 voornaam niet juist opgeslagen", actueleKlant2.getVoornaam(),nieuweKlant2.getVoornaam());
		assertEquals("Klant2 tussenvoegsel niet juist opgeslagen", actueleKlant2.getTussenvoegsel(),nieuweKlant2.getTussenvoegsel());
		assertEquals("Klant2 achternaam niet juist opgeslagen", actueleKlant2.getAchternaam() ,nieuweKlant2.getAchternaam());
		assertEquals("Klant2 id niet juist opgeslagen", actueleKlant2.getId(),nieuweKlant2.getId());
		
		assertEquals("Klant3 voornaam niet juist opgeslagen", actueleKlant3.getVoornaam(),nieuweKlant3.getVoornaam());
		assertEquals("Klant3 tussenvoegsel niet juist opgeslagen", actueleKlant3.getTussenvoegsel(),nieuweKlant3.getTussenvoegsel());
		assertEquals("Klant3 achternaam niet juist opgeslagen", actueleKlant3.getAchternaam() ,nieuweKlant3.getAchternaam());
		assertEquals("Klant3 id niet juist opgeslagen", actueleKlant3.getId(),nieuweKlant3.getId());
		
		assertEquals("Klant4 voornaam niet juist opgeslagen", actueleKlant4.getVoornaam(),nieuweKlant4.getVoornaam());
		assertEquals("Klant4 tussenvoegsel niet juist opgeslagen", actueleKlant4.getTussenvoegsel(),nieuweKlant4.getTussenvoegsel());
		assertEquals("Klant4 achternaam niet juist opgeslagen", actueleKlant4.getAchternaam() ,nieuweKlant4.getAchternaam());
		assertEquals("Klant4 id niet juist opgeslagen", actueleKlant4.getId(),nieuweKlant4.getId());
	}

	@Test
	public void testGetKlant() {
		;
	}

	@Test
	public void testGetAlleKlanten() {
		;
	}

	@Test
	public void testUpdateKlantStringStringStringIntInt() {
		;
	}

	@Test
	public void testUpdateKlantKlantInt() {
		;
	}

	@Test
	public void testDeleteKlantInt() {
		;
	}

	@Test
	public void testDeleteKlantKlant() {
		;
	}

}
