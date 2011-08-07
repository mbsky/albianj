package org.albianj.service;

public class AlbianServiceAttrbuite implements IAlbianServiceAttrbuite
{

	private String id = "";
	private String base = "";
	private String type = "";

	@Override
	public String getId()
	{
		return this.id;
	}

	@Override
	public void setId(String id) throws NullPointerException
	{
		if (null == id || id.isEmpty()) 
		{ 
			throw new NullPointerException("id"); 
		}
		this.id = id;
	}

	@Override
	public String getBase()
	{
		return this.base;
	}

	@Override
	public void setBase(String base) throws NullPointerException
	{
		if (null == base || base.isEmpty()) 
		{ 
			throw new NullPointerException("base"); 
		}
		this.base = base;
	}

	@Override
	public String getType()
	{
		return this.type;
	}

	@Override
	public void setType(String type) throws NullPointerException
	{
		if (null == type || type.isEmpty()) 
		{ 
			throw new NullPointerException("type"); 
		}
		this.type = type;

	}

}
