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

	public final static String DEFAULT_STORAGE_KEY = "!@#$%Albianj_Default_Storage%$#@!";
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
				StorageAttributeCache.insert(DEFAULT_STORAGE_KEY, storage);				
			}
		}
	}

	@Override
	protected IStorageAttribute parserStorage(Element node)
			throws StorageAttributeException
	{
		IAlbianLoggerService logger = AlbianServiceRouter.getService(
				IAlbianLoggerService.class, "logger");
		String name = getValue(node, "Name");
		if (null == name)
		{
			String msg = "There is no name attribute in the storage node.";
			if (null != logger)
			{
				logger.error(msg);
			}
			throw new StorageAttributeException(msg);
		}
		String databaseStyle = getValue(node, "DatabaseStyle");
		String server = getValue(node, "Server");
		if (null == server)
		{
			String msg = "There is no server attribute in the storage node.";
			if (null != logger)
			{
				logger.error(msg);
			}
			throw new StorageAttributeException(msg);
		}
		String database = getValue(node, "Database");
		if (null == database)
		{
			String msg = "There is no database attribute in the storage node.";
			if (null != logger)
			{
				logger.error(msg);
			}
			throw new StorageAttributeException(msg);
		}
		String user = getValue(node, "User");
		if (null == user)
		{
			String msg = "There is no uid attribute in the storage node.";
			if (null != logger)
			{
				logger.error(msg);
			}
			throw new StorageAttributeException(msg);
		}
		String password = getValue(node, "Password");
		String pooling = getValue(node, "Pooling");
		String minPoolSize = getValue(node, "MinPoolSize");
		String maxPoolSize = getValue(node, "MaxPoolSize");
		String timeout = getValue(node, "Timeout");
		String charset = getValue(node, "Charset");
		String transactional = getValue(node, "Transactional");

		IStorageAttribute storage = new StorageAttribute();
		storage.setName(name);
		storage.setDatabaseStyle(null == databaseStyle ? DatabaseStyle.MySql
				: int.class.cast(databaseStyle));
		storage.setServer(server);
		storage.setDatabase(database);
		storage.setUser(user);
		storage.setPassword(null == password ? "" : password);
		storage.setPooling(null == pooling ? true : boolean.class.cast(pooling));
		storage.setMinSize(null == minPoolSize ? 5 : int.class
				.cast(minPoolSize));
		storage.setMaxSize(null == maxPoolSize ? 20 : int.class
				.cast(maxPoolSize));
		storage.setTimeout(null == timeout ? 30 : int.class.cast(timeout));
		storage.setCharset(null == charset ? null : charset);
		storage.setTransactional(null == transactional ? true : boolean.class
				.cast(transactional));

		return storage;
	}

	private static String getValue(Element node, String tagName)
	{
		Node chird = node.selectSingleNode(tagName);
		if (null == chird) return null;
		return chird.getStringValue();
	}

}
