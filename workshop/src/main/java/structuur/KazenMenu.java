package structuur;

import java.util.Scanner;

public class KazenMenu {
	private static  Scanner input = new Scanner(System.in);
	public static void kazenMenu() {
	boolean logout = false;
	//	hard coding the acties for now	
	while(!logout) {
		System.out.println( "Kies en type in wat u wilt doen:  1 :Voeg nieuwe kaas toe");
		System.out.println( "Kies en type in wat u wilt doen:  2 :Pas kaas aan");		
		System.out.println( "Kies en type in wat u wilt doen:  3 :Verwijder kaas");
		System.out.println( "Kies en type in wat u wilt doen:  4 :Zoek kaas"); 
		System.out.println( "Kies en type in wat u wilt doen:  5 :Terug naar Hoofdmenu");
		
		int actie = input.nextInt();
       switch(actie) {
       		case 1: System.out.println( "Voeg nieuwe kaas toe ");break;
       		case 2: System.out.println( "Pas kaas aan");break;
       		case 3: System.out.println( "Verwijder kaas");break;
       		case 4:System.out.println( "Zoek kaas");break;
       		case 5:{ System.out.println( "Terug naar hoofdmenu");
       		logout=true;break;
       		}
       		
 	
       		default: System.out.println( "Kies 1 t/m 5");
       }
    	   
	}  	
}
}
