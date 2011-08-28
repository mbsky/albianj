package org.albianj.persistence.impl.context;

import java.util.List;

import org.albianj.persistence.object.IFilterCondition;
import org.albianj.persistence.object.IOrderByCondition;

public interface IReaderJobAdapter
{
	public IReaderJob buildReaderJob(Class<?> cls, String routingName,
			int start, int step, List<IFilterCondition> wheres,
			List<IOrderByCondition> orderbys);
}
