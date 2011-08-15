package org.albianj.logger;

import org.albianj.io.Path;
import org.albianj.runtime.IStackTrace;
import org.albianj.runtime.RuningTrace;
import org.albianj.service.AlbianServiceException;
import org.albianj.service.FreeAlbianService;
import org.albianj.verify.Validate;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class AlbianLoggerService extends FreeAlbianService implements
		IAlbianLoggerService
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
			DOMConfigurator.configure(Path
					.getExtendResourcePath("../config/log4j.xml"));
			super.loading();
			albianLogger = Logger.getLogger(ALBIAN_LOGGER);
		}
		catch (Exception exc)
		{
			throw new AlbianServiceException(exc.getMessage(), exc.getCause());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.albianj.logger.IAlbianLoggerService#error(java.lang.String)
	 */
	@Override
	public void error(Object... values)
	{
		if (null == albianLogger) return;
		albianLogger.error(getErrorMsg(values));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.albianj.logger.IAlbianLoggerService#warn(java.lang.String)
	 */
	@Override
	public void warn(Object... values)
	{
		if (null == albianLogger) return;
		albianLogger.warn(getWarnMsg(values));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.albianj.logger.IAlbianLoggerService#info(java.lang.String)
	 */
	@Override
	public void info(Object... values)
	{
		if (null == albianLogger) return;
		albianLogger.info(getInfoMsg(values));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.albianj.logger.IAlbianLoggerService#debug(java.lang.String)
	 */
	@Override
	public void debug(Object... values)
	{
		if (null == albianLogger) return;
		albianLogger.debug(getDebugMsg(values));
	}

	public void error(Exception e, Object... values)
	{
		if (null == albianLogger) return;
		albianLogger.error(getErrorMsg(e, values));
	}

	public void warn(Exception e, Object... values)
	{
		if (null == albianLogger) return;
		albianLogger.warn(getWarnMsg(e, values));
	}

	public void info(Exception e, Object... values)
	{
		if (null == albianLogger) return;
		albianLogger.info(getInfoMsg(e, values));
	}

	public void debug(Exception e, Object... values)
	{
		if (null == albianLogger) return;
		albianLogger.debug(getDebugMsg(e, values));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.albianj.logger.IAlbianLoggerService#getErrorMsg(java.lang.String)
	 */
	@Override
	public String getErrorMsg(Object... values)
	{
		return getMessage(ERROR, values);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.albianj.logger.IAlbianLoggerService#getWarnMsg(java.lang.String)
	 */
	@Override
	public String getWarnMsg(Object... values)
	{
		return getMessage(WARN, values);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.albianj.logger.IAlbianLoggerService#getInfoMsg(java.lang.String)
	 */
	@Override
	public String getInfoMsg(Object... values)
	{
		return getMessage(INFO, values);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.albianj.logger.IAlbianLoggerService#getDebugMsg(java.lang.String)
	 */
	@Override
	public String getDebugMsg(Object... values)
	{
		return getMessage(DEBUG, values);
	}

	public String getErrorMsg(Exception e, Object... values)
	{
		return getMessage(ERROR, e, values);	}

	public String getWarnMsg(Exception e, Object... values)
	{
		return getMessage(WARN, e, values);
	}

	public String getInfoMsg(Exception e, Object... values)
	{
		return getMessage(INFO, e, values);
	}

	public String getDebugMsg(Exception e, Object... values)
	{
		return getMessage(DEBUG, e, values);
	}

	protected String getMessage(String level, Exception e, Object... values)
	{
		IStackTrace trace = RuningTrace.getTraceInfo(e);
		StringBuilder sb = new StringBuilder(level).append(" ")
				.append(trace.toString()).append(e.getMessage()).append(".");
		if (null == values || 0 == values.length) return sb.toString();
		for (Object value : values)
		{
			sb.append(value).append(" ");
		}
		return sb.toString();
	}

	protected String getMessage(String level, Object... values)
	{
		IStackTrace trace = RuningTrace.getTraceInfo();
		StringBuilder sb = new StringBuilder(level).append(" ").append(
				trace.toString());
		if (null == values || 0 == values.length) return sb.toString();
		for (Object value : values)
		{
			sb.append(value).append(" ");
		}
		return sb.toString();
	}

}
