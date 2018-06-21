package Dao;

import java.sql.*;
import java.sql.Connection;
import databaseconnection.dbconnect;
import pojo.Account;

public class AccountDao {
	
private dbconnect connection = new dbconnect();
private PreparedStatement stmt = null;
	
	public Integer createAccount(Account account){
		Integer insertId = 0;
		String sql = "INSERT INTO Account (username, password, rol) VALUES (?,?,?);";
		try {
			stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setObject(1, account.getUserNaam());
			stmt.setObject(2, account.getPassword()); 
			stmt.setObject(3, account.getRol());
			stmt.executeUpdate();
			ResultSet resultSet = stmt.getGeneratedKeys();
            if (resultSet.isBeforeFirst()) {
                resultSet.next();
                insertId = resultSet.getInt(1);
                System.out.println("Id " + insertId + " voor User " + account.getUserNaam());
                account.setId(insertId);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return insertId;
	}
	
	public Account getAccount(Integer id){
		String sql = "SELECT * FROM Account WHERE id=?";
		Account returnedAccount = new Account();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setObject(1, id);
			ResultSet resultSet = stmt.executeQuery();
            if (resultSet.isBeforeFirst()) {
                resultSet.next();
                returnedAccount.setId(resultSet.getInt(1));
                returnedAccount.setUserNaam(resultSet.getString(2));
                returnedAccount.setPassword(resultSet.getString(3));
                returnedAccount.setRol(resultSet.getString(4));
                System.out.println("User gevonden: " + returnedAccount.getUserNaam()); 
            }
            else{
            	System.err.println("Geen User gevonden!");
            }
            
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return returnedAccount;
	}
	
	public boolean updateAccount(Integer id, String userNaam, String password,Rol rol ){
		String sql = "UPDATE Account SET userNaam = ?, password = ?, rol = ? WHERE id = ?";
		int rows = -1;
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setObject(1, id);
			stmt.setObject(2, userNaam);
			stmt.setObject(3, password);
			stmt.setObject(4, rol);
			rows = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			
		}
		return rows > 0;
	}
	
	public boolean updateAccount(Account nieuwAccount){
		return updateAccount( nieuwAccount.getId(),nieuwAccount.getUserNaam(), nieuwAccount.getPassword(), nieuwAccount.getRol());
	}
	
	public boolean deleteAccount(Integer id){
		String sql = "DELETE FROM Account WHERE id = ?";
		int rows = -1;
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setObject(1, id);
			rows = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows > 0;
	}
	
	public boolean deleteAccount(Account account){
		return deleteAccount(account.getId());
	}
}

/* ALTER TABLE Account
ADD CONSTRAINT check_Role
  CHECK (role IN ('Administrator', 'medewerker', 'Klant'));  */
