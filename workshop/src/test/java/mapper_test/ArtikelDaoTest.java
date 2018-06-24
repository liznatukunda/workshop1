package mapper_test;

import java.sql.SQLException;
import java.util.ArrayList;

import data.ConnectieDatabase;
import domein.Account;
import domein.Artikel;
import data.ArtikelDao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

import org.junit.*;

public class ArtikelDaoTest {

	
	Artikel nieuweArtikel1=new Artikel ("Oude kaas", new BigDecimal (7.20), 100);
	Artikel nieuweArtikel2=new Artikel ("Belegen kaas", new BigDecimal (6.20), 100);
	Artikel nieuweArtikel3=new Artikel ("Jonge kaas", new BigDecimal (5.20), 100);
	
		
	
	
	static ConnectieDatabase cdb=new ConnectieDatabase();
	ArtikelDao adao=new ArtikelDao();
	
	@BeforeClass
	public static void initialiseer() throws SQLException {
		cdb.maakVerbinding();
	}
	
	@Before
	public void setUp(){
		adao.createArtikel(nieuweArtikel1);
		adao.createArtikel(nieuweArtikel2);
		adao.createArtikel(nieuweArtikel3);
	}
	

	@After
	public void finish() {
		adao.deleteArtikel(nieuweArtikel1);
		adao.deleteArtikel(nieuweArtikel2);
		adao.deleteArtikel(nieuweArtikel3);
		
	}
	
	@AfterClass
	public static void close() {
		cdb.sluitAf();
		
	}
	
	@Test
	public void testCreateArtikel() {
		
		
		Artikel actueleArtikel1=adao.getArtikel(nieuweArtikel1.getId());
		Artikel actueleArtikel2=adao.getArtikel(nieuweArtikel2.getId());
		Artikel actueleArtikel3=adao.getArtikel(nieuweArtikel3.getId());
		
		assertEquals("Artikel1 naam niet juist opgeslagen", actueleArtikel1.getNaam(),nieuweArtikel1.getNaam());
		assertEquals("Artikel1 id niet juist opgeslagen", actueleArtikel1.getId(),nieuweArtikel1.getId());
		assertEquals("Artikel1 prijs niet juist opgeslagen", actueleArtikel1.getPrijs() ,nieuweArtikel1.getPrijs());
		assertEquals("Artikel1 voorraad niet juist opgeslagen", actueleArtikel1.getVoorraad(),nieuweArtikel1.getVoorraad());
		
		assertEquals("Artikel2 naam niet juist opgeslagen", actueleArtikel2.getNaam(),nieuweArtikel2.getNaam());
		assertEquals("Artikel2 id niet juist opgeslagen", actueleArtikel2.getId(),nieuweArtikel2.getId());
		assertEquals("Artikel2 prijs niet juist opgeslagen", actueleArtikel2.getPrijs() ,nieuweArtikel2.getPrijs());
		assertEquals("Artikel2 voorraad niet juist opgeslagen", actueleArtikel2.getVoorraad(),nieuweArtikel2.getVoorraad());
		
		assertEquals("Artikel3 naam niet juist opgeslagen", actueleArtikel3.getNaam(),nieuweArtikel3.getNaam());
		assertEquals("Artikel3 id niet juist opgeslagen", actueleArtikel3.getId(),nieuweArtikel3.getId());
		assertEquals("Artikel3 prijs niet juist opgeslagen", actueleArtikel3.getPrijs() ,nieuweArtikel3.getPrijs());
		assertEquals("Artikel3 voorraad niet juist opgeslagen", actueleArtikel3.getVoorraad(),nieuweArtikel3.getVoorraad());
		
	}
	@Test
	public void testGetArtikel() {
		;
	}
	@Test
	public void testGetAlleArtikelen() {
		ArrayList<Artikel> actueleWaarden = adao.getAlleArtikelen();
		ArrayList <Artikel> testlijst=new ArrayList <Artikel>();
		testlijst.add(nieuweArtikel1);
		testlijst.add(nieuweArtikel2);
		testlijst.add(nieuweArtikel3);
		
		for (int i=0; i<actueleWaarden.size();i++) {
		    
		
		
		assertEquals("Artikel naam niet juist opgeslagen",actueleWaarden.get(i).getNaam(),testlijst.get(i).getNaam());
		assertEquals("Artikel id niet juist opgeslagen", actueleWaarden.get(i).getId(),testlijst.get(i).getId());
		assertEquals("Artikel prijs niet juist opgeslagen", actueleWaarden.get(i).getPrijs() ,testlijst.get(i).getPrijs());
		assertEquals("Artikel voorraad niet juist opgeslagen", actueleWaarden.get(i).getVoorraad(),testlijst.get(i).getVoorraad());
		
		}
		
	}
	@Test
	public void testUpdateArtikelStringBigDecimalIntInt() {
		;
	}
	@Test
	public void testUpdateArtikelArtikel() {
		;
	}
	@Test
	public void testDeleteArtikelInt() {
		;
	}
	@Test
	public void testDeleteArtikelArtikel() {
		;
	}

}
