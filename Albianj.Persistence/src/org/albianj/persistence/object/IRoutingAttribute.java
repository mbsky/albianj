package org.albianj.persistence.object;

public interface IRoutingAttribute
{
//	<Routing Name="IdRouting" StorageName="2thStorage" 
//			TableName="BizOfferById" Owner="dbo" Permission="WR"></Routing>
	
	public String getName();
	public void setName(String name);
	public String getStorageName();
	public void setStorageName(String storageName);
	public String getTableName();
	public void setTableName(String tableName);
	public String getOwner();
	public void setOwner(String owner);
	public int getPermission();
	public void setPermission(int permission);
}
