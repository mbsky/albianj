package org.albianj.service;

import java.util.UUID;

public class AlbianKeyService
{
	//length is 32
	//4-bit kenerl length + 6-bit appName length + 18-bit timestamep + 4-bit serial number
	
	public synchronized static String generate()
	{
		return generate("Kenerl");
	}
	
	public synchronized static String generate(String appName)
	{
		return null;
	}
	
	public synchronized static String generate32UUID()
	{
		return UUID.randomUUID().randomUUID().toString().replaceAll("-", "");
	}
}
