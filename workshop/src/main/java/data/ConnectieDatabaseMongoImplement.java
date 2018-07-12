package data;


import com.mongodb.client.*;
import com.mongodb.MongoClient;

public class ConnectieDatabaseMongoImplement {
	
	MongoClient mc;

   
	   public MongoDatabase getMongoDB() {
		   DOM dom = new DOM();
		   mc=new MongoClient(dom.getUrl("mongo"),dom.getPort("mongo"));
		   
		   MongoDatabase db = mc.getDatabase(dom.getDatabase("mongo"));
		   return db;
	   }
	   
	   public void close() {
		   mc.close();
	   }

	
}
