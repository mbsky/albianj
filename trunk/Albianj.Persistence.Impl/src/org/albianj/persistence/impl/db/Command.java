package org.albianj.persistence.impl.db;

import java.util.Map;



public class Command implements ICommand
{
	private String commandText = null;
	private CommandType commandType = CommandType.Text;
	private Map<Integer,ISqlParameter> paramsters = null;
	@Override

	public String getCommandText()
	{
		// TODO Auto-generated method stub
		return this.commandText;
	}

	@Override
	public void setCommandText(String commandText)
	{
		// TODO Auto-generated method stub
		this.commandText = commandText;
	}

	@Override
	public CommandType getCommandType()
	{
		// TODO Auto-generated method stub
		return this.commandType;
	}

	@Override
	public void setCommandType(CommandType commandType)
	{
		// TODO Auto-generated method stub
		this.commandType = commandType;
	}

	@Override
	public Map<Integer, ISqlParameter> getParameters()
	{
		// TODO Auto-generated method stub
		return this.paramsters;
	}

	@Override
	public void setParameters(Map<Integer, ISqlParameter> parameters)
	{
		// TODO Auto-generated method stub
		this.paramsters = parameters;
	}

}
