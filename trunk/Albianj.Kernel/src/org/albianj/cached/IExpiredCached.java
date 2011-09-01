package org.albianj.cached;


public interface IExpiredCached
{
	void insert(String key, Object value, int seconds) throws IllegalArgumentException;

	public boolean exist(String key) throws IllegalArgumentException;

	public Object get(String key) throws IllegalArgumentException;

	public void remove(String key) throws IllegalArgumentException;

	public void clear();
}
