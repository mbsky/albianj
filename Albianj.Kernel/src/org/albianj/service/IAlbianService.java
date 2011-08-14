package org.albianj.service;

import org.albianj.kernel.Kernel;

@Kernel
public interface IAlbianService
{

	public AlbianServiceLifetime getAlbianServiceState();

	public void beforeLoad() throws RuntimeException;

	public void loading() throws RuntimeException;

	public void afterLoading() throws RuntimeException;

	public void beforeUnload() throws RuntimeException;

	public void unload() throws RuntimeException;

	public void afterUnload() throws RuntimeException;
}
