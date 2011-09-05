package org.albianj.mgr.main;

import java.net.ServerSocket;

import org.albianj.kernel.AlbianBootService;
import org.albianj.kernel.AlbianState;
import org.albianj.mgr.config.MgrSettings;
import org.albianj.socket.server.TcpServer;


public class AlbianjMgr
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			AlbianBootService.start();
			while (AlbianState.Running != AlbianBootService.getLifeState())
			{
				Thread.sleep(1000);
			}
			TcpServer tcpServer = new TcpServer();
			ServerSocket socket = tcpServer.create(MgrSettings.getHost(), MgrSettings.getPort(), MgrSettings.getBacklog(),
					MgrSettings.getReceiveBufferSize(), MgrSettings.getReuseAddress(), MgrSettings.getTimeout());
			tcpServer.accept(socket,new Worker());		
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
