package onlineReport;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;

public class RepOnline {
	private static RepOnline rep=null;
	private String token="";

	private RepOnline(String token) {
		this.token= token;
		// TODO Auto-generated constructor stub
	}

	public static RepOnline getInstance(String token) {
		if(token== null || token == "") {
			System.out.println("Please enter a token");
			return null;
		}else {
			if(rep==null) {
				rep = new RepOnline(token);
			}
		}
		return rep;
	}

	public void insertTestCase(String id,String name,String desc,String status,ArrayList<Step> stepslist)throws Exception {
		String url1 ="http://testapi-priyo.herokuapp.com/add-testcase";
		JSONObject testcase = new JSONObject();
		testcase.put("tc_id", id);
		testcase.put("tc_name", name);
		testcase.put("tc_desc", desc);
		testcase.put("tc_status", status);
		String pattern = "ddMMyyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		testcase.put("tc_executed",date);
		testcase.put("token", this.token);

		JSONArray steps = new JSONArray();


		stepslist.forEach((step)->{
			JSONObject ob = new JSONObject();
			ob.put(step.stepname, step.status?"PASS":"FAIL");
			steps.add(ob);
		});

		testcase.put("steps",steps);

		CloseableHttpClient httpClient = HttpClientBuilder.create().build();

		try {
			HttpPost request = new HttpPost(url1);
			StringEntity params = new StringEntity(testcase.toString());
			request.addHeader("content-type", "application/json");
			request.setEntity(params);
			HttpResponse  response = httpClient.execute(request);
			System.out.println(response);

			// handle response here...
		} catch (Exception ex) {
			// handle exception here
		} finally {
			httpClient.close();
		}
	}

	public class Step{
		String stepname;
		boolean status;

		public Step(String stepname, boolean status) {
			this.stepname = stepname;
			this.status = status;
		}
	}
}
