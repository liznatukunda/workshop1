package structuur;
import java.util.Scanner;

import Controllers.AccountController;
import domein.Account.Rol;
import logger.menu;
import  data.AccountDao;

public class AccountsMenu {
	private static  Scanner input = new Scanner(System.in);
	private static AccountController accountController;
	private static menu hoofdMenu;
	
	
	public AccountsMenu(){
		accountController = new AccountController();
	}
	
	public static void accountsMenu() {
	boolean logout = false;

	// this menu is meant for only the administrator 
	while(!logout) {
		
		System.out.println("Kies en type in wat u wilt doen?");
		System.out.println( "1 :Maak nieuwe Accounts");
		System.out.println( "2 :Pas account aan");	
		System.out.println( "2 :delete account ");	
		System.out.println( "3 :Terug naar Hoofdmenu");
         
		int actie = input.nextInt();
       switch(actie) {
       
       case 1:
    	   voegAccountToe();

			break;
		case 2:
			System.out.println("Voer het nummer in van het Account dat u wilt aanpassen");
			pasAccountAan(input.nextInt());
			break;
	       case 3:
			System.out.println("Voer het nummer in van het Account dat u wilt Verwijderen");
			deleteAcount(input.nextInt());
				break;
		case 4:
			hoofdMenu = new menu();              
			hoofdMenu.actie();
			break;
		default:
			System.out.println( "Kies 1 t/m 3");
			accountsMenu();            
       }   	   
	}  	
	}
	public static void voegAccountToe(){
		System.out.println("Wat is de Account UserNaam van dit Account?");
		String userNaam = input.next();
		System.out.println("Wat is de  User Paasword van dit Account?");
		String userPassword = input.next();
		System.out.println("Wat is de  User rol van dit Account?");
		Rol rol =AccountDao.toRol(input.next());                                       
		if(AccountController.voegAccountToe(userNaam, userPassword, rol)){ 
			System.out.println("Account toegevoegd!");
		}
		else{
			System.err.println("Kon Account niet toevoegen!");
		}
		accountsMenu();
	}
	
	public static void pasAccountAan(int accountId){
		System.out.println("Wat wilt u aanpassen?");
		System.out.println("1: Account UserNaam aanpassen");
		System.out.println("2: User Paasword aanpassen");
		System.out.println("3: User rol aanpassen");
		System.out.println("4: Annuleer");
		int keuze = input.nextInt();
		switch(keuze){
		case 1:
			pasUserNaamAan(accountId);
			break;
		case 2:
			pasPasswordAan(accountId);
			break;
		case 3:
			pasRolAan(accountId);
			break;
		case 4:
			accountsMenu();
			break;
		default:
			System.out.println("Ongeldige keuze");
			pasAccountAan(accountId);
		}
	}
	
	public static void pasUserNaamAan(int Id){
		System.out.println("Vul nieuwe user naam in");
		String userNaam = input.next();
		if(AccountController.pasUserNaamAan(Id, userNaam)){
			System.out.println("UserNaam aangepast!");
		}
		else{
			System.err.println("UserNaam kon niet aangepast worden!");
		}
		accountsMenu();
	}
	
	public static void pasPasswordAan(int accountId){
		System.out.println("Vul nieuwe Password in");
		String password = input.next();
		if(AccountController.pasUserPasswordAan(accountId, password)){
			System.out.println("Password aangepast!");
		}
		else{
			System.err.println("Password kon niet aangepast worden!");
		}
		accountsMenu();
	}
	
	public static void pasRolAan(int accountId){
		System.out.println("Vul nieuwe Rol in");
		Rol rol =AccountDao.toRol(input.next());   
		if(AccountController.pasRolAan(accountId, rol)){
			System.out.println("Rol aangepast!");
		}
		else{
			System.err.println("Rol kon niet aangepast worden!");
		}
		accountsMenu();
	}
	
	public static void deleteAcount(int accountId){
		System.out.println("Wat is de Account id om te verwijderen?");
		int Id = input.nextInt();
		if(AccountController.deleteAccount(Id)){ 
			System.out.println("Account deleted!");
		}
		else{
			System.err.println("Kon Account niet delete worden!");
		}
		accountsMenu();
	}
		
		
	
	
}
