package org.albianj.persistence.impl.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.albianj.kernel.AlbianServiceRouter;
import org.albianj.logger.IAlbianLoggerService;
import org.albianj.persistence.impl.context.ICompensateCallback;
import org.albianj.persistence.impl.context.IWriterJob;
import org.albianj.persistence.impl.context.IWriterTask;
import org.albianj.persistence.impl.context.WriterJobLifeTime;
import org.albianj.persistence.impl.storage.StorageService;
import org.albianj.persistence.object.IStorageAttribute;
import org.albianj.verify.Validate;

public class TransactionClusterScope implements ITransactionClusterScope
{
	@Override
	public boolean execute(IWriterJob writerJob)
	{
		IAlbianLoggerService logger = AlbianServiceRouter.getService(
				IAlbianLoggerService.class, "logger");
		boolean isSuccess = true;
		try
		{
			writerJob.setWriterJobLifeTime(WriterJobLifeTime.NoStarted);
			this.preLoadExecute(writerJob);
			writerJob.setWriterJobLifeTime(WriterJobLifeTime.Opened);
			this.executeHandler(writerJob);
			writerJob.setWriterJobLifeTime(WriterJobLifeTime.Runned);
			this.executed(writerJob);
			writerJob.setWriterJobLifeTime(WriterJobLifeTime.Commited);
		}
		catch(Exception e)
		{
			isSuccess = false;
			if(null != logger)
			{
				logger.error(e,"Execute the query is error.");
			}
			if(null != writerJob.getNotifyCallback())
			{
				try
				{
					StringBuilder sbMsg = new StringBuilder();
					sbMsg.append("Execute job is error.Job lifetime is:")
					.append(writerJob.getWriterJobLifeTime()).append(",exception msg:")
					.append(e.getMessage()).append(",Current task:")
					.append(writerJob.getCurrentStorage());
					writerJob.getNotifyCallback().notice(sbMsg.toString());
				}
				catch(Exception exc)
				{
					if(null != logger)
					{
						logger.error(e,"the job error notice is error.");
					}	
				}
			}
			
			try
			{
				writerJob.setWriterJobLifeTime(WriterJobLifeTime.Rollbacking);
				this.exceptionHandler(writerJob);
				writerJob.setWriterJobLifeTime(WriterJobLifeTime.Rollbacked);
			}
			catch(Exception exc)
			{
				if(null != logger)
				{
					logger.error(exc,"rollback the query is error.");
				}
			}
		}
		finally
		{
			try
			{
				ICompensateCallback callback = writerJob.getCompensateCallback();
				if(null != callback)
				{
					callback.Compensate(writerJob.getCompensateCallbackObject());
				}
			}
			catch(Exception e)
			{
				if(null != logger)
				{
					logger.error(e,"execute the compensate callback is error.");
				}
			}
			finally
			{
				try
				{
					unLoadExecute(writerJob);
				}
				catch(Exception exc)
				{
					if(null != logger)
					{
						logger.error(exc,"unload the job is error.");
					}
				}
			}
			writerJob.setCurrentStorage(null);
		}
		
		return isSuccess;		
	}

	protected void preLoadExecute(IWriterJob writerJob) throws SQLException
	{
		writerJob.setWriterJobLifeTime(WriterJobLifeTime.Opening);
		Map<String,IWriterTask> tasks =  writerJob.getWriterTasks();
		if(Validate.isNullOrEmpty(tasks))
		{
			throw new RuntimeException("The task for job is empty or null.");
		}
		
		for(Map.Entry<String, IWriterTask> task : tasks.entrySet())
		{
			writerJob.setCurrentStorage(task.getKey());
			IStorageAttribute storage = task.getValue().getStorage();
			if(null == storage)
			{
				throw new RuntimeException("The storage for task is null.");
			}
			task.getValue().setConnection(StorageService.getConnection(storage.getName()));
			List<ICommand> cmds = task.getValue().getCommands();
			if(Validate.isNullOrEmpty(cmds))
			{
				throw new RuntimeException("The commands for task is empty or null.");
			}
			List<Statement> statements = new Vector<Statement>();
			for(ICommand cmd : cmds)
			{
				PreparedStatement prepareStatement = task.getValue().getConnection().prepareStatement(cmd.getCommandText());
				Map<Integer,ISqlParameter>  map = cmd.getParameters();
				if(Validate.isNullOrEmpty(map))
				{
					continue;
				}
				else
				{
					for(int i = 1; i<= map.size(); i++)
					{
						ISqlParameter para =(ISqlParameter) map.get(i);
						if(null == para.getValue())
						{
							prepareStatement.setNull(i, para.getSqlType());
						}
						else
						{
							prepareStatement.setObject(i, para.getValue(), para.getSqlType());
						}
					}
				}
				statements.add(prepareStatement);
			}
			task.getValue().setStatements(statements);
		}
	}
	
	protected void executeHandler(IWriterJob writerJob) throws SQLException
	{
		writerJob.setWriterJobLifeTime(WriterJobLifeTime.Running);
		Map<String,IWriterTask> tasks = writerJob.getWriterTasks();
		if(Validate.isNullOrEmpty(tasks))
		{
			throw new RuntimeException("The task is null or empty.");
		}
		for(Map.Entry<String, IWriterTask> task : tasks.entrySet())
		{
			writerJob.setCurrentStorage(task.getKey());
			List<Statement> statements = task.getValue().getStatements();
			for(Statement statement : statements)
			{
				((PreparedStatement)statement).executeUpdate() ;
			}
		}
	}
	
	protected void executed(IWriterJob writerJob) throws SQLException
	{
		writerJob.setWriterJobLifeTime(WriterJobLifeTime.Commiting);
		Map<String,IWriterTask> tasks = writerJob.getWriterTasks();
		if(Validate.isNullOrEmpty(tasks))
		{
			throw new RuntimeException("The task is null or empty.");
		}
		for(Map.Entry<String, IWriterTask> task : tasks.entrySet())
		{
			writerJob.setCurrentStorage(task.getKey());
			task.getValue().getConnection().commit();
		}
	}
	
	protected void exceptionHandler(IWriterJob writerJob) throws SQLException
	{
		Map<String,IWriterTask> tasks = writerJob.getWriterTasks();
		if(Validate.isNullOrEmpty(tasks))
		{
			throw new RuntimeException("The task is null or empty.");
		}
		for(Map.Entry<String, IWriterTask> task : tasks.entrySet())
		{
			task.getValue().getConnection().rollback();
		}
	}
	
	protected void unLoadExecute(IWriterJob writerJob) throws SQLException
	{
		Map<String,IWriterTask> tasks = writerJob.getWriterTasks();
		if(Validate.isNullOrEmpty(tasks))
		{
			throw new RuntimeException("The task is null or empty.");
		}
		for(Map.Entry<String, IWriterTask> task : tasks.entrySet())
		{
			List<Statement> statements = task.getValue().getStatements();
			for(Statement statement : statements)
			{
				((PreparedStatement)statement).clearParameters();
				statement.close();
			}
			task.getValue().getConnection().close();
		}
	}
	  
	
}
