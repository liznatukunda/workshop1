package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import domein.Adres;
import domein.Adres.AdresType;

public class AdresMapper {

	private  Connection con;
	private  PreparedStatement pStatementVoegToe;
	private  PreparedStatement pStatementGet;
	private  PreparedStatement pStatementUpdateStraatnaam;
	private  PreparedStatement pStatementUpdateHuisnummer;
	private  PreparedStatement pStatementUpdateToevoeging;
	private  PreparedStatement pStatementUpdatePostcode;
	private  PreparedStatement pStatementUpdateWoonplaats;
	private  PreparedStatement pStatementUpdateAdresType;
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
			pStatementUpdateStraatnaam=con.prepareStatement("UPDATE adres SET straatnaam=? WHERE id=?");
			pStatementUpdateHuisnummer=con.prepareStatement("UPDATE adres SET huisnummer=? WHERE id=?");
			pStatementUpdateToevoeging=con.prepareStatement("UPDATE adres SET toevoeging=? WHERE id=?");
			pStatementUpdatePostcode=con.prepareStatement("UPDATE adres SET postcode=? WHERE id=?");
			pStatementUpdateWoonplaats=con.prepareStatement("UPDATE adres SET woonplaats=? WHERE id=?");
			pStatementUpdateAdresType=con.prepareStatement("UPDATE adres SET adrestype=? WHERE id=?");
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

	
	public  boolean updateAdresStraatnaam(String nieuweStraatnaam, int id) throws SQLException {
		boolean updated=false;
		initialiseer();
		try {
			pStatementUpdateStraatnaam.setObject(2, id);
			pStatementUpdateStraatnaam.setObject(1, nieuweStraatnaam);
			pStatementUpdateStraatnaam.executeUpdate();
			updated=true;
		}
		catch (SQLException e) {
			einde();
			throw new SQLException("Kon gewijzigde straatnaam niet in database opslaan");
		}
		einde();
		return updated;
	}
	
	public  boolean updateAdresHuisnummer(int nieuwHuisnummer, int id) throws SQLException {
		boolean updated=false;
		initialiseer();
		try {
			pStatementUpdateHuisnummer.setObject(2, id);
			pStatementUpdateHuisnummer.setObject(1, nieuwHuisnummer);
			pStatementUpdateHuisnummer.executeUpdate();
			updated=true;
		}
		catch (SQLException e) {
			einde();
			throw new SQLException("Kon gewijzigde huisnummer niet in database opslaan");
		}
		einde();
		return updated;
	}
	
	public  boolean updateAdresToevoeging(String nieuweToevoeging, int id) throws SQLException {
		boolean updated=false;
		initialiseer();
		try {
			pStatementUpdateToevoeging.setObject(2, id);
			pStatementUpdateToevoeging.setObject(1, nieuweToevoeging);
			pStatementUpdateToevoeging.executeUpdate();
			updated=true;
		}
		catch (SQLException e) {
			einde();
			throw new SQLException("Kon gewijzigde toevoeging niet in database opslaan");
		}
		einde();
		return updated;
	}
	
	public  boolean updateAdresPostcode(String nieuwePostcode, int id) throws SQLException {
		boolean updated=false;
		initialiseer();
		try {
			pStatementUpdatePostcode.setObject(2, id);
			pStatementUpdatePostcode.setObject(1, nieuwePostcode);
			pStatementUpdatePostcode.executeUpdate();
			updated=true;
		}
		catch (SQLException e) {
			einde();
			throw new SQLException("Kon gewijzigde postcode niet in database opslaan");
		}
		einde();
		return updated;
	}
	
	public  boolean updateAdresWoonplaats(String nieuweWoonplaats, int id) throws SQLException {
		boolean updated=false;
		initialiseer();
		try {
			pStatementUpdateWoonplaats.setObject(2, id);
			pStatementUpdateWoonplaats.setObject(1, nieuweWoonplaats);
			pStatementUpdateWoonplaats.executeUpdate();
			updated=true;
		}
		catch (SQLException e) {
			einde();
			throw new SQLException("Kon gewijzigde postcode niet in database opslaan");
		}
		einde();
		return updated;
	}
	
	public  boolean updateAdresAdresType(AdresType nieuweAdresType, int id) throws SQLException {
		boolean updated=false;
		initialiseer();
		try {
			pStatementUpdateAdresType.setObject(2, id);
			pStatementUpdateAdresType.setObject(1, nieuweAdresType.toString());
			pStatementUpdateAdresType.executeUpdate();
			updated=true;
		}
		catch (SQLException e) {
			einde();
			throw new SQLException("Kon gewijzigde adrestype niet in database opslaan");
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
	
}
