package org.albianj.persistence.impl.context;

import java.util.Map;

public interface IJob
{
	public Map<String,ITask> getTasks();
	public void setTasks(Map<String,ITask> tasks);
	public JobLifeTime getJobLifeTime();
	public void setJobLifeTime(JobLifeTime jobLifeTime);
	public String getCurrentStorage();
	public void setCurrentStorage(String currentStorage);
	public INotify getNotifyCallback();
	public void setNotifyCallback(INotify notifyCallback);
	public ICompensateCallback getCompensateCallback();
	public void setCompensateCallback(ICompensateCallback compensateCallback);
	public void setCompensateCallbackObject(Object compensateCallbackObject);
	public Object getCompensateCallbackObject();
}
