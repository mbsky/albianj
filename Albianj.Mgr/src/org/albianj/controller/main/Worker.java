package org.albianj.controller.main;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.albianj.controller.config.ControllerServerSettings;
import org.albianj.datetime.DateTime;
import org.albianj.logger.AlbianLoggerService;
import org.albianj.protocol.Header;
import org.albianj.protocol.ManagementProtocol;
import org.albianj.protocol.ResolveHeader;
import org.albianj.protocol.mgr.ClientIptable;
import org.albianj.protocol.mgr.EngineState;
import org.albianj.protocol.mgr.ServerIptable;
import org.albianj.socket.server.IWork;


public class Worker implements IWork
{
	private static Map<String, ServerIptable> map = Collections
			.synchronizedMap(new LinkedHashMap<String, ServerIptable>());

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
			ClientIptable clientIptable = new ClientIptable(value);
			switch (header.getCommand())
			{
				case ManagementProtocol.REGISTER:
				{
					if (map.containsKey(clientIptable.getKernelId()))
					{
						ServerIptable serverIptable = map.get(clientIptable.getKernelId());
						long timespan = DateTime.getTimeSpan(serverIptable.getLastReportTime(),new Date());
						if(serverIptable.getIp().equals(clientIptable.getIp()) 
								&& serverIptable.getAppName().equals(clientIptable.getAppName()))
						{
							serverIptable.setLastReportTime(new Date());
							serverIptable.setReportTimeSpan(System.currentTimeMillis());
							serverIptable.setSerialId(clientIptable.getSerialId());
							serverIptable.setStartTime(clientIptable.getStartTime());
							serverIptable.setState(clientIptable.getState());
							return ManagementProtocol.SUCCESS;
						}
						else if(timespan > ControllerServerSettings.getReport_timespan())
						{
							AlbianLoggerService.warn("The albianj enginess is Expired.the expired albianj engine is:%s,the new engine is %s.",
									serverIptable.toString(),value);
							ServerIptable newIptable = new ServerIptable(value);
							serverIptable.setStartTime(clientIptable.getStartTime());
							serverIptable.setReportTimeSpan(System.currentTimeMillis());
							map.put(clientIptable.getKernelId(), newIptable);
							return ManagementProtocol.SUCCESS;
						}
						AlbianLoggerService.warn("The %s kerlen id is used.IP:%s.", clientIptable.getKernelId(),clientIptable.getIp());
						return ManagementProtocol.EXCEPTION_REPEAT;
					}
					else
					{
						ServerIptable serverIptable = new ServerIptable(value);
						serverIptable.setLastReportTime(new Date());
						serverIptable.setReportTimeSpan(System.currentTimeMillis());
						map.put(clientIptable.getKernelId(), serverIptable);
						return ManagementProtocol.SUCCESS;
					}
				}
				case ManagementProtocol.REPORT:
				{
					if (!map.containsKey(clientIptable.getKernelId())) 
					{ 
						AlbianLoggerService.warn("The %s kerlen id is no used.", clientIptable.getKernelId());
						return ManagementProtocol.ERROR_UNREGISTER; 
					}
					else
					{
						ServerIptable serverIptable = map.get(clientIptable.getKernelId());
						if(serverIptable.getIp().equals(clientIptable.getIp()) 
								&& serverIptable.getAppName().equals(clientIptable.getAppName()))
						{
							serverIptable.setLastReportTime(new Date());
							serverIptable.setReportTimeSpan(System.currentTimeMillis());
							serverIptable.setSerialId(clientIptable.getSerialId());
							serverIptable.setStartTime(clientIptable.getStartTime());
							serverIptable.setState(clientIptable.getState());
							return ManagementProtocol.SUCCESS;
						}
						AlbianLoggerService.warn("The %s kerlen id is not find used.IP:%s,appName:%s.",
								clientIptable.getKernelId(),clientIptable.getIp(),clientIptable.getAppName());
						return ManagementProtocol.EXCEPTION_REPEAT;
					}
				}
				case ManagementProtocol.LOGOUT:
				{
					if (!map.containsKey(clientIptable.getKernelId())) 
					{ 
						AlbianLoggerService.warn("The %s kerlen id is no used.", clientIptable.getKernelId());
						return ManagementProtocol.ERROR_UNREGISTER; 
					}
					ServerIptable serverIptable = map.get(clientIptable.getKernelId());
					if(serverIptable.getIp().equals(clientIptable.getIp()) 
							&& serverIptable.getAppName().equals(clientIptable.getAppName())
							&&EngineState.Stoped ==  clientIptable.getState())
					{
						map.remove(clientIptable.getKernelId());
						return ManagementProtocol.SUCCESS;
					}
					else
					{
						AlbianLoggerService.warn("The %s kerlen id state is error.", clientIptable.getKernelId());
						return ManagementProtocol.ERROR_UNREGISTER; 
					}
				}
				default:
				{
					AlbianLoggerService.warn("unkown command: %d,the kernel is is %s,ip is %s,appName is %s..",
							header.getCommand(),clientIptable.getKernelId(),clientIptable.getIp(),clientIptable.getAppName());
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
