package cs414.groupH.a4.order;

import java.util.ArrayList;

import cs414.groupH.a4.customer.Customer;
import cs414.groupH.a4.menu.MenuItem;
import cs414.groupH.a4.payment.Payment;

public class Order {
	private String orderId;
	private Customer customer;
	private double amountPaid;
	private ArrayList<MenuItem> orderedItems;
	private ArrayList<Payment> payments;
	private boolean isComplete;

	public Order(String orderId){
		this.orderId = orderId;
		amountPaid = 0.0;
		orderedItems = new ArrayList<MenuItem>();
		payments = new ArrayList<Payment>();
		isComplete = false;
	}
	
	/*public Order(){
		new OrderDialog(this);
	}*/
	
	public boolean addPayment(Payment payment) {
		boolean res = payments.add(payment);
		if (res) {
			amountPaid += payment.getAmount();
		}
		
		return res;
	}
	public boolean removePayment(Payment payment) {
		boolean res = payments.remove(payment);
		if (res) {
			amountPaid -= payment.getAmount();
		}
		
		return res;
	}
	
	public boolean addItem(MenuItem item) {
		return orderedItems.add(item);
	}
	
	public double getAmountDue() {
		double orderTotal = 0.0;
		for (MenuItem i : orderedItems) {
			orderTotal += i.getPrice();
		}
		
		return orderTotal-amountPaid;
	}
	
	public void markComplete(){
		isComplete = true;
	}
	public void markIncomplete() {
		isComplete = false;
	}
	
	public String getOrderId() {
		return orderId;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public double getAmountPaid() {
		return amountPaid;
	}
	public ArrayList<MenuItem> getItems() {
		return orderedItems;
	}
	public ArrayList<Payment> getPayments() {
		return payments;
	}
	public boolean isComplete() {
		return isComplete;
	}
}
