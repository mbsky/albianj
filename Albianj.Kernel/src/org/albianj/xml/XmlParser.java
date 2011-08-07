package org.albianj.xml;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.albianj.logger.AlbianLoggerService;
import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
//import org.w3c.dom.Element;

public final class XmlParser
{
	static Logger logger = Logger.getLogger(XmlParser.class.getName()); 
	public static Document load(String path) throws Exception
	{
		try
		{
			 SAXReader reader = new SAXReader();   
			 Document doc = reader.read(new File(path));
			 return doc;	
		}
		catch(Exception exc)
		{
			if(null != logger)
				logger.error(String.format("%1$s,init xml document with path \"%2$s\" is error.msg:%3$s", AlbianLoggerService.ERROR,path,exc.getMessage()));
			throw exc;
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static List analyze(Document doc,String tagName)
	{
		
		return doc.selectNodes(tagName);
	}
	
	public static Element toElement(Object node)
	{
		return (Element) node;
	}
	
	public static Element analyzeSingle(Document doc,String tagName)
	{
		@SuppressWarnings("rawtypes")
		List nodes = analyze(doc, tagName);
		if(null == nodes || 0 == nodes.size())
			return null;
		@SuppressWarnings("rawtypes")
		Iterator it=nodes.iterator();   
		return it.hasNext() ? (Element) it.next() : null;
	}
	
	public static String getAttributeValue(Element ele, String attributeName)
    {
		Attribute attr = ele.attribute(attributeName);
		if(null == attr) return null;
		return attr.getValue();
    }

    public static String getAttributeValue(Document doc, String tagName, String attributeName)
    {
        return getAttributeValue(analyzeSingle(doc,tagName),attributeName);
    }

    public static boolean hasAttributes(Element ele)
    {
        return null == ele ? false : null != ele.attributes() && 0 != ele.attributes().size();
    }

    public static String getNodeValue(Element ele)
    {
    	if(null == ele) return null;
    	return ele.getText();
    }

    public static String getNodeValue(Document doc, String tagName)
    {
    	Element ele = analyzeSingle(doc, tagName);
    	if(null == ele) return null;
    	return ele.getText();
    }
    
	
	
}
