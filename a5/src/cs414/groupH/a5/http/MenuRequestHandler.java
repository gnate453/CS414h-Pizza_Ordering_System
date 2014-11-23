package cs414.groupH.a5.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import cs414.groupH.a5.manager.SystemManager;
import cs414.groupH.a5.menu.MenuItem;

public class MenuRequestHandler implements HttpHandler {
	
	private static final int QUERY_TYPE = 0;
	private static final int QUERY_SECOND = 1;
	private static final int QUERY_THIRD = 2;
	private static final int QUERY_FOURTH = 3;
	private static final int QUERY_FIFTH = 4;
	private static final int QUERY_KEY = 0;
	private static final int QUERY_VAL = 1;
	private static final int SC_OK = 200;
	private static final int SC_NOTFOUND = 404;
	//private static final int SC_ERROR = 500;
	
	//this function is called when an HTTP request is made
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		//this is the URL of the request
		URI uri = exchange.getRequestURI();
		
		// Log URL hits
     	System.out.print(new Date()+" - "+uri.getPath()+"?");
		
		//the URL could come with parameters
		String query = uri.getQuery();
		String response = "";
		if (query != null) {

			response = parseMenuQuery(query);
			if (response.equalsIgnoreCase("error") )
				exchange.sendResponseHeaders(SC_NOTFOUND, response.length());
			else
				exchange.sendResponseHeaders(SC_OK, response.length());

		}
		else {
			response = "error";
			System.out.println("Menu Handler did not find a query string in URL.");
			exchange.sendResponseHeaders(SC_OK, response.length());
		}

		//output the information
		OutputStream os = exchange.getResponseBody();
		os.write(response.getBytes());
		os.close();
	}
	
	private String parseMenuQuery(String query) {
		query.replace('_', ' ');
		System.out.print(query);
    	System.out.println();
    	
		//split the query based on parameters
		String[] subs = query.split("&");
		
		String[] type = subs[QUERY_TYPE].split("=");
		
		if (type[QUERY_KEY].equalsIgnoreCase("type") && type[QUERY_VAL].equalsIgnoreCase("add")) {
			String[] item = subs[QUERY_SECOND].split("=");
			MenuItem it = itemXMLParser(item[QUERY_VAL]);
			if (SystemManager.addMenuItem(it.getName(), it.getPrice(), it.isDailySpecial()))
				return "VALID";
			else
				return "INVALID";		
		}
		else if (type[QUERY_KEY].equalsIgnoreCase("type") && type[QUERY_VAL].equalsIgnoreCase("edit")) {
			String[] name = subs[QUERY_SECOND].split("=");
			String oldName = name[QUERY_VAL];
			name = subs[QUERY_THIRD].split("=");
			String newName = name[QUERY_VAL];
			Double p = Double.parseDouble(subs[QUERY_FOURTH].split("=")[QUERY_VAL]);
			Boolean s = Boolean.parseBoolean(subs[QUERY_FIFTH].split("=")[QUERY_VAL]);
			if (SystemManager.editMenuItem(oldName, newName, p, s))
				return "VALID";
			else
				return "INVALID";		
		}
		else if (type[QUERY_KEY].equalsIgnoreCase("type") && type[QUERY_VAL].equalsIgnoreCase("remove")) {
			String[] item = subs[QUERY_SECOND].split("=");
			if (SystemManager.removeMenuItem(item[QUERY_VAL]))
				return "VALID";
			else
				return "INVALID";		
		}
		else if (type[QUERY_KEY].equalsIgnoreCase("type") && type[QUERY_VAL].equalsIgnoreCase("get")) {
			return getMenuXml();
		}
		else
			return "error";
		
	}
	
	private MenuItem itemXMLParser(String xmlInput) {
        String name;
        double price;
        boolean isDailySpecial;

        Document dom;
        //get the factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            //Using factory get an instance of document builder
            DocumentBuilder db = dbf.newDocumentBuilder();

            //parse using builder to get DOM representation of the XML file
            dom = db.parse(xmlInput);
            
            //get the root elememt
            Element docEle = dom.getDocumentElement();
            //get a nodelist of <Customer> elements
            name = getTextValue(docEle, "Name");
            price = getDoubleValue(docEle, "Price");
            isDailySpecial = getBoolValue(docEle, "Special");
            
            return new MenuItem(name, price, isDailySpecial);

        }catch(ParserConfigurationException pce) {
            pce.printStackTrace();
        }catch(SAXException se) {
            se.printStackTrace();
        }catch(IOException ioe) {
            ioe.printStackTrace();
        }

        return null;
    }
	
	//Turns the menu into an XML representation
	private String getMenuXml() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		buffer.append("<Menu>");

        //for each item in the system's menu
		for (MenuItem item : SystemManager.getMenuItems()) {

            //format menu item to xml
            buffer.append("<Item>");
			
			buffer.append("<Name>");
			buffer.append(item.getName());
			buffer.append("</Name>");
			buffer.append("<Price>");
			buffer.append(item.getPrice());
			buffer.append("</Price>");
			buffer.append("<Special>");
			buffer.append(item.isDailySpecial());
			buffer.append("</Special>");

			buffer.append("</Item>");
		}
		buffer.append("</Menu>");

		return buffer.toString();
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
    
    private boolean getBoolValue(Element ele, String tagName) {
        //in production application you would catch the exception
        return Boolean.parseBoolean(getTextValue(ele,tagName));
    }
}
