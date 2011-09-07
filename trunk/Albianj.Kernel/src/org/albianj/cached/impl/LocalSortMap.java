package org.albianj.cached.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.albianj.cached.ILocalMap;

public class LocalSortMap extends FreeLocalMap implements ILocalMap
{
	public LocalSortMap()
	{
		super(new LinkedHashMap<String, Object>());
	}
	
	public LocalSortMap(Map<String, Object> map)
	{
		super(map);
	}
}
