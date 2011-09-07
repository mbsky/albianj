package org.albianj.cached.service;

public interface ILocalGroup
{
	public String getId();
	public void setId(String id);
	public int getSize();
	public void setSize(int size);
	public int getSeconds();
	public void setSeconds(int seconds);
	public boolean getEnable();
	public void setEnable(boolean enable);
}
