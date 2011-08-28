package org.albianj.persistence.impl.db;

import java.sql.SQLException;
import java.util.List;

import org.albianj.persistence.impl.context.IReaderJob;
import org.albianj.persistence.object.IAlbianObject;

public interface IQueryScope
{
	public <T extends IAlbianObject> List<T> query(Class<T> cls,IReaderJob job) throws SQLException;
}
