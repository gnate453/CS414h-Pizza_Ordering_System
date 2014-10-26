package cs414.groupH.a4.receipt;

import java.util.ArrayList;

import cs414.groupH.a4.customer.Customer;
import cs414.groupH.a4.menu.MenuItem;
import cs414.groupH.a4.payment.Payment;

public class Receipt {
	private double amount;
	//private OrderType orderedVia;
	private ArrayList<MenuItem> purchases;
	private Customer customer;
	private ArrayList<Payment> payments;
	public Receipt(){
		//Dialog;
		//initialize amount.
		//customer = new Customer();
		boolean moreitem = false;
		while(moreitem){
			//dialog
			//MenuItem item = new MenuItem();
			//this.addItem(item);
			moreitem = false;
		}
		Payment p = new Payment();
		this.payments.add(p);
	}
	public boolean applyPayment(Payment p){
		if(p.isValid()){
			amount = amount - p.getAmount();
			return true;
		}else{
			return false;
		}
	}
	public void applyPayments(){
		for(int i = 0; i < payments.size();i++){
			this.applyPayment(payments.get(i));
		}
	}
	public void addItem(MenuItem item){
		purchases.add(item);
		amount = amount + item.getPrice();
	}
}
