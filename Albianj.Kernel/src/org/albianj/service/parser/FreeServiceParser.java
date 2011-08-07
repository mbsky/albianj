package org.albianj.service.parser;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
// import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.albianj.cached.impl.SortCached;
import org.albianj.io.Path;
import org.albianj.logger.AlbianLogger;
import org.albianj.service.AlbianServiceException;
import org.albianj.service.FreeAlbianService;
import org.albianj.service.IAlbianServiceAttrbuite;
import org.albianj.xml.XmlParser;
// import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;

public abstract class FreeServiceParser extends FreeAlbianService implements
		IServiceParser
{

	private static String path = "../config/service.xml";
	private static String tagName = "Services/Service";

	public final static String ALBIANJSERVICEKEY = "@$#&ALBIANJ_ALL_SERVICE&#$@";

	@Override
	public void init() throws MalformedURLException, URISyntaxException,
			Exception
	{
		Document doc = XmlParser.load(Path.getExtendResourcePath(path));
		@SuppressWarnings("rawtypes")
		List nodes = XmlParser.analyze(doc, tagName);
		if (null == nodes || 0 == nodes.size())
		{
			String msg = String.format("There is not %1$s nodes.", tagName);
			if (null != AlbianLogger.KERNEL_LOGGER)
				AlbianLogger.KERNEL_LOGGER.error(msg);
			throw new UnsupportedOperationException(msg);
		}
		Map<String, IAlbianServiceAttrbuite> map = parserServices(nodes);
		if (null == map)
		{
			if(null != AlbianLogger.SERVICE_LOGGER)
				AlbianLogger.SERVICE_LOGGER.error("The albian services is empty.");
			return;
		}
		SortCached.Instance().insert(ALBIANJSERVICEKEY, map);
		return;
	}

	protected abstract Map<String, IAlbianServiceAttrbuite> parserServices(
			@SuppressWarnings("rawtypes") List nodes)
			throws NullPointerException, AlbianServiceException;

	protected abstract IAlbianServiceAttrbuite parserService(Element node)
			throws NullPointerException, AlbianServiceException;
}
