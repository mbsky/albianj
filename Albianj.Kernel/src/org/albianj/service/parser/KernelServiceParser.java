package org.albianj.service.parser;

import java.util.Properties;

import org.albianj.io.Path;
import org.albianj.kernel.AlbianServiceRouter;
import org.albianj.kernel.KernelSetting;
import org.albianj.logger.IAlbianLoggerService;
import org.albianj.service.FreeAlbianService;

public class KernelServiceParser extends FreeAlbianService implements IServiceParser
{
	private IAlbianLoggerService logger;// = AlbianServiceRouter.getService(IAlbianLoggerService.class, "logger");
	private final static String path = "../config/kernel.properties";
	public void init()
	{
		try
		{
			Properties props = PropertiesParser.load(Path.getExtendResourcePath(path));
			parser(props);
		}
		catch (Exception e)
		{
			if(null != logger)
				logger.error(e,"load the kernel properties file is error.");
			throw new RuntimeException(e);
		}
	}
	
	public void parser(Properties props)
	{
		KernelSetting.setKernelId(PropertiesParser.getValue(props, "Id"));
		KernelSetting.setKernelKey(PropertiesParser.getValue(props, "Key"));
	}


	public void beforeLoad()
	{
		 logger = AlbianServiceRouter.getService(IAlbianLoggerService.class, "logger");
	}
	@Override
	public void loading() throws RuntimeException
	{
		init();
		super.loading();		
	}

}
