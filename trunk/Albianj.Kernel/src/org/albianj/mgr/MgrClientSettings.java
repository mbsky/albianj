package org.albianj.mgr;

public class MgrClientSettings
{
	private static String host;
	private static int port = 5035;
	private static boolean keepalive = true;
	private static int receiveBufferSize = 0;
	private static boolean reuseAddress = true;
	private static int sendBufferSize = 0;
	private static int soLinger = 30;
	private static int soTimeout = 30;
	private static boolean tcpNoDelay = true;
	private static int report_timespan = 300; 

	public static String getHost()
	{
		return host;
	}
	public static void setHost(String host)
	{
		MgrClientSettings.host = host;
	}
	public static int getPort()
	{
		return port;
	}
	public static void setPort(int port)
	{
		MgrClientSettings.port = port;
	}
	public static boolean getKeepalive()
	{
		return keepalive;
	}
	public static void setKeepalive(boolean keepalive)
	{
		MgrClientSettings.keepalive = keepalive;
	}
	public static int getReceiveBufferSize()
	{
		return receiveBufferSize;
	}
	public static void setReceiveBufferSize(int receiveBufferSize)
	{
		MgrClientSettings.receiveBufferSize = receiveBufferSize;
	}
	public static boolean getReuseAddress()
	{
		return reuseAddress;
	}
	public static void setReuseAddress(boolean reuseAddress)
	{
		MgrClientSettings.reuseAddress = reuseAddress;
	}
	public static int getSendBufferSize()
	{
		return sendBufferSize;
	}
	public static void setSendBufferSize(int sendBufferSize)
	{
		MgrClientSettings.sendBufferSize = sendBufferSize;
	}
	public static int getSoLinger()
	{
		return soLinger;
	}
	public static void setSoLinger(int soLinger)
	{
		MgrClientSettings.soLinger = soLinger;
	}
	public static int getSoTimeout()
	{
		return soTimeout;
	}
	public static void setSoTimeout(int soTimeout)
	{
		MgrClientSettings.soTimeout = soTimeout;
	}
	public static boolean getTcpNoDelay()
	{
		return tcpNoDelay;
	}
	public static void setTcpNoDelay(boolean tcpNoDelay)
	{
		MgrClientSettings.tcpNoDelay = tcpNoDelay;
	}
	public static void setReport_timespan(int report_timespan)
	{
		MgrClientSettings.report_timespan = report_timespan;
	}
	public static int getReport_timespan()
	{
		return report_timespan;
	}

}
