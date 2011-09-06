package org.albianj.mgr.main;

import java.net.ServerSocket;

import org.albianj.Controller.config1.MgrServerSettings;
import org.albianj.kernel.AlbianBootService;
import org.albianj.kernel.AlbianState;
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
			ServerSocket socket = tcpServer.create(MgrServerSettings.getHost(), MgrServerSettings.getPort(), MgrServerSettings.getBacklog(),
					MgrServerSettings.getReceiveBufferSize(), MgrServerSettings.getReuseAddress(), MgrServerSettings.getTimeout());
			tcpServer.accept(socket,new Worker());		
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
