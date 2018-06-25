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

import static org.junit.Assert.*;
import org.junit.*;

public class BestellingMapperTest {

	Account nieuweAccount1=new Account ("klant 1", "simpel", Account.Rol.klant);
	Klant nieuweKlant1=new Klant ("Jan", "der", "Boy");
	Bestelling bestelling1 = new Bestelling (new BigDecimal ("100.10"));
	Bestelling bestelling2 = new Bestelling (new BigDecimal ("200.20"));
	Bestelling bestelling3 = new Bestelling (new BigDecimal ("300.30"));
	
	static ConnectieDatabase cdb=new ConnectieDatabase();
	AccountDao adao=new AccountDao();
	KlantDao kdao=new KlantDao();
	BestellingMapper bdao = new BestellingMapper();
	
	@BeforeClass
	public static void initialiseer() throws SQLException {
		cdb.maakVerbinding();
	}
	
		@Before
		public void setUp(){
			adao.createAccount(nieuweAccount1);
			kdao.createKlant(nieuweKlant1,nieuweAccount1.getId());
			bdao.createBestelling(bestelling1,nieuweKlant1.getId());
			bdao.createBestelling(bestelling2,nieuweKlant1.getId());
			bdao.createBestelling(bestelling3,nieuweKlant1.getId());
		}
		
		@After
		public void finish() {
			adao.deleteAccount(nieuweAccount1);
		}
		
		@AfterClass
		public static void close() {
			
			cdb.sluitAf();
		}
	
	
	@Test
	public void testCreateBestelling() {
		Bestelling actueleBestelling1=bdao.getBestelling(bestelling1.getBestellingNummer());
		Bestelling actueleBestelling2=bdao.getBestelling(bestelling2.getBestellingNummer());
		Bestelling actueleBestelling3=bdao.getBestelling(bestelling3.getBestellingNummer());
		
		assertEquals("Bestelling1 totaalprijs niet juist opgeslagen", actueleBestelling1.getTotaalPrijs() ,bestelling1.getTotaalPrijs());
		assertEquals("Bestelling1 id niet juist opgeslagen", actueleBestelling1.getBestellingNummer(),bestelling1.getBestellingNummer());

		assertEquals("Bestelling2 totaalprijs niet juist opgeslagen", actueleBestelling2.getTotaalPrijs() ,bestelling2.getTotaalPrijs());
		assertEquals("Bestelling2 id niet juist opgeslagen", actueleBestelling2.getBestellingNummer(),bestelling2.getBestellingNummer());

		assertEquals("Bestelling3 totaalprijs niet juist opgeslagen", actueleBestelling3.getTotaalPrijs() ,bestelling3.getTotaalPrijs());
		assertEquals("Bestelling3 id niet juist opgeslagen", actueleBestelling3.getBestellingNummer(),bestelling3.getBestellingNummer());
	}

	@Test
	public void testGetAlleBestelling() {
		ArrayList<Bestelling> actueleWaarden = bdao.getAlleBestelling();
		ArrayList <Bestelling> testlijst=new ArrayList <>();
		testlijst.add(bestelling1);
		testlijst.add(bestelling2);
		testlijst.add(bestelling3);
		
		for (int i=0; i<testlijst.size();i++) {	
			assertEquals("bestelling totaalprijs niet juist opgeslagen", actueleWaarden.get(i).getTotaalPrijs(),testlijst.get(i).getTotaalPrijs());
			assertEquals("bestelling id niet juist opgeslagen", actueleWaarden.get(i).getBestellingNummer(),testlijst.get(i).getBestellingNummer());
		}
		
	}

	@Test
	public void testUpdateBestellingenBigDecimalInt() {
		boolean updatesucces = bdao.updateBestellingen(new BigDecimal ("999.99"), bestelling1.getBestellingNummer());
		assertTrue("klant niet ge-update", updatesucces);
	}

	@Test
	public void testUpdateBestellingenBestelling() {
		bestelling1.setTotaalPrijs(new BigDecimal ("888.88"));
		boolean updatesucces = bdao.updateBestellingen(bestelling1);
		assertTrue("klant niet ge-update", updatesucces);
	}

	@Test
	public void testDeleteBestellingenInt() {
		bdao.createBestelling(bestelling1, nieuweKlant1.getId());
		boolean deletesucces = bdao.deleteBestellingen(bestelling1.getBestellingNummer());
		 assertTrue("bestelling 1 niet deleted",deletesucces);
	}

	@Test
	public void testDeleteBestellingenBestelling() {
		bdao.createBestelling(bestelling1, nieuweKlant1.getId());
		boolean deletesucces = bdao.deleteBestellingen(bestelling1);
		 assertTrue("bestelling 1 niet deleted",deletesucces);
	}

}
