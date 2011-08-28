package org.albianj.persistence.impl.context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.albianj.kernel.AlbianServiceRouter;
import org.albianj.logger.IAlbianLoggerService;
import org.albianj.persistence.impl.cached.AlbianObjectsCached;
import org.albianj.persistence.impl.cached.RoutingCached;
import org.albianj.persistence.impl.cached.StorageAttributeCache;
import org.albianj.persistence.impl.db.Command;
import org.albianj.persistence.impl.db.CommandType;
import org.albianj.persistence.impl.db.ICommand;
import org.albianj.persistence.impl.db.ISqlParameter;
import org.albianj.persistence.impl.db.SqlParameter;
import org.albianj.persistence.impl.toolkit.Convert;
import org.albianj.persistence.impl.toolkit.EnumMapping;
import org.albianj.persistence.object.IAlbianObjectAttribute;
import org.albianj.persistence.object.IFilterCondition;
import org.albianj.persistence.object.IMemberAttribute;
import org.albianj.persistence.object.IOrderByCondition;
import org.albianj.persistence.object.IRoutingAttribute;
import org.albianj.persistence.object.IRoutingsAttribute;
import org.albianj.persistence.object.IStorageAttribute;
import org.albianj.verify.Validate;

public abstract class FreeReaderJobAdapter implements IReaderJobAdapter
{
	public IReaderJob buildReaderJob(Class<?> cls, String routingName,
			int start, int step, List<IFilterCondition> wheres,
			List<IOrderByCondition> orderbys)
	{
		IAlbianLoggerService logger = AlbianServiceRouter.getService(
				IAlbianLoggerService.class, "logger");
		String className = cls.getName();
		IRoutingsAttribute routings = (IRoutingsAttribute) RoutingCached
				.get(className);
		IAlbianObjectAttribute albianObject = (IAlbianObjectAttribute) AlbianObjectsCached
				.get(className);
		
		IRoutingAttribute readerRouting = parserReaderRouting(cls,routingName,wheres,orderbys);
		
		StringBuilder sbCols = new StringBuilder();
		StringBuilder sbWhere = new StringBuilder();
		StringBuilder sbOrderby = new StringBuilder();
		StringBuilder sbStatement = new StringBuilder();
		 Map<String,ISqlParameter> paras = new HashMap<String, ISqlParameter>();
		for(String key : albianObject.getMembers().keySet())
		{
			IMemberAttribute member = albianObject.getMembers().get(key);
			if(!member.getIsSave()) continue;
			if(member.getSqlFieldName().equals(member.getName()))
			{
				sbCols.append(member.getSqlFieldName()).append(",");
			}
			else
			{
				sbCols.append(member.getSqlFieldName()).append(" AS ")
				.append(member.getName()).append(",");
			}
		}
		if(0 != sbCols.length()) sbCols.deleteCharAt(sbCols.length() -1);
		if(null != wheres)
		{
			for(IFilterCondition where : wheres)
			{
				sbWhere.append(EnumMapping.toRelationalOperators(where.getRelationalOperator()))
						.append(" ").append(where.getFieldName()).append(EnumMapping.toLogicalOperation(where.getLogicalOperation()))
						.append("#").append(where.getFieldName()).append("#");
				ISqlParameter para = new SqlParameter();
				para.setName(where.getFieldName());
				para.setSqlFieldName(where.getFieldName());
				para.setSqlType(Convert.toSqlType(where.getFieldClass()));
				para.setValue(where.getValue());
				paras.put(String.format("#%1$s#", where.getFieldName()), para);
			}
		}
		if(null != orderbys)
		{
			for(IOrderByCondition orderby : orderbys)
			{
				sbOrderby.append(orderby.getFieldName()).append(" ")
						.append(EnumMapping.toSortOperation(orderby.getSortStyle()))
						.append(",");
			}
		}
		if(0 != sbOrderby.length()) sbOrderby.deleteCharAt(sbOrderby.length() -1);
		String tableName = null;
		if(null == routings || null == routings.getHashMapping())
		{
			tableName = readerRouting.getTableName();
		}
		else
		{
			String tableSuffixName = routings.getHashMapping().mappingReaderTable(readerRouting.getName(), wheres, orderbys);
			
			tableName = Validate.isNullOrEmptyOrAllSpace(tableSuffixName) 
							? readerRouting.getTableName()
								: readerRouting.getTableName() + tableSuffixName;
		}
		
		sbStatement.append("SELECT ").append(sbCols).append(" FROM ").append(tableName)
					.append(" WHERE 1=1 ").append(sbWhere);
		if(0 != sbOrderby.length())
		{
			sbStatement.append(" ORDER BY ").append(sbOrderby);
		}
		if(0 <= start && 0 < step)
		{
			sbStatement.append(" LIMIT ").append(start).append(", ").append(step);
		}
		if(0 > start && 0 < step)
		{
			sbStatement.append(" LIMIT ").append(step);
		}
		
		if(null != logger) logger.debug(sbStatement);
		
		ICommand cmd = new Command();
		cmd.setCommandText(sbStatement.toString());
		cmd.setCommandType(CommandType.Text);
		cmd.setParameters(paras);
		
		IReaderJob job = new ReaderJob();
		job.setCommand(cmd);
		IStorageAttribute storage = (IStorageAttribute) StorageAttributeCache.get(readerRouting.getStorageName());
		job.setStorage(storage);
		return job;
	}
	
	protected abstract IRoutingAttribute parserReaderRouting(Class<?> cls,String routingName,List<IFilterCondition> wheres,
			List<IOrderByCondition> orderbys);
}
