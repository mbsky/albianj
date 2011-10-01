package org.albianj.config.entity;

public class ConfigServerAttribute implements IConfigServerAttribute
{

	private String address;
	private int port;
	private String userName;
	private String password;
	private boolean enable = true;
	
	@Override
	public String getAddress()
	{
		return this.address;
	}

	@Override
	public void setAddress(String address)
	{
		this.address = address;
	}

	@Override
	public int getPort()
	{
		return this.port;
	}

	@Override
	public void setPort(int port)
	{
		this.port = port;

	}

	@Override
	public String getUserName()
	{
		return this.userName;
	}

	@Override
	public void setUserName(String userName)
	{
		this.userName = userName;

	}

	@Override
	public String getPassword()
	{
		return this.password;
	}

	@Override
	public void setPassword(String password)
	{
		this.password = password;

	}

	public boolean getEnable()
	{
		return this.enable;
	}
	public void setEnable(boolean enable)
	{
		this.enable = enable;
	}
}
