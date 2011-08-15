package org.albianj.logger;

import org.albianj.service.IAlbianService;

public interface IAlbianLoggerService extends IAlbianService
{
	public void error(Object... values);

	public void warn(Object... values);

	public void info(Object... values);

	public void debug(Object... values);

	public void error(Exception e,Object... values);

	public void warn(Exception e,Object... values);

	public void info(Exception e,Object... values);

	public void debug(Exception e,Object... values);
	
	public String getErrorMsg(Object... values);

	public String getWarnMsg(Object... values);

	public String getInfoMsg(Object... values);

	public String getDebugMsg(Object... values);

	public String getErrorMsg(Exception e,Object... values);
	
	public String getWarnMsg(Exception e,Object... values);
	
	public String getInfoMsg(Exception e,Object... values);
	
	public String getDebugMsg(Exception e,Object... values);

}