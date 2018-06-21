package data;


import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import domein.Artikel;

public class ArtikelDao implements ArtikelDaoInterface{
	private  static Connection con = ConnectieDatabase.getConnection();
	
	
	public int createArtikel(Artikel artikel){
		
		int insertId = -1;
		String sql = "INSERT INTO Artikel (naam, prijs, voorraad) VALUES (?,?,?);";
		try {
			PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setObject(1, artikel.getNaam());
			stmt.setObject(2, artikel.getPrijs());
			stmt.setObject(3, artikel.getVoorraad());
			stmt.executeUpdate();
			ResultSet resultSet = stmt.getGeneratedKeys();
            if (resultSet.isBeforeFirst()) {
                resultSet.next();
                insertId = resultSet.getInt(1);
                System.out.println("Id " + insertId + " voor artikel " + artikel.getNaam());
                artikel.setId(insertId);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return insertId;
	}
	
	public Artikel getArtikel(int id){
		String sql = "SELECT * FROM Artikel WHERE id=?";
		Artikel returnedArtikel = null;
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, id);
			ResultSet resultSet = stmt.executeQuery();
            if (resultSet.isBeforeFirst()) {
                resultSet.next();

                int id1 = resultSet.getInt(1);
                String naam =  resultSet.getString(2);
                BigDecimal prijs =  resultSet.getBigDecimal(3);
                int voorraad =  resultSet.getInt(4);
                returnedArtikel = new Artikel (naam,prijs,voorraad);
                
                
                returnedArtikel.setId(id1);
                
                System.out.println("Artikel gevonden: " + returnedArtikel.getNaam());
            }
            else{
            	System.err.println("Geen Artikel gevonden!");
            }
            
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return returnedArtikel;
	}
	
	public ArrayList<Artikel> getAlleArtikelen(){
		String sql = "SELECT * FROM Artikel;";
		ArrayList<Artikel> returnedArtikelen = new ArrayList<>();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()){
            	
            	int id1 = resultSet.getInt(1);
                String naam =  resultSet.getString(2);
                BigDecimal prijs =  resultSet.getBigDecimal(3);
                int voorraad =  resultSet.getInt(4);
                Artikel artikel = new Artikel (naam,prijs,voorraad);
            	artikel.setId(id1);
            	
            	System.out.println("Artikel gevonden: " + artikel.getNaam());
            	returnedArtikelen.add(artikel);
            }
            
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnedArtikelen;
	}
	
	public boolean updateArtikel(String naam, BigDecimal prijs , int voorraad, int id){
		String sql = "UPDATE klant SET naam = ?, prijs = ?, voorraad = ? WHERE id = ?";
		int rows = -1;
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, naam);
			stmt.setObject(2, prijs);
			stmt.setObject(3, voorraad);
			stmt.setObject(4, id);
			rows = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			
		}
		return rows > 0;
	}
	
	public boolean updateArtikel(Artikel nieuwArtikel){
		return updateArtikel(nieuwArtikel.getNaam(), nieuwArtikel.getPrijs(), nieuwArtikel.getVoorraad(), nieuwArtikel.getId());
	}
	
	public boolean deleteArtikel(int id){
		String sql = "DELETE FROM Artikel WHERE id = ?";
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
	
	public boolean deleteArtikel(Artikel artikel){
		return deleteArtikel(artikel.getId());
	}
}


