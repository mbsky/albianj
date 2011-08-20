package org.albianj.persistence.impl.context;

import java.util.List;


public class Job implements IJob
{
	private List<ITask> tasks = null;
	private JobLifeTime jobLifeTime = JobLifeTime.Normal;
	private Exception exception = null;
	private boolean isRollback = false;
	private ICompensateCallback compensateCallback = null;
	
	@Override
	public List<ITask> getTasks()
	{
		// TODO Auto-generated method stub
		return this.tasks;
	}

	@Override
	public void setTasks(List<ITask> tasks)
	{
		// TODO Auto-generated method stub
		this.tasks = tasks;
	}

	@Override
	public JobLifeTime getJobLifeTime()
	{
		// TODO Auto-generated method stub
		return this.jobLifeTime;
	}

	@Override
	public void setJobLifeTime(JobLifeTime jobLifeTime)
	{
		// TODO Auto-generated method stub
		this.jobLifeTime = jobLifeTime;
	}

	@Override
	public Exception getException()
	{
		// TODO Auto-generated method stub
		return this.exception;
	}

	@Override
	public void setException(Exception exception)
	{
		// TODO Auto-generated method stub
		this.exception = exception;
	}

	@Override
	public boolean getIsRollback()
	{
		// TODO Auto-generated method stub
		return this.isRollback;
	}

	@Override
	public void setIsRollback(boolean isRollback)
	{
		// TODO Auto-generated method stub
		this.isRollback = isRollback;
	}

	@Override
	public ICompensateCallback getCompensateCallback()
	{
		// TODO Auto-generated method stub
		return this.compensateCallback;
	}

	@Override
	public void setCompensateCallback(ICompensateCallback compensateCallback)
	{
		// TODO Auto-generated method stub
		this.compensateCallback = compensateCallback;
	}

}
