package org.albianj.service;

public class AlbianServiceAttribute implements IAlbianServiceAttribute
{

	private String id = "";
	private String type = "";

	@Override
	public String getId()
	{
		return this.id;
	}

	@Override
	public void setId(String id) throws IllegalArgumentException
	{
		if (null == id || id.isEmpty()) 
		{ 
			throw new IllegalArgumentException("id"); 
		}
		this.id = id;
	}

	@Override
	public String getType()
	{
		return this.type;
	}

	@Override
	public void setType(String type) throws IllegalArgumentException
	{
		if (null == type || type.isEmpty()) 
		{ 
			throw new IllegalArgumentException("type"); 
		}
		this.type = type;

	}

}
