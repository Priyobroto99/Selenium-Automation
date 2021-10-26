package DriverUtils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResultSender 
{
	private static final ObjectMapper OM = new ObjectMapper();
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String CONTENT_TYPE_VALUE = "application/json";
    private static final String ELASTICSEARCH_URL = "http://127.0.0.1:9200/status/default/";
    
    
    public static void send(final TestStatus testStatus)
    {    	
        try 
        {
        	Log.addToLog(OM.writeValueAsString(testStatus));
			HttpClient client = HttpClientBuilder.create().build();
			HttpPost post = new HttpPost(ELASTICSEARCH_URL);
			
			StringEntity entity = new StringEntity(OM.writeValueAsString(testStatus));
			post.setEntity(entity);
			post.setHeader("Accept", "application/json");
			post.setHeader("Content-type", "application/json");
		 
		    HttpResponse response = client.execute(post);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
