package org.albianj.cached.impl;

import org.albianj.cached.IExpiredCached;
import org.albianj.expiredcached.impl.Cache;
import org.albianj.service.FreeAlbianService;
import org.albianj.service.IAlbianService;

public class LocalCachedService extends FreeAlbianService  implements IExpiredCached,IAlbianService 
{
	private static final String DEFAULT_CACHE_NAME = "ALBIAN_DEFAULT_CACHE_NAME";
	private static Cache cache = null;

	public void loading()
	{
		cache = new Cache(DEFAULT_CACHE_NAME, 500, 300 * 1000);
	}
	
	@Override
	public boolean exist(String key) throws IllegalArgumentException
	{
		return cache.containsKey(key);
	}

	@Override
	public Object get(String key) throws IllegalArgumentException
	{
		return cache.get(key);
	}

	@Override
	public void remove(String key) throws IllegalArgumentException
	{
		cache.remove(key);

	}

	@Override
	public void clear()
	{
		cache.clear();

	}

	@Override
	public void insert(String key, Object value, int seconds)
			throws IllegalArgumentException
	{
		cache.put(key, value, seconds * 1000);

	}

}
