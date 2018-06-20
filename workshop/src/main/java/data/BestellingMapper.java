package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;

public class BestellingMapper {
	
	
	private PreparedStatement prepLeesAlleBestelregels = null;
	private ConnectieDatabase cdb=new ConnectieDatabase();
	private Connection con=null;
	
	private void initialiseer() throws SQLException {
		cdb.maakVerbinding();
		con=cdb.getConnection();
		prepLeesAlleBestelregels=con.prepareStatement("SELECT * FROM Bestelregel WHERE Bestelling_id=?");
	}
	
	

}
