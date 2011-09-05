package org.albianj.socket.server;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.albianj.concurrent.IThreadPoolService;
import org.albianj.kernel.AlbianServiceRouter;
import org.albianj.logger.AlbianLoggerService;
import org.albianj.verify.Validate;

public class TcpServer
{
	public ServerSocket create(String host, int port, int backlog,
			int receiveBufferSize, boolean reuseAddress, int timeout)
	{
		try
		{
			ServerSocket server = new ServerSocket();
			InetAddress ip4;
			if(Validate.isNullOrEmptyOrAllSpace(host))
			{
				ip4 = Inet4Address.getLocalHost();
			}
			else
			{
				ip4 = Inet4Address.getByName(host);
			}
			InetSocketAddress addreee = new InetSocketAddress(ip4, port);
			if(0 < receiveBufferSize)
			{
			server.setReceiveBufferSize(receiveBufferSize);
			}
			server.setReuseAddress(reuseAddress);
			if(0 < timeout)
			{
				server.setSoTimeout(timeout * 1000);
			}
			if(0 < backlog)
			{
				server.bind(addreee, backlog);
			}
			else
			{
				server.bind(addreee);
			}
			return server;
		}
		catch (Exception e)
		{
			AlbianLoggerService.error(e, "create the server socket is error.");
			return null;
		}
	}

	public void accept(ServerSocket server,final IWork work)
	{
		if (null == server || server.isClosed())
		{
			AlbianLoggerService.warn("the socket is null or close.");
			return;
		}
		while (true)
		{
			try
			{
				final Socket socket = server.accept();
				IThreadPoolService thread= AlbianServiceRouter.getService(IThreadPoolService.class, "ThreadPool");
				if(null != thread)
				{
					thread.execute(new Runnable()
					{
						
						@Override
						public void run()
						{
							work.deal(socket);
						}
					});
				}
				else
				{
					work.deal(socket);
				}
			}
			catch (Exception e)
			{
				AlbianLoggerService.warn(e,"accept is error.");
			}
		}
	}
}
