package org.albianj.persistence.impl.context;

import java.util.List;

import org.albianj.persistence.object.IAlbianObject;

public interface IJobAdapter
{
	public IJob buildCreation(IAlbianObject object);
	
	public IJob buildCreation(List<IAlbianObject> objects);
	
	public IJob buildModification(IAlbianObject object);
	
	public IJob buildModification(List<IAlbianObject> objects);
	public IJob buildRemoved(IAlbianObject object);
	
	public IJob buildRemoved(List<IAlbianObject> objects);
	
	public IJob buildSaving(IAlbianObject object);
	
	public IJob buildSaving(List<IAlbianObject> objects);

}
