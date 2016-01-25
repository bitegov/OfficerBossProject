package mongodb.main;

import java.net.UnknownHostException;
import com.mongodb.MongoClient;


public class MongoDBMain {
	 
    private static final String DB_NAME = "documenttest";
    private static final String DOCUMENT_COLLECTION = "documenttestonly";
    private static final String MONGO_HOST = "localhost";
    private static final int MONGO_PORT = 27018;
    private static MongoClient mongo;
 
    public static void run() {
        try {
        	System.out.println("connecting to mongodb at"+MONGO_HOST+":"+MONGO_PORT+"...\ndatabase name: "+DB_NAME+"\ncollection: "+DOCUMENT_COLLECTION);
            mongo = new MongoClient(
                    MONGO_HOST, MONGO_PORT);
            System.out.println("connected!");
             
        } catch (UnknownHostException e) {
        	System.err.println("Cannot connect to "+MONGO_HOST+":"+MONGO_PORT);
            e.printStackTrace();
        }
    }
    
    public static MongoClient getMongoClient(){
    	return mongo;
    }
    public static String getDBName(){
    	return DB_NAME;
    }
    public static String getCollection(){
    	return DOCUMENT_COLLECTION;
    }
    public static String getHost(){
    	return MONGO_HOST;
    }
    public static int getPort(){
    	return MONGO_PORT;
    }
 
}