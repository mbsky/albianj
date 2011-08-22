package org.albianj.persistence.object.impl;

import org.albianj.persistence.object.IAlbianObject;
import org.albianj.persistence.object.IAlbianObjectHashMapping;

public abstract class FreeAlbianObjectHashMapping implements
		IAlbianObjectHashMapping
{

	@Override
	public String[] mappingWriterRouting(IAlbianObject obj)
	{
		return null;
	}

	@Override
	public String mappingReaderRouting(IAlbianObject obj)
	{
		return null;
	}

	@Override
	public String mappingWriterTable(IAlbianObject obj,String routingName)
	{
		return null;
	}

	@Override
	public String mappingReaderTable(IAlbianObject obj,String routingName)
	{
		return null;
	}

}
