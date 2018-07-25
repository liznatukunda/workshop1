package viewGui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controllers.AccountController;
import Controllers.MenuController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InlogFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField gebruikersnaamTextField;
	private JTextField wachtwoordTextField;
	private JCheckBox connectiepoolCheckBox;
	private AccountController accountController;
	private MenuController menuController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InlogFrame frame = new InlogFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InlogFrame() {
		accountController=new AccountController();
		menuController = new MenuController();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel wachtwoordLabel = new JLabel("wachtwoord");
		wachtwoordLabel.setBounds(33, 55, 126, 20);
		wachtwoordLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		contentPane.add(wachtwoordLabel);
		
		JLabel gebruikersnaamLabel = new JLabel("gebruikersnaam");
		gebruikersnaamLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		gebruikersnaamLabel.setBounds(33, 24, 126, 20);
		contentPane.add(gebruikersnaamLabel);
		
		gebruikersnaamTextField = new JTextField();
		gebruikersnaamTextField.setBounds(169, 27, 180, 20);
		contentPane.add(gebruikersnaamTextField);
		gebruikersnaamTextField.setColumns(10);
		
		wachtwoordTextField = new JTextField();
		wachtwoordTextField.setToolTipText("");
		wachtwoordTextField.setColumns(10);
		wachtwoordTextField.setBounds(169, 58, 180, 20);
		contentPane.add(wachtwoordTextField);
		
		JRadioButton mySQLDbRadioButton = new JRadioButton("MySQL database");
		mySQLDbRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setConnnectiepoolCheckboxZichtbaarheid(true);
				menuController.setDatabase(1);
			}
		});
		mySQLDbRadioButton.setSelected(true);
		mySQLDbRadioButton.setBounds(6, 165, 202, 23);
		contentPane.add(mySQLDbRadioButton);
		
		JRadioButton MongoDbRadioButton = new JRadioButton("Mongo database");
		MongoDbRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setConnnectiepoolCheckboxZichtbaarheid(false);
				MenuController.setDatabase(2);
			}
		});
		MongoDbRadioButton.setBounds(6, 191, 202, 23);
		contentPane.add(MongoDbRadioButton);
		
		connectiepoolCheckBox = new JCheckBox("Connectiepool in gebruik");
		connectiepoolCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!connectiepoolCheckBox.isSelected()) {
					MenuController.setConnectionPool(0);
				}
				else {
					MenuController.setConnectionPool(1);
				}
				
			}
		});
		connectiepoolCheckBox.setSelected(true);
		connectiepoolCheckBox.setBounds(226, 165, 202, 23);
		contentPane.add(connectiepoolCheckBox);
		
		JButton loginButton = new JButton("log in");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (accountController.checkcredentials(gebruikersnaamTextField.getText(), wachtwoordTextField.getText())) {
					// ga door naar volgende frame
				}
				else {
					JOptionPane.showMessageDialog(contentPane, "Onjuiste gebruikersnaam en/of wachtwoord." + System.lineSeparator() + "Probeer het opnieuw.");
					gebruikersnaamTextField.setText("");
					wachtwoordTextField.setText("");
				}
			}
		});
		loginButton.setBounds(169, 89, 89, 23);
		contentPane.add(loginButton);
		
		ButtonGroup buttongroupRadioButtons=new ButtonGroup();
		buttongroupRadioButtons.add(mySQLDbRadioButton);
		buttongroupRadioButtons.add(MongoDbRadioButton);
	}
	
	
	private void setConnnectiepoolCheckboxZichtbaarheid(boolean connectiepoolCheckBoxWelZichtbaar) {
		if(connectiepoolCheckBoxWelZichtbaar) {
			connectiepoolCheckBox.setVisible(true);
		}
		else {
			connectiepoolCheckBox.setVisible(false);
		}
	}
}
