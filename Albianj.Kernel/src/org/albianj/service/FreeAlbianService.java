package org.albianj.service;

import org.albianj.kernel.Kernel;

@Kernel
public abstract class FreeAlbianService implements IAlbianService
{

	private AlbianServiceLifetime state = AlbianServiceLifetime.Normal;

	@Override
	public AlbianServiceLifetime getAlbianServiceState()
	{
		// TODO Auto-generated method stub
		return this.state;
	}

	@Override
	public void beforeLoad() throws AlbianServiceException
	{
		// TODO Auto-generated method stub
		this.state = AlbianServiceLifetime.BeforeLoading;
	}

	@Override
	public void loading() throws AlbianServiceException
	{
		// TODO Auto-generated method stub
		this.state = AlbianServiceLifetime.Loading;
	}

	@Override
	public void afterLoading() throws AlbianServiceException
	{
		// TODO Auto-generated method stub
		this.state = AlbianServiceLifetime.Running;
	}

	@Override
	public void beforeUnload() throws AlbianServiceException
	{
		// TODO Auto-generated method stub
		this.state = AlbianServiceLifetime.BeforeUnloading;

	}

	@Override
	public void unload() throws AlbianServiceException
	{
		// TODO Auto-generated method stub
		this.state = AlbianServiceLifetime.Unloading;
	}

	@Override
	public void afterUnload() throws AlbianServiceException
	{
		// TODO Auto-generated method stub
		this.state = AlbianServiceLifetime.Unloaded;
	}

}
