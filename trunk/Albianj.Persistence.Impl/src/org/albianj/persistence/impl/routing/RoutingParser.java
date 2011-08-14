package org.albianj.persistence.impl.routing;

import java.util.List;
import java.util.Map;

import org.albianj.kernel.AlbianServiceRouter;
import org.albianj.logger.IAlbianLoggerService;
import org.albianj.persistence.object.IRoutingAttribute;
import org.albianj.xml.XmlParser;
import org.dom4j.Element;

public class RoutingParser extends FreeRoutingParser
{
	protected Map<String,IRoutingAttribute> parserRoutings(@SuppressWarnings("rawtypes") List nodes)
	{
		
		return null;
	}
	protected Map<String,IRoutingAttribute> parserRouting(Element elt)
	{
		IAlbianLoggerService logger = AlbianServiceRouter.getService(IAlbianLoggerService.class, "logger");
		String type = XmlParser.getAttributeValue(elt, "Type");
		if(null == type || type.isEmpty())
		{
			if(null != logger)
					logger.error("The albianObject's type is empty or null.");
			return null;
		}
		
		
//		Node writerNode = elt.selectSingleNode("WriterRoutings");
//		
//		String wHash =XmlParser.getAttributeValue(writerNode, "Hash");
		return null;
	}

}
