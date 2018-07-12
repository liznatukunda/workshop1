package data;

public class DaoFactory {
	
	
	public static AccountDao getAccountDao(DatabaseSoort databaseSoort) {
		AccountDao accountDao;
		
		switch (databaseSoort) {
		case MYSQLDB:
			accountDao = new AccountDaoImplement();
			break;
		default:
			accountDao= new AccountDaoMongoImplement();
		}
		return accountDao;
	}
	
	public static AdresDao getAdresDao(DatabaseSoort databaseSoort) {
		AdresDao adresDao;
		
		switch (databaseSoort) {
		case MYSQLDB:
			adresDao = new AdresDaoImplement();
			break;
		default:
			adresDao= new AdresDaoMongoImplement();
		}
		return adresDao;
	}
	
	public static ArtikelDao getArtikelDao(DatabaseSoort databaseSoort) {
		ArtikelDao artikelDao;
		
		switch (databaseSoort) {
		case MYSQLDB:
			artikelDao = new ArtikelDaoImplement();
			break;
		default:
			artikelDao= new ArtikelDaoMongoImplement();
		}
		return artikelDao;
	}
	
	public static BestellingDao getBestellingDao(DatabaseSoort databaseSoort) {
		BestellingDao bestellingDao;
		
		switch (databaseSoort) {
		case MYSQLDB:
			bestellingDao = new BestellingDaoImplement();
			break;
		default:
			bestellingDao= new BestellingDaoMongoImplement();
		}
		return bestellingDao;
	}
	
	public static BestelregelDao getBestelregelDao(DatabaseSoort databaseSoort) {
		BestelregelDao bestelregelDao;
		
		switch (databaseSoort) {
		case MYSQLDB:
			bestelregelDao = new BestelregelDaoImplement();
			break;
		default:
			bestelregelDao= new BestelregelDaoMongoImplement();
		}
		return bestelregelDao;
	}
	
	public static KlantDao getKlantDao(DatabaseSoort databaseSoort) {
		KlantDao klantDao;
		
		switch (databaseSoort) {
		case MYSQLDB:
			klantDao = new KlantDaoImplement();
			break;
		default:
			klantDao= new KlantDaoMongoImplement();
		}
		return klantDao;
	}
	
	
	
}
