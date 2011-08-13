package org.albianj.persistence.object.impl;

import org.albianj.persistence.object.IAlbianObject;
import org.albianj.persistence.object.MemberAnnotation;

public abstract class FreeAlbianObject implements IAlbianObject
{
	private static final long serialVersionUID = 1608573290358087720L;

	@MemberAnnotation(PrimaryKey=true,AllowNull=false,FieldName="Id",IsSave=true,Length=36)
	private String id = "";
	@MemberAnnotation(IsSave=false)
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
	public String mappingWriterRouting()
	{
		return null;
	}
	
	@Override
	public String mappingReaderRouting()
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
