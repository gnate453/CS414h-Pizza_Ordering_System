package cs414.groupH.a5.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

public class InStoreHttpClient {
	
	private static final String ipAddr = "192.168.0.101:8000";
	
	private static HttpClient httpclient = HttpClientBuilder.create().build();
	
	public static String getEmpType(String empID) {
		String result = null;
		
		String url = "http://"+ipAddr+"/empTypeCheck?empID="+empID;
		HttpGet httpget = new HttpGet(url);
		
		HttpResponse response;
		try {
			//response captures what happens when we execute
			response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				InputStream instream = entity.getContent();
				result = convertToString(instream);
				
				System.out.println(result);
				
				//close the stream
				instream.close();
			}
		} 
		catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}
	
	public Object sendRequest() {
		//this is what we will return
		String result = null;
		
		//the parameters on this URL will generate a new pizza on the server
		String url = "http://10.0.2.2:8000/pizzas?customer=Ashley&size=MEDIUM&toppings=CHEESE-MUSHROOMS-PEPPERONI";
		
		
		HttpGet httpget = new HttpGet(url); 

		//we call the server for the response inside this try...catch because these often fail
		HttpResponse response;
		try {
			//response captures what happens when we execute
			response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				InputStream instream = entity.getContent();
				result = convertToString(instream);
				
				System.out.println(result);
				
				//close the stream
				instream.close();
			}
		} 
		catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;
	}
	
	//converts an input stream to a string
	private static String convertToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuffer buff = new StringBuffer();

		String line = null;
		try {
			line = reader.readLine();
			while (line != null) 
			{
				buff.append(line + "\n");
				line = reader.readLine();
			}
			is.close();
		} 
		catch (Exception e) {
			System.out.println(e.toString());
		} 
		return buff.toString();
	}
}
