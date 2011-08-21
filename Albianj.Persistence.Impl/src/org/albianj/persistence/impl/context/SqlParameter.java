package org.albianj.persistence.impl.context;

import java.sql.Types;

public class SqlParameter implements ISqlParameter
{
	private int sqlType = Types.NVARCHAR;
	private String name = null;
	private Object value = null;
//	private Class valueClass;
//	private int length = 200;
	
	public int getSqlType()
	{
		return sqlType;
	}
	public void setSqlType(int sqlType)
	{
		this.sqlType = sqlType;
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
//	public Class getValueClass()
//	{
//		return valueClass;
//	}
//	public void setValueClass(Class<?> valueClass)
//	{
//		this.valueClass = valueClass;
//	}
//	public int getLength()
//	{
//		return length;
//	}
//	public void setLength(int length)
//	{
//		this.length = length;
//	}
}
