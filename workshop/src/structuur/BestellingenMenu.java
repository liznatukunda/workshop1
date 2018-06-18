package structuur;

import java.util.Scanner;

public class BestellingenMenu {
	private static  Scanner input = new Scanner(System.in);
	public static void bestellingMenu() {
	boolean logout = false;
	//	hard coding the acties for now	
	while(!logout) {
		System.out.println( "Kies en type in wat u wilt doen:  1 :Nieuwe bestelling");
		System.out.println( "Kies en type in wat u wilt doen:  2 :Pas bestelling aan");		
		System.out.println( "Kies en type in wat u wilt doen:  3 :Verwijder bestelling");
		System.out.println( "Kies en type in wat u wilt doen:  4 :Zoek bestelling"); 
		System.out.println( "Kies en type in wat u wilt doen:  5 :Terug naar Hoofdmenu");
		
		int actie = input.nextInt();
       switch(actie) {
       		case 1: System.out.println( "Nieuwe bestelling");break;
       		case 2: System.out.println( "Pas bestelling aan");break;
       		case 3: System.out.println( "Verwijder bestelling");break;
       		case 4:System.out.println( "Zoek bestelling");break;
       		case 5:{ System.out.println( "Terug naar hoofdmenu");
       		logout=true;break;
       		}
       		
 	
       		default: System.out.println( "Kies 1 t/m 5");
       }
    	   
	}  	
}
}
