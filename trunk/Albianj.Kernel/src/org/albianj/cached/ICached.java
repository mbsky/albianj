package org.albianj.cached;

public interface ICached
{
	public boolean exist(String key) throws IllegalArgumentException;

	public Object get(String key) throws IllegalArgumentException;

	public void insert(String key, Object value) throws IllegalArgumentException;

	public void remove(String key) throws IllegalArgumentException;

	public void clear();
	
//	public boolean hasNext();
//	public Object getNext();
}
