package cs414.groupH.a5.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import cs414.groupH.a5.address.Address;
import cs414.groupH.a5.customer.Customer;
import cs414.groupH.a5.employee.Employee;
import cs414.groupH.a5.manager.EmployeeManager;
import cs414.groupH.a5.manager.SystemManager;
import cs414.groupH.a5.menu.MenuItem;
import cs414.groupH.a5.order.Order;
import cs414.groupH.a5.payment.Payment;

public class EmployeeRequestHandler implements HttpHandler {
	
	private static final int QUERY_TYPE = 0;
	private static final int QUERY_ID = 1;
	private static final int QUERY_PW = 2;
	private static final int QUERY_KEY = 0;
	private static final int QUERY_VAL = 1;
	private static final int SC_OK = 200;
	private static final int SC_NOTFOUND = 404;
	private static final int SC_ERROR = 500;
	
	//this function is called when an HTTP request is made
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		//this is the URL of the request
		URI uri = exchange.getRequestURI();
		
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
		//split the query based on parameters
		String[] subs = query.split("&");	
		
		String[] type = subs[QUERY_TYPE].split("=");
		if (type[QUERY_KEY].equalsIgnoreCase("type") && type[QUERY_VAL].equalsIgnoreCase("login")) {
			String[] id = subs[QUERY_ID].split("=");
			String[] pw = subs[QUERY_PW].split("=");
			if (EmployeeManager.doesEmpExist(EmployeeManager.findEmployee(id[QUERY_VAL]))) {
				if (EmployeeManager.verifyCreds(id[QUERY_VAL], pw[QUERY_VAL]))
					return "VALID";
				else
					return "INVALID";
			}
			else
				return "error";
		}
		else if (type[QUERY_KEY].equalsIgnoreCase("type") && type[QUERY_VAL].equalsIgnoreCase("logout")) {
			String[] id = subs[QUERY_ID].split("=");
			if (EmployeeManager.doesEmpExist(EmployeeManager.findEmployee(id[QUERY_VAL]))) {
				return "VALID";
			}
			else
				return "error";
		}
		else if (type[QUERY_KEY].equalsIgnoreCase("type") && type[QUERY_VAL].equalsIgnoreCase("typecheck")) {
			String[] id = subs[QUERY_ID].split("=");
			if (EmployeeManager.doesEmpExist(EmployeeManager.findEmployee(id[QUERY_VAL]))) {
				return EmployeeManager.findEmployee(id[QUERY_VAL]).getEmpType().toString();
			}
			else
				return "error";
		}
		else
			return "error";
	}

}
