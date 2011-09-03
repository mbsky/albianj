package org.albianj.persistence.impl.dbcached;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.albianj.cached.service.ICacheService;
import org.albianj.concurrent.IThreadPoolService;
import org.albianj.expiredcached.impl.ILocalCached;
import org.albianj.kernel.AlbianServiceRouter;
import org.albianj.logger.AlbianLoggerService;
import org.albianj.persistence.object.IFilterCondition;
import org.albianj.persistence.object.IOrderByCondition;

public class CacheOperator
{
	public static String buildKey(Class<?> cls,int start,int step,IFilterCondition[] wheres,IOrderByCondition[] orderbys)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(cls.getName()).append("_")
		.append(start).append("_").append(step).append("_");
		if(null != wheres)
		{
			for(IFilterCondition where : wheres)
			{
				sb.append(where.getRelationalOperator()).append("_")
				.append(where.getFieldName()).append("_")
				.append(where.getLogicalOperation()).append("_")
				.append(where.getValue());
			}
		}
		if(null != orderbys)
		{
			for(IOrderByCondition orderby : orderbys)
			{
				sb.append(orderby.getFieldName()).append("_")
				.append(orderby.getSortStyle()).append("_");
			}
		}
		return sb.toString();
	}
	
	public static Object deepClone(Object obj)
	{
		Object newObj = null;
		ByteArrayOutputStream byteOut = null;
		ObjectOutputStream out = null;
		ByteArrayInputStream byteIn = null;
		ObjectInputStream in = null;
		try
		{
			byteOut = new ByteArrayOutputStream();
			out = new ObjectOutputStream(byteOut);
			if(null == obj) return null;
			out.writeObject(obj);
			byteIn = new ByteArrayInputStream(byteOut.toByteArray());
			in =new ObjectInputStream(byteIn);
			newObj = in.readObject();
		}
		catch(Exception exc)
		{
			AlbianLoggerService.warn(exc,"clone objetc is error.");
			return null;
		}
		finally
		{
			try
			{
				if(null != out) 
				out.close();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				AlbianLoggerService.warn(e,"close out stream is error when clone object.");
			}
			try
			{
				if(null != byteOut) 
					byteOut.close();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				AlbianLoggerService.warn(e,"close bytes out stream is error when clone object.");
			}
			try
			{
				if(null != in) 
					in.close();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				AlbianLoggerService.warn(e,"close in stream is error when clone object.");
			}
			try
			{
				if(null != byteIn) 
					byteIn.close();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				AlbianLoggerService.warn(e,"close bytes in stream is error when clone object.");
			}
			
		}
		return newObj;
	}
	
	public static Object getObjectFromLocalCache(Class<?> cls,int start,int step,IFilterCondition[] wheres,IOrderByCondition[] orderbys)
	{
		String key = buildKey(cls,start,step,wheres,orderbys);
		return getObjectFromLocalCache(key);
	}
	
	public static Object getObjectFromLocalCache(String key)
	{
		ICacheService cache = AlbianServiceRouter.getService(ICacheService.class, key);
		if(null == cache) return null;
		ILocalCached local = cache.getLocalCache("local");
		if(null == local) return null;
		return local.get(key);
	}
	
	public static void storeObjectToLocalCache(String key,Object obj,int seconds)
	{
		ICacheService cache = AlbianServiceRouter.getService(ICacheService.class, key);
		if(null == cache) return;
		ILocalCached local = cache.getLocalCache("local");
		if(null == local) return;
		local.put(key, obj,seconds);
		return;
	}
	
	public static void storeObjectToLocalCache(Class<?> cls,int start,int step,IFilterCondition[] wheres,IOrderByCondition[] orderbys,Object obj,int seconds)
	{
		String key = buildKey(cls,start,step,wheres,orderbys);
		storeObjectToLocalCache(key, obj,seconds);
		return;
	}
	
	
	public static Object getObjectFromRemoterCache(Class<?> cls,int start,int step,IFilterCondition[] wheres,IOrderByCondition[] orderbys)
	{
		String key = buildKey(cls,start,step,wheres,orderbys);
		return getObjectFromLocalCache(key);
	}
	
	public static Object getObjectFromRemoterCache(String key)
	{
		ICacheService cache = AlbianServiceRouter.getService(ICacheService.class, key);
		if(null == cache) return null;
		MemcachedClient remoter = cache.getRemoterClient("remoter");
		if(null == remoter) return null;
		try
		{
			return remoter.get(key);
		}
		catch (Exception e)
		{
			AlbianLoggerService.warn(e,"get the remoter cache is error.key is %s.", key);
			return null;
		}
	}
	
	public static void storeObjectToRemoterCache(final String key,final Object obj,final int seconds)
	{
		IThreadPoolService thread= AlbianServiceRouter.getService(IThreadPoolService.class, "ThreadPool");
		if(null != thread)
		{
			thread.execute(new Runnable()
			{
				
				@Override
				public void run()
				{
					ICacheService cache = AlbianServiceRouter.getService(ICacheService.class, key);
					if(null == cache) return;
					MemcachedClient remoter = cache.getRemoterClient("remoter");
					if(null == remoter) return;
					try
					{
						remoter.add(key, seconds, obj);
					}
					catch (Exception e)
					{
						AlbianLoggerService.warn(e,"set the remoter cache is error.key is %s.", key);
					}
					
					return;
					
				}
			});
		}
		else
		{
			ICacheService cache = AlbianServiceRouter.getService(ICacheService.class, key);
			if(null == cache) return;
			MemcachedClient remoter = cache.getRemoterClient("remoter");
			if(null == remoter) return;
			try
			{
				remoter.add(key, seconds, obj);
			}
			catch (Exception e)
			{
				AlbianLoggerService.warn(e,"set the remoter cache is error.key is %s.", key);
			}
			
			return;
		}
		
	}
	
	public static void storeObjectToRemoterCache(Class<?> cls,int start,int step,IFilterCondition[] wheres,IOrderByCondition[] orderbys,Object obj,int seconds)
	{
		String key = buildKey(cls,start,step,wheres,orderbys);
		storeObjectToLocalCache(key, obj,seconds);
		return;
	}
	
	
}
