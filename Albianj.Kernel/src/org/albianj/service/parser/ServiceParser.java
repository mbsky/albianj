package org.albianj.service.parser;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.albianj.kernel.AlbianServiceRouter;
import org.albianj.logger.IAlbianLoggerService;
import org.albianj.service.AlbianServiceAttribute;
import org.albianj.service.AlbianServiceException;
import org.albianj.service.IAlbianServiceAttribute;
import org.albianj.verify.Validate;
import org.albianj.xml.XmlParser;
import org.dom4j.Element;

public class ServiceParser extends FreeServiceParser
{

	private final static String ID_ATTRBUITE_NAME = "Id";
	private final static String TYPE_ATTRBUITE_NAME = "Type";
	private IAlbianLoggerService logger; 
	@Override
	public void loading()
	{
		super.init();
		super.loading();
		logger = AlbianServiceRouter.getService(IAlbianLoggerService.class, "logger");
	}

	@Override
	protected Map<String,IAlbianServiceAttribute> parserServices(@SuppressWarnings("rawtypes") List nodes) 
	{
		if (Validate.isNullOrEmpty(nodes))
		{
			String msg = "nodes is null or size is 0";
			if (null != logger)
				logger.error(msg);
			throw new IllegalArgumentException(msg);
		}
		Map<String,IAlbianServiceAttribute> map = new LinkedHashMap<String,IAlbianServiceAttribute>(nodes.size());
		for (Object node : nodes)
		{
			Element elt = XmlParser.toElement(node);
			IAlbianServiceAttribute serviceAttr = parserService(elt);
			if(null == serviceAttr)
			{
				throw new NullPointerException("parser service node is error.");
			}
			map.put(serviceAttr.getId(), serviceAttr);
		}
		return 0 == map.size() ? null : map;
	}

	@Override
	protected IAlbianServiceAttribute parserService(Element elt)
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
		IAlbianServiceAttribute serviceAttr = new AlbianServiceAttribute();
		String id = XmlParser.getAttributeValue(elt, ID_ATTRBUITE_NAME);
		if (Validate.isNullOrEmptyOrAllSpace(id))
		{
			if (null != logger)
			{
				logger.error("The id is null or empty.");
			}
			return null;
		}
		serviceAttr.setId(id);
		String type = XmlParser.getAttributeValue(elt, TYPE_ATTRBUITE_NAME);
		if (Validate.isNullOrEmptyOrAllSpace(type))
		{
			if (null != logger)
			{
				logger.error("The",serviceAttr.getId(),"Type is null or empty.");
			}
			return null;
		}
		serviceAttr.setType(type);
		return serviceAttr;
	}

}
