package cs414.groupH.a5.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
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

        //the URL could come with parameters
        String query = uri.getQuery();
        String response = "";
        if (query != null) {
            //get the XML response
            response = parseOrderRequest(query);

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
    private String parseOrderRequest(String query) {
    	String[] subs = query.split("&");	
		
		String[] type = subs[QUERY_TYPE].split("=");
		
		if (type[QUERY_KEY].equalsIgnoreCase("type") && type[QUERY_VAL].equalsIgnoreCase("place")) {
			
			Order o = orderXMLParser(subs[QUERY_ORDER]);
			ArrayList<String> items = new ArrayList<String>();
			for (MenuItem it : o.getItems()) {
				items.add(it.getName());
			}
			return  getOrderConfXML(SystemManager.createOrder(o.getCustomer(), items, o.getPayments()));
		}
		else if (type[QUERY_KEY].equalsIgnoreCase("type") && type[QUERY_VAL].equalsIgnoreCase("complete")) {
			String[] order = subs[QUERY_ORDER].split("=");
			SystemManager.markOrderComplete(order[QUERY_VAL]);
			return "VALID";	
		}
		else if (type[QUERY_KEY].equalsIgnoreCase("type") && type[QUERY_VAL].equalsIgnoreCase("cancel")) {
			String[] order = subs[QUERY_ORDER].split("=");
			if (OrderManager.removeOrder(OrderManager.findOrder(order[QUERY_VAL])))
				return "VALID";
			else
				return "INVALID";		
		}
		else if (type[QUERY_KEY].equalsIgnoreCase("type") && type[QUERY_VAL].equalsIgnoreCase("get")) {
			String orders = "";
			for (Order o : OrderManager.getOrders()) {
				orders += getOrderConfXML(o);
			}
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
    private String getOrderConfXML(Order o) {
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

}