package org.albianj.cached.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.albianj.cached.ICached;

public class SortCached extends FreeCached implements ICached
{
	public SortCached()
	{
		super(new LinkedHashMap<String, Object>());
	}
	
	public SortCached(Map<String, Object> map)
	{
		super(map);
	}
}
