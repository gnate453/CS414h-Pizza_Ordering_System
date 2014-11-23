package cs414.groupH.a5.customer;
import cs414.groupH.a5.address.Address;

public class Customer {

	private String name;
	private Address address;
	private String username;
	private String password;
	
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
	public Customer(String iname, Address ad, String username, String password) {
		name = iname;
		address = ad;
		this.username = username;
		this.password = password;
	}
	public void addAddress(Address a){
		address = a;
	}
	public String getUsername() {
		return username;
	}
	public void setFields(String n) {
		name = n;
		//System.out.println(this);
	}
	public String toString(){
		return "Name: " + name +
				"\nAddress: " + address;
	}
	
	public boolean verifyPassword(String givenPwd) {
		return password.equals(givenPwd);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		
		Customer emp = (Customer) obj;
		
		if (!this.getUsername().equals(emp.getUsername())) {
			return false;
		}
		else {
			return true;
		}
	}
}
