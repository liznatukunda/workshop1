package Controllers;

import data.DaoFactory;
import data.DaoFactory.DatabaseSoort;

public final class FactoryController {
	private static DaoFactory.DatabaseSoort databasesoort;
	
	public static void setDatabase(int database) {
		if (database==1) {
			databasesoort=DatabaseSoort.MYSQLDB;
		}
		else {
			databasesoort=DatabaseSoort.MONGODB;
		}
	}
	
	public static DaoFactory.DatabaseSoort getDatabase() {
		return databasesoort;
	}
}
