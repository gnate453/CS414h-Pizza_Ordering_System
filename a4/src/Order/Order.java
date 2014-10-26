package Order;
import Customer;

import java.util.ArrayList;


public class Order {

	private String orderId;
	private double amount;
	private boolean isCompleted;
	private Customer customer;
	private ArrayList<MenuItem> items;
	
	public String getId(){
		return orderId;
	}
	
	public double getAmount(){
		return amount;
	}
	
	public Order(Customer customer, String orderId, double amount){
		this.orderId = orderId;
		this.amount = amount;
		isCompleted = false;
		this.customer = customer;
		items = new ArrayList<MenuItem>();
	}
	
}
