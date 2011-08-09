package org.albianj.persistence.object;
import java.io.Serializable;


public interface IAlbianObject extends Serializable
{	
	public String getId();
	public void setId(String id);
	public boolean getIsNew();
	public void setIsNew(boolean isNew);
	
	public String mappingWriterRouter();
	public String mappingReaderRouter();
	public String mappingWriterTable();
	public String mappingReaderTable();
}
