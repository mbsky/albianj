package org.albianj.kernel;

import java.util.HashMap;

import org.albianj.cached.ICached;
import org.albianj.cached.impl.FreeCached;
import org.albianj.cached.impl.HashCached;

public class ServiceCached extends FreeCached implements ICached
{
	public ServiceCached()
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
