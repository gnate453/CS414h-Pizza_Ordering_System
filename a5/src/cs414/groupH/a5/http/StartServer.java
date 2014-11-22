package cs414.groupH.a5.http;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

import cs414.groupH.a5.employee.EmployeeType;
import cs414.groupH.a5.manager.SystemManager;

public class StartServer {
	public static void main(String[] args) throws IOException {
		//creates the server on port 8,000
		int port = 8000;
		if (args.length == 1) {
			port = Integer.parseInt(args[0]);
		}
		System.out.println("Starting server using port: "+port);
		HttpServer server = HttpServer.create(new InetSocketAddress(port), port);
		
		SystemManager.addEmployee("0", "John Smith", "0", EmployeeType.manager);
		
		//create the pizza controller
		SystemManager.addMenuItem("Pepperoni Pizza", 9.99, false);
		SystemManager.addMenuItem("Cheese Pizza", 5.00, true);
		SystemManager.addMenuItem("Breadsticks(6)", 3.99, false);
		MenuRequestHandler menuHandler = new MenuRequestHandler();
		EmployeeRequestHandler empHandler = new EmployeeRequestHandler();
        OrderRequestHandler orderHandler = new OrderRequestHandler();
		//creates the URI for the pizza. In this case, {base}/pizzas redirects here
		server.createContext("/menu", menuHandler);
        server.createContext("/order", orderHandler);
		server.createContext("/employee", empHandler);
		
		//start the server
		server.start();
		System.out.println("Server started");
	}
}
