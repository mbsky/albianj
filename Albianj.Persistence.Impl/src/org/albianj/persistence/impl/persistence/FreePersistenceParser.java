package org.albianj.persistence.impl.persistence;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;

import org.albianj.io.Path;
import org.albianj.kernel.AlbianServiceRouter;
import org.albianj.logger.IAlbianLoggerService;
import org.albianj.persistence.object.IAlbianObjectAttribute;
import org.albianj.xml.IParser;
import org.albianj.xml.XmlParser;
import org.dom4j.Document;
import org.dom4j.Element;

public abstract class FreePersistenceParser implements IParser
{

	private final static String path = "../config/persistence.xml";
	private final static String tagName = "AlbianObjects/AlbianObject";
	
	@Override
	public void init() throws RuntimeException
	{
		Document doc = null;
		try
		{
			doc = XmlParser.load(Path.getExtendResourcePath(path));
		}
		catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (URISyntaxException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(null == doc)
		{
			throw new RuntimeException("load persistence is error.");
		}
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
		parserAlbianObjects(nodes);
		return;
	}
	
	protected abstract  void parserAlbianObjects(List nodes);
	protected abstract IAlbianObjectAttribute parserAlbianObject(Element node);

}
