package Controllers;

import data.AccountDao;
import domein.Account;
import domein.Account.Rol;

public class AccountController {

	private static AccountDao accountDao;
	
	public AccountController(){
		accountDao = new AccountDao();
	}
	
	public static int voegAccountToe(String userNaam, String password, Rol rol){
		Account account=new Account(userNaam, password, rol);
		accountDao.createAccount(account);
		return account.getId();
		
	}
	
	public static boolean pasUserNaamAan(int accountId, String userNaam){
		Account account = accountDao.getAccount(accountId);
		if(account == null){
			return false;
		}
		account.setUserNaam(userNaam);
		return accountDao.updateAccount(account); 
	}
	
	
	public static boolean pasUserPasswordAan(int accountId, String password){
		Account account = accountDao.getAccount(accountId);
		if(account == null){
			return false;
		}
		account.setUserNaam(password);
		return accountDao.updateAccount(account); 
	}
	
	
	public static boolean pasRolAan(int accountId, Rol rol){
		Account account = accountDao.getAccount(accountId);
		if(account == null){
			return false;
		}
		account.setRol(rol);
		return accountDao.updateAccount(account); 
	}
	
	public static boolean deleteAccount(int accountId){
		Account account = accountDao.getAccount(accountId);
		if(account == null){
			return false;
		}
		account.setId(accountId);
		return accountDao.deleteAccount(account); 
	}
}
