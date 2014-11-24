package cs414.groupH.a5.http;

import java.util.ArrayList;


public class RequestHandler {
	private static String addressXml;
	private static String paymentXml = "";
	private static String customerXml;
	private static ArrayList<String> itemXml = new ArrayList<String>();
	//private static String addressXml;

	public static String getAddressXml() {
		return addressXml;
	}
	public static void setAddressXml(String addressXml) {
		RequestHandler.addressXml = addressXml;
	}
	public static String getCustomerXml() {
		return customerXml;
	}
	public static void setCustomerXml(String customerXml) {
		RequestHandler.customerXml = customerXml;
	}
	public static void addPaymentXml(String paymentXml) {
		RequestHandler.paymentXml += paymentXml;
	}
	public static String getPaymentXml() {
		return paymentXml;
	}
	public static void addItemXml(String item) {
		itemXml.add(item);
	}
	
	public static void removeItem(int i) {
		itemXml.remove(i);
	}
	public static String getItemXml() {
		String retVal = "";
		
		for (String s : itemXml) {
			retVal += s;
		}
		
		return retVal;
	}
	
	public static boolean isManager(String empID) {
		String empType = InStoreHttpClient.getEmpType(empID);
		return empType.equalsIgnoreCase("manager");
	}
	
	public static String getFinalXml() {
		String res = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><order>";
		
		res += customerXml;
		res += addressXml;
		
		res += "<items>"+getItemXml()+"</items>";
		res += "<payments>"+paymentXml+"</payments>";
		
		res += "</order>";
		 
		return res;
	}
	
	public static void main(String[] args) {
		System.out.println(InStoreHttpClient.getOrderItems("1"));
	}
}
