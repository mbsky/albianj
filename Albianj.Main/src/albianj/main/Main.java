package albianj.main;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import org.albianj.io.*;
import org.albianj.service.AlbianServiceException;
import org.albianj.xml.XmlParser;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.dom4j.Document;
import org.dom4j.Element;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
public class Main
{

	/**
	 * @param args
	 */
	
	static Logger logger = Logger.getLogger(Main.class.getName()); 
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		String path = null;
		try
		{
			DOMConfigurator.configure(Path.getExtendResourcePath("../config/log4j.xml"));
//			logger.info("aasdsadasd");
			try
			{
				Document doc = XmlParser.load(Path.getExtendResourcePath("../config/service.xml"));
				List nodes = XmlParser.analyze(doc, "Services/Service");
				 Iterator it=nodes.iterator();   
			     while(it.hasNext()){   
			       Element ele=(Element)it.next();         
			       System.out.println(ele.getText());   
			     }   
				return;
			}
			catch (Exception e)			
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		catch (IOException e)
		{
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (URISyntaxException e)
		{
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(path);
		return;

	}

}
