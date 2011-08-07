package org.albianj.cached.impl;

import java.util.HashMap;

import org.albianj.cached.ICached;

public class Cached implements ICached
{
	private HashMap<String, Object> map = new HashMap<String, Object>();

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
		if(!map.containsKey(key)) return null;
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
