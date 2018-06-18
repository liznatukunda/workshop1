package databaseconnection;
import java.sql.*;

public class dbconnect extends DOM{
	
	 public  static void main(String[]args) {
	        try {
	        	DOM parser = new DOM();
		        String url = parser.getUrl();
		        String username = parser.getUsername();
		        String wachtwoord = parser.getPassword();  

		        
		       Connection connection = DriverManager.getConnection(url, username, wachtwoord); 	
		        
	           
		     if (connection != null)     
	         
		        System.out.println("Database connected via XML");
		      String insert= "insert into adrestype (id, type) values (321,'536')";
				PreparedStatement preparedStatement = connection.prepareStatement  (insert);
				preparedStatement.execute();

				 System.out.println("Data inserted"); 
	            
	        } catch (Exception e) {
	            e.printStackTrace();   
	        }

	    }

	
}
