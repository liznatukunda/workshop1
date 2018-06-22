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
		testlijst.add(new Account("Boer Piet", "simpel", Account.Rol.beheerder));
		testlijst.add(new Account("medewerker 1", "simpel", Account.Rol.medewerker));
		testlijst.add(new Account("klant 1", "simpel", Account.Rol.klant));
		
		
		

	}
	

	@Test
	public void testCreateAccount() {
		adao.createAccount(nieuweAccount1);
		adao.createAccount(nieuweAccount2);
		adao.createAccount(nieuweAccount3);
		//Account[]actueleWaarden= {nieuweAccount1,nieuweAccount2,nieuweAccount3};
		//Account[]testlijst={nieuweAccount1,nieuweAccount2,nieuweAccount3};
		actueleWaarden.add(adao.getAccount(nieuweAccount1.getId()));
		actueleWaarden.add(adao.getAccount(nieuweAccount2.getId()));
		actueleWaarden.add(adao.getAccount(nieuweAccount3.getId()));
		assertArrayEquals("Kon geen nieuwe accounts opslaan", actueleWaarden.toArray(), testlijst.toArray());
	}
	
	public void testGetAccount() {
		
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
