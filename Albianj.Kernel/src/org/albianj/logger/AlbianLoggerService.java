package org.albianj.logger;

import org.albianj.io.Path;
import org.albianj.service.AlbianServiceException;
import org.albianj.service.FreeAlbianService;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class AlbianLoggerService extends FreeAlbianService implements IAlbianLoggerService
{
	public static final String ERROR = "!";
	public static final String WARN = "@";
	public static final String INFO = "$";
	public static final String DEBUG = "*";
	
	private final static String ALBIAN_LOGGER = "albian_logger";
	private Logger albianLogger;
	
	@Override
	public void loading() throws AlbianServiceException
	{
		try
		{
			DOMConfigurator.configure(Path.getExtendResourcePath("../config/log4j.xml"));
			super.loading();
			albianLogger = Logger.getLogger(ALBIAN_LOGGER);
		}
		catch(Exception exc)
		{
			throw new AlbianServiceException(exc.getMessage(),exc.getCause());
		}
	}
	
	/* (non-Javadoc)
	 * @see org.albianj.logger.IAlbianLoggerService#error(java.lang.String)
	 */
	@Override
	public void error(Object...values)
	{
		if(null == albianLogger) return;
		albianLogger.error(getErrorMsg(values));
	}
	/* (non-Javadoc)
	 * @see org.albianj.logger.IAlbianLoggerService#warn(java.lang.String)
	 */
	@Override
	public void warn(Object...values)
	{
		if(null == albianLogger) return;
		albianLogger.warn(getWarnMsg(values));
	}
	/* (non-Javadoc)
	 * @see org.albianj.logger.IAlbianLoggerService#info(java.lang.String)
	 */
	@Override
	public void info(Object...values)
	{
		if(null == albianLogger) return;
		albianLogger.info(getInfoMsg(values));
	}
	/* (non-Javadoc)
	 * @see org.albianj.logger.IAlbianLoggerService#debug(java.lang.String)
	 */
	@Override
	public void debug(Object...values)
	{
		if(null == albianLogger) return;
		albianLogger.debug(getDebugMsg(values));
	}
	
	/* (non-Javadoc)
	 * @see org.albianj.logger.IAlbianLoggerService#getErrorMsg(java.lang.String)
	 */
	@Override
	public String getErrorMsg(Object...values)
	{
		return getMessage(ERROR,values);
	}
	
	/* (non-Javadoc)
	 * @see org.albianj.logger.IAlbianLoggerService#getWarnMsg(java.lang.String)
	 */
	@Override
	public String getWarnMsg(Object...values)
	{
		return getMessage(WARN,values);
	}
	
	/* (non-Javadoc)
	 * @see org.albianj.logger.IAlbianLoggerService#getInfoMsg(java.lang.String)
	 */
	@Override
	public String getInfoMsg(Object...values)
	{
		return getMessage(INFO,values);
	}
	
	/* (non-Javadoc)
	 * @see org.albianj.logger.IAlbianLoggerService#getDebugMsg(java.lang.String)
	 */
	@Override
	public String getDebugMsg(Object...values)
	{
		return getMessage(DEBUG,values);
	}
	
	protected String getMessage(String level,Object...values)
	{
		StringBuilder sb = new StringBuilder(level).append(" ");
		for(Object value : values)
		{
			sb.append(value).append(" ");
		}
		return sb.toString();
	}
	
	
	
	
	
}
