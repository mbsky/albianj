package org.albianj.persistence.impl.context;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.albianj.kernel.AlbianServiceRouter;
import org.albianj.logger.IAlbianLoggerService;
import org.albianj.persistence.impl.cached.AlbianObjectsCached;
import org.albianj.persistence.impl.cached.BeanPropertyDescriptorCached;
import org.albianj.persistence.impl.cached.RoutingCached;
import org.albianj.persistence.impl.db.CreateCommandAdapter;
import org.albianj.persistence.impl.db.ICommand;
import org.albianj.persistence.impl.db.IUpdateCommand;
import org.albianj.persistence.object.IAlbianObject;
import org.albianj.persistence.object.IAlbianObjectAttribute;
import org.albianj.persistence.object.IAlbianObjectHashMapping;
import org.albianj.persistence.object.IMemberAttribute;
import org.albianj.persistence.object.IRoutingAttribute;
import org.albianj.persistence.object.IRoutingsAttribute;
import org.albianj.verify.Validate;

public class JobAdapter
{
	public IJob buildInsert(IAlbianObject object)
	{
		IJob job = new Job();
		IUpdateCommand cca = new CreateCommandAdapter();
		buildSingleObjectJob(object,job,cca);
		return job;
	}
	
	public IJob buildInsert(IAlbianObject[] objects)
	{
		IJob job = new Job();
		IUpdateCommand cca = new CreateCommandAdapter();
		for(IAlbianObject object : objects)
		{
			buildSingleObjectJob(object,job,cca);
		}
		return job;
	}
	
	private void buildSingleObjectJob(IAlbianObject object,IJob job,IUpdateCommand update)
	{
		String className = object.getClass().getName();
		IRoutingsAttribute routings = (IRoutingsAttribute) RoutingCached.get(className);
		IAlbianObjectAttribute albianObject = (IAlbianObjectAttribute) AlbianObjectsCached.get(className);
		PropertyDescriptor[] propertyDesc = (PropertyDescriptor[]) BeanPropertyDescriptorCached.get(className);
		Map<String, Object> mapValue = buildSqlParameter(object, albianObject,propertyDesc);
		
		List<IRoutingAttribute> useRoutings = parserRoutings(object, routings,albianObject);
		
		for (IRoutingAttribute routing : useRoutings)
		{
			ICommand cmd = update.builder(object, routings, albianObject,	mapValue, routing);
			
			if (Validate.isNull(job.getTasks()))
			{
				Map<String, ITask> tasks = new LinkedHashMap<String, ITask>();
				ITask task = new Task();
				List<ICommand> cmds = new Vector<ICommand>();
				cmds.add(cmd);
				task.setCommands(cmds);
				tasks.put(routing.getStorageName(), task);
				job.setTasks(tasks);
			}
			else
			{
				if (job.getTasks().containsKey(routing.getStorageName()))
				{
					job.getTasks().get(routing.getStorageName()).getCommands().add(cmd);
				}
				else
				{
					ITask task = new Task();
					List<ICommand> cmds = new Vector<ICommand>();
					cmds.add(cmd);
					task.setCommands(cmds);
					job.getTasks().put(routing.getStorageName(), task);
				}
			}
		}
	}
	

	private Map<String, Object> buildSqlParameter(IAlbianObject object,
			IAlbianObjectAttribute albianObject,
			PropertyDescriptor[] propertyDesc)
	{
		Map<String, Object> mapValue = new HashMap<String, Object>();
		IAlbianLoggerService logger = AlbianServiceRouter.getService(IAlbianLoggerService.class, "logger");
		for (PropertyDescriptor p : propertyDesc)
		{
			try
			{
				String name = p.getName();
				if("string".equalsIgnoreCase(p.getClass().getSimpleName()))
				{
					String value = p.getReadMethod().invoke(object).toString();
					IMemberAttribute member = albianObject.getMembers().get(name);
					if(member.getLength() < value.length())//sub the property value for database length
					{
						mapValue.put(p.getName(),value.substring(0, member.getLength()));
					}
					else
					{
						mapValue.put(name,value);
					}
				}
				else
				{
					mapValue.put(name,p.getReadMethod().invoke(object));
				}
				
			}
			catch (IllegalArgumentException e)
			{
				if(null != logger)
				{
					logger.error(e,"invoke bean read method is error.");
				}
				return null;
			}
			catch (IllegalAccessException e)
			{
				if(null != logger)
				{
					logger.error(e,"invoke bean read method is error.");
				}
				return null;
			}
			catch (InvocationTargetException e)
			{
				if(null != logger)
				{
					logger.error(e,"invoke bean read method is error.");
				}
				return null;
			}
		}
		return mapValue;
	}
	
	private List<IRoutingAttribute> parserRoutings(IAlbianObject object,
			IRoutingsAttribute routings, IAlbianObjectAttribute albianObject)
	{
		List<IRoutingAttribute> useRoutings = new Vector<IRoutingAttribute>();
		if (null == routings)// no routing form configure file,then use default
		{
			useRoutings.add(albianObject.getDefaultRouting());
		}
		else// there are routings from configure file
		{
			if (Validate.isNullOrEmpty(routings.getWriterRoutings()))// writer routings is null or empty,teh use default
			{
				useRoutings.add(albianObject.getDefaultRouting());
			}
			else// there are writer routings form configure file
			{
				if (routings.getWriterHash())// use hash mapping for writer operation
				{
					IAlbianObjectHashMapping hashMapping = routings.getHashMapping();
					if (null == hashMapping)// there is no hash mapping function from configure file,then use default
					{
						useRoutings.add(albianObject.getDefaultRouting());
					}
					else
					// there us hash mapping function from configure file
					{
						String[] routingNames = hashMapping.mappingWriterRouting(object);
						if (null == routingNames || 0 == routingNames.length)// there is no routings by hash mapping function
						{
							useRoutings.add(albianObject.getDefaultRouting());
						}
						else
						{
							for (String routingName : routingNames)
							{
								IRoutingAttribute routing = routings.getWriterRoutings().get(routingName);
								if (routing.getEnable())
								{
									useRoutings.add(routing);
								}
							}
							if (Validate.isNullOrEmpty(useRoutings))
							{
								useRoutings.add(albianObject.getDefaultRouting());
							}
						}
					}
				}
			}
		}
		return useRoutings;
	}
}
