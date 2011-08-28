package org.albianj.persistence.object.impl;

import java.util.List;

import org.albianj.persistence.object.IAlbianObject;
import org.albianj.persistence.object.IAlbianObjectHashMapping;
import org.albianj.persistence.object.IFilterCondition;
import org.albianj.persistence.object.IOrderByCondition;

public abstract class FreeAlbianObjectHashMapping implements
		IAlbianObjectHashMapping
{

	@Override
	public String[] mappingWriterRouting(IAlbianObject obj)
	{
		return null;
	}

	@Override
	public String mappingReaderRouting(List<IFilterCondition> wheres,
			List<IOrderByCondition> orderbys)
	{
		return null;
	}

	@Override
	public String mappingWriterTable(String routingName,IAlbianObject obj)
	{
		return null;
	}

	@Override
	public String mappingReaderTable(String routingName,List<IFilterCondition> wheres,
			List<IOrderByCondition> orderbys)
	{
		return null;
	}

}
