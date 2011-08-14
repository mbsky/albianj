package org.albianj.service.parser;

import java.util.List;
import java.util.Map;
import org.albianj.io.Path;
import org.albianj.kernel.AlbianServiceRouter;
import org.albianj.logger.IAlbianLoggerService;
import org.albianj.service.AlbianServiceException;
import org.albianj.service.FreeAlbianService;
import org.albianj.service.IAlbianServiceAttribute;
import org.albianj.verify.Validate;
import org.albianj.xml.XmlParser;
import org.dom4j.Document;
import org.dom4j.Element;

public abstract class FreeServiceParser extends FreeAlbianService implements
		IServiceParser
{

	private final static String path = "../config/service.xml";
	private final static String tagName = "Services/Service";

	public final static String ALBIANJSERVICEKEY = "@$#&ALBIANJ_ALL_SERVICE&#$@";

	@Override
	public void init()
	{
		
		IAlbianLoggerService logger = AlbianServiceRouter.getService(IAlbianLoggerService.class, "logger");
		Document doc = XmlParser.load(Path.getExtendResourcePath(path));
		if(null == doc)
		{
			throw new AlbianServiceException("load service.xml is error.");
		}
		@SuppressWarnings("rawtypes")
		List nodes = XmlParser.analyze(doc, tagName);
		
		if (Validate.isNullOrEmpty(nodes))
		{
			String msg = String.format("There is not %1$s nodes.", tagName);
			if (null != logger)
				logger.error(msg);
			throw new NullPointerException(msg);
		}
		Map<String, IAlbianServiceAttribute> map = parserServices(nodes);
		if (null == map)
		{
			if(null != logger)
				logger.error("The albian services is empty.");
			return;
		}
		ServiceAttributeCached.insert(ALBIANJSERVICEKEY, map);
		return;
	}

	protected abstract Map<String, IAlbianServiceAttribute> parserServices(
			@SuppressWarnings("rawtypes") List nodes)
			throws NullPointerException, AlbianServiceException;

	protected abstract IAlbianServiceAttribute parserService(Element node)
			throws NullPointerException, AlbianServiceException;
}
