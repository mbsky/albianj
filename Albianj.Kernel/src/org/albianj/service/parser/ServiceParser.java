package org.albianj.service.parser;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
//import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//import org.albianj.logger.AlbianLoggerService;
import org.albianj.kernel.AlbianServiceRouter;
import org.albianj.logger.IAlbianLoggerService;
import org.albianj.service.AlbianServiceAttrbuite;
import org.albianj.service.AlbianServiceException;
import org.albianj.service.IAlbianServiceAttrbuite;
import org.albianj.xml.XmlParser;
import org.dom4j.Element;

public class ServiceParser extends FreeServiceParser
{

	private final static String ID_ATTRBUITE_NAME = "Id";
	private final static String TYPE_ATTRBUITE_NAME = "Type";
	IAlbianLoggerService logger; 
	@Override
	public void loading() throws AlbianServiceException
	{
		try
		{
			super.init();
			super.loading();
			logger = AlbianServiceRouter.getService(IAlbianLoggerService.class, "logger");
		}
		catch (MalformedURLException e)
		{
			throw new AlbianServiceException(e.getMessage());
		}
		catch (URISyntaxException e)
		{
			throw new AlbianServiceException(e.getMessage());
		}
		catch (Exception e)
		{
			throw new AlbianServiceException(e.getMessage());
		}
	}

	@Override
	protected Map<String,IAlbianServiceAttrbuite> parserServices(@SuppressWarnings("rawtypes") List nodes) throws NullPointerException,
			AlbianServiceException
	{
		if (null == nodes || 0 == nodes.size())
		{
			String msg = "nodes is null or size is 0";
			if (null != logger)
				logger.error(msg);
			throw new IllegalArgumentException(msg);
		}
		Map<String,IAlbianServiceAttrbuite> map = new LinkedHashMap<String,IAlbianServiceAttrbuite>(nodes.size());
		for (Object node : nodes)
		{
			Element elt = XmlParser.toElement(node);
			IAlbianServiceAttrbuite serviceAttr = parserService(elt);
			map.put(serviceAttr.getId(), serviceAttr);
		}
		return 0 == map.size() ? null : map;
	}

	@Override
	protected IAlbianServiceAttrbuite parserService(Element elt)
			throws NullPointerException, AlbianServiceException
	{
		if (null == elt)
		{
			String msg = "The node is null";
			if (null != logger)
			{
				logger.error(msg);
			}
			throw new IllegalArgumentException(msg);
		}
		IAlbianServiceAttrbuite serviceAttr = new AlbianServiceAttrbuite();
		String id = XmlParser.getAttributeValue(elt, ID_ATTRBUITE_NAME);
		if (null == id || id.trim().isEmpty())
		{
			String msg = "The id is null or empty.";
			if (null != logger)
			{
				logger.error(msg);
			}
			throw new NullPointerException(msg);
		}
		serviceAttr.setId(id);
		String type = XmlParser.getAttributeValue(elt, TYPE_ATTRBUITE_NAME);
		if (null == type || type.trim().isEmpty())
		{
			String msg = String.format("The %1$s Type is null or empty.",
					serviceAttr.getId());
			if (null != logger)
			{
				logger.error(msg);
			}
			throw new NullPointerException(msg);
		}
		serviceAttr.setType(type);
		return serviceAttr;
	}

}
