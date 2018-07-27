package viewGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controllers.AccountController;
import Controllers.KlantController;

public class KlantPane {

	private JPanel contentPane;
	private AccountController accountController;
	private KlantController klantController;
	private JButton klantToevoegButton;
	private JButton kiesKlantButton;
	
	private String voornaam;
	private String tussenvoegsel=null;
	private String achternaam;
	private int accountId;
	

	public KlantPane() {
		contentPane=BasisFrame.getCenterPane();
		accountController=new AccountController();
		klantController=new KlantController();
		setKlantPane();
	}
	
	private void setKlantPane() {
		BasisFrame.reset();
		klantToevoegButton=new JButton("Voeg klant toe");
		kiesKlantButton=new JButton("Kies een bestaande klant");
		klantToevoegButton.setBounds(20,40,300,20);
		kiesKlantButton.setBounds(20,80,300,20);
		contentPane.add(klantToevoegButton);
		contentPane.add(kiesKlantButton);
		BasisFrame.rebuild();
		
		klantToevoegButton.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent arg0) {
				VoegKlantToePane();
			}
		});
		kiesKlantButton.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent arg0) {
				selecteerKlant();
			}
		});		
	}
	
	private void VoegKlantToePane() {
		BasisFrame.reset();
		JLabel accountLabel=new JLabel ("Account");
		JLabel voornaamLabel=new JLabel ("Voornaam");
		JLabel tussenvoegselLabel=new JLabel ("Tussenvoegsel");
		JLabel achternaamLabel=new JLabel ("Achternaam");
		
		JTextField voornaamTextfield=new JTextField();
		JTextField tussenvoegselTextfield=new JTextField();
		JTextField achternaamTextfield=new JTextField();
		JComboBox accountkeuzeComboBox=new JComboBox(accountController.getBeschikbareKlantAccounts());
		
		JButton volgendeButton=new JButton("Ga door naar volgende stap");
		
		accountLabel.setBounds(20, 20, 120, 20);
		voornaamLabel.setBounds(20, 50, 120, 20);
		tussenvoegselLabel.setBounds(20, 80, 120, 20);
		achternaamLabel.setBounds(20, 110, 120, 20);
		accountkeuzeComboBox.setBounds(150, 20, 250, 20);
		voornaamTextfield.setBounds(150, 50, 250, 20);
		tussenvoegselTextfield.setBounds(150, 80, 250, 20);
		achternaamTextfield.setBounds(150, 110, 250, 20);
		volgendeButton.setBounds(150,150, 200,20);
		
		contentPane.add(accountLabel);
		contentPane.add(voornaamLabel);
		contentPane.add(tussenvoegselLabel);
		contentPane.add(achternaamLabel);
		contentPane.add(voornaamTextfield);
		contentPane.add(tussenvoegselTextfield);
		contentPane.add(achternaamTextfield);
		contentPane.add(accountkeuzeComboBox);
		contentPane.add(volgendeButton);
		
		volgendeButton.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent arg0) {
				voornaam=voornaamTextfield.getText();
				tussenvoegsel=tussenvoegselTextfield.getText();
				achternaam=achternaamTextfield.getText();
				accountId=BasisFrame.haalIdUitString(accountkeuzeComboBox.getSelectedItem().toString());
				voegKlantPostAdresToe();
			}
		});
		
		BasisFrame.rebuild();		
		
	}
	
	
	
	private void voegKlantPostAdresToe() {
		BasisFrame.reset();
		JLabel straatnaamLabel=new JLabel ("Straatnaam");
		JLabel huisnummerLabel=new JLabel ("Huisnummer");
		JLabel toevoegingLabel=new JLabel ("Toevoeging");
		JLabel postcodeLabel=new JLabel ("Postcode");
		JLabel plaatsLabel=new JLabel ("Woonplaats");
		
		JTextField straatnaamTextField=new JTextField ();
		JTextField huisnummerTextField=new JTextField ();
		JTextField toevoegingTextField=new JTextField ();
		JTextField postcodeTextField=new JTextField ();
		JTextField plaatsTextField=new JTextField ();
		
		JButton voegKlantToeButton=new JButton("Voeg klant toe");
		
		straatnaamLabel.setBounds(20, 20, 120, 20);
		huisnummerLabel.setBounds(20, 50, 120, 20);
		toevoegingLabel.setBounds(20, 80, 120, 20);
		postcodeLabel.setBounds(20, 110, 120, 20);
		plaatsLabel.setBounds(20, 140, 120, 20);
		
		straatnaamTextField.setBounds(150, 20, 250, 20);
		huisnummerTextField.setBounds(150, 50, 250, 20);
		toevoegingTextField.setBounds(150, 80, 250, 20);
		postcodeTextField.setBounds(150, 110, 250, 20);
		plaatsTextField.setBounds(150, 140, 250, 20);
		
		voegKlantToeButton.setBounds(150, 180, 200, 20);
		
		contentPane.add(straatnaamLabel);
		contentPane.add(huisnummerLabel);
		contentPane.add(toevoegingLabel);
		contentPane.add(postcodeLabel);
		contentPane.add(plaatsLabel);
		contentPane.add(straatnaamTextField);
		contentPane.add(huisnummerTextField);
		contentPane.add(toevoegingTextField);
		contentPane.add(postcodeTextField);
		contentPane.add(plaatsTextField);
		contentPane.add(voegKlantToeButton);			
		BasisFrame.rebuild();
		
		voegKlantToeButton.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent arg0) {
				klantController.voegKlantToe(voornaam, tussenvoegsel, achternaam, accountId, straatnaamTextField.getText(), Integer.parseInt(huisnummerTextField.getText()), toevoegingTextField.getText(), postcodeTextField.getText(), plaatsTextField.getText());
				setKlantPane();
			}
		});
	}
	
	private void selecteerKlant() {
		ArrayList <String> klanten = new ArrayList<String>();
		for(String s : klantController.getAlleKlanten()) {
			klanten.add(s);
		}
		
		Object[] klantenlijst = klanten.toArray();
		Object selectedKlantValue = JOptionPane.showInputDialog(contentPane, "Selecteer een klant", null,JOptionPane.INFORMATION_MESSAGE, null, klantenlijst, klantenlijst[0]);
		int klantId=BasisFrame.haalIdUitString(selectedKlantValue.toString());
		Object[] opties = { "Wijzig klant" , "Verwijder klant" , "Ga naar adressen van deze klant" , "Ga naar bestellingen van deze klant"};
		Object selectedOptieValue = JOptionPane.showInputDialog(contentPane, "Selecteer een optie", null,JOptionPane.INFORMATION_MESSAGE, null, opties, opties[0]);
		if (selectedOptieValue.equals("Wijzig klant")) {
			wijzigKlant(klantId);
		}
		else if (selectedOptieValue.equals("Verwijder klant")) {
			verwijderKlant(klantId);
		}
		else if (selectedOptieValue.equals("Ga naar adressen van deze klant")) {
			AdresPane adresPane=new AdresPane(klantId);
		}
		else {
			// start bestellingPane
		}
	}
	
	private void verwijderKlant(int klantId) {
		klantController.deleteKlant(klantId);
		setKlantPane();
	}
	
	private void wijzigKlant(int klantId) {
		Object[] opties = { "Wijzig voornaam" , "Wijzig tussenvoegsel" , "Wijzig achternaam" };
		Object selectedOptieValue = JOptionPane.showInputDialog(contentPane, "Selecteer een optie", null,JOptionPane.INFORMATION_MESSAGE, null, opties, opties[0]);
		if (selectedOptieValue.equals("Wijzig voornaam")) {
			String voornaam = JOptionPane.showInputDialog("Wat is de nieuwe voornaam");
			klantController.pasVoornaamAan(klantId, voornaam);
		}
		else if (selectedOptieValue.equals("Wijzig tussenvoegsel")) {
			String tussenvoegsel = JOptionPane.showInputDialog("Wat is het nieuwe tussenvoegsel");
			klantController.pasTussenvoegselAan(klantId, tussenvoegsel);
		}
		else {
			String achternaam = JOptionPane.showInputDialog("Wat is de nieuwe achternaam");
			klantController.pasAchternaamAan(klantId, achternaam);
		}
		setKlantPane();
	}


}
