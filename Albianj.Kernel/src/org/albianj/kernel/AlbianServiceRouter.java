package org.albianj.kernel;

import org.albianj.logger.IAlbianLoggerService;
import org.albianj.service.IAlbianService;

public class AlbianServiceRouter
{
	
	public static <T extends IAlbianService> T getService(Class<T> cla,String id,boolean isThrowIfException) throws IllegalArgumentException
	{
		if(null == id || id.isEmpty())
			throw new IllegalArgumentException("id");
		try
		{
			IAlbianService service =(IAlbianService) ServiceCached.get(id);		
			if(null == service) return null;
			return cla.cast(service);
		}
		catch(IllegalArgumentException exc)
		{
			IAlbianLoggerService logger = AlbianServiceRouter.getService(IAlbianLoggerService.class, "logger");
			if(null != logger)
				logger.error("Get service is error.Message:", exc.getMessage());
			if(isThrowIfException)
				throw exc;
		}
		return null;
	}
	
	public static <T extends IAlbianService> T getService(Class<T> cla,String id)
	{
		try
		{
			return getService(cla, id,false);
		}
		catch(Exception exc)
		{
		}
		return null;
	}
}
