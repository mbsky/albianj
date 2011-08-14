package org.albianj.persistence.impl.routing;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import org.albianj.io.Path;
import org.albianj.kernel.AlbianServiceRouter;
import org.albianj.logger.IAlbianLoggerService;
import org.albianj.persistence.object.IRoutingAttribute;
import org.albianj.xml.IParser;
import org.albianj.xml.XmlParser;
import org.dom4j.Document;
import org.dom4j.Element;

//<AlbianObjects>
//<AlbianObject Type="AppTest.Model.Imp.BizOffer">
//	<WriterRoutings Hash="true">
//		<WriteRouting Name="IdRouting" StorageName="2thStorage"
//			TableName="BizOfferById" Owner="dbo"></WriteRouting>
//	</WriterRoutings>
//	<ReaderRoutings Hash="true">
//		<ReaderRouting Name="CreateTimeRouting" StorageName="3thStorage"
//			TableName="BizOfferByCreateTime" Owner="dbo"></ReaderRouting>
//	</ReaderRoutings>
//</AlbianObject>
//</AlbianObjects>
			
public abstract class FreeRoutingParser implements IParser
{
	private final static String path = "../config/routing.xml";
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
		parserRoutings(nodes);
		return;

	}
	
	protected abstract Map<String,IRoutingAttribute> parserRoutings(List nodes);
	protected abstract Map<String,IRoutingAttribute> parserRouting(Element elt);
}
