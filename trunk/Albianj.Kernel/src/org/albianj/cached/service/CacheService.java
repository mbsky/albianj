package org.albianj.cached.service;

import java.io.IOException;
import java.util.List;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.impl.KetamaMemcachedSessionLocator;
import net.rubyeye.xmemcached.utils.AddrUtil;

import org.albianj.expiredcached.impl.ILocalCached;
import org.albianj.expiredcached.impl.LocalCacheManager;
import org.albianj.logger.AlbianLoggerService;
import org.albianj.verify.Validate;
import org.albianj.xml.XmlParser;
import org.dom4j.Element;

public class CacheService extends FreeCacheService implements ICacheService
{
	protected void parserRemoterGroups(@SuppressWarnings("rawtypes") List nodes)
	{
		for (Object node : nodes)
		{
			IMemcacheGroup group = parserRemoterGroup((Element) node);
			if (null == group || !group.getEnable()) continue;
			MemcachedClientBuilder builder;
			if (null == group.getWeights())
			{
				builder = new XMemcachedClientBuilder(
						AddrUtil.getAddresses(group.getAddresses()));
			}
			else
			{
				builder = new XMemcachedClientBuilder(
						AddrUtil.getAddresses(group.getAddresses()),
						group.getWeights());
			}

			builder.setSessionLocator(new KetamaMemcachedSessionLocator());
			builder.setConnectionPoolSize(group.getPoolSize());
			try
			{
				MemcachedClient memcachedClient = builder.build();
				memcachedClient.getTranscoder().setPackZeros(false);// 关闭数据去0，为了和别的客户端兼容
				MemcacheCached.insert(group.getId(), memcachedClient);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	protected IMemcacheGroup parserRemoterGroup(Element elt)
	{
		String id = XmlParser.getAttributeValue(elt, "Id");
		if (Validate.isNullOrEmptyOrAllSpace(id))
		{
			AlbianLoggerService.warn("Memcache id is null or empry.");
			return null;
		}
		String addresses = XmlParser.getAttributeValue(elt, "Addresses");
		if (Validate.isNullOrEmptyOrAllSpace(addresses))
		{
			AlbianLoggerService.warn("%s Memcache id is null or empry.", id);
			return null;
		}
		String weights = XmlParser.getAttributeValue(elt, "Weights");
		String poolSize = XmlParser.getAttributeValue(elt, "PoolSize");
		String enable = XmlParser.getAttributeValue(elt, "Enable");
		String[] sWeight = weights.split(",");

		int[] arr = new int[sWeight.length];
		for (int i = 0; i < sWeight.length; i++)
		{
			arr[i] = new Integer(sWeight[i]);
		}

		IMemcacheGroup group = new MemcacheGroup();
		group.setId(id);
		group.setAddresses(addresses);
		group.setEnable(Validate.isNullOrEmptyOrAllSpace(enable) ? false
				: new Boolean(enable));
		group.setPoolSize(Validate.isNullOrEmptyOrAllSpace(poolSize) ? 5
				: new Integer(poolSize));
		group.setWeights(arr);
		return group;
	}

	protected void parserLocalGroups(@SuppressWarnings("rawtypes") List nodes)
	{
		for (Object node : nodes)
		{
			ILocalGroup group = parserLocalGroup((Element) node);
			if (null == group || !group.getEnable()) continue;
			LocalCacheManager.initializeCache(group.getId(), group.getSize(),
					group.getSeconds());
		}
	}

	protected ILocalGroup parserLocalGroup(Element elt)
	{
		String id = XmlParser.getAttributeValue(elt, "Id");
		if (Validate.isNullOrEmptyOrAllSpace(id))
		{
			AlbianLoggerService.warn("Memcache id is null or empry.");
			return null;
		}
		ILocalGroup group = new LocalGroup();
		String enable = XmlParser.getAttributeValue(elt, "Enable");
		String size = XmlParser.getAttributeValue(elt, "Size");
		String seconds = XmlParser.getAttributeValue(elt, "Seconds");
		LocalCacheManager.initializeCache(group.getId(), group.getSize(),
				group.getSeconds());
		
		group.setId(id);
		group.setEnable(Validate.isNullOrEmptyOrAllSpace(enable) ? false
				: new Boolean(enable));
		group.setSize(Validate.isNullOrEmptyOrAllSpace(size) ? 500
				: new Integer(size));
		group.setSeconds(Validate.isNullOrEmptyOrAllSpace(seconds) ? 300
				: new Integer(seconds));
		return group;
	}

	@Override
	public MemcachedClient getRemoterClient(String id)
	{
		return (MemcachedClient) MemcacheCached.get(id);
	}

	public ILocalCached getLocalCache(String id)
	{
		return LocalCacheManager.getCache(id);
	}
}
