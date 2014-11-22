package cs414.groupH.a5.http;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import cs414.groupH.a5.address.Address;
import cs414.groupH.a5.customer.Customer;
import cs414.groupH.a5.manager.OrderManager;
import cs414.groupH.a5.manager.SystemManager;
import cs414.groupH.a5.menu.MenuItem;
import cs414.groupH.a5.order.Order;
import cs414.groupH.a5.payment.Payment;

public class OrderRequestHandler implements HttpHandler {

	private static final int QUERY_TYPE = 0;
	private static final int QUERY_ORDER = 1;
	private static final int QUERY_KEY = 0;
	private static final int QUERY_VAL = 1;
	private static final int SC_OK = 200;
	private static final int SC_NOTFOUND = 404;
	private static final int SC_ERROR = 500;
	
    //this function is called when an HTTP request is made
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        //this is the URL of the request
        URI uri = exchange.getRequestURI();
        InputStream reqBody = exchange.getRequestBody();
        String rBody = convertToString(reqBody);
        
        // Log URL hits
     	System.out.print(new Date()+" - "+uri.getPath()+"?");

        //the URL could come with parameters
        String query = uri.getQuery();
        String response = "";
        if (query != null) {
        	System.out.print(query);
        	System.out.println();
        	
            //get the XML response
            response = parseOrderRequest(query, rBody);

            if (response.equals("error"))
                exchange.sendResponseHeaders(SC_NOTFOUND, response.length());
            else 
                exchange.sendResponseHeaders(SC_OK, response.length());
            
        }
        else {
            response = "error";
            System.out.println("Order Handler did not find query string in URL.");
            exchange.sendResponseHeaders(SC_ERROR, response.length());
        }

        //output the information
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    //parses the parameters from the URL
    //these are in the form 
    //1. {base_url}/order?type=place&<ORDERXML>
    //2. {base_url}/order?type=complete&id=orderID
    //3. {base_url}/order?type=cancel&id=orderID
    //4. {base_url}/order?type=get&
    private String parseOrderRequest(String query, String reqBody) {
    	String[] subs = query.split("&");
    	System.out.println(reqBody);
		
		String[] type = subs[QUERY_TYPE].split("=");
		
		if (type[QUERY_KEY].equalsIgnoreCase("type") && type[QUERY_VAL].equalsIgnoreCase("place")) {
			
			Order o = orderXMLParser(reqBody);
			
			if (o != null) {
				return "VALID";
			}
			else {
				return "NOT VALID";
			}
			/*ArrayList<String> items = new ArrayList<String>();
			for (MenuItem it : o.getItems()) {
				items.add(it.getName());
			}
			return  getOrderXML(SystemManager.createOrder(o.getCustomer(), items, o.getPayments()));*/
		}
		else if (type[QUERY_KEY].equalsIgnoreCase("type") && type[QUERY_VAL].equalsIgnoreCase("markComplete")) {
			String[] order = subs[QUERY_ORDER].split("=");
			SystemManager.markOrderComplete(order[QUERY_VAL]);
			return "VALID";	
		}
		else if (type[QUERY_KEY].equalsIgnoreCase("type") && type[QUERY_VAL].equalsIgnoreCase("getCust")) {
			String[] orderId = subs[QUERY_ORDER].split("=");
			Order order = OrderManager.findOrder(orderId[QUERY_VAL]);
			if (order != null)
				return order.getCustomer().getName();
			else
				return "INVALID";		
		}
		else if (type[QUERY_KEY].equalsIgnoreCase("type") && type[QUERY_VAL].equalsIgnoreCase("getAddr")) {
			String[] orderId = subs[QUERY_ORDER].split("=");
			Order order = OrderManager.findOrder(orderId[QUERY_VAL]);
			if (order != null) {
				Address addr = order.getCustomer().getAddress();
				String ret = addr.getStreet()+","+addr.getCity()+","+addr.getState()+","+addr.getZip()+","+addr.getPhone();
				return ret;
			}
			else
				return "INVALID";		
		}
		else if (type[QUERY_KEY].equalsIgnoreCase("type") && type[QUERY_VAL].equalsIgnoreCase("getItems")) {
			String[] orderId = subs[QUERY_ORDER].split("=");
			Order order = OrderManager.findOrder(orderId[QUERY_VAL]);
			if (order != null) {
				ArrayList<MenuItem> items = order.getItems();
				String ret = "";
				for (int i=0; i<items.size(); i++) {
					MenuItem item = items.get(i);
					ret += item.getName();
					if (i != items.size()-1) {
						ret += ",";
					}
				}
				
				return ret;
			}
			else
				return "INVALID";		
		}
		else if (type[QUERY_KEY].equalsIgnoreCase("type") && type[QUERY_VAL].equalsIgnoreCase("get")) {
			String orders = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><orders>";
			for (Order o : OrderManager.getOrders()) {
				orders += getOrderXML(o);
			}
			orders += "</orders>";
			return orders;
		}
		else
			return "error";
    }

    private Order orderXMLParser(String xmlInput) {
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
            dom = db.parse(new InputSource(new ByteArrayInputStream(xmlInput.getBytes("utf-8"))));


            //get the root elememt
            Element docEle = dom.getDocumentElement();

            Address addr = new Address();
            NodeList nl = docEle.getElementsByTagName("address");
            if(nl != null && nl.getLength() > 0) {
                for(int i = 0 ; i < nl.getLength();i++) {
                    //get the customer element
                    Element el = (Element)nl.item(i);

                    //get the Customer object
                    addr = getAddress(el);
                }
            }
            
            //get a nodelist of <Customer> elements
            nl = docEle.getElementsByTagName("customer");
            if(nl != null && nl.getLength() > 0) {
                for(int i = 0 ; i < nl.getLength();i++) {
                    //get the customer element
                    Element el = (Element)nl.item(i);

                    //get the Customer object
                    cust = getCustomer(el, addr);
                }
            }

            //get a nodelist of <Item> elements
            nl = docEle.getElementsByTagName("items");
            if(nl != null && nl.getLength() > 0) {
                for(int i = 0 ; i < nl.getLength();i++) {
                    //get the item element
                    Element el = (Element)nl.item(i);

                    List<MenuItem> items = getItem(el);
                    for (MenuItem item : items) {
                    	itemNames.add(item.getName());
                    }
                }
            }

            //get a nodelist of <Payment> elements
            nl = docEle.getElementsByTagName("payments");
            if(nl != null && nl.getLength() > 0) {
                for(int i = 0 ; i < nl.getLength();i++) {
                    //get the payment element
                    Element el = (Element)nl.item(i);

                    //get the MenuItem object and adds it to list
                    payments = getPayment(el);
                }
            }

            return SystemManager.createOrder(cust, itemNames, payments);

        }catch(ParserConfigurationException pce) {
            pce.printStackTrace();
        }catch(SAXException se) {
            se.printStackTrace();
        }catch(IOException ioe) {
            ioe.printStackTrace();
        }

        return null;
    }

    //Turns the order confirmation into XML
    private String getOrderXML(Order o) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("<order>");

        buffer.append("<orderId>");
        buffer.append(o.getOrderId());
        buffer.append("</orderId>");
        
        buffer.append("<Complete>");
        buffer.append(o.isComplete());
        buffer.append("</Complete>");

        buffer.append("<customer>");
	        buffer.append("<name>");
	        buffer.append(o.getCustomer().getName());
	        buffer.append("</name>");
        buffer.append("</customer>");
        buffer.append("<address>");
	        buffer.append("<street>");
	        buffer.append(o.getCustomer().getAddress().getStreet());
	        buffer.append("</street>");
	        buffer.append("<city>");
	        buffer.append(o.getCustomer().getAddress().getCity());
	        buffer.append("</city>");
	        buffer.append("<state>");
	        buffer.append(o.getCustomer().getAddress().getState());
	        buffer.append("</state>");
	        buffer.append("<zipCode>");
	        buffer.append(o.getCustomer().getAddress().getZip());
	        buffer.append("</zipCode>");
	        buffer.append("<phone>");
	        buffer.append(o.getCustomer().getAddress().getPhone());
	        buffer.append("</phone>");
        buffer.append("</address>");

        buffer.append("<items>");
        for (MenuItem item : o.getItems()) {
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
        buffer.append("</items>");
        
        buffer.append("<payments>");
        for (Payment p : o.getPayments()) {
        	buffer.append("<payment>");
	            buffer.append("<amount>");
	            buffer.append(p.getAmount());
	            buffer.append("</amount>");
            buffer.append("</payment>");
        }
        buffer.append("</payments>");

        buffer.append("</order>");

        return buffer.toString();
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
    
    private Address getAddress(Element el) {
    	String street = getTextValue(el, "Street");
    	String city = getTextValue(el, "City");
    	String state = getTextValue(el, "State");
    	String zip = getTextValue(el, "Zip");
    	String phone = getTextValue(el, "Phone");
    	
    	return new Address(street, city, state, zip, phone);
    }

    private Customer getCustomer(Element empEl, Address addr) {
        String name = getTextValue(empEl, "Name");

        //Create a new Customer with the value read from the xml nodes
        Customer cust = new Customer(name, addr);

        return cust;
    }
    
    private List<MenuItem> getItem(Element empEl) {
    	List<MenuItem> items = new ArrayList<MenuItem>();
        String name = "";
        double price = 0.0;
        String special = "";
        
        NodeList nl = empEl.getElementsByTagName("item");
        if(nl != null && nl.getLength() > 0) {
            for(int i = 0 ; i < nl.getLength();i++) {
                //get the customer element
                Element el = (Element)nl.item(i);
            
                name = getTextValue(el, "name");
                price = getDoubleValue(el, "price");
                special = getTextValue(el, "special");
                
                items.add(new MenuItem(name, price, Boolean.parseBoolean(special)));
            }
        }

        return items;
    }

    private List<Payment> getPayment(Element empEl) {
    	List<Payment> payments = new ArrayList<Payment>();
        
        NodeList nl = empEl.getElementsByTagName("item");
        if(nl != null && nl.getLength() > 0) {
            for(int i = 0 ; i < nl.getLength();i++) {
                //get the customer element
                Element el = (Element)nl.item(i);
            
                double amount = getDoubleValue(el, "amount");
                
                payments.add(new Payment(amount));
            }
        }

        return payments;
    }
    
  //converts an input stream to a string
  	private static String convertToString(InputStream is) {
  		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
  		StringBuffer buff = new StringBuffer();

  		String line = null;
  		try {
  			line = reader.readLine();
  			while (line != null) 
  			{
  				buff.append(line);
  				line = reader.readLine();
  			}
  			is.close();
  		} 
  		catch (Exception e) {
  			System.out.println(e.toString());
  		} 
  		return buff.toString();
  	}

}