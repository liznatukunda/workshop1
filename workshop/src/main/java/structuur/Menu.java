package structuur;
import java.util.Scanner;

public class Menu {      

	private static  Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		inloggen();
		
    }	
	
	static void inloggen() {
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
			 System.out.println( "Log in om verder te gaan" );
			 
			 String user = input.nextLine();
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
	
	public static void actie() {  
		boolean logout = false;
		while(!logout) {
			System.out.println( "Kies en type in wat u wilt doen:  1 :Accounts");
			System.out.println( "Kies en type in wat u wilt doen:  2 :Klantgegevens");	
			System.out.println( "Kies en type in wat u wilt doen:  3 :Bestellingen");	
			System.out.println( "Kies en type in wat u wilt doen:  4 :Kazen");	
			System.out.println( "Kies en type in wat u wilt doen:  5 :Uitloggen");
	         
			int actie = input.nextInt();
	       switch(actie) {
	       		case 1: AccountsMenu.accountsMenu();break;
	       		case 2: KlantgegevensMenu.klantGegevensMenu();break;    
	       		case 3: BestellingenMenu.bestellingMenu();break;
	       		case 4: KazenMenu.kazenMenu();break;
	       		case 5:{ System.out.println( "Uitloggen");
	       				logout=true;
	       				break;
	       		}
	       		default: System.out.println( "Kies 1 t/m 5");
	       }
	    	   
	       	
		}
	}
	
}
   
	
	
	
	