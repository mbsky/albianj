package org.albianj.persistence.object.impl;

import java.util.List;

import org.albianj.persistence.object.IRoutingAttribute;
import org.albianj.persistence.object.IRoutingsAttribute;

public class RoutingsAttribute implements IRoutingsAttribute
{

	private boolean writerHash = false;
	private boolean readerHash = false;
	private List<IRoutingAttribute> writerRoutings = null;
	private List<IRoutingAttribute> readerRoutings = null;
	@Override
	public boolean getWriterHash()
	{
		// TODO Auto-generated method stub
		return this.writerHash;
	}

	@Override
	public void setWriterHash(boolean writerHash)
	{
		// TODO Auto-generated method stub
		this.writerHash = writerHash;
	}

	@Override
	public boolean getReaderHash()
	{
		// TODO Auto-generated method stub
		return this.readerHash;
	}

	@Override
	public void setReaderHash(boolean readerHash)
	{
		// TODO Auto-generated method stub
		this.readerHash = readerHash;
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
	public void setReaderRoutings(List<IRoutingAttribute> readerRoutings)
	{
		// TODO Auto-generated method stub
		this.readerRoutings = readerRoutings;
	}

}
