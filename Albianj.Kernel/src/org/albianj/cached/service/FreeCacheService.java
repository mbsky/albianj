package org.albianj.cached.service;

import java.util.List;

import org.albianj.io.Path;
import org.albianj.logger.AlbianLoggerService;
import org.albianj.service.FreeAlbianService;
import org.albianj.service.parser.IParser;
import org.albianj.xml.XmlParser;
import org.dom4j.Document;

public abstract class FreeCacheService extends FreeAlbianService implements
		IParser
{
	private final static String path = "../config/cache.xml";
	private final static String tagRemoterName = "Cache/Memcached/Group";
	private final static String tagLocalName = "Cache/Local/Group";

	public void init()
	{
		Document doc = null;
		try
		{
			doc = XmlParser.load(Path.getExtendResourcePath(path));
		}
		catch(Exception e)
		{
			AlbianLoggerService.error(e, "There is error when parser the cache config file.");
		}
		if (null == doc) { throw new RuntimeException(
				"load memcache config is error."); }
		@SuppressWarnings("rawtypes")
		List remoterNodes = XmlParser.analyze(doc, tagRemoterName);
		if (null == remoterNodes || 0 == remoterNodes.size())
		{
			AlbianLoggerService.error("There is not %s nodes.", tagRemoterName);
		}
		else
		{
			parserRemoterGroups(remoterNodes);
		}

		@SuppressWarnings("rawtypes")
		List localNodes = XmlParser.analyze(doc, tagLocalName);
		if (null == localNodes || 0 == localNodes.size())
		{
			AlbianLoggerService.error("There is not %s nodes.", tagLocalName);
		}
		else
		{
			parserLocalGroups(localNodes);
		}
		return;
	}

	public void loading()
	{
		init();
		super.loading();
	}

	protected abstract void parserRemoterGroups(
			@SuppressWarnings("rawtypes") List nodes);

	protected abstract void parserLocalGroups(
			@SuppressWarnings("rawtypes") List nodes);
}
