package cs414.groupH.a5.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import cs414.groupH.a5.employee.Employee;
import cs414.groupH.a5.manager.EmployeeManager;

public class EmployeeRequestHandler implements HttpHandler {
	
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
		System.out.println(uri.getPath());
		
		String query = uri.getQuery();
		String response = "";
        //if there was a query it was an order request.
		if (query != null) {
			
			response = parseEmployeeQuery(query);
			
			if (response.equalsIgnoreCase("error"))
				exchange.sendResponseHeaders(SC_NOTFOUND, response.getBytes().length);
			else 
				exchange.sendResponseHeaders(SC_OK, response.getBytes().length);
		}
		else {
			response = "error";
			System.out.println("Employee Handler did not find a query string in URL.");
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
	private String parseEmployeeQuery(String query) {
		String retValue = "";
		//split the query based on parameters
		String[] subs = query.split("&");	
		for (String t : subs) {
			System.out.println(t);
		}
		
		String[] type = subs[QUERY_TYPE].split("=");
		if (type[QUERY_KEY].equalsIgnoreCase("type")) {
			if (type[QUERY_VAL].equalsIgnoreCase("login")) {
				String[] id = subs[QUERY_ID].split("=");
				String[] pw = subs[QUERY_PW].split("=");
				Employee emp = EmployeeManager.findEmployee(id[QUERY_VAL]);
				if (emp != null) {
					if (EmployeeManager.verifyCreds(id[QUERY_VAL], pw[QUERY_VAL]))
						retValue = "VALID,"+emp.getName()+","+emp.getEmpType();
					else
						retValue = "INVALID";
				}
				else
					retValue = "error";
			}
			else if (type[QUERY_VAL].equalsIgnoreCase("logout")) {
				String[] id = subs[QUERY_ID].split("=");
				if (EmployeeManager.doesEmpExist(EmployeeManager.findEmployee(id[QUERY_VAL]))) {
					retValue = "VALID";
				}
				else
					retValue = "error";
			}
			else if (type[QUERY_VAL].equalsIgnoreCase("typecheck")) {
				String[] id = subs[QUERY_ID].split("=");
				if (EmployeeManager.doesEmpExist(EmployeeManager.findEmployee(id[QUERY_VAL]))) {
					retValue = EmployeeManager.findEmployee(id[QUERY_VAL]).getEmpType().toString();
				}
				else
					retValue = "error";
			}
		}
		else {
			retValue = "error";
		}
		
		return retValue;
	}

}
