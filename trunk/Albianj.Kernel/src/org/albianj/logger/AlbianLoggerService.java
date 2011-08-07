package org.albianj.logger;

import org.albianj.io.Path;
import org.albianj.service.AlbianServiceException;
import org.albianj.service.FreeAlbianService;
import org.apache.log4j.xml.DOMConfigurator;

public class AlbianLoggerService extends FreeAlbianService
{
	public static final String ERROR = "~";
	public static final String WARN = "!";
	public static final String INFO = "*";
	public static final String DEBUG = "@";
	
	@Override
	public void loading() throws AlbianServiceException
	{
		try
		{

			DOMConfigurator.configure(Path.getExtendResourcePath("../config/log4j.xml"));
			super.loading();
		}
		catch(Exception exc)
		{
			throw new AlbianServiceException(exc.getMessage(),exc.getCause());
		}
	}
}
