package org.albianj.mgr;

public class MgrSettings
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
	public static boolean getKeepalive()
	{
		return keepalive;
	}
	public static void setKeepalive(boolean keepalive)
	{
		MgrSettings.keepalive = keepalive;
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
	public static int getSendBufferSize()
	{
		return sendBufferSize;
	}
	public static void setSendBufferSize(int sendBufferSize)
	{
		MgrSettings.sendBufferSize = sendBufferSize;
	}
	public static int getSoLinger()
	{
		return soLinger;
	}
	public static void setSoLinger(int soLinger)
	{
		MgrSettings.soLinger = soLinger;
	}
	public static int getSoTimeout()
	{
		return soTimeout;
	}
	public static void setSoTimeout(int soTimeout)
	{
		MgrSettings.soTimeout = soTimeout;
	}
	public static boolean getTcpNoDelay()
	{
		return tcpNoDelay;
	}
	public static void setTcpNoDelay(boolean tcpNoDelay)
	{
		MgrSettings.tcpNoDelay = tcpNoDelay;
	}
	

}
