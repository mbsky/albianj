package org.albianj.config.service;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.albianj.config.entity.ConfigNotifyAttribute;
import org.albianj.config.entity.ConfigServerAttribute;
import org.albianj.config.entity.ConfigServiceConstants;
import org.albianj.config.entity.ConfigStorageAttribute;
import org.albianj.config.entity.Deployment;
import org.albianj.config.entity.IConfigAttribute;
import org.albianj.config.entity.IConfigNotifyAttribute;
import org.albianj.config.entity.IConfigServerAttribute;
import org.albianj.config.entity.IConfigStorageAttribute;
import org.albianj.logger.AlbianLoggerService;
import org.albianj.service.AlbianServiceException;
import org.albianj.verify.Validate;
import org.albianj.xml.XmlParser;
import org.dom4j.Element;

import com.mongodb.DBAddress;
import com.mongodb.Mongo;
import com.mongodb.ServerAddress;


public class AlbianConfigStartupService extends FreeAlbianConfigStartupService
{
	private static Mongo mongo;
	@Override
	public void loading() throws AlbianServiceException 
	{
		init();
	}
	
	protected IConfigStorageAttribute parserConfigStorage(Element elt)
	{
		IConfigStorageAttribute storageAttribute = new ConfigStorageAttribute();
		Deployment deployment;
		String sDeployment = XmlParser.getAttributeValue(elt, "Deployment");
		if(Validate.isNullOrEmptyOrAllSpace(sDeployment))
		{
			deployment = Deployment.Normal;
		}
		else
		{
			deployment= Deployment.valueOf(sDeployment);
		}
		storageAttribute.setDeployment(deployment);
		@SuppressWarnings("rawtypes")
		List nodes = elt.selectNodes("Server");
		if(!Validate.isNullOrEmpty(nodes))
		{
			List<IConfigServerAttribute> servers = parserConfigServers(nodes);
			storageAttribute.setStorageServers(servers);
		}
		return storageAttribute;
	}
	protected IConfigNotifyAttribute parserConfigNotify(Element elt)
	{
		IConfigNotifyAttribute notifyAttribute = new ConfigNotifyAttribute();
		String sHash = XmlParser.getAttributeValue(elt, "Hash");
		String sPersistence = XmlParser.getAttributeValue(elt, "Persistence");
		if(Validate.isNullOrEmptyOrAllSpace(sHash))
		{
			notifyAttribute.setHash(false);
		}
		else
		{
			notifyAttribute.setHash(new Boolean(sHash));
		}
		if(Validate.isNullOrEmptyOrAllSpace(sPersistence))
		{
			notifyAttribute.serPersistence(false);
		}
		else
		{
			notifyAttribute.serPersistence(new Boolean(sPersistence));
		}
		
		@SuppressWarnings("rawtypes")
		List nodes = elt.selectNodes("Server");
		if(!Validate.isNullOrEmpty(nodes))
		{
			List<IConfigServerAttribute> servers = parserConfigServers(nodes);
			notifyAttribute.setNotifyServers(servers);
		}
		return notifyAttribute;
	}
	protected List<IConfigServerAttribute> parserConfigServers(@SuppressWarnings("rawtypes") List elts)
	{
		if(Validate.isNullOrEmpty(elts)) return null;
		List<IConfigServerAttribute> servers = new Vector<IConfigServerAttribute>();
		for(Object elt : elts)
		{
			IConfigServerAttribute server = parserConfigServer((Element) elt);
			if(null != server)
			{
				servers.add(server);
			}
		}
		return servers;
	}
	
	protected IConfigServerAttribute parserConfigServer(Element elt)
	{
		if(null == elt) return null;
		String address =XmlParser.getAttributeValue(elt, "Address");
		IConfigServerAttribute server = new ConfigServerAttribute();
		if(Validate.isNullOrEmpty(address))
		{
			AlbianLoggerService.warn("the address config-item in config.xml is null or empty. ");
			return null;
		}
		else
		{
			server.setAddress(address);
		}
		String port = XmlParser.getAttributeValue(elt, "Port");
		if(Validate.isNullOrEmpty(port))
		{
			AlbianLoggerService.warn("the port config-item in config.xml is null or empty. ");
			return null;
		}
		else
		{
			server.setPort(new Integer(port));
		}
		String userName = XmlParser.getAttributeValue(elt, "UserName");
		if(!Validate.isNullOrEmpty(userName))
		{
			server.setUserName(userName);
		}
		String password = XmlParser.getAttributeValue(elt, "Password");
		if(!Validate.isNullOrEmpty(password))
		{
			server.setPassword(password);
		}
		String enbale = XmlParser.getAttributeValue(elt, "Enable");
		if(!Validate.isNullOrEmpty(enbale))
		{
			server.setEnable(new Boolean(enbale));
		}
		return server;
	}
	
	@SuppressWarnings("unchecked")
	protected void createMongo()
	{
		IConfigAttribute config = getConfigAttribute();
		IConfigStorageAttribute storageAttribute = config.getConfigStorage();
		if(null == storageAttribute)
		{
			return;
		}
		
		if(Deployment.Normal == storageAttribute.getDeployment())
		{
			List<IConfigServerAttribute> storages = storageAttribute.getStorageServers();
			if(Validate.isNullOrEmpty(storages))
			{
				
			}
			IConfigServerAttribute storage = storages.get(0);//default is first element
			try
			{
				mongo = new Mongo(new DBAddress(storage.getAddress(),storage.getPort(),ConfigServiceConstants.DATABASE));
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		else if(Deployment.Paired == storageAttribute.getDeployment())
		{
			try
			{
				List<IConfigServerAttribute> storages = storageAttribute.getStorageServers();
				if(Validate.isNullOrEmpty(storages))
				{
					
				}
				IConfigServerAttribute leftStorage = storages.get(0);
				IConfigServerAttribute rightStorage = storages.get(1);
				
				 DBAddress left = new DBAddress(leftStorage.getAddress(),leftStorage.getPort(),ConfigServiceConstants.DATABASE);
				 DBAddress right = new DBAddress(rightStorage.getAddress(),rightStorage.getPort(),ConfigServiceConstants.DATABASE);

				 mongo = new Mongo(left, right);
			}
			catch (UnknownHostException e)
			{
				e.printStackTrace();
			}
		}
		else if(Deployment.Set == storageAttribute.getDeployment())
		{
			try
			{
			List<IConfigServerAttribute> storages = storageAttribute.getStorageServers();
			if(Validate.isNullOrEmpty(storages))
			{
				
			}
			 @SuppressWarnings("rawtypes")
			List addrs = new ArrayList();
			 for(IConfigServerAttribute storage : storages)
			 {
				 addrs.add( new ServerAddress( storage.getAddress() ,storage.getPort()));
			 }
		 	mongo = new Mongo(addrs);
		 	mongo.slaveOk();
			}
			catch(Exception e)
			{
				
			}
		}		
	}
}
