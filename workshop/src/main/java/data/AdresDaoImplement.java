package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import domein.Adres;
import domein.Adres.AdresType;

public class AdresDaoImplement {

	private  Connection con;
	private  PreparedStatement pStatementVoegToe;
	private  PreparedStatement pStatementGet;
	private  PreparedStatement pStatementUpdate;
	private  PreparedStatement pStatementDeleteAdres;

	
	private  AdresType toAdresType(String adresType) {
		if (adresType.equals("postadres")) {
			return AdresType.POSTADRES;
		}
		else if (adresType.equals("bezorgadres")) {
			return AdresType.BEZORGADRES;
		}
		else if (adresType.equals("factuuradres")) {
			return AdresType.FACTUURADRES;
		}
		else return null;
	}
	
	private  void initialiseer() throws SQLException{
		ConnectieDatabase.maakVerbinding();
		con=ConnectieDatabase.getConnection();
		try {
			pStatementVoegToe=con.prepareStatement("INSERT INTO adres (straatnaam, huisnummer, toevoeging, postcode, woonplaats, adrestype, Klant_idKlant) VALUES (?,?,?,?,?,?,?);",Statement.RETURN_GENERATED_KEYS);
			pStatementGet=con.prepareStatement("SELECT * FROM adres WHERE id=?;");
			pStatementUpdate=con.prepareStatement("UPDATE adres SET straatnaam=?, huisnummer=?, toevoeging=?,postcode=?,woonplaats=?,adrestype=? WHERE id=?");
			pStatementDeleteAdres=con.prepareStatement("DELETE FROM adres where id=?");
	    }
	    catch (SQLException e){
	    	einde();
	    	throw new SQLException ("Fout bij formuleren van een SQL-opdracht.");
	    }
	}
	
	private  void einde() {
		ConnectieDatabase.sluitAf();
	}
	
	
	
	public  boolean createAdres(Adres adres, int klantid) throws SQLException{
		boolean created=false;
		int insertId = -1;
		initialiseer();
		try {
			pStatementVoegToe.setObject(1, adres.getStraatnaam());
			pStatementVoegToe.setObject(2, adres.getHuisnummer());
			pStatementVoegToe.setObject(3, adres.getToevoeging());
			pStatementVoegToe.setObject(4, adres.getPostcode());
			pStatementVoegToe.setObject(5, adres.getWoonplaats());
			pStatementVoegToe.setObject(6, adres.getAdresType().toString());
			pStatementVoegToe.setObject(7, klantid);
			pStatementVoegToe.executeUpdate();
			ResultSet resultSet = pStatementVoegToe.getGeneratedKeys();
            if (resultSet.isBeforeFirst()) {
                resultSet.next();
                insertId = resultSet.getInt(1);
                adres.setId(insertId);
            }
            created=true;
		} catch (SQLException e) {
			einde();
			throw new SQLException("Kon geen nieuw adres in database opslaan");
		}
		einde();
		return created;
	}
	
	
	public  Adres getAdres(int id) throws SQLException{
		Adres adres=null;
		initialiseer();

		try {
			pStatementGet.setObject(1, id);

			ResultSet resultSet = pStatementGet.executeQuery();
			if (resultSet.isBeforeFirst()) {
                resultSet.next();

                int id1 = resultSet.getInt(1);
                String straatnaam =  resultSet.getString(2);
                int huisnummer =  resultSet.getInt(3);
                String toevoeging =  resultSet.getString(4);
                String postcode =  resultSet.getString(5);
                String woonplaats =  resultSet.getString(6);
                AdresType adresType =  toAdresType(resultSet.getString(7));
                
                adres = new Adres (adresType,straatnaam,huisnummer,toevoeging, postcode, woonplaats);
                adres.setId(id1);
            }
            else{
            	einde();
            	throw new SQLException("Kon gevraagde adres niet vinden in database");
            }
            
		} catch (SQLException e) {
			einde();
			throw new SQLException("Kon geen adres uit database ophalen");
		}
		einde();
		return adres;
	}

	
	public  boolean updateAdres(Adres gewijzigdAdres, int adresId) throws SQLException {
		boolean updated=false;
		initialiseer();
		try {
			pStatementUpdate.setObject(1, gewijzigdAdres.getStraatnaam());
			pStatementUpdate.setObject(2, gewijzigdAdres.getHuisnummer());
			pStatementUpdate.setObject(3, gewijzigdAdres.getToevoeging());
			pStatementUpdate.setObject(4, gewijzigdAdres.getPostcode());
			pStatementUpdate.setObject(5, gewijzigdAdres.getWoonplaats());
			pStatementUpdate.setObject(6, gewijzigdAdres.getAdresType().toString());
			pStatementUpdate.setObject(7, adresId);
			pStatementUpdate.executeUpdate();
			updated=true;
			gewijzigdAdres.setId(adresId);
		}
		catch (SQLException e) {
			einde();
			throw new SQLException("Kon adres niet in database opslaan");
		}
		einde();
		return updated;
	}
	
	
	
	public boolean deleteAdres(int id) throws SQLException {
		boolean deleted=false;
		initialiseer();
		try {
			pStatementDeleteAdres.setObject(1, id);
			pStatementDeleteAdres.executeUpdate();
			deleted=true;
		}
		catch (SQLException e) {
			einde();
			throw new SQLException("Kon adres niet verwijderen uit database");
		}
		einde();
		return deleted;
	}
	
	public boolean deleteAdres(Adres teVerwijderenAdres) throws SQLException {
		return deleteAdres(teVerwijderenAdres.getId());
	}
	
}
