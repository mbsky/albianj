package org.albianj.cached.service;

public class MemcacheGroup implements IMemcacheGroup
{
	private String id = null;
	private boolean enable = true;
	private String addresses = null;
	private int[] weights = null;
	private int poolSize = 5;
	
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public boolean getEnable()
	{
		return enable;
	}
	public void setEnable(boolean enable)
	{
		this.enable = enable;
	}
	public String getAddresses()
	{
		return addresses;
	}
	public void setAddresses(String addresses)
	{
		this.addresses = addresses;
	}
	public int[] getWeights()
	{
		return weights;
	}
	public void setWeights(int[] weights)
	{
		this.weights = weights;
	}
	public int getPoolSize()
	{
		return this.poolSize;
	}
	public void setPoolSize(int poolSize)
	{
		this.poolSize = poolSize;
	}
}
