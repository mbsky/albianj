package org.albianj.socket.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import org.albianj.logger.AlbianLoggerService;
import org.albianj.protocol.Header;
import org.albianj.protocol.ManagementProtocol;
import org.albianj.protocol.ResolveHeader;
import org.albianj.protocol.mgr.Iptable;

public class TcpClient
{
	public Socket create(String host, int port, boolean so_keepalive,
			int so_rcvbuf, boolean so_reuseaddr, int so_sndbuf, int so_linger,
			int so_timeout, boolean tcp_nodelay)
	{
		try
		{
			Socket socket = new Socket();
			SocketAddress ip4 = new InetSocketAddress(host, port);
			socket.setKeepAlive(so_keepalive);
			if (0 < so_rcvbuf)
			{
				socket.setReceiveBufferSize(so_rcvbuf);
			}
			socket.setReuseAddress(so_reuseaddr);
			if (0 < so_sndbuf)
			{
				socket.setSendBufferSize(so_sndbuf);
			}
			if (0 < so_linger)
			{
				socket.setSoLinger(true, so_linger);
			}
			if (0 < so_timeout)
			{
				socket.setSoTimeout(so_timeout * 1000);
			}
			socket.setTcpNoDelay(tcp_nodelay);
			socket.connect(ip4);
			return socket;
		}
		catch (Exception e)
		{
			AlbianLoggerService.error(e, "create the client socket is error.");
			return null;
		}
	}
	
	public void regist(Socket socket,Iptable iptable)
	{
		OutputStream out;
		InputStream in;
		try
		{
			Header reqHeader = new Header();
			reqHeader.setCommand(ManagementProtocol.REGISTER);
			String json = iptable.toString();
			byte[] values = json.getBytes();
			reqHeader.setBodyLen(values.length);
			byte[] headers = ResolveHeader.packHeader(reqHeader);
			out = socket.getOutputStream();
			out.write(headers);
			out.write(values);
			out.flush();
			in = socket.getInputStream();
			byte[] respHeaders =new byte[ManagementProtocol.HEADER_LEN];
			in.read(respHeaders);
			Header respHeader =  ResolveHeader.unpackHeader(respHeaders);
			if(ManagementProtocol.SUCCESS != respHeader.getState())
			{
				AlbianLoggerService.error("regist the kernel to mgr service is error.errno:%d.", respHeader.getState());
			}
		}
		catch (IOException e)
		{
			AlbianLoggerService.error(e, "regist the kernel to mgr service is error.");
		}
	}
	
	public void report(Socket socket,Iptable iptable)
	{
		OutputStream out;
		InputStream in;
		try
		{
			Header reqHeader = new Header();
			reqHeader.setCommand(ManagementProtocol.REGISTER);
			String json = iptable.toString();
			byte[] values = json.getBytes();
			reqHeader.setBodyLen(values.length);
			byte[] headers = ResolveHeader.packHeader(reqHeader);
			out = socket.getOutputStream();
			out.write(headers);
			out.write(values);
			out.flush();
			in = socket.getInputStream();
			byte[] respHeaders =new byte[ManagementProtocol.HEADER_LEN];
			in.read(respHeaders);
			Header respHeader =  ResolveHeader.unpackHeader(respHeaders);
			if(ManagementProtocol.SUCCESS != respHeader.getState())
			{
				AlbianLoggerService.error("report the kernel to mgr service is error.errno:%d.", respHeader.getState());
			}
		}
		catch (IOException e)
		{
			AlbianLoggerService.error(e, "report the kernel to mgr service is error.");
		}
	}
	
	public void close(Socket socket)
	{
		try
		{
			socket.close();
		}
		catch (IOException e)
		{
			AlbianLoggerService.error(e, "close the socket when report the kernel to mgr service is error.");
		}
	}
}
