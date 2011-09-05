package org.albianj.protocol;

public class Header
{
	private byte command;
	private long bodyLen;
	private byte state = 0;
	
	public byte getCommand()
	{
		return command;
	}
	public void setCommand(byte command)
	{
		this.command = command;
	}
	public long getBodyLen()
	{
		return bodyLen;
	}
	public void setBodyLen(long bodyLen)
	{
		this.bodyLen = bodyLen;
	}
	public byte getState()
	{
		return state;
	}
	public void setState(byte state)
	{
		this.state = state;
	}
	
}
