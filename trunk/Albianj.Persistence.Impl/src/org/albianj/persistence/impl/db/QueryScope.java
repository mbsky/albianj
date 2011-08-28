package org.albianj.persistence.impl.db;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Vector;

//import javax.xml.transform.Result;

import org.albianj.kernel.AlbianServiceRouter;
import org.albianj.logger.IAlbianLoggerService;
import org.albianj.persistence.impl.cached.BeanPropertyDescriptorCached;
import org.albianj.persistence.impl.context.IReaderJob;
import org.albianj.persistence.impl.storage.StorageService;
import org.albianj.persistence.object.IAlbianObject;
import org.albianj.persistence.object.IStorageAttribute;
import org.albianj.verify.Validate;

public class QueryScope implements IQueryScope
{
	public <T extends IAlbianObject> List<T> query(Class<T> cls,IReaderJob job) throws SQLException
	{
		String className = cls.getName();
		NameSqlParameter.parseSql(job.getCommand());
		IStorageAttribute storage = job.getStorage();
		job.setConnection(StorageService.getConnection(storage.getName()));
		ICommand cmd = job.getCommand();
		PreparedStatement statement = job.getConnection().prepareStatement(cmd.getCommandText());
		Map<Integer,String>  map = cmd.getParameterMapper();
		IAlbianLoggerService logger = AlbianServiceRouter.getService(IAlbianLoggerService.class, "logger");
		if(!Validate.isNullOrEmpty(map))
		{
			for(int i = 1; i<= map.size(); i++)
			{
				String paraName = map.get(i);
				ISqlParameter para = cmd.getParameters().get(paraName);
				if(null == para.getValue())
				{
					statement.setNull(i, para.getSqlType());
				}
				else
				{
					statement.setObject(i, para.getValue(), para.getSqlType());
				}
			}
		}
		job.setStatement(statement);
		
		 statement.executeQuery();
		 ResultSet set = statement.getResultSet();
		 PropertyDescriptor[] propertyDesc = (PropertyDescriptor[]) BeanPropertyDescriptorCached.get(className);
		 List<T> list = new Vector<T>();
		 while(set.next())
		 {
			 try
			{
				T obj = (T) cls.newInstance();
				for(PropertyDescriptor desc : propertyDesc)
				{	if(desc.getName().equals("isAlbianNew")) continue;
					desc.getWriteMethod().invoke(obj, set.getObject(desc.getName()));
				}
				obj.setIsAlbianNew(false);
				list.add(obj);
			}
			catch (Exception e)
			{
				if(null != logger)
					logger.error(e,"create the albian object for list is error.");
				throw new RuntimeException(e);
			}
		 }
		 set.close();
		 job.getConnection().close();	
		return list;
	}
}
