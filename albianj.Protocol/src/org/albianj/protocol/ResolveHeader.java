package org.albianj.protocol;

import java.util.Arrays;

public class ResolveHeader
{
	public static byte[] packHeader(byte cmd, long bodylen, byte state)
	{
		byte[] header;
		byte[] hex_len;
		
		header = new byte[ManagementProtocol.HEADER_LEN];
		Arrays.fill(header, (byte)0);
		header[ManagementProtocol.HEADER_CMD_INDEX] = cmd;
		hex_len = Convert.long2buff(bodylen);
		System.arraycopy(hex_len, 0, header, ManagementProtocol.HEADER_BODYLEN_INDEX, hex_len.length);
		header[ManagementProtocol.HEADER_STATE_INDEX] = state;
		return header;
	}
	
	public static byte[] packHeader(Header header)
	{
		byte[] headers;
		byte[] hex_len;
		
		headers = new byte[ManagementProtocol.HEADER_LEN];
		Arrays.fill(headers, (byte)0);
		headers[ManagementProtocol.HEADER_CMD_INDEX] = header.getCommand();
		hex_len = Convert.long2buff(header.getBodyLen());
		System.arraycopy(hex_len, 0, header, ManagementProtocol.HEADER_BODYLEN_INDEX, hex_len.length);
		headers[ManagementProtocol.HEADER_STATE_INDEX] = header.getState();
		return headers;
	}
	
	public static Header unpackHeader(byte[] headers)
	{
//		try
//		{
			Header header = new Header();
			header.setCommand(headers[ManagementProtocol.HEADER_CMD_INDEX]);
			header.setBodyLen(Convert.buff2long(headers,ManagementProtocol.HEADER_BODYLEN_INDEX));
			header.setState(headers[ManagementProtocol.HEADER_STATE_INDEX]);
			return header;			
//		}
//		catch (Exception e)
//		{
//			AlbianLoggerService.error(e, "convert strean to bytes is error.");
//			return null;
//		}
	}
}
