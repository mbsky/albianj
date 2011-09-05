package org.albianj.mgr.config;

public class MgrSettings
{
	private static String host;
	private static int port = 5035;
	private static int backlog = 20;
	private static int receiveBufferSize = 0;
	private static boolean reuseAddress = true;
	private static int timeout = 30;
	
	public static String getHost()
	{
		return host;
	}
	public static void setHost(String host)
	{
		MgrSettings.host = host;
	}
	public static int getPort()
	{
		return port;
	}
	public static void setPort(int port)
	{
		MgrSettings.port = port;
	}
	public static int getBacklog()
	{
		return backlog;
	}
	public static void setBacklog(int backlog)
	{
		MgrSettings.backlog = backlog;
	}
	public static int getReceiveBufferSize()
	{
		return receiveBufferSize;
	}
	public static void setReceiveBufferSize(int receiveBufferSize)
	{
		MgrSettings.receiveBufferSize = receiveBufferSize;
	}
	public static boolean getReuseAddress()
	{
		return reuseAddress;
	}
	public static void setReuseAddress(boolean reuseAddress)
	{
		MgrSettings.reuseAddress = reuseAddress;
	}
	public static int getTimeout()
	{
		return timeout;
	}
	public static void setTimeout(int timeout)
	{
		MgrSettings.timeout = timeout;
	}
	
	
}
