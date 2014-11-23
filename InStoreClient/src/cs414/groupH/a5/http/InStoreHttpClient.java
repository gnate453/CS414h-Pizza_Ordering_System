package cs414.groupH.a5.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;

import com.example.pt.ClientProtocolException;
import com.example.pt.DefaultHttpClient;
import com.example.pt.IOException;

import cs414.groupH.a5.gui.InStoreApp;

public class InStoreHttpClient {
	
	private static final String ipAddr = "saint-paul.cs.colostate.edu:55789";
	
	private static HttpClient httpclient = HttpClientBuilder.create().build();
	
	public static boolean loginEmp(String empID, String empPW) {
		String result = null;
		
		String url = "http://"+ipAddr+"/employee?type=Login&id="+empID+"&pw="+empPW;
		HttpGet httpget = new HttpGet(url);
		
		HttpResponse response;
		try {
			//response captures what happens when we execute
			response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				InputStream instream = entity.getContent();
				result = convertToString(instream);
				//close the stream
				instream.close();
				String[] res = result.split(",");
				
				if (res[0].equalsIgnoreCase("valid")) {
					InStoreApp.loginEmployee(empID, res[1], res[2]);
					return true;
				}
				else
					return false;
			}
			else
				return false;
		} 
		catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}
	
	public static String getEmpType(String empID) {
		String result = null;
		
		String url = "http://"+ipAddr+"/employee?type=TypeCheck&id="+empID;
		HttpGet httpget = new HttpGet(url);
		
		HttpResponse response;
		try {
			//response captures what happens when we execute
			response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				InputStream instream = entity.getContent();
				result = convertToString(instream);
				
				//System.out.println(result);
				
				//close the stream
				instream.close();
			}
		} 
		catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}
	
	public static String getOrders() {
		String result = null;
		
		String url = "http://"+ipAddr+"/order?type=get";
		HttpGet httpget = new HttpGet(url);
		
		HttpResponse response;
		try {
			//response captures what happens when we execute
			response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				InputStream instream = entity.getContent();
				result = convertToString(instream);
				
				//System.out.println(result);
				
				//close the stream
				instream.close();
			}
		} 
		catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return XmlParser.parseViewOrder(result);
	}
	
	public static String markComplete(String orderId) {
		String result = null;
		
		String url = "http://"+ipAddr+"/order?type=markComplete&orderId="+orderId;
		HttpGet httpget = new HttpGet(url);
		
		HttpResponse response;
		try {
			//response captures what happens when we execute
			response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				InputStream instream = entity.getContent();
				result = convertToString(instream);
				
				//close the stream
				instream.close();
			}
		} 
		catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}
	
	public static String getOrderCust(String orderId) {
		String result = null;
		
		String url = "http://"+ipAddr+"/order?type=getCust&orderId="+orderId;
		HttpGet httpget = new HttpGet(url);
		
		HttpResponse response;
		try {
			//response captures what happens when we execute
			response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				InputStream instream = entity.getContent();
				result = convertToString(instream);
				
				//close the stream
				instream.close();
			}
		} 
		catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}
	
	public static String[] getOrderAddr(String orderId) {
		String[] result = null;
		
		String url = "http://"+ipAddr+"/order?type=getAddr&orderId="+orderId;
		HttpGet httpget = new HttpGet(url);
		
		HttpResponse response;
		try {
			//response captures what happens when we execute
			response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				InputStream instream = entity.getContent();
				result = convertToString(instream).split(",");
				
				//close the stream
				instream.close();
			}
		} 
		catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}
	
	public static String[] getOrderItems(String orderId) {
		String[] result = null;
		
		String url = "http://"+ipAddr+"/order?type=getItems&orderId="+orderId;
		//HttpGet httpget = new HttpGet(url);
		HttpPost post = new HttpPost(url);
		try {
			post.setEntity(new StringEntity("I'm here"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//post.
		
		HttpResponse response;
		try {
			//response captures what happens when we execute
			response = httpclient.execute(post);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				InputStream instream = entity.getContent();
				result = convertToString(instream).split(",");
				
				//close the stream
				instream.close();
			}
		} 
		catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}
	
	public static String getMenu() {
		String result = null;
		
		String url = "http://"+ipAddr+"/menu?type=get";
		HttpGet httpget = new HttpGet(url);
		
		HttpResponse response;
		try {
			//response captures what happens when we execute
			response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				InputStream instream = entity.getContent();
				result = convertToString(instream);
				
				//System.out.println(result);
				
				//close the stream
				instream.close();
			}
		} 
		catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return XmlParser.parseMenu(result);
	}
	
	public static boolean editMenuItem(String oldName, String newName, String price, String special) {
		String result = null;
		String url = "http://"+ipAddr+"/menu?type=edit&oldName="+oldName+"&newName="+newName+
						"&price="+price+"&special="+special;
		
		url = url.replace(' ', '_');
		
		HttpGet httpget = new HttpGet(url);
		
		HttpResponse response;
		
		try {	
			//response captures what happens when we execute
			response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				InputStream instream = entity.getContent();
				result = convertToString(instream);
				//close the stream
				instream.close();
				String[] res = result.split(",");
				
				if (res[0].equalsIgnoreCase("valid")) {
					return true;
				}
				else
					return false;
			}
			else
				return false;
		} 
		catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}
	
	public static boolean addMenuItem(String newName, String price, String special) {
		String result = null;
		String url = "http://"+ipAddr+"/menu?type=add&newName="+newName+
						"&price="+price+"&special="+special;
		
		url = url.replace(' ', '_');
		
		HttpGet httpget = new HttpGet(url);
		
		HttpResponse response;
		
		try {	
			//response captures what happens when we execute
			response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				InputStream instream = entity.getContent();
				result = convertToString(instream);
				//close the stream
				instream.close();
				String[] res = result.split(",");
				
				if (res[0].equalsIgnoreCase("valid")) {
					return true;
				}
				else
					return false;
			}
			else
				return false;
		} 
		catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}

//	public Object sendRequest() {
//		//this is what we will return
//		String result = null;
//		
//		//the parameters on this URL will generate a new pizza on the server
//		String url = "http://10.0.2.2:8000/pizzas?customer=Ashley&size=MEDIUM&toppings=CHEESE-MUSHROOMS-PEPPERONI";
//		
//		
//		HttpGet httpget = new HttpGet(url); 
//
//		//we call the server for the response inside this try...catch because these often fail
//		HttpResponse response;
//		try {
//			//response captures what happens when we execute
//			response = httpclient.execute(httpget);
//			HttpEntity entity = response.getEntity();
//
//			if (entity != null) {
//				InputStream instream = entity.getContent();
//				result = convertToString(instream);
//				
//				//System.out.println(result);
//				
//				//close the stream
//				instream.close();
//			}
//		} 
//		catch (Exception e) {
//			System.out.println(e.toString());
//		}
//
//		return result;
//	}

	public static Boolean createOrder(String finalXml) {
		HttpClient httpclient = new DefaultHttpClient();
		String result = null;
		try {
			HttpPost httppost = new HttpPost("http://"+ipAddr+"/order?type=place");          
			StringEntity se = new StringEntity(finalXml);			
			httppost.setEntity(se);  			
			HttpResponse httpResponse = httpclient.execute(httppost);
			
			HttpEntity entity = httpResponse.getEntity();

			if (entity != null) {
				InputStream instream = entity.getContent();
				result = convertToString(instream);
				//close the stream
				instream.close();
				

				if (result.equalsIgnoreCase("valid")) {
					return true;
				}
				else
					return false;
			}
			else
				return false;
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
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
				buff.append(line);
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
