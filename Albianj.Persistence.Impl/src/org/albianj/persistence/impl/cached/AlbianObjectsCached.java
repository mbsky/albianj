package org.albianj.persistence.impl.cached;

import org.albianj.cached.ICached;
import org.albianj.cached.impl.HashCached;

public class AlbianObjectsCached
{
	private static ICached cached = new HashCached();

	public synchronized static boolean exist(String key)
			throws IllegalArgumentException
	{
		return cached.exist(key);
	}

	// no synchronized
	public static Object get(String key) throws IllegalArgumentException
	{
		return cached.get(key);
	}

	public synchronized static void insert(String key, Object value)
			throws IllegalArgumentException
	{
		cached.insert(key, value);
	}

	public synchronized static void remove(String key)
			throws IllegalArgumentException
	{
		cached.remove(key);
	}

	public synchronized static void clear()
	{
		cached.clear();
	}

}
