package view;
import java.util.Scanner;

import Controllers.FactoryController;
import data.DaoFactory;



public class Menu {      

	private static  Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Menu menu= new Menu();
		menu.inloggen();
		
    }	
	
	public void inloggen() {
		while(true) {
			
			System.out.println( "Hallo!  dit is Boer Piet kaas winkel" );
			System.out.println( "Wilt u 1: inloggen of 2: afsluiten" );
			int keus = input.nextInt();
			input.nextLine();
			if (keus==2) {
				System.out.println( "Bedankt tot ziens");
				System.exit(0);
				
			}

			else {
				System.out.println("Kies database.");
				System.out.println("Kies en type in wat u wilt doen:  1 :MySQL Database");
				System.out.println( "Kies en type in wat u wilt doen:  2 :Mongo Database");
				int database=input.nextInt();
				FactoryController.setDatabase(database);
				input.nextLine();
			 System.out.println( "Log in om verder te gaan" );
			 System.out.println( "Usernaam?" );
			 String user = input.nextLine();
			 System.out.println( "Password?" );
			 String password = input.nextLine(); 
	
			 String dbUser = "User";
			 String dbPassword = "Password"; // credentials from the data source  
	
			    if (dbUser.equals(user) && dbPassword.equals(password)) {
			       
			    	System.out.println("U bent succesvol ingelogd ");
			       
			        actie();
			        
			    } 
			    
			    else {
			        
			    	System.out.println("Onjuiste credentials! Probeer het opnieuw");
			    	input.nextLine();
			    
			    }
			 
			    
		}
		}
	}
	
	public  void actie() {  
		boolean logout = false;
		while(!logout) {
			System.out.println( "Kies en type in wat u wilt doen:  1 :Accounts bekijken");
			System.out.println( "Kies en type in wat u wilt doen:  2 :Klanten bekijken");
			System.out.println( "Kies en type in wat u wilt doen:  3 :Artikelen bekijken");
			System.out.println( "Kies en type in wat u wilt doen:  0 :Uitloggen");
	         
			int actie = input.nextInt();
	       switch(actie) {
	       		case 1:
		       		AccountsMenu accountMenu = new AccountsMenu();
		       		accountMenu.accountsMenu();
		       		break;
	       		case 2:
	       			KlantgegevensMenu klantgegevensMenu = new KlantgegevensMenu();
	       			klantgegevensMenu.klantgegevensMenu();
	       			break;
	       		case 3: 
	       			ArtikelMenu artikelmenu = new ArtikelMenu();
	       			artikelmenu.artikelMenu();
	       			break;
	       		case 0:
	       			{ System.out.println( "Uitloggen");
	       			logout=true;
	       			break;
	       			}
	       		default: 
	       			System.out.println( "Kies 1 t/m 3");
	       }
	    	   
	       	
		}
	}
	
}
   
	
	
	
	