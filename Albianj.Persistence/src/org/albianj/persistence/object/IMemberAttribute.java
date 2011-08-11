package org.albianj.persistence.object;

public interface IMemberAttribute
{
//	Name="Id"
//			FieldName="BizOfferId" AllowNull="false" 
//			Length="32" PrimaryKey="true"
//			DbType="string" IsSave="true"/>
	
	public String getName();
	public void setName(String name);
	public String getFieldName();
	public void setFieldName(String fieldName);
	public boolean getAllowNull();
	public void setAllowNull(boolean allowNull);
	public int getLength();
	public void setLength(int length);
	public boolean getPrimaryKey();
	public void setPrimaryKey(boolean primaryKey);
	public int getDatabaseType();
	public void setDatabaseType(int databaseType);
	public boolean getIsSave();
	public void setIsSave(boolean isSave);
}
