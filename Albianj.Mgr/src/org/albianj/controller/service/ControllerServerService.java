package org.albianj.controller.service;

import java.util.Properties;

import org.albianj.controller.config.ControllerServerSettings;
import org.albianj.io.Path;
import org.albianj.logger.AlbianLoggerService;
import org.albianj.service.FreeAlbianService;
import org.albianj.service.parser.PropertiesParser;
import org.albianj.verify.Validate;


public class ControllerServerService extends FreeAlbianService
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
			ControllerServerSettings.setHost(host);
		}
		if(!Validate.isNullOrEmptyOrAllSpace(port))
		{
			ControllerServerSettings.setPort(new Integer(port));
		}
		if(!Validate.isNullOrEmptyOrAllSpace(backlog))
		{
			ControllerServerSettings.setBacklog(new Integer(backlog));
		}
		if(!Validate.isNullOrEmptyOrAllSpace(recvBuffSize))
		{
			ControllerServerSettings.setReceiveBufferSize(new Integer(recvBuffSize));
		}
		if(!Validate.isNullOrEmptyOrAllSpace(reuseAddress))
		{
			ControllerServerSettings.setReuseAddress(new Boolean(reuseAddress));
		}
		if(!Validate.isNullOrEmptyOrAllSpace(timeout))
		{
			ControllerServerSettings.setTimeout(new Integer(timeout));
		}
		if(!Validate.isNullOrEmptyOrAllSpace(report_timespan))
		{
			ControllerServerSettings.setReport_timespan(new Integer(report_timespan));
		}
		
	}

	@Override
	public void loading() throws RuntimeException
	{
		init();
		super.loading();		
	}

}
