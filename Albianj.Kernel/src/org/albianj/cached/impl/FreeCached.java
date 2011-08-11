package org.albianj.cached.impl;

import java.util.Map;

import org.albianj.cached.ICached;

public abstract class FreeCached implements ICached
{

	private Map<String, Object> map = null;
	
	public FreeCached(Map<String, Object> map)
	{
		if(null == map) throw new IllegalArgumentException("key");
		this.map = map;
	}

	@Override
	public boolean exist(String key) throws IllegalArgumentException
	{
		if(null == key || key.isEmpty()) throw new IllegalArgumentException("key");
		return map.containsKey(key);
	}

	@Override
	public Object get(String key) throws IllegalArgumentException
	{
		if(null == key || key.isEmpty()) throw new IllegalArgumentException("key");
		return map.get(key);
	}

	@Override
	public void insert(String key, Object value) throws IllegalArgumentException
	{
		if(null == key || key.isEmpty()) throw new IllegalArgumentException("key");
		if(null == value) throw new IllegalArgumentException("value");
		map.put(key, value);
		return;		
	}

	@Override
	public void remove(String key) throws IllegalArgumentException
	{
		if(null == key || key.isEmpty()) throw new IllegalArgumentException("key");
		map.remove(key);
		return;		
		
	}

	@Override
	public void clear()
	{
		map.clear();		
	}

}
