package cs414.groupH.a5.http;

public class XmlHelper {
	public static String getAddressXml(String street, String city, String state, String zip, String phone) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		buffer.append("<Address>");
			buffer.append("<Street>"+street+"</Street>");
			buffer.append("<City>"+city+"</City>");
			buffer.append("<State>"+state+"</state>");
			buffer.append("<Zip>"+zip+"</Zip>");
			buffer.append("<Phone>"+phone+"</Phone>");
		buffer.append("</Address>");
		
		return buffer.toString();
	}
	
	public static String getCashPaymentXml(String amount) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		buffer.append("<Payment>");
			buffer.append("<Type>Cash</Type>");
			buffer.append("<Amount>"+amount+"</Amount>");
		buffer.append("</Payment>");
		
		return buffer.toString();
	}
	
	public static String getCreditPaymentXml(String cardHolder, String cardNum, String cardSec, String cardExp) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		buffer.append("<Payment>");
			buffer.append("<Type>Credit</Type>");
			buffer.append("<CardHolder>"+cardHolder+"</CardHolder>");
			buffer.append("<CardNum>"+cardNum+"</CardNum>");
			buffer.append("<CardSec>"+cardSec+"</CardSec>");
			buffer.append("<CardExp>"+cardExp+"</CardExp>");
		buffer.append("</Payment>");
		
		return buffer.toString();
	}
	
	public static String getDeditPaymentXml(String cardHolder, String cardNum, String cardSec, String cardExp, String cardPin) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		buffer.append("<Payment>");
			buffer.append("<Type>Dedit</Type>");
			buffer.append("<CardHolder>"+cardHolder+"</CardHolder>");
			buffer.append("<CardNum>"+cardNum+"</CardNum>");
			buffer.append("<CardSec>"+cardSec+"</CardSec>");
			buffer.append("<CardExp>"+cardExp+"</CardExp>");
			buffer.append("<CardPin>"+cardPin+"</CardPin>");
		buffer.append("</Payment>");
		
		return buffer.toString();
	}
	
	public static String getCustomerXml(String name) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		buffer.append("<Customer>");
			buffer.append("<Name>"+name+"</Name>");
		buffer.append("</Customer>");
		
		return buffer.toString();
	}
}