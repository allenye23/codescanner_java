package scanner.mongo.dao;


import com.mongodb.client.MongoDatabase;
import scanner.mongo.core.DataSource;

public class BasicDao {
	private MongoDatabase dataBase;
	
	public BasicDao(){
		dataBase = DataSource.getInstance().getDatabase();
	}
	
	public MongoDatabase getDataBase(){
		return dataBase;
	}

	

}
