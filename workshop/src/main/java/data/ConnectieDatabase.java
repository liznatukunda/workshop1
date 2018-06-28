package data;
import java.sql.*;

public class ConnectieDatabase{
	
	 private static Connection con=null;
	 
	 public ConnectieDatabase() {
		 
	 }
	 
	  /**
	   * Maakt verbinding met de database op basis van de waarden via DOM parser
	   */
	 public static void maakVerbinding() throws SQLException {
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
		 // check is de connectie nog actief !null of zelfde connectie teruggeven//
	 }
	 
	 
	/*  public void sluitAf() {
	    if(con!=null){
	      try{
	        con.close();
	      }
	      catch (SQLException e){
	        
	      }
	    }
	    
	  }
	  */
	  public static Connection getConnection(){
		  try {
	            if (con == null || con.isClosed()) {
	                maakVerbinding();
	            }
	        } catch (SQLException sqlEx) {
	            sqlEx.printStackTrace();
	        }
	        return con;
		  
		 
	  }
	 
	  

	
}
