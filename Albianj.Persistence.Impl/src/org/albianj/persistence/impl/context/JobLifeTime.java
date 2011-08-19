package org.albianj.persistence.impl.context;

public enum JobLifeTime
{
	Normal,
    NoStarted,
    Opening,
    OpenedAndRuning,
    Runned,
    Commiting,
    Commited,
    Rollbacking,
    Rollbacked,
}
