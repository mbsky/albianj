package org.albianj.cached;

public interface IExpiredCached extends ICached
{
	void insert(String key, Object value, int seconds) throws IllegalArgumentException;

	void update(String key, Object value, int seconds) throws IllegalArgumentException;

	void add(String key, Object value, int seconds) throws IllegalArgumentException;
}
