package org.albianj.logger;

import org.albianj.service.IAlbianService;

public interface IAlbianLoggerService extends IAlbianService
{
	public void error(Object... values);

	public void warn(Object... values);

	public void info(Object... values);

	public void debug(Object... values);

	public String getErrorMsg(Object... values);

	public String getWarnMsg(Object... values);

	public String getInfoMsg(Object... values);

	public String getDebugMsg(Object... values);

}