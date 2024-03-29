package org.albianj.persistence.impl.service;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.albianj.persistence.impl.cached.AlbianObjectsMap;
import org.albianj.persistence.impl.context.ICompensateCallback;
import org.albianj.persistence.impl.context.INotify;
import org.albianj.persistence.impl.context.IReaderJob;
import org.albianj.persistence.impl.context.IReaderJobAdapter;
import org.albianj.persistence.impl.context.IWriterJob;
import org.albianj.persistence.impl.context.IWriterJobAdapter;
import org.albianj.persistence.impl.context.ReaderJobAdapter;
import org.albianj.persistence.impl.context.WriterJobAdapter;
import org.albianj.persistence.impl.db.CommandType;
import org.albianj.persistence.impl.db.IQueryScope;
import org.albianj.persistence.impl.db.ITransactionClusterScope;
import org.albianj.persistence.impl.db.QueryScope;
import org.albianj.persistence.impl.db.TransactionClusterScope;
import org.albianj.persistence.impl.dbcached.CacheOperator;
import org.albianj.persistence.object.IAlbianObject;
import org.albianj.persistence.object.IAlbianObjectAttribute;
import org.albianj.persistence.object.IFilterCondition;
import org.albianj.persistence.object.IOrderByCondition;
import org.albianj.persistence.object.LogicalOperation;
import org.albianj.persistence.object.RelationalOperator;
import org.albianj.verify.Validate;

public class AlbianPersistenceService
{
	public static boolean create(IAlbianObject object)
	{
		IWriterJobAdapter ja = new WriterJobAdapter();
		IWriterJob job = ja.buildCreation(object);
		ITransactionClusterScope tcs = new TransactionClusterScope();
		return tcs.execute(job);
	}
	public static boolean create(IAlbianObject object,INotify notifyCallback)
	{
		IWriterJobAdapter ja = new WriterJobAdapter();
		IWriterJob job = ja.buildCreation(object);
		job.setNotifyCallback(notifyCallback);
		ITransactionClusterScope tcs = new TransactionClusterScope();
		return tcs.execute(job);
	}
	public static boolean create(IAlbianObject object,ICompensateCallback compensateCallback,Object compensateCallbackObject)
	{
		IWriterJobAdapter ja = new WriterJobAdapter();
		IWriterJob job = ja.buildCreation(object);
		job.setCompensateCallback(compensateCallback);
		job.setCompensateCallbackObject(compensateCallbackObject);
		ITransactionClusterScope tcs = new TransactionClusterScope();
		return tcs.execute(job);
	}
	public static boolean create(IAlbianObject object,INotify notifyCallback,ICompensateCallback compensateCallback,Object compensateCallbackObject)
	{
		IWriterJobAdapter ja = new WriterJobAdapter();
		IWriterJob job = ja.buildCreation(object);
		job.setNotifyCallback(notifyCallback);
		job.setCompensateCallback(compensateCallback);
		job.setCompensateCallbackObject(compensateCallbackObject);
		ITransactionClusterScope tcs = new TransactionClusterScope();
		return tcs.execute(job);
	}

	public static boolean create(List<IAlbianObject> objects)
	{
		IWriterJobAdapter ja = new WriterJobAdapter();
		IWriterJob job = ja.buildCreation(objects);
		ITransactionClusterScope tcs = new TransactionClusterScope();
		return tcs.execute(job);
	}
	public static boolean create(List<IAlbianObject> objects,INotify notifyCallback)
	{
		IWriterJobAdapter ja = new WriterJobAdapter();
		IWriterJob job = ja.buildCreation(objects);
		job.setNotifyCallback(notifyCallback);
		ITransactionClusterScope tcs = new TransactionClusterScope();
		return tcs.execute(job);
	}
	public static boolean create(List<IAlbianObject> objects,ICompensateCallback compensateCallback,Object compensateCallbackObject)
	{
		IWriterJobAdapter ja = new WriterJobAdapter();
		IWriterJob job = ja.buildCreation(objects);
		job.setCompensateCallback(compensateCallback);
		job.setCompensateCallbackObject(compensateCallbackObject);
		ITransactionClusterScope tcs = new TransactionClusterScope();
		return tcs.execute(job);
	}
	public static boolean create(List<IAlbianObject> objects,INotify notifyCallback,ICompensateCallback compensateCallback,Object compensateCallbackObject)
	{
		IWriterJobAdapter ja = new WriterJobAdapter();
		IWriterJob job = ja.buildCreation(objects);
		job.setNotifyCallback(notifyCallback);
		job.setCompensateCallback(compensateCallback);
		job.setCompensateCallbackObject(compensateCallbackObject);
		ITransactionClusterScope tcs = new TransactionClusterScope();
		return tcs.execute(job);
	}
	

	public static boolean modify(IAlbianObject object)
	{
		IWriterJobAdapter ja = new WriterJobAdapter();
		IWriterJob job = ja.buildModification(object);
		ITransactionClusterScope tcs = new TransactionClusterScope();
		return tcs.execute(job);
	}
	public static boolean modify(IAlbianObject object,INotify notifyCallback)
	{
		IWriterJobAdapter ja = new WriterJobAdapter();
		IWriterJob job = ja.buildModification(object);
		job.setNotifyCallback(notifyCallback);
		ITransactionClusterScope tcs = new TransactionClusterScope();
		return tcs.execute(job);
	}
	public static boolean modify(IAlbianObject object,ICompensateCallback compensateCallback,Object compensateCallbackObject)
	{
		IWriterJobAdapter ja = new WriterJobAdapter();
		IWriterJob job = ja.buildModification(object);
		job.setCompensateCallback(compensateCallback);
		job.setCompensateCallbackObject(compensateCallbackObject);
		ITransactionClusterScope tcs = new TransactionClusterScope();
		return tcs.execute(job);
	}
	public static boolean modify(IAlbianObject object,INotify notifyCallback,ICompensateCallback compensateCallback,Object compensateCallbackObject)
	{
		IWriterJobAdapter ja = new WriterJobAdapter();
		IWriterJob job = ja.buildModification(object);
		job.setNotifyCallback(notifyCallback);
		job.setCompensateCallback(compensateCallback);
		job.setCompensateCallbackObject(compensateCallbackObject);
		ITransactionClusterScope tcs = new TransactionClusterScope();
		return tcs.execute(job);
	}

	public static boolean modify(List<IAlbianObject> objects)
	{
		IWriterJobAdapter ja = new WriterJobAdapter();
		IWriterJob job = ja.buildModification(objects);
		ITransactionClusterScope tcs = new TransactionClusterScope();
		return tcs.execute(job);
	}
	public static boolean modify(List<IAlbianObject> objects,INotify notifyCallback)
	{
		IWriterJobAdapter ja = new WriterJobAdapter();
		IWriterJob job = ja.buildModification(objects);
		job.setNotifyCallback(notifyCallback);
		ITransactionClusterScope tcs = new TransactionClusterScope();
		return tcs.execute(job);
	}
	public static boolean modify(List<IAlbianObject> objects,ICompensateCallback compensateCallback,Object compensateCallbackObject)
	{
		IWriterJobAdapter ja = new WriterJobAdapter();
		IWriterJob job = ja.buildModification(objects);
		job.setCompensateCallback(compensateCallback);
		job.setCompensateCallbackObject(compensateCallbackObject);
		ITransactionClusterScope tcs = new TransactionClusterScope();
		return tcs.execute(job);
	}
	public static boolean modify(List<IAlbianObject> objects,INotify notifyCallback,ICompensateCallback compensateCallback,Object compensateCallbackObject)
	{
		IWriterJobAdapter ja = new WriterJobAdapter();
		IWriterJob job = ja.buildModification(objects);
		job.setNotifyCallback(notifyCallback);
		job.setCompensateCallback(compensateCallback);
		job.setCompensateCallbackObject(compensateCallbackObject);
		ITransactionClusterScope tcs = new TransactionClusterScope();
		return tcs.execute(job);
	}
	
	public static boolean remove(IAlbianObject object)
	{
		IWriterJobAdapter ja = new WriterJobAdapter();
		IWriterJob job = ja.buildRemoved(object);
		ITransactionClusterScope tcs = new TransactionClusterScope();
		return tcs.execute(job);
	}
	public static boolean remove(IAlbianObject object,INotify notifyCallback)
	{
		IWriterJobAdapter ja = new WriterJobAdapter();
		IWriterJob job = ja.buildRemoved(object);
		job.setNotifyCallback(notifyCallback);
		ITransactionClusterScope tcs = new TransactionClusterScope();
		return tcs.execute(job);
	}
	public static boolean remove(IAlbianObject object,ICompensateCallback compensateCallback,Object compensateCallbackObject)
	{
		IWriterJobAdapter ja = new WriterJobAdapter();
		IWriterJob job = ja.buildRemoved(object);
		job.setCompensateCallback(compensateCallback);
		job.setCompensateCallbackObject(compensateCallbackObject);
		ITransactionClusterScope tcs = new TransactionClusterScope();
		return tcs.execute(job);
	}
	public static boolean remove(IAlbianObject object,INotify notifyCallback,ICompensateCallback compensateCallback,Object compensateCallbackObject)
	{
		IWriterJobAdapter ja = new WriterJobAdapter();
		IWriterJob job = ja.buildRemoved(object);
		job.setNotifyCallback(notifyCallback);
		job.setCompensateCallback(compensateCallback);
		job.setCompensateCallbackObject(compensateCallbackObject);
		ITransactionClusterScope tcs = new TransactionClusterScope();
		return tcs.execute(job);
	}
	
	public static boolean remove(List<IAlbianObject> objects)
	{
		IWriterJobAdapter ja = new WriterJobAdapter();
		IWriterJob job = ja.buildRemoved(objects);
		ITransactionClusterScope tcs = new TransactionClusterScope();
		return tcs.execute(job);
	}
	public static boolean remove(List<IAlbianObject> objects,INotify notifyCallback)
	{
		IWriterJobAdapter ja = new WriterJobAdapter();
		IWriterJob job = ja.buildRemoved(objects);
		job.setNotifyCallback(notifyCallback);
		ITransactionClusterScope tcs = new TransactionClusterScope();
		return tcs.execute(job);
	}
	public static boolean remove(List<IAlbianObject> objects,ICompensateCallback compensateCallback,Object compensateCallbackObject)
	{
		IWriterJobAdapter ja = new WriterJobAdapter();
		IWriterJob job = ja.buildRemoved(objects);
		job.setCompensateCallback(compensateCallback);
		job.setCompensateCallbackObject(compensateCallbackObject);
		ITransactionClusterScope tcs = new TransactionClusterScope();
		return tcs.execute(job);
	}
	public static boolean remove(List<IAlbianObject> objects,INotify notifyCallback,ICompensateCallback compensateCallback,Object compensateCallbackObject)
	{
		IWriterJobAdapter ja = new WriterJobAdapter();
		IWriterJob job = ja.buildRemoved(objects);
		job.setNotifyCallback(notifyCallback);
		job.setCompensateCallback(compensateCallback);
		job.setCompensateCallbackObject(compensateCallbackObject);
		ITransactionClusterScope tcs = new TransactionClusterScope();
		return tcs.execute(job);
	}
	
	public static boolean save(IAlbianObject object)
	{
		IWriterJobAdapter ja = new WriterJobAdapter();
		IWriterJob job = ja.buildSaving(object);
		ITransactionClusterScope tcs = new TransactionClusterScope();
		return tcs.execute(job);
	}
	public static boolean save(IAlbianObject object,INotify notifyCallback)
	{
		IWriterJobAdapter ja = new WriterJobAdapter();
		IWriterJob job = ja.buildSaving(object);
		job.setNotifyCallback(notifyCallback);
		ITransactionClusterScope tcs = new TransactionClusterScope();
		return tcs.execute(job);
	}
	public static boolean save(IAlbianObject object,ICompensateCallback compensateCallback,Object compensateCallbackObject)
	{
		IWriterJobAdapter ja = new WriterJobAdapter();
		IWriterJob job = ja.buildSaving(object);
		job.setCompensateCallback(compensateCallback);
		job.setCompensateCallbackObject(compensateCallbackObject);
		ITransactionClusterScope tcs = new TransactionClusterScope();
		return tcs.execute(job);
	}
	public static boolean save(IAlbianObject object,INotify notifyCallback,ICompensateCallback compensateCallback,Object compensateCallbackObject)
	{
		IWriterJobAdapter ja = new WriterJobAdapter();
		IWriterJob job = ja.buildSaving(object);
		job.setNotifyCallback(notifyCallback);
		job.setCompensateCallback(compensateCallback);
		job.setCompensateCallbackObject(compensateCallbackObject);
		ITransactionClusterScope tcs = new TransactionClusterScope();
		return tcs.execute(job);
	}
	
	public static boolean save(List<IAlbianObject> objects)
	{
		IWriterJobAdapter ja = new WriterJobAdapter();
		IWriterJob job = ja.buildSaving(objects);
		ITransactionClusterScope tcs = new TransactionClusterScope();
		return tcs.execute(job);
	}
	public static boolean save(List<IAlbianObject> objects,INotify notifyCallback)
	{
		IWriterJobAdapter ja = new WriterJobAdapter();
		IWriterJob job = ja.buildSaving(objects);
		job.setNotifyCallback(notifyCallback);
		ITransactionClusterScope tcs = new TransactionClusterScope();
		return tcs.execute(job);
	}
	public static boolean save(List<IAlbianObject> objects,ICompensateCallback compensateCallback,Object compensateCallbackObject)
	{
		IWriterJobAdapter ja = new WriterJobAdapter();
		IWriterJob job = ja.buildSaving(objects);
		job.setCompensateCallback(compensateCallback);
		job.setCompensateCallbackObject(compensateCallbackObject);
		ITransactionClusterScope tcs = new TransactionClusterScope();
		return tcs.execute(job);
	}
	public static boolean save(List<IAlbianObject> objects,INotify notifyCallback,ICompensateCallback compensateCallback,Object compensateCallbackObject)
	{
		IWriterJobAdapter ja = new WriterJobAdapter();
		IWriterJob job = ja.buildSaving(objects);
		job.setNotifyCallback(notifyCallback);
		job.setCompensateCallback(compensateCallback);
		job.setCompensateCallbackObject(compensateCallbackObject);
		ITransactionClusterScope tcs = new TransactionClusterScope();
		return tcs.execute(job);
	}
	
	

	
	public static <T extends IAlbianObject> T findObject(Class<T> cls,String routingName, IFilterCondition[] wheres)
	{
	    return doFindObject(cls,routingName, wheres);
	}
	public static <T extends IAlbianObject> T findObject(Class<T> cls,String id)
	{
		IFilterCondition[] wheres = new IFilterCondition[1];
		wheres[0].setFieldClass(String.class);
		wheres[0].setFieldName("id");
		wheres[0].setLogicalOperation(LogicalOperation.Equal);
		wheres[0].setRelationalOperator(RelationalOperator.And);
		wheres[0].setValue(id);
		
		return doFindObject(cls, null, wheres);
	}
	public static  <T extends IAlbianObject> T findObject(Class<T> cls,String routingName, String id)
	{
		IFilterCondition[] wheres = new IFilterCondition[1];
		wheres[0].setFieldClass(String.class);
		wheres[0].setFieldName("id");
		wheres[0].setLogicalOperation(LogicalOperation.Equal);
		wheres[0].setRelationalOperator(RelationalOperator.And);
		wheres[0].setValue(id);
		
		return doFindObject(cls, routingName, wheres);
	}
	public static <T extends IAlbianObject> T findObject(Class<T> cls,IFilterCondition[] wheres)
	{
	    return doFindObject(cls, null, wheres);
	}
	public static <T extends IAlbianObject> T findObject(Class<T> cls,String cacheKey,CommandType cmdType,Statement statement)
	{
		return doFindObject(cls, cacheKey, cmdType, statement);
	}
	
	public static <T extends IAlbianObject> List<T> findObjects(Class<T> cls,int start,int step, IFilterCondition[] wheres, IOrderByCondition[] orderbys)
	{
		return doFindObjects(cls, null, start, step, wheres, orderbys);
	}
	public static <T extends IAlbianObject> List<T> findObjects(Class<T> cls,IFilterCondition[] wheres)
	{
	    return doFindObjects(cls, null, 0, 30, wheres, null);
	}
	public static <T extends IAlbianObject> List<T> findObjects(Class<T> cls, IFilterCondition[] wheres, IOrderByCondition[] orderbys)
	{
	    return doFindObjects(cls, null, 0, 30, wheres, orderbys);
	}
	public static  <T extends IAlbianObject> List<T> findObjects(Class<T> cls,IOrderByCondition[] orderbys)
	{
	    return doFindObjects(cls, null, 0, 30, null, orderbys);
	}
	public static  <T extends IAlbianObject> List<T> findObjects(Class<T> cls,String routingName, IFilterCondition[] wheres, IOrderByCondition[] orderbys)
	{
	    return doFindObjects(cls, routingName, 0, 30, wheres, orderbys);
	}
	public static  <T extends IAlbianObject> List<T> findObjects(Class<T> cls,String routingName, IOrderByCondition[] orderbys)
	{
		return doFindObjects(cls, routingName, 0, 30, null, orderbys);
	}
	public static <T extends IAlbianObject> List<T> findObjects(Class<T> cls,String routingName, IFilterCondition[] wheres)
	{
		return doFindObjects(cls, routingName, 0, 30, wheres, null);
	}
	public static  <T extends IAlbianObject> List<T> findObjects(Class<T> cls,String routingName, int start,int step, IFilterCondition[] wheres,
	                                      IOrderByCondition[] orderbys)
	{
		return doFindObjects(cls, routingName, start, step, wheres, orderbys);
	}
	public static  <T extends IAlbianObject> List<T> findObjects(Class<T> cls,String routingName, int start,int step, IFilterCondition[] wheres)
	{
		return doFindObjects(cls, routingName, start, step, wheres, null);
	}
	public static  <T extends IAlbianObject> List<T> findObjects(Class<T> cls,String routingName, int start,int step, IOrderByCondition[] orderbys)
	{
		return doFindObjects(cls, routingName, start, step, null, orderbys);
	}
	public static  <T extends IAlbianObject> List<T> findObjects(Class<T> cls,String cacheKey,CommandType cmdType,Statement statement)
	{
		return doFindObjects(cls, cacheKey, cmdType, statement);
	}
	
	public static  <T extends IAlbianObject> T loadObject(Class<T> cls,String routingName, IFilterCondition[] wheres)
	{
		return doLoadObject(cls, routingName, wheres);
	}
	public static  <T extends IAlbianObject> T loadObject(Class<T> cls,String id)
	{
		IFilterCondition[] wheres = new IFilterCondition[1];
		wheres[0].setFieldClass(String.class);
		wheres[0].setFieldName("id");
		wheres[0].setLogicalOperation(LogicalOperation.Equal);
		wheres[0].setRelationalOperator(RelationalOperator.And);
		wheres[0].setValue(id);
		
		return doLoadObject(cls, null, wheres);
	}
	public static  <T extends IAlbianObject> T loadObject(Class<T> cls,String routingName, String id)
	{
		IFilterCondition[] wheres = new IFilterCondition[1];
		wheres[0].setFieldClass(String.class);
		wheres[0].setFieldName("id");
		wheres[0].setLogicalOperation(LogicalOperation.Equal);
		wheres[0].setRelationalOperator(RelationalOperator.And);
		wheres[0].setValue(id);
		
		return doLoadObject(cls, routingName, wheres);
	}
	public static  <T extends IAlbianObject> T loadObject(Class<T> cls,IFilterCondition[] wheres)
	{
		return doLoadObject(cls, null, wheres);
	}
	public static  <T extends IAlbianObject> T loadObject(Class<T> cls,CommandType cmdType, Statement statement)
	{
		return doLoadObject(cls, cmdType, statement);
	}
	
	public static  <T extends IAlbianObject> List<T> loadObjects(Class<T> cls,int start,int step, IFilterCondition[] wheres, IOrderByCondition[] orderbys)
	{
		return doLoadObjects(cls, null, start, step, wheres, orderbys);
	}
	public static  <T extends IAlbianObject> List<T> loadObjects(Class<T> cls,IFilterCondition[] wheres)
	{
		return doLoadObjects(cls, null, 0, 30, wheres, null);
	}
	public static  <T extends IAlbianObject> List<T> loadObjects(Class<T> cls,IFilterCondition[] wheres, IOrderByCondition[] orderbys)
	{
		return doLoadObjects(cls, null, 0, 30, null, orderbys);
	}
	public static  <T extends IAlbianObject> List<T> loadObjects(Class<T> cls,IOrderByCondition[] orderbys)
	{
		return doLoadObjects(cls, null, 0, 30, null, orderbys);
	}
	public static  <T extends IAlbianObject> List<T> loadObjects(Class<T> cls,String routingName, IFilterCondition[] wheres, IOrderByCondition[] orderbys)
	{
		return doLoadObjects(cls, routingName, 0, 30, wheres, orderbys);
	}
	public static  <T extends IAlbianObject> List<T> loadObjects(Class<T> cls,String routingName, IOrderByCondition[] orderbys)
	{
		return doLoadObjects(cls, routingName, 0, 30, null, orderbys);
	}
	public static  <T extends IAlbianObject> List<T> loadObjects(Class<T> cls,String routingName, IFilterCondition[] wheres)
	{
		return doLoadObjects(cls, routingName, 0, 30, wheres, null);
	}
	public static  <T extends IAlbianObject> List<T> loadObjects(Class<T> cls,String routingName, int start,int step,IFilterCondition[] wheres, IOrderByCondition[] orderbys)
	{
	return  doLoadObjects(cls, routingName, start, step, wheres, orderbys);
	}
	public static  <T extends IAlbianObject> List<T> loadObjects(Class<T> cls,String routingName, int start,int step,IFilterCondition[] wheres)
	{
		return doLoadObjects(cls, routingName, start, step, wheres, null);
	}
	public static  <T extends IAlbianObject> List<T> loadObjects(Class<T> cls,CommandType cmdType,Statement statement)
	{
		return doLoadObjects(cls, cmdType, statement);
	}
	
	@SuppressWarnings("unchecked")
	protected static  <T extends IAlbianObject> T doFindObject(Class<T> cls,String routingName, IFilterCondition[] wheres)
	{
		Object obj = CacheOperator.getObjectFromRemoterCache(cls,0,0, wheres, null);
		if(null != obj) return (T) obj;
		T newObj = doLoadObject(cls, routingName, wheres);
		if(null == newObj) return null;
		IAlbianObjectAttribute albianObject = (IAlbianObjectAttribute) AlbianObjectsMap
				.get(cls.getName());
		CacheOperator.storeObjectToRemoterCache(cls, 0,0,wheres, null, newObj, null == albianObject.getCache() ? 300 : albianObject.getCache().getLifeTime());
		return newObj;
	}
	
	@SuppressWarnings("unchecked")
	protected static  <T extends IAlbianObject> T doFindObject(Class<T> cls,String cacheKey,CommandType cmdType,Statement statement)
	{
		Object obj = CacheOperator.getObjectFromRemoterCache(cacheKey);
		if(null != obj) return (T) obj;
		T newObj = doLoadObject(cls, cmdType, statement);
		if(null == newObj) return null;
		IAlbianObjectAttribute albianObject = (IAlbianObjectAttribute) AlbianObjectsMap
				.get(cls.getName());
		CacheOperator.storeObjectToRemoterCache(cacheKey, newObj, null == albianObject.getCache() ? 300 : albianObject.getCache().getLifeTime());
		return newObj;
	}
	
	@SuppressWarnings("unchecked")
	protected static  <T extends IAlbianObject> List<T> doFindObjects(Class<T> cls,String routingName, int start,int step, IFilterCondition[] wheres,
	                                         IOrderByCondition[] orderbys)
	{
		Object obj = CacheOperator.getObjectFromLocalCache(cls, start,step,wheres, orderbys);
		if(null != obj) return (List<T>) obj;
		List<T> newObj = doLoadObjects(cls, routingName,start,step, wheres,orderbys);
		if(null == newObj) return null;
		IAlbianObjectAttribute albianObject = (IAlbianObjectAttribute) AlbianObjectsMap
				.get(cls.getName());
		CacheOperator.storeObjectToRemoterCache(cls, start,step,wheres, null, newObj, null == albianObject.getCache() ? 300 : albianObject.getCache().getLifeTime());
		return newObj;
	}
	@SuppressWarnings("unchecked")
	protected static  <T extends IAlbianObject> List<T> doFindObjects(Class<T> cls,String cacheKey,CommandType cmdType,Statement statement)
	{
		Object obj = CacheOperator.getObjectFromLocalCache(cacheKey);
		if(null != obj) return (List<T>) obj;
		List<T> newObj = doLoadObjects(cls, cmdType, statement);
		if(null == newObj) return null;
		IAlbianObjectAttribute albianObject = (IAlbianObjectAttribute) AlbianObjectsMap
				.get(cls.getName());
		CacheOperator.storeObjectToRemoterCache(cacheKey, newObj, null == albianObject.getCache() ? 300 : albianObject.getCache().getLifeTime());
		return newObj;
	}
	
	protected static  <T extends IAlbianObject> T doLoadObject(Class<T> cls,String routingName,  IFilterCondition[] wheres)
	{
		List<T> list = doLoadObjects(cls,routingName,0,0,wheres,null);
		if(Validate.isNullOrEmpty(list)) return null;
		return list.get(0);
	}
	protected static  <T extends IAlbianObject> T doLoadObject(Class<T> cls,CommandType cmdType,Statement statement)
	{
		List<T> list = doLoadObjects(cls,cmdType,statement);
		if(Validate.isNullOrEmpty(list)) return null;
		return list.get(0);
	}
	
	protected static  <T extends IAlbianObject> List<T> doLoadObjects(Class<T> cls,String routingName, int start,int step, IFilterCondition[] wheres, IOrderByCondition[] orderbys)
	{
		IReaderJobAdapter ad = new ReaderJobAdapter();
		IReaderJob job = ad.buildReaderJob(cls, routingName, start, step, wheres, orderbys);
		IQueryScope scope = new QueryScope();
		List<T> list = null;
		try
		{
			list = scope.execute(cls, job);
		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);
		}
		return list;
	}
	protected static  <T extends IAlbianObject> List<T> doLoadObjects(Class<T> cls,CommandType cmdType,Statement statement)
	{
		IQueryScope scope = new QueryScope();
		List<T> list = null;
		try
		{
			list = scope.execute(cls, cmdType,statement);
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
		return list;
	}
}
