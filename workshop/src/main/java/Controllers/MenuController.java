package Controllers;

import data.DaoFactory;
import data.DatabaseSoort;

public final class MenuController {
//	private static DatabaseSoort databasesoort;
	
	public static void setDatabase(int database) {
		if (database==1) {
			DaoFactory.setDatabaseMYSQL(true);
		}
		else {
			DaoFactory.setDatabaseMYSQL(false);
		}
	}
	
/*	public static DatabaseSoort getDatabase() {
		return databasesoort;
	}
*/
	public static void setConnectionPool(int connectionPool) {
		if(connectionPool == 1) {
			data.ConnectieFactory.setConnectiePool(true);
		}
		else 
			data.ConnectieFactory.setConnectiePool(false);
	}
}
