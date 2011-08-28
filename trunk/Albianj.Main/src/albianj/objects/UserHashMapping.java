package albianj.objects;

import java.util.List;

import org.albianj.algorithm.Hash;
import org.albianj.persistence.impl.routing.RoutingService;
import org.albianj.persistence.object.IAlbianObject;
import org.albianj.persistence.object.IAlbianObjectHashMapping;
import org.albianj.persistence.object.IFilterCondition;
import org.albianj.persistence.object.IOrderByCondition;
import org.albianj.persistence.object.impl.FreeAlbianObjectHashMapping;

public class UserHashMapping extends FreeAlbianObjectHashMapping implements
		IAlbianObjectHashMapping
{

	@Override
	public String[] mappingWriterRouting(IAlbianObject obj)
	{
		// TODO Auto-generated method stub
		return super.mappingWriterRouting(obj);
	}

	@Override
	public String mappingReaderRouting(List<IFilterCondition> wheres,
			List<IOrderByCondition> orderbys)
	{
		// TODO Auto-generated method stub
		return super.mappingReaderRouting(wheres,orderbys);
	}

	@Override
	public String mappingWriterTable(String routingName,IAlbianObject obj)
	{
		// TODO Auto-generated method stub
		return witerTableMapper(routingName,obj);
	}

	@Override
	public String mappingReaderTable(String routingName,List<IFilterCondition> wheres,
			List<IOrderByCondition> orderbys)
	{
		return readerTableMapper(routingName,wheres,orderbys);
	}
	
	protected String witerTableMapper(String routingName,IAlbianObject obj)
	{
		if(routingName.equalsIgnoreCase(RoutingService.DEFAULT_ROUTING_NAME))
		{
			long code = Hash.generateHashCode(obj.getId()) % 4;
			return "_" + code;
		}
		return "";
	}
	
	protected String readerTableMapper(String routingName,List<IFilterCondition> wheres,
			List<IOrderByCondition> orderbys)
	{
		if(routingName.equalsIgnoreCase(RoutingService.DEFAULT_ROUTING_NAME))
		{
			for(IFilterCondition filter : wheres)
			{
				if(filter.getFieldName().equals("id"))
				{
					String id = filter.getValue().toString();
					long code = Hash.generateHashCode(id) % 4;
					return "_" + code;
				}
			}
		}
		return "";
	}

}
