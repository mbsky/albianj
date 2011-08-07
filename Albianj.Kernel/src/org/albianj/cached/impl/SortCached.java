package org.albianj.cached.impl;

import java.util.LinkedHashMap;

import org.albianj.cached.ICached;

public class SortCached extends FreeCached implements ICached
{
	public SortCached()
	{
		map = new LinkedHashMap<String, Object>();
	}
	
	private static ICached cached = null;
	public synchronized static ICached Instance()
	{
		if(null == cached) cached = new SortCached();
		return cached;
	}
}
