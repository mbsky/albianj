package org.albianj.persistence.object.impl;

import org.albianj.persistence.object.IAlbianObjectHashMapping;

public abstract class FreeAlbianObjectHashMapping implements
		IAlbianObjectHashMapping
{

	@Override
	public String[] mappingWriterRouting(Object obj)
	{
		return null;
	}

	@Override
	public String mappingReaderRouting(Object obj)
	{
		return null;
	}

	@Override
	public String mappingWriterTable(Object obj)
	{
		return null;
	}

	@Override
	public String mappingReaderTable(Object obj)
	{
		return null;
	}

}
