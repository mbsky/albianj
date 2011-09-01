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
	 * @param �ö�������reader routings
	 * @param ��ѯ����
	 * @param ��������
	 * @return ���Ҹö����routing
	 */
	public IRoutingAttribute mappingReaderRouting(Map<String,IRoutingAttribute> routings,Map<String,IFilterCondition> wheres,
			Map<String,IOrderByCondition> orderbys)
	{
		return null;
	}
	/**
	 * @param �ö���ɢ�е���routing
	 * @param ��Ҫɢ�еĶ���
	 * @return �ö���ɢ�е�����������
	 */
	public String mappingWriterTable(IRoutingAttribute routing,IAlbianObject obj)
	{
		return null;
	}

	/**
	 * @param �ö���ɢ�е���reader routing
	 * @param ��ѯ����
	 * @param ��������
	 * @return ��ѯ�ö������������
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
