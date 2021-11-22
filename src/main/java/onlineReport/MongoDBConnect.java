package onlineReport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.json.simple.JSONObject;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import DriverUtils.Config;


public class MongoDBConnect {
	
	
	private static MongoDatabase db;
	private static MongoCollection<Document> col;
	private static String s;
	private static Config config;
	

	public static void mongoDBConnect() {
		config = new Config();
		s= config.getConfigval("MongoDbUpload");
		if(s.equalsIgnoreCase("N"))
			return;
		
		ConnectionString connectionString = new ConnectionString("mongodb+srv://Priyo:3JXC8pViYPWX1mzl@automationcluster.wqskx.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
		MongoClientSettings settings = MongoClientSettings.builder()
				.applyConnectionString(connectionString)
				.build();
		MongoClient mongoClient = MongoClients.create(settings);
		db = mongoClient.getDatabase("Automation");
		
		col = db.getCollection("Execution");
		
	}
	
	public static void insertTestCaseMongoDB(String tc_id,
			String tc_name,String tc_status,String tc_desc,JSONObject steps) {
		
		if(s.equalsIgnoreCase("N"))
			return;
		
		Document doc = new Document();
		String pattern = "ddMMyyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		
		doc
		.append("tc_id", tc_id)
		.append("tc_name", tc_name)
		.append("tc_status", tc_status)
		.append("tc_desc", tc_desc)
		.append("tc_executed_on",date)
		.append("steps", steps);
		
		col.insertOne(doc);
	}
	

}
