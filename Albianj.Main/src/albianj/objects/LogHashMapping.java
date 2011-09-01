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
