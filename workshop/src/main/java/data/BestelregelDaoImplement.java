package data;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

import domein.BestelRegel;
import domein.Bestelling;
import domein.Artikel;

public class BestelregelDaoImplement {
//private  static Connection con = ConnectieDatabase.getConnection();
	
	public int createBestelregel(BestelRegel bestelregel,int bestellingnummer,int artikelnummer){		
		int insertId = -1;
		String sql = "INSERT INTO Bestelregel (aantal,prijs,Bestelling_idBestelling,Artikel_idArtikel) VALUES (?,?,?,?);";
		try ( Connection con= ConnectieDatabase.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);){
			stmt.setObject(1, bestelregel.getAantal());
			stmt.setObject(2, bestelregel.getPrijs());
			stmt.setObject(3, bestellingnummer);
			stmt.setObject(4, artikelnummer);		// of moeten we hier werken met artikelnummer in de methode?
			stmt.executeUpdate();
			ResultSet resultSet = stmt.getGeneratedKeys();
            if (resultSet.isBeforeFirst()) {
                resultSet.next();
                insertId = resultSet.getInt(1);
                 bestelregel.setId(insertId);
               System.out.println("Id " + insertId + " voor bestelregel " + bestelregel.getId());
               
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return insertId;
	}
	
	public BestelRegel getBestelRegel(int id){
		String sql = "SELECT * FROM Bestelregel WHERE id=?";
		BestelRegel returnedBestelRegel = null;
		try ( Connection con= ConnectieDatabase.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql);){
			stmt.setObject(1, id);
			ResultSet resultSet = stmt.executeQuery();
            if (resultSet.isBeforeFirst()) {
                resultSet.next();

                int id1 = resultSet.getInt(1);
                int aantal =  resultSet.getInt(2);
                BigDecimal totaalPrijs =  resultSet.getBigDecimal(3);
                int bestellingnummer = resultSet.getInt(4);
                int artikelnummer = resultSet.getInt(5);
                Artikel artikel =new Artikel(artikelnummer);
               returnedBestelRegel = new BestelRegel (artikel, aantal);
           //ToDo     nieuwe constructor met artikelnummer of artikel behorende bij artikelnummer
                //hier opvragen met artikeldao??
                
                returnedBestelRegel.setId(id1);
                
            //    System.out.println("BestelRegel gevonden: " + returnedBestelRegel.getId());
            }
            else{
            	System.err.println("Geen Bestelregel gevonden!");
            }
            
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return returnedBestelRegel;
	}
	
	public ArrayList<BestelRegel> getAlleBestelRegel(){
		String sql = "SELECT * FROM bestelregel;";
		ArrayList<BestelRegel> returnedBestelRegel = new ArrayList<>();
		try ( Connection con= ConnectieDatabase.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql);){
			ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()){
            	

                int id1 = resultSet.getInt(1);
                int aantal =  resultSet.getInt(2);
                BigDecimal totaalPrijs =  resultSet.getBigDecimal(3);
                int bestellingnummer = resultSet.getInt(4);
                int artikelnummer = resultSet.getInt(5);
                Artikel artikel = new Artikel (artikelnummer);
            	BestelRegel bestelregel = new BestelRegel (artikel, aantal);
                bestelregel.setId(id1);
            	
            	//System.out.println("Bestellingen gevonden: " + bestellingen.getBestellingNummer());
            	returnedBestelRegel.add(bestelregel);
            }
            
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnedBestelRegel;
	}
	
	public boolean updateBestelRegel(int aantal, int id){
		String sql = "UPDATE bestelregel SET aantal = ? WHERE id = ?";
		int rows = -1;
		try ( Connection con= ConnectieDatabase.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql);){
			stmt.setObject(1, aantal);
			stmt.setObject(2, id);
			rows = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			
		}
		return rows > 0;
	}
	
	public boolean updateBestelRegel(BestelRegel bestelregel){
		return updateBestelRegel(bestelregel.getAantal(), bestelregel.getId());
	}
	
	public boolean deleteBestelRegel(int id){
		String sql = "DELETE FROM bestelregel WHERE id = ?";
		int rows = -1;
		try ( Connection con= ConnectieDatabase.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql);){
			stmt.setObject(1, id);
			rows = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows > 0;
	}
	
	public boolean deleteBestelRegel(BestelRegel bestelregel){
		return deleteBestelRegel(bestelregel.getId());
	}

}
