package cs414.groupH.a4.manager;

import java.util.ArrayList;
import java.util.List;

import cs414.groupH.a4.order.Order;

public class OrderManager {
	private static List<Order> orders = new ArrayList<Order>();
	
	public static boolean addOrder(Order o) {
		if (!orders.contains(o)) {
			return orders.add(o);
		}
		else {
			System.out.println("ERROR: Order with ID '" + o.getOrderId() + "' already exists.");
			return false;
		}
	}
	
	public static void markOrderComplete(Order order) {
		order.markComplete();
	}
	
	public static boolean doesOrderExist(Order o) {
		return orders.contains(o);
	}
	
	public static Order findOrder(String orderId) {
		for (Order o : orders) {
			if (o.getOrderId().equals(orderId)) {
				return o;
			}
		}
		
		return null;
	}
	
	public static boolean removeOrder(Order o) {
		return orders.remove(o);
	}
	
	public static void clearOrders() {
		orders.clear();
	}
	
	public static List<Order> getOrders() {
		return orders;
	}
}
