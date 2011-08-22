package org.albianj.persistence.impl.service;

import org.albianj.persistence.impl.db.LogicalOperation;
import org.albianj.persistence.impl.db.RelationalOperator;

public interface IFilterCondition
{
	public RelationalOperator getRelationalOperator();
	public void setRelationalOperator(RelationalOperator relationalOperator);
	public String getFieldName();
	public void setFieldName(String fieldName);
	public Class<?> getFieldClass();
	public void setFieldClass(Class<?> cls);
	public LogicalOperation getLogicalOperation();
	public void setLogicalOperation(LogicalOperation logicalOperation);
	
}
