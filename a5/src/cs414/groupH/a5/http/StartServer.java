package cs414.groupH.a5.http;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

import cs414.groupH.a5.employee.EmployeeType;
import cs414.groupH.a5.manager.SystemManager;

public class StartServer {
	public static void main(String[] args) throws IOException {
		//creates the server on port 8,000
		HttpServer server = HttpServer.create(new InetSocketAddress(8000), 8000);
		
		SystemManager.addEmployee("0", "John Smith", "", EmployeeType.manager);
		
		//create the pizza controller
		SystemManager.addMenuItem("Pepperoni Pizza", 9.99, false);
		SystemManager.addMenuItem("Cheese Pizza", 5.00, true);
		SystemManager.addMenuItem("Breadsticks(6)", 3.99, false);
		MenuRequestHandler menuHandler = new MenuRequestHandler();
		EmployeeRequestHandler empHandler = new EmployeeRequestHandler();
		//creates the URI for the pizza. In this case, {base}/pizzas redirects here
		server.createContext("/menu", menuHandler);
		server.createContext("/employee", empHandler);
		
		//start the server
		server.start();
	}
}
