package mongodb.dao;

import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

import main.model.Folder;
import mongodb.main.MongoDBMain;

public class FolderDAOImpl implements FolderDAO{
	private MongoOperations mongoOps;
	private static final String COLLECTION = MongoDBMain.getCollection();
	public static final MongoClient mongo = MongoDBMain.getMongoClient();
	
	public FolderDAOImpl(MongoOperations mongoOps){
		this.mongoOps=mongoOps;
	}

	public void create(Folder folder) {
		System.out.println("DAO: Adding new folder");
		this.mongoOps.insert(folder, COLLECTION);
		System.out.println("DAO: Added!");
		
	}

	public List<Folder> getAllFolders() {
		System.out.println("DAO: Return all folders");
		return this.mongoOps.findAll(Folder.class, COLLECTION);
	}

	public void update(Folder folder) {
		System.out.println("DAO: Querying folder id:"+folder.getId());
		Query query = new Query(Criteria.where("_id").is(folder.getId()));
		Update update = new Update();
		update.set("folderName", folder.getFolderName());
		update.set("documentIdList", folder.getDocumentList());
		update.set("lastUpdate", folder.getLastUpdate());
		update.set("folderStatus", folder.getFolderStatus());
		update.set("numberOfDocument", folder.getNumberOfDocuments());
		update.set("creatorId", folder.getCreatorId());
		System.out.println("DAO: Updating folder id:"+folder.getId());
		this.mongoOps.findAndModify(query, update, Folder.class, COLLECTION);
		
	}


	public void delete(String id) {
		System.out.println("DAO: Querying folder id:"+id);
		Query query = new Query(Criteria.where("_id").is(id));
		System.out.println("DAO: Deleting folder id:"+id);
        WriteResult result = this.mongoOps.remove(query, Folder.class, COLLECTION);
        System.out.println("DAO: Deleted!");
		
	}

	public Folder readById(String id) {
		System.out.println("DAO: Querying folder id:"+id);
		Query query = new Query(Criteria.where("_id").is(id));
		System.out.println("DAO: Return folder");
		return this.mongoOps.findOne(query, Folder.class, COLLECTION);
	}
	
	

}
