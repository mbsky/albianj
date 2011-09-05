package org.albianj.protocol.mgr;

import com.google.gson.Gson;

public class Iptable
{
	private String appName;
	private String kernelId;
	private String ip;
	private String startTime;
	private EngineState state = EngineState.Normal;
	
	public Iptable()
	{
		
	}
	
	public Iptable(String json)
	{
		Gson gson = new Gson();
		Iptable iptable = gson.fromJson(json, Iptable.class);
		this.setAppName(iptable.getAppName());
		this.setIp(iptable.getAppName());
		this.setKernelId(iptable.getAppName());
		this.setStartTime(iptable.getStartTime());
		this.setState(iptable.getState());
	}
	public String getAppName()
	{
		return appName;
	}
	public void setAppName(String appName)
	{
		this.appName = appName;
	}
	public String getKernelId()
	{
		return kernelId;
	}
	public void setKernelId(String kernelId)
	{
		this.kernelId = kernelId;
	}
	public String getIp()
	{
		return ip;
	}
	public void setIp(String ip)
	{
		this.ip = ip;
	}
	public String getStartTime()
	{
		return startTime;
	}
	public void setStartTime(String startTime)
	{
		this.startTime = startTime;
	}
	
	public EngineState getState()
	{
		return this.state;
	}
	public void setState(EngineState state)
	{
		this.state = state;
	}
	public String toString()
	{
		Gson gson = new Gson();
		return  gson.toJson(this, Iptable.class);
	}
}
