package org.albianj.persistence.impl.context;

public interface ITransactionClusterScope
{
	public boolean execute(IJob job);
}
