package org.albianj.logger;

import org.apache.log4j.Logger;

public final class AlbianLogger {
	public final static String ALBIANJ_KERNEL = "albianj_kernel";
	public final static String ALBIANJ_SERVICE = "albianj_service";
	public final static String ALBIANJ_CACHED = "albianj_cached";
	
	public final static Logger KERNEL_LOGGER = Logger.getLogger(AlbianLogger.ALBIANJ_SERVICE);
	public final static Logger SERVICE_LOGGER = Logger.getLogger(AlbianLogger.ALBIANJ_SERVICE);
	public final static Logger CACHED_LOGGER = Logger.getLogger(AlbianLogger.ALBIANJ_CACHED);
}
