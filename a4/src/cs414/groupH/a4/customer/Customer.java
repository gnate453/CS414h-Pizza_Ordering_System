package cs414.groupH.a4.customer;
import java.util.ArrayList;

import cs414.groupH.a4.address.Address;

public class Customer {

	private String name;
	private Address address;
	
	public String getName(){
		return name;
	}
	
	public Address getAddress(){
		return address;
	}
	
	public Customer() {
		name = "";
		address = new Address("", "", "", "", "");
	}
	public Customer(String iname, Address ad){
		name = iname;
		address = ad;
	}
	public void addAddress(Address a){
		address = a;
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
