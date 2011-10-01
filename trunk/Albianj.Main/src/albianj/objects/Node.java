package albianj.objects;

import com.mongodb.BasicDBObject;
import com.mongodb.ReflectionDBObject;

public class Node extends ReflectionDBObject  
{
	private String name;
	private Object value;
	private boolean enable;
	private String forefatherKey;
	private String _id;
	
	public String getId()
	{
		return this._id;
	}
	public void setId(String id)
	{
		this._id = id;
	}
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
	public String getForefatherKey()
	{
		return this.forefatherKey;
	}
	public void setForefatherKey(String forefatherKey)
	{
		this.forefatherKey = forefatherKey;
	}
}
