package org.albianj.logger;

import org.albianj.service.IAlbianService;

public interface IAlbianLoggerService extends IAlbianService
{
	public void error(String... values);

	public void warn(String... values);

	public void info(String... values);

	public void debug(String... values);

	public String getErrorMsg(String... values);

	public String getWarnMsg(String... values);

	public String getInfoMsg(String... values);

	public String getDebugMsg(String... values);

}