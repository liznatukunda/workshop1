package data;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

import domein.Bestelling;


public class BestellingDaoImplement {
	
//	private  static Connection con = ConnectieDatabase.getConnection();
	
	public int createBestelling(Bestelling bestelling,int klantid ){		
		int insertId = -1;
		String sql = "INSERT INTO Bestelling (totaalPrijs,Klant_idKlant) VALUES (?,?);";
		try ( Connection con= ConnectieDatabase.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);){
			stmt.setObject(1, bestelling.getTotaalPrijs());
			stmt.setObject(2, klantid);    
			stmt.executeUpdate();
			ResultSet resultSet = stmt.getGeneratedKeys();
            if (resultSet.isBeforeFirst()) {
                resultSet.next();
                insertId = resultSet.getInt(1);
          //      System.out.println("Id " + insertId + " voor bestelling " + bestelling.getBestellingNummer());
                bestelling.setBestellingNummer(insertId);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return insertId;
	}
	
	public Bestelling getBestelling(int id){
		String sql = "SELECT * FROM Bestelling WHERE id=?";
		Bestelling returnedBestelling = null;
		try (Connection con= ConnectieDatabase.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql);){
			stmt.setObject(1, id);
			ResultSet resultSet = stmt.executeQuery();
            if (resultSet.isBeforeFirst()) {
                resultSet.next();

                int id1 = resultSet.getInt(1);
                BigDecimal totaalprijs =  resultSet.getBigDecimal(2);
            //    int Klant_id =  resultSet.getInt(3);             
                returnedBestelling = new Bestelling (totaalprijs);
                
                
                returnedBestelling.setBestellingNummer(id1);
                
            //    System.out.println("Bestelling gevonden: " + returnedBestelling.getBestellingNummer());
            }
            else{
            	System.err.println("Geen Bestelling gevonden!");
            }
            
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return returnedBestelling;
	}
	
	public ArrayList<Bestelling> getAlleBestelling(){
		String sql = "SELECT * FROM bestelling;";
		ArrayList<Bestelling> returnedBestelling = new ArrayList<>();
		try (Connection con= ConnectieDatabase.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql);){
			ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()){
            	
            	int bestellingNummer = resultSet.getInt(1);
            	BigDecimal totaalprijs =  resultSet.getBigDecimal(2);
              //  int klantid =  resultSet.getBigDecimal(3);
               // Bestellingen bestellingen = new Bestellingen (bestellingNummer,totaalprijs,klantid);
            	Bestelling bestellingen = new Bestelling (totaalprijs);
                bestellingen.setBestellingNummer(bestellingNummer);
            	
            	//System.out.println("Bestellingen gevonden: " + bestellingen.getBestellingNummer());
            	returnedBestelling.add(bestellingen);
            }
            
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnedBestelling;
	}
	
	public boolean updateBestellingen(BigDecimal totaalprijs, int id){
		String sql = "UPDATE bestelling SET totaalprijs = ? WHERE id = ?";
		int rows = -1;
		try (Connection con= ConnectieDatabase.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql);){
			stmt.setObject(1, totaalprijs);
			stmt.setObject(2, id);
			rows = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			
		}
		return rows > 0;
	}
	
	public boolean updateBestellingen(Bestelling bestelling){
		return updateBestellingen(bestelling.getTotaalPrijs(), bestelling.getBestellingNummer());
	}
	
	public boolean deleteBestellingen(int id){
		String sql = "DELETE FROM bestelling WHERE id = ?";
		int rows = -1;
		try (Connection con= ConnectieDatabase.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql);){
			stmt.setObject(1, id);
			rows = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows > 0;
	}
	
	public boolean deleteBestellingen(Bestelling bestellingen){
		return deleteBestellingen(bestellingen.getBestellingNummer());
	}
}
