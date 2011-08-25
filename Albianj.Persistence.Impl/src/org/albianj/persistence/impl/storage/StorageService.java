package org.albianj.persistence.impl.storage;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.albianj.kernel.AlbianServiceRouter;
import org.albianj.logger.IAlbianLoggerService;
import org.albianj.persistence.impl.cached.DataSourceCached;
import org.albianj.persistence.impl.cached.StorageAttributeCache;
import org.albianj.persistence.object.DatabaseStyle;
import org.albianj.persistence.object.IStorageAttribute;
import org.albianj.persistence.object.impl.StorageAttribute;
import org.albianj.verify.Validate;
import org.albianj.xml.XmlParser;
import org.apache.commons.dbcp.BasicDataSource;
import org.dom4j.Element;

public class StorageService extends FreeStorageParser
{

	public final static String DEFAULT_STORAGE_NAME = "!@#$%Albianj_Default_Storage%$#@!";
	public final static String DRIVER_CLASSNAME = "com.mysql.jdbc.Driver";
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

	public void loading()
	{
		super.init();
	}
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
			DataSource ds = setupDataSource(storage);
			DataSourceCached.insert(storage.getName(),ds);
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

	public DataSource setupDataSource(IStorageAttribute storageAttribute)
	{
		BasicDataSource ds = new BasicDataSource();
		try
		{
			String url = FreeStorageParser.generateConnectionUrl(storageAttribute);
			ds.setDriverClassName(DRIVER_CLASSNAME);
			ds.setUrl(url);
			ds.setUsername(storageAttribute.getUser());
			ds.setPassword(storageAttribute.getPassword());
			if(storageAttribute.getTransactional())
			{
				ds.setDefaultAutoCommit(false);
				ds.setDefaultTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
			}
			ds.setDefaultReadOnly(false);
			if(storageAttribute.getPooling())
			{
				ds.setInitialSize(storageAttribute.getMinSize());
				ds.setMaxIdle(storageAttribute.getMaxSize());
				ds.setMinIdle(storageAttribute.getMinSize());
			}
			else
			{
				ds.setInitialSize(5);
				ds.setMaxIdle(10);
				ds.setMinIdle(5);
			}
			ds.setMaxWait(storageAttribute.getTimeout()  * 1000);
			ds.setRemoveAbandoned(true);
			ds.setRemoveAbandonedTimeout(storageAttribute.getTimeout());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}

		return ds;
	}
	
	public synchronized static Connection getConnection(String storageName)
	{
		DataSource ds =(DataSource) DataSourceCached.get(storageName);
		IAlbianLoggerService logger = AlbianServiceRouter.getService(
				IAlbianLoggerService.class, "logger");
		try
		{
			return ds.getConnection();
		}
		catch (SQLException e)
		{
			if (null != logger) logger.error(e,"Get the '",storageName,"' connection form connection pool is error.");
			return null;
		}
	}

}
