package org.albianj.persistence.impl.context;

import java.util.List;

public interface IJob
{
	public List<ITask> getTasks();
	public void setTasks(List<ITask> tasks);
	public JobLifeTime getJobLifeTime();
	public void setJobLifeTime(JobLifeTime jobLifeTime);
	public Exception getException();
	public void setException(Exception exception);
	public boolean getIsRollback();
	public void setIsRollback(boolean isRollback);
	public ICompensateCallback getCompensateCallback();
	public void setCompensateCallback(ICompensateCallback compensateCallback);
}
