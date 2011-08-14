package org.albianj.persistence.impl.storage;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.albianj.cached.impl.SortCached;
import org.albianj.io.Path;
import org.albianj.kernel.AlbianServiceRouter;
import org.albianj.logger.IAlbianLoggerService;
import org.albianj.persistence.object.DatabaseStyle;
import org.albianj.persistence.object.IStorageAttribute;
import org.albianj.service.IAlbianServiceAttribute;
import org.albianj.xml.IParser;
import org.albianj.xml.XmlParser;
import org.dom4j.Document;
import org.dom4j.Element;

public abstract class FreeStorageParser implements IParser
{
	private final static String path = "../config/storage.xml";
	private final static String tagName = "Storages/Storage";


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
		parserStorages(nodes);
		return;
	}
	
	protected abstract void parserStorages(List nodes) throws StorageAttributeException;
	
	protected abstract IStorageAttribute parserStorage(Element node) throws StorageAttributeException;
	

	public static String GenerateConnectionUrl(IStorageAttribute storageAttribute)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("jdbc:");
		//String url = "jdbc:mysql://localhost/baseinfo?useUnicode=true&characterEncoding=8859_1";
		switch(storageAttribute.getDatabaseStyle())
		{
			case (DatabaseStyle.MySql):
			{
				sb.append("mysql://")
				.append(storageAttribute.getServer()).append("/")
				.append(storageAttribute.getDatabase());
				if(null != storageAttribute.getCharset())
				{
					sb.append("?useUnicode=true&characterEncoding=").append(storageAttribute.getCharset());
				}
			}
			case (DatabaseStyle.Oracle) :
			{
				sb.append("oracle:thin:@")
				.append(storageAttribute.getServer()).append(":")
				.append(storageAttribute.getDatabase());
			}
			case(DatabaseStyle.SqlServer) : 
			{
				sb.append("microsoft:sqlserver://")
				.append(storageAttribute.getServer()).append(";")
				.append(storageAttribute.getDatabase());
			}
			default:
			{
				sb.append("mysql://")
				.append(storageAttribute.getServer()).append("/")
				.append(storageAttribute.getDatabase());
				if(null != storageAttribute.getCharset())
				{
					sb.append("?useUnicode=true&characterEncoding=").append(storageAttribute.getCharset());
				}
			}
		}
		return sb.toString();
	}
}
