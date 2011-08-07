package org.albianj.service;

import org.albianj.kernel.Kernel;

@Kernel
public interface IAlbianService
{

	public AlbianServiceState getAlbianServiceState();

	public void beforeLoad() throws AlbianServiceException;

	public void loading() throws AlbianServiceException;

	public void afterLoading() throws AlbianServiceException;

	public void beforeUnload() throws AlbianServiceException;

	public void unload() throws AlbianServiceException;

	public void afterUnload() throws AlbianServiceException;
}
