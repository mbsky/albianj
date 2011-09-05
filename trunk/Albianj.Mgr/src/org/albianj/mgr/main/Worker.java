package org.albianj.mgr.main;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.albianj.logger.AlbianLoggerService;
import org.albianj.protocol.Header;
import org.albianj.protocol.ManagementProtocol;
import org.albianj.protocol.ResolveHeader;
import org.albianj.protocol.mgr.Iptable;
import org.albianj.socket.server.IWork;


public class Worker implements IWork
{
	private static Map<String, Iptable> map = Collections
			.synchronizedMap(new LinkedHashMap<String, Iptable>());

	@Override
	public void deal(Socket socket)
	{
		if (null == socket)
		{
			AlbianLoggerService.warn("The recv socket is null.");
			return;
		}
		Header respHeader = new Header();
		respHeader.setBodyLen(0);
		respHeader.setCommand(ManagementProtocol.RESP);

		InputStream in;
		Header header;
		try
		{
			do
			{
				in = socket.getInputStream();
				header = dealHeader(in);
				if (null == header)
				{
					respHeader.setState(ManagementProtocol.ERROR_READ_HEADER);
					break;
				}
				byte state = dealBody(in,header);
				if(ManagementProtocol.SUCCESS != state)
				{
					respHeader.setState(ManagementProtocol.ERROR_READ_BODY);
					break;
				}
				resp(socket.getOutputStream(),respHeader);
			} while (false);

		}
		catch (Exception e)
		{
			AlbianLoggerService.error(e, "get the header is error.");
		}
		finally
		{
			try
			{
				socket.close();
			}
			catch (IOException e)
			{
				AlbianLoggerService.error(e, "close the socket is error.");
			}
		}
	}

	protected Header dealHeader(InputStream in)
	{
		if (null == in)
		{
			AlbianLoggerService.error("the recv input stream is null.");
			return null;
		}
		byte[] headers = new byte[ManagementProtocol.HEADER_LEN];

		try
		{
			int len = 0;
			len = in.read(headers);
			if (len != ManagementProtocol.HEADER_LEN)
			{
				AlbianLoggerService.error("recv the header is error.");
				return null;
			}
			return ResolveHeader.unpackHeader(headers);
		}
		catch (Exception e)
		{
			AlbianLoggerService.error(e, "recv the header is error.");
			return null;
		}
	}

	protected byte dealBody(InputStream in, Header header)
	{
		byte[] bodys = new byte[(int) header.getBodyLen()];

		try
		{
			int len = 0;
			len = in.read(bodys);
			if (len != ManagementProtocol.HEADER_LEN)
			{
				AlbianLoggerService.error("recv the body is error.");
				return ManagementProtocol.ERROR_READ_BODY;
			}
			String value = new String(bodys);
			Iptable iptable = new Iptable(value);
			switch (header.getCommand())
			{
				case ManagementProtocol.REGISTER:
				{
					if (map.containsKey(iptable.getKernelId()))
					{
						AlbianLoggerService.warn("The %s kerlen id is used.IP:%s.", iptable.getKernelId(),iptable.getIp());
						return ManagementProtocol.EXCEPTION_REPEAT;
					}
					else
					{
						map.put(iptable.getKernelId(), iptable);
						return ManagementProtocol.SUCCESS;
					}
				}
				case ManagementProtocol.REPORT:
				{
					if (!map.containsKey(iptable.getKernelId())) 
					{ 
						AlbianLoggerService.warn("The %s kerlen id is no used.", iptable.getKernelId());
						return ManagementProtocol.ERROR_UNREGISTER; 
					}
					map.put(iptable.getKernelId(), iptable);
					return ManagementProtocol.SUCCESS;
				}
				default:
				{
					AlbianLoggerService.warn("unkown command: %d,the kernel is is %s,ip is %s,appName is %s..",
							header.getCommand(),iptable.getKernelId(),iptable.getIp(),iptable.getAppName());
					return ManagementProtocol.ERROR_UNKOWN_COMMAND;
				}
			}
		}
		catch (Exception e)
		{
			AlbianLoggerService.error(e, "recv the header is error.");
			return ManagementProtocol.ERROR_READ_BODY;
		}
	}

	protected void resp(OutputStream out,Header respHeader)
	{
		byte[] headers =ResolveHeader.packHeader(respHeader.getCommand(), respHeader.getBodyLen(), respHeader.getState());
		try
		{
			out.write(headers);
			out.flush();
			
			return;
		}
		catch (Exception e)
		{
			AlbianLoggerService.error(e, "send the header is error.");
			return;
		}
	}
}
