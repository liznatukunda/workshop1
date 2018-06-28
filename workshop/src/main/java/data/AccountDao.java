package data;
import domein.Account;
import domein.Account.Rol;

public interface AccountDao {
	public abstract void createAccount(Account account);	
	public abstract Account getAccount(int id);
	public abstract boolean updateAccount(int id, String userNaam, String password,Rol rol );
	public abstract boolean updateAccount(Account nieuwAccount);
	public abstract boolean deleteAccount(int id);
	

}
