package org.albianj.cached.impl;

import java.util.HashMap;

import org.albianj.cached.ICached;

public class HashCached extends FreeCached implements ICached
{
	public HashCached()
	{
		map = new HashMap<String, Object>();
	}
	
	private static ICached cached = null;
	public synchronized static ICached Instance()
	{
		if(null == cached) cached = new HashCached();
		return cached;
	}
}
