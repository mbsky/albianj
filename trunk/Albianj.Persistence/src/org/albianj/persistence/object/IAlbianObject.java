package org.albianj.persistence.object;
import java.io.Serializable;


public interface IAlbianObject extends Serializable
{	
	public String getId();
	public void setId(String id);
	public boolean getIsAlbianNew();
	public void setIsAlbianNew(boolean isNew);
	
	public String mappingWriterRouting();
	public String mappingReaderRouting();
	public String mappingWriterTable();
	public String mappingReaderTable();
}
