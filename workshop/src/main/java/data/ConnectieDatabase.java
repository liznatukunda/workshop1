package data;
import java.io.PrintWriter;
import java.sql.*;

public class ConnectieDatabase extends DOM{
	
	 private static Connection con=null;
	 
	 public ConnectieDatabase() {
		 
	 }
	 
	  /**
	   * Maakt verbinding met de database op basis van de waarden via DOM parser
	   */
	 public void maakVerbinding() throws SQLException {
		 DOM parser = new DOM();
	     String url = parser.getUrl();
	     String username = parser.getUsername();
	     String wachtwoord = parser.getPassword();
		 try {
		     con=DriverManager.getConnection(url, username, wachtwoord);
		 }
		 catch (SQLException e) {
			 throw new SQLException("Verbinding maken mislukt");
		 }
	 }
	 
	  /**
	   * Sluit de verbinding met de database
	   */
	  public void sluitAf() {
	    if(con!=null){
	      try{
	        con.close();
	      }
	      catch (SQLException e){
	        
	      }
	    }
	    
	  }
	  
	  public static Connection getConnection(){
		  return con;
	  }
	 

	
}
