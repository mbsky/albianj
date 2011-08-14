package org.albianj.service.parser;

import org.albianj.cached.ICached;
import org.albianj.cached.impl.SortCached;

public final class ServiceAttributeCached
{
	private static ICached cached = new SortCached();
	
	public synchronized static boolean exist(String key) throws IllegalArgumentException
	{
		return cached.exist(key);
	}

	public static Object get(String key) throws IllegalArgumentException
	{
		return cached.get(key);
	}

	public synchronized static void insert(String key, Object value) throws IllegalArgumentException
	{
		cached.insert(key, value);
	}

	public synchronized static void remove(String key) throws IllegalArgumentException
	{
		cached.remove(key);
	}

	public synchronized static void clear()
	{
		cached.clear();
	}
}
