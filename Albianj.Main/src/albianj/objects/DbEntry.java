package albianj.objects;

import java.util.Map;
import java.util.Set;

import org.bson.BSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class DbEntry extends BasicDBObject
{
	private String name;
	private Object value;
	private boolean enable;
	private Map<String,Node> childNodes;
	
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
	public Map<String,Node> getChildNodes()
	{
		return childNodes;
	}
	public void setChildNodes(Map<String,Node> childNodes)
	{
		this.childNodes = childNodes;
	}
}