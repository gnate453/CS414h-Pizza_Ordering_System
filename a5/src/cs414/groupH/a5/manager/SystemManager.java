package cs414.groupH.a5.manager;

import java.util.List;
import java.util.Map;

import cs414.groupH.a5.customer.Customer;
import cs414.groupH.a5.employee.Employee;
import cs414.groupH.a5.employee.EmployeeType;
import cs414.groupH.a5.menu.Menu;
import cs414.groupH.a5.menu.MenuItem;
import cs414.groupH.a5.order.Order;
import cs414.groupH.a5.payment.Payment;

public class SystemManager {

	public static boolean addEmployee(String employeeId, String name, String pwd, EmployeeType empType) {
		Employee emp = new Employee(employeeId, name, pwd, empType);
		return EmployeeManager.addEmployee(emp);
	}
	
	public static boolean removeEmployee(String employeeId, String name, String pwd, EmployeeType empType) {
		Employee emp = new Employee(employeeId, name, pwd, empType);
		return EmployeeManager.removeEmployee(emp);
	}

	public static boolean addMenuItem(String name, double price, boolean isDailySpecial) {
		return Menu.addMenuItem(new MenuItem(name, price, isDailySpecial));
	}

	public static boolean editMenuItem(String oldName, String newName, double price, boolean isDailySpecial) {
		return Menu.editMenuItem(oldName, new MenuItem(newName, price, isDailySpecial));
	}

	public static void removeMenuItem(MenuItem item) {
		Menu.removeMenuItem(item);
	}
	
	public static List<MenuItem> getMenuItems() {
		return Menu.getMenuItems();
	}	
	
	/*public static String createOrderFromApp(Map<String, String> params) {
		String street = params.get("street");
		String city = params.get("city");
		String state = params.get("state");
		String zip = params.get("zip");
		String phone = params.get("phone");
		Customer cust = new Customer(params.get("customer"), new Address(street, city, state, zip, phone));
		
		Order newOrder = new Order(cust);
		OrderManager.addOrder(newOrder);
		for (String i : itemNames) {
			MenuItem item = Menu.findMenuItem(i);
			newOrder.addItem(item);
		}
		for(Payment p : payments){
			newOrder.addPayment(p);
		}
				
		return newOrder.getOrderId();
	}*/
	public static Order createOrder(Customer cust, List<String> itemNames, List<Payment> payments) {
		Order newOrder = new Order(cust);
		OrderManager.addOrder(newOrder);
		for (String i : itemNames) {
			MenuItem item = Menu.findMenuItem(i);
			newOrder.addItem(item);
		}
		for(Payment p : payments){
			newOrder.addPayment(p);
		}
				
		return newOrder;
	}

	public static Order findOrder(String orderId) {
		return OrderManager.findOrder(orderId);
	}

	public static void markOrderComplete(String orderId) {
		OrderManager.markOrderComplete(OrderManager.findOrder(orderId));
	}
	
	public static boolean addPayment(Map<String, String> params) {
		return true;
	}
	public static boolean addPayment(String orderId, Payment payment) {
		return OrderManager.findOrder(orderId).addPayment(payment);
	}
	
	public static List<Order> getOrders() {
		return OrderManager.getOrders();
	}
	
}
