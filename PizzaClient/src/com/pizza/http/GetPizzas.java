package com.pizza.http;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

//this is the class created for the AsyncTask
//AsyncTask is an Android base class you extend for your web calls
public class GetPizzas extends AsyncTask
{
	@Override
	protected Object doInBackground(Object... arg0) {
		//this is what we will return
		String result = null;
		
		//the parameters on this URL will generate a new pizza on the server
		String url = "http://10.0.2.2:8000/pizzas?customer=Ashley&size=MEDIUM&toppings=CHEESE-MUSHROOMS-PEPPERONI";
		
		//the http client to make the call
		HttpClient httpclient = new DefaultHttpClient();

		//we are making the request over GET, so we create a GET object
		HttpGet httpget = new HttpGet(url); 

		//we call the server for the response inside this try...catch because these often fail
		HttpResponse response;
		try {
			//response captures what happens when we execute
			response = httpclient.execute(httpget);

			//The entity is the value the request returns
			HttpEntity entity = response.getEntity();

			//if we managed to grab anything at all
			if (entity != null) {
				//the entity returns the content as an input stream
				//we could have gotten text, an image, ect, so it's an input stream
				InputStream instream = entity.getContent();
				
				//however, we know we want a string, so we convert this to a string
				result = convertToString(instream);
				
				//this prints to LogCat for our reference
				System.out.println(result);
				
				//close the stream
				instream.close();
			}


		} 
		catch (Exception e) 
		{
			//you can change your code to handle an exception how you want
			System.out.println(e.toString());
		}

		return result;
		
	}

	//converts an input stream to a string
	private String convertToString(InputStream is) {
		//buffered reader reads the input stream
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		
		//StringBuffer creates the string efficiently
		StringBuffer buff = new StringBuffer();

		String line = null;
		try {
			//iterate over all the strings and build the value
			line = reader.readLine();
			while (line != null) 
			{
				buff.append(line + "\n");
				line = reader.readLine();
			}
			is.close();
		} 
		catch (Exception e) 
		{
			//you can change your code to handle an exception how you want
			System.out.println(e.toString());
		} 
		return buff.toString();
	}
}
