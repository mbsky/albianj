package org.albianj.persistence.object;

import java.util.List;

public interface IRoutingsAttribute
{
	public boolean getWriterHash();
	public void setWriterHash(boolean writerHash);
	public boolean getReaderHash();
	public void setReaderHash(boolean readerHash);
	
	public List<IRoutingAttribute> getWriterRoutings();
	public void setWriterRoutings(List<IRoutingAttribute> writerRoutings);
	public List<IRoutingAttribute> getReaderRoutings();
	public void setReaderRoutings(List<IRoutingAttribute> readerRoutings);
}
