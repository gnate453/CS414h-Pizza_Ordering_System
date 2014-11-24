package cs414.groupH.a5.http;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import com.sun.net.httpserver.HttpServer;

import cs414.groupH.a5.address.Address;
import cs414.groupH.a5.customer.Customer;
import cs414.groupH.a5.employee.EmployeeType;
import cs414.groupH.a5.manager.CustomerManager;
import cs414.groupH.a5.manager.SystemManager;
import cs414.groupH.a5.payment.Payment;
import cs414.groupH.a5.rewards.RewardsSystem;

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
		
		//create initial data
		SystemManager.addEmployee("001", "John Smith", "password", EmployeeType.cashier);
		SystemManager.addEmployee("002", "Joh Smith", "password", EmployeeType.chef);
		SystemManager.addEmployee("003", "Jo Smith", "password", EmployeeType.manager);
		SystemManager.addEmployee("004", "J Smith", "password", EmployeeType.cashier);
		
		CustomerManager.addEmployee(new Customer("B", new Address("123 main", "foco", "co", "80525", "3035554678"), "bm1", "pass"));
		CustomerManager.addEmployee(new Customer("B", new Address(), "bm2", "pass"));
		CustomerManager.addEmployee(new Customer("B", new Address(), "bm3", "pass"));
		CustomerManager.addEmployee(new Customer("B", new Address(), "bm4", "pass"));
		RewardsSystem.setThreshold(5);
		RewardsSystem.newMember("bm1");
		RewardsSystem.addPoints("bm1", 6);
		RewardsSystem.newMember("bm2");
		RewardsSystem.addPoints("bm2", 5);
		RewardsSystem.newMember("bm3");
		RewardsSystem.addPoints("bm3", 2);
		RewardsSystem.newMember("bm4");
		
		SystemManager.addMenuItem("Pepperoni Pizza", 9.99, false);
		SystemManager.addMenuItem("Cheese Pizza", 5.00, true);
		SystemManager.addMenuItem("Breadsticks(6)", 3.99, false);
		Address addr = new Address("1423 Foo Bar","Fort Collins","CO","80526","(111)111-1111");
		Customer c = new Customer("Rick Henderson", addr);
		List<String> items = new ArrayList<String>();
		items.add("Pepperoni Pizza");
		items.add("Cheese Pizza");
		items.add("Pepperoni Pizza");
		List<Payment> pay = new ArrayList<Payment>();
		pay.add(new Payment(34.29));
		SystemManager.createOrder(c, items, pay);
		
		
		MenuRequestHandler menuHandler = new MenuRequestHandler();
		EmployeeRequestHandler empHandler = new EmployeeRequestHandler();
        OrderRequestHandler orderHandler = new OrderRequestHandler();
        CustomerRequestHandler custHandler = new CustomerRequestHandler();
		//creates the URI for the pizza. In this case, {base}/pizzas redirects here
		server.createContext("/menu", menuHandler);
        server.createContext("/order", orderHandler);
		server.createContext("/employee", empHandler);
		server.createContext("/customer", custHandler);
		
		//start the server
		server.start();
		System.out.println("Server started");
	}
}
