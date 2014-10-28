package cs414.groupH.a4.customer;
import java.util.ArrayList;

import cs414.groupH.a4.address.Address;

public class Customer {

	private String customerId;
	private String name;
	private ArrayList<Address> address = new ArrayList<Address>();
	
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
	}
	public Customer(String id, String iname, ArrayList<Address> ad){
		customerId = id;
		name = iname;
		address = ad;
	}
	public void addAddress(Address a){
		address.add(a);
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
