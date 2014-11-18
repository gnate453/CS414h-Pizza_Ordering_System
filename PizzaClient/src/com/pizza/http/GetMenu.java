package com.pizza.http;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import android.os.AsyncTask;

//this is the class created for the AsyncTask
//AsyncTask is an Android base class you extend for your web calls
public class GetMenu extends AsyncTask<String, Void, List<String>> {
	@Override
	protected List<String> doInBackground(String... arg0) {
		//this is what we will return
		String xmlResult = null;
		
		//the parameters on this URL will generate a new pizza on the server
		String url = "http://192.168.0.101:8000/menu";
		
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
				xmlResult = convertToString(instream);
				
				//this prints to LogCat for our reference
				System.out.println(xmlResult);
				
				//close the stream
				instream.close();
			}
		} 
		catch (Exception e) {
			//you can change your code to handle an exception how you want
			System.out.println(e.toString());
		}

		return xmlParser(xmlResult);
	}
	
	private List<String> xmlParser(String xmlInput) {
		List<String> itemNames = new ArrayList<String>();
		List<Double> itemPrices = new ArrayList<Double>();
		
		Document dom;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {
			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			//parse using builder to get DOM representation of the XML file
			dom = db.parse(new InputSource(new ByteArrayInputStream(xmlInput.getBytes("utf-8"))));
			
			//get the root elememt
			Element docEle = dom.getDocumentElement();
			
			//get a nodelist of <Item> elements
			NodeList nl = docEle.getElementsByTagName("Item");
			//System.out.println(nl.getLength());
			if(nl != null && nl.getLength() > 0) {
				for(int i = 0 ; i < nl.getLength();i++) {
					//get the item element
					Element el = (Element)nl.item(i);
					
					//get the MenuItem object and adds it to list
					itemNames.add(getTextValue(el, "Name"));
					itemPrices.add(getDoubleValue(el, "Price"));
				}
			}
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
		/*final String[] matrix = { "_id", "name", "value" };
		MatrixCursor cursor = new MatrixCursor(matrix);
		
		DecimalFormat formatter = new DecimalFormat("##,##0.00");
		for (int i=0; i<itemNames.size(); i++) {
			cursor.addRow(new Object[] { i, "", itemNames.get(i)});
		    cursor.addRow(new Object[] { i, "", "$" + formatter.format(itemPrices.get(i)) });
		}*/
		
		return itemNames;
	}
	
	private String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			Element el = (Element)nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}

		return textVal;
	}
	
	private double getDoubleValue(Element ele, String tagName) {
		//in production application you would catch the exception
		return Double.parseDouble(getTextValue(ele,tagName));
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
		catch (Exception e) {
			//you can change your code to handle an exception how you want
			System.out.println(e.toString());
		} 
		return buff.toString();
	}
}
