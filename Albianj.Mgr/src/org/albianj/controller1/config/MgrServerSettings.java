package org.albianj.controller1.config;

public class MgrServerSettings
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
		MgrServerSettings.host = host;
	}
	public static int getPort()
	{
		return port;
	}
	public static void setPort(int port)
	{
		MgrServerSettings.port = port;
	}
	public static int getBacklog()
	{
		return backlog;
	}
	public static void setBacklog(int backlog)
	{
		MgrServerSettings.backlog = backlog;
	}
	public static int getReceiveBufferSize()
	{
		return receiveBufferSize;
	}
	public static void setReceiveBufferSize(int receiveBufferSize)
	{
		MgrServerSettings.receiveBufferSize = receiveBufferSize;
	}
	public static boolean getReuseAddress()
	{
		return reuseAddress;
	}
	public static void setReuseAddress(boolean reuseAddress)
	{
		MgrServerSettings.reuseAddress = reuseAddress;
	}
	public static int getTimeout()
	{
		return timeout;
	}
	public static void setTimeout(int timeout)
	{
		MgrServerSettings.timeout = timeout;
	}
	public static int getReport_timespan()
	{
		return report_timespan;
	}
	public static void setReport_timespan(int report_timespan)
	{
		MgrServerSettings.report_timespan = report_timespan;
	}
	
}
