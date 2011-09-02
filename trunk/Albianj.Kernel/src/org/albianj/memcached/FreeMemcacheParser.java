package org.albianj.memcached;

import java.util.List;

import org.albianj.io.Path;
import org.albianj.logger.AlbianLoggerService;
import org.albianj.service.FreeAlbianService;
import org.albianj.xml.IParser;
import org.albianj.xml.XmlParser;
import org.dom4j.Document;

public abstract class FreeMemcacheParser extends FreeAlbianService implements IParser
{
	private final static String path = "../config/memcached.xml";
	private final static String tagName = "Memcached/Group";
	public void init()
	{
		Document doc = XmlParser.load(Path.getExtendResourcePath(path));
		if(null == doc)
		{
			throw new RuntimeException("load memcache config is error.");
		}
		@SuppressWarnings("rawtypes")
		List nodes = XmlParser.analyze(doc, tagName);
		if (null == nodes || 0 == nodes.size())
		{
			String msg = String.format("There is not %1$s nodes.", tagName);
				AlbianLoggerService.error(msg);
			throw new UnsupportedOperationException(msg);
		}
//		List<IMemcacheGroup> groups = 
				parserGroups(nodes);
		return;
	}
	
	protected abstract void parserGroups(@SuppressWarnings("rawtypes") List nodes);
}
