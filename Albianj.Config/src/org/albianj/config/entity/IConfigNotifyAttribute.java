package org.albianj.config.entity;

import java.util.List;

public interface IConfigNotifyAttribute
{
	public boolean getHash();
	public void setHash(boolean hash);
	public List<IConfigServerAttribute> getNotifyServers();
	public void setNotifyServers(List<IConfigServerAttribute> notifyServers);
	public boolean getPersistence();
	public void serPersistence(boolean persistence);
}
