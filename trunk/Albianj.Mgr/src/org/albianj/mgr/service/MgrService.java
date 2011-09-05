package org.albianj.mgr.service;

import java.util.Properties;

import org.albianj.io.Path;
import org.albianj.logger.AlbianLoggerService;
import org.albianj.mgr.config.MgrSettings;
import org.albianj.service.FreeAlbianService;
import org.albianj.service.parser.PropertiesParser;
import org.albianj.verify.Validate;


public class MgrService extends FreeAlbianService
{
	private final static String path = "../config/mgr.properties";
	public void init()
	{
		try
		{
			Properties props = PropertiesParser.load(Path.getExtendResourcePath(path));
			parser(props);
		}
		catch (Exception e)
		{
			AlbianLoggerService.error(e,"load the mgr properties file is error.");
			throw new RuntimeException(e);
		}
	}
	
	public void parser(Properties props)
	{
		String host = PropertiesParser.getValue(props, "host");
		String port = PropertiesParser.getValue(props, "port");
		String backlog = PropertiesParser.getValue(props, "backlog");
		String recvBuffSize = PropertiesParser.getValue(props, "receiveBufferSize");
		String reuseAddress = PropertiesParser.getValue(props, "reuseAddress");
		String timeout = PropertiesParser.getValue(props, "timeout");
		if(!Validate.isNullOrEmptyOrAllSpace(host))
		{
			MgrSettings.setHost(host);
		}
		if(!Validate.isNullOrEmptyOrAllSpace(port))
		{
			MgrSettings.setPort(new Integer(port));
		}
		if(!Validate.isNullOrEmptyOrAllSpace(backlog))
		{
			MgrSettings.setBacklog(new Integer(backlog));
		}
		if(!Validate.isNullOrEmptyOrAllSpace(recvBuffSize))
		{
			MgrSettings.setReceiveBufferSize(new Integer(recvBuffSize));
		}
		if(!Validate.isNullOrEmptyOrAllSpace(reuseAddress))
		{
			MgrSettings.setReuseAddress(new Boolean(reuseAddress));
		}
		if(!Validate.isNullOrEmptyOrAllSpace(timeout))
		{
			MgrSettings.setTimeout(new Integer(timeout));
		}
	}


	@Override
	public void loading() throws RuntimeException
	{
		init();
		super.loading();		
	}

}
