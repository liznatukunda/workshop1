package structuur;
import java.util.Scanner;
public class AccountsMenu {
	private static  Scanner input = new Scanner(System.in);
	public static void accountsMenu() {
	boolean logout = false;
	//	hard coding the acties for now	
	while(!logout) {
		System.out.println( "Kies en type in wat u wilt doen:  1 :Accounts");
		System.out.println( "Kies en type in wat u wilt doen:  2 :Pas account aan");		
		System.out.println( "Kies en type in wat u wilt doen:  3 :Terug naar Hoofdmenu");
         
		int actie = input.nextInt();
       switch(actie) {
       		case 1: System.out.println( "maak nieuw account aan");break;
       		case 2: System.out.println( "pas account aan");break;
       		case 3:{ System.out.println( "Terug naar hoofdmenu");
       		logout=true;break;
       		}
 	
       		default: System.out.println( "Kies 1 t/m 3");
       }
    	   
	}  	
	}
}
