package Controllers;

import java.util.ArrayList;

import data.AccountDaoImplement;
import data.AccountDaoMongoImplement;
import data.KlantDaoImplement;
import data.KlantDaoMongoImplement;
import domein.Account;
import domein.Bestelling;
import domein.Klant;
import domein.Account.Rol;

public class AccountController {

	private  AccountDaoMongoImplement accountDao;
	private KlantDaoMongoImplement klantDao;
	
	public AccountController(){
		accountDao = new AccountDaoMongoImplement();
		klantDao = new KlantDaoMongoImplement();
	}
	
	public AccountController(AccountDaoMongoImplement accountDao) {
		this.accountDao=accountDao;
	}
	
	
	public boolean voegAccountToe(String userNaam, String password, Rol rol){
		Integer id = accountDao.createAccount(new Account(userNaam, password, rol));  
		return id > 0;
	}
	
	public String zoekAccount(int accountId) {
		Account account =accountDao.getAccount(accountId);
		if (account==null) {
			return null;
		}
		else {
		String  returnstring = ("" + account.getId() +" "+ account.getUserNaam()+" " + account.getPassword()+" " + account.getRol().toString());
		return returnstring;
		}
	}
	
	public  boolean pasUserNaamAan(int accountId, String userNaam){
		Account account = accountDao.getAccount(accountId);
		if(account == null){
			return false;
		}
		account.setUserNaam(userNaam);
		return accountDao.updateAccount(account); 
	}
	
	
	public  boolean pasUserPasswordAan(int accountId, String password){
		Account account = accountDao.getAccount(accountId);
		if(account == null){
			return false;
		}
		account.setUserNaam(password);
		return accountDao.updateAccount(account); 
	}
	
	
	public  boolean pasRolAan(int accountId, Rol rol){
		Account account = accountDao.getAccount(accountId);
		if(account == null){
			return false;
		}
		account.setRol(rol);
		return accountDao.updateAccount(account); 
	}
	
	public  boolean deleteAccount(int accountId){
		Account account = accountDao.getAccount(accountId);
		if(account == null){
			return false;
		}
		account.setId(accountId);
		return accountDao.deleteAccount(account); 
	}
	
	public String[] getAlleAccounts(){ 
		ArrayList<Account> accounts = accountDao.getAlleAccounts();
		String[] returnArray = new String[accounts.size()];
		for(int i=0; i<accounts.size(); i++){
			Account a = accounts.get(i);	
			returnArray[i] = a.getId() + ": " + a.getUserNaam()+ " " + a.getRol().toString(); 
		}
		return returnArray;
	}
	public boolean accountIsKlant(int accountId) {
		Account account =accountDao.getAccount(accountId);
		return (account.getRol()==Account.Rol.klant);
	}
	public boolean accountHeeftGeenKlant(int accountId) {
		ArrayList<Klant> klanten = klantDao.getAlleKlantenPerAccount(accountId);
		return (klanten.isEmpty());
	}
}
