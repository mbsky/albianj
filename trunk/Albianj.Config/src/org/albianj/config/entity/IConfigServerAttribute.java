package org.albianj.config.entity;

public interface IConfigServerAttribute
{
	public String getAddress();
	public void setAddress(String address);
	public int getPort();
	public void setPort(int port);
	public String getUserName();
	public void setUserName(String userName);
	public String getPassword();
	public void setPassword(String password);
	public boolean getEnable();
	public void setEnable(boolean enable);
}
