package org.albianj.persistence.impl.service;

import java.sql.Statement;
import java.util.List;

import org.albianj.persistence.object.IAlbianObject;

public class AlbianPersistenceService
{
	public static boolean create(IAlbianObject object)
	{
		return true;
	}

	public static boolean create(IAlbianObject[] objs)
	{
	    return true;
	}

	public static boolean modify(IAlbianObject obj)
	{
		return true;
	}

	public static boolean modify(IAlbianObject[] objs)
	{
	   return true;
	}
	
	public static boolean remove(IAlbianObject obj)
	{
	    return true;
	}
	
	public static boolean remove(IAlbianObject[] objs)
	{
	    return true;
	}
	
	public static boolean save(IAlbianObject obj)
	{
	    return true;
	}
	
	public static boolean save(IAlbianObject[] objs)
	{
	    return true;
	}
	
	public static <T extends IAlbianObject> T findObject(Class<T> cls,String routingName, IFilterCondition[] where)
	{
	    return doFindObject(cls,routingName, where);
	}
	
	public static <T extends IAlbianObject> T findObject(Class<T> cls,String id)
	{
	return null;
	}
	
	public static  <T extends IAlbianObject> T findObject(Class<T> cls,String routingName, String id)
	{
	   return null;
	}
	
	public static <T extends IAlbianObject> T findObject(Class<T> cls,IFilterCondition[] where)
	{
	    return null;
	}
	
	public static <T extends IAlbianObject> T findObject(Class<T> cls,Statement statement)
	{
		return null;
	}
	
	public static <T extends IAlbianObject> List<T> findObjects(Class<T> cls,int top, IFilterCondition[] where, IOrderByCondition[] orderby)
	{
		return null;
	}
	
	public static <T extends IAlbianObject> List<T> findObjects(Class<T> cls,IFilterCondition[] where)
	{
	    return null;
	}
	
	public static <T extends IAlbianObject> List<T> findObjects(Class<T> cls, IFilterCondition[] where, IOrderByCondition[] orderby)
	{
	    return null;
	}
	
	public static  <T extends IAlbianObject> List<T> findObjects(Class<T> cls,IOrderByCondition[] orderby)
	{
	    return null;
	}
	
	public static  <T extends IAlbianObject> List<T> findObjects(Class<T> cls,String routingName, IFilterCondition[] where, IOrderByCondition[] orderby)
	{
	    return null;
	}
	
	public static  <T extends IAlbianObject> List<T> findObjects(Class<T> cls,String routingName, IOrderByCondition[] orderby)
	{
		return null;
	}
	
	public static <T extends IAlbianObject> List<T> findObjects(Class<T> cls,String routingName, IFilterCondition[] where)
	{
		return null;
	}
	
	public static  <T extends IAlbianObject> List<T> findObjects(Class<T> cls,String routingName, int top, IFilterCondition[] where,
	                                      IOrderByCondition[] orderby)
	{
		return null;
	}
	
	public static  <T extends IAlbianObject> List<T> findObjects(Class<T> cls,String routingName, int top, IFilterCondition[] where)
	{
		return null;
	}
	
	public static  <T extends IAlbianObject> List<T> findObjects(Statement statement)
	{
		return null;
	}
	
	public static  <T extends IAlbianObject> T loadObject(Class<T> cls,String routingName, IFilterCondition[] where)
	{
		return null;
	}
	
	public static  <T extends IAlbianObject> T loadObject(Class<T> cls,String id)
	{
		return null;
	}
	
	
	public static  <T extends IAlbianObject> T loadObject(Class<T> cls,String routingName, String id)
	{
		return null;
	}
	
	
	public static  <T extends IAlbianObject> T loadObject(Class<T> cls,IFilterCondition[] where)
	{
		return null;
	}
	
	public static  <T extends IAlbianObject> T loadObject(Class<T> cls,Statement statement)
	{
		return null;
	}
	
	public static  <T extends IAlbianObject> List<T> loadObjects(Class<T> cls,int top, IFilterCondition[] where, IOrderByCondition[] orderby)
	{
		return null;
	}
	
	public static  <T extends IAlbianObject> List<T> loadObjects(Class<T> cls,IFilterCondition[] where)
	{
		return null;
	}
	
	public static  <T extends IAlbianObject> List<T> loadObjects(Class<T> cls,IFilterCondition[] where, IOrderByCondition[] orderby)
	{
		return null;
	}
	
	public static  <T extends IAlbianObject> List<T> loadObjects(Class<T> cls,IOrderByCondition[] orderby)
	{
		return null;
	}
	
	public static  <T extends IAlbianObject> List<T> loadObjects(Class<T> cls,String routingName, IFilterCondition[] where, IOrderByCondition[] orderby)
	{
		return null;
	}
	
	public static  <T extends IAlbianObject> List<T> loadObjects(Class<T> cls,String routingName, IOrderByCondition[] orderby)
	{
	return null;
	}
	
	public static  <T extends IAlbianObject> List<T> loadObjects(Class<T> cls,String routingName, IFilterCondition[] where)
	{
		return null;
	}
	
	public static  <T extends IAlbianObject> List<T> loadObjects(Class<T> cls,String routingName, int top, IFilterCondition[] where,
	                                      IOrderByCondition[] orderby)
	{
	return  null;
	}
	
	public static  <T extends IAlbianObject> List<T> loadObjects(Class<T> cls,String routingName, int top, IFilterCondition[] where)
	{
	return null;
	}
	
	public static  <T extends IAlbianObject> List<T> loadObjects(Class<T> cls,Statement statement)
	{
		return null;
	}
	
	
	private static  <T extends IAlbianObject> T doFindObject(Class<T> cls,String routingName, IFilterCondition[] where)
	{
		return null;
	//    try
	//    {
	//        T target = ResultCache.GetCachingObject<T>(routingName, where);
	//        if (null != target) return target;
	//        target = DoLoadObject<T>(routingName, where);
	//        //ResultCache.CachingObject(routingName, where, target);
	//        return target;
	//    }
	//    catch (Exception exc)
	//    {
	//        if (null != Logger)
	//            Logger.ErrorFormat("Find Object is error.info:{0}.", exc.Message);
	//        throw;
	//    }
	}
	
	private static  <T extends IAlbianObject> T doFindObject(Class<T> cls,Statement statement)
	{
		return null;
	//    try
	//    {
	//        T target = ResultCache.GetCachingObject<T>(cmd);
	//        if (null != target) return target;
	//        target = DoLoadObject<T>(cmd);
	//        //ResultCache.CachingObject(cmd, target);
	//        return target;
	//    }
	//    catch (Exception exc)
	//    {
	//        if (null != Logger)
	//            Logger.ErrorFormat("Find Object is error..info:{0}.", exc.Message);
	//        throw;
	//    }
	}
	
	private static  <T extends IAlbianObject> List<T> doFindObjects(Class<T> cls,String routingName, int top, IFilterCondition[] where,
	                                         IOrderByCondition[] orderby)
	{
		return null;
	//    try
	//    {
	//        IList<T> target = ResultCache.GetCachingObjects<T>(routingName, top, where, orderby);
	//        if (null != target) return target;
	//        target = DoLoadObjects<T>(routingName, top, where, orderby);
	//        //ResultCache.CachingObjects(routingName, top, where, orderby, target);
	//        return target;
	//    }
	//    catch (Exception exc)
	//    {
	//        if (null != Logger)
	//            Logger.ErrorFormat("Find Object is error..info:{0}.", exc.Message);
	//        throw exc;
	//    }
	}
	
	private static  <T extends IAlbianObject> List<T> doFindObjects(Class<T> cls,Statement statement)
	{
		return null;
	//    try
	//    {
	//        IList<T> target = ResultCache.GetCachingObjects<T>(cmd);
	//        if (null != target) return target;
	//        target = DoLoadObjects<T>(cmd);
	//        //ResultCache.CachingObjects(cmd, target);
	//        return target;
	//    }
	//    catch (Exception exc)
	//    {
	//        if (null != Logger)
	//            Logger.ErrorFormat("Find Object is error..info:{0}.", exc.Message);
	//        throw exc;
	//    }
	}
	
	private static  <T extends IAlbianObject> List<T> doLoadObject(Class<T> cls,String routingName, IFilterCondition[] where)
	{
		return null;
	//    try
	//    {
	//        ITaskBuilder taskBuilder = new TaskBuilder();
	//        ITask task = taskBuilder.BuildQueryTask<T>(routingName, 0, where, null);
	//        IQueryCluster query = new QueryCluster();
	//        T target = query.QueryObject<T>(task);
	//        ResultCache.CachingObject(routingName, where, target);
	//        return target;
	//    }
	//    catch (Exception exc)
	//    {
	//        if (null != Logger)
	//            Logger.ErrorFormat("load Object is error..info:{0}.", exc.Message);
	//        throw;
	//    }
	}
	
	private static  <T extends IAlbianObject> T doLoadObject(Class<T> cls,Statement statement)
	{
		return null;
	//    try
	//    {
	//        IQueryCluster query = new QueryCluster();
	//        T target = query.QueryObject<T>(cmd);
	//        ResultCache.CachingObject(cmd, target);
	//        return target;
	//    }
	//    catch (Exception exc)
	//    {
	//        if (null != Logger)
	//            Logger.ErrorFormat("load Object is error..info:{0}.", exc.Message);
	//        throw;
	//    }
	}
	
	private static  <T extends IAlbianObject> List<T> doLoadObjects(Class<T> cls,String routingName, int top, IFilterCondition[] where,
	                                         IOrderByCondition[] orderby)
	{
		return null;
	//    try
	//    {
	//        ITaskBuilder taskBuilder = new TaskBuilder();
	//        ITask task = taskBuilder.BuildQueryTask<T>(routingName, top, where, orderby);
	//        IQueryCluster query = new QueryCluster();
	//        IList<T> targets = query.QueryObjects<T>(task);
	//        ResultCache.CachingObjects(routingName, top, where, orderby, targets);
	//        return targets;
	//    }
	//    catch (Exception exc)
	//    {
	//        if (null != Logger)
	//            Logger.ErrorFormat("Find Object is error..info:{0}.", exc.Message);
	//        throw;
	//    }
	}
	
	private static  <T extends IAlbianObject> List<T> doLoadObjects(Class<T> cls,Statement statement)
	{
		return null;
	//    try
	//    {
	//        IQueryCluster query = new QueryCluster();
	//        IList<T> targets = query.QueryObjects<T>(cmd);
	//        ResultCache.CachingObjects(cmd, targets);
	//        return targets;
	//    }
	//    catch (Exception exc)
	//    {
	//        if (null != Logger)
	//            Logger.ErrorFormat("Find Object is error..info:{0}.", exc.Message);
	//        throw;
	//    }
	}
}
