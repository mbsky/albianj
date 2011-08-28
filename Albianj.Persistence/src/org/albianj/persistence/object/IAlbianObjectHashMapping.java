package org.albianj.persistence.object;

import java.util.List;


public interface IAlbianObjectHashMapping
{
	public String[] mappingWriterRouting(IAlbianObject obj);
	public String mappingReaderRouting(List<IFilterCondition> wheres,
			List<IOrderByCondition> orderbys);
	public String mappingWriterTable(String routingName,IAlbianObject obj);
	public String mappingReaderTable(String routingName,List<IFilterCondition> wheres,
			List<IOrderByCondition> orderbys);
}
