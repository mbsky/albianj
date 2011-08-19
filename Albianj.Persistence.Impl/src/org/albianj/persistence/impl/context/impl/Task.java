package org.albianj.persistence.impl.context.impl;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import org.albianj.persistence.impl.context.ICommand;
import org.albianj.persistence.impl.context.ITask;
import org.albianj.persistence.object.IStorageAttribute;

public class Task implements ITask
{
	private IStorageAttribute storage = null;
	private List<ICommand> commands = null;
	private Connection connection = null;
	private List<Statement> statements = null;
	
	@Override
	public IStorageAttribute getStorage()
	{
		// TODO Auto-generated method stub
		return this.storage;
	}

	@Override
	public void setStorage(IStorageAttribute storage)
	{
		// TODO Auto-generated method stub
		this.storage = storage;
	}

	@Override
	public List<ICommand> getCommands()
	{
		// TODO Auto-generated method stub
		return this.commands;
	}

	@Override
	public void setCommands(List<ICommand> commands)
	{
		// TODO Auto-generated method stub
		this.commands = commands;
	}

	@Override
	public Connection getConnection()
	{
		// TODO Auto-generated method stub
		return this.connection;
	}

	@Override
	public void setConnection(Connection connection)
	{
		// TODO Auto-generated method stub
		this.connection = connection;
	}

	@Override
	public List<Statement> getStatements()
	{
		// TODO Auto-generated method stub
		return this.statements;
	}

	@Override
	public void setStatements(List<Statement> statements)
	{
		// TODO Auto-generated method stub
		this.statements = statements;
	}

}