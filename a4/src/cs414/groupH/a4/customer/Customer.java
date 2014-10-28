package cs414.groupH.a4.customer;
import java.util.ArrayList;

import cs414.groupH.a4.address.Address;
import cs414.groupH.a4.payment.DebitPayment;

public class Customer {

	private String customerId;
	private String name;
	private ArrayList<Address> address;
	
	public String getId(){
		return customerId;
	}
	
	public String getName(){
		return name;
	}
	
	public ArrayList<Address> getAddress(){
		return address;
	}
	public static void main(String args[]){
		Customer c = new Customer();	
	}
	public Customer(){
		new CustomerDialog(this);
		address.add(new Address());
	}
	public Customer(String id, String iname, ArrayList<Address> ad){
		customerId = id;
		name = iname;
		address = ad;
	}
	public void setFields(String n) {
		name = n;
		System.out.println(this);
	}
	public String toString(){
		return "Name: " + name +
				"\nAddress: " + address;
	}
	
	
}
