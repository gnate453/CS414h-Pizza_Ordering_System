package cs414.groupH.a4.manager;

import java.util.List;

import cs414.groupH.a4.customer.Customer;
import cs414.groupH.a4.employee.Employee;
import cs414.groupH.a4.employee.EmployeeType;
import cs414.groupH.a4.menu.Menu;
import cs414.groupH.a4.menu.MenuItem;
import cs414.groupH.a4.order.Order;
import cs414.groupH.a4.payment.Payment;

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
	
	public static String createOrder(Customer cust, List<String> itemNames, List<Payment> payments) {
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
	}
	
	public static Order findOrder(String orderId) {
		return OrderManager.findOrder(orderId);
	}
	
	public static void markOrderComplete(String orderId) {
		OrderManager.markOrderComplete(OrderManager.findOrder(orderId));
	}
	
	public static boolean addPayment(String orderId, Payment payment) {
		return OrderManager.findOrder(orderId).addPayment(payment);
	}
	
	public static List<Order> getOrders() {
		return OrderManager.getOrders();
	}
	
}
