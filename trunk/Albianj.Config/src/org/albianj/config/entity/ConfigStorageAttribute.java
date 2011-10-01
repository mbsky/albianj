package org.albianj.config.entity;

import java.util.List;

public class ConfigStorageAttribute implements IConfigStorageAttribute
{

	private Deployment deployment = Deployment.Normal;
	private List<IConfigServerAttribute> storageServers;
	
	public Deployment getDeployment()
	{
		return this.deployment;
	}

	public void setDeployment(Deployment deployment)
	{
		this.deployment = deployment;

	}

	public List<IConfigServerAttribute> getStorageServers()
	{
		return this.storageServers;
	}

	public void setStorageServers(List<IConfigServerAttribute> storageServers)
	{
		this.storageServers = storageServers;
	}

}
