package cs414.groupH.a5.http;


public class RequestHandler {
	private static String addressXml;
	private static String paymentXml = "";
	private static String customerXml;
	private static String itemXml = "";
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
	public static void addItemXml(String itemXml) {
		RequestHandler.itemXml += itemXml;
	}
	public static String getItemXml() {
		return itemXml;
	}
	
	public static boolean isManager(String empID) {
		String empType = InStoreHttpClient.getEmpType(empID);
		return empType.equalsIgnoreCase("manager");
	}
	
	public static String getFinalXml() {
		String res = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		
		res += customerXml;
		res += addressXml;
		res += "<items>"+itemXml+"</items>";
		res += "<payments>"+paymentXml+"</payments>";
		
		res = "<order>" + res + "</order>";
		 
		return res;
	}
	
	public static void main(String[] args) {
		System.out.println(InStoreHttpClient.getOrderItems("1"));
	}
}
