package org.albianj.persistence.impl.storage;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.albianj.cached.impl.SortCached;
import org.albianj.io.Path;
import org.albianj.kernel.AlbianServiceRouter;
import org.albianj.logger.IAlbianLoggerService;
import org.albianj.persistence.object.IStorageAttribute;
import org.albianj.service.IAlbianServiceAttribute;
import org.albianj.xml.IParser;
import org.albianj.xml.XmlParser;
import org.dom4j.Document;
import org.dom4j.Element;

public abstract class FreeStorageParser implements IParser
{
	private static String path = "../config/storage.xml";
	private static String tagName = "Storages/Storage";


	@Override
	public void init() throws Exception
	{
		Document doc = XmlParser.load(Path.getExtendResourcePath(path));
		@SuppressWarnings("rawtypes")
		List nodes = XmlParser.analyze(doc, tagName);
		IAlbianLoggerService logger = AlbianServiceRouter.getService(IAlbianLoggerService.class, "logger");
		if (null == nodes || 0 == nodes.size())
		{
			String msg = String.format("There is not %1$s nodes.", tagName);
			if (null != logger)
				logger.error(msg);
			throw new UnsupportedOperationException(msg);
		}
		Map<String, IStorageAttribute> map = parserStorages(nodes);
		if (null == map)
		{
			if(null != logger)
				logger.error("The albian services is empty.");
			return;
		}
		return;
	}
	
	protected abstract Map<String, IStorageAttribute> parserStorages(List nodes);
	
	protected abstract IStorageAttribute parserStorage(Element node);
	

}
