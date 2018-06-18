package databaseconnection;

import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class DOM {
	private String host;
	private String padnaam;
	private String extrastatements;
	private String drivernaam;
	private String gebruikersnaam;
	private String wachtwoord;
	
	
	public void initializeDOM() {
		// TODO Auto-generated method stub
	
	DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder builder = null;{
	try {
	    builder = builderFactory.newDocumentBuilder();
	} 
	catch (ParserConfigurationException e) {
	    e.printStackTrace();  
	}
}
	
	Document document = null;
	try {
	document =  builder.parse(new FileInputStream("resources\\connectie.xml"));
	} catch (SAXException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	Element rootElement = document.getDocumentElement();
	//System.out.println(rootElement);
	
	NodeList nodes = rootElement.getChildNodes();

	for(int i=0; i<nodes.getLength(); i++){
	  Node node = nodes.item(i);
	 
	  
	  if(node instanceof Element){
	    //a child element to process
	    Element child = (Element) node;
	    String tagName = child.getTagName();
	   // System.out.println(tagName);
	    
	    switch (tagName) {
	    case "host": this.host=child.getTextContent();
	    	break;
	    case "padnaam": this.padnaam=child.getTextContent();
	       	break;
	    case "extrastatements" : this.extrastatements=child.getTextContent();
	    	break;
	    case "drivernaam" : this.drivernaam=child.getTextContent();
	    	break;
	    case "gebruikersnaam" : this.gebruikersnaam=child.getTextContent();
	    	break;
	    case "wachtwoord" : this.wachtwoord=child.getTextContent();
	    	break;
	    	default: System.out.println("error parsing xml file");
	    }
	   
	  }
	}

}
	public String getHost() {
		return this.host;
	}
	public String getPadnaam() {
		return this.padnaam;
	}
	public String getExtrastatements() {
		return this.extrastatements;
	}
	public String getDrivernaam() {
		return this.drivernaam;
	}
	public String getGebruikersnaam() {
		return this.gebruikersnaam;
	}
	public String getWachtwoord() {
		return this.wachtwoord;
	}
	
}