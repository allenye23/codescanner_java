package scanner.mongo.dao.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.client.MongoCollection;
import di.scanner.mongo.dao.BasicDao;
import di.scanner.mongo.dao.MethodInvocationResultDao;
import di.scanner.mongo.model.MethodInvocationResult;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;


public class MethodInvocationResultDaoImpl extends BasicDao implements MethodInvocationResultDao{

	private final static String TABLE_NAME = "methodInvocation";
	MongoCollection<Document> collection;

	public MethodInvocationResultDaoImpl(){
		super();
		collection = getDataBase().getCollection(TABLE_NAME);
	}


	public void save(MethodInvocationResult result) {
		Document document = Document.parse(JSON.toJSONString(result));
		collection.insertOne(document);
	}

	public List<MethodInvocationResult> search(){
		List<Document> documentList = new ArrayList<>();
		List<MethodInvocationResult> methodInvocationResultList = new ArrayList<>();
		collection.find().into(documentList);
		for (Document document: documentList) {
			MethodInvocationResult methodInvocationResult = JSONObject.parseObject(document.toJson(),MethodInvocationResult.class);
			methodInvocationResultList.add(methodInvocationResult);
		}

		return methodInvocationResultList;
	}
}
