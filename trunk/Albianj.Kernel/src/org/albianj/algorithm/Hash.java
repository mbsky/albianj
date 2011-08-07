package org.albianj.algorithm;

public class Hash
{

	public static long generateCode(String value) throws IllegalArgumentException
	{
		if (null == value || value.isEmpty()) 
		{ 
			throw new IllegalArgumentException("value"); 
		}
		int len = value.length();
		long llen = (long) len;
		int step = (len >> 5) + 1;
		for (int i = len; i >= step; i -= step)
		{
			llen = llen ^ ((llen << 5) + (llen >> 2) + value.charAt(i - 1));
		}
		return llen;
	}
}
