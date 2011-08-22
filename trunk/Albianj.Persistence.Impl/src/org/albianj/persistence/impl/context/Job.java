package org.albianj.persistence.impl.context;

import java.util.Map;


public class Job implements IJob
{
	private Map<String,ITask> tasks = null;
	private JobLifeTime jobLifeTime = JobLifeTime.Normal;
	private ICompensateCallback compensateCallback = null;
	private Object compensateCallbackObject = null;
	private INotify notifyCallback = null;
	private String currentStorage = null;
	
	@Override
	public Map<String,ITask> getTasks()
	{
		// TODO Auto-generated method stub
		return this.tasks;
	}

	@Override
	public void setTasks(Map<String,ITask> tasks)
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

	public void setCompensateCallbackObject(Object compensateCallbackObject)
	{
		this.compensateCallbackObject = compensateCallbackObject;
	}
	public Object getCompensateCallbackObject()
	{
		return this.compensateCallbackObject;
	}
	
	public INotify getNotifyCallback()
	{
		return this.notifyCallback;
	}
	
	public void setNotifyCallback(INotify notifyCallback)
	{
		this.notifyCallback = notifyCallback;
	}
	public String getCurrentStorage()
	{
		return this.currentStorage;
	}
	public void setCurrentStorage(String currentStorage)
	{
		this.currentStorage = currentStorage;
	}
}
