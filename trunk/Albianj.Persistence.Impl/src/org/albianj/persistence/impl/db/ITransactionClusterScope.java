package org.albianj.persistence.impl.db;

import org.albianj.persistence.impl.context.IJob;

public interface ITransactionClusterScope
{
	public boolean execute(IJob job);
}
