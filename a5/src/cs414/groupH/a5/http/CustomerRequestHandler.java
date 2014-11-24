package cs414.groupH.a5.http;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.util.Date;

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
import cs414.groupH.a5.manager.CustomerManager;
import cs414.groupH.a5.rewards.RewardsSystem;

public class CustomerRequestHandler implements HttpHandler {
	
	private static final int QUERY_TYPE = 0;
	private static final int QUERY_ID = 1;
	private static final int QUERY_PW = 2;
	private static final int QUERY_KEY = 0;
	private static final int QUERY_VAL = 1;
	private static final int SC_OK = 200;
	private static final int SC_NOTFOUND = 404;
	//private static final int SC_ERROR = 500;
	
	//this function is called when an HTTP request is made
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		//this is the URL of the request
		URI uri = exchange.getRequestURI();
		InputStream reqBody = exchange.getRequestBody();
        String rBody = convertToString(reqBody);
		
		// Log URL hits
     	System.out.print(new Date()+" - "+uri.getPath()+"?");
		
		String query = uri.getQuery();
		String response = "";
        //if there was a query it was an order request.
		if (query != null) {
			
			response = parseCustomerQuery(query, rBody);
			
			if (response.equalsIgnoreCase("error"))
				exchange.sendResponseHeaders(SC_NOTFOUND, response.getBytes().length);
			else 
				exchange.sendResponseHeaders(SC_OK, response.getBytes().length);
		}
		else {
			response = "error";
			System.out.println("Customer Handler did not find a query string in URL.");
			exchange.sendResponseHeaders(SC_NOTFOUND, response.getBytes().length);
		}

		//output the information
		OutputStream os = exchange.getResponseBody();
		os.write(response.getBytes());
		os.close();
	}
	
	//parses the parameters from the URL
    //Option 1: /employee?type=Login&id=empID&pw=empPW
	//Option 2: /employee?type=Logout&id=empID
	//Option 3: /employee?type=TypeCheck&id=empID
	private String parseCustomerQuery(String query, String request) {
		System.out.print(query);
    	System.out.println();
    	
		String retValue = "";
		//split the query based on parameters
		String[] subs = query.split("&");
		
		String[] type = subs[QUERY_TYPE].split("=");
		if (type[QUERY_KEY].equalsIgnoreCase("type")) {
			if (type[QUERY_VAL].equalsIgnoreCase("login")) {
				String[] uname = subs[QUERY_ID].split("=");
				String[] pw = subs[QUERY_PW].split("=");
				Customer cust = CustomerManager.findCustomer(uname[QUERY_VAL]);
				if (cust != null) {
					if (cust.verifyPassword(pw[QUERY_VAL]))
						retValue = cust.getUsername();
					else
						retValue = "INVALID";
				}
				else
					retValue = "invalid";
			}
			else if (type[QUERY_VAL].equalsIgnoreCase("add")) {
				
				Customer cust = customerXmlParser(request);
				CustomerManager.addEmployee(cust);
				RewardsSystem.newMember(cust.getUsername());
				
				return "SUCCESS";
			}
			else if (type[QUERY_VAL].equalsIgnoreCase("addRewardPoint")) {
				String[] uname = subs[QUERY_ID].split("=");
				
				boolean res = RewardsSystem.addPoints(uname[QUERY_VAL], 1);
				
				if (res) {
					return "SUCCESS";
				}
				else {
					return "FAILURE";
				}
			}
			else if (type[QUERY_VAL].equalsIgnoreCase("unameExist")) {
				String[] uname = subs[QUERY_ID].split("=");
				Customer cust = CustomerManager.findCustomer(uname[QUERY_VAL]);
				if (cust == null) {
					return "available";
				}
				else
					retValue = "exists";
			}
			else if (type[QUERY_VAL].equalsIgnoreCase("getThreshold")) {
				return Integer.toString(RewardsSystem.getThreshold());
			}
			else if (type[QUERY_VAL].equalsIgnoreCase("setThreshold")) {
				String[] threshold = subs[QUERY_ID].split("=");
				RewardsSystem.setThreshold(Integer.parseInt(threshold[QUERY_VAL]));
				return "SUCCESS";
			}
			else if (type[QUERY_VAL].equalsIgnoreCase("rewardavailable")) {
				String[] uname = subs[QUERY_ID].split("=");
				Customer cust = CustomerManager.findCustomer(uname[QUERY_VAL]);
				if (cust != null) {
					if (RewardsSystem.isEligible(cust.getUsername())) {
						retValue = "TRUE";
					}
					else {
						retValue = "FALSE";
					}
				}
				else
					retValue = "FALSE";
			}
			else if (type[QUERY_VAL].equalsIgnoreCase("redeemReward")) {
				String[] uname = subs[QUERY_ID].split("=");
				Customer cust = CustomerManager.findCustomer(uname[QUERY_VAL]);
				if (cust != null) {
					if (RewardsSystem.isEligible(cust.getUsername())) {
						boolean res = RewardsSystem.redeemCertificate(cust.getUsername());
						if (res) {
							retValue = "SUCCESS";
						}
						else {
							retValue = "ERROR";
						}
					}
					else {
						retValue = "ERROR";
					}
				}
				else
					retValue = "ERROR";
			}
		}
		else {
			retValue = "error";
		}
		
		return retValue;
	}
	
	private Customer customerXmlParser(String xmlInput) {
        Customer cust = new Customer();

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

            return cust;

        }catch(ParserConfigurationException pce) {
            pce.printStackTrace();
        }catch(SAXException se) {
            se.printStackTrace();
        }catch(IOException ioe) {
            ioe.printStackTrace();
        }

        return null;
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
    	String name = getTextValue(empEl, "name");
        String uname = getTextValue(empEl, "uname");
        String password = getTextValue(empEl, "password");
        
        Customer cust = new Customer(name, addr, uname, password);

        return cust;
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
