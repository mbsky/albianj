package org.albianj.controller.config;

public class ControllerServerSettings
{
	private static String host;
	private static int port = 5035;
	private static int backlog = 20;
	private static int receiveBufferSize = 0;
	private static boolean reuseAddress = true;
	private static int timeout = 30;
	private static int report_timespan = 300;
	public static String getHost()
	{
		return host;
	}
	public static void setHost(String host)
	{
		ControllerServerSettings.host = host;
	}
	public static int getPort()
	{
		return port;
	}
	public static void setPort(int port)
	{
		ControllerServerSettings.port = port;
	}
	public static int getBacklog()
	{
		return backlog;
	}
	public static void setBacklog(int backlog)
	{
		ControllerServerSettings.backlog = backlog;
	}
	public static int getReceiveBufferSize()
	{
		return receiveBufferSize;
	}
	public static void setReceiveBufferSize(int receiveBufferSize)
	{
		ControllerServerSettings.receiveBufferSize = receiveBufferSize;
	}
	public static boolean getReuseAddress()
	{
		return reuseAddress;
	}
	public static void setReuseAddress(boolean reuseAddress)
	{
		ControllerServerSettings.reuseAddress = reuseAddress;
	}
	public static int getTimeout()
	{
		return timeout;
	}
	public static void setTimeout(int timeout)
	{
		ControllerServerSettings.timeout = timeout;
	}
	public static int getReport_timespan()
	{
		return report_timespan;
	}
	public static void setReport_timespan(int report_timespan)
	{
		ControllerServerSettings.report_timespan = report_timespan;
	}
	
}
