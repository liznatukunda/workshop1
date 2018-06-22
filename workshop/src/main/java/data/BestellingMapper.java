package data;

import java.sql.*;

import domein.Bestelling;


public class BestellingMapper {
	
	
	private  static Connection con = ConnectieDatabase.getConnection();
	private PreparedStatement stmt = null;
		
		public int createBestelling(Bestelling bestelling){
			int insertId = -1;
			String sql = "INSERT INTO Bestelling (totaalprijs) VALUES (?);";
			try {
				stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
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
	               Rol rol = (Rol) resultSet.getObject(4);
	                 returnedAccount = new Account (userNaam,password,rol);
	                
	                returnedAccount.setId(id1);
	               		
	                
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
		
		public boolean updateAccount(int id, String userNaam, String password,Rol rol ){
			String sql = "UPDATE Account SET userNaam = ?, password = ?, rol = ? WHERE id = ?";
			int rows = -1;
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
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