package org.albianj.persistence.object;

public interface IAlbianObjectHashMapping
{
	public String[] mappingWriterRouting(Object obj);
	public String mappingReaderRouting(Object obj);
	public String mappingWriterTable(Object obj);
	public String mappingReaderTable(Object obj);
}
