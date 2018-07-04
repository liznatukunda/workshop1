package structuur;

import java.sql.SQLException;
import java.util.Scanner;
import Controllers.AdresController;
import Controllers.KlantController;
import domein.Adres;

public class AdresMenu {
	private  Scanner input = new Scanner(System.in);
	private AdresController adresController;
	private KlantController klantController;
	int klantId;

	
	public AdresMenu(int klantId) {
		adresController = new AdresController();
		this.klantId=klantId;
		adresController.setKlant(klantId);
		adresGegevensMenu();
	}
	
	public void adresGegevensMenu()  {   
		boolean logout = false;
		boolean erIsEenFactuuradres=adresController.factuurAdresAanwezig();
		boolean erIseenBezorgadres=adresController.BezorgAdresAanwezig();
		
		
		System.out.println("POSTADRES: "+System.lineSeparator()+adresController.toonPostadres());
		if (erIsEenFactuuradres) {
			System.out.println("FACTUURADRES: "+System.lineSeparator()+adresController.toonFactuuradres());
		}
		if (erIseenBezorgadres) {
				System.out.println("BEZORGADRES: "+System.lineSeparator()+adresController.toonBezorgadres());
		}
		System.out.println("");
		System.out.println("Kies en type in wat u wilt doen?");
		
		int factuuradresWijziging=-2;
		int factuuradresVerwijdering=-2;
		int factuuradresToevoeging=-2;
		int bezorgadresWijziging=-2;
		int bezorgadresVerwijdering=-2;
		int bezorgadresToevoeging=-2;
		
		int menuNummer=1;
		while(!logout) {
			System.out.println( "1: Wijzig postadres");
			if (erIsEenFactuuradres) {
				factuuradresWijziging=menuNummer+1;
				menuNummer++;
				System.out.println( ""+factuuradresWijziging+": Wijzig factuuradres");
				factuuradresVerwijdering=menuNummer+1;
				menuNummer++;
				System.out.println( ""+factuuradresVerwijdering+": Verwijder factuuradres");
			}
			else {
				factuuradresToevoeging=menuNummer+1;
				menuNummer++;
				System.out.println( ""+factuuradresToevoeging+": Voeg factuuradres toe");
			}
			if (erIseenBezorgadres) {
				bezorgadresWijziging=menuNummer+1;
				menuNummer++;
				System.out.println(""+bezorgadresWijziging+ ": Wijzig bezorgadres");
				bezorgadresVerwijdering=menuNummer+1;
				menuNummer++;
				System.out.println(""+bezorgadresVerwijdering+ ": Verwijder bezorgadres");
			}
			else {
				bezorgadresToevoeging=menuNummer+1;
				menuNummer++;
				System.out.println(""+bezorgadresToevoeging+ ": Voeg bezorgadres toe");
			}
			System.out.println("-1: Ga terug naar klantmenu");
			System.out.println("0: Log uit");
						
			int actie = input.nextInt();
		       if (actie == 1) {
				wijzigPostadres();
			} else if (actie == factuuradresWijziging) {
				wijzigFactuuradres();
			} else if (actie == factuuradresVerwijdering) {
				verwijderFactuuradres();
			} else if (actie == factuuradresToevoeging) {
				VoegFactuuradresToe();
			} else if (actie == bezorgadresWijziging) {
				wijzigBezorgadres();
			} else if (actie == bezorgadresVerwijdering) {
				verwijderBezorgadres();
			} else if (actie == bezorgadresToevoeging) {
				VoegBezorgadresToe();
			} else if (actie == -1) {
				KlantgegevensMenu klantmenu = new KlantgegevensMenu();
				klantmenu.klantgegevensMenu();
			} else if (actie == 0) {
				logout=true;
			} else {
				System.out.println("Ongeldige keuze");
			}
		}
	}

	private void VoegBezorgadresToe() {
		input.nextLine();
		System.out.println("Geef de straatnaam van het adres op");
		String straatnaam=input.nextLine();
		System.out.println("Geef het huisnummer van het adres op");
		int huisnummer=input.nextInt();
		input.nextLine();
		System.out.println("Heeft het adres een toevoeging op het huisnummer?");
		System.out.println("1: ja");
		System.out.println("2: nee");
		String toevoeging=null;
		int toevoegingKeuze=input.nextInt();
		input.nextLine();
		if (toevoegingKeuze==1) {
			System.out.println("Geef de toevoeging op het huisnummer op");
			toevoeging=input.nextLine();
		}
		else if (toevoegingKeuze==2) {
			}
		else {
			System.out.println("Ongelding keuze, er wordt geen toevoeging opgeslagen");
		}
		System.out.println("Geef de postcode van het adres op");
		String postcode=input.nextLine();
		System.out.println("Geef de plaats van het adres op");
		String plaats=input.nextLine();
		adresController.maakBezorgAdres(straatnaam, huisnummer, toevoeging, postcode, plaats);
		AdresMenu nieuwAdresMenu=new AdresMenu(klantId);
		
	}

	private void VoegFactuuradresToe() {
		input.nextLine();
		System.out.println("Geef de straatnaam van het adres op");
		String straatnaam=input.nextLine();
		System.out.println("Geef het huisnummer van het adres op");
		int huisnummer=input.nextInt();
		input.nextLine();
		System.out.println("Heeft het adres een toevoeging op het huisnummer?");
		System.out.println("1: ja");
		System.out.println("2: nee");
		String toevoeging=null;
		int toevoegingKeuze=input.nextInt();
		input.nextLine();
		if (toevoegingKeuze==1) {
			System.out.println("Geef de toevoeging op het huisnummer op");
			toevoeging=input.nextLine();
		}
		else if (toevoegingKeuze==2) {
			}
		else {
			System.out.println("Ongelding keuze, er wordt geen toevoeging opgeslagen");
		}
		System.out.println("Geef de postcode van het adres op");
		String postcode=input.nextLine();
		System.out.println("Geef de plaats van het adres op");
		String plaats=input.nextLine();
		adresController.maakFactuurAdres(straatnaam, huisnummer, toevoeging, postcode, plaats);
		AdresMenu nieuwAdresMenu=new AdresMenu(klantId);
	}
	
	private void nieuwAdres() {
		
	}

	private void verwijderBezorgadres()  {
		adresController.setBezorgadres();
		adresController.verwijderAdres();
		AdresMenu nieuwAdresMenu=new AdresMenu(klantId);		
	}

	private void verwijderFactuuradres()  {
		adresController.setFactuuradres();
		adresController.verwijderAdres();
		AdresMenu nieuwAdresMenu=new AdresMenu(klantId);
	}

	private void wijzigBezorgadres()  {
		input.nextLine();
		adresController.setPostadres();
		System.out.println("Huidig BEZORGADRES:");
		System.out.println(adresController.toonBezorgadres());
		wijzigAdres();
		
		int actie = input.nextInt();
		input.nextLine();
	       switch(actie) {             
	       	case 1:   	   
	       		wijzigStraat();
				break;
	       	case 2:
	       		wijzigHuisnummer();
	       		break;
	       	case 3:
	       		wijzigToevoeging();
	       		break;
	       	case 4:
	       		wijzigPostcode();
	       		break;
	       	case 5:
	       		wijzigPlaats();
	       		break;
	       	case 6:
	       		wijzigAlles();
	       }
	       AdresMenu nieuwAdresMenu=new AdresMenu(klantId);
	}

	private void wijzigFactuuradres()  {
		adresController.setFactuuradres();
		System.out.println("Huidig FACTUURADRES:");
		System.out.println(adresController.toonFactuuradres());
		wijzigAdres();
		
		int actie = input.nextInt();
		input.nextLine();
	       switch(actie) {             
	       	case 1:   	   
	       		wijzigStraat();
				break;
	       	case 2:
	       		wijzigHuisnummer();
	       		break;
	       	case 3:
	       		wijzigToevoeging();
	       		break;
	       	case 4:
	       		wijzigPostcode();
	       		break;
	       	case 5:
	       		wijzigPlaats();
	       		break;
	       	case 6:
	       		wijzigAlles();
	       }
	       AdresMenu nieuwAdresMenu=new AdresMenu(klantId);
	}

	private void wijzigPostadres()  {
		adresController.setPostadres();
		System.out.println("Huidig POSTADRES:");
		System.out.println(adresController.toonPostadres());
		wijzigAdres();
		
		int actie = input.nextInt();
		input.nextLine();
	       switch(actie) {             
	       	case 1:   	   
	       		wijzigStraat();
				break;
	       	case 2:
	       		wijzigHuisnummer();
	       		break;
	       	case 3:
	       		wijzigToevoeging();
	       		break;
	       	case 4:
	       		wijzigPostcode();
	       		break;
	       	case 5:
	       		wijzigPlaats();
	       		break;
	       	case 6:
	       		wijzigAlles();
	       }
	       AdresMenu nieuwAdresMenu=new AdresMenu(klantId);
	}
	


	private void wijzigAdres() {
		System.out.println("");
		System.out.println("Wat wilt u aanpassen?");
		System.out.println("1: Enkel straatnaam aanpassen");
		System.out.println("2: Enkel huisnummer aanpassen");
		System.out.println("3: Enkel toevoeging aanpassen");
		System.out.println("4: Enkel postcode aanpassen");
		System.out.println("5: Enkel plaats aanpassen");
		System.out.println("6: Alles aanpassen");
		System.out.println("");
		System.out.println("-1: Ga terug naar klantmenu");
		System.out.println("0: Log uit");
	}
	
	private void wijzigStraat()  {
		System.out.println("Geef nieuwe straatnaam in");
		String nieuweStraat=input.nextLine();
		adresController.wijzigStraat(nieuweStraat);
		System.out.println("Straatnaam aangepast!");
	}
	
	private void wijzigHuisnummer()  {
		System.out.println("Geef nieuwe huisnummer in");
		int nieuwHuisnummer=input.nextInt();
		input.nextLine();
		adresController.wijzigHuisnummer(nieuwHuisnummer);
		System.out.println("Huisnummer aangepast!");		
	}
	
	private void wijzigToevoeging()  {
		System.out.println("Geef nieuwe toevoeging in (typ verwijder om toevoeging te verwijderen");
		String nieuweToevoeging=input.nextLine();
		if (nieuweToevoeging.equals("verwijder")) {
			adresController.wijzigToevoeging(null);
		}
		else {
			adresController.wijzigToevoeging(nieuweToevoeging);
		}
		System.out.println("Toevoeging aangepast!");		
	}
	
	private void wijzigPostcode()  {
		System.out.println("Geef nieuwe postcode in");
		String nieuwePostcode=input.nextLine();
		adresController.wijzigPostcode(nieuwePostcode);
		System.out.println("Postcode aangepast!");		
	}
	
	private void wijzigPlaats()  {
		System.out.println("Geef nieuwe plaatsnaam in");
		String nieuwePlaats=input.nextLine();
		adresController.wijzigPlaats(nieuwePlaats);
		System.out.println("Plaatsnaam aangepast!");		
	}
	
	private void wijzigAlles()  {
		wijzigStraat();
		wijzigHuisnummer();
		wijzigToevoeging();
		wijzigPostcode();
		wijzigPlaats();
	}
}
