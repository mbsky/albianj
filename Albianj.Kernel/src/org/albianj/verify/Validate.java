package org.albianj.verify;

import java.util.Collection;

public class Validate
{

	public static boolean isNullOrEmpty(Collection<?> collection)
	{
		return null == collection || collection.isEmpty();
	}

	public static boolean isNullOrEmpty(String value)
	{
		return null == value || value.isEmpty();
	}

	public static boolean isNullOrEmptyOrAllSpace(String value)
	{
		return null == value || value.trim().isEmpty();
	}

}
