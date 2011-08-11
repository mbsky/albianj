package org.albianj.persistence.object.impl;

import java.util.List;

import org.albianj.persistence.object.ICacheAttribute;
import org.albianj.persistence.object.IMemberAttribute;
import org.albianj.persistence.object.IPersistenceAttribute;
import org.albianj.persistence.object.IRoutingAttribute;

public class PersistenceAttribute implements IPersistenceAttribute
{

	private ICacheAttribute cache = null;
	private List<IRoutingAttribute> writerRoutings = null;
	private List<IRoutingAttribute> readerRoutings = null;
	private  List<IMemberAttribute> members = null;
	
	@Override
	public ICacheAttribute getCache()
	{
		// TODO Auto-generated method stub
		return this.cache;
	}

	@Override
	public void setCache(ICacheAttribute cache)
	{
		// TODO Auto-generated method stub
		this.cache = cache;
	}

	@Override
	public List<IRoutingAttribute> getWriterRoutings()
	{
		// TODO Auto-generated method stub
		return this.writerRoutings;
	}

	@Override
	public void setWriterRoutings(List<IRoutingAttribute> writerRoutings)
	{
		// TODO Auto-generated method stub
		this.writerRoutings = writerRoutings;
	}

	@Override
	public List<IRoutingAttribute> getReaderRoutings()
	{
		// TODO Auto-generated method stub
		return this.readerRoutings;
	}

	@Override
	public void setReadererRoutings(List<IRoutingAttribute> readerRoutings)
	{
		// TODO Auto-generated method stub
		this.readerRoutings = readerRoutings;
	}

	@Override
	public List<IMemberAttribute> getMembers()
	{
		// TODO Auto-generated method stub
		return this.members;
	}

	@Override
	public void setMembers(List<IMemberAttribute> members)
	{
		// TODO Auto-generated method stub
		this.members = members;
	}

}
