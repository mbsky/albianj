package org.albianj.persistence.impl.dbcached;

import org.albianj.persistence.object.IFilterCondition;
import org.albianj.persistence.object.IOrderByCondition;

public class CacheOperator
{
	public static String buildKey(Class<?> cls,IFilterCondition[] wheres,IOrderByCondition[] orderbys)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(cls.getName()).append("_");
		if(null != wheres)
		{
			for(IFilterCondition where : wheres)
			{
				sb.append(where.getRelationalOperator()).append("_")
				.append(where.getFieldName()).append("_")
				.append(where.getLogicalOperation()).append("_")
				.append(where.getValue());
			}
		}
		if(null != orderbys)
		{
			for(IOrderByCondition orderby : orderbys)
			{
				sb.append(orderby.getFieldName()).append("_")
				.append(orderby.getSortStyle()).append("_");
			}
		}
		return sb.toString();
	}
}
