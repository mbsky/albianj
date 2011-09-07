package org.albianj.cached.service;

public class LocalGroup implements ILocalGroup
{
	private String id = null;
	private int size = 500;
	private int seconds = 300;
	private boolean enable = false;
	
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public int getSize()
	{
		return size;
	}
	public void setSize(int size)
	{
		this.size = size;
	}
	public int getSeconds()
	{
		return seconds;
	}
	public void setSeconds(int seconds)
	{
		this.seconds = seconds;
	}
	public boolean getEnable()
	{
		return this.enable;
	}
	public void setEnable(boolean enable)
	{
		this.enable = enable;
	}
}
