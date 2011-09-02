package org.albianj.memcached;

import org.albianj.service.IAlbianService;

import net.rubyeye.xmemcached.MemcachedClient;

public interface IMemcacheService extends IAlbianService
{
	public MemcachedClient getClient(String id);
}
