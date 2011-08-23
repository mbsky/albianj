package albianj.objects;

import org.albianj.algorithm.Hash;
import org.albianj.persistence.impl.routing.RoutingService;
import org.albianj.persistence.object.IAlbianObject;
import org.albianj.persistence.object.IAlbianObjectHashMapping;
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
	public String mappingReaderRouting(IAlbianObject obj)
	{
		// TODO Auto-generated method stub
		return super.mappingReaderRouting(obj);
	}

	@Override
	public String mappingWriterTable(IAlbianObject obj, String routingName)
	{
		// TODO Auto-generated method stub
		return mappingTable(obj, routingName);
	}

	@Override
	public String mappingReaderTable(IAlbianObject obj, String routingName)
	{
		return mappingTable(obj, routingName);
	}
	
	protected String mappingTable(IAlbianObject obj,String routingName)
	{
		if(routingName.equalsIgnoreCase(RoutingService.DEFAULT_ROUTING_NAME))
		{
			long code = Hash.generateHashCode(obj.getId()) % 4;
			return "_" + code;
		}
		return "";
	}

}
