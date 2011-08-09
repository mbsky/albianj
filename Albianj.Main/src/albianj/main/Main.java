package albianj.main;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import org.albianj.io.*;
import org.albianj.kernel.AlbianBootService;
import org.albianj.kernel.AlbianServiceRouter;
import org.albianj.kernel.AlbianState;
import org.albianj.logger.IAlbianLoggerService;
import org.albianj.service.AlbianServiceException;
import org.albianj.xml.XmlParser;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.dom4j.Document;
import org.dom4j.Element;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.Element;
//import org.w3c.dom.NodeList;
public class Main
{

	/**
	 * @param args
	 */
	
	static Logger logger = Logger.getLogger(Main.class.getName()); 
	public static void main(String[] args)
	{
		try
		{
			AlbianBootService.start();
			while(AlbianState.Running != AlbianBootService.getLifeState())
			{
				Thread.sleep(1000);
			}
			IAlbianLoggerService logger = AlbianServiceRouter.getService(IAlbianLoggerService.class, "logger");
			logger.info("i","heat","java");
			return;
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		
		
		
		
		
		// TODO Auto-generated method stub
//		String path = null;
//		try
//		{
////			DOMConfigurator.configure(Path.getExtendResourcePath("../config/log4j.xml"));
//			logger.info("aasdsadasd");
////			try
////			{
//////				Document doc = XmlParser.load(Path.getExtendResourcePath("../config/service.xml"));
//////				List nodes = XmlParser.analyze(doc, "Services/Service");
//////				 Iterator it=nodes.iterator();   
//////			     while(it.hasNext()){   
//////			       Element ele=(Element)it.next();         
//////			       //System.out.println(ele.getText());
//////			       String base = XmlParser.getAttributeValue(ele, "Base");
//////			       System.out.println(base);
//////			       logger.info(base);
////			     }   
//				return;
//			}
//			catch (Exception e)			
//			{
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		catch (IOException e)
//		{
//			
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		catch (URISyntaxException e)
//		{
//			
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(path);
//		return;

	}

}
