package org.albianj.config.entity;

import java.util.List;

public class ConfigNotifyAttribute implements IConfigNotifyAttribute
{

	private boolean hash = false;
	private List<IConfigServerAttribute> notifyServers;
	private boolean persistence = false;
	@Override
	public boolean getHash()
	{
	return this.hash;
	}

	@Override
	public void setHash(boolean hash)
	{
		this.hash = hash;

	}

	@Override
	public List<IConfigServerAttribute> getNotifyServers()
	{
		return this.notifyServers;
	}

	@Override
	public void setNotifyServers(List<IConfigServerAttribute> notifyServers)
	{
		this.notifyServers = notifyServers;
	}

	public boolean getPersistence()
	{
		return this.persistence;
	}
	public void serPersistence(boolean persistence)
	{
		this.persistence = persistence;
	}
}
