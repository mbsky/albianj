package org.albianj.persistence.impl.context;

public interface ISqlParameter
{
	public int getSqlType();
	public void setSqlType(int sqlType);
	public String getName();
	public void setName(String name);
	public Object getValue();
	public void setValue(Object value);
//	public Class getValueClass();
//	public void setValueClass(Class<?> valueClass);
//	public int getLength();
//	public void setLength(int length);
}
