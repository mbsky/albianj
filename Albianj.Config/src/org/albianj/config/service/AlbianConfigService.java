package org.albianj.config.service;

import org.albianj.verify.Validate;

public class AlbianConfigService
{
	public static String generateForefatherKey(String firstLevelName,String secondLevelName,String thirdLevelName)
	{
		StringBuilder sb = new StringBuilder();
		if(!Validate.isNullOrEmptyOrAllSpace(firstLevelName))
		{
			sb.append(firstLevelName);
		}
		else
		{
			return "";
		}
		if(!Validate.isNullOrEmptyOrAllSpace(secondLevelName))
		{
			sb.append("_").append(secondLevelName);
		}
		else
		{
			return sb.toString();
		}
		if(!Validate.isNullOrEmptyOrAllSpace(thirdLevelName))
		{
			sb.append("_").append(thirdLevelName);
		}
		else
		{
			return sb.toString();
		}
		return sb.toString();
		
	}
}
