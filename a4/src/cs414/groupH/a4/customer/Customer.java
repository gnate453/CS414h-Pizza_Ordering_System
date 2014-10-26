package cs414.groupH.a4.customer;
import java.util.ArrayList;

import cs414.groupH.a4.address.Address;

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
	
	public Customer(String id, String iname, ArrayList<Address> ad){
		customerId = id;
		name = iname;
		address = ad;
	}
	
	public String toString(){
		return "Name: " + name +
				"\nAddress: " + address;
	}
	
	
}
