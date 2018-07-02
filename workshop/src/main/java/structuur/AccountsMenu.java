package structuur;
import java.util.Scanner;

import Controllers.AccountController;
import domein.Account;
import domein.Account.Rol;
import logger.menu;

public class AccountsMenu {
	private  Scanner input = new Scanner(System.in);
	private AccountController accountController;

	
	
	public AccountsMenu(){
		accountController = new AccountController();
	}
	
	public void accountsMenu() {
	boolean logout = false;

	// this menu is meant for only the administrator 
	while(!logout) {
		
		System.out.println("Kies en type in wat u wilt doen?");
		System.out.println( "1 :Maak nieuwe Accounts");
		System.out.println( "2 :Zoek een Account");
		System.out.println( "3 :Pas account aan");	
		System.out.println( "4 :delete account ");		
		System.out.println( "5 :Toon alle accounts ");
		System.out.println( "6 :Terug naar Hoofdmenu");
         
		int actie = input.nextInt();
       switch(actie) {
       
       case 1:
    	   voegAccountToe();

			break;
        case 2:
        	zoekAccount();
        	break;
		case 3:
			System.out.println("Voer het nummer in van het Account dat u wilt aanpassen");
			pasAccountAan(input.nextInt());
			break;
	    case 4:
			System.out.println("Voer het nummer in van het Account dat u wilt Verwijderen");
			deleteAcount(input.nextInt());
				break;
	    case 5:
	    	toonAccounts();
	    	break;
		case 6:
			logout=true;
			break;
		default:
			System.out.println( "Kies 1 t/m 4");           
       }   	   
	}  	
	}
	public void toonAccounts() {
		System.out.println("Alle accountinformatie: ");		
			for(String s : accountController.getAlleAccounts()){
				System.out.println(s);
			}
		}
	
	
	public void zoekAccount() {
		System.out.println("Vul  Account id  in om te zoeken");
		int id = input.nextInt();
		String accountInfo =accountController.zoekAccount(id);
		if (accountInfo!=null) {
			System.out.println( "Account gevonden: "+ accountInfo  );
		}
		else {
			System.out.println("Account niet gevonden");
		};
		
	}
	public  void voegAccountToe(){
		System.out.println("Wat is de Account UserNaam van dit Account?");
		String userNaam = input.next();
		System.out.println("Wat is de  User Password van dit Account?");
		String userPassword = input.next();
		System.out.println("Wat is de  User rol van dit Account?");
		Rol rol =Account.Rol.toRol(input.next());                                       
		if(accountController.voegAccountToe(userNaam, userPassword, rol)){ 
			System.out.println("Account toegevoegd!");
		}
		else{
			System.err.println("Kon Account niet toevoegen!");
		}
		
	}
	
	public void pasAccountAan(int accountId){
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
			break;
		default:
			System.out.println("Ongeldige keuze");
			pasAccountAan(accountId);
		}
	}
	
	public void pasUserNaamAan(int Id){
		System.out.println("Vul nieuwe user naam in");
		String userNaam = input.next();
		if(accountController.pasUserNaamAan(Id, userNaam)){
			System.out.println("UserNaam aangepast!");
		}
		else{
			System.err.println("UserNaam kon niet aangepast worden!");
		}
		
	}
	
	public void pasPasswordAan(int accountId){
		System.out.println("Vul nieuwe Password in");
		String password = input.next();
		if(accountController.pasUserPasswordAan(accountId, password)){
			System.out.println("Password aangepast!");
		}
		else{
			System.err.println("Password kon niet aangepast worden!");
		}
	
	}
	
	public void pasRolAan(int accountId){
		System.out.println("Vul nieuwe Rol in");
		Rol rol =Account.Rol.toRol(input.next());   
		if(accountController.pasRolAan(accountId, rol)){
			System.out.println("Rol aangepast!");
		}
		else{
			System.err.println("Rol kon niet aangepast worden!");
		}
	
	}
	
	public void deleteAcount(int accountId){
		System.out.println("Wat is de Account id om te verwijderen?");
		int Id = input.nextInt();
		if(accountController.deleteAccount(Id)){ 
			System.out.println("Account deleted!");
		}
		else{
			System.err.println("Kon Account niet delete worden!");
		}
	
	}
		
		
	
	
}
