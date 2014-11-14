package cs414.groupH.a4.manager;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import cs414.groupH.a4.address.Address;
import cs414.groupH.a4.customer.Customer;
import cs414.groupH.a4.employee.Employee;
import cs414.groupH.a4.employee.EmployeeType;
import cs414.groupH.a4.menu.Menu;
import cs414.groupH.a4.menu.MenuItem;
import cs414.groupH.a4.order.Order;
import cs414.groupH.a4.payment.Payment;

public class SystemManager implements HttpHandler {
	
	//this function is called when an HTTP request is made
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		//this is the URL of the request
		URI uri = exchange.getRequestURI();
		
		//the URL could come with parameters
		String query = uri.getQuery();
		String response;
		if (query != null) {
			//get the XML response
			response = parseQuery(query);
			
			if (response.equals("error")) {
				response = "An error occurred! Please try your request again. Sorry for the inconvenience.";
				exchange.sendResponseHeaders(500, response.length());
			}
			
			//send the response
			//200 means the request was successful 
			exchange.sendResponseHeaders(200, response.length());
		}
		else {
			response = "An error occurred! Please try your request again. Sorry for the inconvenience.";
			exchange.sendResponseHeaders(500, response.length());
		}

		//output the information
		OutputStream os = exchange.getResponseBody();
		os.write(response.getBytes());
		os.close();
	}
	
	//parses the parameters from the URL
	//these are in the form {base_url}?customer=name&size=pizza_size&toppings=t1-t2-..-tn
	private String parseQuery(String xmlInput) {
		Document dom;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {
			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			//parse using builder to get DOM representation of the XML file
			dom = db.parse(xmlInput);
			
			//get the root elememt
			Element docEle = dom.getDocumentElement();
			
			String request = getTextValue(docEle, "Request");
			
			switch (request) {
				case "getMenu":
					return getMenuXml();
				case "createOrder":
					Order order = xmlParser(xmlInput);
					if (order != null) {
						return getOrderConfXml(order);
					}
					else {
						return "error";
					}
				default:
					return "error";
			}
			
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
		return "error";
		
		//split the query based on parameters
		/*String[] subs = query.split("&");
		for (String parameter : subs) {	
			//key is on the left and value is on the right, so we split this
			String[] values = parameter.split("=");
			params.put(values[0], values[1]);
		}*/
	}
	
	private Order xmlParser(String xmlInput) {
		Customer cust = new Customer();
		List<String> itemNames = new ArrayList<String>();
		List<Payment> payments = new ArrayList<Payment>();
		
		Document dom;
		//get the factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {
			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			//parse using builder to get DOM representation of the XML file
			dom = db.parse(xmlInput);
			
			
			//get the root elememt
			Element docEle = dom.getDocumentElement();
			
			//get a nodelist of <Customer> elements
			NodeList nl = docEle.getElementsByTagName("Customer");
			if(nl != null && nl.getLength() > 0) {
				for(int i = 0 ; i < nl.getLength();i++) {
					//get the customer element
					Element el = (Element)nl.item(i);
					
					//get the Customer object
					cust = getCustomer(el);
				}
			}
			
			//get a nodelist of <Item> elements
			nl = docEle.getElementsByTagName("Item");
			if(nl != null && nl.getLength() > 0) {
				for(int i = 0 ; i < nl.getLength();i++) {
					//get the item element
					Element el = (Element)nl.item(i);
					
					//get the MenuItem object and adds it to list
					itemNames.add(getTextValue(el, "Name"));
				}
			}
			
			//get a nodelist of <Payment> elements
			nl = docEle.getElementsByTagName("Payment");
			if(nl != null && nl.getLength() > 0) {
				for(int i = 0 ; i < nl.getLength();i++) {
					//get the payment element
					Element el = (Element)nl.item(i);
					
					//get the MenuItem object and adds it to list
					payments.add(getPayment(el));
				}
			}
			
			return createOrder(cust, itemNames, payments);
			
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
		return null;
	}
	
	private Customer getCustomer(Element empEl) {
		String name = getTextValue(empEl, "Name");
		String street = getTextValue(empEl, "Street");
		String city = getTextValue(empEl, "City");
		String state = getTextValue(empEl, "State");
		String zip = getTextValue(empEl, "Zip");
		String phone = getTextValue(empEl, "Phone");
		
		//Create a new Customer with the value read from the xml nodes
		Customer cust = new Customer(name, new Address(street, city, state, zip, phone));
		
		return cust;
	}
	
	private Payment getPayment(Element empEl) {
		double amount = getDoubleValue(empEl, "Amount");
		String validated = getTextValue(empEl, "Validated");
		
		//Create a new Customer with the value read from the xml nodes
		Payment payment = new Payment(amount, Boolean.parseBoolean(validated));
		
		return payment;
	}
	
	private String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			Element el = (Element)nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}

		return textVal;
	}
	
	private double getDoubleValue(Element ele, String tagName) {
		//in production application you would catch the exception
		return Double.parseDouble(getTextValue(ele,tagName));
	}
	
	
	//Turns the menu into an XML representation
	private String getMenuXml() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		buffer.append("<menu>");
		for (MenuItem item : getMenuItems()) {
			buffer.append("<item>");
			
			buffer.append("<name>");
			buffer.append(item.getName());
			buffer.append("</name>");
			buffer.append("<price>");
			buffer.append(item.getPrice());
			buffer.append("</price>");
			buffer.append("<special>");
			buffer.append(item.isDailySpecial());
			buffer.append("</special>");

			buffer.append("</item>");
		}
		buffer.append("</menu>");

		return buffer.toString();
	}
	
	//Turns the order confirmation into XML
	private String getOrderConfXml(Order o) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		buffer.append("<order>");

		buffer.append("<orderId>");
		buffer.append(o.getOrderId());
		buffer.append("</orderId>");
		
		buffer.append("<customer>");
		buffer.append("<name>");
		buffer.append(o.getCustomer().getName());
		buffer.append("</name>");
		buffer.append("<address>");
		buffer.append(o.getCustomer().getAddress());
		buffer.append("</address>");
		buffer.append("</customer>");

		buffer.append("<items>");
		for (MenuItem item : o.getItems()) {
			buffer.append("<name>");
			buffer.append(item.getName());
			buffer.append("</name>");
			buffer.append("<price>");
			buffer.append(item.getPrice());
			buffer.append("</price>");
		}
		buffer.append("</items>");

		buffer.append("</order>");

		return buffer.toString();
	}

	public static boolean addEmployee(String employeeId, String name, String pwd, EmployeeType empType) {
		Employee emp = new Employee(employeeId, name, pwd, empType);
		return EmployeeManager.addEmployee(emp);
	}
	
	public static boolean removeEmployee(String employeeId, String name, String pwd, EmployeeType empType) {
		Employee emp = new Employee(employeeId, name, pwd, empType);
		return EmployeeManager.removeEmployee(emp);
	}

	public static boolean addMenuItem(String name, double price, boolean isDailySpecial) {
		return Menu.addMenuItem(new MenuItem(name, price, isDailySpecial));
	}

	public static boolean editMenuItem(String oldName, String newName, double price, boolean isDailySpecial) {
		return Menu.editMenuItem(oldName, new MenuItem(newName, price, isDailySpecial));
	}

	public static void removeMenuItem(MenuItem item) {
		Menu.removeMenuItem(item);
	}
	
	public static List<MenuItem> getMenuItems() {
		return Menu.getMenuItems();
	}	
	
	/*public static String createOrderFromApp(Map<String, String> params) {
		String street = params.get("street");
		String city = params.get("city");
		String state = params.get("state");
		String zip = params.get("zip");
		String phone = params.get("phone");
		Customer cust = new Customer(params.get("customer"), new Address(street, city, state, zip, phone));
		
		Order newOrder = new Order(cust);
		OrderManager.addOrder(newOrder);
		for (String i : itemNames) {
			MenuItem item = Menu.findMenuItem(i);
			newOrder.addItem(item);
		}
		for(Payment p : payments){
			newOrder.addPayment(p);
		}
				
		return newOrder.getOrderId();
	}*/
	public static Order createOrder(Customer cust, List<String> itemNames, List<Payment> payments) {
		Order newOrder = new Order(cust);
		OrderManager.addOrder(newOrder);
		for (String i : itemNames) {
			MenuItem item = Menu.findMenuItem(i);
			newOrder.addItem(item);
		}
		for(Payment p : payments){
			newOrder.addPayment(p);
		}
				
		return newOrder;
	}

	public static Order findOrder(String orderId) {
		return OrderManager.findOrder(orderId);
	}

	public static void markOrderComplete(String orderId) {
		OrderManager.markOrderComplete(OrderManager.findOrder(orderId));
	}
	
	public static boolean addPayment(Map<String, String> params) {
		return true;
	}
	public static boolean addPayment(String orderId, Payment payment) {
		return OrderManager.findOrder(orderId).addPayment(payment);
	}
	
	public static List<Order> getOrders() {
		return OrderManager.getOrders();
	}
	
}