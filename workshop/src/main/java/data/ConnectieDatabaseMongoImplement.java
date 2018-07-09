package data;


import com.mongodb.client.*;
import com.mongodb.MongoClient;

public class ConnectieDatabaseMongoImplement {
	
	MongoClient mc;

   
	   public MongoDatabase getMongoDB() {
		   mc=new MongoClient("127.0.0.1",27017);
		   
		   MongoDatabase db = mc.getDatabase("boerPiet_LOP");
		   return db;
	   }
	   
	   public void close() {
		   mc.close();
	   }

	
}
