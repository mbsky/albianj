package org.albianj.persistence.impl.storage;

import java.util.List;
import java.util.Map;

import org.albianj.kernel.AlbianServiceRouter;
import org.albianj.logger.IAlbianLoggerService;
import org.albianj.persistence.impl.cached.StorageAttributeCache;
import org.albianj.persistence.object.DatabaseStyle;
import org.albianj.persistence.object.IStorageAttribute;
import org.albianj.persistence.object.impl.StorageAttribute;
import org.albianj.xml.XmlParser;
import org.dom4j.Element;
import org.dom4j.Node;

public class StorageParser extends FreeStorageParser
{

	public final static String DEFAULT_STORAGE_NAME = "!@#$%Albianj_Default_Storage%$#@!";
	// <Storage>
	// <Name>1thStorage</Name>
	// <DatabaseStyle>MySql</DatabaseStyle>
	// <Server>localhost</Server>
	// <Database>BaseInfo</Database>
	// <Uid>root</Uid>
	// <Password>xuhf</Password>
	// <Pooling>false</Pooling>
	// <MinPoolSize>10</MinPoolSize>
	// <MaxPoolSize>20</MaxPoolSize>
	// <Timeout>60</Timeout>
	// <Charset>gb2312</Charset>
	// <Transactional>true</Transactional>
	// </Storage>

	@Override
	protected void parserStorages(List nodes) throws StorageAttributeException
	{
		// TODO Auto-generated method stub
		if (null == nodes || 0 == nodes.size())
		{
			IAlbianLoggerService logger = AlbianServiceRouter.getService(
					IAlbianLoggerService.class, "logger");
			if (null != logger) logger
					.error("Storage node is null or size is 0.");
			return;
		}
		for (int i = 0; i<nodes.size(); i++)
		{
			IStorageAttribute storage = parserStorage((Element) nodes.get(i));
			StorageAttributeCache.insert(storage.getName(), storage);
			if(i == 0)
			{
				StorageAttributeCache.insert(DEFAULT_STORAGE_NAME, storage);				
			}
		}
	}

	@Override
	protected IStorageAttribute parserStorage(Element node)
			throws StorageAttributeException
	{
		IAlbianLoggerService logger = AlbianServiceRouter.getService(
				IAlbianLoggerService.class, "logger");
		String name = XmlParser.getSingleChildNodeValue(node, "Name");
		if (null == name)
		{
			String msg = "There is no name attribute in the storage node.";
			if (null != logger)
			{
				logger.error(msg);
			}
			throw new StorageAttributeException(msg);
		}
		String databaseStyle = XmlParser.getSingleChildNodeValue(node, "DatabaseStyle");
		String server = XmlParser.getSingleChildNodeValue(node, "Server");
		if (null == server)
		{
			String msg = "There is no server attribute in the storage node.";
			if (null != logger)
			{
				logger.error(msg);
			}
			throw new StorageAttributeException(msg);
		}
		String database = XmlParser.getSingleChildNodeValue(node, "Database");
		if (null == database)
		{
			String msg = "There is no database attribute in the storage node.";
			if (null != logger)
			{
				logger.error(msg);
			}
			throw new StorageAttributeException(msg);
		}
		String user = XmlParser.getSingleChildNodeValue(node, "User");
		if (null == user)
		{
			String msg = "There is no uid attribute in the storage node.";
			if (null != logger)
			{
				logger.error(msg);
			}
			throw new StorageAttributeException(msg);
		}
		String password = XmlParser.getSingleChildNodeValue(node, "Password");
		String pooling = XmlParser.getSingleChildNodeValue(node, "Pooling");
		String minPoolSize = XmlParser.getSingleChildNodeValue(node, "MinPoolSize");
		String maxPoolSize = XmlParser.getSingleChildNodeValue(node, "MaxPoolSize");
		String timeout = XmlParser.getSingleChildNodeValue(node, "Timeout");
		String charset = XmlParser.getSingleChildNodeValue(node, "Charset");
		String transactional = XmlParser.getSingleChildNodeValue(node, "Transactional");

		IStorageAttribute storage = new StorageAttribute();
		storage.setName(name);
		if(null == databaseStyle)
		{
			storage.setDatabaseStyle(DatabaseStyle.MySql);
		}
		else
		{
			String style = databaseStyle.trim().toLowerCase();
			storage.setDatabaseStyle("sqlserver".equalsIgnoreCase(style) ? DatabaseStyle.SqlServer : 
				"oracle".equalsIgnoreCase(style) ? DatabaseStyle.Oracle : DatabaseStyle.MySql);
		}
		storage.setServer(server);
		storage.setDatabase(database);
		storage.setUser(user);
		storage.setPassword(null == password ? "" : password);
		storage.setPooling(null == pooling ? true :new Boolean(pooling));
		storage.setMinSize(null == minPoolSize ? 5 : new Integer(minPoolSize));
		storage.setMaxSize(null == maxPoolSize ? 20 : new Integer(maxPoolSize));
		storage.setTimeout(null == timeout ? 30 : new Integer(timeout));
		storage.setCharset(null == charset ? null : charset);
		storage.setTransactional(null == transactional ? true : new Boolean(transactional));

		return storage;
	}



}
