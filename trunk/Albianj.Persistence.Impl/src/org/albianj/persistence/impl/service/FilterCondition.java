package org.albianj.persistence.impl.service;

import org.albianj.persistence.impl.db.LogicalOperation;
import org.albianj.persistence.impl.db.RelationalOperator;

public class FilterCondition implements IFilterCondition
{
	private RelationalOperator relationalOperator = RelationalOperator.And;
	private String fieldName = null;
	private Class<?> fieldClass = String.class;
	private LogicalOperation logicalOperation = LogicalOperation.Equal;
	
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
}
