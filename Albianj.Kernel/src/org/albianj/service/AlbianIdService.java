package org.albianj.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.albianj.kernel.KernelSetting;
import org.albianj.text.StringHelper;

public class AlbianIdService
{
	//length is 32
	//4-bit kenerl length - 7-bit appName length - 14-bit timestamep + 4-bit serial number
	
	public synchronized static String generate()
	{
		return generate("Kenerl");
	}
	static int serial = 0;
	public synchronized static String generate(String appName)
	{
		serial = 10000 == serial ? 0 : serial+1; 
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMddHHmmss"); 
		String app = appName;
		if(app.length() < 7)
		{
			app = StringHelper.padLeft(app, 7);
		}
		if(app.length() > 7)
		{
			app = app.substring(0,7);
		}
		
		return String.format("%1$s-%2$s-%3$s-%4$04d",
				StringHelper.padLeft(KernelSetting.getKernelId(),4),app,dateFormat.format(new Date())
				,serial);
	}
	
	@SuppressWarnings("static-access")
	public synchronized static String generate32UUID()
	{
		return UUID.randomUUID().randomUUID().toString().replaceAll("-", "");
	}
}
