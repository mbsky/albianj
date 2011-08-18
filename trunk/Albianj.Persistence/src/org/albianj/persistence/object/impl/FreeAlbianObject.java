package org.albianj.persistence.object.impl;

import org.albianj.persistence.object.IAlbianObject;

public abstract class FreeAlbianObject implements IAlbianObject
{
	private static final long serialVersionUID = 1608573290358087720L;

	private String id = "";
	private boolean isAlbianNew = false;
	
	@Override
	public String getId()
	{
		return this.id;
	}

	@Override
	public void setId(String id)
	{
		this.id = id;
	}

	@Override
	public boolean getIsAlbianNew()
	{
		return this.isAlbianNew;
	}

	@Override
	public void setIsAlbianNew(boolean isAlbianNew)
	{
		this.isAlbianNew = isAlbianNew;
	}
}
