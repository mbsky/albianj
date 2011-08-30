package org.albianj.persistence.impl.context;

import java.util.Map;
import java.util.Set;

import org.albianj.kernel.AlbianServiceRouter;
import org.albianj.logger.IAlbianLoggerService;
import org.albianj.persistence.impl.cached.AlbianObjectsCached;
import org.albianj.persistence.impl.cached.RoutingCached;
import org.albianj.persistence.object.IAlbianObjectAttribute;
import org.albianj.persistence.object.IAlbianObjectHashMapping;
import org.albianj.persistence.object.IFilterCondition;
import org.albianj.persistence.object.IOrderByCondition;
import org.albianj.persistence.object.IRoutingAttribute;
import org.albianj.persistence.object.IRoutingsAttribute;
import org.albianj.verify.Validate;

public class ReaderJobAdapter extends FreeReaderJobAdapter implements IReaderJobAdapter
{
	protected IRoutingAttribute parserReaderRouting(Class<?> cls,String routingName,Map<String,IFilterCondition> hashWheres,
			Map<String,IOrderByCondition> hashOrderbys)
	{
		IAlbianLoggerService logger = AlbianServiceRouter.getService(
				IAlbianLoggerService.class, "logger");
		String className = cls.getName();
		IRoutingsAttribute routings = (IRoutingsAttribute) RoutingCached.get(className);
		IAlbianObjectAttribute albianObject = (IAlbianObjectAttribute) AlbianObjectsCached.get(className);
		if (null == routings || Validate.isNullOrEmpty(routings.getReaderRoutings()))
		{
			if(null != logger)
			{
				logger.warn("the '",className,"' routings is null or empty.then use default.");
			}
			return albianObject.getDefaultRouting();
		}
		if(!Validate.isNullOrEmptyOrAllSpace(routingName))
		{
			IRoutingAttribute routing = routings.getReaderRoutings().get(routingName);
			if(null == routing)
			{
				if(null != logger)
				{
					logger.warn("The '",routingName,"' routing is not in reader routings,then use default routing.");
				}
				return albianObject.getDefaultRouting();
			}
			if(!routing.getEnable())
			{
				if(null != logger)
				{
					logger.warn("The '",routingName,"' routing is not enable,then use default.");
				}
				return albianObject.getDefaultRouting();
			}
			return routing;
		}
	
		if (1 == routings.getReaderRoutings().size())
		{
			if (null != logger)
			{
				logger.warn("The ",className," routing in configure is one,so only use it.");
			}

			Set<String> keys = routings.getReaderRoutings().keySet();
			String key = (String) keys.toArray()[0];
			IRoutingAttribute routing = routings.getReaderRoutings().get(key);
			if(null == routing)
			{
				if(null != logger)
				{
					logger.warn("The '",routingName,"' routing is not in reader routings,then use default routing.");
				}
				return albianObject.getDefaultRouting();
			}
			if(!routing.getEnable())
			{
				if(null != logger)
				{
					logger.warn("The '",className,"' routing is only one,but not enable,then use default.");
				}
				return albianObject.getDefaultRouting();
			}
			return routing;
		}
		
		if (routings.getReaderHash())
		{
			IAlbianObjectHashMapping hashMapping = routings.getHashMapping();
			if(null != hashMapping)
			{
				IRoutingAttribute routing  = hashMapping.mappingReaderRouting(routings.getReaderRoutings(),hashWheres, hashOrderbys);
				if(null == routing || !routing.getEnable())
				{
					if(null != logger)
					{
						logger.warn("The '",className,"'hash routing is null or empty,,then use default.");
					}
					return albianObject.getDefaultRouting();
				}
				return routing;
			}
			if(null != logger)
			{
				logger.warn("There is not hash function for reader.then use default.");
			}
			return albianObject.getDefaultRouting();
		}
		
		if(null != logger)
		{
			logger.warn("Not match the all parser routings condition.then use default.");
		}
		return albianObject.getDefaultRouting();
	}
}
