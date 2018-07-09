package controllerTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import static org.mockito.Mockito.*;

import Controllers.AccountController;
import Controllers.ArtikelController;
import data.AccountDao;
import data.AccountDaoImplement;
import domein.Account;
import domein.Account.Rol;


public class AccountControllerTest {
	
	@Mock
	private AccountDaoImplement accountDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAccountController() {
		fail("Not yet implemented");
	}

	@Test
	public void testVoegAccountToe() {
		MockitoAnnotations.initMocks(this);
		AccountController accountController=new AccountController();
		accountController.voegAccountToe("test gebruiker", "test wachtwoord", Account.Rol.klant);
		Mockito.verify(accountDao).createAccount(new Account ("test gebruiker", "test wachtwoord", Account.Rol.klant));
	}
	
	@Test
	public void test() {
		AccountController accountController=new AccountController();
		Mockito.when(accountDao.createAccount(new Account ("test gebruiker", "test wachtwoord", Account.Rol.klant))).thenReturn(createTestAccount());
		Account effectiefAccount=accountDao.createAccount(new Account ("test gebruiker", "test wachtwoord", Account.Rol.klant));
		// Zou mooier zijn als we hier methode equals(Account account) zouden gebruiken
		assertEquals("test gebruiker",effectiefAccount.getUserNaam());
		assertEquals("test wachtwoord",effectiefAccount.getPassword());
		assertEquals(Account.Rol.klant, effectiefAccount.getRol());
		// Indien geen methode equals toegevoegd: moet dan id nog worden gecontroleerd?
	}
	
	private Account createTestAccount() {
		Account account = new Account("test gebruiker", "test wachtwoord", Account.Rol.klant);
		return account;
	}

	@Test
	public void testPasUserNaamAan() {
		fail("Not yet implemented");
	}

	@Test
	public void testPasUserPasswordAan() {
		fail("Not yet implemented");
	}

	@Test
	public void testPasRolAan() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteAccount() {
		Account account=new Account( "te deleten account", "te deleten password" , Rol.medewerker);
		int accountId=-1;
		account.setId(accountId);
		AccountController accountContr = new AccountController(accountDao);
		accountContr.deleteAccount(accountId);
		Mockito.when(accountDao.getAccount(anyInt())).thenReturn(account);
		Mockito.when(accountDao.deleteAccount((Account)any())).thenReturn(true);
		
		Assert.assertTrue(condition);
	}

}
