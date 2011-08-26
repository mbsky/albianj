package org.albianj.persistence.impl.context;

import org.albianj.persistence.impl.cached.AlbianObjectsCached;
import org.albianj.persistence.impl.cached.RoutingCached;
import org.albianj.persistence.object.IAlbianObjectAttribute;
import org.albianj.persistence.object.IFilterCondition;
import org.albianj.persistence.object.IOrderByCondition;
import org.albianj.persistence.object.IRoutingAttribute;
import org.albianj.persistence.object.IRoutingsAttribute;

public class ReaderJobAdapter
{
	public IReaderJob buildReaderJob(Class<?> cls,String routingName, int start,int step, IFilterCondition[] where,
            IOrderByCondition[] orderby)
	{
		String className = cls.getName();
		IRoutingAttribute usedRoutingsAttribute = null;
		IRoutingsAttribute routings = (IRoutingsAttribute) RoutingCached.get(className);
		IAlbianObjectAttribute albianObject = (IAlbianObjectAttribute) AlbianObjectsCached.get(className);
		if(null == routings)
		{
			usedRoutingsAttribute = albianObject.getDefaultRouting();
		}
		else
		{
			if(routings.getReaderHash())
			{
//				routings.getHashMapping().mappingReaderRouting(obj);
			}
		}
		return null;
	}
}
