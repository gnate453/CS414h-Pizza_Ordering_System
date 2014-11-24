package cs414.groupH.a5.http;

public class XmlHelper {
	public static String getAddressXml(String street, String city, String state, String zip, String phone) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<address>");
			buffer.append("<street>"+street+"</street>");
			buffer.append("<city>"+city+"</city>");
			buffer.append("<state>"+state+"</state>");
			buffer.append("<zip>"+zip+"</zip>");
			buffer.append("<phone>"+phone+"</phone>");
		buffer.append("</address>");
		
		return buffer.toString();
	}
	
	public static String getItemXml(String name, String price, String special) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<item>");
			buffer.append("<name>"+name+"</name>");
			buffer.append("<price>"+price+"</price>");
			buffer.append("<special>"+special+"</special>");
		buffer.append("</item>");
		
		return buffer.toString();
	}
	
	public static String getCashPaymentXml(String amount) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<payment>");
			buffer.append("<type>Cash</type>");
			buffer.append("<amount>"+amount+"</amount>");
		buffer.append("</payment>");
		
		return buffer.toString();
	}
	
	public static String getCreditPaymentXml(String cardHolder, String cardNum, String cardSec, String cardExp) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<payment>");
			buffer.append("<type>Credit</type>");
			buffer.append("<cardHolder>"+cardHolder+"</cardHolder>");
			buffer.append("<cardNum>"+cardNum+"</cardNum>");
			buffer.append("<cardSec>"+cardSec+"</cardSec>");
			buffer.append("<cardExp>"+cardExp+"</cardExp>");
		buffer.append("</payment>");
		
		return buffer.toString();
	}
	
	public static String getDeditPaymentXml(String cardHolder, String cardNum, String cardSec, String cardExp, String cardPin) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<payment>");
			buffer.append("<type>Dedit</type>");
			buffer.append("<cardHolder>"+cardHolder+"</cardHolder>");
			buffer.append("<cardNum>"+cardNum+"</cardNum>");
			buffer.append("<cardSec>"+cardSec+"</cardSec>");
			buffer.append("<cardExp>"+cardExp+"</cardExp>");
			buffer.append("<cardPin>"+cardPin+"</cardPin>");
		buffer.append("</payment>");
		
		return buffer.toString();
	}
	
	public static String getCustomerXml(String name) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<customer>");
			buffer.append("<name>"+name+"</name>");
		buffer.append("</customer>");
		
		return buffer.toString();
	}
	
	public static String getCustomerXml(String name, String uname) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<customer>");
			buffer.append("<name>"+name+"</name>");
			buffer.append("<uname>"+uname+"</uname>");
		buffer.append("</customer>");
		
		return buffer.toString();
	}
	
	public static String getCustomerXml(String name, String uname, String password) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<customer>");
			buffer.append("<name>"+name+"</name>");
			buffer.append("<uname>"+uname+"</uname>");
			buffer.append("<password>"+password+"</password>");
		buffer.append("</customer>");
		
		return buffer.toString();
	}
}