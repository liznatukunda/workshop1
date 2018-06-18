package databaseconnection;
import java.sql.*;

public class dbconnect {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		initializeDB();
		
    }	
	public static void initializeDB() {
		DOM dom = new DOM();
		dom.initializeDOM();
		
		try {
	//Class.forName("com.mysql.cj.jdbc.Driver");
	//System.out.println("Driver loaded");
			Connection connection = DriverManager.getConnection(dom.getHost()+dom.getPadnaam()+dom.getExtrastatements(), dom.getGebruikersnaam(), dom.getWachtwoord());
	//Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/workshop1?useLegacyDatetimeCode=false&serverTimezone=UTC&verifyServerCertificate=false&useSSL=true", "root", "RS4@Student");
	System.out.println("Database connected");
	
	//Statement statement = connection.createStatement();
	//statement.executeQuery("insert into account (id) values (1)");
	connection.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
