package albianj.objects;

import org.albianj.persistence.impl.routing.RoutingService;
import org.albianj.persistence.object.IAlbianObject;
import org.albianj.persistence.object.IAlbianObjectHashMapping;
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
	public String mappingReaderRouting(IAlbianObject obj)
	{
		// TODO Auto-generated method stub
		return super.mappingReaderRouting(obj);
	}

	@Override
	public String mappingWriterTable(IAlbianObject obj, String routingName)
	{
		// TODO Auto-generated method stub
		return super.mappingWriterTable(obj, routingName);
	}

	@Override
	public String mappingReaderTable(IAlbianObject obj, String routingName)
	{
		// TODO Auto-generated method stub
		return super.mappingReaderTable(obj, routingName);
	}
	
	protected String mappingTable(IAlbianObject obj,String routingName)
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
	
}
