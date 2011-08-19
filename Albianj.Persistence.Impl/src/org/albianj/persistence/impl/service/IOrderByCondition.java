package org.albianj.persistence.impl.service;

import org.albianj.persistence.impl.context.SortStyle;

public interface IOrderByCondition
{
	public String getFieldName();
	public void setFieldName(String fieldName);
	public SortStyle getSortStyle();
	public void setSortStyle(SortStyle sortStyle);
}