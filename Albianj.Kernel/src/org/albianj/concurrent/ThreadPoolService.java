package org.albianj.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.albianj.logger.AlbianLoggerService;
import org.albianj.service.FreeAlbianService;

public class ThreadPoolService extends FreeAlbianService implements IThreadPoolService
{
	private ThreadPoolExecutor threadPool ;
	
	public void loading() throws org.albianj.service.AlbianServiceException
	{
		threadPool = new ThreadPoolExecutor(5, 15, 300,
				TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3),
				new ThreadPoolExecutor.DiscardOldestPolicy());
		super.loading();
	}
	
	public void execute(Runnable event)
	{
		if(null == threadPool)
		{
			AlbianLoggerService.error("The thread pool is null.");
			return;
		}
		threadPool.execute(event);
	}
	
	public void unload()
	{
		if(null != threadPool) threadPool.shutdown();
		super.unload();
	}
}
