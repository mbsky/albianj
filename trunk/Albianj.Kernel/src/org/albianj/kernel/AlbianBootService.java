package org.albianj.kernel;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.albianj.cached.impl.SortCached;
import org.albianj.logger.IAlbianLoggerService;
import org.albianj.service.AlbianServiceException;
import org.albianj.service.IAlbianService;
import org.albianj.service.IAlbianServiceAttribute;
import org.albianj.service.parser.FreeServiceParser;
import org.albianj.service.parser.IServiceParser;
import org.albianj.service.parser.ServiceAttributeCached;
import org.albianj.service.parser.ServiceParser;

@Kernel
public final class AlbianBootService
{
//	private static Map<String,IAlbianService> services = new HashMap<String,IAlbianService>();
	private static AlbianState state = AlbianState.Normal;
	
//	static Map<String,IAlbianService> getAlbianServices()
//	{
//		return services;
//	}
	public static AlbianState getLifeState()
	{
		return state;
	}
	
	public static void start() throws Exception
	{
		Thread thread = new ServiceThread(); 
		thread.start();
	}
	
	static void doStart() throws Exception
	{
		state = AlbianState.Initing;
		IServiceParser parser = new ServiceParser();
		parser.init();
		LinkedHashMap<String,IAlbianServiceAttribute> map = (LinkedHashMap<String, IAlbianServiceAttribute>) ServiceAttributeCached.get(FreeServiceParser.ALBIANJSERVICEKEY);
		LinkedHashMap<String,IAlbianServiceAttribute> failMap = new LinkedHashMap<String,IAlbianServiceAttribute>();
		int lastFailSize = 0;
		
		while(true)
		{
			for(Map.Entry<String,IAlbianServiceAttribute> entry : map.entrySet())
			{
				try
				{
					IAlbianServiceAttribute serviceAttr = entry.getValue(); 
					Class<?> cla = Class.forName(serviceAttr.getType());
					IAlbianService service =(IAlbianService) cla.newInstance();
					service.beforeLoad();
					service.loading();
					service.afterLoading();
					ServiceCached.insert(entry.getKey(), service);
				}
				catch(Exception exc)
				{
					lastFailSize++;
					failMap.put(entry.getKey(), entry.getValue());
				}
			}
			if(0 == lastFailSize) 
			{
				state = AlbianState.Running;
				IAlbianLoggerService logger = AlbianServiceRouter.getService(IAlbianLoggerService.class, "logger");
				if(null != logger)
				{
					logger.info("start service is success!");
				}
				break;//all success
			}
			if(lastFailSize == failMap.size())//create instance but all fail in this times,so throw exception
			{
				state = AlbianState.Unloading;
				String msg = "the service maybe cross reference.";
				IAlbianLoggerService logger = AlbianServiceRouter.getService(IAlbianLoggerService.class, "logger");
				if(null != logger)
				{
					logger.error(msg);
					logger.error(failMap.keySet().toString());
				}
				ServiceCached.clear();
				state = AlbianState.Unloaded;
				throw new AlbianServiceException(msg);
			}
			else
			{
				map.clear();
				map.putAll(failMap);
				failMap.clear();
				lastFailSize = 0;	
			}			
		}
	}
	
	public static String requestHandlerContext()
	{
		if(AlbianState.Running != state)
		{
			return "Albian is not ready,Please wait a minute or contact administrators!";
		}
		return "";
	}
}
