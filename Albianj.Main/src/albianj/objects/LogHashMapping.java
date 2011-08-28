package albianj.objects;

import java.util.List;

import org.albianj.persistence.impl.routing.RoutingService;
import org.albianj.persistence.object.IAlbianObject;
import org.albianj.persistence.object.IAlbianObjectHashMapping;
import org.albianj.persistence.object.IFilterCondition;
import org.albianj.persistence.object.IOrderByCondition;
import org.albianj.persistence.object.impl.FreeAlbianObjectHashMapping;

public class LogHashMapping extends FreeAlbianObjectHashMapping implements
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
		return writerTableMapper(routingName,obj);
	}

	@Override
	public String mappingReaderTable(String routingName,List<IFilterCondition> wheres,
			List<IOrderByCondition> orderbys)
	{
		// TODO Auto-generated method stub
		return readerTableMapper(routingName,wheres,orderbys);
	}
	
	protected String writerTableMapper(String routingName,IAlbianObject obj)
	{
		if(routingName.equals(RoutingService.DEFAULT_ROUTING_NAME))
		{
			ILogInfo log = (ILogInfo) obj;
			switch(log.getStyle())
			{
				case ILogInfo.LOGGER_BIZOFFER_STYLE:
					return "_bizoffer";
				case ILogInfo.LOGGER_ORDER_STYLE :
					return "_order";
				case ILogInfo.LOGGER_USER_STYLE:
					return "_user";
			}
		}
		return "";
	}
	
	protected String readerTableMapper(String routingName,List<IFilterCondition> wheres,
			List<IOrderByCondition> orderbys)
	{
		if(routingName.equals(RoutingService.DEFAULT_ROUTING_NAME))
		{
			for(IFilterCondition filter : wheres)
			{
				if(filter.getFieldName().equals("style"))
				{
					int style = Integer.parseInt(filter.getValue().toString());
					switch(style)
					{
						case ILogInfo.LOGGER_BIZOFFER_STYLE:
							return "_bizoffer";
						case ILogInfo.LOGGER_ORDER_STYLE :
							return "_order";
						case ILogInfo.LOGGER_USER_STYLE:
							return "_user";
					}
				}
			}
		}
		return "";
	}
	
}
