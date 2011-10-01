package org.albianj.config.entity;

import java.util.List;

public interface IConfigStorageAttribute
{
	public Deployment getDeployment();
	public void setDeployment(Deployment deployment);
	public List<IConfigServerAttribute> getStorageServers();
	public void setStorageServers(List<IConfigServerAttribute> storageServers);
}
