package cs414.groupH.a5.http;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XmlParser {
	
	public static String parseMenu(String menuXml) {
		String ret = "";
		
		Document dom;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {
			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			//parse using builder to get DOM representation of the XML file
			dom = db.parse(new InputSource(new ByteArrayInputStream(menuXml.getBytes("utf-8"))));
			
			//get the root elememt
			Element docEle = dom.getDocumentElement();
			
			//get a nodelist of <Item> elements
			NodeList nl = docEle.getElementsByTagName("Item");
			//System.out.println(nl.getLength());
			if(nl != null && nl.getLength() > 0) {
				for(int i = 0 ; i < nl.getLength();i++) {
					if (i != 0) {
						ret += ",";
					}
					//get the item element
					Element el = (Element)nl.item(i);
					
					//get the MenuItem object and adds it to list
					ret += getTextValue(el, "Name")+",";
					ret += getTextValue(el, "Price")+",";
					ret += getTextValue(el, "Special");
				}
			}
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
		return ret;
	}
	
	public static String parseViewOrder(String orderXml) {
		String ret = "";
		
		Document dom;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {
			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			//parse using builder to get DOM representation of the XML file
			dom = db.parse(new InputSource(new ByteArrayInputStream(orderXml.getBytes("utf-8"))));
			
			//get the root elememt
			Element docEle = dom.getDocumentElement();
			
			System.out.println(docEle.getTagName());
			
			ret += getTextValue(docEle, "orderId")+",";
			ret += getTextValue(docEle, "Complete");
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
		return ret;
	}
	
	private static String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			Element el = (Element)nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}

		return textVal;
	}
}
