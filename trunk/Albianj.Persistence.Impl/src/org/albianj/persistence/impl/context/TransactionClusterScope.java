package org.albianj.persistence.impl.context;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.management.RuntimeErrorException;
import javax.sql.DataSource;

import org.albianj.kernel.AlbianServiceRouter;
import org.albianj.logger.IAlbianLoggerService;
import org.albianj.persistence.impl.cached.DataSourceCached;
import org.albianj.persistence.impl.storage.StorageService;
import org.albianj.persistence.object.IStorageAttribute;
import org.albianj.verify.Validate;

public class TransactionClusterScope implements ITransactionClusterScope
{
	@Override
	public boolean execute(IJob job)
	{
		IAlbianLoggerService logger = AlbianServiceRouter.getService(
				IAlbianLoggerService.class, "logger");
		boolean isSuccess = true;
		try
		{
			job.setJobLifeTime(JobLifeTime.NoStarted);
			this.preLoadExecute(job);
			job.setJobLifeTime(JobLifeTime.Opened);
			this.executeHandler(job);
			job.setJobLifeTime(JobLifeTime.Runned);
			this.executed(job);
			job.setJobLifeTime(JobLifeTime.Commited);
		}
		catch(Exception e)
		{
			isSuccess = false;
			if(null != logger)
			{
				logger.error(e,"Execute the query is error.");
			}
			if(null != job.getNotifyCallback())
			{
				try
				{
					StringBuilder sbMsg = new StringBuilder();
					sbMsg.append("Execute job is error.Job lifetime is:")
					.append(job.getJobLifeTime()).append(",exception msg:")
					.append(e.getMessage()).append(",Current task:")
					.append(job.getCurrentTask().getStorage().getName());
					job.getNotifyCallback().notice(sbMsg.toString());
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
				job.setJobLifeTime(JobLifeTime.Rollbacking);
				this.exceptionHandler(job);
				job.setJobLifeTime(JobLifeTime.Rollbacked);
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
				ICompensateCallback callback = job.getCompensateCallback();
				if(null != callback)
				{
					callback.Compensate(job.getCompensateCallbackObject());
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
					unLoadExecute(job);
				}
				catch(Exception exc)
				{
					if(null != logger)
					{
						logger.error(exc,"unload the job is error.");
					}
				}
			}
			job.setCurrentTask(null);
		}
		
		return isSuccess;		
	}

	protected void preLoadExecute(IJob job) throws SQLException
	{
		job.setJobLifeTime(JobLifeTime.Opening);
		List<ITask> tasks =  job.getTasks();
		if(Validate.isNullOrEmpty(tasks))
		{
			throw new RuntimeException("The task for job is empty or null.");
		}
		
		for(ITask task : tasks)
		{
			job.setCurrentTask(task);
			IStorageAttribute storage = task.getStorage();
			if(null == storage)
			{
				throw new RuntimeException("The storage for task is null.");
			}
			task.setConnection(StorageService.getConnection(storage.getName()));
			List<ICommand> cmds = task.getCommands();
			if(Validate.isNullOrEmpty(cmds))
			{
				throw new RuntimeException("The commands for task is empty or null.");
			}
			List<Statement> statements = new Vector<Statement>();
			for(ICommand cmd : cmds)
			{
				PreparedStatement prepareStatement = task.getConnection().prepareStatement(cmd.getCommandText());
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
			task.setStatements(statements);
		}
	}
	
	protected void executeHandler(IJob job) throws SQLException
	{
		job.setJobLifeTime(JobLifeTime.Running);
		List<ITask> tasks = job.getTasks();
		if(Validate.isNullOrEmpty(tasks))
		{
			throw new RuntimeException("The task is null or empty.");
		}
		for(ITask task : tasks)
		{
			job.setCurrentTask(task);
			List<Statement> statements = task.getStatements();
			for(Statement statement : statements)
			{
				if(!((PreparedStatement)statement).execute())
				{
					throw new RuntimeException("Execute the query is error.");
				}
			}
		}
	}
	
	protected void executed(IJob job) throws SQLException
	{
		job.setJobLifeTime(JobLifeTime.Commiting);
		List<ITask> tasks = job.getTasks();
		if(Validate.isNullOrEmpty(tasks))
		{
			throw new RuntimeException("The task is null or empty.");
		}
		for(ITask task : tasks)
		{
			job.setCurrentTask(task);
			task.getConnection().commit();
		}
	}
	
	protected void exceptionHandler(IJob job) throws SQLException
	{
		List<ITask> tasks = job.getTasks();
		if(Validate.isNullOrEmpty(tasks))
		{
			throw new RuntimeException("The task is null or empty.");
		}
		for(ITask task : tasks)
		{
//			job.setCurrentTask(task);
			task.getConnection().rollback();
		}
	}
	
	protected void unLoadExecute(IJob job) throws SQLException
	{
		List<ITask> tasks = job.getTasks();
		if(Validate.isNullOrEmpty(tasks))
		{
			throw new RuntimeException("The task is null or empty.");
		}
		for(ITask task : tasks)
		{
//			job.setCurrentTask(task);
			List<Statement> statements = task.getStatements();
			for(Statement statement : statements)
			{
				((PreparedStatement)statement).clearParameters();
				statement.close();
			}
			task.getConnection().close();
		}
	}
	
	
}
