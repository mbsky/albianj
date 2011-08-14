package org.albianj.kernel;

import org.albianj.logger.IAlbianLoggerService;

public class ServiceThread extends Thread
{
	@Override
	public void run()
	{
		try
		{
			AlbianBootService.doStart();
		}
		catch (Exception e)
		{
			IAlbianLoggerService logger = AlbianServiceRouter.getService(IAlbianLoggerService.class, "logger");
			if(null != logger)
			{
				logger.error(String.format("start service is fail.Message:%1$s", e.getMessage()));
			}
		}
	}
}
