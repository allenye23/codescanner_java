package scanner.mongo.core;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import scanner.utils.PropertiesUtil;

import java.util.Properties;

public class DataSource {
	
	private final static MongoClientURI mongoClientURI;
	
	private MongoClient client;
	static{
		Properties properties = PropertiesUtil.getProp();
		mongoClientURI = new MongoClientURI(properties.getProperty("mongo.db.uri"));
	}
	
	
	private volatile static DataSource dataSource = null;
	
	private  DataSource(){
		client = new MongoClient(mongoClientURI);
	}
	
	public static DataSource getInstance(){
		if(dataSource==null){
			synchronized(DataSource.class){
				if(dataSource==null){
					dataSource = new DataSource();
				}
			}
		}
		
		return dataSource;
	}
	
	public MongoDatabase getDatabase(){
//		try{
			return client.getDatabase(mongoClientURI.getDatabase());
//		}catch(Exception e){
//			client.close();
//			client = new MongoClient(new MongoClientURI("mongodb://"+HOST+":"+PORT));
//			return client.getDatabase(DB_NAME);
//		}
		
	}
}
