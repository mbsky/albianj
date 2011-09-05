package org.albianj.mgr;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;

import org.albianj.datetime.DateTime;
import org.albianj.io.Path;
import org.albianj.kernel.AlbianBootService;
import org.albianj.kernel.AlbianState;
import org.albianj.kernel.KernelSetting;
import org.albianj.logger.AlbianLoggerService;
import org.albianj.protocol.mgr.EngineState;
import org.albianj.protocol.mgr.Iptable;
import org.albianj.service.FreeAlbianService;
import org.albianj.service.parser.PropertiesParser;
import org.albianj.socket.client.TcpClient;
import org.albianj.verify.Validate;
import org.albianj.xml.IParser;

import com.google.code.yanf4j.nio.TCPController;

public class MgrService extends FreeAlbianService implements IMgrService
{
	private final static String path = "../config/mgr.properties";

	public void init()
	{
		try
		{
			Properties props = PropertiesParser.load(Path
					.getExtendResourcePath(path));
			parser(props);
		}
		catch (Exception e)
		{
			AlbianLoggerService.error(e,
					"load the mgr properties file is error.");
			throw new RuntimeException(e);
		}
	}

	public void parser(Properties props)
	{
		String host = PropertiesParser.getValue(props, "host");
		if (Validate.isNullOrEmptyOrAllSpace(host))
		{
			AlbianLoggerService.error("the mgr host is null or empty/");
			throw new RuntimeException("the mgr host is null or empty.");
		}
		String port = PropertiesParser.getValue(props, "port");
		String so_keepalive = PropertiesParser.getValue(props, "so_keepalive");
		String so_rcvbuf = PropertiesParser.getValue(props, "so_rcvbuf");
		String so_reuseaddr = PropertiesParser.getValue(props, "so_reuseaddr");
		String so_sndbuf = PropertiesParser.getValue(props, "so_sndbuf");
		String so_linger = PropertiesParser.getValue(props, "so_linger");
		String so_timeout = PropertiesParser.getValue(props, "so_timeout");
		String tcp_nodelay = PropertiesParser.getValue(props, "tcp_nodelay");

		MgrSettings.setHost(host);
		if (!Validate.isNullOrEmptyOrAllSpace(port))
		{
			MgrSettings.setPort(new Integer(port));
		}
		if (!Validate.isNullOrEmptyOrAllSpace(so_keepalive))
		{
			MgrSettings.setKeepalive(new Boolean(so_keepalive));
		}
		if (!Validate.isNullOrEmptyOrAllSpace(so_rcvbuf))
		{
			MgrSettings.setReceiveBufferSize(new Integer(so_rcvbuf));
		}
		if (!Validate.isNullOrEmptyOrAllSpace(so_reuseaddr))
		{
			MgrSettings.setReuseAddress(new Boolean(so_reuseaddr));
		}
		if (!Validate.isNullOrEmptyOrAllSpace(so_sndbuf))
		{
			MgrSettings.setSendBufferSize(new Integer(so_sndbuf));
		}
		if (!Validate.isNullOrEmptyOrAllSpace(so_linger))
		{
			MgrSettings.setSoLinger(new Integer(so_linger));
		}
		if (!Validate.isNullOrEmptyOrAllSpace(so_timeout))
		{
			MgrSettings.setSoTimeout(new Integer(so_timeout));
		}
		if (!Validate.isNullOrEmptyOrAllSpace(tcp_nodelay))
		{
			MgrSettings.setTcpNoDelay(new Boolean(tcp_nodelay));
		}
	}

	@Override
	public void loading() throws RuntimeException
	{
		init();
		regedit();
		super.loading();
	}

	@Override
	public void afterLoading() throws RuntimeException
	{
		report();
	}

	@Override
	public void unload()
	{
		unloading();
	}

	public void regedit()
	{
		Iptable table = new Iptable();
		table.setAppName(KernelSetting.getAppName());
		TcpClient client = new TcpClient();
		Socket socket = null;
		try
		{
			InetAddress ip4 = Inet4Address.getLocalHost();
			table.setIp(ip4.getHostAddress());
			table.setKernelId(KernelSetting.getKernelId());
			table.setStartTime(DateTime.getDateTimeString());
			table.setState(EngineState.Starting);

			socket = client.create(MgrSettings.getHost(),
					MgrSettings.getPort(), MgrSettings.getKeepalive(),
					MgrSettings.getReceiveBufferSize(),
					MgrSettings.getReuseAddress(),
					MgrSettings.getSendBufferSize(), MgrSettings.getSoLinger(),
					MgrSettings.getSoTimeout(), MgrSettings.getTcpNoDelay());
			client.regist(socket, table);
		}
		catch (UnknownHostException e)
		{
			AlbianLoggerService.warn(e, "regedit the albianj state is error.");
		}
		finally
		{
			if (null != socket) client.close(socket);
		}

	}

	public void report()
	{
		Runnable run = new Runnable()
		{
			public void run()
			{
				while (true)
				{
					if (AlbianState.Running != AlbianBootService.getLifeState())
					{
						unload();
						break;
					}
					Iptable table = new Iptable();
					table.setAppName(KernelSetting.getAppName());
					TcpClient client = new TcpClient();
					Socket socket = null;
					try
					{
						InetAddress ip4 = Inet4Address.getLocalHost();
						table.setIp(ip4.getHostAddress());
						table.setKernelId(KernelSetting.getKernelId());
						table.setStartTime(DateTime.getDateTimeString());
						table.setState(EngineState.Runing);

						socket = client.create(MgrSettings.getHost(),
								MgrSettings.getPort(),
								MgrSettings.getKeepalive(),
								MgrSettings.getReceiveBufferSize(),
								MgrSettings.getReuseAddress(),
								MgrSettings.getSendBufferSize(),
								MgrSettings.getSoLinger(),
								MgrSettings.getSoTimeout(),
								MgrSettings.getTcpNoDelay());
						client.regist(socket, table);
					}
					catch (UnknownHostException e)
					{
						AlbianLoggerService.warn(e,
								"report the  albianj state is error.");
					}
					finally
					{
						if (null != socket) client.close(socket);
					}
					try
					{
						Thread.sleep(300 * 1000);
					}
					catch (InterruptedException e)
					{
						AlbianLoggerService
								.error(e,
										"sleep the thread is error when report the  albianj state.");
					}
				}
			}
		};
		run.run();
	}

	public void unloading()
	{
		Iptable table = new Iptable();
		table.setAppName(KernelSetting.getAppName());
		TcpClient client = new TcpClient();
		Socket socket = null;
		try
		{
			InetAddress ip4 = Inet4Address.getLocalHost();
			table.setIp(ip4.getHostAddress());
			table.setKernelId(KernelSetting.getKernelId());
			table.setStartTime(DateTime.getDateTimeString());
			table.setState(EngineState.Stoped);

			socket = client.create(MgrSettings.getHost(),
					MgrSettings.getPort(), MgrSettings.getKeepalive(),
					MgrSettings.getReceiveBufferSize(),
					MgrSettings.getReuseAddress(),
					MgrSettings.getSendBufferSize(), MgrSettings.getSoLinger(),
					MgrSettings.getSoTimeout(), MgrSettings.getTcpNoDelay());
			client.regist(socket, table);
		}
		catch (UnknownHostException e)
		{
			AlbianLoggerService.warn(e, "report the  albianj state is error.");
		}
		finally
		{
			if (null != socket) client.close(socket);
		}
	}
}
