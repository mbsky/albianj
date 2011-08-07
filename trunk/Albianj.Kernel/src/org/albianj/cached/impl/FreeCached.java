package org.albianj.cached.impl;

import java.util.Map;

import org.albianj.cached.ICached;

public abstract class FreeCached implements ICached
{

	protected Map<String, Object> map;

	@Override
	public synchronized boolean exist(String key) throws IllegalArgumentException
	{
		if(null == key || key.isEmpty()) throw new IllegalArgumentException("key");
		return map.containsKey(key);
	}

	@Override
	public synchronized Object get(String key) throws IllegalArgumentException
	{
		if(null == key || key.isEmpty()) throw new IllegalArgumentException("key");
		return map.get(key);
	}

	@Override
	public synchronized void insert(String key, Object value) throws IllegalArgumentException
	{
		if(null == key || key.isEmpty()) throw new IllegalArgumentException("key");
		if(null == value) throw new IllegalArgumentException("value");
		map.put(key, value);
		return;		
	}

	@Override
	public synchronized void remove(String key) throws IllegalArgumentException
	{
		if(null == key || key.isEmpty()) throw new IllegalArgumentException("key");
		map.remove(key);
		return;		
		
	}

	@Override
	public synchronized void clear()
	{
		map.clear();		
	}

}
