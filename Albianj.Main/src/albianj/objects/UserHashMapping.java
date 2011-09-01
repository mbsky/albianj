package albianj.objects;

import java.util.List;
import java.util.Map;

import org.albianj.algorithm.Hash;
import org.albianj.persistence.impl.routing.RoutingService;
import org.albianj.persistence.object.IAlbianObject;
import org.albianj.persistence.object.IAlbianObjectHashMapping;
import org.albianj.persistence.object.IFilterCondition;
import org.albianj.persistence.object.IOrderByCondition;
import org.albianj.persistence.object.IRoutingAttribute;
import org.albianj.persistence.object.impl.FreeAlbianObjectHashMapping;

public class UserHashMapping extends FreeAlbianObjectHashMapping implements
		IAlbianObjectHashMapping
{

	@Override
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
