package Controllers;

import data.DaoFactory;
import domein.Account.Rol;

public final class MenuController {
	private static  Rol ingelogdeRol  = null ;
	
	public static void setDatabase(int database) {
		if (database==1) {
			DaoFactory.setDatabaseMYSQL(true);
		}
		else {
			DaoFactory.setDatabaseMYSQL(false);
		}
	}

	
	public  static void setRol (Rol rol) {
		ingelogdeRol=rol;
		
	}
	
	public  static boolean isBeheerder() {
		if(ingelogdeRol==Rol.beheerder) {
			return true;
		}
		else
		return false;
	}
	
	public static void setConnectionPool(int connectionPool) {
		if(connectionPool == 1) {
			data.ConnectieFactory.setConnectiePool(true);
		}
		else {
			data.ConnectieFactory.setConnectiePool(false);
		}
	}
	
	
}
