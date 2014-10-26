package Customer;
import cs414.groupH.a4.Address.Address;


public class Customer {

	private String customerId;
	private String name;
	private Address address;
	
	public String getId(){
		return customerId;
	}
	
	public String getName(){
		return name;
	}
	
	public Address getAddress(){
		return address;
	}
	
	public Customer(String id, String iname, Address ad){
		customerId = id;
		name = iname;
		address = ad;
	}
	
	public String toString(){
		return "Name: " + name +
				"\nAddress: " + address;
	}
	
	
}
