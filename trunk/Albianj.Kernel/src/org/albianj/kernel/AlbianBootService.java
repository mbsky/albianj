package org.albianj.kernel;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.albianj.logger.AlbianLoggerService;
import org.albianj.service.AlbianIdService;
import org.albianj.service.AlbianServiceException;
import org.albianj.service.IAlbianService;
import org.albianj.service.IAlbianServiceAttribute;
import org.albianj.service.parser.FreeServiceParser;
import org.albianj.service.parser.IServiceParser;
import org.albianj.service.parser.ServiceAttributeMap;
import org.albianj.service.parser.ServiceParser;

@Kernel
public final class AlbianBootService
{
	private static AlbianState state = AlbianState.Normal;
	private static Date startDateTime;
	private static String serialId;
	
	public static Date getStartDateTime()
	{
		return startDateTime;
	}
	public static String getSerialId()
	{
		return serialId;
	}
	
	
	
	public static AlbianState getLifeState()
	{
		return state;
	}
	
	public static void start() throws Exception
	{
		startDateTime = new Date();
		serialId = AlbianIdService.generate32UUID();
		
		Thread thread = new ServiceThread(); 
		thread.start();
	}
	
	static void doStart() throws AlbianServiceException
	{
		state = AlbianState.Initing;
		IServiceParser parser = new ServiceParser();
		parser.init();
		@SuppressWarnings("unchecked")
		LinkedHashMap<String,IAlbianServiceAttribute> map = (LinkedHashMap<String, IAlbianServiceAttribute>) ServiceAttributeMap.get(FreeServiceParser.ALBIANJSERVICEKEY);
		LinkedHashMap<String,IAlbianServiceAttribute> failMap = new LinkedHashMap<String,IAlbianServiceAttribute>();
		int lastFailSize = 0;
		int currentFailSize = 0;
		
		while(true)
		{
			lastFailSize = currentFailSize;
			currentFailSize = 0;
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
					ServiceMap.insert(entry.getKey(), service);
				}
				catch(Exception exc)
				{
					currentFailSize++;
					failMap.put(entry.getKey(), entry.getValue());
				}
			}
			if(0 == currentFailSize) 
			{
				state = AlbianState.Running;
					AlbianLoggerService.info("start service is success!");
				break;//all success
			}
			
			if(lastFailSize == currentFailSize)//create instance but all fail in this times,so throw exception
			{
				state = AlbianState.Unloading;
				String msg = "the service maybe cross reference.";
					AlbianLoggerService.error(msg);
					AlbianLoggerService.error(failMap.keySet().toString());
				ServiceMap.clear();
				state = AlbianState.Unloaded;
				throw new AlbianServiceException(msg);
			}
			else
			{
				map.clear();
				map.putAll(failMap);
				failMap.clear();	
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
	
	public static void unload() throws Exception
	{
		Set<String> keys = ServiceMap.getKeys();
		for(String key : keys)
		{
			try
			{
				IAlbianService service = (IAlbianService) ServiceMap.get(key);
				service.beforeUnload();
				service.unload();
				service.afterUnload();
			}
			catch(Exception e)
			{
				AlbianLoggerService.error(e, "unload the service is error.");
				e.printStackTrace();
			}
		}
	}

}
