package mapper_test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import data.AccountDao;
import data.ConnectieDatabase;
import data.KlantDao;
import domein.Account;
import domein.Account.Rol;
import domein.Adres;
import domein.Adres.AdresType;
import domein.Klant;

public class AdresMapperTest {

	static Connection con;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ConnectieDatabase.maakVerbinding();
			con=ConnectieDatabase.getConnection();
			
			AccountDao accountDao=new AccountDao();
			accountDao.createAccount(new Account("testaccount","mijnWW", Rol.klant));
			// vanaf hier verder schrijven
			int accountId=1;
			
			KlantDao klantDao=new KlantDao();
			klantDao.createKlant(new Klant("MijnVoornaam", "mijntussenvoegsel", "MijnAchternaam"), accountId);
            
			PreparedStatement pStatement=con.prepareStatement("INSERT INTO adres (straatnaam, huisnummer, toevoeging, postcode, woonplaats, adrestype, Klant_idKlant) VALUES (teststraat1,13,C,1234AB,Gekkendorp,AdresType.BEZORGADRES.toString(),?)",Statement.RETURN_GENERATED_KEYS);
		}
		catch (Exception e){
			throw new Exception("Kon de startwaarden niet aanmaken voor de AdresMapperTest");
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateAdres() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAdres() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateAdresStraatnaam() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateAdresHuisnummer() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateAdresToevoeging() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateAdresPostcode() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateAdresWoonplaats() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateAdresAdresType() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteAdres() {
		fail("Not yet implemented");
	}

}
