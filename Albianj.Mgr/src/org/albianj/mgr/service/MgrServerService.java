package org.albianj.mgr.service;

import java.util.Properties;

import org.albianj.io.Path;
import org.albianj.logger.AlbianLoggerService;
import org.albianj.mgr.config.MgrServerSettings;
import org.albianj.service.FreeAlbianService;
import org.albianj.service.parser.PropertiesParser;
import org.albianj.verify.Validate;


public class MgrServerService extends FreeAlbianService
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
		String report_timespan = PropertiesParser.getValue(props, "report_timespan");
		if(!Validate.isNullOrEmptyOrAllSpace(host))
		{
			MgrServerSettings.setHost(host);
		}
		if(!Validate.isNullOrEmptyOrAllSpace(port))
		{
			MgrServerSettings.setPort(new Integer(port));
		}
		if(!Validate.isNullOrEmptyOrAllSpace(backlog))
		{
			MgrServerSettings.setBacklog(new Integer(backlog));
		}
		if(!Validate.isNullOrEmptyOrAllSpace(recvBuffSize))
		{
			MgrServerSettings.setReceiveBufferSize(new Integer(recvBuffSize));
		}
		if(!Validate.isNullOrEmptyOrAllSpace(reuseAddress))
		{
			MgrServerSettings.setReuseAddress(new Boolean(reuseAddress));
		}
		if(!Validate.isNullOrEmptyOrAllSpace(timeout))
		{
			MgrServerSettings.setTimeout(new Integer(timeout));
		}
		if(!Validate.isNullOrEmptyOrAllSpace(report_timespan))
		{
			MgrServerSettings.setReport_timespan(new Integer(report_timespan));
		}
		
	}

	@Override
	public void loading() throws RuntimeException
	{
		init();
		super.loading();		
	}

}
