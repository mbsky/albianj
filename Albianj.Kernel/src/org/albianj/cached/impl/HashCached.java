package org.albianj.cached.impl;

import java.util.HashMap;
import java.util.Map;

import org.albianj.cached.ICached;

public class HashCached extends FreeCached implements ICached
{
	public HashCached()
	{
		super(new HashMap<String, Object>());
	}
	
	public HashCached(Map<String, Object> map)
	{
		super(map);
	}
}
