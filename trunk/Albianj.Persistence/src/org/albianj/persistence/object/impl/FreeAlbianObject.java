package org.albianj.persistence.object.impl;

import org.albianj.persistence.object.IAlbianObject;

public abstract class FreeAlbianObject implements IAlbianObject
{
	private static final long serialVersionUID = 1608573290358087720L;

	private String id = "";
	private boolean isNew = false;
	
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
	public boolean getIsNew()
	{
		return this.isNew;
	}

	@Override
	public void setIsNew(boolean isNew)
	{
		this.isNew = isNew;
	}
	
	@Override
	public String mappingWriterRouter()
	{
		return null;
	}
	
	@Override
	public String mappingReaderRouter()
	{
		return null;
	}
	
	@Override
	public String mappingWriterTable()
	{
		return null;
	}
	
	@Override
	public String mappingReaderTable()
	{
		return null;
	}

}
