package org.albianj.persistence.impl.persistence;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;

import org.albianj.kernel.AlbianServiceRouter;
import org.albianj.logger.IAlbianLoggerService;
import org.albianj.persistence.object.MemberAnnotation;

public class ReflectionHelper
{
	public static BeanInfo getBeanInfo(String className)
	{
		IAlbianLoggerService logger = AlbianServiceRouter.getService(IAlbianLoggerService.class, "logger");
		try
		{
			Class cls = Class.forName(className);
			BeanInfo info = Introspector.getBeanInfo(cls, Object.class);
			return info;
		}
		catch (ClassNotFoundException exc)
		{
			if(null != logger)
				logger.error("Reflection bean is error.Message:", exc.getMessage());
			return null;
		}
		catch (IntrospectionException exc)
		{
			if(null != logger)
				logger.error("Reflection bean is error.Message:", exc.getMessage());
			return null;
		}
	}
	
	public static PropertyDescriptor[] getBeanPropertyDescriptors(String className)
	{
		IAlbianLoggerService logger = AlbianServiceRouter.getService(IAlbianLoggerService.class, "logger");
		BeanInfo beanInfo;
		try
		{
			beanInfo = getBeanInfo(className);
		}
		catch(Exception exc)
		{			
			if(null != logger)
				logger.error("Get bean info is error.Message:", exc.getMessage());
			return null;
		}
		if(null == beanInfo)
		{
			if(null != logger)
				logger.error("Get bean info is error.");
			return null;
		}
		return beanInfo.getPropertyDescriptors();
	}
	
	public static String getClassSimpleName(String className)
	{
		IAlbianLoggerService logger = AlbianServiceRouter.getService(IAlbianLoggerService.class, "logger");
		try
		{
			Class cls = Class.forName(className);
			return cls.getSimpleName();
		}
		catch (ClassNotFoundException exc)
		{
			if(null != logger)
				logger.error("Reflection bean is error.Message:", exc.getMessage());
			return null;
		}
	}
}
