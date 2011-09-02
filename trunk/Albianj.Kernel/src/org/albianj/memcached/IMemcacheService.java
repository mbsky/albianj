package org.albianj.memcached;

import net.rubyeye.xmemcached.MemcachedClient;

public interface IMemcacheService
{
	public MemcachedClient getClient(String id);
}
