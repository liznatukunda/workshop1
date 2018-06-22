package WS_1.workshop;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import data.ConnectieDatabase;
import domein.Account;
import domein.Account.Rol;
import data.AccountDao;

import org.junit.*;

public class AccountDaoTest {
	
	ArrayList <Account> testlijst;
	ArrayList <Account> actueleWaarden;
	
	Account nieuweAccount1=new Account ("Boer Piet", "simpel", Account.Rol.beheerder);
	Account nieuweAccount2=new Account ("medewerker 1", "simpel", Account.Rol.medewerker);
	Account nieuweAccount3=new Account ("klant 1", "simpel", Account.Rol.klant);
	static ConnectieDatabase cdb=new ConnectieDatabase();
	AccountDao adao=new AccountDao();
	
	@BeforeClass
	public static void initialiseer() throws SQLException {
		cdb.maakVerbinding();
	}
	
	
	
	@Before
	public void setUp(){
		testlijst=new ArrayList <Account>();
		actueleWaarden=new ArrayList <Account>();
		testlijst.add(nieuweAccount1);
		testlijst.add(nieuweAccount2);
		testlijst.add(nieuweAccount3);
	}
	
	@After
	public void finish() {
		adao.deleteAccount(nieuweAccount1);
		adao.deleteAccount(nieuweAccount2);
		adao.deleteAccount(nieuweAccount3);
	}
	
	

	@Test
	public void testCreateAccount() {
		adao.createAccount(nieuweAccount1);
		adao.createAccount(nieuweAccount2);
		adao.createAccount(nieuweAccount3);
		//Account[]actueleWaarden= {nieuweAccount1,nieuweAccount2,nieuweAccount3};
		//Account[]testlijst={nieuweAccount1,nieuweAccount2,nieuweAccount3};
		//actueleWaarden.add(adao.getAccount(nieuweAccount1.getId()));
		//actueleWaarden.add(adao.getAccount(nieuweAccount2.getId()));
		//actueleWaarden.add(adao.getAccount(nieuweAccount3.getId()));
		//assertArrayEquals("Kon geen nieuwe accounts opslaan", actueleWaarden.toArray(), testlijst.toArray());
		Account actueleAccount1=adao.getAccount(nieuweAccount1.getId());
		Account actueleAccount2=adao.getAccount(nieuweAccount2.getId());
		Account actueleAccount3=adao.getAccount(nieuweAccount3.getId());
		
		assertEquals("boer Piet gebruikersnaam niet juist opgeslagen", actueleAccount1.getUserNaam(),nieuweAccount1.getUserNaam());
		assertEquals("boer Piet id niet juist opgeslagen", actueleAccount1.getId(),nieuweAccount1.getId());
		assertEquals("boer Piet password niet juist opgeslagen", actueleAccount1.getPassword() ,nieuweAccount1.getPassword());
		assertEquals("boer Piet rol niet juist opgeslagen", actueleAccount1.getRol(),nieuweAccount1.getRol());
		
		assertEquals("medewerker gebruikersnaam niet juist opgeslagen", actueleAccount2.getUserNaam(),nieuweAccount2.getUserNaam());
		assertEquals("medewerker id niet juist opgeslagen", actueleAccount2.getId(),nieuweAccount2.getId());
		assertEquals("medewerker password niet juist opgeslagen", actueleAccount2.getPassword() ,nieuweAccount2.getPassword());
		assertEquals("medewerker rol niet juist opgeslagen", actueleAccount2.getRol(),nieuweAccount2.getRol());
		
		assertEquals("medewerker gebruikersnaam niet juist opgeslagen", actueleAccount3.getUserNaam(),nieuweAccount3.getUserNaam());
		assertEquals("medewerker id niet juist opgeslagen", actueleAccount3.getId(),nieuweAccount3.getId());
		assertEquals("medewerker password niet juist opgeslagen", actueleAccount3.getPassword() ,nieuweAccount3.getPassword());
		assertEquals("medewerker rol niet juist opgeslagen", actueleAccount3.getRol(),nieuweAccount3.getRol());
	}
	

	@Test
	public void testUpdateAccountIntStringStringRol() {
		
	}

	@Test
	public void testUpdateAccountAccount() {
		
	}

	@Test
	public void testDeleteAccountInt() {
		
	}

	@Test
	public void testDeleteAccountAccount() {
		
	}

}
