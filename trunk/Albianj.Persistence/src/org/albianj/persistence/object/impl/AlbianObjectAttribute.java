package org.albianj.persistence.object.impl;

import org.albianj.persistence.object.IAlbianObjectAttribute;
import org.albianj.persistence.object.ICacheAttribute;
import org.albianj.persistence.object.IMemberAttribute;
import org.albianj.persistence.object.IRoutingAttribute;

public class AlbianObjectAttribute implements IAlbianObjectAttribute
{

	private ICacheAttribute cache = null;
	private IRoutingAttribute defaultRouting = null;
	private  IMemberAttribute[] members = null;
	private String type = null; 
	
	public String getType()
	{
		return this.type;
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	
	@Override	
	public ICacheAttribute getCache()
	{
		// TODO Auto-generated method stub
		return this.cache;
	}

	@Override
	public void setCache(ICacheAttribute cache)
	{
		// TODO Auto-generated method stub
		this.cache = cache;
	}

	public IRoutingAttribute getDefaultRouting()
	{
		return this.defaultRouting;
	}
	
	public void setDefaultRouting(IRoutingAttribute defaultRouting)
	{
		this.defaultRouting = defaultRouting;
	}
	
	@Override
	public IMemberAttribute[] getMembers()
	{
		// TODO Auto-generated method stub
		return this.members;
	}

	@Override
	public void setMembers(IMemberAttribute[] members)
	{
		// TODO Auto-generated method stub
		this.members = members;
	}
}