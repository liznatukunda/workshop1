package structuur;
import java.util.Scanner;


public class KlantgegevensMenu {
	private static  Scanner input = new Scanner(System.in);
	public static void klantGegevensMenu() {
	boolean logout = false;
	//	hard coding the acties for now	
	while(!logout) {
		System.out.println( "Kies en type in wat u wilt doen:  1 :Voeg klantgegevens toe");
		System.out.println( "Kies en type in wat u wilt doen:  2 :Wijzig klantgegevens");		
		System.out.println( "Kies en type in wat u wilt doen:  3 :Verwijder klantgegevens");
		System.out.println( "Kies en type in wat u wilt doen:  4 :Zoek klant"); 
		System.out.println( "Kies en type in wat u wilt doen:  5 :Terug naar Hoofdmenu");
		
		int actie = input.nextInt();
       switch(actie) {
       		case 1: System.out.println( "voeg klantgegevens toe");break;
       		case 2: System.out.println( "Wijzig klantgegevens");break;
       		case 3: System.out.println( "Verwijder klantgegevens");break;
       		case 4:System.out.println( "Zoek klant");break;
       		case 5:{ System.out.println( "Terug naar hoofdmenu");
       		logout=true;break;
       		}
       		
 	
       		default: System.out.println( "Kies 1 t/m 5");
       }
    	   
	}  	
	}
}
