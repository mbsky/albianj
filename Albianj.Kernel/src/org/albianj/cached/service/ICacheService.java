package org.albianj.cached.service;

import org.albianj.cache.ILocalCached;
import org.albianj.service.IAlbianService;

import net.rubyeye.xmemcached.MemcachedClient;

public interface ICacheService extends IAlbianService
{
	public MemcachedClient getRemoterClient(String id);
	
	public ILocalCached getLocalCache(String id);
}
