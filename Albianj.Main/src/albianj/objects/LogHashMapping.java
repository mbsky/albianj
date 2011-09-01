package albianj.objects;

import java.util.List;
import java.util.Map;

import org.albianj.persistence.impl.routing.RoutingService;
import org.albianj.persistence.object.IAlbianObject;
import org.albianj.persistence.object.IAlbianObjectHashMapping;
import org.albianj.persistence.object.IFilterCondition;
import org.albianj.persistence.object.IOrderByCondition;
import org.albianj.persistence.object.IRoutingAttribute;
import org.albianj.persistence.object.impl.FreeAlbianObjectHashMapping;

public class LogHashMapping extends FreeAlbianObjectHashMapping implements
		IAlbianObjectHashMapping
{

	public List<IRoutingAttribute> mappingWriterRouting(Map<String,IRoutingAttribute> routings,IAlbianObject obj)
	{
		return null;
	}
	/**
	 * @param 该对象所有reader routings
	 * @param 查询条件
	 * @param 排序条件
	 * @return 查找该对象的routing
	 */
	public IRoutingAttribute mappingReaderRouting(Map<String,IRoutingAttribute> routings,Map<String,IFilterCondition> wheres,
			Map<String,IOrderByCondition> orderbys)
	{
		return null;
	}
	/**
	 * @param 该对象散列到的routing
	 * @param 需要散列的对象
	 * @return 该对象散列到的完整表名
	 */
	public String mappingWriterTable(IRoutingAttribute routing,IAlbianObject obj)
	{
		return null;
	}

	/**
	 * @param 该对象散列到的reader routing
	 * @param 查询条件
	 * @param 排序条件
	 * @return 查询该对象的完整表明
	 */
	public String mappingReaderTable(IRoutingAttribute routing,Map<String,IFilterCondition> wheres,
			Map<String,IOrderByCondition> orderbys)
	{
		return null;
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
