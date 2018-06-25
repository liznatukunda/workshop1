package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import domein.Klant;

public class KlantDao {
private  static Connection con = ConnectieDatabase.getConnection();
	
	
	public int createKlant(Klant klant, int accountId){
		
		int insertId = -1;
		String sql = "INSERT INTO Klant (voornaam, tussenvoegsel, achternaam, accountId) VALUES (?,?,?,?);";
		try {
			PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setObject(1, klant.getVoornaam());
			stmt.setObject(2, klant.getTussenvoegsel());
			stmt.setObject(3, klant.getAchternaam());
			stmt.setObject(4, accountId);
			stmt.executeUpdate();
			ResultSet resultSet = stmt.getGeneratedKeys();
            if (resultSet.isBeforeFirst()) {
                resultSet.next();
                insertId = resultSet.getInt(1);
               // System.out.println("Id " + insertId + " voor klant " + klant.getVoornaam());
                klant.setId(insertId);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return insertId;
	}
	
	public Klant getKlant(int id){
		String sql = "SELECT * FROM Klant WHERE id=?";
		Klant returnedKlant = null;
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, id);
			ResultSet resultSet = stmt.executeQuery();
            if (resultSet.isBeforeFirst()) {
                resultSet.next();

                int id1 = resultSet.getInt(1);
                String voornaam =  resultSet.getString(2);
                String tussenvoegsel =  resultSet.getString(3);
                String achternaam =  resultSet.getString(4);
                
                returnedKlant = new Klant (voornaam,tussenvoegsel,achternaam);
                
                
                returnedKlant.setId(id1);
                
                //System.out.println("Klant gevonden: " + returnedKlant.getVoornaam());
            }
            else{
            	System.err.println("Geen Klant gevonden!");
            }
            
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return returnedKlant;
	}
	
	public ArrayList<Klant> getAlleKlanten(){
		String sql = "SELECT * FROM Klant;";
		ArrayList<Klant> returnedKlanten = new ArrayList<>();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()){
            	
            	int id1 = resultSet.getInt(1);
            	 String voornaam =  resultSet.getString(2);
                 String tussenvoegsel =  resultSet.getString(3);
                 String achternaam =  resultSet.getString(4);
                 
                Klant returnedKlant = new Klant (voornaam,tussenvoegsel,achternaam);
               
            	returnedKlant.setId(id1);
            	
            	//System.out.println("Klant gevonden: " + returnedKlant.getvoornaam());
            	returnedKlanten.add(returnedKlant);
            }
            
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnedKlanten;
	}
	
	public boolean updateKlant(String voornaam, String tussenvoegsel, String achternaam, int accountId, int id){
		String sql = "UPDATE Klant SET voornaam = ?, tussenvoegsel = ?, achternaam = ?, account_id WHERE id = ?";
		int rows = -1;
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, voornaam);
			stmt.setObject(2, tussenvoegsel);
			stmt.setObject(3, achternaam);
			stmt.setObject(4, accountId);
			stmt.setObject(5, id);
			rows = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			
		}
		return rows > 0;
	}
	
	public boolean updateKlant(Klant nieuwKlant, int accountId){
		return updateKlant(nieuwKlant.getVoornaam(), nieuwKlant.getTussenvoegsel(), nieuwKlant.getAchternaam(), accountId , nieuwKlant.getId());
	}
	
	public boolean deleteKlant(int id){
		String sql = "DELETE FROM Klant WHERE id = ?";
		int rows = -1;
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, id);
			rows = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows > 0;
	}
	
	public boolean deleteKlant(Klant klant){
		return deleteKlant(klant.getId());
	}
}
