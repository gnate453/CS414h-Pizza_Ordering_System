package cs414.groupH.a5.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.util.Date;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import cs414.groupH.a5.address.Address;
import cs414.groupH.a5.customer.Customer;
import cs414.groupH.a5.manager.CustomerManager;
import cs414.groupH.a5.rewards.RewardsSystem;

public class CustomerRequestHandler implements HttpHandler {
	
	private static final int QUERY_TYPE = 0;
	private static final int QUERY_ID = 1;
	private static final int QUERY_PW = 2;
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
		InputStream reqBody = exchange.getRequestBody();
        String rBody = convertToString(reqBody);
		
		// Log URL hits
     	System.out.print(new Date()+" - "+uri.getPath()+"?");
		
		String query = uri.getQuery();
		String response = "";
        //if there was a query it was an order request.
		if (query != null) {
			
			response = parseCustomerQuery(query, rBody);
			
			if (response.equalsIgnoreCase("error"))
				exchange.sendResponseHeaders(SC_NOTFOUND, response.getBytes().length);
			else 
				exchange.sendResponseHeaders(SC_OK, response.getBytes().length);
		}
		else {
			response = "error";
			System.out.println("Customer Handler did not find a query string in URL.");
			exchange.sendResponseHeaders(SC_NOTFOUND, response.getBytes().length);
		}

		//output the information
		OutputStream os = exchange.getResponseBody();
		os.write(response.getBytes());
		os.close();
	}
	
	//parses the parameters from the URL
    //Option 1: /employee?type=Login&id=empID&pw=empPW
	//Option 2: /employee?type=Logout&id=empID
	//Option 3: /employee?type=TypeCheck&id=empID
	private String parseCustomerQuery(String query, String request) {
		System.out.print(query);
    	System.out.println();
    	
		String retValue = "";
		//split the query based on parameters
		String[] subs = query.split("&");
		
		String[] type = subs[QUERY_TYPE].split("=");
		if (type[QUERY_KEY].equalsIgnoreCase("type")) {
			if (type[QUERY_VAL].equalsIgnoreCase("login")) {
				String[] uname = subs[QUERY_ID].split("=");
				String[] pw = subs[QUERY_PW].split("=");
				Customer cust = CustomerManager.findCustomer(uname[QUERY_VAL]);
				if (cust != null) {
					if (cust.verifyPassword(pw[QUERY_VAL]))
						retValue = cust.getName();
					else
						retValue = "INVALID";
				}
				else
					retValue = "invalid";
			}
			else if (type[QUERY_VAL].equalsIgnoreCase("add")) {
				
				//Customer cust = 
				
				/*if (cust != null) {
					System.out.println("Found cust");
					if (RewardsSystem.isEligible(cust.getUsername())) {
						retValue = "TRUE";
					}
					else {
						retValue = "FALSE";
					}
				}
				else
					retValue = "FALSE";*/
			}
			else if (type[QUERY_VAL].equalsIgnoreCase("rewardavailable")) {
				String[] uname = subs[QUERY_ID].split("=");
				Customer cust = CustomerManager.findCustomer(uname[QUERY_VAL]);
				if (cust != null) {
					System.out.println("Found cust");
					if (RewardsSystem.isEligible(cust.getUsername())) {
						retValue = "TRUE";
					}
					else {
						retValue = "FALSE";
					}
				}
				else
					retValue = "FALSE";
			}
			else if (type[QUERY_VAL].equalsIgnoreCase("redeemReward")) {
				String[] uname = subs[QUERY_ID].split("=");
				Customer cust = CustomerManager.findCustomer(uname[QUERY_VAL]);
				if (cust != null) {
					if (RewardsSystem.isEligible(cust.getUsername())) {
						boolean res = RewardsSystem.redeemCertificate(cust.getUsername());
						if (res) {
							retValue = "SUCCESS";
						}
						else {
							retValue = "ERROR";
						}
					}
					else {
						retValue = "ERROR";
					}
				}
				else
					retValue = "ERROR";
			}
		}
		else {
			retValue = "error";
		}
		
		return retValue;
	}
	
	private Address getAddress(Element el) {
    	String street = getTextValue(el, "Street");
    	String city = getTextValue(el, "City");
    	String state = getTextValue(el, "State");
    	String zip = getTextValue(el, "Zip");
    	String phone = getTextValue(el, "Phone");
    	
    	return new Address(street, city, state, zip, phone);
    }

    private Customer getCustomer(Element empEl, Address addr) {
        String name = getTextValue(empEl, "Name");

        //Create a new Customer with the value read from the xml nodes
        Customer cust = new Customer(name, addr);

        return cust;
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
