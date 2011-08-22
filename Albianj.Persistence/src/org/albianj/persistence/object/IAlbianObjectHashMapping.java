package org.albianj.persistence.object;

public interface IAlbianObjectHashMapping
{
	public String[] mappingWriterRouting(IAlbianObject obj);
	public String mappingReaderRouting(IAlbianObject obj);
	public String mappingWriterTable(IAlbianObject obj,String routingName);
	public String mappingReaderTable(IAlbianObject obj,String routingName);
}
