package org.albianj.service;

import org.albianj.kernel.Kernel;

@Kernel
public abstract class FreeAlbianService implements IAlbianService
{

	private AlbianServiceState state = AlbianServiceState.Normal;

	@Override
	public AlbianServiceState getAlbianServiceState()
	{
		// TODO Auto-generated method stub
		return this.state;
	}

	@Override
	public void beforeLoad() throws AlbianServiceException
	{
		// TODO Auto-generated method stub
		this.state = AlbianServiceState.BeforeLoading;
	}

	@Override
	public void loading() throws AlbianServiceException
	{
		// TODO Auto-generated method stub
		this.state = AlbianServiceState.Loading;
	}

	@Override
	public void afterLoading() throws AlbianServiceException
	{
		// TODO Auto-generated method stub
		this.state = AlbianServiceState.Running;
	}

	@Override
	public void beforeUnload() throws AlbianServiceException
	{
		// TODO Auto-generated method stub
		this.state = AlbianServiceState.BeforeUnloading;

	}

	@Override
	public void unload() throws AlbianServiceException
	{
		// TODO Auto-generated method stub
		this.state = AlbianServiceState.Unloading;
	}

	@Override
	public void afterUnload() throws AlbianServiceException
	{
		// TODO Auto-generated method stub
		this.state = AlbianServiceState.Unloaded;
	}

}
