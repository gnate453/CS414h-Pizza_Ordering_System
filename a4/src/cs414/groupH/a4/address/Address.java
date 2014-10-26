package cs414.groupH.a4.address;

public class Address {

	private String street;
	private String city;
	private String state;
	private String zip;
	
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
	
	public Address(String street, String city, String state, String zip){
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	
	public String toString(){
		return "Street: " + street +
				"\nCity: " + city +
				"\nState: " + state +
				"\nZip Code: " + zip;
	}
}
