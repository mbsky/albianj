package org.albianj.config.entity;

import com.mongodb.ReflectionDBObject;


public class Element extends ReflectionDBObject  
{
	private String name;
	private Object value;
	private boolean enable;
	private String forefatherName;
	private String description;
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public Object getValue()
	{
		return value;
	}
	public void setValue(Object value)
	{
		this.value = value;
	}
	public boolean getEnable()
	{
		return enable;
	}
	public void setEnable(boolean enable)
	{
		this.enable = enable;
	}
	public String getForefatherName()
	{
		return this.forefatherName;
	}
	public void setForefatherKey(String forefatherName)
	{
		this.forefatherName = forefatherName;
	}
	public String getDescription()
	{
		return this.description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
}
