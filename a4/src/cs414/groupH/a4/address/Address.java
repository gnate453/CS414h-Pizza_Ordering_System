package cs414.groupH.a4.address;


public class Address {

	private String street;
	private String city;
	private String state;
	private String zip;
	private String phone;
	
	public String getStreet(){
		return street;
	}
	
	public String getCity(){
		return city;
	}
	
	public String getState(){
		return state;
	}
	
	public String getZip(){
		return zip;
	}
	
	public String getPhone(){
		return phone;
	}
	
	public Address() {
		
	}
	public Address(String street, String city, String state, String zip, String phone){
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phone = phone;
	}
	
	public void setFields(String street, String city, String state, String zip, String phone) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phone = phone;
		//System.out.println(this);
	}
	
	public String toString(){
		return "\nStreet: " + street +
				"\nCity: " + city +
				"\nState: " + state +
				"\nZip Code: " + zip +
				"\nPhone: "+ phone;
	}
}
