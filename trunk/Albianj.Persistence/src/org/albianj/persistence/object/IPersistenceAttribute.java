package org.albianj.persistence.object;

import java.util.List;

public interface IPersistenceAttribute
{
//	  <AlbianObject Type="AppTest.Model.Imp.Order">
//	    <Cache Enable="true" LifeTime="300"></Cache>
//	    <Routings>
//	      <Routing Name="IdRouting" StorageName="2thStorage" TableName="OrderById" Owner="dbo" Permission="WR"></Routing>
//	      <Routing Name="CreateTimeRouting" StorageName="3thStorage" TableName="OrderByCreateTime" Owner="dbo" Permission="WR"></Routing>
//	    </Routings>
//	    <Members>
//	      <Member Name="Id" FieldName="OrderId" AllowNull="false" Length="32" PrimaryKey="true" DbType="string" IsSave="true"/>
//	    </Members>
//	  </AlbianObject>
	
	public ICacheAttribute getCache();
	public void setCache(ICacheAttribute cache);
	public List<IRoutingAttribute> getWriterRoutings();
	public void setWriterRoutings(List<IRoutingAttribute> writerRoutings);
	public List<IRoutingAttribute> getReaderRoutings();
	public void setReaderRoutings(List<IRoutingAttribute> readerRoutings);
	public List<IMemberAttribute> getMembers();
	public void setMembers(List<IMemberAttribute> members);
}
