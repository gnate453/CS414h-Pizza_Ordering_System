package cs414.groupH.a5.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.Date;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

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
		
		// Log URL hits
     	System.out.print(new Date()+" - "+uri.getPath()+"?");
		
		String query = uri.getQuery();
		String response = "";
        //if there was a query it was an order request.
		if (query != null) {
			
			response = parseCustomerQuery(query);
			
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
	private String parseCustomerQuery(String query) {
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
						retValue = "VALID";
					else
						retValue = "INVALID";
				}
				else
					retValue = "error";
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

}
