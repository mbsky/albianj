package org.albianj.persistence.impl.db;

import java.util.Map;

import org.albianj.persistence.object.IAlbianObject;
import org.albianj.persistence.object.IAlbianObjectAttribute;
import org.albianj.persistence.object.IRoutingAttribute;
import org.albianj.persistence.object.IRoutingsAttribute;

public class SaveCommandAdapter implements IUpdateCommand
{

	@Override
	public ICommand builder(IAlbianObject object, IRoutingsAttribute routings,
			IAlbianObjectAttribute albianObject, Map<String, Object> mapValue,
			IRoutingAttribute routing)
	{
		if(object.getIsAlbianNew())
		{
			return new CreateCommandAdapter().builder(object, routings, albianObject, mapValue, routing);
		}
		else
		{
			return new ModifyCommandAdapter().builder(object, routings, albianObject, mapValue, routing);
		}
	}

}
