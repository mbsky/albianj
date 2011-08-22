package org.albianj.persistence.impl.db;

import java.util.Map;


public interface ICommand
{
	public String getCommandText();
	public void setCommandText(String commandText);
	public CommandType getCommandType();
	public void setCommandType(CommandType commandType);
	public Map<Integer,ISqlParameter> getParameters();
	public void setParameters(Map<Integer,ISqlParameter> parameters);
}
