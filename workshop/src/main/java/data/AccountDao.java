package data;

import java.sql.*;

import domein.Account;
import domein.Klant;
import domein.Account.Rol;
import java.util.ArrayList;

public class AccountDao implements AccountDaoInterface {
	
private  static Connection con = ConnectieDatabase.getConnection();
private PreparedStatement stmt = null;



public static Rol toRol (String rol) {
	if (rol.equals("klant")){
		return Account.Rol.klant;
	}
	if (rol.equals("medewerker")){
		return Account.Rol.medewerker;
	}
	else return Account.Rol.beheerder;
}
	
	public Integer createAccount(Account account){
		int insertId = 0;
		String sql = "INSERT INTO Account (username, password, rol) VALUES (?,?,?);";
		try {
			stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setObject(1, account.getUserNaam());
			stmt.setObject(2, account.getPassword()); 
			stmt.setObject(3, account.getRol().toString());
			stmt.executeUpdate();
			ResultSet resultSet = stmt.getGeneratedKeys();
            if (resultSet.isBeforeFirst()) {
                resultSet.next();
                insertId = resultSet.getInt(1);
                account.setId(insertId);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return insertId;
	}
	
	public Account getAccount(int id){
		String sql = "SELECT * FROM Account WHERE id=?";
		Account returnedAccount = null;
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, id);
			ResultSet resultSet = stmt.executeQuery();
            if (resultSet.isBeforeFirst()) {
                resultSet.next();
              
               int id1 = resultSet.getInt(1);
                String userNaam =  resultSet.getString(2);
                String password =  resultSet.getString(3);
               Rol rol = toRol(resultSet.getString(4));
                 returnedAccount = new Account (userNaam,password,rol);
                
                returnedAccount.setId(id1);
               		
                
             //   System.out.println("User gevonden: " + returnedAccount.getUserNaam()); 
            }
            else{
            	System.err.println("Geen User gevonden!");
            }
            
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
		
		return returnedAccount;
	}
	
	public boolean updateAccount(int id, String userNaam, String password,Rol rol ){
		String sql = "UPDATE Account SET username = ?, password = ?, rol = ? WHERE id = ?";
		int rows = -1;
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setObject(1, userNaam);
			stmt.setObject(2, password);
			stmt.setObject(3, rol.toString());
			stmt.setObject(4, id);
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
	
	public boolean deleteAccount(int id){
		String sql = "DELETE FROM Account WHERE id = ?";
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
	
	public boolean deleteAccount(Account account){
		return deleteAccount(account.getId());
	}
	
	public ArrayList<Account> getAlleAccounts(){
		String sql = "SELECT * FROM Account;";
		ArrayList<Account> returnedAccounts = new ArrayList<>();
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				ResultSet resultSet = stmt.executeQuery();
	            while(resultSet.next()){
	            	
	            	int id1 = resultSet.getInt(1);
	            	 String userNaam =  resultSet.getString(2);
	                 String password =  resultSet.getString(3);
	                 Rol rol =  toRol(resultSet.getString(4));
	                 
	                 Account returnedAccount = new Account (userNaam,password,rol);
	               
	                returnedAccount.setId(id1);
	            
	                returnedAccounts.add(returnedAccount); 
	            }
	            
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return returnedAccounts;
		}
	
		
	
}


