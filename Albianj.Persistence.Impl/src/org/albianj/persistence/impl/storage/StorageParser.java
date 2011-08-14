package org.albianj.persistence.impl.storage;

import java.util.List;

import org.albianj.kernel.AlbianServiceRouter;
import org.albianj.logger.IAlbianLoggerService;
import org.albianj.persistence.impl.cached.StorageAttributeCache;
import org.albianj.persistence.object.DatabaseStyle;
import org.albianj.persistence.object.IStorageAttribute;
import org.albianj.persistence.object.impl.StorageAttribute;
import org.albianj.verify.Validate;
import org.albianj.xml.XmlParser;
import org.dom4j.Element;

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
	protected void parserStorages(@SuppressWarnings("rawtypes") List nodes) throws StorageAttributeException
	{
		// TODO Auto-generated method stub
		if (Validate.isNullOrEmpty(nodes))
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
			if(null == storage)
			{
				throw new StorageAttributeException("parser storage.xml is error.");
			}
			StorageAttributeCache.insert(storage.getName(), storage);
			if(i == 0)
			{
				StorageAttributeCache.insert(DEFAULT_STORAGE_NAME, storage);				
			}
		}
	}

	@Override
	protected IStorageAttribute parserStorage(Element node)
	{
		IAlbianLoggerService logger = AlbianServiceRouter.getService(
				IAlbianLoggerService.class, "logger");
		String name = XmlParser.getSingleChildNodeValue(node, "Name");
		if (null == name)
		{
			if (null != logger)
			{
				logger.error("There is no name attribute in the storage node.");
			}
			return null;
		}
		String databaseStyle = XmlParser.getSingleChildNodeValue(node, "DatabaseStyle");
		String server = XmlParser.getSingleChildNodeValue(node, "Server");
		if (null == server)
		{
			if (null != logger)
			{
				logger.error("There is no server attribute in the storage node.");
			}
			return null;
		}
		String database = XmlParser.getSingleChildNodeValue(node, "Database");
		if (null == database)
		{
			if (null != logger)
			{
				logger.error("There is no database attribute in the storage node.");
			}
			return null;
		}
		String user = XmlParser.getSingleChildNodeValue(node, "User");
		if (null == user)
		{
			if (null != logger)
			{
				logger.error( "There is no uid attribute in the storage node.");
			}
			return null;
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
		storage.setPassword(Validate.isNullOrEmptyOrAllSpace(password) ? "" : password);
		storage.setPooling(Validate.isNullOrEmptyOrAllSpace(pooling) ? true :new Boolean(pooling));
		storage.setMinSize(Validate.isNullOrEmptyOrAllSpace(minPoolSize) ? 5 : new Integer(minPoolSize));
		storage.setMaxSize(Validate.isNullOrEmptyOrAllSpace(maxPoolSize) ? 20 : new Integer(maxPoolSize));
		storage.setTimeout(Validate.isNullOrEmptyOrAllSpace(timeout) ? 30 : new Integer(timeout));
		storage.setCharset(Validate.isNullOrEmptyOrAllSpace(charset) ? null : charset);
		storage.setTransactional(Validate.isNullOrEmptyOrAllSpace(transactional) ? true : new Boolean(transactional));

		return storage;
	}



}
