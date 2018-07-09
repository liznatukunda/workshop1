package data;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Sorts.descending;
import static com.mongodb.client.model.Sorts.orderBy;
import data.ConnectieDatabaseMongoImplement;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import domein.Account;
import domein.Artikel;
import domein.Account.Rol;

public class AccountDaoMongoImplement implements AccountDao{
	ConnectieDatabaseMongoImplement mongoConnector;
	
	public AccountDaoMongoImplement() {
        mongoConnector = new ConnectieDatabaseMongoImplement();
    }
	
    private Account convertDocumentToAccount(Document doc){
        Account account= new Account(doc.getString("usernaam"),doc.getString("password"),Rol.toRol(doc.getString("rol")));
        account.setId(doc.getInteger("id"));
        return account;
    }
    
    private Document convertAccountToDoc(Account account){
        Document document = new Document();
        document.append("id", account.getId());
        document.append("usernaam", account.getUserNaam());
        document.append("password", account.getPassword());
        document.append("rol", account.getRol().toString());
        return document;
    }
    
    private Integer getNextId(){
        int id = 0;
        MongoCollection<Document> collection = mongoConnector.getMongoDB().getCollection("account");
        if(collection.countDocuments() > 0){
            Document highestId = collection.find().sort(orderBy(descending("id"))).first();
            id = highestId.getInteger("id") + 1;
        }
        else {
            id = 1;
        }
        mongoConnector.close();
        return id;
    }
	
	@Override
	public Integer createAccount(Account account) {
		account.setId(getNextId());
        MongoCollection<Document> collection = mongoConnector.getMongoDB().getCollection("account");
        collection.insertOne(convertAccountToDoc(account));
        mongoConnector.close();
        return account.getId();
	}

	@Override
	public Account getAccount(int id) {
		MongoCollection<Document> collection = mongoConnector.getMongoDB().getCollection("account");
        Document doc = collection.find(eq("id", id)).first();
        mongoConnector.close();
        return convertDocumentToAccount(doc);
	}

	@Override
	public boolean updateAccount(int id, String userNaam, String password, Rol rol) {
		MongoCollection<Document> collection = mongoConnector.getMongoDB().getCollection("account");
    	Account nieuwAccount=new Account (userNaam, password, rol);
    	nieuwAccount.setId(id);
    	collection.findOneAndReplace(eq("id", nieuwAccount.getId()), convertAccountToDoc(nieuwAccount));
        mongoConnector.close();
        return true;
	}

	@Override
	public boolean updateAccount(Account nieuwAccount) {
		MongoCollection<Document> collection = mongoConnector.getMongoDB().getCollection("account");
        collection.findOneAndReplace(eq("id", nieuwAccount.getId()), convertAccountToDoc(nieuwAccount));
        mongoConnector.close();
        return true;
	}

	@Override
	public boolean deleteAccount(int id) {
		MongoCollection<Document> collection = mongoConnector.getMongoDB().getCollection("account");
        collection.findOneAndDelete(eq("id", id));
        mongoConnector.close();
        return true;
	}

	@Override
	public ArrayList<Account> getAlleAccounts() {
		ArrayList<Account> accounts = new ArrayList<>();
        MongoCollection<Document> collection = mongoConnector.getMongoDB().getCollection("account");
        FindIterable<Document> allDocuments = collection.find();
        MongoCursor<Document> iterator = allDocuments.iterator();
        while(iterator.hasNext()){
            Document doc = iterator.next();
            accounts.add(convertDocumentToAccount(doc));
        }
        mongoConnector.close();
        return accounts;
	}

	@Override
	public boolean deleteAccount(Account account) {
		return deleteAccount(account.getId());
	}

}
