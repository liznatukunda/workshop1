package Controllers;

import java.util.ArrayList;

import data.AccountDaoImplement;
import domein.Account;
import domein.Account.Rol;

public class AccountController {

	private static AccountDaoImplement accountDaoImplement;
	
	public AccountController(){
		accountDaoImplement = new AccountDaoImplement();
	}
	
	public static boolean voegAccountToe(String userNaam, String password, Rol rol){
		Integer id = accountDaoImplement.createAccount(new Account(userNaam, password, rol));
		return id > 0;
	}
	
	public static String[] getAlleAccounts(){ 
		ArrayList<Account> accounts = accountDaoImplement.getAlleAccounts();
		String[] returnArray = new String[accounts.size()];
		for(int i=0; i<accounts.size(); i++){
			Account a = accounts.get(i);	
			returnArray[i] = a.getId() + ": " + a.getUserNaam() + "‚ " + a.getPassword() + ", " + a.getRol();
		}
		return returnArray;
	}
	
	
	public static boolean pasUserNaamAan(int accountId, String userNaam){
		Account account = accountDaoImplement.getAccount(accountId);
		if(account == null){
			return false;
		}
		account.setUserNaam(userNaam);
		return accountDaoImplement.updateAccount(account); 
	}
	
	
	public static boolean pasUserPasswordAan(int accountId, String password){
		Account account = accountDaoImplement.getAccount(accountId);
		if(account == null){
			return false;
		}
		account.setUserNaam(password);
		return accountDaoImplement.updateAccount(account); 
	}
	
	
	public static boolean pasRolAan(int accountId, Rol rol){
		Account account = accountDaoImplement.getAccount(accountId);
		if(account == null){
			return false;
		}
		account.setRol(rol);
		return accountDaoImplement.updateAccount(account); 
	}
	
	public static boolean deleteAccount(int accountId){
		Account account = accountDaoImplement.getAccount(accountId);
		if(account == null){
			return false;
		}
		account.setId(accountId);
		return accountDaoImplement.deleteAccount(account); 
	}
}
