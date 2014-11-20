package cs414.groupH.a5.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import cs414.groupH.a5.address.Address;
import cs414.groupH.a5.customer.Customer;
import cs414.groupH.a5.manager.SystemManager;
import cs414.groupH.a5.menu.MenuItem;
import cs414.groupH.a5.order.Order;
import cs414.groupH.a5.payment.Payment;

public class MenuRequestHandler implements HttpHandler {
		
	//this function is called when an HTTP request is made
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		//this is the URL of the request
		URI uri = exchange.getRequestURI();
		
		//the URL could come with parameters
		String query = uri.getQuery();
		String response = "";
		if (query != null) {

			response = "404 address " + query + "unknown.";
			exchange.sendResponseHeaders(500, response.length());

		}
		else {
			response = getMenuXml();
			System.out.println(response);
			exchange.sendResponseHeaders(200, response.length());
		}

		//output the information
		OutputStream os = exchange.getResponseBody();
		os.write(response.getBytes());
		os.close();
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
}
