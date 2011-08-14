package org.albianj.persistence.impl.persistence;

import java.util.List;
import org.albianj.io.Path;
import org.albianj.kernel.AlbianServiceRouter;
import org.albianj.logger.IAlbianLoggerService;
import org.albianj.persistence.object.IAlbianObjectAttribute;
import org.albianj.verify.Validate;
import org.albianj.xml.IParser;
import org.albianj.xml.XmlParser;
import org.dom4j.Document;
import org.dom4j.Element;

public abstract class FreePersistenceParser implements IParser
{

	private final static String path = "../config/persistence.xml";
	private final static String tagName = "AlbianObjects/AlbianObject";
	
	@Override
	public void init()
	{
		Document doc = XmlParser.load(Path.getExtendResourcePath(path));
		if(null == doc)
		{
			throw new PersistenceAttributeException("load persistence is error.");
		}
		@SuppressWarnings("rawtypes")
		List nodes = XmlParser.analyze(doc, tagName);
		IAlbianLoggerService logger = AlbianServiceRouter.getService(IAlbianLoggerService.class, "logger");
		if (Validate.isNullOrEmpty(nodes))
		{
			String msg = String.format("There is not %1$s nodes.", tagName);
			if (null != logger)
				logger.error(msg);
			throw new UnsupportedOperationException(msg);
		}
		parserAlbianObjects(nodes);
		return;
	}
	
	protected abstract  void parserAlbianObjects(@SuppressWarnings("rawtypes") List nodes);
	protected abstract IAlbianObjectAttribute parserAlbianObject(Element node);

}
