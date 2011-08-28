package org.albianj.persistence.impl.service;

import org.albianj.persistence.object.IFilterCondition;
import org.albianj.persistence.object.LogicalOperation;
import org.albianj.persistence.object.RelationalOperator;

public class FilterCondition implements IFilterCondition
{
	private RelationalOperator relationalOperator = RelationalOperator.And;
	private String fieldName = null;
	private Class<?> fieldClass = String.class;
	private LogicalOperation logicalOperation = LogicalOperation.Equal;
	private Object value = null;
	

	public RelationalOperator getRelationalOperator()
	{
		return relationalOperator;
	}
	public void setRelationalOperator(RelationalOperator relationalOperator)
	{
		this.relationalOperator = relationalOperator;
	}
	public String getFieldName()
	{
		return fieldName;
	}
	public void setFieldName(String fieldName)
	{
		this.fieldName = fieldName;
	}
	public Class<?> getFieldClass()
	{
		return fieldClass;
	}
	public void setFieldClass(Class<?> fieldClass)
	{
		this.fieldClass = fieldClass;
	}
	public LogicalOperation getLogicalOperation()
	{
		return logicalOperation;
	}
	public void setLogicalOperation(LogicalOperation logicalOperation)
	{
		this.logicalOperation = logicalOperation;
	}
	public Object getValue()
	{
		return this.value;
	}
	
	public void setValue(Object value)
	{
		this.value = value;
	}
}
