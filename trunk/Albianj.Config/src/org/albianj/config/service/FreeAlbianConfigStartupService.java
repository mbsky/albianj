package org.albianj.config.service;

import java.util.List;

import org.albianj.config.entity.ConfigAttribute;
import org.albianj.config.entity.IConfigAttribute;
import org.albianj.config.entity.IConfigNotifyAttribute;
import org.albianj.config.entity.IConfigServerAttribute;
import org.albianj.config.entity.IConfigStorageAttribute;
import org.albianj.config.entity.LoadMode;
import org.albianj.config.entity.Role;
import org.albianj.io.Path;
import org.albianj.logger.AlbianLoggerService;
import org.albianj.service.FreeAlbianService;
import org.albianj.service.parser.IParser;
import org.albianj.verify.Validate;
import org.albianj.xml.XmlParser;
import org.dom4j.Document;
import org.dom4j.Element;

public abstract class FreeAlbianConfigStartupService extends FreeAlbianService implements IParser
{
	private final static String path = "../config/config.xml";
	private final static String tagNameForStorage = "Config/Storage";
	private final static String tagNameForNotify = "Config/Notify";
	private static IConfigAttribute config = null;
	
	public static IConfigAttribute getConfigAttribute()
	{
		return config;
	}
	
	@Override
	public void init()
	{
		Document doc = null;
		try
		{
			doc = XmlParser.load(Path.getExtendResourcePath(path));
		}
		catch(Exception e)
		{
			AlbianLoggerService.error(e, "There is error when parser the storage config file.");
		}
		if (null == doc) 
		{ 
			throw new RuntimeException("load persistence is error."); 
		}
		config = new ConfigAttribute();
		Element roleElement = XmlParser.analyzeSingle(doc, "Role");
		if(null == roleElement)
		{
			config.setRole(Role.Client);
		}
		else
		{
			String sRole = XmlParser.getNodeValue(roleElement);
			if(Validate.isNullOrEmptyOrAllSpace(sRole))
			{
				config.setRole(Role.Client);
			}
			Role role = Role.valueOf(sRole);
			config.setRole(role);
		}
		
		Element loadModeElement = XmlParser.analyzeSingle(doc, "LoadMode");
		if(null == loadModeElement)
		{
			config.setLoadMode(LoadMode.Lazyload);
		}
		else
		{
			String sLoadMode = XmlParser.getNodeValue(loadModeElement);
			if(Validate.isNullOrEmptyOrAllSpace(sLoadMode))
			{
				config.setLoadMode(LoadMode.Lazyload);
			}
			LoadMode loadMode = LoadMode.valueOf(sLoadMode);
			config.setLoadMode(loadMode);
		}
		
		Element storageElement = XmlParser.analyzeSingle(doc, tagNameForStorage);
		if (null == storageElement)
		{
			String msg = String.format("There is not %1$s nodes.", tagNameForStorage);
			AlbianLoggerService.error(msg);
			throw new UnsupportedOperationException(msg);
		}
		IConfigStorageAttribute storage = parserConfigStorage(storageElement);
		config.setConfigStorage(storage);
		
		Element notifyElement = XmlParser.analyzeSingle(doc, tagNameForNotify);
		if (null == notifyElement)
		{
			String msg = String.format("There is not %1$s nodes.", tagNameForNotify);
			AlbianLoggerService.error(msg);
			throw new UnsupportedOperationException(msg);
		}
		IConfigNotifyAttribute notify = parserConfigNotify(notifyElement);
		config.setConfigNotify(notify);
		return;
		
	}
	
	protected abstract IConfigStorageAttribute parserConfigStorage(Element elt);
	protected abstract IConfigNotifyAttribute parserConfigNotify(Element elt);
	protected abstract List<IConfigServerAttribute> parserConfigServers(@SuppressWarnings("rawtypes") List elts);
}
