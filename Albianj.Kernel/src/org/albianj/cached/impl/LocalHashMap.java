package org.albianj.cached.impl;

import java.util.HashMap;
import java.util.Map;

import org.albianj.cached.ILocalMap;

public class LocalHashMap extends FreeLocalMap implements ILocalMap
{
	public LocalHashMap()
	{
		super(new HashMap<String, Object>());
	}
	
	public LocalHashMap(Map<String, Object> map)
	{
		super(map);
	}


}
