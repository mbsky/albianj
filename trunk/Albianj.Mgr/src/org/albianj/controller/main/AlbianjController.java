package org.albianj.controller.main;

import java.net.ServerSocket;

import org.albianj.controller.config.ControllerServerSettings;
import org.albianj.kernel.AlbianBootService;
import org.albianj.kernel.AlbianState;
import org.albianj.socket.server.TcpServer;


public class AlbianjController
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
			ServerSocket socket = tcpServer.create(ControllerServerSettings.getHost(), ControllerServerSettings.getPort(), ControllerServerSettings.getBacklog(),
					ControllerServerSettings.getReceiveBufferSize(), ControllerServerSettings.getReuseAddress(), ControllerServerSettings.getTimeout());
			tcpServer.accept(socket,new Worker());		
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
