package org.albianj.persistence.impl.context;

import java.beans.PropertyDescriptor;
import java.util.List;
import java.util.Map;

import org.albianj.persistence.impl.db.CreateCommandAdapter;
import org.albianj.persistence.impl.db.IUpdateCommand;
import org.albianj.persistence.impl.db.ModifyCommandAdapter;
import org.albianj.persistence.impl.db.RemoveCommandAdapter;
import org.albianj.persistence.object.IAlbianObject;
import org.albianj.persistence.object.IAlbianObjectAttribute;
import org.albianj.persistence.object.IRoutingAttribute;
import org.albianj.persistence.object.IRoutingsAttribute;

public abstract class FreeJobAdapter implements IJobAdapter
{
	public IJob buildCreation(IAlbianObject object)
	{
		IJob job = new Job();
		IUpdateCommand cca = new CreateCommandAdapter();
		buildJob(object,job,cca);
		return job;
	}
	
	public IJob buildCreation(List<IAlbianObject> objects)
	{
		IJob job = new Job();
		IUpdateCommand cca = new CreateCommandAdapter();
		for(IAlbianObject object : objects)
		{
			buildJob(object,job,cca);
		}
		return job;
	}
	
	public IJob buildModification(IAlbianObject object)
	{
		IJob job = new Job();
		IUpdateCommand mca = new ModifyCommandAdapter();
		buildJob(object,job,mca);
		return job;
	}
	
	public IJob buildModification(List<IAlbianObject> objects)
	{
		IJob job = new Job();
		IUpdateCommand mca = new ModifyCommandAdapter();
		for(IAlbianObject object : objects)
		{
			buildJob(object,job,mca);
		}
		return job;
	}
	
	public IJob buildRemoved(IAlbianObject object)
	{
		IJob job = new Job();
		IUpdateCommand rca = new RemoveCommandAdapter();
		buildJob(object,job,rca);
		return job;
	}
	
	public IJob buildRemoved(List<IAlbianObject> objects)
	{
		IJob job = new Job();
		IUpdateCommand rca = new RemoveCommandAdapter();
		for(IAlbianObject object : objects)
		{
			buildJob(object,job,rca);
		}
		return job;
	}
	
	public IJob buildSaving(IAlbianObject object)
	{
		IJob job = new Job();
		IUpdateCommand iuc;
		if(object.getIsAlbianNew())
		{
			iuc= new CreateCommandAdapter();
		}
		else
		{
			iuc= new ModifyCommandAdapter();
		}
		 
		buildJob(object,job,iuc);
		return job;
	}
	
	public IJob buildSaving(List<IAlbianObject> objects)
	{
		IJob job = new Job();
		IUpdateCommand cca = new CreateCommandAdapter();
		IUpdateCommand mca = new ModifyCommandAdapter();
		for(IAlbianObject object : objects)
		{
			if(object.getIsAlbianNew())
			{
				buildJob(object,job,cca);
			}
			else
			{
				buildJob(object,job,mca);
			}
		}
		return job;
	}
	
	
	protected abstract void buildJob(IAlbianObject object,IJob job,IUpdateCommand update);

	protected abstract Map<String, Object> buildSqlParameter(IAlbianObject object,
			IAlbianObjectAttribute albianObject,
			PropertyDescriptor[] propertyDesc);
	
	protected abstract List<IRoutingAttribute> parserRoutings(IAlbianObject object,
			IRoutingsAttribute routings, IAlbianObjectAttribute albianObject);
}
